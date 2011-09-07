package org.modelibra.modeler.model;

import java.awt.Color;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Observable;

import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.action.Command;
import org.modelibra.modeler.model.action.SetCommand;
import org.modelibra.modeler.model.ref.Oid;
import org.modelibra.modeler.util.TextHandler;

/**
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-03-01
 */
public abstract class EntityModel extends Observable implements Serializable {

	static final long serialVersionUID = 7168319479760002938L;

	public static final String DEFAULT_NAME = "?";

	public static final Color DEFAULT_COLOR = new Color(255, 204, 102);

	protected Oid oid = new Oid();

	protected String name = new String(DEFAULT_NAME);

	protected String nameInPlural = new String(DEFAULT_NAME);

	protected String alias = new String(DEFAULT_NAME);

	protected Color color = DEFAULT_COLOR;

	public EntityModel() {
		super();
	}

	public Oid getOid() {
		return oid;
	}

	public void setOid(Oid anOid) {
		oid = anOid;
	}

	public String getName() {
		return name;
	}

	public void setName(String aName) {
		if(!getName().equals(aName)){
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "name", name, aName);
			command.execute();
			setNameInPlural(putNameInPlural());
		}		
	}

	private String putNameInPlural() {
		String plural = "?";
		/*if (/*nameInPlural == null || nameInPlural.trim().equals("?")
				|| nameInPlural.trim().equals("")) {*/
			if (name != null && !name.trim().equals("?")
					&& !name.trim().equals("")) {
				Config config = Config.getConfig();
				String langKey = config.getProperty("language");
				if (langKey.equals("en")) {
					TextHandler textHandler = new TextHandler();
					plural = textHandler.putInEnglishPlural(name);
				} else if (langKey.equals("fr")) {
					plural = name + "s";
				} else if (langKey.equals("ba")) {
					plural = name + "i";
				}
			}
		//}
		return plural;
	}

	public String getNameInPlural() {
		return nameInPlural;
	}

	public void setNameInPlural(String aNameInPlural) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "nameInPlural", nameInPlural,
				aNameInPlural);
		command.execute();
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String anAlias) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "alias", alias, anAlias);
		command.execute();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color aColor) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "color", color, aColor);
		command.execute();
	}

	public void setColor(int aRed, int aGreen, int aBlue) {
		this.setColor(new Color(aRed, aGreen, aBlue));
	}
	
	public int getRed() {
		return color.getRed();
	}

	public void setRed(int aRed) {
		if ((0 <= aRed) && (aRed <= 255)) {
			this.setColor(aRed, color.getGreen(), color.getBlue());
		}
	}

	public int getGreen() {
		return color.getGreen();
	}

	public void setGreen(int aGreen) {
		if ((0 <= aGreen) && (aGreen <= 255)) {
			this.setColor(color.getRed(), aGreen, color.getBlue());
		}
	}

	public int getBlue() {
		return color.getBlue();
	}

	public void setBlue(int aBlue) {
		if ((0 <= aBlue) && (aBlue <= 255)) {
			this.setColor(color.getRed(), color.getGreen(), aBlue);
		}
	}

	// overriden from Observable
	public void notifyObservers(Object arg) {
		this.setChanged();
		super.notifyObservers(arg);
	}

	private Field getDeclaredOrInheritedField(String aFieldName, Class aClass) {
		Field f = null;
		try {
			f = aClass.getDeclaredField(aFieldName);
		} catch (NoSuchFieldException e) {
			Class superClass = aClass.getSuperclass();
			if (superClass != null) {
				f = getDeclaredOrInheritedField(aFieldName, superClass);
			} else {
				System.out.println("Field not found: " + e.getMessage());
				return null;
			}
		}
		return f;
	}

	public Object getProperty(String aPropertyName) {
		Field f = getDeclaredOrInheritedField(aPropertyName, this.getClass());
		try {
			return f.get(this);
		} catch (IllegalAccessException e1) {
			System.out.println("Field not geted: " + aPropertyName + ", "
					+ e1.getMessage());
		} catch (IllegalArgumentException e2) {
			System.out.println("Field not geted: " + aPropertyName + ", "
					+ e2.getMessage());
		}
		return null;
	}

	public void setProperty(String aPropertyName, Object aNewValue) {
		Field f = getDeclaredOrInheritedField(aPropertyName, this.getClass());
		try {
			f.set(this, aNewValue);
		} catch (IllegalAccessException e1) {
			System.out.println("Field not seted: " + aPropertyName + ", "
					+ e1.getMessage());
		} catch (IllegalArgumentException e2) {
			System.out.println("Field not seted: " + aPropertyName + ", "
					+ e2.getMessage());
		}
	}

	public String allLettersToLower(String text) {
		TextHandler textHandler = new TextHandler();
		return textHandler.allLettersToLower(text);
	}

	public String firstLetterToUpper(String text) {
		TextHandler textHandler = new TextHandler();
		return textHandler.firstLetterToUpper(text);
	}

	public String firstLetterToLower(String text) {
		TextHandler textHandler = new TextHandler();
		return textHandler.firstLetterToLower(text);
	}

	protected void copy(EntityModel entityModel) {
		if (entityModel != null) {
			this.setName(entityModel.getName());
			this.setNameInPlural(entityModel.getNameInPlural());
			this.setAlias(entityModel.getAlias());
			this.setColor(entityModel.getColor());
		}
	}

}