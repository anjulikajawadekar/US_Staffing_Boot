package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.staffing.demo.entity.Requisition;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer client_id;
	private String client_name;

	@ManyToOne
	@JoinColumn(name = "requisitor_id")
	private Requisitor_fd requisitor_fd;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Integer client_id, String client_name, Requisitor_fd requisitor_fd) {
		super();
		this.client_id = client_id;
		this.client_name = client_name;
		this.requisitor_fd = requisitor_fd;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public Requisitor_fd getRequisitor_fd() {
		return requisitor_fd;
	}

	public void setRequisitor_fd(Requisitor_fd requisitor_fd) {
		this.requisitor_fd = requisitor_fd;
	}

}
