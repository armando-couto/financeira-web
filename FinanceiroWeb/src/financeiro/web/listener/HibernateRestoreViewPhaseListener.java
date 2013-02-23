package financeiro.web.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.hibernate.SessionFactory;

import financeiro.util.HibernateUtil;

public class HibernateRestoreViewPhaseListener implements PhaseListener {

	public HibernateRestoreViewPhaseListener() {
		System.out.println("### HibernateRestoreViewPhaseListener");
	}

	public void beforePhase(PhaseEvent event) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		sessionFactory.getCurrentSession().beginTransaction();
	}

	public void afterPhase(PhaseEvent event) {
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}