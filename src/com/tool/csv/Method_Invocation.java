/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tool.csv;

import java.util.ArrayList;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;


/**
 *
 * @author Waseem Anjum
 */
public class Method_Invocation extends ASTVisitor{
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> Exp = new ArrayList<String>();
   
    
    public ArrayList<String> Return_line()
    {
       
        return name;
    }
     public ArrayList<String> Return_Exp()
    {
       
        return Exp;
    }
    public boolean visit(MethodInvocation node)
    {
            //System.out.println("Invoked method----"+node.getName());
            //System.out.println("Invoking Expression-----"+node.getExpression());
             name.add(node.getName().toString());
            if(node.getExpression()!=null)
            Exp.add(node.getExpression().toString());
            else
            Exp.add("this");
         
             return true;
        
    }
    
}
