package org.modelibra.modeler.gen.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.BoxModelKeysInfo;

/**
 * 
 * @author Vincent Dussault
 * @author Dzenan Ridjanovic
 * @version 2006-12-09
 */
public class SqlDeletePreparedStatement extends SqlPreparedStatement {

	public SqlDeletePreparedStatement(BoxModelKeysInfo tableInfo) {
		super(tableInfo);
	}

	public SqlDeletePreparedStatement(BoxModel table) {
		super(table);
	}

	public String getDeleteStatement() {
		String sql = "DELETE FROM " + table.getName().trim();
		String pkColumnName = tableInfo.getPrimaryKeyColumn().getName().trim();
		sql += " WHERE " + pkColumnName + "=?";
		return sql;
	}

	public String getDeleteUniqueKeyStatement() {
		String sql = "DELETE FROM " + table.getName().trim() + " WHERE ";
		Iterator ukColumns = tableInfo.getUniqueKeyColumns().iterator();
		while (ukColumns.hasNext()) {
			ArrayList ukColumn = (ArrayList) ukColumns.next();
			String ukColumnName = (String) ukColumn.get(0);
			sql += ukColumnName + "=?";
			if (ukColumns.hasNext()) {
				sql += " AND ";
			}
		}
		return sql;
	}

	public String getDeleteForForeignKeyStatement(String fkColumnName) {
		String sql = "DELETE FROM " + table.getName().trim() + " WHERE "
				+ fkColumnName + "=?";
		return sql;
	}

	public Collection<String> getDeleteForForeignKeyStatements() {
		Collection<String> sqlDeleteForeignKeyStatements = new ArrayList<String>();
		Iterator fkColumns = tableInfo.getForeignKeyColumns().iterator();
		while (fkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) fkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			sqlDeleteForeignKeyStatements
					.add(getDeleteForForeignKeyStatement(fkColumnName));
		}
		return sqlDeleteForeignKeyStatements;
	}

}