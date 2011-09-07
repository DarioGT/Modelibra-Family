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
package org.modelibra.persistency.xml;

import java.net.URL;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.modelibra.DomainModel;
import org.modelibra.Entity;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.Oid;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.Config;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.PersistencyRuntimeException;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.persistency.IPersistentEntity;
import org.modelibra.persistency.IPersistentModel;
import org.modelibra.type.Email;
import org.modelibra.type.PropertyClass;
import org.modelibra.util.Reflector;
import org.modelibra.util.TextHandler;
import org.modelibra.util.Transformer;

/**
 * XmlEntity class to implement common behavior of an XML persistent entity.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2009-01-29
 */
public class XmlEntity implements IPersistentEntity {

	private static final long serialVersionUID = 4820L;

	private static Log log = LogFactory.getLog(XmlEntity.class);

	private IEntity<?> entity;

	private IPersistentModel persistentModel;

	/**
	 * Constructs an XML persistent entity from a given entity, within the
	 * persistent model.
	 * 
	 * @param entity
	 *            entity
	 * @param persistentModel
	 *            persistent model
	 */
	public XmlEntity(IEntity<?> entity, IPersistentModel persistentModel) {
		this.entity = entity;
		this.persistentModel = persistentModel;
	}

	/**
	 * Gets the entity.
	 * 
	 * @return entity
	 */
	public IEntity<?> getEntity() {
		return entity;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return persistentModel;
	}

	/**
	 * Loads an XML element.
	 * 
	 * @param element
	 *            XML element
	 */
	protected void load(Element element) {
		Entity<?> entity = (Entity<?>) getEntity();
		Attribute oidAttribute = element.attribute("oid");
		if (oidAttribute != null) {
			String oidString = oidAttribute.getText().trim();
			try {
				Oid oid = new Oid(Transformer.longInteger(oidString));
				entity.setOid(oid);
			} catch (TypeRuntimeException e) {
				String msg = "XmlEntity.load -- oid is not Integer: "
						+ oidString;
				throw new PersistencyRuntimeException(msg, e);
			}
		}

		Element codeChild = element.element("code");
		if (codeChild != null) {
			String code = codeChild.getText().trim();
			entity.setCode(code);
		}

		loadPropertyChildren(element);
		loadNeighborChildren(element);
	}

	/**
	 * Loads a default element. Needed for Java reflection getMethod.
	 * 
	 * @param defaultElement
	 *            default element
	 */
	public void load(org.dom4j.tree.DefaultElement defaultElement) {
		Element element = defaultElement;
		load(element);
	}

