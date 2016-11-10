package edu.neumont.pro150.hibernate5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface iQueryUtil 
{
	/**
	 * Runs the query provided in parameter query.
	 * @param query hibernate hql formatted string to query
	 * @param classz expected result class
	 * @return List of results from query.  May be null or empty on 0 results.
	 */
	public <T> List<T> customQueryResult(String query, Class<T> classz);
	
	
	/**
	 * Runs a predefined Named Query with the name provided.  Replaces any parameters name and value pair with the params map provided
	 * @param namedQuery The name of the named query
	 * @param params map of parameters to add to the query; leave this empty for no parameters
	 * @param classz expected class of the results
	 * @return List of results of the query. May be null or empty on 0 results.
	 */
	public <T> List<T> namedQueryResult(String namedQuery, 
			Map<String, Object> params, Class<T> classz);


	/**
	 * Runs a predefined Named Query with no parameters to replace.  This default method runs the base namedQueryResult with an empty params map.
	 * @param namedquery The name of the named query
	 * @param classz expected class of the results
	 * @return List of results of the query.  May be null or empty on 0 results.
	 */
	public default <T> List<T> namedQueryResult(String namedquery, Class<T> classz)
	{
		return namedQueryResult(namedquery, new HashMap<String,Object>(), classz);
	}
	
	/**
	 * Runs a predefined named Query with only 1 parameter to replace.  This default method runs the base namedQueryResult with a params map of size 1. 
	 * The key to the params map is the provided paramname, and the value is the provided object value
	 * @param namedquery The named of the named query
	 * @param paramname The name of the parameter in the query
	 * @param value The value of the named parameter
	 * @param classz expected class of the results
	 * @return List of results of the query.  May be null or empty on 0 results.
	 */
	public default <T> List<T> namedQueryResult(String namedquery, String paramname, 
			Object value, Class<T> classz)
	{
		HashMap<String,Object> params = new HashMap<String,Object>(1);
		params.put(paramname, value);
		
		return namedQueryResult(namedquery, params, classz);
	}
	
	/**
	 * Similar to namedQueryResult except expects a single result rather than a list.
	 * Throws JPA NonUniqueResultException if query isn't unique.
	 * @param namedQuery The name of the named queyr
	 * @param params Map of parameters and their values
	 * @param classz expected class of the result
	 * @return single result value of query
	 */
	public <T> T namedQuerySingleResult(String namedQuery,
			Map<String, Object> params, Class<T> classz);
	
	public default <T> T namedQuerySingleResult(String namedquery, Class<T> classz)
	{
		return namedQuerySingleResult(namedquery, new HashMap<String,Object>(), classz);
	}
	public default <T> T namedQuerySingleResult(String namedquery, String paramname, 
			Object value, Class<T> classz)
	{
		HashMap<String,Object> params = new HashMap<String,Object>(1);
		params.put(paramname, value);
		
		return namedQuerySingleResult(namedquery, params, classz);
	}

}
