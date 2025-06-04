package com.example.carwash.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "washers")
public class Washer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;
    private int age;
    
 

    

	// Constructors
    public Washer() {}





	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public String getPhoneNumber() {
		return phoneNumber;
	}





	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public int getAge() {
		return age;
	}





	public void setAge(int age) {
		this.age = age;
	}





	public Washer(Long id, String username, String password, String phoneNumber, String email, String address,
			int age) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.age = age;
	}

    
}
