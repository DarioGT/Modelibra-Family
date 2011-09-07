/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.modelibra;

/**
 * Selector (selection rule) interface. Selection rules are: NOT, AND, OR, ALL,
 * NONE, NULL, EQUAL, CONTAIN, CONTAIN_SOME, CONTAIN_ALL, BEGIN, END, MATCH, IN,
 * LESS_THAN, LESS_EQUAL, GREATER_THAN, GREATER_EQUAL, BETWEEN.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public interface ISelector {

	// logical operators

	public static final String NOT = "NOT";

	public static final String AND = "AND";

	public static final String OR = "OR";

	// relational operators

	public static final String ALL = "ALL";

	public static final String NONE = "NONE";

	public static final String NULL = "NULL";

	public static final String EQUAL = "EQUAL";

	public static final String CONTAIN = "CONTAIN";

	public static final String CONTAIN_SOME = "CONTAIN_SOME";

	public static final String CONTAIN_ALL = "CONTAIN_ALL";

	public static final String BEGIN = "BEGIN";

	public static final String END = "END";

	public static final String MATCH = "MATCH";

	public static final String IN = "IN";

	public static final String LESS_THAN = "LESS_THAN";

	public static final String LESS_EQUAL = "LESS_EQUAL";

	public static final String GREATER_THAN = "GREATER_THAN";

	public static final String GREATER_EQUAL = "GREATER_EQUAL";

	public static final String BETWEEN = "BETWEEN";

}
