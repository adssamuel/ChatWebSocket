package RNE;

import javax.persistence.EntityManager;

import base.GenericRNE;
import entidade.Mensagens;

public class RNEMensagens extends GenericRNE {

	private static final long serialVersionUID = 4896713526239575606L;

	public Mensagens salvar(EntityManager session, Mensagens l) {
		return super.saveOrUpdatePojo(session, l);
	}

	public void delete(EntityManager session, Mensagens l) {
		super.deletePojo(l, session);
	}

	public Mensagens getEnt(EntityManager session, Long id) {
		String sql = " select e from mensagens e where e.id=?1";
		return getPojoUnique(session, Mensagens.class, sql, id);
	}
}
