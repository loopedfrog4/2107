package simple_web_server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;

public class webServer {

	public static void main(String[] args) throws Exception {
		String get_url = "http://localhost:8080/Test.html";
		URL url = new URL(get_url);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		
		//The port, at which, the server is listening for requests
		int port = 8080;
		
		try {
			//ServerSocket object is used to listen for requests
			ServerSocket ss = new ServerSocket(port);
			
			System.out.println("Server is ready to receive command!");
			
			//accepting connection requests
			Socket socket = ss.accept();
					
			sendPage(con, socket);					
				
			
			socket.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// Helper method to serve html file to HTTP request from browser
	public static void sendPage(HttpURLConnection con, Socket client) throws Exception {
		System.out.println("Page writter called");

        File file = new File(new URI("file:///C:/Users/zheng/eclipse-workspace/Assignment_2/src/simple_web_server/Test.html"));
        
        
        PrintWriter printWriter = new PrintWriter(client.getOutputStream());// Make a writer for the output stream to
                                                                            // the client
        BufferedReader reader = new BufferedReader(new FileReader(file));// grab a file and put it into the buffer
        // print HTTP headers
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html");
        printWriter.println("Content-Length: " + file.length());
        printWriter.println("\r\n");
        
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			response.append("HTTP/1.0 200 OK");
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request did not work.");
		}
        
        
        String line = reader.readLine();// line to go line by line from file
        while (line != null)// repeat till the file is read
        {
            printWriter.println(line);// print current line

            line = reader.readLine();// read next line
        }
        reader.close();// close the reader
        printWriter.close();
	}
    

}
