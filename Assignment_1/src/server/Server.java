package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	
	public static void main(String[] args) throws IOException{
		
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
				String[] commandTokens = br.readLine().split("\\s+");;
				String response = "";
				
				// Command Validations
				
				// 2. Invalid Number of arguments
				if(commandTokens.length == 3){
					
					if (isNumeric(commandTokens[1]) && isNumeric(commandTokens[2])) {
						// Call help method 'calculate' to do calculations
						int result = calculate(commandTokens[0], Integer.parseInt(commandTokens[1]), Integer.parseInt(commandTokens[2]));
						
						if (result == 0) {
							response = "Error: Invalid command " + "\"" + commandTokens[0] +"\"";
							if (Integer.parseInt(commandTokens[2]) == 0) {
								response = "Error: Divided by zero exception";
							}
						} else {
							response = "The " + commandTokens[0] + " result is: " + Integer.toString(result);
						}
					} else {
						if (!isNumeric(commandTokens[1])) {
							response = "Error: " + "\"" + commandTokens[1] + "\"" + " is not a number";
						} else if (!isNumeric(commandTokens[2])) {
							response = "Error: " + "\"" + commandTokens[2] + "\"" + " is not a number";
						}
					}
					
					
					
				} else {
					response = "Result: Invalid number of arguments ";
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

	public static int calculate(String command, int first, int second) {
		int result = 0;
	
		
		if (command.equalsIgnoreCase("add")) {
			return first + second;
		} else if (command.equalsIgnoreCase("multiply")) {
			return first * second;
		} else if (command.equalsIgnoreCase("subtract")) {
			return first - second;
		} else if (command.equalsIgnoreCase("divide")) {
			if (second == 0) {
				return result;
			} else {
				return first / second;
			}	
		} else {
			return result;
		}
		
	}

	public static boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} catch(NumberFormatException e){  
		    return false;  
		}  
	}
	
}
