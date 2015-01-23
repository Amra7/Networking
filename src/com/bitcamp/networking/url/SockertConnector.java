package com.bitcamp.networking.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SockertConnector {

	public static final String serverAdress = "127.0.0.1"; // mogli smo napisati
															// i "local host" to
															// znaci da se
															// pokusavamo
															// spojiti na isti
															// racunar

	// port mora biti isti kao kod servere, ako nije isti onda se ne mogu
	// spojiti
	public static final int port = 1728;

	private static void connectToServer() throws UnknownHostException,
			IOException {

		// klijentu Socket moramo predati i adresu i port
		Socket client = new Socket(serverAdress, port);

		sendMessage(client.getOutputStream());
		System.out.println(getMessage(client.getInputStream()));

		System.out.println("Gotovo!");
		client.close();

	}

	public static String getMessage(InputStream clientInt) {
		String bufferString = "";
		try {
			int msgLength = clientInt.read();

			byte[] byteBuffer = new byte[msgLength];
			int byteRead = 0;
			
			StringBuilder sb = new StringBuilder();

			while ((byteRead += clientInt.read(byteBuffer)) >= 0) {

				sb.append(new String(byteBuffer).replaceAll("\\s+", " "));

				if (byteRead >= msgLength) {
					break;
				}
			}
			bufferString = sb.toString();
			System.out.println(bufferString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufferString;

	}

	public static void sendMessage(OutputStream clientOut) {
		try {
			String msgClient = "Gorjane sasavce!";
			byte[] msgClientLength = msgClient.getBytes();
			clientOut.write(msgClientLength.length);
			clientOut.write(msgClientLength);
			System.out.println("Message sent!");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			connectToServer();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
