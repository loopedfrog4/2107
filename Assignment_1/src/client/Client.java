package client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {

	public static void main(String[] args) throws IOException{
		//Name of the host that we are going to conenct to
		String hostName = "localhost";
		//Make sure that this port is the same as
		//the server listening port
		int port = 12345;
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			try {
				//Use socket to connect to the server
				Socket socket = new Socket(hostName, port);
				String request = sc.nextLine();
				//Access to the output stream
				OutputStream os = socket.getOutputStream();
				//Write lie
				PrintWriter pw = new PrintWriter(os);
				pw.println(request);
				pw.flush();
				//Read response.
				InputStream is = socket.getInputStream();
				//Read characters
				InputStreamReader isr = new InputStreamReader(is);
				//Read lines
				BufferedReader br = new BufferedReader(isr);
				String header = br.readLine();
				System.out.println(header);
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}	
		}
		
		
		
		
	}

}
