package com.serp.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;

import com.serp.modelo.Administrador;
import com.serp.util.NegocioException;
import com.serp.util.jpa.Transactional;

public class AdmDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public void save(Administrador adm) throws NegocioException {
		try {
			manager.merge(adm);
		} catch (ConstraintViolationException e) {
			throw new NegocioException("Violação de restrição, possível causa: email já existente.");
		}
	}

	@Transactional
	public void delete(Administrador adm) throws NegocioException {
		adm = findById(adm.getId());
		try {
			manager.remove(adm);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Não é possível excluir o administrador selecionado.");
		}
	}

	public Administrador getAdministrador(String email, String senha) {

		try {
			Administrador adm = (Administrador) manager.createNamedQuery("Administrador.getAdministrador", Administrador.class)
					.setParameter("email", email).setParameter("senha", senha).getSingleResult();

			return adm;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Administrador findById(Integer id) {
		return manager.find(Administrador.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Administrador> findAll() {
		return manager.createNamedQuery("Administrador.findAll").getResultList();
	}
	@Transactional
	public Administrador findByEmail(String email) {
		try {
			return manager.createNamedQuery("Administrador.findByEmail", Administrador.class)
					.setParameter("email", email).getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
		
	}

	public List<Administrador> findByName(String nome) {

		String jpql = "from Administrador a where a.nome LIKE :nome";
		TypedQuery<Administrador> query = manager.createQuery(jpql, Administrador.class);
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Administrador> buscarComPaginacao(int first, int pageSize) {
		return manager.createNamedQuery("Administrador.findAll").setFirstResult(first).setMaxResults(pageSize)
				.getResultList();
	}

	public Long encontrarQuantidadeDeAdministradores() {
		return manager.createQuery("select count(a) from Administrador a", Long.class).getSingleResult();
	}
}
