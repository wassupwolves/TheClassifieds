package edu.neumont.pro150;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class HibernateExample {
	
	@PersistenceUnit
	protected void setUp() throws Exception {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("edu.neumont.pro150" );
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
	}

}
