package org.modelibra.modeler.gen.dblite.db.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.gen.GenBoxModelClass;
import org.modelibra.modeler.gen.sql.SqlDeletePreparedStatement;
import org.modelibra.modeler.gen.sql.SqlInsertPreparedStatement;
import org.modelibra.modeler.gen.sql.SqlSelectPreparedStatement;
import org.modelibra.modeler.gen.sql.SqlUpdatePreparedStatement;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.BoxModelKeysInfo;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.model.TypeModel;
import org.modelibra.modeler.util.JdbcUtilities;
import org.modelibra.modeler.util.StringUtilities;

/**
 * 
 * @author Vincent Dussault
 * @version 2007-03-02
 */
public class GenSqlDbQueryClass extends GenBoxModelClass {

	protected String dbfwRootPackage = Config.getConfig().getProperty(
			"gen.package.dbLite");

	protected String specificRootPackage = "";

	protected static final int ALL = 3;

	protected static final int ONE = 4;

	protected static final int UNIQUE_KEY = 5;

	protected static final int COUNT = 6;

	public GenSqlDbQueryClass(BoxModelKeysInfo tableInfo, String srcDirPath,
			String specificRootPackage) {
		super(tableInfo, srcDirPath);
		this.specificRootPackage = specificRootPackage;
	}

	public String getPackageName() {
		if (specificRootPackage != null
				&& !specificRootPackage.trim().equals("")) {
			return specificRootPackage + ".db."
					+ table.getName().trim().toLowerCase() + ".sql";
		} else {
			return "db." + table.getName().trim().toLowerCase() + ".sql";
		}
	}

	public Collection<String> getImports() {
		Collection<String> imports = new ArrayList<String>();
		if (tableInfo.isChild()) {
			imports.add("java.util.Collection");
			// imports.add("java.util.Iterator");
		}
		imports.add("java.util.Map");
		imports.add("java.util.HashMap");
		imports.add("");
		imports.add("java.sql.Types");
		imports.add("");
		imports.add(dbfwRootPackage + ".db.DbAPI");
		imports.add(dbfwRootPackage + ".db.DbException");
		// if (tableInfo.isChild()){
		// imports.add(dbfwRootPackage+".db.DbQueryIterator");
		// }
		imports.add(dbfwRootPackage + ".db.DbQueryRecordAPI");
		imports.add("");
		imports.add(dbfwRootPackage + ".db.sql.SqlDbQuery");
		imports.add("");

		String prefix = "";
		if (specificRootPackage != null
				&& !specificRootPackage.trim().equals("")) {
			prefix += specificRootPackage + ".";
		}

		if (tableInfo.isChild()) {
			// if they exist, a delete method for the foreign keys
			Collection fkColumns = tableInfo.getForeignKeyColumns();
			if (!fkColumns.isEmpty()) {
				Iterator xFkColumns = fkColumns.iterator();

				while (xFkColumns.hasNext()) {
					ArrayList fkColumn = (ArrayList) xFkColumns.next();
					String fkColumnName = (String) fkColumn.get(0);

					BoxModel parent = tableInfo.getBoxModelParent(fkColumnName);
					String parentRecordClassName = StringUtilities
							.withFirstCharUpperCase(parent.getName());

					String aImportRecord = prefix + "db."
							+ parent.getName().trim().toLowerCase() + "."
							+ parentRecordClassName;

					if (!imports.contains(aImportRecord)) {
						imports.add(aImportRecord);
						imports.add(prefix + "db."
								+ parent.getName().trim().toLowerCase() + "."
								+ parentRecordClassName + "Query");
						imports.add(prefix + "db."
								+ parent.getName().trim().toLowerCase() + "."
								+ parentRecordClassName + "QueryFactory");

						if (xFkColumns.hasNext()) {
							imports.add("");
						}
					}
				}
			}
		}
		String aImportRecord = prefix + "db."
				+ table.getName().trim().toLowerCase() + "."
				+ capitalizedTableName();
		String aImportQuery = prefix + "db."
				+ table.getName().trim().toLowerCase() + "."
				+ capitalizedTableName() + "Query";
		if (!imports.contains(aImportRecord)) {
			imports.add(aImportRecord);
			imports.add(aImportQuery);
		}

		return imports;
	}

	public String getClassName() {
		String className = "Sql" + capitalizedTableName() + "Query";
		return className;
	}

	public String getExtendsName() {
		return "SqlDbQuery";
	}

	public Collection<String> getImplementsNames() {
		String tableInterface = capitalizedTableName() + "Query";
		Collection<String> implementsNames = new ArrayList<String>();
		implementsNames.add(tableInterface);
		return implementsNames;
	}

	public String capitalizedTableName() {
		return StringUtilities.withFirstCharUpperCase(table.getName());
	}

	public Collection<String> getFields() {
		Collection<String> fields = new ArrayList<String>();

		// SQL statements
		fields.addAll(buildSqlSelectFields());
		fields.add(buildSqlInsertField());
		fields.add("");
		fields.add(buildSqlUpdateField());
		fields.add("");
		fields.addAll(buildSqlDeleteFields());
		fields.addAll(buildSqlGetCountFields());
		// fields.add(""); //not needed since line after last getCount field

		fields.add(TAB + "//Column names");
		fields.addAll(buildSqlColumnNamesFields());

		return fields;
	}

	public Collection<String> getConstructors() {
		Collection<String> constructors = new ArrayList<String>();
		// empty
		// String emptyConstructor = "";
		// emptyConstructor += TAB+"public "+getClassNameForTable()+"Query() {";
		// emptyConstructor += LINE;
		// emptyConstructor += TAB+TAB+"super();";
		// emptyConstructor += LINE;
		// emptyConstructor += TAB+"}";
		// constructors.add(emptyConstructor);

		// DbAPI
		String dbConstructor = "";
		dbConstructor += TAB + "public Sql" + capitalizedTableName()
				+ "Query(DbAPI db) {";
		dbConstructor += LINE;
		dbConstructor += TAB + TAB + "super(db);";
		dbConstructor += LINE;
		dbConstructor += TAB + "}";
		constructors.add(dbConstructor);

		return constructors;
	}

	public Collection<String> getMethods() {
		Collection<String> methods = new ArrayList<String>();

		methods.addAll(buildGetCollectionMethods());
		// methods.addAll(buildIteratorMethods());
		methods.addAll(buildRetrieveMethods());
		methods.addAll(buildContainsMethods());
		methods.addAll(buildGetCountMethods());
		methods.add(buildInsertMethod());
		methods.add(buildUpdateMethod());
		methods.addAll(buildDeleteMethods());
		methods.add(buildCurrentRecordMethod());
		methods.addAll(buildSelectMethods());

		return methods;
	}

	protected String buildSelectSqlStatement(int selectType) {
		SqlSelectPreparedStatement selectStmt = new SqlSelectPreparedStatement(
				tableInfo);
		switch (selectType) {
		case ALL:
			return selectStmt.getSelectAllStatement();
		case ONE:
			return selectStmt.getSelectOneStatement();
		case UNIQUE_KEY:
			return selectStmt.getSelectUniqueKeyStatement();
		case COUNT:
			return selectStmt.getSelectCountStatement();
		}
		return null;
	}

	protected String buildSelectForForeignKeySqlStatement(String fkColumnName) {
		SqlSelectPreparedStatement selectStmt = new SqlSelectPreparedStatement(
				tableInfo);
		return selectStmt.getSelectForForeignKeyStatement(fkColumnName);
	}

	protected String buildInsertSqlStatement() {
		SqlInsertPreparedStatement insertStmt = new SqlInsertPreparedStatement(
				tableInfo);
		return insertStmt.getInsertStatement();
	}

	protected String buildUpdateSqlStatement() {
		SqlUpdatePreparedStatement updatetStmt = new SqlUpdatePreparedStatement(
				tableInfo);
		return updatetStmt.getUpdateStatement();
	}

