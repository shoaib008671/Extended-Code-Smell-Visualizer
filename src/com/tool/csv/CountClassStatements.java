/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;

/**
 *
 * @author Bilal
 */
public class CountClassStatements extends ASTVisitor{
    
    private int line =0;
    private String[] field = null;
    public int getLine()
    {
        return line;
    }
    public String[] getfield()
    {
        return field;
    }
    
    public boolean visit(Class node)
    {
        field = node.getFields().toString().split(",");
        return true;
    }
}
