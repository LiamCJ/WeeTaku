/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sever_side;

import static sever_side.SQL_Commands.SQL_Commands;

/**
 *
 * @author liam
 */
public class addSeries {
    
    
    public static String[] addSeries (String title, String last_epi, String last_sea, String num_sea){
        String[] output = new String[2];
        String db_command;
        //command that is sent to method SQL_Commands
        db_command = String.format("insert into Seriesous (Title, Last_Episode_Watched, Last_Season_Watched, No_of_Seasons) values ('%s', '%s', '%s', '%s')",title, last_epi, last_sea, num_sea);
        
        // error handling conditional statement
        if(title.contains("'")){ // if the title contains an apostrophy  the output string will be changed to an appropiate statement
            output[0] = "Error";
            output[1] = "Invalid Character. Please remove any Apostrophes( ' ) from the title";
        }
        else{
            output[0] = "Succes";
            output[1] = SQL_Commands(db_command, 1); //Sending Command to SQL_Commands;
        } 
        
        return output;
    }
    
}
/*
add error handling where:
check if SQL_Commands returns Error if so check all types of possible errors 
e.g if there is a ' or " in the title tell the user to remove it 
if there is a title that already exists, tell them so that they can make a alternate title
if there 
*/