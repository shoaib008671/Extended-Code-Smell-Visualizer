/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 *
 * @author Madiha
 */
class LongParameterListMethodDeclaration extends ASTVisitor {
    
    private ArrayList<Integer> totalparameters = new ArrayList<Integer>();
    private ArrayList<String> methods = new ArrayList<String>();
    private ArrayList<String> parameters = new ArrayList<String>();
    private ArrayList<String> methodSig = new ArrayList<String>();

    public ArrayList<String> getmethod() 
    {
        return methods;
    }
    public ArrayList<String> getParameters() 
    {
        return parameters;
    }
    public ArrayList<Integer> getTotalpara() 
    {
        return totalparameters;
    }   
    public ArrayList<String> getMethodSig()
    {
        return methodSig;
    }
    public boolean visit(MethodDeclaration node) 
    {
        String methodName = node.getName().toString();
        methods.add(methodName);
        parameters.add(node.parameters().toString());
        totalparameters.add(node.parameters().size());
        String[] lines = node.toString().split("\n");
        String signature ="";
        for (String temp : lines) {

        if (temp.contains(methodName) && !temp.startsWith("/") && temp.contains("(") && temp.contains(")")) {
                signature = temp.substring(0, temp.indexOf(")")+1);
            }
        }
        methodSig.add(signature);
        return true;
    }
    
}
