package sendMails;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
public class ClassMail {
    public static void sendMail(String recipient, String Subject, String Text) throws Exception {
        System.out.println("Prepare l'envoie de l'E-mail");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccountEmail = "asgeplex@gmail.com";
        String password = "i7HeoKaXgGtypa5DPFU73kCS4XAtX4KjIr254KPufT49xejmMhM67fASaTlX";
        Session session = Session.getInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
    }
 });
		Message message = prepareMessage(session, myAccountEmail, recipient, Subject, Text);
        Transport.send(message);
        System.out.println("E-mail envoyé !");
}
private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String Subject, String Text) {
  try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(Subject);
            message.setText(Text);
            return message;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
       return null;
    }
}