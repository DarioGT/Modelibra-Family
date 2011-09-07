package org.modelibra.modeler.gen.sql;

import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.BoxModelKeysInfo;

/**
 * 
 * @author Vincent Dussault
 * @version 2003
 */
public abstract class SqlPreparedStatement {

	protected BoxModel table;

	protected BoxModelKeysInfo tableInfo;

	public SqlPreparedStatement(BoxModelKeysInfo tableInfo) {
		this.tableInfo = tableInfo;
		this.table = tableInfo.getBoxModel();
	}

	public SqlPreparedStatement(BoxModel table) {
		this.tableInfo = new BoxModelKeysInfo(table);
		this.table = table;
	}

	public BoxModel getBoxModel() {
		return table;
	}

	public BoxModelKeysInfo getBoxModelKeysInfo() {
		return tableInfo;
	}

}