package com.frigorifico.mendes.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.frigorifico.mendes.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.frigorifico.mendes.thymeleaf.processor.MessageElementTagProcessor;

public class MendesDialect extends AbstractProcessorDialect {
	
	public MendesDialect() {
		super("Frigorifico Mendes", "mendes", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		return processadores;
	}

}
