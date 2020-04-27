import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.net.*;

/*
 * Name: Client.java
 * Client class for single player. Connect with server than takes integr input betwwen 0 to 12.
 * Send the number to server & print the response.
 */

public class Client
{
    //Server host name
    static final String SERVER_HOST = "netprog2.csit.rmit.edu.au";
    //port number
    static final int SERVER_PORT = 61103;
    
    public static void main(String[] args) throws IOException 
    {
        try 
        {
            //Connect to server via Socket
            Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);         
            //Initialize socket input
            Scanner socketIn = new Scanner(clientSocket.getInputStream());
            //Initialize Socket output
            PrintWriter socketOut = new PrintWriter(clientSocket.getOutputStream(),true); 
            Scanner scannerIn = new Scanner(System.in);
            System.out.println("Welcome!");
            
            String serverResponse = "";
            System.out.println("Hi, Guess a number between 0 and 12.");

            while (true) 
            {
                String userInput = scannerIn.nextLine();
                socketOut.println(userInput);
                serverResponse = socketIn.nextLine();
                System.out.println(serverResponse);
                if (serverResponse.contains("Thank you for playing.")) 
                    break; 

            }

            //Close all connection        
            socketOut.close();
            scannerIn.close();
            socketIn.close();
            clientSocket.close();
        } 
        catch (ConnectException e) 
        {
            //Show error message if connection failed
            System.err.println("Failed connection. Check the server and the connection to it.");
            System.exit(1);
        } 
        catch (IOException e) 
        {
            //Show error message if there is input or output error
            System.err.println("There is no Input or Output to connect");
            System.exit(1);
        }
    }
}
