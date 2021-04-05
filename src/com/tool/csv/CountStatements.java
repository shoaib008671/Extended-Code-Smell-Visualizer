/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;

/**
 *
 * @author Bilal
 */
public class CountStatements extends ASTVisitor{
    private int returnCount ;
    private int assignCount ;
    public int getReturnCount()
    {
        return returnCount;
    }
    public boolean visit(ReturnStatement node)
    {
        returnCount++;
        return true;
    }
    
    
}
