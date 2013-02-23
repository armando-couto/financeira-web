package financeiro.web.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import financeiro.util.HibernateUtil;

public class HibernateRenderResponsePhaseListener implements PhaseListener {
	
   public HibernateRenderResponsePhaseListener() {
	   System.out.println("### HibernateRenderResponsePhaseListener");
   }

	public void beforePhase(PhaseEvent event) {
	}

	public void afterPhase(PhaseEvent event) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		try {
			System.out.println("open: " + session.isOpen());
			session.getTransaction().commit();
			if (session.isOpen()) {
				System.out.println("open: " + session.isOpen());
				session.close();
			}
			System.out.println("open: " + session.isOpen());
		} catch (Throwable ex) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
		}
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}