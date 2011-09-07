package org.modelibra.modeler.gen.dblite.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.gen.GenBoxModelInterface;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.BoxModelKeysInfo;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.model.TypeModel;
import org.modelibra.modeler.util.JdbcUtilities;
import org.modelibra.modeler.util.StringUtilities;

/**
 * 
 * @author Vincent Dussault
 * @author Dzenan Ridjanovic
 * @version 2007-03-02
 */
public class GenDbQueryInterface extends GenBoxModelInterface {

	protected String dbLiteRootPackage = Config.getConfig().getProperty(
			"gen.package.dbLite");

	protected String specificRootPackage = "";

	public GenDbQueryInterface(BoxModelKeysInfo tableInfo, String srcDirPath,
			String specificRootPackage) {
		super(tableInfo, srcDirPath);
		this.specificRootPackage = specificRootPackage;
	}

	public String getPackageName() {
		if (specificRootPackage != null
				&& !specificRootPackage.trim().equals("")) {
			return specificRootPackage + ".db."
					+ table.getName().trim().toLowerCase();
		} else {
			return "db." + table.getName().trim().toLowerCase();
		}
	}

	public Collection<String> getImports() {
		Collection<String> imports = new ArrayList<String>();
		if (tableInfo.isChild()) {
			imports.add("java.util.Collection");
			imports.add("java.util.Iterator");
			imports.add("");
		}
		imports.add(dbLiteRootPackage + ".db.DbException");
		imports.add(dbLiteRootPackage + ".db.DbQueryAPI");

		String prefix = "";
		if (specificRootPackage != null
				&& !specificRootPackage.trim().equals("")) {
			prefix += specificRootPackage + ".";
		}

		if (tableInfo.isChild()) {
			imports.add("");
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

					String aImport = prefix + "db."
							+ parent.getName().trim().toLowerCase() + "."
							+ parentRecordClassName;
					if (!imports.contains(aImport)) {
						imports.add(aImport);
					} else {
						if (xFkColumns.hasNext()) {
							imports.add("");
						}
					}
				}
			}
		}

		return imports;
	}

	public String getInterfaceName() {
		String interfaceName = capitalizedTableName() + "Query";
		return interfaceName;
	}

	public String getExtendsName() {
		return "DbQueryAPI";
	}

	public String capitalizedTableName() {
		return StringUtilities.withFirstCharUpperCase(table.getName());
	}

	public Collection getMethods() {
		Collection<String> methods = new ArrayList<String>();

		Collection<String> getCollectionMethods = buildGetCollectionMethods();
		if (getCollectionMethods.size() > 0) {
			methods.addAll(getCollectionMethods);
			methods.add("");
		}

		Collection<String> retrieveMethods = buildRetrieveMethods();
		if (retrieveMethods.size() > 0) {
			methods.addAll(retrieveMethods);
			methods.add("");
		}

		Collection<String> containsMethods = buildContainsMethods();
		if (containsMethods.size() > 0) {
			methods.addAll(containsMethods);
			methods.add("");
		}

		Collection<String> getCountMethods = buildGetCountMethods();
		if (getCountMethods.size() > 0) {
			methods.addAll(getCountMethods);
			methods.add("");
		}

		methods.addAll(buildDeleteMethods());

		return methods;
	}

	protected Collection<String> buildRetrieveMethods() {
		Collection<String> retrieveMethods = new ArrayList<String>();

		// default
		ItemModel pkColumn = table.getOidItem();
		String pkColumnJdbcType = getJdbcType(pkColumn);
		String pkColumnJavaType = JdbcUtilities
				.getJavaTypeForJdbcType(pkColumnJdbcType);

		// utility method for primary key
		String pkMethod = "";
		pkMethod += TAB + "public " + capitalizedTableName() + " retrieve("
				+ pkColumnJavaType + " oid) throws DbException;";
		retrieveMethods.add(pkMethod);

		// if it exists, a retrieve method for the unique key
		Collection<Collection<Object>> ukColumns = tableInfo
				.getUniqueKeyColumns();
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
				if (xUkColumns.hasNext()) {
					args += ", ";
					sqlArgumentInstructions += LINE;
				}
				counter++;
			}
			String ukMethod = "";
			ukMethod += TAB + "public " + capitalizedTableName()
					+ " retrieveUniqueKey(" + args + ") throws DbException;";
			retrieveMethods.add(ukMethod);
		}
		return retrieveMethods;
	}

	protected Collection<String> buildContainsMethods() {
		Collection<String> retrieveMethods = new ArrayList<String>();

		// default
		ItemModel pkColumn = table.getOidItem();
		String pkColumnJdbcType = getJdbcType(pkColumn);
		String pkColumnJavaType = JdbcUtilities
				.getJavaTypeForJdbcType(pkColumnJdbcType);

		// utility method for primary key
		String pkMethod = "";
		pkMethod += TAB + "public boolean contains(" + pkColumnJavaType
				+ " oid) throws DbException;";
		retrieveMethods.add(pkMethod);

		// if it exists, a retrieve method for the unique key
		Collection<Collection<Object>> ukColumns = tableInfo
				.getUniqueKeyColumns();
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
				if (xUkColumns.hasNext()) {
					args += ", ";
					sqlArgumentInstructions += LINE;
				}
				counter++;
			}
			String ukMethod = "";
			ukMethod += TAB + "public boolean containsUniqueKey(" + args
					+ ") throws DbException;";
			retrieveMethods.add(ukMethod);
		}
		return retrieveMethods;
	}

	protected Collection<String> buildGetCollectionMethods() {
		Collection<String> getCollectionMethods = new ArrayList<String>();
		// if they exist, a getCollection method for the foreign keys
		Collection<Collection<Object>> fkColumns = tableInfo
				.getForeignKeyColumns();
		if (!fkColumns.isEmpty()) {
			Iterator xFkColumns = fkColumns.iterator();

			while (xFkColumns.hasNext()) {
				ArrayList fkColumn = (ArrayList) xFkColumns.next();
				String fkColumnName = (String) fkColumn.get(0);
				ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
				String directionName = StringUtilities.withFirstCharUpperCase(
						(String) fkColumn.get(2)).trim();

				String fkColumnObjectName = StringUtilities
						.withFirstCharLowerCase(fkColumnName);

				BoxModel parent = parentLinkedColumn.getBoxModel();
				BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
				String parentName = parentInfo.getBoxModel().getName().trim();
				String parentRecordClassName = StringUtilities
						.withFirstCharUpperCase(parentName);
				String parentRecordObjectName = StringUtilities
						.withFirstCharLowerCase(parentRecordClassName);

				if (fkColumnObjectName.endsWith("?")) {
					fkColumnObjectName = "oid" + parentRecordClassName;
				}

				String parentOneArgMethod = "";
				parentOneArgMethod += TAB
						+ "public Collection getCollectionFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName + ") throws DbException;";
				getCollectionMethods.add(parentOneArgMethod);

				String parentTwoArgsMethod = "";
				parentTwoArgsMethod += TAB
						+ "public Collection getCollectionFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName + ", int begin) throws DbException;";
				getCollectionMethods.add(parentTwoArgsMethod);

				String parentThreeArgsMethod = "";
				parentThreeArgsMethod += TAB
						+ "public Collection getCollectionFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName
						+ ", int begin, int maxSize) throws DbException;";
				getCollectionMethods.add(parentThreeArgsMethod);
			}
		}
		return getCollectionMethods;
	}

	protected Collection<String> buildIteratorMethods() {
		Collection<String> iteratorMethods = new ArrayList<String>();
		// if they exist, a getCollection method for the foreign keys
		Collection<Collection<Object>> fkColumns = tableInfo
				.getForeignKeyColumns();
		if (!fkColumns.isEmpty()) {
			Iterator xFkColumns = fkColumns.iterator();

			while (xFkColumns.hasNext()) {
				ArrayList fkColumn = (ArrayList) xFkColumns.next();
				String fkColumnName = (String) fkColumn.get(0);
				ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
				String directionName = StringUtilities.withFirstCharUpperCase(
						(String) fkColumn.get(2)).trim();

				String fkColumnObjectName = StringUtilities
						.withFirstCharLowerCase(fkColumnName);

				BoxModel parent = parentLinkedColumn.getBoxModel();
				BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
				String parentName = parentInfo.getBoxModel().getName().trim();
				String parentRecordClassName = StringUtilities
						.withFirstCharUpperCase(parentName);
				String parentRecordObjectName = StringUtilities
						.withFirstCharLowerCase(parentRecordClassName);

				if (fkColumnObjectName.endsWith("?")) {
					fkColumnObjectName = "oid" + parentRecordClassName;
				}

				String parentMethod = "";
				parentMethod += TAB + "public Iterator iteratorFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName + ") throws DbException;";
				iteratorMethods.add(parentMethod);
			}
		}
		return iteratorMethods;
	}

	protected Collection<String> buildGetCountMethods() {
		Collection<String> getCountMethods = new ArrayList<String>();
		// if they exist, a getCount method for the foreign keys
		Collection<Collection<Object>> fkColumns = tableInfo
				.getForeignKeyColumns();
		if (!fkColumns.isEmpty()) {
			Iterator xFkColumns = fkColumns.iterator();

			while (xFkColumns.hasNext()) {
				ArrayList fkColumn = (ArrayList) xFkColumns.next();
				String fkColumnName = (String) fkColumn.get(0);
				ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
				String directionName = StringUtilities.withFirstCharUpperCase(
						(String) fkColumn.get(2)).trim();

				String fkColumnObjectName = StringUtilities
						.withFirstCharLowerCase(fkColumnName);

				BoxModel parent = parentLinkedColumn.getBoxModel();
				BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
				String parentName = parentInfo.getBoxModel().getName().trim();
				String parentRecordClassName = StringUtilities
						.withFirstCharUpperCase(parentName);
				String parentRecordObjectName = StringUtilities
						.withFirstCharLowerCase(parentRecordClassName);

				if (fkColumnObjectName.endsWith("?")) {
					fkColumnObjectName = "oid" + parentRecordClassName;
				}

				String fkParentMethod = "";
				fkParentMethod += TAB + "public int getCountFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName + ") throws DbException;";
				getCountMethods.add(fkParentMethod);
			}
		}
		return getCountMethods;
	}

	protected Collection<String> buildDeleteMethods() {
		Collection<String> deleteMethods = new ArrayList<String>();

		// default
		ItemModel pkColumn = table.getOidItem();
		String pkColumnJdbcType = getJdbcType(pkColumn);
		String pkColumnJavaType = JdbcUtilities
				.getJavaTypeForJdbcType(pkColumnJdbcType);

		// utility method for primary key
		String pkMethod = "";
		pkMethod += TAB + "public int delete(" + pkColumnJavaType
				+ " oid) throws DbException;";
		deleteMethods.add(pkMethod);

		// if it exists, a delete method for the unique key
		Collection<Collection<Object>> ukColumns = tableInfo
				.getUniqueKeyColumns();
		if (!ukColumns.isEmpty()) {
			Iterator xUkColumns = ukColumns.iterator();
			String args = "";
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
				if (xUkColumns.hasNext()) {
					args += ", ";
				}
				counter++;
			}
			String ukMethod = "";
			ukMethod += TAB + "public int deleteUniqueKey(" + args
					+ ") throws DbException;";
			deleteMethods.add(ukMethod);
		}

		// if they exist, a delete method for the foreign keys
		Collection<Collection<Object>> fkColumns = tableInfo
				.getForeignKeyColumns();
		if (!fkColumns.isEmpty()) {
			Iterator xFkColumns = fkColumns.iterator();

			while (xFkColumns.hasNext()) {
				ArrayList fkColumn = (ArrayList) xFkColumns.next();
				String fkColumnName = (String) fkColumn.get(0);
				ItemModel parentLinkedColumn = (ItemModel) fkColumn.get(1);
				String directionName = StringUtilities.withFirstCharUpperCase(
						(String) fkColumn.get(2)).trim();

				String fkColumnObjectName = StringUtilities
						.withFirstCharLowerCase(fkColumnName);

				BoxModel parent = parentLinkedColumn.getBoxModel();
				BoxModelKeysInfo parentInfo = new BoxModelKeysInfo(parent);
				String parentName = parentInfo.getBoxModel().getName().trim();
				String parentRecordClassName = StringUtilities
						.withFirstCharUpperCase(parentName);
				String parentRecordObjectName = StringUtilities
						.withFirstCharLowerCase(parentName);

				if (fkColumnObjectName.endsWith("?")) {
					fkColumnObjectName = "oid" + parentRecordClassName;
				}

				String fkParentMethod = "";
				fkParentMethod += TAB + "public int deleteFor"
						+ parentRecordClassName + directionName + "("
						+ parentRecordClassName + " " + parentRecordObjectName
						+ directionName + ") throws DbException;";
				deleteMethods.add(fkParentMethod);
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
