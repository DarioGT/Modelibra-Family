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
public class SqlSelectPreparedStatement extends SqlPreparedStatement {

	public SqlSelectPreparedStatement(BoxModelKeysInfo tableInfo) {
		super(tableInfo);
	}

	public SqlSelectPreparedStatement(BoxModel table) {
		super(table);
	}

	public String getSelectAllStatement() {
		String sql = "SELECT * FROM " + table.getName().trim();
		return sql;
	}

	public String getSelectCountStatement() {
		String sql = "SELECT COUNT(*) FROM " + table.getName().trim();
		return sql;
	}

	public String getSelectCountForForeignKeyStatement(String fkColumnName) {
		String sql = "SELECT COUNT(*) FROM " + table.getName().trim()
				+ " WHERE " + fkColumnName + "=?";
		return sql;
	}

	public Collection<String> getSelectCountForForeignKeyStatements() {
		Collection<String> sqlSelectCountForeignKeyStatements = new ArrayList<String>();
		Iterator fkColumns = tableInfo.getForeignKeyColumns().iterator();
		while (fkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) fkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			sqlSelectCountForeignKeyStatements
					.add(getSelectCountForForeignKeyStatement(fkColumnName));
		}
		return sqlSelectCountForeignKeyStatements;
	}

	public String getSelectOneStatement() {
		String sql = "SELECT * FROM " + table.getName().trim();
		String pkColumnName = tableInfo.getPrimaryKeyColumn().getName().trim();
		sql += " WHERE " + pkColumnName + "=?";
		return sql;
	}

	public String getSelectUniqueKeyStatement() {
		String sql = "SELECT * FROM " + table.getName().trim() + " WHERE ";
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

	public String getSelectForForeignKeyStatement(String fkColumnName) {
		String sql = "SELECT * FROM " + table.getName().trim() + " WHERE "
				+ fkColumnName + "=?";
		return sql;
	}

	public Collection<String> getSelectForeignKeyStatements() {
		Collection<String> sqlSelectForeignKeyStatements = new ArrayList<String>();
		Iterator fkColumns = tableInfo.getForeignKeyColumns().iterator();
		while (fkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) fkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			sqlSelectForeignKeyStatements
					.add(getSelectForForeignKeyStatement(fkColumnName));
		}
		return sqlSelectForeignKeyStatements;
	}

}