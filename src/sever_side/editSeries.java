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
public class editSeries {
    
    public static void editSeries(String title, String detail, String changedDetail){    
        //command that is sent to method SQL_Commands    
        String db_command = String.format("update Seriesous set %s = '%s' where Title = '%s'",detail, changedDetail, title);
        SQL_Commands(db_command, 2); //Sending Command to SQL_Commands
        
    }
}
