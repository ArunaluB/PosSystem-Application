package Edu.icet.DAO.Util;

import Edu.icet.DTO.OrderDto;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendReceiptEmail {

    private static void sendReceiptEmail(OrderDto dto) {
        String username = "snanayakkara426@gmail.com"; // Add your Gmail address here
        String password = "srkxhlfhmtqerzdf"; // Add your Gmail password here

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dto.getEmail()));
            message.setSubject("Receipt for Order #" + dto.getOrderId());

            // Build the email body with all the attributes
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("Dear ").append(dto.getName()).append(",\n\n")
                    .append("Thank you for your order. Here is your receipt:\n\n")
                    .append("Order ID: ").append(dto.getOrderId()).append("\n")
                    .append("Name: ").append(dto.getName()).append("\n")
                    .append("Email: ").append(dto.getEmail()).append("\n")
                    .append("Phone Number: ").append(dto.getPhonenumber()).append("\n")
                    .append("Date: ").append(dto.getDate()).append("\n")
                    .append("Note: ").append(dto.getNote()).append("\n")
                    .append("Type: ").append(dto.getType()).append("\n")
                    .append("Status: ").append(dto.getStatus()).append("\n\n")
                    .append("Thank you for shopping with us!");

            message.setText(emailBody.toString());

            Transport.send(message);

            System.out.println("Receipt sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendReceipt(OrderDto dto){
        sendReceiptEmail(dto);
    }

}
