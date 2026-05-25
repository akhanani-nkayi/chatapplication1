/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class mainapp {
   public static void main(String[] args){
       //Allows the user to type in their information
       Scanner input = new Scanner(System.in);
       //Creates an object for ligin class so that it can call its method
       login user = new login();
       
       //Registration Section
       System.out.println("===User Registration===");
       
       System.out.print("Enter a username");
       String username = input.nextLine();
       
       System.out.print("Enter a password");
       String password = input.nextLine();
       
       System.out.print("Enter your South African phone number (+27...):");
       String cellnumber = input.nextLine();
       
       // Register user
String response = user.RegisterUser(username, password, cellnumber);

System.out.println(response);

// ONLY continue if registration succeeded
if (response.equals("User registered successfully")) {
    // Login Section
    System.out.println("===User Login===");

    System.out.print("Enter your username: ");
    String loginUsername = input.nextLine();

    System.out.print("Enter your password: ");
    String loginPassword = input.nextLine();

    boolean loggedIn = user.LoginUser(loginUsername, loginPassword);

    String loginMessage = user.ReturnLoginStatus(loggedIn);

    System.out.println(loginMessage);

    //Only opens menu if login succeeded
    if (loggedIn) {
        System.out.println("Welcome to ChatApp");

        boolean loggedInPart2 = true;
        while(loggedInPart2) {
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");

            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine();
                switch(choice){
                    case 1:
                        System.out.println("How many messages would you like to send?");
                        int numMessages = input.nextInt();
                        input.nextLine();

                        for (int i = 0; i < numMessages; i++) {
                            int messageNumber = i + 1;
                            System.out.println("--- Message " + messageNumber + " ---");
                            System.out.print("Enter message: ");
                            String message = input.nextLine();
                            System.out.println("Message sent: " + message);
                        }
                        break;
                    case 2:
                        System.out.println("Coming soon");
                        break;
                    case 3:
                        loggedInPart2 = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Please choose 1, 2 or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter numbers only.");
                input.nextLine();
            }
        }
    } else {
        System.out.println("Login failed. Program stopped.");
    }
    } else {
    System.out.println("Registration failed. Program stopped.");
        }
   }
}