package org.example;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutlookReader1 {

    public static void main(String[] args) throws MessagingException, IOException {
        String host = "outlook.office365.com";
        String mailStoreType="imap";
        String username = "varun.khurana@simplifyvms.com";
        String password = "cORP#123";
        receiveEmail(host, mailStoreType, username, password);
    }

    private static String receiveEmail(String host, String mailStoreType, String username, String password) throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", "imap");
        properties.setProperty("mail.imap.user", username);
        properties.setProperty("mail.imap.host", "outlook.office365.com");
        properties.put("mail.imap.starttls.enable", "false");
        properties.setProperty("mail.imap.port", "995");
        Session emailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Store store = emailSession.getStore("imaps");
        store.connect(host, username, password);
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);

        String emailSubject = "Candidate Invite";
        SearchTerm searchTerm = new SubjectTerm(emailSubject);
        Message[] messages1 = emailFolder.search(searchTerm);
        String urlStr = null;
        for (Message message : messages1) {
            if (message.getSubject().contains(emailSubject)) {
                System.out.println("---------------------------------");
                System.out.println("Found the Email with Subject : " + emailSubject);
                System.out.println("---------------------------------");

                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                String str = message.getContent().toString();
                //System.out.println(str);
                //Matcher m = Pattern.compile(".*img src=\"(.*)\" alt.*").matcher(str);
                //Matcher m = Pattern.compile(".*<a(.*)</a>.*").matcher(str);
                //Matcher m = Pattern.compile(" (<a [^>]+>)" + "Set Up Account" + "</a>").matcher(str);
                Matcher m = Pattern.compile("href=\"(.*?)\".*?Set", Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(str);
                while (m.find()) {
                    String str1 = m.group(1);
                    //System.out.println(str1);
                    if (str1.contains("simplifyvms.com")) {
                        System.out.println(str1);
                        urlStr = str1;
                    }
                }
            }
        }
        return urlStr;
    }
        public static String getSetupURL() throws MessagingException, IOException {
            String host = "outlook.office365.com";
            String mailStoreType="imap";
            String username = "varun.khurana@simplifyvms.com";
            String password = "cORP#123";
            return receiveEmail(host, mailStoreType, username, password);
        }
        

}