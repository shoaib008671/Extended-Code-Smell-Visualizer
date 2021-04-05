/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Type;

/**
 *
 * @author Muhammad
 */
public class BrainMethodDeclaration extends ASTVisitor {
    private ArrayList<Integer> lines = new ArrayList<>();
    private String smell;
    private ArrayList<String> methods = new ArrayList<>();
    private int numberOfStatements;
    private ArrayList<String> brainMethodBody = new ArrayList<>();
    private int getter;
    private int setter;
    private int totalMethod;
    private int constructor;
    
    public String getSmell() 
    {
        return smell;
    }
    public ArrayList<String> getmethod() 
    {
        return methods;
    }
    public ArrayList<Integer> getlines()
    {
        return lines;
    }
    public ArrayList<String> getMethodBody()
    {
        return brainMethodBody;
    }
    
    public int getGetter()
    {
        return getter;
    }
    
    public int getSetter()
    {
        return setter;
    }
    public int getTotalMethods()
    {
        return totalMethod;
    }
    public int getConstructor()
    {
        return constructor;
    }

    private static int d ;
    public boolean visit(MethodDeclaration node) {
        totalMethod++;
        
        String[] methodBody = null;
        methodBody = node.toString().split("\n");
        numberOfStatements = methodBody.length - 2;
        String method=node.getName().toString();
     //   System.out.println("method...."+method);
        int para = node.parameters().size();
        String returnType ="";
        try{
            returnType = node.getReturnType2().toString();
        
        }catch(Exception e){
            returnType = "nothing";
        }
        if(method.startsWith("get") && para==0)
        {
            brainMethodBlock visitor = new brainMethodBlock();
            node.accept(visitor);
            if(visitor.getFlag())
            getter++;
        }
        else if(method.startsWith("set") && returnType.equals("void"))
        {
            brainMethodBlock visitor = new brainMethodBlock();
            node.accept(visitor);
            if(para==visitor.getSetterSt())
            {
                setter++;
            }
        }
        else if(returnType.equals("nothing"))
        {
            constructor++;
        }
        methods.add(method);
        brainMethodBody.add(node.toString());
        lines.add(numberOfStatements);
        smell = "Method Name: " + node.getName().toString()+ " Number of Lines: " +numberOfStatements;
        return true;
    }
    
}
