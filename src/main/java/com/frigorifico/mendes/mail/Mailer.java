package com.frigorifico.mendes.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.frigorifico.mendes.model.ItemVeiculo;
import com.frigorifico.mendes.model.Movimentacao;
import com.frigorifico.mendes.model.Veiculo;
import com.frigorifico.mendes.storage.FotoStorage;

@Component
public class Mailer {
	
	private static Logger logger = LoggerFactory.getLogger(Mailer.class);

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Async
	public void enviar(Movimentacao movimentacao) {
		
		Context context = new Context();
		context.setVariable("movimentacao", movimentacao);
		context.setVariable("logo", "logo");
		
		Map<String, String> fotos = new HashMap<>();
		boolean adicionarMockVeiculo = false;
		for (ItemVeiculo item : movimentacao.getItens()) {
			Veiculo veiculo = item.getVeiculo();
			if (veiculo.temFoto()) {
				String cid = "foto-" + veiculo.getCodigo();
				context.setVariable(cid, cid);
				
				fotos.put(cid, veiculo.getFoto() + "|" + veiculo.getContentType());
			} else {
				adicionarMockVeiculo = true;
				context.setVariable("mockVeiculo", "mockVeiculo");
			}
		}
		
		try {
			String email = thymeleaf.process("mail/ResumoVeiculo", context);
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("mayconwas2018@gmail.com");
			helper.setTo(movimentacao.getMotorista().getTransportadora().getEmail());
			helper.setSubject("Mendes - Veiculo adicionado!");
			helper.setText(email, true);
			
			helper.addInline("logo", new ClassPathResource("static/images/logo.png"));
			
			if (adicionarMockVeiculo) {
				helper.addInline("mockVeiculo", new ClassPathResource("static/images/veiculo-mock.png"));
			}
			
			for (String cid : fotos.keySet()) {
				String[] fotoContentType = fotos.get(cid).split("\\|");
				String foto = fotoContentType[0];
				String contentType = fotoContentType[1];
				byte[] arrayFoto = fotoStorage.recuperarThumbnail(foto);
				helper.addInline(cid, new ByteArrayResource(arrayFoto), contentType);
			}
		
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Erro enviando e-mail", e);
		}
	}
	
}

