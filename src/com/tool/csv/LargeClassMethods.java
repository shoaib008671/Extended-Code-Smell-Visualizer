/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 *
 * @author Bilal
 */
public class LargeClassMethods extends ASTVisitor{
    
    private int totalMethods;
    private String nameofclass="";
    private String methodsList="";
    public boolean visit(MethodDeclaration node)
    {
        totalMethods++;
        String[] method = node.toString().split("\n");
        String temp = method[0];
        String signature = temp.substring(0, temp.indexOf(")")+1);
        
        methodsList = methodsList + signature+"\n";
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
    
    public int getTotalMethods()
    {
        return totalMethods;
    }
}
