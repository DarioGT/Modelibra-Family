package org.ieducnews.server;

import java.net.URL;

import org.mortbay.jetty.Server;

public class Start {

	public static void main(String[] args) {
		try {
			URL jettyConfig = Start.class.getResource("jetty-config.xml");
			if (jettyConfig != null) {
				Server jettyServer = new Server(jettyConfig);
				jettyServer.start();
			}
		} catch (Exception e) {
			System.out.println("Could not start the Jetty server: " + e);
		}
	}

}
