package com.republica.start.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "simetric_private_key")
public class Simetric_private_key_entity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	public Simetric_private_key_entity(long id, String value, int user_id) {
		super();
		this.id = id;
		this.value = value;
		this.user_id = user_id;
	}
	
	Simetric_private_key_entity(){}
	private String value;
	private int user_id;
	
	public Simetric_private_key_entity(String value, int id) {
		this.value = value;
		this.user_id = id;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
