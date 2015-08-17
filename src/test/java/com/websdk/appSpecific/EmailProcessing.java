package com.websdk.appSpecific;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;


public class EmailProcessing {

	public String sender = null;
	public String subject = null;
	public String mailContent = null;

	public EmailProcessing(String sender, String subject, String mailContent) {
		this.sender = sender;
		this.subject = subject;
		this.mailContent = mailContent;
	}

	public static boolean VerifyEmail(String sender, String subject,
			Date sentDate, String mailContent) throws MessagingException,
			InterruptedException {
		boolean flag = false;
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imap");
		Session session = Session.getInstance(props, null);
		Store store = session.getStore();
		try{
		store.connect("mxabu1.cisco.com","anisinha@mxabu1.com", "cisco_123");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//store.connect("10.194.82.90","anisinha@mxabu1.com", "cisco_123");
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);
		System.out.println("Total messages : " + inbox.getMessageCount());
		int total = inbox.getMessageCount();
		System.out.println("Total unread messages: "+ inbox.getUnreadMessageCount());
		/*
		 * Flags read = new Flags(Flags.Flag.SEEN); FlagTerm unRead = new
		 * FlagTerm(read,false); Message messages[] = inbox.search(unRead);
		 */
		Message messages[] = null;
		Message message = inbox.getMessage(total);
		Address[] fromAddress = message.getFrom();
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// sentDate = "Tue Nov 19 17:33:15 IST 2013";
		// Date currDate = dateFormat.format(date);
		System.out.println("Current Date " + date);
		System.out.println("Latest mail in inbox:");
		String emailFrom = fromAddress == null ? null	: ((InternetAddress) fromAddress[0]).getAddress();
		System.out.println(emailFrom + "\t" + message.getSubject() + "\t"+ message.getReceivedDate());
		int end = inbox.getMessageCount();
		int start = end - 9;
		messages = inbox.getMessages(start, end);
		System.out.println("Length :" + messages.length);
		if (messages.length == 0) {
			Thread.sleep(15000);
			messages = inbox.getMessages(start, end);
		} else {
			for (int i = 9; i >= 0; i--) {
				Date receivedDate = messages[i].getReceivedDate();
				if (sentDate.compareTo(receivedDate) < 0) {
					fromAddress = messages[i].getFrom();
					emailFrom = fromAddress == null ? null	: ((InternetAddress) fromAddress[0]).getAddress();
					if (sender.equalsIgnoreCase(emailFrom)) {
						flag =  true;
						String subjectLine = messages[i].getSubject();
						if (subject.equalsIgnoreCase(subjectLine)) {
							flag = flag && true;
							System.out.println(emailFrom + "\t" + subjectLine	+ "\t" + messages[i].getReceivedDate());
						}
						else
						{
							flag = flag && false;
						}
					}
				}
			}
		}
		if(flag == false)
		{
			System.out.println("No emails received with specified subject and sender.");
		}
		return flag;
	}
	
	public static void main(String[] a ) throws MessagingException, InterruptedException
	{
	Date date = new Date();
	VerifyEmail("etao@mxabu1.com","Processing of video \"Shortvideo.mp4\" has completed ",date,"asljasdkad");
	}

}
