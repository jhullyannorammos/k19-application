package br.com.application.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.application.domain.Lancamento;
import br.com.application.util.HibernateUtil;

public class LancamentoDAO extends GenericDAO<Lancamento> {
	
	private List<Lancamento> lancamentos;
	private Session session;
	
	@SuppressWarnings("finally")
	public List<Lancamento> findBy(Long codigo) throws HibernateException, Exception{
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			lancamentos = session.createQuery(" from Lancamento where usuario.id = " + codigo).getResultList();
		}catch(RuntimeException exception) {
			exception.printStackTrace();
			throw exception;
		}finally {
			session.close();
			return lancamentos;
		}
		
	}

}
