/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;

/**
 *
 * @author Muhammad
 */
public class methods_instance extends ASTVisitor{
    public int total=0;
    public String name;
    ArrayList<String> methods = new ArrayList<String>();
    @Override
    public boolean visit(ClassInstanceCreation node)
    {
        total++;
        methods.add(node.getType().toString());
        
        return true;
    }
    public void getname(Class node){
         name = node.getName();
   }
    public int get_total()
            
    {
        return total;
    }
   
    
     public ArrayList<String> match_this(ArrayList<String> all_files , String file)
    {
        int count = 0;
        ArrayList<String> others = new ArrayList<String>();
        for(int i=0;i<all_files.size();i++){
         String clas_name = all_files.get(i).substring(all_files.get(i).lastIndexOf("\\")+1, all_files.get(i).lastIndexOf(".java"));
         String main_class = file.substring(file.lastIndexOf("\\")+1, file.lastIndexOf(".java"));
          for(String met:methods)
          {
              if(clas_name.equals(met) &&! clas_name.equals(main_class))
              {
                  count++;
                  others.add(met);
              }
          }
        }
        return others;
    }
}

    

