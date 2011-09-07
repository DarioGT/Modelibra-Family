package org.modelibra.modeler.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author Vincent Dussault
 * @author Dzenan Ridjanovic
 * @version 2006-12-09
 */
public class BoxModelKeysInfo {

	/*
	 * Lines ===== Lines illustrate a relation between a table and a neighbor of
	 * it. This neighbor may be a parent or a child, and both types relations
	 * can be represented by a 12 relation or by a 21 relation.
	 * 
	 * However, by default, table 1 in a 12 relation is the parent and table 2
	 * is the child. But one must keep in mind that these relations can be
	 * altered manually.
	 */

	// A database schema is generated using JDBC types.
	// public static final int NAME_LENGTH_LIMIT = 30;
	private BoxModel table;

	private Collection<Collection<Object>> uniqueKeyColumns = new ArrayList<Collection<Object>>();

	private Collection<Collection<Object>> foreignKeyColumns = new ArrayList<Collection<Object>>();

	public BoxModelKeysInfo(BoxModel table) {
		this.table = table;
		initUniqueKeyColumns();
		initForeignKeyColumns();
	}

	public BoxModel getBoxModel() {
		return table;
	}

	public ItemModel getPrimaryKeyColumn() {
		if (table.hasOid()) {
			return table.getOidItem();
		}
		return null;
	}

	public Collection<Collection<Object>> getUniqueKeyColumns() {
		return uniqueKeyColumns;
	}

	public Collection<Collection<Object>> getForeignKeyColumns() {
		return foreignKeyColumns;
	}

	public boolean isChild() {
		return foreignKeyColumns.size() > 0 ? true : false;
	}

	public BoxModel getBoxModelParent(String fkName) {
		Iterator x = foreignKeyColumns.iterator();
		while (x.hasNext()) {
			ArrayList currentFkColumn = (ArrayList) x.next();
			String currentFkName = (String) currentFkColumn.get(0); // 0 :
			// fkName
			if (currentFkName.equals(fkName)) {
				ItemModel currentFkRelatedPk = (ItemModel) currentFkColumn
						.get(1); // 1 : related item model of parent
				return currentFkRelatedPk.getBoxModel();
			}
		}
		return null;
	}

	// Used to represent a 0,N - 0,N relation
	public boolean isIntersection() {
		return foreignKeyColumns.size() > 1 ? true : false;
	}

	private void initUniqueKeyColumns() {
		if (table.hasId()) {
			Collection<Collection<Object>> fkIdFkColumns = this.getIdLine();
			if (fkIdFkColumns != null) {
				uniqueKeyColumns.addAll(fkIdFkColumns);
			}
			Collection<ItemModel> idColumns = table.getIdItems();
			Iterator xIdColumns = idColumns.iterator();
			while (xIdColumns.hasNext()) {
				ItemModel idItem = (ItemModel) xIdColumns.next();
				String idColumnName = idItem.getName().trim();
				Collection<Object> idColumn = new ArrayList<Object>();
				idColumn.add(idColumnName);
				idColumn.add(idItem);
				uniqueKeyColumns.add(idColumn);
			}
		}
	}

	private void initForeignKeyColumns() {
		if (table.hasLine()) {
			foreignKeyColumns.addAll(getDirForeignKeyColumns(12));
			foreignKeyColumns.addAll(getDirForeignKeyColumns(21));
		}
	}

	private Collection<Collection<Object>> getIdLine() {
		Collection<Collection<Object>> fkNames = new ArrayList<Collection<Object>>();
		if (table.hasLine() && table.hasIdLine()) {
			fkNames.addAll(getDirIdLine(12));
			fkNames.addAll(getDirIdLine(21));
		}
		return fkNames;
	}

	private Collection<Collection<Object>> getDirIdLine(int dir) {
		String fkColumnName;
		Collection<Collection<Object>> fkColumns = new ArrayList<Collection<Object>>();
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
				neighborName = neighbor.getName().trim();
				if (child.isTwin()) {
					fkColumnName = "oid" + neighborDirName;
				} else {
					fkColumnName = "oid" + neighborName;
				}
				if (dir == 12) {
					// if id is checked for this line
					if (child.isDir12Id()) {
						Collection<Object> fkColumn = new ArrayList<Object>();
						fkColumn.add(fkColumnName); // 0 : columnName
						fkColumn.add(neighbor.getOidItem()); // 1 : related
						// column in
						// parent table
						String dirName = child.getDir21Name();
						if (dirName.equals("?")) {
							dirName = "";
						}
						fkColumn.add(dirName); // 2 : name of the direction

						fkColumns.add(fkColumn);
					}
				} else {
					// if id is checked for this line
					if (child.isDir21Id()) {
						Collection<Object> fkColumn = new ArrayList<Object>();
						fkColumn.add(fkColumnName); // 0 : columnName
						fkColumn.add(neighbor.getOidItem()); // 1 : related
						// column in
						// parent table
						String dirName = child.getDir12Name();
						if (dirName.equals("?")) {
							dirName = "";
						}
						fkColumn.add(dirName); // 2 : name of the direction

						fkColumns.add(fkColumn);
					}
				}
			}
		} // end for
		return fkColumns;
	}

	private Collection<Collection<Object>> getDirForeignKeyColumns(int dir) {
		Collection<Collection<Object>> fkColumns = new ArrayList<Collection<Object>>();
		String fkColumnName;
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
				neighborName = neighbor.getName().trim();
				if (child.isTwin()) {
					fkColumnName = "oid" + neighborDirName;
				} else {
					fkColumnName = "oid" + neighborName;
				}
				Collection<Object> fkColumn = new ArrayList<Object>();
				fkColumn.add(fkColumnName); // 0 : columnName
				fkColumn.add(neighbor.getOidItem()); // 1 : related column in
				// parent table
				String dirName = child.getDir21Name();
				if (dirName.equals("?")) {
					dirName = "";
				}
				fkColumn.add(dirName); // 2 : name of the direction

				fkColumns.add(fkColumn);
			}
		} // end for
		return fkColumns;
	}

	public void test() {
		System.out.println("========Primary key column========");
		System.out.println(getPrimaryKeyColumn().getName());
		System.out.println("========Unique key columns========");
		Iterator x1 = getUniqueKeyColumns().iterator();
		while (x1.hasNext()) {
			ItemModel column = (ItemModel) x1.next();
			System.out.println(column.getName());
		}
		System.out.println("========Foreign key columns========");
		Iterator x2 = getForeignKeyColumns().iterator();
		while (x2.hasNext()) {
			ArrayList column = (ArrayList) x2.next();
			String columnName = (String) column.get(0);
			ItemModel parentColumn = (ItemModel) column.get(1);
			System.out.println(columnName + "("
					+ parentColumn.getTypeModel().getDbmsType() + ")");
		}
	}
}