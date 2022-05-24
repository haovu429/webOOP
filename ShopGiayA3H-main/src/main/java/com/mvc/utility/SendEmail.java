package com.mvc.utility;

import com.controller.CartBean;
import com.controller.CartItemBean;
import com.controller.User;
import com.dao.OrderDao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Properties;
import java.util.Random;



public class SendEmail {
    //generate vrification code
    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    //send email to the user email
    public boolean sendEmail(User user, String email, String name, String phone, String address, String total, String rootlink) {
        boolean test = false;

        String toEmail = user.getEmail();
        String fromEmail = "caihoncuagiamnguc@gmail.com";
        String password = "Azkaban11@";

        try {

            // your host email smtp server details
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com");
            pr.setProperty("mail.smtp.port", "587");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.ssl.trust", "*");
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

            //set from email address
            mess.setFrom(new InternetAddress(fromEmail));
            //set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            OrderDao orderDao = new OrderDao();
            Integer oid = orderDao.getLastOrderId();
            String hashemail = MD5Utils.hashcode(email);
            String link = "https://shopgiaya3h.herokuapp.com/profile?oid="+oid+"&email="+hashemail+"";
            //set email subject
            mess.setSubject("User Email Verification");
            long millis=System.currentTimeMillis();
            java.sql.Date nowDate=new java.sql.Date(millis);

            //set message text

//            mess.setText("Registered successfully.Please verify your account using this code: " + email);
            mess.setContent("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"vi\">\n" +
                    "    <head>\n" +
                    "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                    "        <title>Email Template for Order Confirmation Email</title>\n" +
                    "        \n" +
                    "        <!-- Start Common CSS -->\n" +
                    "        <style type=\"text/css\">\n" +
                    "            #outlook a {padding:0;}\n" +
                    "            body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0; font-family: Helvetica, arial, sans-serif;}\n" +
                    "            .ExternalClass {width:100%;}\n" +
                    "            .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;}\n" +
                    "            .backgroundTable {margin:0; padding:0; width:100% !important; line-height: 100% !important;}\n" +
                    "            .main-temp table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family: Helvetica, arial, sans-serif;}\n" +
                    "            .main-temp table td {border-collapse: collapse;}\n" +
                    "        </style>\n" +
                    "        <!-- End Common CSS -->\n" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"backgroundTable main-temp\" style=\"background-color: #d5d5d5;\">\n" +
                    "            <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td>\n" +
                    "                        <table width=\"600\" align=\"center\" cellpadding=\"15\" cellspacing=\"0\" border=\"0\" class=\"devicewidth\" style=\"background-color: #ffffff;\">\n" +
                    "                            <tbody>\n" +
                    "                                <!-- Start header Section -->\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"padding-top: 30px;\">\n" +
                    "                                        <table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"devicewidthinner\" style=\"border-bottom: 1px solid #eeeeee; text-align: center;\">\n" +
                    "                                            <tbody>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"padding-bottom: 10px;\">\n" +
                    "                                                        <a href=\"https://htmlcodex.com\"><img src=\"https://raw.githubusercontent.com/ThaiHaiDev/ProjectSummaryImage-/main/logo.png\" alt=\"PapaChina\" /></a>\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        Hoa Don Thanh Toan\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        Thu Duc, Viet Nam, 84\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        Phone: 0979409568 | Email: \"+email+\"\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"font-size: 14px; line-height: 18px; color: #666666; padding-bottom: 25px;\">\n" +
                    "                                                        <strong>Order Number:</strong> "+oid+" | <strong>Order Date:</strong> "+nowDate+"\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                            </tbody>\n" +
                    "                                        </table>\n" +
                    "                                    </td>\n" +
                    "                                </tr>\n" +
                    "                                <!-- End header Section -->\n" +
                    "                                \n" +
                    "                                <!-- Start address Section -->\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"padding-top: 0;\">\n" +
                    "                                        <table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"devicewidthinner\" style=\"border-bottom: 1px solid #bbbbbb; margin-left: 70px;\">\n" +
                    "                                            <tbody>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"width: 55%; font-size: 16px; font-weight: bold; color: #666666; padding-bottom: 5px;\">\n" +
                    "                                                        Delivery Adderss\n" +
                    "                                                    </td>\n" +
                    "                                                    <td style=\"width: 45%; font-size: 16px; font-weight: bold; color: #666666; padding-bottom: 5px;\">\n" +
                    "                                                        Billing Address\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"width: 55%; font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        So 1, Vo Van Ngan\n" +
                    "                                                    </td>\n" +
                    "                                                    <td style=\"width: 45%; font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        "+address+"\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"width: 55%; font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        Phuong Linh Chieu\n" +
                    "                                                    </td>\n" +
                    "                                                    <td style=\"width: 45%; font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        dia chi\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"width: 55%; font-size: 14px; line-height: 18px; color: #666666; padding-bottom: 10px;\">\n" +
                    "                                                        Thanh Pho Thu Duc, Viet Nam \n" +
                    "                                                    </td>\n" +
                    "                                                    <td style=\"width: 45%; font-size: 14px; line-height: 18px; color: #666666; padding-bottom: 10px;\">\n" +
                    "                                                        Viet Nam\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                            </tbody>\n" +
                    "                                        </table>\n" +
                    "                                    </td>\n" +
                    "                                </tr>\n" +
                    "                                <!-- End address Section -->\n" +
                    "                                \n" +
                    "                                \n" +
                    "                                <!-- Start payment method Section -->\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"padding: 0 10px;\">\n" +
                    "                                        <table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"devicewidthinner\" style=\"margin-left: 80px;\">\n" +
                    "                                            <tbody>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td colspan=\"2\" style=\"font-size: 16px; font-weight: bold; color: #666666; padding-bottom: 5px;\">\n" +
                    "                                                        Detail Receipt\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"width: 55%; font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        Name: "+name+"\n" +
                    "                                                    </td>\n" +
                    "                                                    <td style=\"width: 45%; font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        Phone: "+phone+"\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"width: 55%; font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        Email: "+email+"\n" +
                    "                                                    </td>\n" +
                    "                                                    <td style=\"width: 45%; font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        Date: "+nowDate+"\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"width: 55%; font-size: 14px; line-height: 18px; color: #666666;\">\n" +
                    "                                                        Address: "+address+"\n" +
                    "                                                    </td>\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"width: 55%; font-size: 14px; line-height: 18px; color: #666666; padding-bottom: 10px;\">\n" +
                    "                                                        Price Ship: 50$\n" +
                    "                                                    </td>\n" +
                    "                                                    <td style=\"width: 45%; font-size: 14px; line-height: 18px; color: #666666; padding-bottom: 10px;\">\n" +
                    "                                                        Total: "+total+"\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td style=\"width: 55%; font-size: 14px; line-height: 18px; color: #666666; padding-bottom: 10px;\">\n" +
                    "                                                        Link Detail Bill: "+link+"\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                                <tr>\n" +
                    "                                                    <td colspan=\"2\" style=\"width: 100%; text-align: center; font-style: italic; font-size: 13px; font-weight: 600; color: #666666; padding: 15px 0; border-top: 1px solid #eeeeee;\">\n" +
                    "                                                        <b style=\"font-size: 14px;\">Note:</b> Thank You For Purchasing From Us\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                            </tbody>\n" +
                    "                                        </table>\n" +
                    "                                    </td>\n" +
                    "                                </tr>\n" +
                    "                                <!-- End payment method Section -->\n" +
                    "                            </tbody>\n" +
                    "                        </table>\n" +
                    "                    </td>\n" +
                    "                </tr>\n" +
                    "            </tbody>\n" +
                    "        </table>\n" +
                    "    </body>\n" +
                    "</html>","text/html");

//            //send the message
            Transport.send(mess);

            test=true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
}