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
public class deleteSeries {
    
    public static void deleteSeries(String title){
        //command that is sent to method SQL_Commands
        String db_command  = String.format("delete from Seriesous where Title = '%s'", title); 
        SQL_Commands(db_command, 5); //Sending Command to SQL_Commands
    }
    
}
