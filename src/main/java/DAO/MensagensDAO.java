package DAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import RNE.RNEMensagens;
import entidade.Mensagens;

@Stateless
public class MensagensDAO extends RNEMensagens {

	private static final long serialVersionUID = 7198247943623768165L;
	
	@PersistenceContext(unitName = "default") // <-- deve bater com o nome da unidade
	private EntityManager session;

	public EntityManager getSession() {
		this.session.joinTransaction();
		return this.session;
	}
	
	public Mensagens salvar(Mensagens t) {

		EntityManager session = getSession();

		try {

			t = super.salvar(session, t);

//			commitSalva(t, session);

		} catch (Exception e) {
			rollback(e);
		}

		return t;

	}

	public void delete(String tela, Mensagens t) {

		EntityManager session = getSession();

		try {


			super.delete(session, t);

		} catch (Exception e) {
			rollback(e);
		}

	}
	
	public Mensagens getEnt(Long id) {
		return super.getEnt(session, id);
	}
	
}
