package com.bitcamp.networking.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	// port boje od 0 do 65000 ( najbolje je da koristimo preko 1500)
	public static final int port = 1728;

	public static void startServer(){
			try {
				// ovaj Socket samo prima port
                // drugi client Socket prima adresu i port
				ServerSocket server = new ServerSocket(port); 
				
				// koristimo true da bi nam socket uvijek radio
				while(true){ 
					
					System.out.println("waiting");
					Socket client = server.accept();
					System.out.println(getMessage(client.getInputStream()));
					sendMessage(client);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	public static String getMessage(InputStream in){
		String bufferedStringClient ="";
		try {
			
			int msgClientLenght = in.read();
			byte [] byteBuffer = new byte[msgClientLenght];
			int byteRead = 0;
			
			StringBuilder sb = new StringBuilder();
			
			while ( (byteRead += in.read(byteBuffer)) >=0){
				sb.append( new String (byteBuffer).replaceAll("\\s+", " "));
				
				if ( byteRead >= msgClientLenght){
					break;
				}
			}
			
			bufferedStringClient = sb.toString();
//			System.out.println(bufferedStringClient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufferedStringClient;
		
	}
	
	public static void sendMessage(Socket client){
		OutputStream out;
		try {
			out = client.getOutputStream();
			String msg = "Hello from server!";
			byte [] msgBytes = msg.getBytes();
			out.write(msgBytes.length);
			
			out.write(msgBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	public static void main(String[] args) {
		startServer();
	}

}
