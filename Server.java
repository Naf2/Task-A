import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

/*
 *Name: Server.java
 *Generate a random number between 0 to 12. get connect from client. takes integer number from client.
 *Match the number. Sebd response to Client. After 4 attempts, it terminate the program.
 */

public class Server {
   //port number
   static final int SERVER_PORT = 61103;
  
	
   public static void main(String args[]) throws IOException
   {
      boolean correctFlag = true;
      Random random = new Random();
      int userNumber,number2,counter = 1;
      System.out.println("Waiting to connect...");
      
      String inputLine;
      //generate a randon number between 0 to 12
      number2= random.nextInt(12)+1;
      ServerSocket s1 = new ServerSocket(SERVER_PORT);
      Socket srvsocket= s1.accept(); 
      
      //System.out.println("Waiting to connect..");
      
      try
      {
      	  Scanner scannerin = new Scanner(srvsocket.getInputStream());          
          PrintWriter scannerout = new PrintWriter(srvsocket.getOutputStream(), true);
          //System.out.println("Waiting to connect...");
          System.out.println("Client connected.");
          
          while(scannerin.hasNextLine())
          {
             inputLine = scannerin.nextLine();
          
             userNumber = Integer.parseInt(inputLine);  
             
             if(userNumber > 12 || userNumber < 0){
                 scannerout.println("Wrong Number, Number should be between 0~12 ");
             }
             else if(counter == 4 && userNumber != number2){
                 scannerout.println("Lost the game. The answer is "+ number2 +
                             ". Thank you for playing.");
             }
             
             else if (userNumber == number2) 
             {
                 scannerout.println("Congratulation. Thank you for playing. ");
             }
             
             else if (userNumber < number2)
             {
                 scannerout.println("The client guess number "+userNumber + 
                             " is smaller than the generated number");
                 counter ++; 
             }
             
             else if (userNumber > number2)      
             {
                 scannerout.println("The client guess number "+userNumber + 
                             " is bigger than the generated number");
                 counter ++; 
             }
        }

        scannerout.close();            
        scannerin.close();
        srvsocket.close();
     }  
     catch (Exception e) 
     {
         e.printStackTrace();
         System.err.println("The game is finshed ");
     }
  }
}

