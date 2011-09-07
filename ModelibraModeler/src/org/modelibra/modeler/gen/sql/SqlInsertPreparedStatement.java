package org.modelibra.modeler.gen.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.BoxModelKeysInfo;
import org.modelibra.modeler.model.ItemModel;

/**
 * 
 * @author Vincent Dussault
 * @version 2003
 */
public class SqlInsertPreparedStatement extends SqlPreparedStatement {

	public SqlInsertPreparedStatement(BoxModelKeysInfo tableInfo) {
		super(tableInfo);
	}

	public SqlInsertPreparedStatement(BoxModel table) {
		super(table);
	}

	public String getInsertStatement() {
		String sql = "INSERT INTO " + table.getName().trim() + " (";
		Collection columns = tableInfo.getBoxModel().getItems();
		int columnCount = columns.size();
		Iterator xColumns = columns.iterator();
		while (xColumns.hasNext()) {
			ItemModel column = (ItemModel) xColumns.next();
			String columnName = column.getName().trim();
			sql += columnName;
			if (xColumns.hasNext()) {
				sql += ", ";
			}
		}

		// Foreign key columns
		Collection fkColumns = tableInfo.getForeignKeyColumns();
		columnCount += fkColumns.size();
		Iterator xFkColumns = fkColumns.iterator();
		if (xFkColumns.hasNext()) {
			sql += ", ";
		}
		while (xFkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) xFkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			sql += fkColumnName;
			if (xFkColumns.hasNext()) {
				sql += ", ";
			}
		}
		sql += ") VALUES (";
		for (int i = 0; i < columnCount; i++) {
			sql += "?";
			if (i < columnCount - 1) {
				sql += ",";
			}
		}
		sql += ")";
		return sql;
	}
}