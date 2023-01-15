package tcpserverapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TCPServerApp {
	public static void main(String[] args) {
		//The port, at which, the server is listening for requests
		int port = 12345;
		
		try {
			//ServerSocket object is used to listen for requests
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Server is ready to receive command!");
			while(true){
				//accepting connection requests
				Socket socket = ss.accept();
				
				//get the input stream to read data
				InputStream is = socket.getInputStream();
				
				//Read data as character
				InputStreamReader isr = new InputStreamReader(is);
				
				//Read data as lines
				BufferedReader br = new BufferedReader(isr);
				
				//Read the string command from the user
				String command = br.readLine();
				String response = "";
				if(command.equals("GET TIME")){
					response = "This is " + socket.getLocalAddress() + ":" + socket.getLocalPort() + ", current time here is: " + new Date();
				}else{
					response = "Invalid command, should use 'GET TIME'";
				}
				//Access to the output stream, to write data back
				OutputStream os = socket.getOutputStream();
				//Write lines
				PrintWriter pw = new PrintWriter(os);
				pw.println(response);
				pw.flush();
				pw.close();
				socket.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}