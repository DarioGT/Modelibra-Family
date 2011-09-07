package modelibra.swing.designer.metamodel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import modelibra.ModelibraData;
import modelibra.designer.metamodel.MetaModel;
import modelibra.swing.app.config.Para;
import modelibra.swing.designer.metaconcept.ConceptsFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DiagramFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(DiagramFrame.class);

	public static final int DIAGRAM_FRAME_WIDTH = ModelsFrame.MODELS_FRAME_WIDTH * 2;
	public static final int DIAGRAM_FRAME_HEIGHT = ModelsFrame.MODELS_FRAME_HEIGHT * 4;
	public static final Dimension DIAGRAM_FRAME_SIZE = new Dimension(
			DIAGRAM_FRAME_WIDTH, DIAGRAM_FRAME_HEIGHT);

	private String appTitle = Para.getOne().getText("appTitle");

	private DiagramMenuBar diagramMenuBar = new DiagramMenuBar(this);
	private DiagramToolBar diagramToolBar = new DiagramToolBar(this);
	private DiagramPanel diagramPanel;
	private JScrollPane scrollPane;

	private ModelsFrame modelsFrame;

	private MetaModel metaModel;

	public DiagramFrame(final ModelsFrame modelsFrame, final MetaModel metaModel) {
		this.modelsFrame = modelsFrame;
		this.metaModel = metaModel;
		try {
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					exit();
				}
			});

			ModelibraData modelibraData = ModelibraData.getOne(); // ??
			diagramPanel = new DiagramPanel(diagramToolBar, metaModel);
			diagramMenuBar.setSession(modelibraData.getDesigner().getSession());

			setJMenuBar(diagramMenuBar);
			setTitle(appTitle);
			setSize(DIAGRAM_FRAME_SIZE);
			Container cp = getContentPane();
			cp.add(diagramToolBar, BorderLayout.NORTH);
			scrollPane = new JScrollPane(diagramPanel);
			cp.add(scrollPane, BorderLayout.CENTER);
		} catch (Exception e) {
			log.error("Error in DiagramFrame.constructor: " + e.getMessage());
		}
	}

	public ModelsFrame getModelsFrame() {
		return modelsFrame;
	}

	public MetaModel getMetaModel() {
		return metaModel;
	}

	public DiagramPanel getDiagramPanel() {
		return diagramPanel;
	}

	void setScrollBars(double x, double y) {
		Double doubleX = new Double(x);
		Double doubleY = new Double(y);
		scrollPane.getHorizontalScrollBar().setValue(doubleX.intValue());
		scrollPane.getVerticalScrollBar().setValue(doubleY.intValue());
	}

	void save() {
		getModelsFrame().getDomainsFrame().save();
	}

	void exit() {
		ConceptsFrame conceptsFrame = diagramMenuBar.getConceptsFrame();
		if (conceptsFrame != null) {
			conceptsFrame.exit();
		}
		ModelibraData.getOne().getDesigner().getSession().getHistory().empty();
		dispose();
	}

}
