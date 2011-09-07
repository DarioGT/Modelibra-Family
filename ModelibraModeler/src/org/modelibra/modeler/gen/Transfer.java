package org.modelibra.modeler.gen;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.AppModel;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.DiagramModel;
import org.modelibra.modeler.model.ElementModel;
import org.modelibra.modeler.model.EntityModel;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.model.LineModel;
import org.modelibra.modeler.model.TypeModel;
import org.modelibra.modeler.model.ref.Oid;
import org.modelibra.modeler.util.FileHandling;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-30
 */
public class Transfer {

	private static void informUser(String msg) {
		JOptionPane.showMessageDialog(null, // context frame
				msg, // message
				Para.getPara().getText("info"), // title
				JOptionPane.INFORMATION_MESSAGE); // message type
	}

	private static void readTypes(DataInputStream in) {
		try {
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String s, sType;
			StringTokenizer st;
			int noOfTypes = 0;
			Manager.getSingleton().startTransaction("import types"); // Transaction
			while ((s = inBr.readLine()) != null) {
				st = new StringTokenizer(s, ",");
				sType = st.nextToken();
				if (sType.equals("T")) {
					noOfTypes++;
					importType(st);
				}
			}
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------

			informUser(noOfTypes + " "
					+ Para.getPara().getText("typesImported"));
			in.close();
		} catch (EOFException e1) {
			System.out.println(e1.getMessage()
					+ " // EOFException: Import Types!");
		} catch (IOException e2) {
			System.out.println(e2.getMessage()
					+ " // IOException: Import Types!");
		} catch (NoSuchElementException e3) {
			System.out.println(e3.getMessage()
					+ " // NoSuchElementException: Import Types!");
		}
	}

	public static void importTypes() {
		File file = FileHandling
				.getOpenFile(AppModel.getSingleton().getAlias());
		if (file != null) {
			importTypes(file);
		}
	}

	public static void importTypes(File file) {
		try {
			DataInputStream in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			readTypes(in);
		} catch (IOException e2) {
			System.out.println(e2.getMessage()
					+ " // IOException: Import Types!");
		}
	}

