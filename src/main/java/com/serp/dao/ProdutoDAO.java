package com.serp.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;

import com.serp.modelo.Produto;
import com.serp.util.NegocioException;
import com.serp.util.jpa.Transactional;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	@Transactional
	public void salvar(Produto produto) throws NegocioException {
		try {
			manager.merge(produto);

		} catch (ConstraintViolationException e) {
			throw new NegocioException("Violação de restrição, provavelmente produto já cadastrado.");
		}
	}
	
	@Transactional
	public void excluir(Produto produto) throws NegocioException {
		produto = buscarPeloCodigo(produto.getCodigo());
		try {
			manager.remove(produto);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Este produto não pode ser excluído.");
		}
	}
	
	
	
	/*
	 * Buscas
	 */	
	
	public Produto buscarPeloCodigo(Long codigo) {
		return manager.find(Produto.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> buscarTodos() {
		return manager.createNamedQuery("Produto.buscarTodos").getResultList();
	}	
		
	public Produto buscarPelaMarca(String marca) {
		return manager.createNamedQuery("Produto.buscarPelaMarca", Produto.class)
				.setParameter("marca", marca)
				.getSingleResult();
	}	
	
	public List<Produto> buscarPeloNome(String nome) {
		
		String jpql = "from Produto p where p.nome LIKE :nome";		
		TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);		
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");			
		return query.getResultList();		
	}	
		
	@SuppressWarnings("unchecked")
	public List<Produto> buscarComPaginacao(int first, int pageSize) {
		return manager.createNamedQuery("Produto.buscarTodos")
							.setFirstResult(first)
							.setMaxResults(pageSize)
							.getResultList();
	}

	public Long encontrarQuantidadeDeProdutos() {
		return manager.createQuery("select count(p) from Produto p", Long.class).getSingleResult();
	}
}