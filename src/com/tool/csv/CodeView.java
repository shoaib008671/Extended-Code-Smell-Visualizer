/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import java.awt.Color;
import java.awt.Font;
//import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.Console;
import java.io.File;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
//import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
//import javax.tools.FileObject;
import org.apache.commons.io.FileUtils;
//import org.eclipse.core.internal.utils.FileUtil;
//import sun.net.www.content.text.plain;

/**
 *
 * @author Bilal
 */
public class CodeView extends javax.swing.JDialog {

    /**
     * Creates new form CodeView
     */
    public ArrayList<String> active_Smells = new ArrayList<String>();
    public CodeView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.visualizeCode();
    }
    public CodeView(){}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void visualizeCode()
    {   
        jLabel_title.setText(MainFrame.v_data.get(0));
        this.setTitle(MainFrame.v_data.get(0)+" Smell Visualization");
        for(int i=1;i<MainFrame.v_data.size();i++)
        {
            jTextArea_code.append(MainFrame.v_data.get(i));
        }
        
        //jTextArea_code.setText(MainFrame.v_data);
        jTextArea_code.setFont(new Font("Tahoma",Font.PLAIN , 24));
        if(MainFrame.active_smell == 1)
        {
            jTextArea_code.setForeground(Color.red);
            jButton1.setEnabled(false);
        }
        else if(MainFrame.active_smell == 2)
        {
            jTextArea_code.setForeground(Color.blue);
            jButton1.setEnabled(false);
        }
        else if(MainFrame.active_smell == 7)
        {
            jTextArea_code.setForeground(Color.green);
            jButton1.setEnabled(false);
        }
        else if(MainFrame.active_smell == 4)
        {
            jTextArea_code.setForeground(Color.magenta);
            jButton1.setEnabled(false);
        }
        else if(MainFrame.active_smell == 5)
        {
            jTextArea_code.setForeground(Color.orange);
            jButton1.setEnabled(false);
        }
        else if(MainFrame.active_smell == 6)
        {
            jTextArea_code.setForeground(Color.cyan);
            jButton1.setEnabled(false);
        }
        else if(MainFrame.active_smell == 3)
        {
            jTextArea_code.setForeground(Color.yellow);
            jButton1.setEnabled(false);
        }
        else if(MainFrame.active_smell == 8)
        {
            jTextArea_code.setForeground(Color.gray);
            jButton1.setEnabled(false);
        }
       
        else if (MainFrame.active_smell == 11){
               jTextArea_code.setForeground(Color.red);
        }
        
         else if (MainFrame.active_smell == 9){
               jTextArea_code.setForeground(Color.ORANGE);
               jButton1.setEnabled(false);
               
        }
         else if (MainFrame.active_smell == 12){
               jTextArea_code.setForeground(Color.gray);
               jButton1.setEnabled(false);
               
        }
        jTextArea2.setText(MainFrame.longParaSuggestion);
        jTextArea2.setFont(new Font("Tahoma",Font.PLAIN , 12));
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_title = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_code = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel_title.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_title.setText("Long Parameter List");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_title, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_title)
                .addGap(20, 20, 20))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Visualized Code", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jTextArea_code.setEditable(false);
        jTextArea_code.setColumns(20);
        jTextArea_code.setRows(5);
        jScrollPane1.setViewportView(jTextArea_code);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Refactoring Suggestions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 51, 51));
        jButton1.setText("ReFactor Code");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
       if (MainFrame.active_smell==11){
           try {
               String value = jTextArea_code.getText();
               String classvalue ="";
               String[] splitedvalues = value.split("\n");
               String filePath = "";
               for (int i=0;i<splitedvalues.length;i++){
                   if (splitedvalues[i].endsWith(".java")){
                       filePath= splitedvalues[i];
                   }
               }
               filePath = filePath.replace("File Path: ", "");
               filePath.trim();
               int index= filePath.lastIndexOf("src");
               String dirPath = filePath.substring(0, index);
               dirPath = dirPath.trim();
               String folder = JOptionPane.showInputDialog("Enter the Folder Name:");
               if (folder ==null)
               {
               
                   return;
               }
               else
               {
              
               
                    dirPath = dirPath+ folder;
                    String filename = "\\Paths Of All Refactored Files.txt";

                    String fileToCreate = dirPath+filename;
                    File file = new File(dirPath);
                    if (!file.exists()) {
                        if (file.mkdir()) {
                            JOptionPane.showMessageDialog(null, folder+ " has been Created in Path "+ dirPath);
                                 File sourceFile = new File(filePath);
                                 File desDirectory = new File (dirPath);
                                 FileUtils.moveFileToDirectory(sourceFile, desDirectory, true);
                                 JOptionPane.showMessageDialog(null, "File Moved");
                                 File f = new File(fileToCreate);
                                 if (f.createNewFile()){
                                     BufferedWriter writer = null;
                                 try
                                 {
                                     writer = new BufferedWriter( new FileWriter( fileToCreate));
                                     writer.write(filePath);

                                 }
                                 catch ( IOException e)
                                 {
                                 }
                                 finally
                                 {
                                     try
                                     {
                                         if ( writer != null)
                                         writer.close( );
                                         JOptionPane.showMessageDialog(null, "File Written");
                                     }
                                     catch ( IOException e)
                                     {
                                     }
                                 }

                                 }
                                 else{

                                     JOptionPane.showMessageDialog(null, "Error");
                                 }

                        } else {

                        }
                    }else {
                      JOptionPane.showMessageDialog(null, "File Already Exists");
                      JFileChooser fileChooser = new JFileChooser();
                      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                      int result = fileChooser.showOpenDialog(this);
                      if (result == JFileChooser.APPROVE_OPTION) {
                          JOptionPane.showMessageDialog(null, "File Already Exist Please Select the directory");
                          File selectedFile = fileChooser.getSelectedFile();
                          String filepath= selectedFile.getAbsolutePath();
                          String filepathcomplete = filepath + filename;
                          Writer output;
                          output = new BufferedWriter(new FileWriter(filepathcomplete, true));
                          output.append("%s\r\n\n\n"+ filePath);
                          output.close();
                           File sourceFile = new File(filePath);
                           File desDirectory = new File (filepath);
                           FileUtils.moveFileToDirectory(sourceFile, desDirectory, true);
                           JOptionPane.showMessageDialog(null, "File Moved && Path Written");
     }
                   }

                }} catch (IOException ex) {
                    Logger.getLogger(CodeView.class.getName()).log(Level.SEVERE, null, ex);
                }



            }
     
       
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CodeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CodeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CodeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CodeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CodeView dialog = new CodeView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel_title;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea_code;
    // End of variables declaration//GEN-END:variables
}