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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Saves images as JPEG (.jpg or .jpeg) files.
 * 
 * @author Dzenan Ridjanovic
 * @version 2005-05-27
 */
public class ImageHandler {

	private static Log log = LogFactory.getLog(ImageHandler.class);

	/**
	 * Saves an image to a jpeg file.
	 * 
	 * @param bi
	 *            buffered image
	 * @param file
	 *            jpeg file
	 * @param quality
	 *            image quality
	 */
	public static void saveImageToJPEGFile(BufferedImage bi, File file,
			float quality) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(bi);
		param.setQuality(quality, false);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos, param);
		encoder.encode(bi);
		fos.close();
	}

	/**
	 * Saves an image to a jpeg file.
	 * 
	 * @param bi
	 *            buffered image
	 * @param file
	 *            jpeg file
	 * @param quality
	 *            image quality
	 */
	public static void saveImageToJPEGFile14(BufferedImage bi, File file,
			float quality) throws IOException {

		JPEGImageWriteParam param = new JPEGImageWriteParam(null);
		ImageTypeSpecifier type = new ImageTypeSpecifier(bi);
		param.setDestinationType(type);
		ImageOutputStream imgos = ImageIO.createImageOutputStream(file);
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
		ImageWriter writer = writers.next();
		writer.setOutput(imgos);
		writer.write(bi);
		imgos.close();
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		log.info("");
	}

}
