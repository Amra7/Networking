package com.bitcamp.networking.url;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnector {

	private static final String url = "http://klix.ba";
	OutputStream os;
	private static final String file = "/tmp/";

	public static void getUrl(String urlToVisit) throws MalformedURLException {
		URL urlVisiting = new URL(urlToVisit);
		try {
			URLConnection connection = urlVisiting.openConnection();
			InputStream in = connection.getInputStream();
			printOutput(in);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	@SuppressWarnings("unused")
	private static String printOutput(InputStream in) throws IOException {
		InputStreamReader isr = new InputStreamReader(in);
		@SuppressWarnings("unused")
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();

		while (true) {
			if (br.readLine() == null) {
				break;
			}
			sb.append(br.readLine() + "\n");

		}
		writeOutputToFile(sb.toString());
		return null;

	}

	private static void writeOutputToFile(String inputString)
			throws IOException {

		// System.out.println(inputString);
		File f = new File(file + "BitCamp.txt");
		FileOutputStream fos = new FileOutputStream(f);

		try {
			fos.write(inputString.getBytes());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			fos.close();

		}

	}

	public static void main(String[] args) {
		// System.out.println(getUrl(url));
		try {
			getUrl(url);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}

	}
}
