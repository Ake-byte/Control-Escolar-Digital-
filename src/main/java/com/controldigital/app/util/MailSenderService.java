package com.controldigital.app.util;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.controldigital.app.models.entity.Usuario;



@Component
public class MailSenderService {

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public String sendEmail(String to, String subject, String body, Usuario usuario) throws Exception {
		
		//String templateName = "mail/welcome";
		Context context = new Context();
		context.setVariable("Content", create(usuario));
		//String body = templateEngine.process(templateName, context);
		//String body = null;
		MimeMessage mail = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, StandardCharsets.UTF_8.name());
		
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		helper.setFrom("afawad3cm9@gmail.com");
		sender.send(mail);
		
		return "mail send successfully";
		
	}
	
	public MailBodyContent create(Usuario user) {
		MailBodyContent content = new MailBodyContent();
		content.setUsername("usuario");
		List<String> attributes = new ArrayList<>();
		attributes.add("Bienvenido al sistema BD-LNCAE: " + user.getNombre() + " " + user.getApellidoPaterno() + " " + user.getApellidoMaterno());
		attributes.add("Tu usuario es: " + user.getEmail());
		attributes.add("Tu contraseña: " + user.getPassword());
		//attributes.add("Tu rol actual es: " + user.getRoles_usuarios());

		content.setFeatures(attributes);
		content.setMessage("Este es un correo de bienvenida al sistema.");
		content.setFeatures(attributes);
		return content;
	}
}
