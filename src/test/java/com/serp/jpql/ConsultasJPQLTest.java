package com.serp.jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class ConsultasJPQLTest {
	
	private static EntityManagerFactory factory;	
	private EntityManager manager;

	
	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("serp_PU");
	}
	
	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();
	}
	
	@After
	public void tearDown() {
		this.manager.close();
	}
	
		
	@Test
	public void consultaJPQL() {
		
		
		Long qde = manager.createQuery("SELECT codigo, count(codigo), FROM Federacao "
				+ "WHERE codigo = :id", Long.class)
			.setParameter("id", 1)				
			.getSingleResult();

		
		System.out.println("Qde =  " + qde.intValue());
		
	}
	
	
}
