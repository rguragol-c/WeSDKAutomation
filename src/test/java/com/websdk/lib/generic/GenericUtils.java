package com.websdk.lib.generic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.ITestResult;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;


import com.websdk.lib.generic.ConfigProperties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


public class GenericUtils{

	
public static void writeToFile(String filepath,String data)
{
	BufferedWriter writer;     
	File file=new File(filepath);
	
		try
		{
			file.createNewFile();
		    writer = new BufferedWriter(new FileWriter(file,false));
		    
	           writer.write(data);
		        
		    writer.close();
	}
		catch(IOException e)
		{
		    System.out.println("There was a problem: " + e);
		}
}
public static void deleteFile(String filepath)
{
	File file=new File(filepath);
	file.deleteOnExit();
}
}