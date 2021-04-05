/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;




/**
 *
 * @author Muhammad Shoaib
 */
public class DivergentChangeClassMethods extends ASTVisitor{
    
    private int totalMethods;
    private String nameofclass="";
    private String methodsList="";
    private String methodsList2="";
    
   
   
    
    
    
    
    public boolean visit(MethodDeclaration node)
    {
        totalMethods++;
        String[] method = node.toString().split("\n");
        String temp = method[0];
        String signature = temp.substring(0, temp.indexOf(")")+1);
        String signature2 = temp.substring(0, temp.indexOf(";")+1);
        methodsList = methodsList + signature+"\n";
        methodsList2 = methodsList + signature2+"\n";
        
        
        
         
             
            return true;
        
        
        
    }
   
     public String getclassname()
    {
        return nameofclass;
    }
    public String getMethodsList()
    {
        return methodsList;
    }
    public String getMethodsList2()
    {
        return methodsList2;
    }
    public int getTotalMethods()
    {
        return totalMethods;
    }
    
}
