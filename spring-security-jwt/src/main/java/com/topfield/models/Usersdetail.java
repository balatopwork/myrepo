package com.topfield.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usersdetails database table.
 * 
 */
@Entity
@Table(name="usersdetails")
@NamedQuery(name="Usersdetail.findAll", query="SELECT u FROM Usersdetail u")
public class Usersdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String company;

	private String email;

	private String firstname;

	private String jobtitle;

	private String lastname;

	private String password;

	private int purchaseUserLimit;

	//bi-directional many-to-one association to Usersdetail
	@ManyToOne
	@JoinColumn(name="corporateAdmin")
	private Usersdetail usersdetail;

	//bi-directional many-to-one association to Usersdetail
	@OneToMany(mappedBy="usersdetail")
	private List<Usersdetail> usersdetails;

	public Usersdetail() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getJobtitle() {
		return this.jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPurchaseUserLimit() {
		return this.purchaseUserLimit;
	}

	public void setPurchaseUserLimit(int purchaseUserLimit) {
		this.purchaseUserLimit = purchaseUserLimit;
	}

	public Usersdetail getUsersdetail() {
		return this.usersdetail;
	}

	public void setUsersdetail(Usersdetail usersdetail) {
		this.usersdetail = usersdetail;
	}

	public List<Usersdetail> getUsersdetails() {
		return this.usersdetails;
	}

	public void setUsersdetails(List<Usersdetail> usersdetails) {
		this.usersdetails = usersdetails;
	}

	public Usersdetail addUsersdetail(Usersdetail usersdetail) {
		getUsersdetails().add(usersdetail);
		usersdetail.setUsersdetail(this);

		return usersdetail;
	}

	public Usersdetail removeUsersdetail(Usersdetail usersdetail) {
		getUsersdetails().remove(usersdetail);
		usersdetail.setUsersdetail(null);

		return usersdetail;
	}

}