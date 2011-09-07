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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.DomainModel;
import org.modelibra.exception.PersistencyRuntimeException;
import org.modelibra.persistency.IPersistentEntities;
import org.modelibra.persistency.IPersistentModel;
import org.modelibra.util.TextHandler;

/**
 * <p>
 * XmlEntities class to implement common behavior of XML persistent entities.
 * </p>
 * 
 * <p>
 * Entitis are saved (loaded) to (from) an XML file.
 * </p>
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class XmlEntities<T extends IEntity<T>> implements IPersistentEntities {

	private static final long serialVersionUID = 4810L;

	private IEntities<T> entities;

	private IPersistentModel persistentModel;

	private String dataFilePath;

	/**
	 * Constructs XML persistent entities from given entities, within the
	 * persistent model.
	 * 
	 * @param entities
	 *            entities
	 * @param persistentModel
	 *            persistent model
	 */
	public XmlEntities(IEntities<T> entities, IPersistentModel persistentModel) {
		this.entities = entities;
		this.persistentModel = persistentModel;
	}

	/**
	 * Sets the data file path.
	 * 
	 * @param dataFilePath
	 *            data file path
	 */
	public void setDataFilePath(String dataFilePath) {
		this.dataFilePath = dataFilePath;
	}

	/**
	 * Gets the data file path.
	 * 
	 * @return data file path
	 */
	public String getDataFilePath() {
		if (dataFilePath == null) {
			String fileName = entities.getConceptConfig().getFileName();
			String sep = File.separator;
			XmlModel xmlModel = (XmlModel) persistentModel;
			String dataDirectoryPath = xmlModel.getDataDirectoryPath();
			File dataDirectory = new File(dataDirectoryPath);
			if (!dataDirectory.exists()) {
				dataDirectory.mkdirs();
			}
			dataFilePath = dataDirectoryPath + sep + fileName;
		}
		return dataFilePath;
	}

	/**
	 * Gets the entities.
	 * 
	 * @return entities
	 */
	public IEntities<T> getEntities() {
		return entities;
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
	 * Loads entry entities from the XML data file.
	 */
	public void load() {
		try {
			SAXReader reader = new SAXReader();
			String dataFilePath = getDataFilePath();
			Document document = reader.read(dataFilePath);
			load(document);
		} catch (DocumentException e) {
			String message = "=== Problem with XmlEntities.load() === "
					+ e.getMessage();
			message = message
					+ " --- Check if a path to the data file has space(s). --- ";
			message = message
					+ " --- Check if each entry point in the model has its own xml data file. --- ";
			throw new PersistencyRuntimeException(message, e);
		}
	}

	/**
	 * Loads entry entities from the XML document.
	 * 
	 * @param document
	 *            XML document
	 * @throws persistency
	 *             exception if there is a problem
	 */
	private void load(Document document) {
		Element root = document.getRootElement();
		load(root);
	}

	/**
	 * Loads an element.
	 * 
	 * @param element
	 *            element
	 */
	protected void load(Element element) {
		for (Iterator<?> i = element.elementIterator(); i.hasNext();) {
			Element childElement = (Element) i.next();
			DomainModel model = (DomainModel) persistentModel.getModel();
			IEntity<?> entity = model.getModelMeta().createEntity(entities);
			XmlEntity xmlEntity = new XmlEntity(entity, persistentModel);
			xmlEntity.load(childElement);
			entities.add((T) xmlEntity.getEntity());
		}
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
	 * Saves data to the XML data file.
	 */
	public void save() {
		Document document = DocumentHelper.createDocument();
		fill(document);
		save(document, getDataFilePath(), "UTF-8");
	}

	/**
	 * Saves a document to the XML data file using the given encoding.
	 * 
	 * @param document
	 *            XML document
	 * @param dataFilePath
	 *            XML data file path
	 * @param encoding
	 *            text encoding
	 */
	private void save(Document document, String dataFilePath, String encoding) {
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(dataFilePath),
					new OutputFormat("  ", true, encoding));
		} catch (FileNotFoundException e) {
			String message = "=== Problem with XmlEntities.save(Document, String, String) === "
					+ e.getMessage();
			throw new PersistencyRuntimeException(message, e);
		} catch (UnsupportedEncodingException e) {
			String message = "=== Problem with XmlEntities.save(Document, String, String) === "
					+ e.getMessage();
			throw new PersistencyRuntimeException(message, e);
		}
		try {
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			String message = "=== Problem with XmlEntities.save(Document, String, String) === "
					+ e.getMessage();
			throw new PersistencyRuntimeException(message, e);
		}
	}

	/**
	 * Fills an XML document.
	 * 
	 * @param document
	 *            XML document
	 */
	private void fill(Document document) {
		String conceptCode = entities.getConceptConfig().getCode();
		String conceptsCode = entities.getConceptConfig().getEntitiesCode();
		TextHandler textHandler = new TextHandler();
		String smallLetterConceptCode = textHandler
				.firstLetterToLower(conceptCode);
		String smallLetterConceptsCode = textHandler
				.firstLetterToLower(conceptsCode);
		Element root = document.addElement(smallLetterConceptsCode);
		fill(root, smallLetterConceptCode);
	}

	/**
	 * Fills an element.
	 * 
	 * @param element
	 *            element
	 * @param childElementCode
	 *            child element code
	 */
	protected void fill(Element element, String childElementCode) {
		synchronized (entities) {
			for (IEntity<T> entity : entities) {
				XmlEntity xmlEntity = new XmlEntity(entity, persistentModel);
				Element childElement = element.addElement(childElementCode);
				xmlEntity.fill(childElement);
			}
		}
	}

	/**
	 * Fills a default element. Needed for Java reflection setMethod.
	 * 
	 * @param defaultElement
	 *            default element
	 * @param childElementCode
	 *            child element code
	 */
	public void fill(org.dom4j.tree.DefaultElement defaultElement,
			String childElementCode) {
		Element element = defaultElement;
		fill(element, childElementCode);
	}

}