	public static void importTypes(InputStream inputStream) {
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				inputStream));
		readTypes(in);
	}

	public static void importTypes(URL typesUrl) {
		try {
			InputStream inputStream = typesUrl.openStream();
			importTypes(inputStream);
		} catch (IOException e) {
			System.out.println(e.getMessage() + " // no .types file!");
			informUser(Para.getPara().getText("checkURL"));
		}
	}

	public static void exportTypes() {
		if (!AppModel.getSingleton().getTypes().isEmpty()) {
			try {
				DataOutputStream out = null;
				File file = FileHandling.getSaveFile(AppModel.getSingleton()
						.getAlias());
				if (file == null) {
					return;
				}
				out = new DataOutputStream(new BufferedOutputStream(
						new FileOutputStream(file)));

				exportTitle(out, " === Types === ");
				Collection c = AppModel.getSingleton().getTypes();
				TypeModel type;
				for (Iterator x = c.iterator(); x.hasNext();) {
					type = (TypeModel) x.next();
					exportType(out, type);
				}

				informUser(c.size() + " "
						+ Para.getPara().getText("typesExported"));
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println(e.getMessage()
						+ " // IOException: Export Types!");
			}
		}
	}

	public static void importDiagrams() {
		try {
			DataInputStream in = null;
			File file = FileHandling.getOpenFile(AppModel.getSingleton()
					.getAlias());
			if (file == null) {
				return;
			}
			in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String s, sType;
			StringTokenizer st;
			int noOfDiagrams = 0;
			DiagramModel diagram = null;
			BoxModel box = null;
			Manager.getSingleton().startTransaction("import diagrams"); // Transaction
			while ((s = inBr.readLine()) != null) {
				st = new StringTokenizer(s, ",");
				sType = st.nextToken();
				if (sType.equals("D")) {
					noOfDiagrams++;
					diagram = importDiagram(st);
				} else if (sType.equals("B")) {
					box = importBox(st, diagram);
				} else if (sType.equals("I"))
					importItem(st, box);
				else if (sType.equals("L"))
					importLine(st, diagram);
			}
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------

			informUser(noOfDiagrams + " "
					+ Para.getPara().getText("diagramsImported"));
			in.close();
		} catch (EOFException e1) {
			System.out.println(e1.getMessage()
					+ " // EOFException: Import Diagrams!");
		} catch (IOException e2) {
			System.out.println(e2.getMessage()
					+ " // IOException: Import Diagrams!");
		} catch (NoSuchElementException e3) {
			System.out.println(e3.getMessage()
					+ " // NoSuchElementException: Import Diagrams!");
		}
	}

	public static void importBoxesAndLines(DiagramModel diagram) {
		try {
			DataInputStream in = null;
			File file = FileHandling.getOpenFile(AppModel.getSingleton()
					.getAlias());
			if (file == null) {
				return;
			}
			in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String s, sType;
			StringTokenizer st;
			int noOfBoxes = 0;
			BoxModel box = null;
			Manager.getSingleton().startTransaction("import boxes and lines"); // Transaction
			while ((s = inBr.readLine()) != null) {
				st = new StringTokenizer(s, ",");
				sType = st.nextToken();
				if (sType.equals("B")) {
					noOfBoxes++;
					box = importBox(st, diagram);
				} else if (sType.equals("I"))
					importItem(st, box);
				else if (sType.equals("L"))
					importLine(st, diagram);
			}
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------

			informUser(noOfBoxes + " "
					+ Para.getPara().getText("boxesImported"));
			in.close();
		} catch (EOFException e1) {
			System.out.println(e1.getMessage()
					+ " // EOFException: Import Boxes and Lines!");
		} catch (IOException e2) {
			System.out.println(e2.getMessage()
					+ " // IOException: Import Boxes and Lines!");
		} catch (NoSuchElementException e3) {
			System.out.println(e3.getMessage()
					+ " // NoSuchElementException: Import Boxes and Lines!");
		}
	}

	public static void importItems(BoxModel box) {
		try {
			DataInputStream in = null;
			File file = FileHandling.getOpenFile(AppModel.getSingleton()
					.getAlias());
			if (file == null) {
				return;
			}
			in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String s, sType;
			StringTokenizer st;
			int noOfItems = 0;
			Manager.getSingleton().startTransaction("import items"); // Transaction
			while ((s = inBr.readLine()) != null) {
				st = new StringTokenizer(s, ",");
				sType = st.nextToken();
				if (sType.equals("I")) {
					noOfItems++;
					importItem(st, box);
				}
			}
			Manager.getSingleton().commit(); // Transaction
			// ------------------------------

			informUser(noOfItems + " "
					+ Para.getPara().getText("itemsImported"));
			in.close();
		} catch (EOFException e1) {
			System.out.println(e1.getMessage()
					+ " // EOFException: Import Items!");
		} catch (IOException e2) {
			System.out.println(e2.getMessage()
					+ " // IOException: Import Items!");
		} catch (NoSuchElementException e3) {
			System.out.println(e3.getMessage()
					+ " // NoSuchElementException: Import Items!");
		}
	}

	private static Oid getOid(StringTokenizer st) throws NoSuchElementException {
		try {
			Oid oid = new Oid();
			String timeStamp = st.nextToken();
			oid.setTimeStamp((Long.valueOf(timeStamp)).longValue());
			String sequence = st.nextToken();
			oid.setSequence((Integer.valueOf(sequence)).intValue());
			return oid;
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static void importEntity(StringTokenizer st, EntityModel entity)
			throws NoSuchElementException {
		try {
			Oid oid = getOid(st);
			if (oid != null)
				entity.setOid(oid);

			String name = st.nextToken();
			entity.setName(name);
			String nameInPlural = st.nextToken();
			entity.setNameInPlural(nameInPlural);
			String alias = st.nextToken();
			entity.setAlias(alias);

			String red = st.nextToken();
			entity.setRed((Integer.valueOf(red)).intValue());
			String green = st.nextToken();
			entity.setGreen((Integer.valueOf(green)).intValue());
			String blue = st.nextToken();
			entity.setBlue((Integer.valueOf(blue)).intValue());
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void importElement(StringTokenizer st, ElementModel entity)
			throws NoSuchElementException {
		String visible = st.nextToken();
		entity.setVisible((new Boolean(visible)).booleanValue());
		String selected = st.nextToken();
		entity.setSelected((new Boolean(selected)).booleanValue());
	}

	private static void importType(StringTokenizer st)
			throws NoSuchElementException {
		try {
			AppModel app = AppModel.getSingleton();

			TypeModel type = new TypeModel(app);
			importEntity(st, type);

			String baseType = st.nextToken();
			type.setDbmsType(baseType);
			String length = st.nextToken();
			type.setLength((Integer.valueOf(length)).intValue());
			String noOfDec = st.nextToken();
			type.setNoOfDec((Integer.valueOf(noOfDec)).intValue());
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage()
					+ " // Error in Transfer.importType!");
		}
	}

	private static DiagramModel importDiagram(StringTokenizer st)
			throws NoSuchElementException {
		try {
			AppModel app = AppModel.getSingleton();

			DiagramModel diagram = new DiagramModel(app);
			importEntity(st, diagram);

			String author = st.nextToken();
			diagram.setAuthor(author);
			String extension = st.nextToken();
			diagram.setExtension((new Boolean(extension)).booleanValue());
			String extensionName = st.nextToken();
			diagram.setExtensionName(extensionName);
			String abstractDef = st.nextToken();
			diagram.setAbstractDef((new Boolean(abstractDef)).booleanValue());	
			String persistence = st.nextToken();
			diagram.setPersistence(persistence);
			String oidCreated = st.nextToken();
			diagram.setOidCreated((new Boolean(oidCreated)).booleanValue());	
			String dirNameDisplayed = st.nextToken();
			diagram.setDirNameDisplayed((new Boolean(dirNameDisplayed)).booleanValue());							
			String width = st.nextToken();
			diagram.setWidth((Integer.valueOf(width)).intValue());
			String height = st.nextToken();
			diagram.setHeight((Integer.valueOf(height)).intValue());

			return diagram;
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static BoxModel importBox(StringTokenizer st, DiagramModel aDiagram)
			throws NoSuchElementException {
		try {
			AppModel app = AppModel.getSingleton();

			Oid diagramOid = getOid(st);
			DiagramModel diagram;
			if (aDiagram == null)
				diagram = app.getDiagram(diagramOid);
			else
				diagram = aDiagram;
			if (diagram == null)
				return null;
			BoxModel box = new BoxModel(diagram);

			String extension = st.nextToken();
			box.setExtension((new Boolean(extension)).booleanValue());
			String extensionName = st.nextToken();
			box.setExtensionName(extensionName);
			String abstractDef = st.nextToken();
			box.setAbstractDef((new Boolean(abstractDef)).booleanValue());
			String entry = st.nextToken();
			box.setEntry((new Boolean(entry)).booleanValue());
			String x = st.nextToken();
			box.setX((Integer.valueOf(x)).intValue());
			String y = st.nextToken();
			box.setY((Integer.valueOf(y)).intValue());
			String width = st.nextToken();
			box.setWidth((Integer.valueOf(width)).intValue());
			String height = st.nextToken();
			box.setHeight((Integer.valueOf(height)).intValue());

			importEntity(st, box);
			importElement(st, box);

			return box;
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static void importItem(StringTokenizer st, BoxModel aBox)
			throws NoSuchElementException {
		try {
			AppModel app = AppModel.getSingleton();

			Oid boxOid = getOid(st);
			BoxModel box;
			if (aBox == null)
				box = app.getBox(boxOid);
			else
				box = aBox;
			if (box == null)
				return;
			ItemModel item = new ItemModel(box);

			Oid typeOid = getOid(st);
			String typeName = st.nextToken();

			if ((typeOid != null) && (typeOid.getTimeStamp() != 0)) {
				// TypeModel type = app.getType(typeOid);
				TypeModel type = app.getType(typeName);
				if (type != null)
					item.attach(type);
			}

			importEntity(st, item);

			String min = st.nextToken();
			item.setMin((Integer.valueOf(min)).intValue());
			String max = st.nextToken();
			item.setMax((Integer.valueOf(max)).intValue());
			String oid = st.nextToken();
			item.setOid((new Boolean(oid)).booleanValue());
			String id = st.nextToken();
			item.setId((new Boolean(id)).booleanValue());
			String defaultValue = st.nextToken();
			item.setDefaultValue(defaultValue);
			String increment = st.nextToken();
			item.setIncrement((new Boolean(increment)).booleanValue());
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void importLine(StringTokenizer st, DiagramModel aDiagram)
			throws NoSuchElementException {
		try {
			AppModel app = AppModel.getSingleton();

			Oid diagramOid = getOid(st);
			DiagramModel diagram;
			if (aDiagram == null)
				diagram = app.getDiagram(diagramOid);
			else
				diagram = aDiagram;
			if (diagram == null)
				return;

			Oid box1Oid = getOid(st);
			BoxModel box1 = diagram.getBox(box1Oid);
			if (box1 == null)
				return;

			Oid box2Oid = getOid(st);
			BoxModel box2 = diagram.getBox(box2Oid);
			if (box2 == null)
				return;

			LineModel line = new LineModel(diagram, box1, box2);

			String inheritance = st.nextToken();
			line.setInheritance((new Boolean(inheritance)).booleanValue());
			String internal = st.nextToken();
			line.setInternal((new Boolean(internal)).booleanValue());
			String partOfManyToMany = st.nextToken();
			line.setPartOfManyToMany((new Boolean(partOfManyToMany)).booleanValue());

			String dir12Min = st.nextToken();
			line.setDir12Min(dir12Min);
			String dir12Max = st.nextToken();
			line.setDir12Max(dir12Max);
			String dir12Id = st.nextToken();
			line.setDir12Id((new Boolean(dir12Id)).booleanValue());
			String dir12Name = st.nextToken();
			line.setDir12Name(dir12Name);
			String dir12NameVisible = st.nextToken();
			line.setDir12NameVisible((new Boolean(dir12NameVisible))
					.booleanValue());
			String dir12InsertRule = st.nextToken();
			line.setDir12InsertRule(dir12InsertRule);
			String dir12DeleteRule = st.nextToken();
			line.setDir12DeleteRule(dir12DeleteRule);
			String dir12UpdateRule = st.nextToken();
			line.setDir12UpdateRule(dir12UpdateRule);

			String dir21Min = st.nextToken();
			line.setDir21Min(dir21Min);
			String dir21Max = st.nextToken();
			line.setDir21Max(dir21Max);
			String dir21Id = st.nextToken();
			line.setDir21Id((new Boolean(dir21Id)).booleanValue());
			String dir21Name = st.nextToken();
			line.setDir21Name(dir21Name);
			String dir21NameVisible = st.nextToken();
			line.setDir21NameVisible((new Boolean(dir21NameVisible))
					.booleanValue());
			String dir21InsertRule = st.nextToken();
			line.setDir21InsertRule(dir21InsertRule);
			String dir21DeleteRule = st.nextToken();
			line.setDir21DeleteRule(dir21DeleteRule);
			String dir21UpdateRule = st.nextToken();
			line.setDir21UpdateRule(dir21UpdateRule);

			importEntity(st, line);
			importElement(st, line);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void exportDiagrams() {
		Collection c1, c2, c3;
		try {
			DataOutputStream out = null;
			File file = FileHandling.getSaveFile(AppModel.getSingleton()
					.getAlias());
			if (file == null) {
				return;
			}
			out = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(file)));

			exportTitle(out, " === Diagrams === ");
			c1 = AppModel.getSingleton().getDiagrams();
			DiagramModel diagram;
			BoxModel box;
			ItemModel item;
			LineModel line;
			for (Iterator x = c1.iterator(); x.hasNext();) {
				diagram = (DiagramModel) x.next();
				exportTitle(out, " --- Diagram --- ");
				exportDiagram(out, diagram);

				exportTitle(out, " === Boxes === ");
				c2 = diagram.getBoxes();
				for (Iterator y = c2.iterator(); y.hasNext();) {
					box = (BoxModel) y.next();
					exportTitle(out, " --- Box --- ");
					exportBox(out, box);

					exportTitle(out, " === Items === ");
					c3 = box.getItems();
					for (Iterator z = c3.iterator(); z.hasNext();) {
						item = (ItemModel) z.next();
						exportItem(out, item);
					}
				}

				exportTitle(out, " === Lines === ");
				c2 = diagram.getLines();
				for (Iterator y = c2.iterator(); y.hasNext();) {
					line = (LineModel) y.next();
					exportLine(out, line);
				}
			}

			informUser(c1.size() + " "
					+ Para.getPara().getText("diagramsExported"));
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage()
					+ " // IOException: Export Diagrams!");
		}
	}

	public static void exportSelection(DiagramModel diagram) {
		Collection c1, c2;
		try {
			DataOutputStream out = null;
			File file = FileHandling.getSaveFile(AppModel.getSingleton()
					.getAlias());
			if (file == null) {
				return;
			}
			out = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(file)));

			exportTitle(out, " === Boxes === ");
			int noOfBoxes = 0;
			BoxModel box;
			ItemModel item;
			LineModel line;
			c1 = diagram.getBoxes();
			for (Iterator y = c1.iterator(); y.hasNext();) {
				box = (BoxModel) y.next();
				if (box.isSelected()) {
					exportTitle(out, " --- Box --- ");
					exportBox(out, box);
					noOfBoxes++;

					exportTitle(out, " === Items === ");
					c2 = box.getItems();
					for (Iterator z = c2.iterator(); z.hasNext();) {
						item = (ItemModel) z.next();
						exportItem(out, item);
					}
				}
			}

			exportTitle(out, " === Lines === ");
			c1 = diagram.getLines();
			for (Iterator y = c1.iterator(); y.hasNext();) {
				line = (LineModel) y.next();
				if (line.isSelected())
					exportLine(out, line);
			}

			informUser(noOfBoxes + " "
					+ Para.getPara().getText("boxesExported"));
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage()
					+ " // IOException: Export Selection!");
		}
	}

	public static void exportItems(BoxModel box) {
		Collection c;
		try {
			DataOutputStream out = null;
			File file = FileHandling.getSaveFile(AppModel.getSingleton()
					.getAlias());
			if (file == null) {
				return;
			}
			out = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(file)));

			int noOfItems = 0;
			ItemModel item = null;
			exportTitle(out, " === Items === ");
			c = box.getItems();
			for (Iterator z = c.iterator(); z.hasNext();) {
				item = (ItemModel) z.next();
				exportItem(out, item);
				noOfItems++;
			}

			informUser(noOfItems + " "
					+ Para.getPara().getText("itemsExported"));
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage()
					+ " // IOException: Export Items!");
		}
	}

	private static void exportTitle(DataOutputStream out, String title)
			throws IOException {
		out.writeBytes(" ");
		out.writeBytes("\r"); // CR
		out.writeBytes("\n"); // new line

		out.writeBytes("C," + title);
		out.writeBytes("\r"); // CR
		out.writeBytes("\n"); // new line

		out.writeBytes(" ");
		out.writeBytes("\r"); // CR
		out.writeBytes("\n"); // new line
	}

	private static void exportEntity(DataOutputStream out, EntityModel entity)
			throws IOException {
		out.writeBytes(",");
		out.writeBytes(Long.toString(entity.getOid().getTimeStamp()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getOid().getSequence()));
		
		out.writeBytes(",");
		out.writeBytes(entity.getName());
		out.writeBytes(",");
		out.writeBytes(entity.getNameInPlural());
		out.writeBytes(",");
		out.writeBytes(entity.getAlias());
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getRed()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getGreen()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getBlue()));
	}

	private static void exportElement(DataOutputStream out, ElementModel entity)
			throws IOException {
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isVisible())).toString());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isSelected())).toString());
	}

	private static void exportType(DataOutputStream out, TypeModel entity)
			throws IOException {
		out.writeBytes("T");

		exportEntity(out, entity);

		out.writeBytes(",");
		out.writeBytes(entity.getDbmsType());
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getLength()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getNoOfDec()));

		out.writeBytes("\r"); // CR
		out.writeBytes("\n"); // new line
	}

	private static void exportDiagram(DataOutputStream out, DiagramModel entity)
			throws IOException {
		out.writeBytes("D");

		exportEntity(out, entity);

		out.writeBytes(",");
		out.writeBytes(entity.getAuthor());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isExtension())).toString());
		out.writeBytes(",");
		out.writeBytes(entity.getExtensionName());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isAbstractDef())).toString());	
		out.writeBytes(",");
		out.writeBytes(entity.getPersistence());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isOidCreated())).toString());	
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isDirNameDisplayed())).toString());
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getWidth()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getHeight()));

		out.writeBytes("\r"); // CR
		out.writeBytes("\n"); // new line
	}

	private static void exportBox(DataOutputStream out, BoxModel entity)
			throws IOException {
		out.writeBytes("B");

		out.writeBytes(",");
		out.writeBytes(Long.toString(entity.getDiagramModel().getOid()
				.getTimeStamp()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getDiagramModel().getOid()
				.getSequence()));

		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isExtension())).toString());
		out.writeBytes(",");
		out.writeBytes(entity.getExtensionName());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isAbstractDef())).toString());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isEntry())).toString());
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getX()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getY()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getWidth()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getHeight()));

		exportEntity(out, entity);
		exportElement(out, entity);

		out.writeBytes("\r"); // CR
		out.writeBytes("\n"); // new line
	}

	private static void exportItem(DataOutputStream out, ItemModel entity)
			throws IOException {
		out.writeBytes("I");

		out.writeBytes(",");
		out.writeBytes(Long.toString(entity.getBoxModel().getOid()
				.getTimeStamp()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getBoxModel().getOid()
				.getSequence()));

		if (entity.getTypeModel() != null) {
			out.writeBytes(",");
			out.writeBytes(Long.toString(entity.getTypeModel().getOid()
					.getTimeStamp()));
			out.writeBytes(",");
			out.writeBytes(Integer.toString(entity.getTypeModel().getOid()
					.getSequence()));
			out.writeBytes(",");
			out.writeBytes(entity.getTypeModel().getName());
		} else {
			out.writeBytes(",");
			out.writeBytes("0");
			out.writeBytes(",");
			out.writeBytes("0");
			out.writeBytes(",");
			out.writeBytes(" ");
		}

		exportEntity(out, entity);

		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getMin()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getMax()));
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isOid())).toString());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isId())).toString());
		out.writeBytes(",");
		out.writeBytes(entity.getDefaultValue());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isIncrement())).toString());

		out.writeBytes("\r"); // CR
		out.writeBytes("\n"); // new line
	}

	private static void exportLine(DataOutputStream out, LineModel entity)
			throws IOException {
		out.writeBytes("L");

		out.writeBytes(",");
		out.writeBytes(Long.toString(entity.getDiagramModel().getOid()
				.getTimeStamp()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getDiagramModel().getOid()
				.getSequence()));

		out.writeBytes(",");
		out.writeBytes(Long.toString(entity.getBoxModel1().getOid()
				.getTimeStamp()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getBoxModel1().getOid()
				.getSequence()));

		out.writeBytes(",");
		out.writeBytes(Long.toString(entity.getBoxModel2().getOid()
				.getTimeStamp()));
		out.writeBytes(",");
		out.writeBytes(Integer.toString(entity.getBoxModel2().getOid()
				.getSequence()));

		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isInheritance())).toString());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isInternal())).toString());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isPartOfManyToMany())).toString());

		out.writeBytes(",");
		out.writeBytes(entity.getDir12Min());
		out.writeBytes(",");
		out.writeBytes(entity.getDir12Max());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isDir12Id())).toString());
		out.writeBytes(",");
		out.writeBytes(entity.getDir12Name());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isDir12NameVisible())).toString());
		out.writeBytes(",");
		out.writeBytes(entity.getDir12InsertRule());
		out.writeBytes(",");
		out.writeBytes(entity.getDir12DeleteRule());
		out.writeBytes(",");
		out.writeBytes(entity.getDir12UpdateRule());

		out.writeBytes(",");
		out.writeBytes(entity.getDir21Min());
		out.writeBytes(",");
		out.writeBytes(entity.getDir21Max());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isDir21Id())).toString());
		out.writeBytes(",");
		out.writeBytes(entity.getDir21Name());
		out.writeBytes(",");
		out.writeBytes((new Boolean(entity.isDir21NameVisible())).toString());
		out.writeBytes(",");
		out.writeBytes(entity.getDir21InsertRule());
		out.writeBytes(",");
		out.writeBytes(entity.getDir21DeleteRule());
		out.writeBytes(",");
		out.writeBytes(entity.getDir21UpdateRule());

		exportEntity(out, entity);
		exportElement(out, entity);

		out.writeBytes("\r"); // CR
		out.writeBytes("\n"); // new line
	}

}