	/**
	 * Loads property children of an XML element. Meta handling is used.
	 * 
	 * @param element
	 *            XML element
	 */
	private void loadPropertyChildren(Element element) {
		ConceptConfig conceptConfig = entity.getConceptConfig();
		if (conceptConfig != null) {
			PropertiesConfig propertiesConfig = conceptConfig
					.getPropertiesConfig();
			for (PropertyConfig propertyConfig : propertiesConfig) {
				if (propertyConfig.isDerived()) {
					continue;
				}
				String propertyCode = propertyConfig.getCode();
				if (propertyCode.equals("code")) {
					continue;
				}
				String conceptDotProperty = conceptConfig.getCode() + "."
						+ propertyCode;
				Element codeChild = element.element(propertyCode);
				if (codeChild == null) {
					if (propertyConfig.isRequired()) {
						String msg = "XmlEntity.loadPropertyChildren -- XML element for the property does not exist: "
								+ conceptDotProperty;
						throw new PersistencyRuntimeException(msg);
					} // else is a normal case of not having an XML element for
					// null
				} else { // if (codeChild != null)
					String propertyStringValue = codeChild.getText();
					String propertyClass = propertyConfig.getPropertyClass();
					if (propertyClass.equals(PropertyClass.getString())) {
						entity.setProperty(propertyCode, propertyStringValue);
					} else if (propertyClass.equals(PropertyClass.getInteger())) {
						try {
							Integer propertyValue = Transformer
									.integer(propertyStringValue);
							entity.setProperty(propertyCode, propertyValue);
						} catch (TypeRuntimeException e) {
							String msg = "XmlEntity.loadPropertyChildren -- XML element for the property is not Integer: "
									+ conceptDotProperty;
							if (propertyConfig.isRequired()) {
								throw new PersistencyRuntimeException(msg, e);
							} else {
								log.info(msg);
							}
						}
					} else if (propertyClass.equals(PropertyClass.getFloat())) {
						try {
							Float propertyValue = Transformer
									.floatDecimal(propertyStringValue);
							entity.setProperty(propertyCode, propertyValue);
						} catch (TypeRuntimeException e) {
							String msg = "XmlEntity.loadPropertyChildren -- XML element for the property is not Float: "
									+ conceptDotProperty;
							if (propertyConfig.isRequired()) {
								throw new PersistencyRuntimeException(msg, e);
							} else {
								log.info(msg);
							}
						}
					} else if (propertyClass.equals(PropertyClass.getBoolean())) {
						try {
							Boolean propertyValue = Transformer
									.logic(propertyStringValue);
							entity.setProperty(propertyCode, propertyValue);
						} catch (TypeRuntimeException e) {
							String msg = "XmlEntity.loadPropertyChildren -- XML element for the property is not Boolean: "
									+ conceptDotProperty;
							if (propertyConfig.isRequired()) {
								throw new PersistencyRuntimeException(msg, e);
							} else {
								log.info(msg);
							}
						}
					} else if (propertyClass.equals(PropertyClass.getDate())) {
						try {
							Date propertyValue = Transformer.date(
									propertyStringValue, conceptConfig
											.getModelConfig().getDatePattern());
							entity.setProperty(propertyCode, propertyValue);
						} catch (TypeRuntimeException e) {
							String msg = "XmlEntity.loadPropertyChildren -- XML element for the property is not java.util.Date: "
									+ conceptDotProperty;
							if (propertyConfig.isRequired()) {
								throw new PersistencyRuntimeException(msg, e);
							} else {
								log.info(msg);
							}
						}
					} else if (propertyClass.equals(PropertyClass.getUrl())) {
						try {
							URL propertyValue = Transformer
									.url(propertyStringValue);
							entity.setProperty(propertyCode, propertyValue);
						} catch (TypeRuntimeException e) {
							String msg = "XmlEntity.loadPropertyChildren -- XML element for the property is not java.net.URL: "
									+ conceptDotProperty;
							if (propertyConfig.isRequired()) {
								throw new PersistencyRuntimeException(msg, e);
							} else {
								log.info(msg);
							}
						}
					} else if (propertyClass.equals(PropertyClass.getEmail())) {
						try {
							Email propertyValue = Transformer
									.email(propertyStringValue);
							entity.setProperty(propertyCode, propertyValue);
						} catch (TypeRuntimeException e) {
							String msg = "XmlEntity.loadPropertyChildren -- XML element for the property is not org.modelibra.type.Email: "
									+ conceptDotProperty;
							if (propertyConfig.isRequired()) {
								throw new PersistencyRuntimeException(msg, e);
							} else {
								log.info(msg);
							}
						}
					} else if (propertyClass.equals(PropertyClass.getLong())) {
						try {
							Long propertyValue = Transformer
									.longInteger(propertyStringValue);
							entity.setProperty(propertyCode, propertyValue);
						} catch (TypeRuntimeException e) {
							String msg = "XmlEntity.loadPropertyChildren -- XML element for the property is not Long: "
									+ conceptDotProperty;
							if (propertyConfig.isRequired()) {
								throw new PersistencyRuntimeException(msg, e);
							} else {
								log.info(msg);
							}
						}
					} else if (propertyClass.equals(PropertyClass.getDouble())) {
						try {
							Double propertyValue = Transformer
									.doubleDecimal(propertyStringValue);
							entity.setProperty(propertyCode, propertyValue);
						} catch (TypeRuntimeException e) {
							String msg = "XmlEntity.loadPropertyChildren -- XML element for the property is not Double: "
									+ conceptDotProperty;
							if (propertyConfig.isRequired()) {
								throw new PersistencyRuntimeException(msg, e);
							} else {
								log.info(msg);
							}
						}
					} else {
						String msg = "XmlEntity.loadPropertyChildren -- property is not one of the base classes of Modelibra: "
								+ conceptDotProperty;
						log.info(msg);
						entity.setProperty(propertyCode, propertyStringValue);
					}
				} // end if (codeChild == null)
			}
		}
	}

