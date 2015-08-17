package com.websdk.lib.generic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHConnect {
	
	private static Session SessionConnection;
	private static JSch jsch = new JSch();
	private static Channel channel;
	
	public static void connectToServer(String hostname,String username,String password)
	{
		try
		{
		Properties prop = new Properties();
		prop.put("StrictHostKeyChecking","no");
		SessionConnection = jsch.getSession("root",hostname); 
		SessionConnection.setPassword(password);
		SessionConnection.setConfig(prop);
		SessionConnection.connect();
		}
		catch(Exception e)
		{
			Log.error(e.toString());
		}
	}
	
	public static int executeCommand(String command)
	{
		int exitStatus=1;
		try
		{
		channel = SessionConnection.openChannel("exec");
	        ((ChannelExec)channel).setCommand(command);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	   
	        InputStream in=channel.getInputStream();
	   
	        channel.connect();
	        byte[] tmp=new byte[1024];
	        while(true){
	          while(in.available()>0){
	            int i=in.read(tmp, 0, 1024);
	            if(i<0)break;
	            Log.info(new String(tmp, 0, i));
	            
	           
	          }
	          if(channel.isClosed()){
	            if(in.available()>0) continue; 
	            exitStatus=channel.getExitStatus();
	            Log.info("exit-status: "+exitStatus);
	            break;
	          }
	          try{Thread.sleep(1000);}catch(Exception ee){}
	        }
	        channel.disconnect();
		}
		catch(Exception e)
		{
			Log.error(e.toString());
		}
		return exitStatus;
	}
	
	public static String GetTarfilename(String command)
	{
		int exitStatus=1;
		String tarfilename="";
		
		try
		{
		channel = SessionConnection.openChannel("exec");
	        ((ChannelExec)channel).setCommand(command);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	   
	        InputStream in=channel.getInputStream();
	   
	        channel.connect();
	        byte[] tmp=new byte[1024];
	        while(true){
	          while(in.available()>0){
	            int i=in.read(tmp, 0, 1024);
	            if(i<0)break;
	            Log.info(new String(tmp, 0, i));
	            String tarfilename1[] =new String(tmp, 0, i).split("\n");
	            tarfilename=tarfilename1[0];
	            break;
	          }
	          if(channel.isClosed()){
	            if(in.available()>0) continue; 
	            exitStatus=channel.getExitStatus();
	            Log.info("exit-status: "+exitStatus);
	            break;
	          }
	          try{Thread.sleep(1000);}catch(Exception ee){}
	        }
	        channel.disconnect();
		}
		catch(Exception e)
		{
			Log.error(e.toString());
		}
		return tarfilename;
	}
	public static String executeCommandwithOutput(String command)
	{
		String result=null;
		try
		{
		channel = SessionConnection.openChannel("shell");
        ((ChannelExec)channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec)channel).setErrStream(System.err);
        InputStream in=channel.getInputStream();
 	   
        channel.connect();
        BufferedReader reader= new BufferedReader(new InputStreamReader(in));
        
	    BufferedWriter writer =
	            new BufferedWriter(new OutputStreamWriter(channel.getOutputStream()));
       
	

	    writer.write("ls\r\n");  
	    writer.flush();  

	    // and print the response   
	    String line = reader.readLine();
	    result = line + "\n";

	    while ((line = reader.readLine()) != null) {
	        result += line + "\n";
	    }
	    reader.close();  
	    writer.close();  
	    channel.disconnect();
		}
		catch(Exception e)
		{
			Log.error(e.toString());
		}
	    return result;
	}
	
	public static void disconnectFromServer()
	{
		SessionConnection.disconnect();
	}
	
	
}
