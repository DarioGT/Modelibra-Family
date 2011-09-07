package org.modelibra.modeler.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.action.AddCommand;
import org.modelibra.modeler.model.action.Command;
import org.modelibra.modeler.model.action.DownCommand;
import org.modelibra.modeler.model.action.RemoveCommand;
import org.modelibra.modeler.model.action.SetCommand;
import org.modelibra.modeler.model.action.UpCommand;
import org.modelibra.modeler.model.ref.Oid;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-05-24
 */
public class AppModel extends EntityModel {

	static final long serialVersionUID = 7168319479760000050L;

	public static final String DEFAULT_PREFIX = "?";
	
	protected String author = "?";

	protected String prefix = DEFAULT_PREFIX;

	// if false: may be used once in an abstract class
	protected boolean oidCreated = true;

	protected boolean dirNameDisplayed = true;
	
	protected boolean blackAndWhite = false;

	protected Collection<DiagramModel> diagrams = new ArrayList<DiagramModel>();

	protected Collection<TypeModel> types = new ArrayList<TypeModel>();

	private static AppModel instance;

	private AppModel() {
		super();
	}

	public static AppModel getSingleton() {
		if (instance == null) {
			instance = new AppModel();
		}
		return instance;
	}

	public int diagramsCount() {
		return diagrams.size();
	}

	public int typesCount() {
		return types.size();
	}

	// begin: overriden from EntityModel to use instance
	public String getName() {
		return instance.name;
	}