	protected String buildDeleteSqlStatement(int selectType) {
		SqlDeletePreparedStatement deleteStmt = new SqlDeletePreparedStatement(
				tableInfo);
		switch (selectType) {
		case ONE:
			return deleteStmt.getDeleteStatement();
		case UNIQUE_KEY:
			return deleteStmt.getDeleteUniqueKeyStatement();
		}
		return null;
	}

	protected String buildGetCountSqlStatement() {
		SqlSelectPreparedStatement selectStmt = new SqlSelectPreparedStatement(
				tableInfo);
		return selectStmt.getSelectCountStatement();
	}

	protected String buildGetCountForForeignKeySqlStatement(String fkColumnName) {
		SqlSelectPreparedStatement selectStmt = new SqlSelectPreparedStatement(
				tableInfo);
		return selectStmt.getSelectCountForForeignKeyStatement(fkColumnName);
	}

	protected String buildDeleteForeignKeySqlStatement(String fkColumnName) {
		SqlDeletePreparedStatement deleteStmt = new SqlDeletePreparedStatement(
				tableInfo);
		return deleteStmt.getDeleteForForeignKeyStatement(fkColumnName);
	}

	protected Collection<String> buildSqlSelectFields() {
		Collection<String> selectFields = new ArrayList<String>();
		String selectAllField = TAB + "public static final String ALL = \""
				+ buildSelectSqlStatement(ALL) + "\";";
		selectFields.add(selectAllField);
		selectFields.add("");
		String selectOneField = TAB + "public static final String ONE = \""
				+ buildSelectSqlStatement(ONE) + "\";";
		selectFields.add(selectOneField);
		selectFields.add("");
		if (tableInfo.getUniqueKeyColumns().size() > 0) {
			String selectOneUniqueKeyField = TAB
					+ "public static final String ONE_UNIQUE_KEY = \""
					+ buildSelectSqlStatement(UNIQUE_KEY) + "\";";
			selectFields.add(selectOneUniqueKeyField);
			selectFields.add("");
		}
		Iterator fkColumns = tableInfo.getForeignKeyColumns().iterator();
		while (fkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) fkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
			String directionName = StringUtilities.withFirstCharUpperCase(
					(String) fkColumn.get(2)).trim();

			BoxModel parent = parentLinkedColumn.getBoxModel();
			BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
			String parentName = parentInfo.getBoxModel().getName().trim();
			String parentRecordClassName = StringUtilities
					.withFirstCharUpperCase(parentName);

			String selectForeignKeyField = TAB
					+ "public static final String ALL_FOREIGN_KEY_"
					+ (parentRecordClassName + directionName).toUpperCase()
					+ " = \""
					+ buildSelectForForeignKeySqlStatement(fkColumnName)
					+ "\";";
			selectFields.add(selectForeignKeyField);
			selectFields.add("");
		}
		return selectFields;
	}

	protected String buildSqlInsertField() {
		String field = TAB + "public static final String INSERT = \""
				+ buildInsertSqlStatement() + "\";";
		return field;
	}

	protected String buildSqlUpdateField() {
		String updateField = TAB + "public static final String UPDATE = \""
				+ buildUpdateSqlStatement() + "\";";
		return updateField;
	}

	protected Collection<String> buildSqlDeleteFields() {
		Collection<String> deleteFields = new ArrayList<String>();
		String deleteField = TAB + "public static final String DELETE = \""
				+ buildDeleteSqlStatement(ONE) + "\";";
		deleteFields.add(deleteField);
		deleteFields.add("");
		if (tableInfo.getUniqueKeyColumns().size() > 0) {
			String deleteUniqueKeyField = TAB
					+ "public static final String DELETE_UNIQUE_KEY = \""
					+ buildDeleteSqlStatement(UNIQUE_KEY) + "\";";
			deleteFields.add(deleteUniqueKeyField);
			deleteFields.add("");
		}
		Iterator fkColumns = tableInfo.getForeignKeyColumns().iterator();
		while (fkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) fkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
			String directionName = StringUtilities.withFirstCharUpperCase(
					(String) fkColumn.get(2)).trim();

			BoxModel parent = parentLinkedColumn.getBoxModel();
			BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
			String parentName = parentInfo.getBoxModel().getName().trim();
			String parentRecordClassName = StringUtilities
					.withFirstCharUpperCase(parentName);

			String deleteForeignKeyField = TAB
					+ "public static final String DELETE_FOREIGN_KEY_"
					+ (parentRecordClassName + directionName).toUpperCase()
					+ " = \"" + buildDeleteForeignKeySqlStatement(fkColumnName)
					+ "\";";
			deleteFields.add(deleteForeignKeyField);
			deleteFields.add("");
		}
		return deleteFields;
	}

	protected Collection<String> buildSqlGetCountFields() {
		Collection<String> getCountFields = new ArrayList<String>();
		String allField = TAB + "public static final String COUNT = \""
				+ buildGetCountSqlStatement() + "\";";
		getCountFields.add(allField);
		getCountFields.add("");

		Iterator fkColumns = tableInfo.getForeignKeyColumns().iterator();
		while (fkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) fkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
			String directionName = StringUtilities.withFirstCharUpperCase(
					(String) fkColumn.get(2)).trim();

			BoxModel parent = parentLinkedColumn.getBoxModel();
			BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
			String parentName = parentInfo.getBoxModel().getName().trim();
			String parentRecordClassName = StringUtilities
					.withFirstCharUpperCase(parentName);

			String getCountForeignKeyField = TAB
					+ "public static final String COUNT_FOREIGN_KEY_"
					+ (parentRecordClassName + directionName).toUpperCase()
					+ " = \""
					+ buildGetCountForForeignKeySqlStatement(fkColumnName)
					+ "\";";
			getCountFields.add(getCountForeignKeyField);
			getCountFields.add("");
		}

		return getCountFields;
	}

	protected Collection<String> buildSqlColumnNamesFields() {
		Collection<String> columnNamesFields = new ArrayList<String>();
		Iterator columns = table.getItems().iterator();
		while (columns.hasNext()) {
			ItemModel column = (ItemModel) columns.next();
			String columnNameField = TAB + "public static final String COL_"
					+ column.getName().toUpperCase() + "_SQL = \""
					+ column.getName().trim() + "\";";
			columnNamesFields.add(columnNameField);
		}
		Iterator fkColumns = tableInfo.getForeignKeyColumns().iterator();
		while (fkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) fkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			String columnNameForeignKeyField = TAB
					+ "public static final String COL_"
					+ fkColumnName.toUpperCase() + "_SQL = \"" + fkColumnName
					+ "\";";
			columnNamesFields.add(columnNameForeignKeyField);
		}
		return columnNamesFields;
	}

	protected String buildCurrentRecordMethod() {
		String tab = "  ";
		String method = "";
		method += tab
				+ "protected DbQueryRecordAPI currentRecord() throws DbException {";
		method += LINE;

		Collection columns = table.getItems();
		Iterator xColumns = columns.iterator();
		while (xColumns.hasNext()) {
			ItemModel column = (ItemModel) xColumns.next();
			String objectName = StringUtilities.withFirstCharLowerCase(column
					.getName().trim());
			TypeModel columnType = column.getTypeModel();
			String columnJavaType = "Object"; // Default
			if (columnType != null) {
				columnJavaType = columnType.getAlias().trim();
			}
			String instruction = "";

			if (columnJavaType.trim().equalsIgnoreCase("java.net.URL")
					|| columnJavaType.trim().equalsIgnoreCase("org.dmlite.type.Email")) {
				columnJavaType = "java.lang.String";
			}
				
				instruction = TAB + TAB + columnJavaType + " " + objectName
						+ " = " + buildGetColumnXxxMethodName(column) + "(COL_"
						+ objectName.toUpperCase() + "_SQL);";
			
			method += instruction;
			method += LINE;
		}
		// Foreign key columns
		Collection fkColumns = tableInfo.getForeignKeyColumns();
		Iterator xFkColumns = fkColumns.iterator();
		while (xFkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) xFkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);

			ItemModel linkedPkColumn = (ItemModel) fkColumn.get(1);
			String directionName = StringUtilities.withFirstCharUpperCase(
					(String) fkColumn.get(2)).trim();

			BoxModel parent = tableInfo.getBoxModelParent(fkColumnName);
			String parentRecordClassName = StringUtilities
					.withFirstCharUpperCase(parent.getName().trim());
			String parentRecordName = StringUtilities
					.withFirstCharLowerCase(parent.getName().trim());
			String parentQueryName = StringUtilities
					.withFirstCharLowerCase(parent.getName().trim())
					+ "Query";

			method += LINE;
			String createParentFactoryInstruction = TAB + TAB
					+ parentRecordClassName + "Query " + parentQueryName
					+ directionName + " = " + parentRecordClassName
					+ "QueryFactory.getInstance(getDb());";
			method += createParentFactoryInstruction;
			method += LINE;

			String instruction = TAB + TAB + parentRecordClassName + " "
					+ parentRecordName + directionName + " = "
					+ parentQueryName + directionName + ".retrieve("
					+ buildGetColumnXxxMethodName(linkedPkColumn) + "(COL_"
					+ fkColumnName.toUpperCase() + "_SQL));";
			method += instruction;
			method += LINE;
		}

		// ///////////////////////////////////////

		ItemModel oidItem = table.getOidItem();
		String oidObjectName = StringUtilities.withFirstCharLowerCase(oidItem
				.getName().trim());

		method += LINE;
		method += tab + tab + capitalizedTableName() + " record = new "
				+ capitalizedTableName() + "(" + oidObjectName + ", this);";
		method += LINE;

		xColumns = columns.iterator();
		while (xColumns.hasNext()) {
			ItemModel column = (ItemModel) xColumns.next();

			if (!column.isOid()) {
				String objectName = StringUtilities
						.withFirstCharLowerCase(column.getName().trim());
				String recordSetterMethodName = "set"
						+ StringUtilities.withFirstCharUpperCase(objectName);

				String instruction = TAB + TAB + "record."
						+ recordSetterMethodName + "(" + objectName + ");";
				method += instruction;
				method += LINE;
			}
		}

		// Foreign key columns
		xFkColumns = fkColumns.iterator();
		while (xFkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) xFkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			String directionName = StringUtilities.withFirstCharUpperCase(
					(String) fkColumn.get(2)).trim();

			BoxModel parent = tableInfo.getBoxModelParent(fkColumnName);
			String parentRecordClassName = StringUtilities
					.withFirstCharUpperCase(parent.getName());
			String parentObjectName = StringUtilities
					.withFirstCharLowerCase(parent.getName());

			String recordSetterMethodName = "set" + parentRecordClassName
					+ directionName;

			String instruction = TAB + TAB + "record." + recordSetterMethodName
					+ "(" + parentObjectName + directionName + ");";
			method += instruction;
			method += LINE;
		}

		method += LINE;
		method += tab + tab + "return record;";
		method += LINE;
		method += tab + "}";
		return method;
	}

	protected Collection<String> buildSelectMethods() {
		Collection<String> selectMethods = new ArrayList<String>();

		// all
		String all = "";
		all += TAB + "protected void selectAll() throws DbException {";
		all += LINE;
		all += TAB + TAB + "try {";
		all += LINE;
		all += TAB + TAB + TAB + "sqlCommand(ALL);";
		all += LINE;
		all += TAB + TAB + TAB + "sqlQuery();";
		all += LINE;
		all += TAB + TAB + "}";
		all += LINE;
		all += TAB + TAB + "catch (DbException dbe){";
		all += LINE;
		all += TAB + TAB + TAB + "release();";
		all += LINE;
		all += TAB + TAB + TAB + "throw dbe;";
		all += LINE;
		all += TAB + TAB + "}";
		all += LINE;
		all += TAB + "}";
		selectMethods.add(all);

		// foreign keys
		Collection fkColumns = tableInfo.getForeignKeyColumns();
		if (!fkColumns.isEmpty()) {
			Iterator xFkColumns = fkColumns.iterator();

			while (xFkColumns.hasNext()) {
				ArrayList fkColumn = (ArrayList) xFkColumns.next();
				String fkColumnName = (String) fkColumn.get(0);
				ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
				String directionName = StringUtilities.withFirstCharUpperCase(
						(String) fkColumn.get(2)).trim();

				String fkColumnJdbcType = getJdbcType(parentLinkedColumn);
				String fkColumnJavaType = JdbcUtilities
						.getJavaTypeForJdbcType(fkColumnJdbcType);

				String parentInterfaceName = StringUtilities
						.withFirstCharUpperCase(tableInfo.getBoxModelParent(
								fkColumnName).getName());
				String fkMethod = "";
				fkMethod += TAB + "protected void selectAllForForeignKey"
						+ parentInterfaceName + directionName + "("
						+ fkColumnJavaType + " " + fkColumnName
						+ ") throws DbException {";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "try {";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "sqlCommand(ALL_FOREIGN_KEY_"
						+ (parentInterfaceName + directionName).toUpperCase()
						+ ");";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "sqlCleanup();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "sqlArgument(Types."
						+ fkColumnJdbcType + ", " + fkColumnName + ", 0);";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "sqlQuery();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "}";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "catch (DbException dbe){";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "release();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "throw dbe;";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "}";
				fkMethod += LINE;
				fkMethod += TAB + "}";

				selectMethods.add(fkMethod);

				String parentLinkedColumnGetter = "get"
						+ StringUtilities
								.withFirstCharUpperCase(parentLinkedColumn
										.getName());
				String fkInterfaceMethod = "";
				fkInterfaceMethod += TAB + "protected void selectAllFor"
						+ parentInterfaceName + directionName + "("
						+ parentInterfaceName + " parent) throws DbException {";
				fkInterfaceMethod += LINE;
				fkInterfaceMethod += TAB + TAB + "selectAllForForeignKey"
						+ parentInterfaceName + directionName + "(parent."
						+ parentLinkedColumnGetter + "());";
				fkInterfaceMethod += LINE;
				fkInterfaceMethod += TAB + "}";

				selectMethods.add(fkInterfaceMethod);
			}
		}
		return selectMethods;
	}

	protected Collection<String> buildRetrieveMethods() {
		Collection<String> retrieveMethods = new ArrayList<String>();

		// default
		String defaultMethod = "";
		defaultMethod += TAB
				+ "public DbQueryRecordAPI retrieve(Map primaryKey) throws DbException {";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + "try {";
		defaultMethod += LINE;
		ItemModel pkColumn = table.getOidItem();
		String pkColumnJdbcType = getJdbcType(pkColumn);
		String pkColumnJavaType = JdbcUtilities
				.getJavaTypeForJdbcType(pkColumnJdbcType);
		String pkColumnName = pkColumn.getName().trim();
		defaultMethod += TAB + TAB + TAB + pkColumnJavaType + " id = ("
				+ pkColumnJavaType + ")primaryKey.get(COL_"
				+ pkColumnName.toUpperCase() + "_SQL);";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + capitalizedTableName()
				+ " record = null;";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "sqlCommand(ONE);";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "sqlCleanup();";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "sqlArgument(Types."
				+ pkColumnJdbcType + ", id, 0);";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "sqlQuery();";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "if (next()){ //First and only row";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + TAB + "record = ("
				+ capitalizedTableName() + ")currentRecord();";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "}";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "release();";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "return record;";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + "}";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + "catch (DbException dbe){";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "release();";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "throw dbe;";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + "}";
		defaultMethod += LINE;
		defaultMethod += TAB + "}";
		retrieveMethods.add(defaultMethod);

		// utility method for primary key
		String pkMethod = "";
		pkMethod += TAB + "public " + capitalizedTableName() + " retrieve("
				+ pkColumnJavaType + " oid) throws DbException {";
		pkMethod += LINE;
		pkMethod += TAB + TAB + "Map primaryKey = new HashMap();";
		pkMethod += LINE;
		pkMethod += TAB + TAB + "primaryKey.put(COL_"
				+ pkColumnName.toUpperCase() + "_SQL, oid);";
		pkMethod += LINE;
		pkMethod += TAB + TAB + "return (" + capitalizedTableName()
				+ ")retrieve(primaryKey);";
		pkMethod += LINE;
		pkMethod += TAB + "}";
		retrieveMethods.add(pkMethod);

		// if it exists, a retrieve method for the unique key
		Collection ukColumns = tableInfo.getUniqueKeyColumns();
		if (!ukColumns.isEmpty()) {
			Iterator xUkColumns = ukColumns.iterator();
			String args = "";
			String sqlArgumentInstructions = "";
			int counter = 0;

			while (xUkColumns.hasNext()) {
				ArrayList ukColumn = (ArrayList) xUkColumns.next();
				String ukColumnName = (String) ukColumn.get(0);
				ItemModel ukColumnItem = (ItemModel) ukColumn.get(1);
				String ukColumnJdbcType = getJdbcType(ukColumnItem);
				String ukColumnJavaType = JdbcUtilities
						.getJavaTypeForJdbcType(ukColumnJdbcType);
				String ukColumnObjectName = StringUtilities
						.withFirstCharLowerCase(ukColumnName);
				args += ukColumnJavaType + " " + ukColumnObjectName;
				sqlArgumentInstructions += TAB + TAB + TAB
						+ "sqlArgument(Types." + ukColumnJdbcType + ", "
						+ ukColumnObjectName + ", " + counter + ");";
				if (xUkColumns.hasNext()) {
					args += ", ";
					sqlArgumentInstructions += LINE;
				}
				counter++;
			}
			String ukMethod = "";
			ukMethod += TAB + "public " + capitalizedTableName()
					+ " retrieveUniqueKey(" + args + ") throws DbException {";
			ukMethod += LINE;
			ukMethod += TAB + TAB + "try {";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + capitalizedTableName()
					+ " record = null;";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "sqlCommand(ONE_UNIQUE_KEY);";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "sqlCleanup();";
			ukMethod += LINE;
			ukMethod += sqlArgumentInstructions;
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "sqlQuery();";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "if (next()){ //First and only row";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + TAB + "record = ("
					+ capitalizedTableName() + ")currentRecord();";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "}";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "release();";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "return record;";
			ukMethod += LINE;
			ukMethod += TAB + TAB + "}";
			ukMethod += LINE;
			ukMethod += TAB + TAB + "catch (DbException dbe){";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "release();";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "throw dbe;";
			ukMethod += LINE;
			ukMethod += TAB + TAB + "}";
			ukMethod += LINE;
			ukMethod += TAB + "}";
			retrieveMethods.add(ukMethod);
		}
		return retrieveMethods;
	}

	protected Collection<String> buildContainsMethods() {
		Collection<String> containsMethods = new ArrayList<String>();

		ItemModel pkColumn = table.getOidItem();
		String pkColumnJdbcType = getJdbcType(pkColumn);
		String pkColumnJavaType = JdbcUtilities
				.getJavaTypeForJdbcType(pkColumnJdbcType);
		String pkColumnName = pkColumn.getName().trim();

		// utility method for primary key
		String pkMethod = "";
		pkMethod += TAB + "public boolean contains(" + pkColumnJavaType
				+ " oid) throws DbException {";
		pkMethod += LINE;
		pkMethod += TAB + TAB + "Map primaryKey = new HashMap();";
		pkMethod += LINE;
		pkMethod += TAB + TAB + "primaryKey.put(COL_"
				+ pkColumnName.toUpperCase() + "_SQL, oid);";
		pkMethod += LINE;
		pkMethod += TAB + TAB + "return super.contains(primaryKey);";
		pkMethod += LINE;
		pkMethod += TAB + "}";
		containsMethods.add(pkMethod);

		// if it exists, a retrieve method for the unique key
		Collection ukColumns = tableInfo.getUniqueKeyColumns();
		if (!ukColumns.isEmpty()) {
			Iterator xUkColumns = ukColumns.iterator();
			String args = "";
			String ukObjectNames = "";
			String sqlArgumentInstructions = "";
			int counter = 0;

			while (xUkColumns.hasNext()) {
				ArrayList ukColumn = (ArrayList) xUkColumns.next();
				String ukColumnName = (String) ukColumn.get(0);
				ItemModel ukColumnItem = (ItemModel) ukColumn.get(1);
				String ukColumnJdbcType = getJdbcType(ukColumnItem);
				String ukColumnJavaType = JdbcUtilities
						.getJavaTypeForJdbcType(ukColumnJdbcType);
				String ukColumnObjectName = StringUtilities
						.withFirstCharLowerCase(ukColumnName);
				if (!ukObjectNames.equals("")) {
					ukObjectNames += ", ";
				}
				ukObjectNames += ukColumnObjectName;

				args += ukColumnJavaType + " " + ukColumnObjectName;
				sqlArgumentInstructions += TAB + TAB + TAB
						+ "sqlArgument(Types." + ukColumnJdbcType + ", "
						+ ukColumnObjectName + ", " + counter + ");";
				if (xUkColumns.hasNext()) {
					args += ", ";
					sqlArgumentInstructions += LINE;
				}
				counter++;
			}
			String ukMethod = "";
			ukMethod += TAB + "public boolean containsUniqueKey(" + args
					+ ") throws DbException {";
			ukMethod += LINE;
			ukMethod += TAB + TAB + capitalizedTableName()
					+ " record = retrieveUniqueKey(" + ukObjectNames + ");";
			ukMethod += LINE;
			ukMethod += TAB + TAB + "return record != null;";
			ukMethod += LINE;
			ukMethod += TAB + "}";
			containsMethods.add(ukMethod);
		}
		return containsMethods;
	}

	protected Collection<String> buildGetCollectionMethods() {
		Collection<String> getCollectionMethods = new ArrayList<String>();
		// if they exist, a getCollection method for the foreign keys
		Collection fkColumns = tableInfo.getForeignKeyColumns();
		if (!fkColumns.isEmpty()) {
			Iterator xFkColumns = fkColumns.iterator();

			while (xFkColumns.hasNext()) {
				ArrayList fkColumn = (ArrayList) xFkColumns.next();
				ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
				String directionName = StringUtilities.withFirstCharUpperCase(
						(String) fkColumn.get(2)).trim();

				BoxModel parent = parentLinkedColumn.getBoxModel();
				BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
				String parentName = parentInfo.getBoxModel().getName().trim();
				String parentRecordClassName = StringUtilities
						.withFirstCharUpperCase(parentName);
				String parentRecordObjectName = StringUtilities
						.withFirstCharLowerCase(parentRecordClassName);

				String fkOneArgMethod = "";
				fkOneArgMethod += TAB + "public Collection getCollectionFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName + ") throws DbException {";
				fkOneArgMethod += LINE;
				fkOneArgMethod += TAB + TAB + "return getCollectionFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordObjectName + directionName + ", 0);";
				fkOneArgMethod += LINE;
				fkOneArgMethod += TAB + "}";
				getCollectionMethods.add(fkOneArgMethod);

				String fkTwoArgsMethod = "";
				fkTwoArgsMethod += TAB + "public Collection getCollectionFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName + ", int begin) throws DbException {";
				fkTwoArgsMethod += LINE;
				fkTwoArgsMethod += TAB + TAB + "return getCollectionFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordObjectName + directionName
						+ ", begin, -1);";
				fkTwoArgsMethod += LINE;
				fkTwoArgsMethod += TAB + "}";
				getCollectionMethods.add(fkTwoArgsMethod);

				String fkThreeArgsMethod = "";
				fkThreeArgsMethod += TAB + "public Collection getCollectionFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName
						+ ", int begin, int maxSize) throws DbException {";
				fkThreeArgsMethod += LINE;
				fkThreeArgsMethod += TAB + TAB + "selectAllFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordObjectName + directionName + ");";
				fkThreeArgsMethod += LINE;
				fkThreeArgsMethod += TAB
						+ TAB
						+ "Collection results = fillCollection(begin, maxSize);";
				fkThreeArgsMethod += LINE;
				fkThreeArgsMethod += TAB + TAB + "release();";
				fkThreeArgsMethod += LINE;
				fkThreeArgsMethod += TAB + TAB + "return results;";
				fkThreeArgsMethod += LINE;
				fkThreeArgsMethod += TAB + "}";
				getCollectionMethods.add(fkThreeArgsMethod);
			}

			xFkColumns = fkColumns.iterator();

			while (xFkColumns.hasNext()) {
				ArrayList fkColumn = (ArrayList) xFkColumns.next();
				String fkColumnName = (String) fkColumn.get(0);
				ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
				String directionName = StringUtilities.withFirstCharUpperCase(
						(String) fkColumn.get(2)).trim();

				String fkColumnJdbcType = getJdbcType(parentLinkedColumn);
				String fkColumnJavaType = JdbcUtilities
						.getJavaTypeForJdbcType(fkColumnJdbcType);
				String fkColumnObjectName = StringUtilities
						.withFirstCharLowerCase(fkColumnName);

				BoxModel parent = parentLinkedColumn.getBoxModel();
				BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
				String parentName = parentInfo.getBoxModel().getName().trim();
				String parentRecordClassName = StringUtilities
						.withFirstCharUpperCase(parentName);
				String parentRecordObjectName = StringUtilities
						.withFirstCharLowerCase(parentRecordClassName);

				String fkOneArgMethod = "";
				fkOneArgMethod += TAB
						+ "public Collection getCollectionForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ fkColumnJavaType + " " + fkColumnObjectName
						+ ") throws DbException {";
				fkOneArgMethod += LINE;
				fkOneArgMethod += TAB + TAB
						+ "return getCollectionForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ fkColumnObjectName + ", 0);";
				fkOneArgMethod += LINE;
				fkOneArgMethod += TAB + "}";
				getCollectionMethods.add(fkOneArgMethod);

				String fkTwoArgsMethod = "";
				fkTwoArgsMethod += TAB
						+ "public Collection getCollectionForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ fkColumnJavaType + " " + fkColumnObjectName
						+ ", int begin) throws DbException {";
				fkTwoArgsMethod += LINE;
				fkTwoArgsMethod += TAB + TAB
						+ "return getCollectionForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ fkColumnObjectName + ", begin, -1);";
				fkTwoArgsMethod += LINE;
				fkTwoArgsMethod += TAB + "}";
				getCollectionMethods.add(fkTwoArgsMethod);

				String fkThreeArgsMethod = "";
				fkThreeArgsMethod += TAB
						+ "public Collection getCollectionForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ fkColumnJavaType + " " + fkColumnObjectName
						+ ", int begin, int maxSize) throws DbException {";
				fkThreeArgsMethod += LINE;
				fkThreeArgsMethod += TAB + TAB + parentRecordClassName + " "
						+ parentRecordObjectName + directionName + " = new "
						+ parentRecordClassName + "(" + fkColumnObjectName
						+ ", getDb());";
				fkThreeArgsMethod += LINE;
				fkThreeArgsMethod += TAB + TAB + "return getCollectionFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordObjectName + directionName
						+ ", begin, maxSize);";
				fkThreeArgsMethod += LINE;
				fkThreeArgsMethod += TAB + "}";
				getCollectionMethods.add(fkThreeArgsMethod);
			}

		}
		return getCollectionMethods;
	}

	protected Collection<String> buildIteratorMethods() {
		String className = "Sql" + capitalizedTableName() + "Query";

		Collection<String> iteratorMethods = new ArrayList<String>();
		// if they exist, a getCollection method for the foreign keys
		Collection fkColumns = tableInfo.getForeignKeyColumns();
		if (!fkColumns.isEmpty()) {
			Iterator xFkColumns = fkColumns.iterator();

			while (xFkColumns.hasNext()) {
				ArrayList fkColumn = (ArrayList) xFkColumns.next();
				String fkColumnName = (String) fkColumn.get(0);
				ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
				String directionName = StringUtilities.withFirstCharUpperCase(
						(String) fkColumn.get(2)).trim();

				String fkColumnJdbcType = getJdbcType(parentLinkedColumn);
				String fkColumnJavaType = JdbcUtilities
						.getJavaTypeForJdbcType(fkColumnJdbcType);
				String fkColumnObjectName = StringUtilities
						.withFirstCharLowerCase(fkColumnName);

				BoxModel parent = parentLinkedColumn.getBoxModel();
				BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
				String parentName = parentInfo.getBoxModel().getName().trim();
				String parentRecordClassName = StringUtilities
						.withFirstCharUpperCase(parentName);
				String parentRecordObjectName = StringUtilities
						.withFirstCharLowerCase(parentRecordClassName);

				String parentMethod = "";
				parentMethod += TAB + "public Iterator iteratorFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName + ") throws DbException {";
				parentMethod += LINE;
				parentMethod += TAB + TAB + className + " instance = new "
						+ className + "(db);";
				parentMethod += LINE;
				parentMethod += TAB + TAB + "if (isInTransaction()){";
				parentMethod += LINE;
				parentMethod += TAB + TAB + TAB
						+ "instance.setDbTransaction(getDbTransaction());";
				parentMethod += LINE;
				parentMethod += TAB + TAB + "}";
				parentMethod += LINE;
				parentMethod += TAB + TAB + "int count = instance.getCountFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordObjectName + directionName + ");";
				parentMethod += LINE;
				parentMethod += TAB + TAB + "instance.selectAllFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordObjectName + directionName + ");";
				parentMethod += LINE;
				parentMethod += TAB + TAB
						+ "return new DbQueryIterator(instance, count);";
				parentMethod += LINE;
				parentMethod += TAB + "}";
				iteratorMethods.add(parentMethod);

				String fkMethod = "";
				fkMethod += TAB + "public Iterator iteratorForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ fkColumnJavaType + " " + fkColumnObjectName
						+ ") throws DbException {";
				fkMethod += LINE;
				fkMethod += TAB + TAB + className + " instance = new "
						+ className + "(db);";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "if (isInTransaction()){";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB
						+ "instance.setDbTransaction(getDbTransaction());";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "}";
				fkMethod += LINE;
				fkMethod += TAB + TAB
						+ "int count = instance.getCountForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ fkColumnObjectName + ");";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "instance.selectAllForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ fkColumnObjectName + ");";
				fkMethod += LINE;
				fkMethod += TAB + TAB
						+ "return new DbQueryIterator(instance, count);";
				fkMethod += LINE;
				fkMethod += TAB + "}";
				iteratorMethods.add(fkMethod);
			}
		}
		return iteratorMethods;
	}

	protected Collection<String> buildGetCountMethods() {
		Collection<String> getCountMethods = new ArrayList<String>();
		String allMethod = "";
		allMethod += TAB + "public int getCount() throws DbException {";
		allMethod += LINE;
		allMethod += TAB + TAB + "try {";
		allMethod += LINE;
		allMethod += TAB + TAB + TAB + "int count;";
		allMethod += LINE;
		allMethod += TAB + TAB + TAB + "sqlCommand(COUNT);";
		allMethod += LINE;
		allMethod += TAB + TAB + TAB + "sqlQuery();";
		allMethod += LINE;
		allMethod += TAB + TAB + TAB + "count = super.getRowCount();";
		allMethod += LINE;
		allMethod += TAB + TAB + TAB + "release();";
		allMethod += LINE;
		allMethod += TAB + TAB + TAB + "return count;";
		allMethod += LINE;
		allMethod += TAB + TAB + "}";
		allMethod += LINE;
		allMethod += TAB + TAB + "catch (DbException dbe){";
		allMethod += LINE;
		allMethod += TAB + TAB + TAB + "release();";
		allMethod += LINE;
		allMethod += TAB + TAB + TAB + "throw dbe;";
		allMethod += LINE;
		allMethod += TAB + TAB + "}";
		allMethod += LINE;
		allMethod += TAB + "}";
		getCountMethods.add(allMethod);

		// if they exist, a getCount method for the foreign keys
		Collection fkColumns = tableInfo.getForeignKeyColumns();
		if (!fkColumns.isEmpty()) {
			Iterator xFkColumns = fkColumns.iterator();

			while (xFkColumns.hasNext()) {
				ArrayList fkColumn = (ArrayList) xFkColumns.next();
				String fkColumnName = (String) fkColumn.get(0);
				ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
				String directionName = StringUtilities.withFirstCharUpperCase(
						(String) fkColumn.get(2)).trim();

				String fkColumnJdbcType = getJdbcType(parentLinkedColumn);
				String fkColumnJavaType = JdbcUtilities
						.getJavaTypeForJdbcType(fkColumnJdbcType);

				BoxModel parent = parentLinkedColumn.getBoxModel();
				BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
				String parentName = parentInfo.getBoxModel().getName().trim();
				String parentRecordClassName = StringUtilities
						.withFirstCharUpperCase(parentName);
				String parentRecordObjectName = StringUtilities
						.withFirstCharLowerCase(parentRecordClassName);
				String parentGetPkMethodName = "get"
						+ StringUtilities
								.withFirstCharUpperCase(parentLinkedColumn
										.getName().trim());

				String fkParentMethod = "";
				fkParentMethod += TAB + "public int getCountFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName + ") throws DbException {";
				fkParentMethod += LINE;
				fkParentMethod += TAB + TAB + "return getCountForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ parentRecordObjectName + directionName + "."
						+ parentGetPkMethodName + "());";
				fkParentMethod += LINE;
				fkParentMethod += TAB + "}";
				getCountMethods.add(fkParentMethod);

				String fkMethod = "";
				fkMethod += TAB + "public int getCountForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ fkColumnJavaType + " " + fkColumnName
						+ ") throws DbException {";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "try {";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "int count;";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "sqlCommand(COUNT_FOREIGN_KEY_"
						+ (parentRecordClassName + directionName).toUpperCase()
						+ ");";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "sqlCleanup();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "sqlArgument(Types."
						+ fkColumnJdbcType + ", " + fkColumnName + ", 0);";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "sqlQuery();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "count = super.getRowCount();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "release();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "return count;";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "}";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "catch (DbException dbe){";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "release();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "throw dbe;";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "}";
				fkMethod += LINE;
				fkMethod += TAB + "}";
				getCountMethods.add(fkMethod);
			}
		}
		return getCountMethods;
	}

	protected String buildInsertMethod() {
		String method = "";
		method += TAB
				+ "public int insert(DbQueryRecordAPI record) throws DbException {";
		method += LINE;
		method += TAB + TAB + "if (record == null){";
		method += LINE;
		method += TAB + TAB + TAB + "return -1;";
		method += LINE;
		method += TAB + TAB + "}";
		method += LINE;
		method += TAB + TAB + "try {";
		method += LINE;
		method += TAB + TAB + TAB + capitalizedTableName() + " aRecord = ("
				+ capitalizedTableName() + ")record;";
		method += LINE;
		method += TAB + TAB + TAB + "int updatedRows = -1;";
		method += LINE;
		method += TAB + TAB + TAB + "sqlCommand(INSERT);";
		method += LINE;
		method += TAB + TAB + TAB + "sqlCleanup();";
		method += LINE;
		Iterator columns = table.getItems().iterator();
		int counter = 0;
		while (columns.hasNext()) {
			ItemModel column = (ItemModel) columns.next();
			String columnName = column.getName().trim();
			String columnJdbcType = getJdbcType(column);
			String columnGetter = "get"
					+ StringUtilities.withFirstCharUpperCase(columnName);
			
			method += TAB + TAB + TAB + "sqlArgument(Types."
			+ columnJdbcType + ", aRecord." + columnGetter + "(), "
			+ counter + ");";
			
			method += LINE;
			counter++;
		}
		Iterator fkColumns = tableInfo.getForeignKeyColumns().iterator();
		while (fkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) fkColumns.next();
			ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
			String directionName = StringUtilities.withFirstCharUpperCase(
					(String) fkColumn.get(2)).trim();

			String columnJdbcType = getJdbcType(parentLinkedColumn);

			BoxModel parent = parentLinkedColumn.getBoxModel();
			BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
			String parentName = parentInfo.getBoxModel().getName().trim();
			String parentRecordClassName = StringUtilities
					.withFirstCharUpperCase(parentName);
			String parentGetPkMethodName = "get"
					+ StringUtilities.withFirstCharUpperCase(parentLinkedColumn
							.getName().trim());

			String columnGetter = "get" + parentRecordClassName + directionName;

			method += TAB + TAB + TAB + "sqlArgument(Types." + columnJdbcType
					+ ", aRecord." + columnGetter + "()!= null?" + "aRecord."
					+ columnGetter + "()." + parentGetPkMethodName
					+ "():null, " + counter + ");";
			method += LINE;
			counter++;
		}
		method += TAB + TAB + TAB + "updatedRows = sqlUpdate();";
		method += LINE;
		method += TAB + TAB + TAB + "release();";
		method += LINE;
		method += TAB + TAB + TAB + "return updatedRows;";
		method += LINE;
		method += TAB + TAB + "}";
		method += LINE;
		method += TAB + TAB + "catch (DbException dbe){";
		method += LINE;
		method += TAB + TAB + TAB + "release();";
		method += LINE;
		method += TAB + TAB + TAB + "throw dbe;";
		method += LINE;
		method += TAB + TAB + "}";
		method += LINE;
		method += TAB + "}";

		return method;
	}

	protected String buildUpdateMethod() {
		String updateMethod = "";
		updateMethod += TAB
				+ "public int update(DbQueryRecordAPI record) throws DbException {";
		updateMethod += LINE;
		updateMethod += TAB + TAB + "if (record == null){";
		updateMethod += LINE;
		updateMethod += TAB + TAB + TAB + "return -1;";
		updateMethod += LINE;
		updateMethod += TAB + TAB + "}";
		updateMethod += LINE;
		updateMethod += TAB + TAB + "try {";
		updateMethod += LINE;
		updateMethod += TAB + TAB + TAB + capitalizedTableName()
				+ " aRecord = (" + capitalizedTableName() + ")record;";
		updateMethod += LINE;
		updateMethod += TAB + TAB + TAB + "int updatedRows = -1;";
		updateMethod += LINE;
		updateMethod += TAB + TAB + TAB + "sqlCommand(UPDATE);";
		updateMethod += LINE;
		updateMethod += TAB + TAB + TAB + "sqlCleanup();";
		updateMethod += LINE;
		Iterator columns = table.getItems().iterator();
		int counter = 0;
		while (columns.hasNext()) {
			ItemModel column = (ItemModel) columns.next();
			if (!column.isOid()) {
				String columnName = column.getName().trim();
				String columnJdbcType = getJdbcType(column);
				String columnGetter = "get"
						+ StringUtilities.withFirstCharUpperCase(columnName);
				
				updateMethod += TAB + TAB + TAB + "sqlArgument(Types."
				+ columnJdbcType + ", aRecord." + columnGetter
				+ "(), " + counter + ");";
				
				updateMethod += LINE;
				counter++;
			}
		}
		Iterator fkColumns = tableInfo.getForeignKeyColumns().iterator();
		while (fkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) fkColumns.next();
			ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
			String directionName = StringUtilities.withFirstCharUpperCase(
					(String) fkColumn.get(2)).trim();

			String columnJdbcType = getJdbcType(parentLinkedColumn);

			BoxModel parent = parentLinkedColumn.getBoxModel();
			BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
			String parentName = parentInfo.getBoxModel().getName().trim();
			String parentRecordClassName = StringUtilities
					.withFirstCharUpperCase(parentName);
			String parentGetPkMethodName = "get"
					+ StringUtilities.withFirstCharUpperCase(parentLinkedColumn
							.getName().trim());

			String columnGetter = "get" + parentRecordClassName + directionName;

			updateMethod += TAB + TAB + TAB + "sqlArgument(Types."
					+ columnJdbcType + ", aRecord." + columnGetter
					+ "()!= null?" + "aRecord." + columnGetter + "()."
					+ parentGetPkMethodName + "():null, " + counter + ");";
			updateMethod += LINE;
			counter++;
		}

		// primary key
		ItemModel pkColumn = table.getOidItem();
		String pkColumnName = pkColumn.getName().trim();
		String pkColumnJdbcType = getJdbcType(pkColumn);
		String pkColumnGetter = "get"
				+ StringUtilities.withFirstCharUpperCase(pkColumnName);
		updateMethod += TAB + TAB + TAB + "sqlArgument(Types."
				+ pkColumnJdbcType + ", aRecord." + pkColumnGetter + "(), "
				+ counter + "); //primary key";
		updateMethod += LINE;

		updateMethod += TAB + TAB + TAB + "updatedRows = sqlUpdate();";
		updateMethod += LINE;
		updateMethod += TAB + TAB + TAB + "release();";
		updateMethod += LINE;
		updateMethod += TAB + TAB + TAB + "return updatedRows;";
		updateMethod += LINE;
		updateMethod += TAB + TAB + "}";
		updateMethod += LINE;
		updateMethod += TAB + TAB + "catch (DbException dbe){";
		updateMethod += LINE;
		updateMethod += TAB + TAB + TAB + "release();";
		updateMethod += LINE;
		updateMethod += TAB + TAB + TAB + "throw dbe;";
		updateMethod += LINE;
		updateMethod += TAB + TAB + "}";
		updateMethod += LINE;
		updateMethod += TAB + "}";

		return updateMethod;
	}

	protected Collection<String> buildDeleteMethods() {
		Collection<String> deleteMethods = new ArrayList<String>();

		// default
		String defaultMethod = "";
		defaultMethod += TAB
				+ "public int delete(DbQueryRecordAPI record) throws DbException {";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + "if (record == null){";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "return -1;";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + "}";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + "try {";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + capitalizedTableName()
				+ " aRecord = (" + capitalizedTableName() + ")record;";
		defaultMethod += LINE;
		ItemModel pkColumn = table.getOidItem();
		String pkColumnJdbcType = getJdbcType(pkColumn);
		String pkColumnJavaType = JdbcUtilities
				.getJavaTypeForJdbcType(pkColumnJdbcType);
		String pkColumnName = pkColumn.getName().trim();
		String pkColumnGetter = "get"
				+ StringUtilities.withFirstCharUpperCase(pkColumnName);
		defaultMethod += TAB + TAB + TAB + "int updatedRows = -1;";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "sqlCommand(DELETE);";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "sqlCleanup();";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "sqlArgument(Types."
				+ pkColumnJdbcType + ", aRecord." + pkColumnGetter + "(), 0);";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "updatedRows = sqlUpdate();";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "release();";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "return updatedRows;";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + "}";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + "catch (DbException dbe){";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "release();";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + TAB + "throw dbe;";
		defaultMethod += LINE;
		defaultMethod += TAB + TAB + "}";
		defaultMethod += LINE;
		defaultMethod += TAB + "}";
		deleteMethods.add(defaultMethod);

		// utility method for primary key
		String pkMethod = "";
		pkMethod += TAB + "public int delete(" + pkColumnJavaType
				+ " oid) throws DbException {";
		pkMethod += LINE;
		pkMethod += TAB + TAB + capitalizedTableName() + " record = new "
				+ capitalizedTableName() + "(oid, getDb());";
		pkMethod += LINE;
		pkMethod += TAB + TAB + "return delete(record);";
		pkMethod += LINE;
		pkMethod += TAB + "}";
		deleteMethods.add(pkMethod);

		// if it exists, a delete method for the unique key
		Collection ukColumns = tableInfo.getUniqueKeyColumns();
		if (!ukColumns.isEmpty()) {
			Iterator xUkColumns = ukColumns.iterator();
			String args = "";
			String sqlArgumentInstructions = "";
			int counter = 0;

			while (xUkColumns.hasNext()) {
				ArrayList ukColumn = (ArrayList) xUkColumns.next();
				String ukColumnName = (String) ukColumn.get(0);
				ItemModel ukColumnItem = (ItemModel) ukColumn.get(1);
				String ukColumnJdbcType = getJdbcType(ukColumnItem);
				String ukColumnJavaType = JdbcUtilities
						.getJavaTypeForJdbcType(ukColumnJdbcType);
				String ukColumnObjectName = StringUtilities
						.withFirstCharLowerCase(ukColumnName);
				args += ukColumnJavaType + " " + ukColumnObjectName;
				sqlArgumentInstructions += TAB + TAB + TAB
						+ "sqlArgument(Types." + ukColumnJdbcType + ", "
						+ ukColumnObjectName + ", " + counter + ");";
				if (xUkColumns.hasNext()) {
					args += ", ";
					sqlArgumentInstructions += LINE;
				}
				counter++;
			}
			String ukMethod = "";
			ukMethod += TAB + "public int deleteUniqueKey(" + args
					+ ") throws DbException {";
			ukMethod += LINE;
			ukMethod += TAB + TAB + "try {";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "int updatedRows = -1;";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "sqlCommand(DELETE_UNIQUE_KEY);";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "sqlCleanup();";
			ukMethod += LINE;
			ukMethod += sqlArgumentInstructions;
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "updatedRows = sqlUpdate();";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "release();";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "return updatedRows;";
			ukMethod += LINE;
			ukMethod += TAB + TAB + "}";
			ukMethod += LINE;
			ukMethod += TAB + TAB + "catch (DbException dbe){";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "release();";
			ukMethod += LINE;
			ukMethod += TAB + TAB + TAB + "throw dbe;";
			ukMethod += LINE;
			ukMethod += TAB + TAB + "}";
			ukMethod += LINE;
			ukMethod += TAB + "}";
			deleteMethods.add(ukMethod);
		}

		// if they exist, a delete method for the foreign keys
		Collection fkColumns = tableInfo.getForeignKeyColumns();
		if (!fkColumns.isEmpty()) {
			Iterator xFkColumns = fkColumns.iterator();

			while (xFkColumns.hasNext()) {
				ArrayList fkColumn = (ArrayList) xFkColumns.next();
				String fkColumnName = (String) fkColumn.get(0);
				ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
				String directionName = StringUtilities.withFirstCharUpperCase(
						(String) fkColumn.get(2)).trim();

				String fkColumnJdbcType = getJdbcType(parentLinkedColumn);
				String fkColumnJavaType = JdbcUtilities
						.getJavaTypeForJdbcType(fkColumnJdbcType);

				BoxModel parent = parentLinkedColumn.getBoxModel();
				BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
				String parentName = parentInfo.getBoxModel().getName().trim();
				String parentRecordClassName = StringUtilities
						.withFirstCharUpperCase(parentName);
				String parentRecordObjectName = StringUtilities
						.withFirstCharLowerCase(parentName);
				String parentGetPkMethodName = "get"
						+ StringUtilities
								.withFirstCharUpperCase(parentLinkedColumn
										.getName().trim());

				String fkParentMethod = "";
				fkParentMethod += TAB + "public int deleteFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName + ") throws DbException {";
				fkParentMethod += LINE;
				fkParentMethod += TAB + TAB + "return deleteForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ parentRecordObjectName + directionName + "."
						+ parentGetPkMethodName + "());";
				fkParentMethod += LINE;
				fkParentMethod += TAB + "}";
				deleteMethods.add(fkParentMethod);

				String fkMethod = "";
				fkMethod += TAB + "public int deleteForForeignKey"
						+ parentRecordClassName + directionName + "("
						+ fkColumnJavaType + " " + fkColumnName
						+ ") throws DbException {";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "try {";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "int updatedRows = -1;";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "sqlCommand(DELETE_FOREIGN_KEY_"
						+ (parentRecordClassName + directionName).toUpperCase()
						+ ");";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "sqlCleanup();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "sqlArgument(Types."
						+ fkColumnJdbcType + ", " + fkColumnName + ", 0);";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "updatedRows = sqlUpdate();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "release();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "return updatedRows;";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "}";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "catch (DbException dbe){";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "release();";
				fkMethod += LINE;
				fkMethod += TAB + TAB + TAB + "throw dbe;";
				fkMethod += LINE;
				fkMethod += TAB + TAB + "}";
				fkMethod += LINE;
				fkMethod += TAB + "}";
				deleteMethods.add(fkMethod);
			}
		}
		return deleteMethods;
	}

	protected String getJdbcType(ItemModel column) {
		TypeModel columnType = column.getTypeModel();
		if (columnType != null) {
			String baseType = columnType.getDbmsType();
			if (JdbcUtilities.isJdbcType(baseType)) {
				return baseType;
			} else {
				String javaType = columnType.getAlias();
				return getJdbcTypeForJavaType(javaType);
			}
		}
		return null;
	}

	// This method is arbitrary, so it is not in JdbcUtilities
	public String getJdbcTypeForJavaType(String javaType) {
		javaType = javaType.trim();
		if (javaType.equalsIgnoreCase("String")
				|| javaType.equalsIgnoreCase("java.lang.String")) {
			return "VARCHAR";
		} else if (javaType.equalsIgnoreCase("Integer")
				|| javaType.equalsIgnoreCase("java.lang.Integer")
				|| javaType.equalsIgnoreCase("int")) {
			return "INTEGER";
		} else if (javaType.equalsIgnoreCase("Boolean")
				|| javaType.equalsIgnoreCase("java.lang.Boolean")) {
			return "BIT";
		} else if (javaType.equalsIgnoreCase("Short")
				|| javaType.equalsIgnoreCase("java.lang.Short")) {
			return "SMALLINT";
		} else if (javaType.equalsIgnoreCase("Double")
				|| javaType.equalsIgnoreCase("java.lang.Double")) {
			return "DOUBLE";
		} else if (javaType.equalsIgnoreCase("Float")
				|| javaType.equalsIgnoreCase("java.lang.Float")) {
			return "REAL";
		} else if (javaType.equalsIgnoreCase("Long")
				|| javaType.equalsIgnoreCase("java.lang.Long")) {
			return "BIGINT";
		} else if (javaType.equalsIgnoreCase("Byte")
				|| javaType.equalsIgnoreCase("java.lang.Byte")) {
			return "TINYINT";
		} else if (javaType.equalsIgnoreCase("Byte[]")
				|| javaType.equalsIgnoreCase("java.lang.Byte[]")) {
			return "BINARY";
		} else if (javaType.equalsIgnoreCase("Object")
				|| javaType.equalsIgnoreCase("java.lang.Object")) {
			return "JAVA_OBJECT";
		} else if (javaType.equalsIgnoreCase("BigDecimal")
				|| javaType.equalsIgnoreCase("java.math.BigDecimal")) {
			return "NUMERIC";
		} else if (javaType.equalsIgnoreCase("Array")
				|| javaType.equalsIgnoreCase("java.sql.Array")) {
			return "ARRAY";
		} else if (javaType.equalsIgnoreCase("Blob")
				|| javaType.equalsIgnoreCase("java.sql.Blob")) {
			return "BLOB";
		} else if (javaType.equalsIgnoreCase("Clob")
				|| javaType.equalsIgnoreCase("java.sql.Clob")) {
			return "CLOB";
		} else if (javaType.equalsIgnoreCase("Date")
				|| javaType.equalsIgnoreCase("java.sql.Date")) {
			return "DATE";
		} else if (javaType.equalsIgnoreCase("java.util.Date")) {
			return "DATE";
		} else if (javaType.equalsIgnoreCase("Ref")
				|| javaType.equalsIgnoreCase("java.sql.Ref")) {
			return "REF";
		} else if (javaType.equalsIgnoreCase("Struct")
				|| javaType.equalsIgnoreCase("java.sql.Struct")) {
			return "STRUCT";
		} else if (javaType.equalsIgnoreCase("Time")
				|| javaType.equalsIgnoreCase("java.sql.Time")) {
			return "TIME";
		} else if (javaType.equalsIgnoreCase("Timestamp")
				|| javaType.equalsIgnoreCase("java.sql.Timestamp")) {
			return "TIMESTAMP";
		} else if (javaType.equalsIgnoreCase("null")) {
			return "NULL";
		} else {
			return "????????";
		}
	}

	protected String buildGetColumnXxxMethodName(ItemModel column) {
		String methodName = "getColumn";
		String type = getJdbcType(column);
		if (type != null) {
			if (type.equalsIgnoreCase("ARRAY"))
				methodName += "Array";
			else if (type.equalsIgnoreCase("BIGINT"))
				methodName += "Long";
			else if (type.equalsIgnoreCase("BINARY"))
				methodName += "Bytes";
			else if (type.equalsIgnoreCase("BIT"))
				methodName += "Boolean";
			else if (type.equalsIgnoreCase("BLOB"))
				methodName += "Blob";
			else if (type.equalsIgnoreCase("CHAR"))
				methodName += "String";
			else if (type.equalsIgnoreCase("CLOB"))
				methodName += "Clob";
			else if (type.equalsIgnoreCase("DATE"))
				methodName += "Date";
			else if (type.equalsIgnoreCase("DECIMAL"))
				methodName += "Double";
			else if (type.equalsIgnoreCase("DISTINCT"))
				methodName += "Integer";
			else if (type.equalsIgnoreCase("DOUBLE"))
				methodName += "Double";
			else if (type.equalsIgnoreCase("FLOAT"))
				methodName += "Double";
			else if (type.equalsIgnoreCase("INTEGER"))
				methodName += "Integer";
			else if (type.equalsIgnoreCase("JAVA_OBJECT"))
				methodName += "Object";
			else if (type.equalsIgnoreCase("LONGVARBINARY"))
				methodName += "Bytes";
			else if (type.equalsIgnoreCase("LONGVARCHAR"))
				methodName += "String";
			else if (type.equalsIgnoreCase("NULL"))
				methodName += "??????";
			else if (type.equalsIgnoreCase("NUMERIC"))
				methodName += "BigDecimal";
			else if (type.equalsIgnoreCase("OTHER"))
				methodName += "Object";
			else if (type.equalsIgnoreCase("REAL"))
				methodName += "Float";
			else if (type.equalsIgnoreCase("REF"))
				methodName += "Ref";
			else if (type.equalsIgnoreCase("SMALLINT"))
				methodName += "Short";
			else if (type.equalsIgnoreCase("STRUCT"))
				methodName += "Object";
			else if (type.equalsIgnoreCase("TIME"))
				methodName += "Time";
			else if (type.equalsIgnoreCase("TIMESTAMP"))
				methodName += "Timestamp";
			else if (type.equalsIgnoreCase("TINYINT"))
				methodName += "Byte";
			else if (type.equalsIgnoreCase("VARBINARY"))
				methodName += "Bytes";
			else if (type.equalsIgnoreCase("VARCHAR"))
				methodName += "String";
		}
		return methodName;
	}

	// overriden from GenBoxModelInterface
	public boolean save() {
		String tableName = table.getName();
		boolean isSequence = false;
		if (!tableName.equalsIgnoreCase("DrdbSequence")) {
			return super.save();
		} else {
			for (int i = 0; i < table.getItems().size(); i++) {
				ItemModel column = table.getItem(i);
				String columnName = column.getName();
				if (columnName.equalsIgnoreCase("seqName")
						|| columnName.equalsIgnoreCase("seq")) {
					isSequence = true;
					break;
				}
			}
			if (!isSequence) {
				return super.save();
			}
		}
		return false;
	}
}
