/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 *
 * @author Muhammad Shoaib
 */
public class s_Surgery extends ASTVisitor{
    int low_ins;
    int low_m;
    int ex_fields=0;
    int total_eny=0;
    int total_ins=0;
    int totalMethod=0;
    ArrayList<String> Files= new ArrayList<String>();
    ArrayList<String> other_ins= new ArrayList<>();
    ArrayList<String> other_methods= new ArrayList<>();
        ArrayList<String> calling_methods= new ArrayList<>();
        
        ArrayList<String> changing_methods= new ArrayList<>();
        ArrayList<String> changing_classes= new ArrayList<>();
        
    String current_file = null;
    CompilationUnit cu;
     ArrayList<String> list_all_methods=new ArrayList<>();
     ArrayList<String> this_list = new ArrayList<>();
     ArrayList<String> inv_list = new ArrayList<>();
     ArrayList<String> exp_list = new ArrayList<>();
     ArrayList<String[]> temp_arr = new ArrayList<>();
     String this_method=null;
     
    public void accept_data(ArrayList<String> all_files , String Current_file , ArrayList<String> all_m,int var,int m)
    {
        Files = all_files;
        current_file =  Current_file;
        list_all_methods=all_m;
        low_ins = var;
        low_m = m;
    }
    
   
    @Override
    public boolean visit(MethodDeclaration node)
    {
          // this_list.add(node.getName().toString());
            totalMethod++;
         //  defined_methods = this_method.match_this(Files,current_file);
            this_method=node.getName().toString();
            Instance_creation ic = new Instance_creation();
            node.accept(ic);
            total_ins=ic.get_total();
            other_ins = ic.match_this(Files,current_file);
            Method_Invocation mi = new Method_Invocation();
            node.accept(mi);
            inv_list=mi.Return_line();
            exp_list=mi.Return_Exp();
            for(int i=0;i<inv_list.size();i++){
            for(int j=0;j<list_all_methods.size();j++)
            {
                if(list_all_methods.get(j).equals(inv_list.get(i)) && exp_list.get(i)!=null  )
                {if (!exp_list.get(i).equals("this")) {
                            other_methods.add(inv_list.get(i));
                            calling_methods=exp_list;
                            
                            changing_methods=other_methods;
                            changing_classes=calling_methods;
                            
                            break;
                    }
                }
            }
           
            }
             if(changing_methods.size()>=low_m && changing_classes.size()>=low_ins)
            {
                String str1="\n>>>>Instances in Class<<<< \n";
                String str2="\n>>>>Calling Methods<<<< \n";
                String[] temp = new String[7];
                package_get pg = new package_get();
                try {
                    temp[0] = pg.get_pkg(current_file);
                    temp[1] = pg.get_clas(current_file);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(s_Surgery.class.getName()).log(Level.SEVERE, null, ex);
                }
                temp[2] = this_method;
                temp[3] = ""+changing_methods.size();
                temp[4] = ""+changing_classes.size();
                for(String s:other_ins)
                    str1 = str1+s+"\n";
                temp[5] = str1;
                for(String a:other_methods)
                    str2 = str2+a+"\n";
                temp[6]= str2;
                temp_arr.add(temp);
                other_methods.clear();
                other_ins.clear();
                temp=null;
                
            }
             else
             {
                other_methods.clear();
                other_ins.clear();
                changing_methods.clear();
                changing_classes.clear();
                        
             }
            return true;
    }
    
    
    
    
    public  ArrayList<String[]> get_total_ssurgery()
    {
        
        return temp_arr;
    }
     public int getTotalMethods()
    {
        return totalMethod;
    }
}
