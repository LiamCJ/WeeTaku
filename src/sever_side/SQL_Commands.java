/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools|Templates
 * and open the template in the editor.
 */
package sever_side;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author liam
 */
public class SQL_Commands {
    // A Method that executes all commands using JDBC inorder to communicate with SQL database
    public static String SQL_Commands(String command, int task){
        try{
           // getting connection to the database call WeeBase
           Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/WeeBase", "root", "");
           // Statement variable to shorten the "createStatement"'s methods when calling them
           Statement myStatement = myConnection.createStatement();
           String outputMessage = "";
           
           switch(task){
                case 1: // case for Adding series
                     //lines 29,36,65 use this method that update the Table in the database 
                    myStatement.executeUpdate(command);
                    
                    outputMessage = "Added!!";
                    return outputMessage;
//                    break;
                    
                case 2: //case for editing series
                    myStatement.executeUpdate(command);
                    
                    outputMessage = "Changes Made";
                    return outputMessage;
//                    break;
                    
                case 3: //case for searching for a specific series
                    //lines 44,54 use this method in order to excute querys to the database about the table
                    ResultSet mySearch = myStatement.executeQuery(command);
                    // retrieves the infomation about the title the user searches
                    while (mySearch.next()){
                        outputMessage = mySearch.getString("Title") + "/" + mySearch.getString("Last_Episode_Watched") + "/" +  mySearch.getString("Last_Season_Watched") + "/" +  mySearch.getString("No_of_Seasons") ;
                        
                    }
                    return outputMessage;
//                    break;
                    
                case 4: //case for viewing all series
                    ResultSet myRes = myStatement.executeQuery(command);

                    while (myRes.next()){
                        outputMessage = outputMessage + myRes.getString("Title") + "/" + myRes.getString("Last_Episode_Watched") + "/" +  myRes.getString("Last_Season_Watched") + "/" +  myRes.getString("No_of_Seasons") + ",";
                        
                    }
                    return outputMessage;
//                    break;
                    
                case 5: //case to delete series
                    myStatement.executeUpdate(command);
                    
                    outputMessage = "Deleted Series";
                    return outputMessage;
//                    break;
                
                default:
                    outputMessage = "Error";
                    return outputMessage;
                   
           } 
           
       }catch ( Exception exc){
           exc.printStackTrace();
           return "Error";
       }
    }
}
