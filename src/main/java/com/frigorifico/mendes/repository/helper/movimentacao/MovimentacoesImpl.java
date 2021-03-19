package com.frigorifico.mendes.repository.helper.movimentacao;

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
import com.frigorifico.mendes.model.Movimentacao;
import com.frigorifico.mendes.model.StatusVeiculo;

public class MovimentacoesImpl implements MovimentacoesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Transactional(readOnly = true)
	@Override
	public Movimentacao buscarVeiculos(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Movimentacao.class);
		criteria.createAlias("itens", "i", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Movimentacao) criteria.uniqueResult();
	}

	@Override
	public Long veiculosTotal() {
		Optional<Long> optional = Optional.ofNullable(
				manager.createQuery("select count(*) from Movimentacao", Long.class)
				.getSingleResult());
				
		return optional.get();
	}

	@Override
	public Long totalVeiculosNoano() {
		Optional<Long> optional = Optional.ofNullable(manager.createQuery(
				"select count(*) from Movimentacao where year(dataChegada) = :ano and status = :status",
				Long.class).setParameter("ano", Year.now().getValue()).setParameter("status", StatusVeiculo.FINALIZADO)
				.getSingleResult());
		return optional.get();
	}

	@Override
	public Long totalVeiculosNoMes() {
		Optional<Long> optional = Optional.ofNullable(manager.createQuery(
				"select count(*) from Movimentacao where month(dataChegada) = :mes and status = :status",
				Long.class).setParameter("mes", MonthDay.now().getMonthValue()).setParameter("status", StatusVeiculo.FINALIZADO)
				.getSingleResult());
		return optional.get();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovimentacaoMes> totalPorMes() {
		return manager.createNamedQuery("Movimentacao.totalPorMes").getResultList();
			
	}

}
