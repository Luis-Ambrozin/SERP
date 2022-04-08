package com.serp.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;

import com.serp.modelo.Cliente;
import com.serp.util.NegocioException;
import com.serp.util.jpa.Transactional;

public class ClienteDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public void salvar(Cliente cliente) throws NegocioException {
		try {
			manager.merge(cliente);

		} catch (ConstraintViolationException e) {
			throw new NegocioException("Violação de restrição, provavelmente e-mail já existe.");
		}
	}
	

	@Transactional
	public void excluir(Cliente cliente) throws NegocioException {
		cliente = buscarPeloCodigo(cliente.getCodigo());
		try {
			manager.remove(cliente);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Este cliente não pode ser excluído.");
		}
	}

	/*
	 * Buscas
	 */

	public Cliente buscarPeloCodigo(Long codigo) {
		return manager.find(Cliente.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscarTodos() {
		return manager.createNamedQuery("Cliente.buscarTodos").getResultList();
	}

	public Cliente buscarPeloEmail(String email) {
		return manager.createNamedQuery("Cliente.buscarPorEmail", Cliente.class).setParameter("email", email)
				.getSingleResult();
	}

	public List<Cliente> buscarPeloNome(String nome) {

		String jpql = "from Cliente c where c.nome LIKE :nome";
		TypedQuery<Cliente> query = manager.createQuery(jpql, Cliente.class);
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscarComPaginacao(int first, int pageSize) {
		return manager.createNamedQuery("Cliente.buscarTodos").setFirstResult(first).setMaxResults(pageSize)
				.getResultList();
	}

	public Long encontrarQuantidadeDeClientes() {
		return manager.createQuery("select count(c) from Cliente c", Long.class).getSingleResult();
	}
}