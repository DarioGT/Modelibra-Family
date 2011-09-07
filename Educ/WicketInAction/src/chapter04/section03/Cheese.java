package chapter04.section03;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA. User: dashorst Date: Sep 16, 2007 Time: 10:51:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cheese implements Serializable {
	private Long id;
	private String name;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
