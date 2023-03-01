package com.staffing.demo.entity_fd;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.staffing.demo.entity.Candidate;

@Entity
public class Requisitor_fd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer requisitor_id;
	private String requisitor_fd;

	@OneToMany(mappedBy = "requisitor_fd")
	@JsonIgnore
	private List<Client> client;

	public Requisitor_fd() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Requisitor_fd(Integer requisitor_id, String requisitor_fd, List<Client> client) {
		super();
		this.requisitor_id = requisitor_id;
		this.requisitor_fd = requisitor_fd;
		this.client = client;
	}

	public Integer getRequisitor_id() {
		return requisitor_id;
	}

	public void setRequisitor_id(Integer requisitor_id) {
		this.requisitor_id = requisitor_id;
	}

	public String getRequisitor_fd() {
		return requisitor_fd;
	}

	public void setRequisitor_fd(String requisitor_fd) {
		this.requisitor_fd = requisitor_fd;
	}

	public List<Client> getClient() {
		return client;
	}

	public void setClient(List<Client> client) {
		this.client = client;
	}

}
