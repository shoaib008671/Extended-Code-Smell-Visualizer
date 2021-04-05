/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tool.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Waseem Anjum
 */
public class Comments_HC {
    
    public Comments_HC(){
       
    }
    
 public String[] Comments_counter(String file_names , int low_imp) throws FileNotFoundException{
            int counter = 0;
            int block = 0;
            int LC= 0;
            int source_files=0;
            int line_number=0;
            String coments="";
            String pkg="" ;
            String clas = file_names.substring(file_names.lastIndexOf("\\")+1);
            String cols[] = new String[7];
            File files = new File(file_names);
            Scanner sc = new Scanner(files);
            String mth=null;
            boolean flag = true;
            while(sc.hasNext())
            {
                String line = sc.nextLine();
                source_files++;
                if(line.contains("//"))
                {
                    coments =coments + source_files+"  "+line+"\n";
                    counter++;
                    LC++;
                    
                }
               if(line.contains("package")  &&  flag == true){
            
                   pkg = line;//.substring(line.lastIndexOf(" "));
                     pkg=pkg.substring(pkg.lastIndexOf(" ")+1, pkg.lastIndexOf(";"));
                   flag=false;
               }
               if(line.contains("/*")){
                   counter++;
                   block++;
                    coments =coments + source_files+"  "+line+"\n";
                   while(!line.contains("*/")){
                   counter++;
                   block++;
                   line=sc.nextLine();
                   source_files++;
                    coments =coments + source_files+"  "+line+"\n";
                   }               
               }
               
            }
            
            int per = (counter*100)/source_files;
           if(per>low_imp){
            cols[0]=pkg.toString();
            cols[1]=clas;
            cols[2]=""+counter;
            cols[3]=""+LC;
            cols[4]=""+block;
            cols[5]=""+source_files;
            cols[6]=""+coments;
            }
           else
               cols=null;
            
           
            return cols;
 }   
 
 public String[] CommentsCounter(String file_names) throws FileNotFoundException{
            int counter = 0;
            int block = 0;
            int LC= 0;
            int source_files=0;
            int line_number=0;
            String coments="";
            String pkg="" ;
            String clas = file_names.substring(file_names.lastIndexOf("\\")+1);
            String cols[] = new String[7];
            File files = new File(file_names);
            Scanner sc = new Scanner(files);
            String mth=null;
            boolean flag = true;
            while(sc.hasNext())
            {
                String line = sc.nextLine();
                source_files++;
                if(line.contains("//"))
                {
                    coments =coments + source_files+"  "+line+"\n";
                    counter++;
                    LC++;
                    
                }
               if(line.contains("package")  &&  flag == true){
            
                   pkg = line;//.substring(line.lastIndexOf(" "));
                     pkg=pkg.substring(pkg.lastIndexOf(" ")+1, pkg.lastIndexOf(";"));
                   flag=false;
               }
               if(line.contains("/*")){
                   counter++;
                   block++;
                    coments =coments + source_files+"  "+line+"\n";
                   while(!line.contains("*/")){
                   counter++;
                   block++;
                   line=sc.nextLine();
                   source_files++;
                    coments =coments + source_files+"  "+line+"\n";
                   }               
               }
               
            }
            
            int per = (counter*100)/source_files;
           if(per>18){
            cols[0]=pkg.toString();
            cols[1]=clas;
            cols[2]=""+counter;
            cols[3]=""+LC;
            cols[4]=""+block;
            cols[5]=""+source_files;
            cols[6]=""+coments;
            }
           else
               cols=null;
            
           
            return cols;
 }   
    
    
}
