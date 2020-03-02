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
public class viewAllSeries {
    
    public static String viewAllSeries(){
        //command that is sent to method SQL_Commands
        String output = SQL_Commands("select * from Seriesous", 4); //Sending Command to SQL_Commands
        return output;
    }
    
}
