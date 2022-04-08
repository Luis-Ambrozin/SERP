package com.serp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GerarTabelas {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("serp_PU");
	private static EntityManager manager;

	public static EntityManager createEntityManager() {
		return emf.createEntityManager();
	}

	public static void main(String[] args) {

		manager = GerarTabelas.createEntityManager();
		
		System.out.println(">>>>> Tabelas  CRIADAS com sucesso! <<<<<<<");		
		
		manager.close();
		System.exit(0);

	}	
	
}