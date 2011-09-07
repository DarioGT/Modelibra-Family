package org.modelibra.modeler.util;

import java.sql.Types;

/**
 * 
 * @author Vincent Dussault
 * @version 2003
 */
public class JdbcUtilities {

	public static boolean isJdbcType(String type) {
		type = type.trim();
		if (type.equalsIgnoreCase("ARRAY"))
			return true;
		else if (type.equalsIgnoreCase("BIGINT"))
			return true;
		else if (type.equalsIgnoreCase("BINARY"))
			return true;
		else if (type.equalsIgnoreCase("BIT"))
			return true;
		else if (type.equalsIgnoreCase("BLOB"))
			return true;
		else if (type.equalsIgnoreCase("CHAR"))
			return true;
		else if (type.equalsIgnoreCase("CLOB"))
			return true;
		else if (type.equalsIgnoreCase("DATE"))
			return true;
		else if (type.equalsIgnoreCase("DECIMAL"))
			return true;
		else if (type.equalsIgnoreCase("DISTINCT"))
			return true;
		else if (type.equalsIgnoreCase("DOUBLE"))
			return true;
		else if (type.equalsIgnoreCase("FLOAT"))
			return true;
		else if (type.equalsIgnoreCase("INTEGER"))
			return true;
		else if (type.equalsIgnoreCase("JAVA_OBJECT"))
			return true;
		else if (type.equalsIgnoreCase("LONGVARBINARY"))
			return true;
		else if (type.equalsIgnoreCase("LONGVARCHAR"))
			return true;
		else if (type.equalsIgnoreCase("NULL"))
			return true;
		else if (type.equalsIgnoreCase("NUMERIC"))
			return true;
		else if (type.equalsIgnoreCase("OTHER"))
			return true;
		else if (type.equalsIgnoreCase("REAL"))
			return true;
		else if (type.equalsIgnoreCase("REF"))
			return true;
		else if (type.equalsIgnoreCase("SMALLINT"))
			return true;
		else if (type.equalsIgnoreCase("STRUCT"))
			return true;
		else if (type.equalsIgnoreCase("TIME"))
			return true;
		else if (type.equalsIgnoreCase("TIMESTAMP"))
			return true;
		else if (type.equalsIgnoreCase("TINYINT"))
			return true;
		else if (type.equalsIgnoreCase("VARBINARY"))
			return true;
		else if (type.equalsIgnoreCase("VARCHAR"))
			return true;
		else
			return false;
	}

	public static String getColumnJdbcType(int typeInt) {
		switch (typeInt) {
		case Types.ARRAY:
			return "ARRAY";
		case Types.BIGINT:
			return "BIGINT";
		case Types.BINARY:
			return "BINARY";
		case Types.BIT:
			return "BIT";
		case Types.BLOB:
			return "BLOB";
		case Types.CHAR:
			return "CHAR";
		case Types.CLOB:
			return "CLOB";
		case Types.DATE:
			return "DATE";
		case Types.DECIMAL:
			return "DECIMAL";
		case Types.DISTINCT:
			return "DISTINCT";
		case Types.DOUBLE:
			return "DOUBLE";
		case Types.FLOAT:
			return "FLOAT";
		case Types.INTEGER:
			return "INTEGER";
		case Types.JAVA_OBJECT:
			return "JAVA_OBJECT";
		case Types.LONGVARBINARY:
			return "LONGVARBINARY";
		case Types.LONGVARCHAR:
			return "LONGVARCHAR";
		case Types.NULL:
			return "NULL";
		case Types.NUMERIC:
			return "NUMERIC";
		case Types.OTHER:
			return "OTHER";
		case Types.REAL:
			return "REAL";
		case Types.REF:
			return "REF";
		case Types.SMALLINT:
			return "SMALLINT";
		case Types.STRUCT:
			return "STRUCT";
		case Types.TIME:
			return "TIME";
		case Types.TIMESTAMP:
			return "TIMESTAMP";
		case Types.TINYINT:
			return "TINYINT";
		case Types.VARBINARY:
			return "VARBINARY";
		case Types.VARCHAR:
			return "VARCHAR";
		}
		return "Unknown Type";
	}

	public static String getJavaTypeForJdbcType(String jdbcType) {
		jdbcType = jdbcType.trim();
		if (jdbcType.equalsIgnoreCase("ARRAY"))
			return "java.sql.Array";
		else if (jdbcType.equalsIgnoreCase("BIGINT"))
			return "Long";
		else if (jdbcType.equalsIgnoreCase("BINARY"))
			return "Byte[]";
		else if (jdbcType.equalsIgnoreCase("BIT"))
			return "Boolean";
		else if (jdbcType.equalsIgnoreCase("BLOB"))
			return "java.sql.Blob";
		else if (jdbcType.equalsIgnoreCase("CHAR"))
			return "String";
		else if (jdbcType.equalsIgnoreCase("CLOB"))
			return "java.sql.Clob";
		else if (jdbcType.equalsIgnoreCase("DATE"))
			return "java.sql.Date";
		else if (jdbcType.equalsIgnoreCase("DECIMAL"))
			return "java.math.BigDecimal";
		else if (jdbcType.equalsIgnoreCase("DISTINCT"))
			return "Integer";
		else if (jdbcType.equalsIgnoreCase("DOUBLE"))
			return "Double";
		else if (jdbcType.equalsIgnoreCase("FLOAT"))
			return "Double";
		else if (jdbcType.equalsIgnoreCase("INTEGER"))
			return "Integer";
		else if (jdbcType.equalsIgnoreCase("JAVA_OBJECT"))
			return "Object";
		else if (jdbcType.equalsIgnoreCase("LONGVARBINARY"))
			return "Byte[]";
		else if (jdbcType.equalsIgnoreCase("LONGVARCHAR"))
			return "String";
		else if (jdbcType.equalsIgnoreCase("NULL"))
			return "null";
		else if (jdbcType.equalsIgnoreCase("NUMERIC"))
			return "java.math.BigDecimal";
		else if (jdbcType.equalsIgnoreCase("OTHER"))
			return "Object";
		else if (jdbcType.equalsIgnoreCase("REAL"))
			return "Float";
		else if (jdbcType.equalsIgnoreCase("REF"))
			return "java.sql.Ref";
		else if (jdbcType.equalsIgnoreCase("SMALLINT"))
			return "Short";
		else if (jdbcType.equalsIgnoreCase("STRUCT"))
			return "java.sql.Struct";
		else if (jdbcType.equalsIgnoreCase("TIME"))
			return "java.sql.Time";
		else if (jdbcType.equalsIgnoreCase("TIMESTAMP"))
			return "java.sql.Timestamp";
		else if (jdbcType.equalsIgnoreCase("TINYINT"))
			return "Byte";
		else if (jdbcType.equalsIgnoreCase("VARBINARY"))
			return "Byte[]";
		else if (jdbcType.equalsIgnoreCase("VARCHAR"))
			return "String";
		else
			return null;
	}
}