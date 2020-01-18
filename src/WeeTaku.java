import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

import java.io.IOException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class WeeTaku {
    static Scanner input = new Scanner(System.in);
    static String userStr;
    static Object seriesData;
    static int userIn;
    static Map<Integer, Runnable> menuCommands = new HashMap<>() ; //HashSt That Executes The Method That The User 
    String Title, Last_Episode_Watched, Last_Season_Watched;
    
/*    private void WeeTaku(String title,String status,String frequency, int last_chapter_read ){
        this.Title = title;
        this.Last Episode Watched = status;
        this.Last Season Watched = frequency;
        this.LastChapter = last_chapter_read;
    }*/
    
    // Method To Clear Terminal In Order For A Clean Display When A Function Is Selected
    public final static void clearTerminal(){
        try{
            final String os = System.getProperty("os.name");
            
            if (os.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            }else{
                Runtime.getRuntime().exec("clear");
            }
        }catch (final IOException e){
            System.out.println("Unkown Command");
        }
    }
    
    //Method That Handles Each SQL Command
    public static void sqlCommands(String command, int task){
       try{
           Connection myCnt = DriverManager.getConnection("jdbc:mysql://localhost:3306/WeeBase", "root", "");
           Statement mySmt = myCnt.createStatement();
           switch(task){
                case 1:
                    mySmt.executeUpdate(command);
                    
                    System.out.println("Added!!");
                    break;
                    
                case 2:
                    mySmt.executeUpdate(command);
                    
                    System.out.println("Changes Made");
                    break;
                    
                case 3:
                    ResultSet mySearch = mySmt.executeQuery(command);

                    while (mySearch.next()){
                     System.out.println(mySearch.getString("Title") + " | " + mySearch.getString("Last_Episode_Watched") + " | " +  mySearch.getString("Last_Season_Watched") + " | " +  mySearch.getString("No_of_Seasons"));
                    }
                    break;
                    
                case 4:
                    ResultSet myRes = mySmt.executeQuery(command);

                    while (myRes.next()){
                     System.out.println(myRes.getString("Title") + " | " + myRes.getString("Last_Episode_Watched") + " | " +  myRes.getString("Last_Season_Watched") + " | " +  myRes.getString("No_of_Seasons"));
                    }
                    break;
                    
                case 5:
                    mySmt.executeUpdate(command);
                    
                    System.out.println("Deleted Series");
                    break;
                
                default:
//                    Menu();
                    System.out.println("Enter Numbers From 1-5");
                   
           }
           
           
       }catch ( Exception exc){
           exc.printStackTrace();
       }
    }
    
    //Method For The Menu Of Weetaku
    public static void Menu(){
        clearTerminal();
        System.out.println("_____________WeeTaku: Menu_____________\n");
        System.out.println("(PLEASE ENTER A NUMBER FROM 1-6)");
        System.out.println("1. Add Series To Libary \n2. Edit Series \n3. Find Series \n4. View All \n5. Delete Series\n6. Exit");
        userIn = input.nextInt();
        
        menuCommands.get(userIn).run();  
    }
    
    //Method To Add A New Series
    public static void addSeries(){
        clearTerminal();
        String title, last_epi, last_sea, num_sea  ;
        System.out.println("_____________Weetaku: Add Series_____________\n");
        title = input.nextLine();
        System.out.print("Enter Series Title: ") ;
        title = input.nextLine();
        System.out.print("Enter The Number of The Last Episode Watched of The Season: ") ;
        last_epi = input.nextLine();
        System.out.print("Enter The Number of The Last Season Watched of The Series: ") ;
        last_sea = input.nextLine();
        System.out.print("Enter The Total Number of Season In Series: ") ;
        num_sea = input.nextLine();
        
        String db_command = String.format("insert into Seriesous (Title, Last_Episode_Watched, Last_Season_Watched, No_of_Seasons) values ('%s', '%s', '%s', '%s')",title, last_epi, last_sea, num_sea);
        sqlCommands(db_command, 1);
    }
    
    
    //Method To Edit A Series
    public static void editSeries(){
        clearTerminal();
        String title, detail = "", changedDetail = "";
        System.out.println("_____________Weetaku: Edit Series_____________\n");
        title = input.nextLine();
        System.out.print("Enter Series Title You Want To Change: ") ;
        title = input.nextLine();
        System.out.println("What Is The Details Of The Series You Would Like To Edit?(PLEASE ENTER A NUMBER FROM 1-4)");
        System.out.println("1. Title\n2. Last Episode Watched\n3. Last Season Watched\n4. Number of Seasons");
        userIn = input.nextInt();
        
        switch(userIn){
            case 1:
                detail = "Title";
                changedDetail = input.nextLine();
                System.out.println("What Would You Like To Change The Title To?: ");
                changedDetail = input.nextLine();
                break;
            case 2:
                detail = "Last_Episode_Watched";
                changedDetail = input.nextLine();
                System.out.println("What Would You Like To Change The Last Episode Watched To?: ");
                changedDetail = input.nextLine();
                break;
            case 3:
                detail = "Last_Season_Watched";
                changedDetail = input.nextLine();
                System.out.println("What Would You Like To Change The Last Season Watched To?: ");
                changedDetail = input.nextLine();
                break;
            case 4:
                detail = "No_of_Seasons";
                changedDetail = input.nextLine();
                System.out.println("What Would You Like To Change The Number of Seasons To?: ");
                changedDetail = input.nextLine();
                break;
            default:
                editSeries();
                System.out.println("PLEASE ENTER A NUMBER FROM 1-4");
                
        }
        
        String db_command = String.format("update Seriesous set %s = '%s' where Title = '%s'",detail, changedDetail, title);
        sqlCommands(db_command, 2);
    }
    
    //Method To Find The Details Of A Series
    public static void findSeries(){
        clearTerminal();
        userStr = input.nextLine();
        System.out.print("Please Enter The Title of The Series You Wish To Find: ");
        userStr = input.nextLine();
        String db_command  = String.format("select * from Seriesous where Title = '%s'", userStr); 
        sqlCommands(db_command, 3);
    }
    
    //Method To View All Series' Details
    public static void viewAllSeries(){
        clearTerminal();
        sqlCommands("select * from Seriesous", 4);
    }
    
    //Method That Allows User To Delete Series
    public static void deleteSeries(){
        clearTerminal();
        userStr = input.nextLine();
        System.out.print("Please Enter The Title of The Series You Wish To Delete: ");
        userStr = input.nextLine();
        String db_command  = String.format("delete from Seriesous where Title = '%s'", userStr); 
        sqlCommands(db_command, 5);
    }
     
    public static void main(String[] args){
        menuCommands.put(1, () -> addSeries());
        menuCommands.put(2, () -> editSeries());
        menuCommands.put(3, () -> findSeries());
        menuCommands.put(4, () -> viewAllSeries());
        menuCommands.put(5, () -> deleteSeries());
        menuCommands.put(6, () -> System.exit(0));
        Menu();
    }
}
