package com.topfield.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wireless database table.
 * 
 */
@Entity
@Table(name="wireless")
@NamedQuery(name="Wireless.findAll", query="SELECT w FROM Wireless w")
public class Wireless implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String password;

	private String url;

	public Wireless() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}