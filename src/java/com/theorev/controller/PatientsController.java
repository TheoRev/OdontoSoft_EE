package com.theorev.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import com.theorev.dao.PatientsDao;
import com.theorev.model.Patients;
import com.theorev.service.IOdontoSoft;
import com.theorev.util.MessagesUtil;

@ManagedBean
@ViewScoped
public class PatientsController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Patients> pacientes;
	private Patients paciente;

	private String accion;

	@PostConstruct
	public void init() {
		paciente = new Patients();
		findAll();
	}

	public void findAll() {
		FacesMessage message = null;

		IOdontoSoft<Patients> dao = new PatientsDao();

		try {
			pacientes = dao.findAll("Patients", "", "");
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, MessagesUtil.ERROR_DB_TITLE, e.getMessage());
		}

		if (message != null) {
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public List<Patients> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Patients> pacientes) {
		this.pacientes = pacientes;
	}

	public Patients getPaciente() {
		return paciente;
	}

	public void setPaciente(Patients paciente) {
		this.paciente = paciente;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

}
