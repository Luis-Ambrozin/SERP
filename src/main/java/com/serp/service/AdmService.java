package com.serp.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.serp.dao.AdmDAO;
import com.serp.modelo.Administrador;
import com.serp.util.NegocioException;

public class AdmService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AdmDAO admDAO;

	public void save(Administrador adm) throws Exception {
		if (admDAO.findByEmail(adm.getEmail()) != null) {
			throw new NegocioException("Email já cadastrado!");
		}
		this.admDAO.save(adm);
	}

	public List<Administrador> findAll() {
		return admDAO.findAll();
	}

	public void delete(Administrador adm) throws NegocioException {
		admDAO.delete(adm);
	}

	public Administrador findByEmail(String email) {
		return admDAO.findByEmail(email);

	}

	public List<Administrador> findByName(String nome) {
		return admDAO.findByName(nome);
	}

	public List<Administrador> buscarAdministradores() {
		return admDAO.findAll();
	}
}
