package base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public abstract class GenericRNE implements Serializable {

	private static final long serialVersionUID = -1032612798305865437L;

	// -------------------------------------------------------------------------------------------------------
	/**
	 *
	 * @param pojo
	 * @param session
	 */
	protected void validSave(Serializable pojo, EntityManager session) {

	}

	/**
	 *
	 * @param pojo
	 * @param session
	 */
	protected void validInsere(Serializable pojo, EntityManager session) {

	}

	/**
	 *
	 * @param pojo
	 * @param session
	 */
	protected void validAltera(Serializable pojo, EntityManager session) {

	}

	/**
	 *
	 * @param pojo
	 * @param session
	 */
	protected void validDelete(Serializable pojo, EntityManager session) {

	}

	// -------------------------------------------------------------------------------------------------------------
	/**
	 *
	 * @param e Exception
	 */
	public void rollback(Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e.getLocalizedMessage());
	}

	/**
	 * @param <T>
	 * @param session
	 * @param pojo
	 * @return Entidade.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> T saveOrUpdatePojo(EntityManager session, Serializable pojo) {
		pojo = session.merge(pojo);
		return (T) pojo;
	}

	/**
	 *
	 * @param pojo
	 * @param session
	 */
	public void deletePojo(Serializable pojo, EntityManager session) {
		validDelete(pojo, session);
		session.remove(session.contains(pojo) ? pojo : session.merge(pojo));
	}

	/**
	 * Pesquisar resultado unico
	 *
	 * @param <T>
	 * @param session
	 * @param classToSearch
	 * @param query
	 * @param parametros
	 * @return Retorna o primeiro resultado da pesquisa se encontrado se não retorna
	 *         null.
	 *
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> T getPojoUnique(EntityManager session, Class<T> classToSearch, String query, Object... parametros) {

		Query qr = session.createQuery(query);

		for (int i = 0; i < parametros.length; i++) {
			qr.setParameter(i + 1, parametros[i]);
		}

		List<T> results = qr.getResultList();
		if (results != null && !results.isEmpty()) {
			return (T) results.get(0);
		} else {
			return null;
		}

	}

}