	public void setName(String aName) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), instance, "name", instance.name, aName);
		command.execute();
	}

	public String getAlias() {
		return instance.alias;
	}

	public void setAlias(String anAlias) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), instance, "alias", instance.alias, anAlias);
		command.execute();
	}

	// end: overriden from EntityModel
	
	public String getAuthor() {
		return instance.author;
	}

	public void setAuthor(String anAuthor) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), instance, "author", instance.author, anAuthor);
		command.execute();
	}

	public String getPrefix() {
		return instance.prefix;
	}

	public void setPrefix(String aPrefix) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), instance, "prefix", instance.prefix, aPrefix);
		command.execute();
	}

	public boolean isOidCreated() {
		return instance.oidCreated;
	}

	public void setOidCreated(boolean aValue) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), instance, "oidCreated", new Boolean(
				instance.oidCreated), new Boolean(aValue));
		command.execute();
	}

	public TypeModel getOidType() {
		TypeModel typeModel;
		Collection c = instance.types;
		for (Iterator x = c.iterator(); x.hasNext();) {
			typeModel = (TypeModel) x.next();
			if (typeModel.getName().equals("Oid")) {
				return typeModel;
			}
		}
		return null;
	}

	public TypeModel getStringType() {
		TypeModel typeModel;
		Collection c = instance.types;
		for (Iterator x = c.iterator(); x.hasNext();) {
			typeModel = (TypeModel) x.next();
			if (typeModel.getName().equals("String")) {
				return typeModel;
			}
		}
		return null;
	}

	public boolean isDirNameDisplayed() {
		return instance.dirNameDisplayed;
	}

	public void setDirNameDisplayed(boolean aValue) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), instance, "dirNameDisplayed", new Boolean(
				instance.dirNameDisplayed), new Boolean(aValue));
		command.execute();
	}
	
	public boolean isBlackAndWhite() {
		return instance.blackAndWhite;
	}

	public void setBlackAndWhite(boolean aValue) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), instance, "blackAndWhite", new Boolean(
				instance.blackAndWhite), new Boolean(aValue));
		command.execute();
	}

	// attention: a user of this public method obtains a reference to diagrams
	public Collection<DiagramModel> getDiagrams() {
		return instance.diagrams;
	}

	public List<DiagramModel> getDiagramList() {
		return (List<DiagramModel>) instance.diagrams;
	}

	public DiagramModel getFirstDiagram() {
		Collection c = instance.diagrams;
		Iterator x = c.iterator();
		return (DiagramModel) x.next();
	}

	public void add(DiagramModel aDiagramModel) {
		Command command = new AddCommand(Manager.getSingleton()
				.getTransaction(), instance, aDiagramModel, diagrams,
				"diagrams", "appModel");
		command.execute();
	}

	public void remove(DiagramModel aDiagramModel) {
		Command command = new RemoveCommand(Manager.getSingleton()
				.getTransaction(), instance, aDiagramModel, diagrams,
				"diagrams", "appModel");
		command.execute();
	}

	public void removeAllDiagrams() {
		DiagramModel diagramModel;
		Collection c = instance.diagrams;
		for (Iterator x = c.iterator(); x.hasNext();) {
			diagramModel = (DiagramModel) x.next();
			x.remove();
			diagramModel.detach(instance);
		}
	}

	public DiagramModel getDiagram(int ix) {
		return (DiagramModel) getDiagramList().get(ix);
	}

	public DiagramModel getDiagram(Oid oid) {
		DiagramModel diagramModel;
		Collection c = instance.diagrams;
		for (Iterator x = c.iterator(); x.hasNext();) {
			diagramModel = (DiagramModel) x.next();
			if (diagramModel.getOid().equals(oid)) {
				return diagramModel;
			}
		}
		return null;
	}

	public BoxModel getBox(Oid oid) {
		DiagramModel diagramModel;
		BoxModel boxModel;
		Collection c1 = instance.diagrams;
		Collection c2;
		for (Iterator x = c1.iterator(); x.hasNext();) {
			diagramModel = (DiagramModel) x.next();
			c2 = diagramModel.getBoxes();
			for (Iterator y = c2.iterator(); y.hasNext();) {
				boxModel = (BoxModel) y.next();
				if (boxModel.getOid().equals(oid)) {
					return boxModel;
				}
			}
		}
		return null;
	}

	public void moveDiagramUp(int ix) {
		Command command = new UpCommand(
				Manager.getSingleton().getTransaction(), this,
				instance.diagrams, "diagrams", ix);
		command.execute();
	}

	public void moveDiagramDown(int ix) {
		Command command = new DownCommand(Manager.getSingleton()
				.getTransaction(), this, instance.diagrams, "diagrams", ix);
		command.execute();
	}

	public void moveDiagramUp(DiagramModel diagram) {
		int ix = getDiagramList().indexOf(diagram);
		instance.moveDiagramUp(ix);
	}

	public void moveDiagramDown(DiagramModel diagram) {
		int ix = getDiagramList().indexOf(diagram);
		instance.moveDiagramDown(ix);
	}

	// attention: a user of this public method obtains a reference to types
	public Collection<TypeModel> getTypes() {
		return instance.types;
	}

	public List<TypeModel> getTypeList() {
		return (List<TypeModel>) instance.types;
	}

	public void add(TypeModel aTypeModel) {
		Command command = new AddCommand(Manager.getSingleton()
				.getTransaction(), instance, aTypeModel, types, "types",
				"appModel");
		command.execute();
	}

	public void remove(TypeModel aTypeModel) {
		Command command = new RemoveCommand(Manager.getSingleton()
				.getTransaction(), instance, aTypeModel, types, "types",
				"appModel");
		command.execute();
	}

	public void removeAllTypes() {
		TypeModel typeModel;
		Collection c = instance.types;
		for (Iterator x = c.iterator(); x.hasNext();) {
			typeModel = (TypeModel) x.next();
			x.remove();
			typeModel.detach(instance);
		}
	}

	public TypeModel getType(int ix) {
		return (TypeModel) getTypeList().get(ix);
	}

	public TypeModel getType(Oid oid) {
		TypeModel typeModel;
		Collection c = instance.types;
		for (Iterator x = c.iterator(); x.hasNext();) {
			typeModel = (TypeModel) x.next();
			if (typeModel.getOid().equals(oid)) {
				return typeModel;
			}
		}
		return null;
	}

	public TypeModel getType(String aName) {
		TypeModel typeModel;
		Collection c = instance.types;
		for (Iterator x = c.iterator(); x.hasNext();) {
			typeModel = (TypeModel) x.next();
			if (typeModel.getName().equals(aName)) {
				return typeModel;
			}
		}
		return null;
	}

	public void moveTypeUp(int ix) {
		Command command = new UpCommand(
				Manager.getSingleton().getTransaction(), this, instance.types,
				"types", ix);
		command.execute();
	}

	public void moveTypeDown(int ix) {
		Command command = new DownCommand(Manager.getSingleton()
				.getTransaction(), this, instance.types, "types", ix);
		command.execute();
	}

	public void moveTypeUp(TypeModel type) {
		int ix = getTypeList().indexOf(type);
		instance.moveTypeUp(ix);
	}

	public void moveTypeDown(TypeModel type) {
		int ix = getTypeList().indexOf(type);
		instance.moveTypeDown(ix);
	}

	// you may use this.getDiagram
	public boolean hasDiagram(DiagramModel diagram) {
		DiagramModel diagramModel;
		for (Iterator x = instance.diagrams.iterator(); x.hasNext();) {
			diagramModel = (DiagramModel) x.next();
			if (diagram == diagramModel) {
				return true;
			}
		}
		return false;
	}

	public void deepCopyDiagram(DiagramModel diagram) {
		if (instance.hasDiagram(diagram)) {
			DiagramModel diagramModel = new DiagramModel(this);
			diagramModel.deepCopyBoxes(diagram);
			diagramModel.deepCopyLines(diagram);
			diagramModel.copy(diagram);
		}
	}

	public void inheritAll(DiagramModel diagram) {
		if (instance.hasDiagram(diagram)) {
			DiagramModel diagramModel = new DiagramModel(this);
			diagramModel.copy(diagram);
			diagramModel.setExtension(true);
			diagramModel.setExtensionName(diagram.getName());
		}
	}

	public void inheritSome(DiagramModel diagram) {
		if (instance.hasDiagram(diagram)) {
			DiagramModel diagramModel = new DiagramModel(this);
			diagramModel.copyBoxesWithNamesOnly(diagram);
			diagramModel.copy(diagram);
			diagramModel.setExtension(false);
			diagramModel.setExtensionName(diagram.getName());
		}
	}

	public void writeFile(BufferedOutputStream file) {
		try {
			ObjectOutput o = new ObjectOutputStream(file);
			o.writeObject(instance);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void readFile(BufferedInputStream file) {
		try {
			ObjectInput i = new ObjectInputStream(file);
			instance = (AppModel) i.readObject();
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		} catch (IOException e2) {
			System.out.println(e2.getMessage());
		}
	}

}