package edu.northeastern.cs_5004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PalindromeServer {
	private static ServerSocket listener;

	public static void main(String[]args) throws IOException{
		if(args.length != 1){
			System.err.println("Usage: java PalidromeServer<port number>");
			System.exit(1);
		}
		//get the port number 
		int portNumber = Integer.parseInt(args[0]);
		System.out.println(portNumber);
		//connect to this specific server
		listener = new ServerSocket(portNumber);
		
		while(true){
			
			Socket socket = listener.accept();
			InputStream serverIn = socket.getInputStream();
			OutputStream serverOut = socket.getOutputStream();
			serverOut.write("Just connected...\n".getBytes());
			BufferedReader in = new BufferedReader(new InputStreamReader(serverIn));
			serverOut.write("Please input a text...".getBytes());
			
			//turn input into the output by attach a palindrome string of the input
			String input = in.readLine();
			String palindrome = new StringBuilder(input).reverse().toString();
			String output = input + palindrome + "\n";
			serverOut.write(output.getBytes());
			
			in.close();
			serverOut.close();
			//Closes the socket, which makes this Socket object no longer capable 
			//of connecting again to any server.
			socket.close();
			
		
			}
			
		}
	}

