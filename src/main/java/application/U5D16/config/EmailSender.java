package application.U5D16.config;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailSender {
    private final String apikey;
    private final String sender;

    public EmailSender(@Value("${sendgrid.apikey}") String apikey,
                       @Value("${sendgrid.sender}") String sender){
        this.apikey = apikey;
        this.sender = sender;
    }
    public void sendRegistrationEmail(String recipient, String name) throws IOException {
        Email from = new Email(sender);
        String subject = "Registration successful";
        Email to = new Email(recipient);
        Content content = new Content("text/plain", "welcome aboard " + name);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        sg.api(request);
    }

    public void sendemail(String recipient, String subjects, String contents) throws IOException{
        Email from = new Email(sender);
        String subject = subjects;
        Email to = new Email(recipient);
        Content content = new Content("text/plain", contents);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        sg.api(request);
    }
}
