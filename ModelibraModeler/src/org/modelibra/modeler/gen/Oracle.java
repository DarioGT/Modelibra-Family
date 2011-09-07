package org.modelibra.modeler.gen;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.model.AppModel;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.DiagramModel;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.model.LineModel;
import org.modelibra.modeler.model.TypeModel;
import org.modelibra.modeler.util.FileHandling;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-00
 */
public class Oracle {

	public static final int NAME_LENGTH_LIMIT = 30;

	private DataOutputStream out;

	private PrintWriter printWriter;

	private static final String BEGIN_COMMENT = "/*********************************************************************";

	private static final String LINE_COMMENT = "----------------------------------------------------------------------";

	private static final String END_COMMENT = "*********************************************************************/";

	private static Oracle instance;

	private Oracle() {
		super();
	}

	public static Oracle getSingleton() {
		if (instance == null) {
			instance = new Oracle();
		}
		return instance;
	}

	public void genDropSchema(DiagramModel diagramModel) {
		try {
			out = null;
			printWriter = null;
			String defaultPath = AppModel.getSingleton().getAlias();
			File file = FileHandling.getSaveFile(defaultPath);
			if (file == null) {
				return;
			}
			out = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(file)));
			printWriter = new PrintWriter(out, true);
			this.printSchemaHeader(diagramModel);
			this.printDropOi();
			Collection boxes = diagramModel.getBoxes();
			BoxModel box;
			int counter = 0;
			for (Iterator x = boxes.iterator(); x.hasNext();) {
				box = (BoxModel) x.next();
				if (!box.isAbstractDef()) {
					this.printDropTable(box);
					counter++;
				}
			}
			this.printSchemaFooter();
			JOptionPane.showMessageDialog(null, // context frame
					Para.getPara().getText("schemaDropped") + " -- " + counter
							+ " " + Para.getPara().getText("tables"), // message
					Para.getPara().getText("info"), // title
					JOptionPane.INFORMATION_MESSAGE); // message type
		} catch (IOException e) {
			System.out.println(e.getMessage()
					+ " // IOException: Drop Schema File");
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}

	private void printDropTable(BoxModel table) {
		printWriter.print("DROP TABLE ");
		if (table.getName().equals("")) {
			printWriter.print("????");
		} else {
			printWriter.print(table.getName());
		}
		printWriter.println(" CASCADE CONSTRAINTS;");
	}

	private void printDropOi() {
		printWriter.println(" ");
		printWriter.println("DROP PROCEDURE P_OI;");
		printWriter.println("DROP SEQUENCE S_OI;");
		printWriter.println();
	}

	public void genCreateSchema(DiagramModel diagramModel) {
		try {
			out = null;
			printWriter = null;
			String defaultPath = AppModel.getSingleton().getAlias();
			File file = FileHandling.getSaveFile(defaultPath);
			if (file == null) {
				return;
			}
			out = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(file)));
			printWriter = new PrintWriter(out, true);
			this.printSchemaHeader(diagramModel);
			Collection boxes = diagramModel.getBoxes();
			BoxModel box;
			ItemModel item;
			int counter = 0;
			for (Iterator x = boxes.iterator(); x.hasNext();) {
				box = (BoxModel) x.next();
				if (!box.isAbstractDef()) {
					this.printTableHeader(box);
					this.printCreateTableBegin(box);
					Collection items = box.getItems();
					for (Iterator y = items.iterator(); y.hasNext();) {
						item = (ItemModel) y.next();
						this.printColumn(item);
					}
					this.printForeignKeyColumns(box);
					this.printPrimaryKey(box);
					this.printUniqueKey(box);
					this.printCreateTableEnd(box);
					counter++;
				}
			}

			/*
			 * Foreign keys are generated after all tables are generated to
			 * avoid FK dependencies (a table must exist before a FK referencing
			 * that table is generated).
			 */
			this.printFKHeader();
			for (Iterator x = boxes.iterator(); x.hasNext();) {
				box = (BoxModel) x.next();
				if (!box.isAbstractDef()) {
					this.printTableHeader(box);
					this.printForeignKeys(box);
					this.printForeignKeyIndexes(box);
				}
			}
			this.printCreateOi();
			this.printSchemaFooter();
			JOptionPane.showMessageDialog(null, // context frame
					Para.getPara().getText("schemaCreated") + " -- " + counter
							+ " " + Para.getPara().getText("tables"), // message
					Para.getPara().getText("info"), // title
					JOptionPane.INFORMATION_MESSAGE); // message type
		} catch (IOException e) {
			System.out.println(e.getMessage()
					+ " // IOException: Create Schema File");
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}

	private void printSchemaHeader(DiagramModel schema) {
		if (schema == null) {
			return;
		}
		printWriter.println();
		printWriter.println();
		printWriter.println(BEGIN_COMMENT);
		printWriter.println(" " + schema.getName());
		printWriter.println(END_COMMENT);
	}

	private void printSchemaFooter() {
		printWriter.println();
		printWriter.println();
		printWriter.println(BEGIN_COMMENT);
		printWriter.println(LINE_COMMENT);
		printWriter.println(END_COMMENT);
	}

	private void printTableHeader(BoxModel table) {
		printWriter.println();
		printWriter.println();
		printWriter.println(BEGIN_COMMENT);
		printWriter.println(" " + table.getName());
		printWriter.println(END_COMMENT);
	}

	private void printFKHeader() {
		printWriter.println();
		printWriter.println();
		printWriter.println(BEGIN_COMMENT);
		printWriter.println(" FKs");
		printWriter.println(END_COMMENT);
	}

	private void printCreateTableBegin(BoxModel table) {
		printWriter.println();
		printWriter.print("CREATE TABLE ");
		if (table.getName().equals("")) {
			printWriter.println("????");
		} else {
			printWriter.println(table.getName());
		}
		printWriter.println("(");
	}

	private void printCreateTableEnd(BoxModel table) {
		printWriter.println();
		printWriter.println(");");
	}

	private void printColumn(ItemModel column) {
		printWriter.print("  " + column.getName() + "  ");
		TypeModel type = column.getTypeModel();
		if (type != null) {
			printWriter.print(type.getDbmsType());
			if (type.getLength() != 0) {
				printWriter.print("(" + Integer.toString(type.getLength()));
				if (type.getNoOfDec() != 0) {
					printWriter
							.print("," + Integer.toString(type.getNoOfDec()));
				}
				printWriter.print(")");
			}
		} else {
			printWriter.print("????");
		}
		if (column.getMin() == 0) {
			printWriter.println(" NULL,");
		} else {
			printWriter.println(" NOT NULL,");
		}
	}

	private void printPrimaryKey(BoxModel table) {
		ItemModel item = table.getOidItem();
		if (item != null) {
			printWriter.print("  CONSTRAINT PK_" + table.getName());
			printWriter.print(" PRIMARY KEY (" + item.getName() + ")");
		} else {
			printWriter.print("  CONSTRAINT ???? PRIMARY KEY (????)");
		}
	}

	private void printUniqueKey(BoxModel table) {
		if (table.hasId()) {
			printWriter.println(",");
			printWriter.print("  CONSTRAINT UK_" + table.getName()
					+ " UNIQUE (");
			boolean first = true;
			String fkNameList = this.sqlIdLine(table);
			if (!fkNameList.equals("")) {
				first = false;
				printWriter.print(fkNameList);
			}
			Collection idColumns = table.getIdItems();
			ItemModel idColumn = null;
			for (Iterator x = idColumns.iterator(); x.hasNext();) {
				idColumn = (ItemModel) x.next();
				if (first) {
					first = false;
				} else {
					printWriter.print(", ");
				}
				printWriter.print(idColumn.getName());
			}
			printWriter.print(")");
		}
	}

	private String sqlIdLine(BoxModel table) {
		Collection<String> fkNames = new ArrayList<String>();
		if (table.hasLine() && table.hasIdLine()) {
			fkNames = sqlDirIdLine(table, 12);
			fkNames.addAll(sqlDirIdLine(table, 21));
		}
		return this.nameList(fkNames);
	}

	private String nameList(Collection<String> c) {
		String name;
		String names = "";
		boolean first = true;
		for (Iterator x = c.iterator(); x.hasNext();) {
			name = (String) x.next();
			if (first) {
				names = name;
				first = false;
			} else {
				names = names + ", " + name;
			}
		}
		return names;
	}

	private Collection<String> sqlDirIdLine(BoxModel table, int dir) {
		String fkColumnName;
		Collection<String> fkNames = new ArrayList<String>();
		BoxModel neighbor;
		String neighborName;
		String neighborDirName = "";
		Collection children;
		if (dir == 12) {
			children = table.getChildren12();
		} else {
			children = table.getChildren21();
		}
		LineModel child = null;
		for (Iterator x = children.iterator(); x.hasNext();) {
			child = (LineModel) x.next();
			if (dir == 12) {
				neighbor = child.getBoxModel2();
				if (child.isTwin()) {
					neighborDirName = child.getDir12Name();
				}
			} else {
				neighbor = child.getBoxModel1();
				if (child.isTwin()) {
					neighborDirName = child.getDir21Name();
				}
			}
			if (!neighbor.isAbstractDef()) {
				neighborName = neighbor.getName();
				if (child.isTwin()) {
					fkColumnName = "oid" + neighborDirName;
				} else {
					fkColumnName = "oid" + neighborName;
				}
				if (dir == 12) {
					if (child.isDir12Id()) {
						fkNames.add(fkColumnName);
					}
				} else {
					if (child.isDir21Id()) {
						fkNames.add(fkColumnName);
					}
				}
			}
		} // end for
		return fkNames;
	}

	private void printForeignKeyColumns(BoxModel table) {
		if (table.hasLine()) {
			this.printDirForeignKeyColumns(table, 12);
			this.printDirForeignKeyColumns(table, 21);
		}
	}

	private void printDirForeignKeyColumns(BoxModel table, int dir) {
		BoxModel neighbor = null;
		String neighborName = null;
		String neighborDirName = null;
		String neighborMin = null;
		TypeModel type = null;
		Collection children = null;
		if (dir == 12) {
			children = table.getChildren12();
		} else {
			children = table.getChildren21();
		}
		LineModel child = null;
		for (Iterator x = children.iterator(); x.hasNext();) {
			child = (LineModel) x.next();
			if (dir == 12) {
				neighbor = child.getBoxModel2();
				if (child.isTwin()) {
					neighborDirName = child.getDir12Name();
				}
			} else {
				neighbor = child.getBoxModel1();
				if (child.isTwin()) {
					neighborDirName = child.getDir21Name();
				}
			}
			if (!neighbor.isAbstractDef()) {
				neighborName = neighbor.getName();
				if (child.isTwin()) {
					printWriter.print("  " + "oid" + neighborDirName + "  ");
				} else {
					printWriter.print("  " + "oid" + neighborName + "  ");
				}
				type = AppModel.getSingleton().getOidType();
				if (type != null) {
					printWriter.print(type.getDbmsType());
					if (type.getLength() != 0) {
						printWriter.print("("
								+ Integer.toString(type.getLength()));
						printWriter.print(")");
					}
				} else {
					printWriter.print("????");
				}
				if (dir == 12) {
					neighborMin = child.getDir12Min();
				} else {
					neighborMin = child.getDir21Min();
				}
				if (neighborMin.equals("0")) {
					printWriter.println(" NULL,");
				} else {
					printWriter.println(" NOT NULL,");
				}
			}
		} // end for
	}

	private void printForeignKeys(BoxModel table) {
		if (table.hasLine()) {
			this.printDirForeignKeys(table, 12);
			this.printDirForeignKeys(table, 21);
			printWriter.println();
		} else {
			printWriter.println();
		}
	}

	private void printDirForeignKeys(BoxModel table, int dir) {
		BoxModel neighbor = null;
		String neighborDirName = null;
		String neighborDeleteRule = null;
		String name = null;
		int count = 1;
		Collection children = null;
		if (dir == 12) {
			children = table.getChildren12();
		} else {
			children = table.getChildren21();
		}
		LineModel child = null;
		for (Iterator x = children.iterator(); x.hasNext();) {
			child = (LineModel) x.next();
			if (dir == 12) {
				neighbor = child.getBoxModel2();
				if (child.isTwin()) {
					neighborDirName = child.getDir12Name();
				}
			} else {
				neighbor = child.getBoxModel1();
				if (child.isTwin()) {
					neighborDirName = child.getDir21Name();
				}
			}
			if (!neighbor.isAbstractDef()) {
				printWriter.println();
				printWriter.println("ALTER TABLE " + table.getName() + " ADD");
				printWriter.println("(");
				if (child.isTwin()) {
					name = "FK_" + table.getName() + neighborDirName;
					if (name.length() > NAME_LENGTH_LIMIT) {
						name = name.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count++;
					}
					printWriter.println("  CONSTRAINT " + name);
					printWriter.println("    FOREIGN KEY (oid"
							+ neighborDirName + ")");
					printWriter.println("    REFERENCES " + neighbor.getName());

				} else {
					name = "FK_" + table.getName() + neighbor.getName();
					if (name.length() > NAME_LENGTH_LIMIT) {
						name = name.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count++;
					}
					printWriter.println("  CONSTRAINT " + name);
					printWriter.println("    FOREIGN KEY (oid"
							+ neighbor.getName() + ")");
					printWriter.println("    REFERENCES " + neighbor.getName());
				}
				if (dir == 12) {
					neighborDeleteRule = child.getDir21DeleteRule();
				} else {
					neighborDeleteRule = child.getDir12DeleteRule();
				}
				if (neighborDeleteRule.equals("CASCADE")) {
					printWriter.println("      ON DELETE CASCADE");
				}
				printWriter.println(");");
			}
		} // end for
	}

	private void printForeignKeyIndexes(BoxModel table) {
		if (table.hasLine()) {
			this.printDirForeignKeyIndexes(table, 12);
			this.printDirForeignKeyIndexes(table, 21);
		}
	}

	private void printDirForeignKeyIndexes(BoxModel table, int dir) {
		BoxModel neighbor = null;
		String neighborDirName = null;
		String name = null;
		int count = 1;
		Collection children = null;
		if (dir == 12) {
			children = table.getChildren12();
		} else {
			children = table.getChildren21();
		}
		LineModel child = null;
		for (Iterator x = children.iterator(); x.hasNext();) {
			child = (LineModel) x.next();
			if (dir == 12) {
				neighbor = child.getBoxModel2();
				if (child.isTwin()) {
					neighborDirName = child.getDir12Name();
				}
			} else {
				neighbor = child.getBoxModel1();
				if (child.isTwin()) {
					neighborDirName = child.getDir21Name();
				}
			}
			if (!neighbor.isAbstractDef()) {
				printWriter.println();
				if (child.isTwin()) {
					name = "IX_" + table.getName() + neighborDirName;
					if (name.length() > NAME_LENGTH_LIMIT) {
						name = name.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count++;
					}
					printWriter.println("CREATE INDEX " + name + " ON "
							+ table.getName() + " (oid" + neighborDirName
							+ " ASC);");
				} else {
					name = "IX_" + table.getName() + neighbor.getName();
					if (name.length() > NAME_LENGTH_LIMIT) {
						name = name.substring(0, NAME_LENGTH_LIMIT - 2)
								+ count++;
					}
					printWriter.println("CREATE INDEX " + name + " ON "
							+ table.getName() + " (oid" + neighbor.getName()
							+ " ASC);");
				}
			}
		} // end for
	}

	private void printOiHeader() {
		printWriter.println(" ");
		printWriter.println(BEGIN_COMMENT);
		printWriter.println(" OI");
		printWriter.println(END_COMMENT);
		printWriter.println(" ");
	}

	private void printCreateOiSequence() {
		printWriter.println("CREATE SEQUENCE S_OI");
		printWriter.println("  INCREMENT BY 1");
		printWriter.println("  START WITH 1;");
		printWriter.println(" ");
	}

	private void printCreateOiProcedure() {
		printWriter
				.println("CREATE OR REPLACE PROCEDURE P_OI (newOi OUT NUMBER)");
		printWriter.println("IS");
		printWriter.println("BEGIN");
		printWriter.println("  SELECT S_OI.NEXTVAL");
		printWriter.println("  INTO newOi");
		printWriter.println("  FROM DUAL;");
		printWriter.println("END;");
		printWriter.println("/");
	}

	private void printCreateOi() {
		this.printOiHeader();
		this.printCreateOiSequence();
		this.printCreateOiProcedure();
	}

}
