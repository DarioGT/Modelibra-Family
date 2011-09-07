package org.modelibra.modeler.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-10
 */
public class ImageHandling {

	public static BufferedImage toBufferedImage(Image image) {
		// This code ensures that all the pixels in the image are loaded.
		image = new ImageIcon(image).getImage();
		// Create the buffered image.
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		// Copy image to buffered image.
		Graphics g = bufferedImage.createGraphics();
		// Clear background and paint the image.
		g.setColor(Color.white);
		g.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return bufferedImage;
	}

}
