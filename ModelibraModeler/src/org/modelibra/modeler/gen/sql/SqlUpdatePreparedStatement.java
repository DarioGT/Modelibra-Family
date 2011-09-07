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
public class SqlUpdatePreparedStatement extends SqlPreparedStatement {

	public SqlUpdatePreparedStatement(BoxModelKeysInfo tableInfo) {
		super(tableInfo);
	}

	public SqlUpdatePreparedStatement(BoxModel table) {
		super(table);
	}

	public String getUpdateStatement() {
		String sql = "UPDATE " + table.getName().trim() + " SET ";
		Collection columns = tableInfo.getBoxModel().getItems();
		Iterator xColumns = columns.iterator();
		boolean isFirst = true;
		while (xColumns.hasNext()) {
			ItemModel column = (ItemModel) xColumns.next();
			if (!column.isOid()) {
				if (!isFirst) {
					sql += ", ";
				}
				String columnName = column.getName().trim();
				sql += columnName + "=?";
				isFirst = false;
			}
		}

		// Foreign key columns
		Collection fkColumns = tableInfo.getForeignKeyColumns();
		Iterator xFkColumns = fkColumns.iterator();
		if (xFkColumns.hasNext()) {
			sql += ", ";
		}
		while (xFkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) xFkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			sql += fkColumnName + "=?";
			if (xFkColumns.hasNext()) {
				sql += ", ";
			}
		}
		sql += " WHERE " + table.getOidItem().getName().trim() + "=?";
		return sql;
	}

}