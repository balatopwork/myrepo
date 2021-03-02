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
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;

	@Column(name="age")
    private Integer age;

	@Column(name="favourite_colour")
    private String favouriteColour;
	
	public Person() {
		super();
	}

	public Person(String firstName, String lastName, Integer age, String favouriteColour) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.favouriteColour = favouriteColour;
	}
	
	public Person(Long id, String firstName, String lastName, Integer age, String favouriteColour) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.favouriteColour = favouriteColour;
	}
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
    public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	

	public String getFavouriteColour() {
		return favouriteColour;
	}

	public void setFavouriteColour(String favouriteColour) {
		this.favouriteColour = favouriteColour;
	}

	@Override
    public String toString() {
        return "EmployeeEntity [id=" + id + ", firstName=" + firstName + 
                ", lastName=" + lastName + ", email=" + favouriteColour   + "]";
    }  
}