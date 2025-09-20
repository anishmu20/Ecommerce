package com.backsmiths.notification.email;

import com.backsmiths.notification.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.Characters;
import org.bouncycastle.util.encoders.UTF8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private  JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference

    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("anishmu1220@gmail.com");
        messageHelper.setTo(destinationEmail);
        final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);


        Context context = new Context();
        context.setVariables(variables);

        messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());
        try {
            String html = templateEngine.process(templateName, context);
            messageHelper.setText(html, true);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO- EMAIL SENT SUCCESSFULLY FOR %s with template %s", destinationEmail, templateName));
        } catch (MessagingException e) {
            log.warn("Cannot send email to " + destinationEmail);

        }
    }

        @Async
        public void sendOrderSuccessEmail(
                String destinationEmail,
                String customerName,
                BigDecimal amount,
                String orderReference,
                List<Product> products

    ) throws MessagingException {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
            messageHelper.setFrom("anishmu1220@gmail.com");
            messageHelper.setTo(destinationEmail);
            final  String  templateName= EmailTemplates.ORDER_CONFIRMATION.getTemplate();

            Map<String,Object> variables=new HashMap<String,Object>();
            variables.put("customerName", customerName);
            variables.put("totalAmount", amount);
            variables.put("orderReference", orderReference);
            variables.put("products", products);

            Context context = new Context();
            context.setVariables(variables);

            messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());
            try{
                String html = templateEngine.process(templateName, context);
                messageHelper.setText(html, true);
                mailSender.send(mimeMessage);
                log.info(String.format("INFO- EMAIL SENT SUCCESSFULLY FOR %s with template %s", destinationEmail,templateName));
            }catch (MessagingException e){
                log.warn("Cannot send email to " + destinationEmail);

            }


    }


}
