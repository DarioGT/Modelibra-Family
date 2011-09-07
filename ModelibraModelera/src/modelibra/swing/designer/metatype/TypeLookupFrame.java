package modelibra.swing.designer.metatype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modelibra.ModelibraData;
import modelibra.designer.Designer;
import modelibra.designer.metaproperty.MetaProperties;
import modelibra.designer.metaproperty.MetaProperty;
import modelibra.designer.metatype.MetaType;
import modelibra.designer.metatype.MetaTypes;
import modelibra.swing.app.config.Para;
import modelibra.swing.designer.metaproperty.PropertiesFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.UpdateAction;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-05-20
 */
public class TypeLookupFrame extends TypesFrame {

	static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(TypeLookupFrame.class);

	private JButton attachButton = new JButton(Para.getOne().getText("attach"));

	private MetaProperty currentProperty;

	public TypeLookupFrame(final PropertiesFrame propertiesFrame,
			final MetaProperty property) {
		this.currentProperty = property;
		setTitle(Para.getOne().getText("lookup"));
		buttonPanel.add(attachButton);
		attachButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((typeTable.getRowCount() > 0)
						&& (typeTable.getSelectedRow() >= 0)) {
					int ix = typeTable.getSelectedRow();
					Designer designer = ModelibraData.getOne().getDesigner();
					MetaTypes types = designer.getMetaTypes();
					MetaType type = types.getMetaType(ix);

					MetaProperties properties = currentProperty
							.getMetaConcept().getMetaProperties();
					MetaProperty propertyCopy = currentProperty.copy();
					propertyCopy.setMetaType(type);
					EntitiesAction action = new UpdateAction(designer
							.getSession(), properties, currentProperty,
							propertyCopy);
					action.execute();

					if (propertiesFrame != null) {
						propertiesFrame.selectNextRow();
						currentProperty = propertiesFrame.getCurrentProperty();
					}
				}
			}
		});
	}

	public void sertCurrentProperty(MetaProperty property) {
		currentProperty = property;
	}

}
