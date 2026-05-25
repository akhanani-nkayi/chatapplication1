/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapplication;
//This will be my package name depending on how I saved my project
/**
 *
 * @author Student
 */
public class login {
    
    //These variables store user details
    
   String username;
   String password;
   String phoneNumber;
   

   public boolean checkUserName(String username){
    //This checks the username contains an underscore "_" and no more than 5 characters long   
        return username.contains("_") && username.length() <= 5;
            }
   
  //Checks if the password is at least 8 characters long 
  public boolean checkPasswordComplexity(String password){
      boolean hasCapital = false;
      boolean hasNumber = false;
      boolean hasSpecial = false;
      
      for (int i = 0; i < password.length(); i++) { //Goes through each character based on the lenght of the password
          
          char c = password.charAt(i);
          
          if (Character.isUpperCase(c)) {   //Checks if it has a capital letter
              hasCapital = true;
          }
          else if (Character.isDigit(c)){   //Checks if it has a digit
              hasNumber = true;
          } 
          else if (!Character.isLetterOrDigit(c)) {     //Checks if it has a special character
              hasSpecial = true;
          }     
      }
      return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;  
  }
    //Check if the phone number is 10 characters length
    public boolean checkCellPhoneNumber(String phoneNumber){
        return phoneNumber.startsWith("+27") && phoneNumber.length() == 12;
    }
    
    public String RegisterUser(String username, String password, String phoneNumber){
        if (!checkUserName(username)){//Displays error message when Username is false
         return "Username is not correctly formatted. Please ensure that your username contains"
                 + " an underscore and is no more than five characters in length.";   
        }
        
        if (!checkPasswordComplexity(password)){//Displays error message when password is false
         return "Password is not correctly formatted. Please ensure that the password"
                 + " contains at least 8 characters, capital letter, number, and a special character";   
        }
        
        if (!checkCellPhoneNumber(phoneNumber)){//Displays error message when phone number is false
         return "Cell phone number is not correctly formatted or does not contain international code";   
        }
        
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        
        return "User registered successfully";
    }
    
    public boolean LoginUser(String username, String password){
    //This checks the username and password are correct
        if (this.username == null || this.password == null){ 
        return false;
        }
        return this.username.equals(username) && this.password.equals(password);
    }
    
    
    public String ReturnLoginStatus(boolean succes){
    //This displays message if status correct or incorrect    
        if (succes){
            return "Welcome " + username + " it is great to see you again.";
        }else{
            return "Username or password is incorrect please try again.";
        }
    }
}
