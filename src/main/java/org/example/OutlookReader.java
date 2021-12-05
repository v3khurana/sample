package org.example;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import com.sun.mail.pop3.POP3Store;

public class OutlookReader {

    public static void main(String[] args) throws MessagingException, IOException {
        // TODO Auto-generated method stub


        String host = "outlook.office365.com";
        String mailStoreType="imap";
        String username = "varun.khurana@simplifyvms.com";
        String password = "";




        receiveEmail(host, mailStoreType, username, password);



    }

    private static void receiveEmail(String host, String mailStoreType, String username, String password) throws MessagingException, IOException {
        // TODO Auto-generated method stub

        Properties properties = new Properties();


        properties.setProperty("mail.store.protocol", "imap");
        properties.setProperty("mail.imap.user",username);
        properties.setProperty("mail.imap.host", "outlook.office365.com");
        properties.put("mail.imap.starttls.enable", "false");
        properties.setProperty("mail.imap.port", "995");
        //Session emailSession = Session.getDefaultInstance(properties);
        Session emailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        System.out.println("1");

        Store store = emailSession.getStore("imaps");
        System.out.println("2");
        store.connect(host, username, password);
        System.out.println("3");
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);
        //4) retrieve the messages from the folder in an array and print it

        /*
        Message[] messages = emailFolder.getMessages();
        for (int i = 0; i < messages.length; i++) {
            Message message = messages[i];
            System.out.println("---------------------------------");
            System.out.println("Email Number " + (i + 1));
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + message.getFrom()[0]);
            System.out.println("Text: " + message.getContent().toString());
        }
        */
        String emailSubject = "Candidate Invite";
        //SearchTerm searchTerm = new AndTerm(new SubjectTerm(emailSubject), new BodyTerm(emailSubject));
        SearchTerm searchTerm = new SubjectTerm(emailSubject);
        Message[] messages1 = emailFolder.search(searchTerm);
        for (Message message : messages1) {
            if (message.getSubject().contains(emailSubject)) {
                System.out.println("---------------------------------");
                System.out.println("Found the Email with Subject : " + emailSubject);
                System.out.println("---------------------------------");
                //System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                //System.out.println("Text: " + message.getContent().toString());
                String str = message.getContent().toString();
                //System.out.println(str);
                System.out.println("---------------------------------");
                System.out.println("---------------------------------");
                System.out.println("---------------------------------");
                //System.out.println("Text: " + message.getContent().toString().split("alt=")[0].split("img src=")[1]);
                String string1 = "Out of this String I want 'Geeks for Geeks' only this fwyyuuew want ghewjj";
                //Matcher m = Pattern.compile(".*this(.*)want.*").matcher(string1);
                Matcher m = Pattern.compile(".*img src=\"(.*)\" alt.*").matcher(str);
                while(m.find()) {
                    System.out.println("---------------------------------");
                    System.out.println("URL is ");
                    System.out.println("---------------------------------");
                    //System.out.println("Printing something");
                    //System.out.println(m.group(1));
                    String str1 = m.group(1);
                    System.out.println(str1);
                    //Matcher m1 = Pattern.compile(".*http://(.*)\".*").matcher(str1);
                    //if(m1.find()) {
                    if(str1.contains("http")){
                        System.out.println("---------------------------------");
                        System.out.println("-+++++++++++++++++++++++++++++");
                        //System.out.println(m.group(1));
                        //String str2 = m.group(1);
                        //System.out.println(str2);
                        //Matcher m1 = Pattern.compile(".*alt=(.*)").matcher(str1);

                        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                        //String str3 = m1.group();
                        //System.out.println(str1.split("alt=")[0]);
                    }
                    System.out.println("---------------------------------");
                    System.out.println("---------------------------------");
                }
            }
        }



    }

}