package modelibra.swing.designer.metadomain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import modelibra.ModelibraData;
import modelibra.designer.Designer;
import modelibra.designer.metadomain.MetaDomain;
import modelibra.designer.metadomain.MetaDomains;
import modelibra.swing.app.Start;
import modelibra.swing.app.config.Para;
import modelibra.swing.app.util.FileSelector;
import modelibra.swing.designer.metamodel.ModelsFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.ModelSession;
import org.modelibra.action.AddAction;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.RemoveAction;
import org.modelibra.action.Transaction;
import org.modelibra.action.UpdateAction;
import org.modelibra.swing.app.App;
import org.modelibra.util.PropertiesLoader;

/**
 * Domains window with a table of domains. You can add a new domain, remove the
 * selected domain, and update the selected domain.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-20
 */
public class DomainsFrame extends JFrame implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(DomainsFrame.class);

	public static final int DOMAINS_FRAME_WIDTH = 512;
	public static final int DOMAINS_FRAME_HEIGHT = 160;
	public static final Dimension DOMAINS_FRAME_SIZE = new Dimension(
			DOMAINS_FRAME_WIDTH, DOMAINS_FRAME_HEIGHT);

	private MetaDomain currentDomain;
	private JTable domainTable;
	private int selectedDomainRow = -1;
	private DomainTableModel domainTableModel;

	private JPanel buttonPanel = new JPanel();
	private JButton addButton = new JButton(Para.getOne().getText("add"));
	private JButton removeButton = new JButton(Para.getOne().getText("remove"));
	private JButton modelsButton = new JButton(Para.getOne().getText("models"));

	private String appTitle = Para.getOne().getText("appTitle");
	private DomainsMenuBar domainsMenuBar = new DomainsMenuBar(this);

	private String selectedFile;
	private FileSelector fileSelector;

	private App app;

	private ModelsFrame modelsFrame;

	public DomainsFrame() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		ModelibraData modelibraData = ModelibraData.getOne();
		ModelSession modelSession = modelibraData.getDesigner().getSession();
		domainsMenuBar.setModelSession(modelSession);

		org.modelibra.util.NatLang natLang = new org.modelibra.util.NatLang();
		Properties configurator = PropertiesLoader.load(Start.class,
				Start.APP_CONFIG_LOCAL_PATH);
		String language = configurator.getProperty("lang");
		String textResources = configurator.getProperty("textResources");
		natLang.setNaturalLanguage(language, textResources);
		app = new App(modelibraData.getModelibra(), natLang);
		app.setModelSession(modelSession);

		setJMenuBar(domainsMenuBar);
		// setTitle(appTitle);

		setSize(DOMAINS_FRAME_SIZE);
		setTitle(Para.getOne().getText("domains"));

		domainTableModel = new DomainTableModel();
		domainTable = new JTable(domainTableModel);
		domainTable.getSelectionModel().addListSelectionListener(this);
		setSelectedRow(0);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		JScrollPane jScrollPane = new JScrollPane(domainTable);
		cp.add(jScrollPane, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.white);

		buttonPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Designer designer = ModelibraData.getOne().getDesigner();
				MetaDomains domains = designer.getMetaDomains();
				MetaDomain domain = new MetaDomain(designer);
				domain.setCode(Para.getOne().getText("domain"));
				EntitiesAction action = new AddAction(designer.getSession(),
						domains, domain);
				action.execute();
			}
		});

		buttonPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Designer designer = ModelibraData.getOne().getDesigner();
				MetaDomains domains = designer.getMetaDomains();
				if (currentDomain != null) {
					EntitiesAction action = new RemoveAction(designer
							.getSession(), domains, currentDomain);
					action.execute();
				}
			}
		});

		buttonPanel.add(modelsButton);
		modelsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentDomain != null) {
					modelsFrame = new ModelsFrame(DomainsFrame.this,
							currentDomain);
					int x = (int) DomainsFrame.this.getLocation().getX();
					int y = (int) DomainsFrame.this.getLocation().getY();
					modelsFrame.setLocation(x + DOMAINS_FRAME_WIDTH, y);
					modelsFrame.setVisible(true);
				}
			}
		});

		fileSelector = new FileSelector();
	}

	public App getApp() {
		return app;
	}

	private void setSelectedRow(int ix) {
		if (getRowCount() <= 0)
			return;
		if (ix < 0) {
			ix = getSelectedRow();
		}
		if ((ix >= 0) && (ix <= getRowCount() - 1)) {
			domainTable.setRowSelectionInterval(ix, ix);
			domainTable.scrollRectToVisible(domainTable
					.getCellRect(ix, 0, true));
			selectedDomainRow = ix;
		}
	}

	private int getSelectedRow() {
		return selectedDomainRow;
	}

	private int getRowCount() {
		return domainTable.getRowCount();
	}

	// implemented from ListSelectionListener
	public void valueChanged(ListSelectionEvent e) {
		setSelectedRow(domainTable.getSelectedRow());
		if ((getRowCount() > 0) && (getSelectedRow() >= 0)) {
			Designer designer = ModelibraData.getOne().getDesigner();
			MetaDomain domain = designer.getMetaDomains().getMetaDomain(
					getSelectedRow());
			if (domain != currentDomain) {
				currentDomain = designer.getMetaDomains().getMetaDomain(
						getSelectedRow());
			}
		}
	}

	private class DomainTableModel extends AbstractTableModel implements
			Observer {

		private static final long serialVersionUID = 1L;

		private static final int COLUMN_COUNT = 2;

		public DomainTableModel() {
			super();
			Designer designer = ModelibraData.getOne().getDesigner();
			designer.addObserver(this);
		}

		// implemented from TableModel
		public int getRowCount() {
			Designer designer = ModelibraData.getOne().getDesigner();
			return designer.getMetaDomains().size();
		}

		// implemented from TableModel
		public int getColumnCount() {
			return COLUMN_COUNT;
		}

		// implemented from TableModel
		public Object getValueAt(int r, int c) {
			Object result = null;
			Designer designer = ModelibraData.getOne().getDesigner();
			MetaDomain metaDomain = designer.getMetaDomains().getMetaDomain(r);
			if (c == 0) {
				result = metaDomain.getCode();
			} else if (c == 1) {
				result = metaDomain.getDescription();
			}
			return result;
		}

		@Override
		public String getColumnName(int c) {
			if (c == 0) {
				return Para.getOne().getText("name");
			} else if (c == 1) {
				return Para.getOne().getText("description");
			}
			return "";
		}

		@Override
		public boolean isCellEditable(int r, int c) {
			return true;
		}

		@Override
		public Class<?> getColumnClass(int c) {
			if (getValueAt(0, c) != null) {
				return getValueAt(0, c).getClass();
			}
			return String.class;
		}

		@Override
		public void setValueAt(Object value, int r, int c) {
			Designer designer = ModelibraData.getOne().getDesigner();
			MetaDomain domain = designer.getMetaDomains().getMetaDomain(r);
			MetaDomain domainCopy = domain.copy();
			if (c == 0) {
				domainCopy.setCode((String) value);
			} else if (c == 1) {
				domainCopy.setDescription((String) value);
			}
			EntitiesAction action = new UpdateAction(designer.getSession(),
					designer.getMetaDomains(), domain, domainCopy);
			action.execute();
		}

		// implemented from Observer
		public void update(Observable o, Object arg) {
			try {
				Designer designer = ModelibraData.getOne().getDesigner();
				if (o == designer) {
					if (arg instanceof Transaction) {
						Transaction transaction = (Transaction) arg;
						List<EntitiesAction> entitiesActions = transaction
								.getEntitiesActions();
						for (EntitiesAction entitiesAction : entitiesActions) {
							IEntities<?> entities = entitiesAction
									.getEntities();
							if (entities instanceof MetaDomains) {
								if (entitiesAction instanceof AddAction
										|| entitiesAction instanceof RemoveAction) {
									int ix = designer.getMetaDomains().size() - 1;
									setSelectedRow(ix);
									fireTableDataChanged();
								} else if (entitiesAction instanceof UpdateAction) {
									fireTableDataChanged();
								}
							}
						}
					} else if (arg instanceof EntitiesAction) {
						EntitiesAction entitiesAction = (EntitiesAction) arg;
						IEntities<?> entities = entitiesAction.getEntities();
						if (entities instanceof MetaDomains) {
							if (entitiesAction instanceof AddAction
									|| entitiesAction instanceof RemoveAction) {
								int ix = designer.getMetaDomains().size() - 1;
								setSelectedRow(ix);
								fireTableDataChanged();
							} else if (entitiesAction instanceof UpdateAction) {
								fireTableDataChanged();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("Error in DomainsFrame.DomainTableModel.update: "
						+ e.getMessage());
			}
		}

	}

	private void loadDomains(String filePath) {
		try {
			ModelibraData.getOne().loadDomains(filePath);
			domainTableModel.fireTableDataChanged();
		} catch (Exception e) {
			log.error("Load data error from DiagramFrame.loadData: "
					+ e.getMessage());
		}
	}

	public void saveDomains(String filePath) {
		try {
			ModelibraData.getOne().saveDomains(filePath);
		} catch (Exception e) {
			log.error("Save data error from DiagramFrame.saveData: "
					+ e.getMessage());
		}
	}

	private void emptyDomains() {
		ModelibraData modelibraData = ModelibraData.getOne();
		modelibraData.getDesigner().emptyDomains();
		modelibraData.getDesigner().getSession().getHistory().empty();
	}

	void neww() {
		close();
		selectedFile = fileSelector.selectFile(Para.getOne().getNatLang());
	}

	void open() {
		close();
		selectedFile = fileSelector.selectFile(Para.getOne().getNatLang());
		if (selectedFile != null) {
			loadDomains(selectedFile);
			setSelectedRow(0);
		}
	}

	void close() {
		if (modelsFrame != null) {
			modelsFrame.exit();
		}
		emptyDomains();
		ModelibraData.getOne().getDesigner().getSession().getHistory().empty();
		currentDomain = null;
		selectedFile = null;
	}

	public void save() {
		if (selectedFile != null) {
			saveDomains(selectedFile);
		} else {
			saveAs();
		}
	}

	public void saveAs() {
		selectedFile = fileSelector.selectFile(Para.getOne().getNatLang());
		if (selectedFile != null) {
			save();
		}
	}

	void exit() {
		if (modelsFrame != null) {
			modelsFrame.exit();
		}
		ModelibraData.getOne().close();
		dispose();
		System.exit(0);
	}

}