	/**
	 * Loads neighbor children of an XML element. Meta handling is used.
	 * 
	 * @param element
	 *            XML element
	 */
	private void loadNeighborChildren(Element element) {
		if (entity.getConceptConfig() != null) {
			NeighborsConfig neighborsConfig = entity.getConceptConfig()
					.getNeighborsConfig();
			TextHandler textHandler = new TextHandler();
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.getType().equals("child")
						&& neighborConfig.isInternal()) {
					String neigborProperty = textHandler
							.firstLetterToLower(neighborConfig.getCode());
					Element neighborChildElement = element
							.element(neigborProperty);
					ConceptConfig neighborChildConceptConfig = (ConceptConfig) persistentModel
							.getModel().getModelConfig().getConceptsConfig()
							.retrieveByCode(
									neighborConfig.getDestinationConcept());
					if (neighborChildConceptConfig != null) {
						String neighborChildClassName = neighborChildConceptConfig
								.getEntitiesClass();
						DomainModel model = (DomainModel) persistentModel
								.getModel();
						IEntities<?> neighborChildEntities = model
								.getModelMeta().createEntities(
										neighborChildClassName);
						String neighborCode = neighborConfig.getCode();
						String setChildMethodName = "set"
								+ textHandler.firstLetterToUpper(neighborCode);
						entity.setChildNeighbor(neighborCode,
								neighborChildEntities);
						XmlEntities<?> neighborChildXmlEntities = new XmlEntities(
								neighborChildEntities, persistentModel);
						if (neighborChildElement != null) {
							neighborChildXmlEntities.load(neighborChildElement);
						}
					} // end if
				} // end if
			} // end for
		} // end if
	}

	/**
	 * Fills an XML element.
	 * 
	 * @param element
	 *            XML element
	 */
	protected void fill(Element element) {
		Entity<?> entity = (Entity<?>) getEntity();

		String oidString = Transformer
				.string(entity.getOid().getUniqueNumber());
		if (oidString != null) {
			element.addAttribute("oid", oidString);
		}

		String code = entity.getCode();
		if (code != null) {
			element.addElement("code").addText(code);
		}

		fillPropertyChildren(element);
		fillNeighborChildren(element);
	}

	/**
	 * Fills a default element. Needed for Java reflection setMethod.
	 * 
	 * @param defaultElement
	 *            default element
	 */
	public void fill(org.dom4j.tree.DefaultElement defaultElement) {
		Element element = defaultElement;
		fill(element);
	}

	/**
	 * Fills an XML element with property children. Meta handling is used.
	 * 
	 * @param element
	 *            XML element
	 */
	private void fillPropertyChildren(Element element) {
		if (entity.getConceptConfig() != null) {
			PropertiesConfig propertiesConfig = entity.getConceptConfig()
					.getPropertiesConfig();
			TextHandler textHandler = new TextHandler();
			for (PropertyConfig propertyConfig : propertiesConfig) {
				if (propertyConfig.isDerived()) {
					continue;
				}
				String propertyCode = propertyConfig.getCode();
				if (propertyCode.equals("code")) {
					continue;
				}
				String conceptDotProperty = entity.getConceptConfig().getCode()
						+ "." + propertyCode;
				String cPropertyCode = textHandler
						.firstLetterToUpper(propertyCode);
				String getMethodName = "get" + cPropertyCode;
				Object getObject = Reflector.executeMethod(entity,
						getMethodName);
				if (getObject == null) {
					if (propertyConfig.isRequired()) {
						String msg = "XmlEntity.fillPropertyChildren -- property is required: "
								+ conceptDotProperty;
						throw new TypeRuntimeException(msg);
					} // else is a normal case of not required property
				} else { // if (getObject != null)
					if (getObject instanceof String) {
						String fieldValue = (String) getObject;
						String fieldValidXmlValue = textHandler
								.stripNonValidXMLCharacters(fieldValue);
						element.addElement(propertyCode).addText(
								fieldValidXmlValue);
					} else if (getObject instanceof Integer) {
						Integer fieldIntegerValue = (Integer) getObject;
						String fieldValue = Transformer
								.string(fieldIntegerValue);
						element.addElement(propertyCode).addText(fieldValue);
					} else if (getObject instanceof Float) {
						Float fieldFloatValue = (Float) getObject;
						String fieldValue = Transformer.string(fieldFloatValue);
						element.addElement(propertyCode).addText(fieldValue);
					} else if (getObject instanceof Boolean) {
						Boolean fieldBooleanValue = (Boolean) getObject;
						String fieldValue = Transformer
								.string(fieldBooleanValue);
						element.addElement(propertyCode).addText(fieldValue);
					} else if (getObject instanceof java.util.Date) {
						Date fieldDateValue = (Date) getObject;
						String datePattern = entity.getConceptConfig()
								.getModelConfig().getDatePattern();
						if (datePattern == null) {
							datePattern = Config.DEFAULT_DATE_PATTERN;
						}
						String fieldValue = Transformer.string(fieldDateValue,
								datePattern);
						element.addElement(propertyCode).addText(fieldValue);
					} else if (getObject instanceof URL) {
						URL fieldURLValue = (URL) getObject;
						String fieldValue = Transformer.string(fieldURLValue);
						element.addElement(propertyCode).addText(fieldValue);
					} else if (getObject instanceof Email) {
						Email fieldEmailValue = (Email) getObject;
						String fieldValue = Transformer.string(fieldEmailValue);
						element.addElement(propertyCode).addText(fieldValue);
					} else if (getObject instanceof Long) {
						Long fieldLongValue = (Long) getObject;
						String fieldValue = Transformer.string(fieldLongValue);
						element.addElement(propertyCode).addText(fieldValue);
					} else if (getObject instanceof Double) {
						Double fieldDoubleValue = (Double) getObject;
						String fieldValue = Transformer
								.string(fieldDoubleValue);
						element.addElement(propertyCode).addText(fieldValue);
					} else {
						String msg = "XmlEntity.fillPropertyChildren -- property is not one of the base classes of Modelibra: "
								+ conceptDotProperty;
						log.info(msg);
						element.addElement(propertyCode).addText(
								getObject.toString());
					}
				} // end if (getObject == null)
			} // end for
		} // end if
	}

	/**
	 * Fills an XML element with neighbor children. Meta handling is used.
	 * 
	 * @param element
	 *            XML element
	 */
	private void fillNeighborChildren(Element element) {
		if (entity.getConceptConfig() != null) {
			NeighborsConfig neighborsConfig = entity.getConceptConfig()
					.getNeighborsConfig();
			TextHandler textHandler = new TextHandler();
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.getType().equals("child")
						&& neighborConfig.isInternal()) {
					String neigborProperty = textHandler
							.firstLetterToLower(neighborConfig.getCode());
					IEntities<?> neighborChildEntities = (IEntities<?>) Reflector
							.getProperty(entity, neigborProperty);
					if (neighborChildEntities != null) {
						if (!neighborChildEntities.getList().isEmpty()) {
							Element neighborChildElement = element
									.addElement(neigborProperty);
							XmlEntities<?> neighborChildXmlEntities = new XmlEntities(
									neighborChildEntities, persistentModel);
							if (neighborChildElement != null) {
								String childElementCode = textHandler
										.firstLetterToLower(neighborConfig
												.getDestinationConcept());
								neighborChildXmlEntities.fill(
										neighborChildElement, childElementCode);
							}
						}
					}
				} // end if
			} // end for
		} // end if
	}

}