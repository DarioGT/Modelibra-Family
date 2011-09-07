package org.modelibra.modeler.app.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.AppModel;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.model.TypeModel;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public class TypeLookupForm extends TypesForm {
	
	static final long serialVersionUID = 7168319479760000380L;

	private JButton attachButton = new JButton(Para.getPara().getText("attach"));

	private AppModel appModel = AppModel.getSingleton();

	private static ItemsForm itemsForm;

	private static ItemModel itemModel;

	public TypeLookupForm(ItemsForm anItemsForm, ItemModel anItemModel) {
		super();
		try {
			itemsForm = anItemsForm;
			itemModel = anItemModel;
			this.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// this.pack();
	}

	private void init() throws Exception {
		this.setTitle(Para.getPara().getText("lookupType"));
		buttonPanel.add(attachButton);
		attachButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((jTable.getRowCount() > 0)
						&& (jTable.getSelectedRow() >= 0)) {
					int ix = jTable.getSelectedRow();
					TypeModel typeModel = appModel.getType(ix);
					Manager.getSingleton().startTransaction(
							"attach item to type"); // Transaction
					itemModel.setTypeModel(typeModel); // detach & attach
					Manager.getSingleton().commit(); // Transaction
														// ------------------------------
					itemsForm.selectNextRow();
				}
			}
		});
	}

	public ItemModel getItemModel() {
		return itemModel;
	}

	public void setItemModel(ItemModel anItemModel) {
		itemModel = anItemModel;
	}

}
