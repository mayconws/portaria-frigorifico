package com.frigorifico.mendes.repository.helper.controle;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.dto.MovimentacaoMes;
import com.frigorifico.mendes.model.Controle;
import com.frigorifico.mendes.model.Movimentacao;
import com.frigorifico.mendes.model.StatusVisitante;

public class ControlesImpl implements ControlesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Transactional(readOnly = true)
	@Override
	public Movimentacao buscarVisitantes(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Controle.class);
		criteria.createAlias("itens", "i", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Movimentacao) criteria.uniqueResult();
	}

	@Override
	public Long visitantesTotal() {
		Optional<Long> optional = Optional.ofNullable(
				manager.createQuery("select count(*) from Controle", Long.class)
				.getSingleResult());
				
		return optional.get();
	}

	@Override
	public Long totalVisitantesNoano() {
		Optional<Long> optional = Optional.ofNullable(manager.createQuery(
				"select count(*) from Controle where year(dataEntrada) = :ano and status = :status",
				Long.class).setParameter("ano", Year.now().getValue()).setParameter("status", StatusVisitante.FINALIZADO)
				.getSingleResult());
		return optional.get();
	}

	@Override
	public Long totalVisitantesNoMes() {
		Optional<Long> optional = Optional.ofNullable(manager.createQuery(
				"select count(*) from Controle where month(dataEntrada) = :mes and status = :status",
				Long.class).setParameter("mes", MonthDay.now().getMonthValue()).setParameter("status", StatusVisitante.FINALIZADO)
				.getSingleResult());
		return optional.get();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovimentacaoMes> totalPorMes() {
		List<MovimentacaoMes> movimentacaoMes = manager.createNamedQuery("Controle.totalPorMes").getResultList();
		
		LocalDate hoje = LocalDate.now();
		for (int i = 1; i <= 6; i++) {
			String mesIdeal = String.format("%d/%02d", hoje.getYear(), hoje.getMonthValue());
			
			boolean possuiMes = movimentacaoMes.stream().filter(v -> v.getMes().equals(mesIdeal)).findAny().isPresent();
			if (!possuiMes) {
				movimentacaoMes.add(i - 1, new MovimentacaoMes(mesIdeal, 0));
			}
			
			hoje = hoje.minusMonths(1);
		}
		
		return movimentacaoMes;
			
	}

}
