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
package org.modelibra.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Locates a file/directory path.
 * 
 * @author Dzenan Ridjanovic
 * @author Jean-Francois Taillon
 * @version 2008-09-19
 */
public class PathLocator {

	private static Log log = LogFactory.getLog(PathLocator.class);

	/**
	 * Finds the absolute path for a given end path relative to a given class
	 * location.
	 * 
	 * @param claz
	 *            class that is an anchor point
	 * @param anchorRelativePath
	 *            anchor relative path (e.g., the classes directory)
	 * @param endPath
	 *            end path (for what we want to find the absolute path)
	 */
	public String findAbsolutePath(Class<?> claz, String anchorRelativePath,
			String endPath) {
		String path = null;

		String classDirPath = getClassBasedPath(claz, "");

		TextHandler textExtractor = new TextHandler();
		String classPackageNameWithSlashes = textExtractor
				.extractClassPackageNameWithSlashes(claz.getName());

		String classPackagePath = textExtractor.dropEnd(classDirPath,
				classPackageNameWithSlashes);
		String classParentDirPath = textExtractor.dropEnd(classPackagePath,
				anchorRelativePath);

		path = classParentDirPath + endPath;
		return path;
	}

	/**
	 * Gets the file URL given a local path anchored to the class base.
	 * 
	 * @param claz
	 *            class that is an anchor point
	 * @param localPath
	 *            local path
	 * @return file URL
	 */
	public URL getClassBasedUrl(Class<?> claz, String localPath) {		
		return claz.getResource(localPath);
	}

	/**
	 * Gets the file path given a local path anchored to the class base.
	 * 
	 * @param claz
	 *            class that is an anchor point
	 * @param localPath
	 *            local path
	 * @return file path
	 */
	public String getClassBasedPath(Class<?> claz, String localPath) {
		URL fileUrl = getClassBasedUrl(claz, localPath);
		return getPath(fileUrl);
	}

	/**
	 * Gets the file path given a url.
	 * 
	 * @param url
	 *            url
	 * @return file path
	 */
	public String getPath(URL url) {
		String filePath = null;
		try {
			if (url != null) {
				filePath = URLDecoder.decode(url.getFile(), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage() + " // problem with UTF-8");
		}
		return filePath;
	}

	/**
	 * Gets the image given an image local path.
	 * 
	 * @param claz
	 *            class that is an anchor point
	 * @param localPath
	 *            image local path
	 * @return image icon
	 */
	public ImageIcon getImageIcon(Class<?> claz, String localPath) {
		ImageIcon imageIcon = null;
		URL imageUrl = getClassBasedUrl(claz, localPath);
		if (imageUrl != null) {
			imageIcon = new ImageIcon(imageUrl);
		}
		return imageIcon;
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		PathLocator resourceLocator = new PathLocator();
		String path = resourceLocator.findAbsolutePath(PathLocator.class,
				"classes", "db");
		log.info("Db path: " + path);
	}

}
