package com.frigorifico.mendes.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.frigorifico.mendes.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.frigorifico.mendes.thymeleaf.processor.MenuAttributeTagProcessor;
import com.frigorifico.mendes.thymeleaf.processor.MessageElementTagProcessor;
import com.frigorifico.mendes.thymeleaf.processor.OrderElementTagProcessor;
import com.frigorifico.mendes.thymeleaf.processor.PaginationElementTagProcessor;

@Component
public class MendesDialect extends AbstractProcessorDialect {
	
	public MendesDialect() {
		super("Frigorifico Mendes", "mendes", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		return processadores;
	}

}
