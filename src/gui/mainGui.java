/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/*
 *
 * @author liam
 *
 */

import static sever_side.addSeries.addSeries;
import static sever_side.viewAllSeries.viewAllSeries;
import static sever_side.findSeries.findSeries;
import static sever_side.editSeries.editSeries;
import static sever_side.deleteSeries.deleteSeries;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;

public class mainGui {

    public static void main(String[] args){
        
        // create JFrame and JTable
        JFrame frame = new JFrame();
        JTable table = new JTable(); 
        
        // create a table model and set a Column Identifiers to this model 
        Object[] columns = {"Title", "Last Episode", "Last Season", "No. of Seasons"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        // set the model to the table
        table.setModel(model);
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        frame.setBackground(Color.DARK_GRAY);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        
        //create JLabel
        JLabel titleLabel = new JLabel("Enter Title of Series");
        JLabel epiLabel = new JLabel("Enter The Last Episode You Watched");
        JLabel sznLabel = new JLabel("Enter The Last Season You Watched");
        JLabel sumSznLabel = new JLabel("Enter The Total Number of Seasons");
        JLabel outputMessage = new JLabel("");
        
        // create JTextFields
        JTextField title = new JTextField();
        JSpinner currentEpi = new JSpinner();
        JSpinner currentSzn = new JSpinner();
        JSpinner sumSzns = new JSpinner();
        
        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");  

        // aligining JLabels in position
        titleLabel.setBounds(20, 220, 254, 25);
        epiLabel.setBounds(20, 250, 280, 25);
        sznLabel.setBounds(20, 280, 280, 25);
        sumSznLabel.setBounds(20, 310, 254, 25);
        outputMessage.setBounds(20, 340, 800, 25);
        
        //aliginig JTextFields in postion
        title.setBounds(300, 220, 300, 25);
        currentEpi.setBounds(300, 250, 100, 25);
        currentSzn.setBounds(300, 280, 100, 25);
        sumSzns.setBounds(300, 310, 100, 25);
        
        //aliginig JButtons in position
        btnAdd.setBounds(600, 220, 100, 25);
        btnUpdate.setBounds(600, 250, 100, 25);
        btnDelete.setBounds(600, 280, 100, 25);
        
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        
        frame.setLayout(null);
        
        frame.add(pane);
        
        //add JLabels to jFrame
        frame.add(titleLabel);
        frame.add(epiLabel);
        frame.add(sznLabel);
        frame.add(sumSznLabel);
        frame.add(outputMessage);
        
        // add JTextFields to the jframe
        frame.add(title);
        frame.add(currentEpi);
        frame.add(currentSzn);
        frame.add(sumSzns);
    
        // add JButtons to the jframe
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);
        
        // create an array of objects to set the row data
        Object[] row = new Object[4];
        
        //View All
        String allSeries = viewAllSeries(); 
        String[] seriesInfo = allSeries.split(",");
        String[][] eachSeries = new String[seriesInfo.length][4];

        for(int i = 0; i < eachSeries.length; i++){
            eachSeries[i] = seriesInfo[i].split("/");
        }

        for(String[] seriesDisp: eachSeries){
            model.addRow(seriesDisp);
        }

        
        // button add row
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                String addTitle = title.getText();
                Object crntSzn = currentSzn.getValue(), crntEpi= currentEpi.getValue() , addSumSzn = sumSzns.getValue();
                String[] result = addSeries(addTitle, crntEpi.toString(), crntSzn.toString(), addSumSzn.toString() );
                
                if(result[0] == "Error"){
                    outputMessage.setText(result[1]);
                }else{
                    outputMessage.setText(result[1]);
                    
                    row[0] = title.getText();
                    row[1] = currentEpi.getValue();
                    row[2] = currentSzn.getValue();
                    row[3] = sumSzns.getValue();

                    // add row to the model
                    model.addRow(row);
                }
                
            }
        });
        
        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    deleteSeries(model.getValueAt(i, 0).toString());
                    model.removeRow(i);
                }
                else{
                    outputMessage.setText("Please Select A Series");
                }
            }
        });
        
        // get selected row data From table to textfields 
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            // i = the index of the selected row
            int i = table.getSelectedRow();
            
            title.setText(model.getValueAt(i, 0).toString());
            currentEpi.setValue(Integer.parseInt((String) model.getValueAt(i, 1)));
            currentSzn.setValue(Integer.parseInt((String) model.getValueAt(i, 2)));
            sumSzns.setValue(Integer.parseInt((String) model.getValueAt(i, 3)));
        }
        });
        
        // button update row
        btnUpdate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                // i = the index of the selected row
                int i = table.getSelectedRow();
                
                if(i >= 0) 
                {
                   model.setValueAt(title.getText(), i, 0);
                   model.setValueAt(currentEpi.getValue(), i, 1);
                   model.setValueAt(currentSzn.getValue(), i, 2);
                   model.setValueAt(sumSzns.getValue(), i, 3);
                }
                else{
                    outputMessage.setText("Please Select A Series");
                }
            }
        });
        
        frame.setSize(880,420);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBackground(Color.DARK_GRAY);
        
    }
}