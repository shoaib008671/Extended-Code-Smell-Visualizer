/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;

/**
 *
 * @author Bilal
 */
public class CountIf extends ASTVisitor{
    
    private int ifCounter;
    private int elseCounter;
    private int elseIfCounter;
    private int total;
    public int getIfCount()
    {
        return ifCounter;
    }
    
    public int getElseCount()
    {
        return elseCounter;
    }
    
    public int getElseIfCount()
    {
        return elseIfCounter;
    }
    
    public int getTotal()
    {
        total = ifCounter +   (elseCounter - elseIfCounter);
        return total;
    }
    
    public boolean visit(IfStatement node)
    {
        ifCounter++;
            if(node.getElseStatement()!=null)
            {
                elseCounter++;
               
                if(node.getElseStatement().toString().startsWith("if"))
                {
                    /////at the end return else_count - elseif
                    elseIfCounter++;  
                }
                
            }
        return true;
    }
    
}
