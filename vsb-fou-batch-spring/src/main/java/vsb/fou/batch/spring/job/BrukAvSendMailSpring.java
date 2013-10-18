package vsb.fou.batch.spring.job;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class BrukAvSendMailSpring {

    /**
     * auw-ws-backend
     * ask-ws-backend
     * contempus-ws-backend
     * sb1-mail-backend
     * sb1-sftp-backend
     * nav-http-backend
     * posten-http-backend
     * esb-ws-backend
     * fip-jms-backend
     * f2100-ws-backend
     * paris-ws-backend
     * prolife-jcapi-backend
     * straalfors-sftp-backend
     * roff-ws-backend
     * solr-rest-backend
     */
    public static void main(String[] args) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("mail.gmail.com");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vegbye@gmail.com");
        message.setTo("vegbye@gmail.com");
        message.setSubject("Hie hei");
        message.setText("jepp");
        mailSender.send(message);
    }
}
