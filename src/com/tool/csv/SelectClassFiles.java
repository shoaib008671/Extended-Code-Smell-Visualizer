/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;


/**
 *
 * @author Bilal
 */
public class SelectClassFiles extends ASTVisitor {
    
    private int counter =0;
    private String fieldName="";
    public int getCounter()
    {
        return counter;
    }
    
    public String getFieldName()
    {
        return fieldName;
    }
    
    public boolean visit(FieldDeclaration node)
    {
        String name = node.toString();
        if(!name.equals(null))
        fieldName = fieldName+ name;
        counter++;
        return true;
    }
   
    
}
