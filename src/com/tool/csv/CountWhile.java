/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.WhileStatement;

/**
 *
 * @author Bilal
 */
public class CountWhile extends ASTVisitor{
    
    private int whileCount;
    
    public int getWhileCount()
    {
        return whileCount;
    }
    
    public boolean visit(WhileStatement node)
    {
        whileCount++;
        return true;
    }
    
}
