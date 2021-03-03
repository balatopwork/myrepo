package com.assess.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String first_name;
    
    @Column(name="last_name")
    private String last_name;

	@Column(name="age")
    private Integer age;

	@Column(name="favourite_colour")
    private String favourite_colour;
	
	public Person() {
		super();
	}


    public Person(String first_name, String last_name, Integer age, String favourite_colour) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.favourite_colour = favourite_colour;
	}


	public Person(Long id, String first_name, String last_name, Integer age, String favourite_colour) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.favourite_colour = favourite_colour;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirst_name() {
		return first_name;
	}



	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}



	public String getLast_name() {
		return last_name;
	}



	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public String getFavourite_colour() {
		return favourite_colour;
	}



	public void setFavourite_colour(String favourite_colour) {
		this.favourite_colour = favourite_colour;
	}



	@Override
	public String toString() {
		return "Person [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", age=" + age
				+ ", favourite_colour=" + favourite_colour + "]";
	}

  
}