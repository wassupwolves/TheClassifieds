package edu.neumont.pro150.hibernate5.emqueryutil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.neumont.pro150.hibernate5.QueryUtilConnectionException;
import edu.neumont.pro150.hibernate5.iQueryUtil;



public class EMQueryUtil implements iQueryUtil
{
	/*
	    @PersistenceContext(unitName="sample-db1", 
          type=PersistenceContextType.TRANSACTION)
   				private EntityManager em1;
	 */
	
	private static Map<String, EntityManagerFactory> emfs =
			new HashMap<String, EntityManagerFactory>();
	
	public static void RegisterEMF(String key, String unitName)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(unitName);
		emfs.put(key, emf);
	}
	


	private EntityManagerFactory emf;
	public EMQueryUtil(String key) throws QueryUtilConnectionException
	{
		emf = emfs.get(key);
		if(emf == null)
			throw new QueryUtilConnectionException("Entity Manager Factory is null, perhaps you forgot to register it?");
		else if(!emf.isOpen())
			reopenEMF(key);
	}
	
	private void reopenEMF(String key) throws QueryUtilConnectionException 
	{
		EntityManagerFactory emf = emfs.get(key);
		if(emf == null || !emf.isOpen())
			throw new QueryUtilConnectionException("Entity Manager Factory is not open for key " + key);
		
	}

	@Override
	public <T> List<T> customQueryResult(String query, Class<T> classz) {
		EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
    	List<T> list = em.createQuery(query,classz).getResultList();
    	em.getTransaction().commit();
    	em.close();
    	
    	return list;
	}

	@Override
	public <T> List<T> namedQueryResult(String namedquery, 
			Map<String, Object> params, Class<T> classz) 
	{
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<T> q = em.createNamedQuery(namedquery, classz);
		for(String param : params.keySet())
			q.setParameter(param, params.get(param));
		
		List<T> rtn = q.getResultList();
		em.getTransaction().commit();
		em.close();
		return rtn;
		
	}

	@Override
	public <T> T namedQuerySingleResult(String namedQuery, 
			Map<String, Object> params, Class<T> classz) 
	{
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<T> q = em.createNamedQuery(namedQuery, classz);
		for(String param : params.keySet())
			q.setParameter(param, params.get(param));
		T rtn = null;
		try{
			rtn = q.getSingleResult();
		}
		catch(Exception e){}
		
		em.getTransaction().commit();
		em.close();
		return rtn;
	}

	public void insertORupdate(Object entry) 
	{
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(entry);
		em.getTransaction().commit();
		em.close();
	}




}
