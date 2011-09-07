package chapter02;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;

public class HelloSun extends WebPage {

	public HelloSun() {
		ExternalLink link = new ExternalLink("link", "http://java.sun.com");
		add(link);
		link.add(new Label("label", "goto the java web site"));
	}
}