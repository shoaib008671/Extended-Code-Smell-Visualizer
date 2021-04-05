/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ForStatement;

/**
 *
 * @author Bilal
 */
public class CountFor extends ASTVisitor{
    
    private int forCount;
    
    public int getForCount()
    {
        return forCount;
    }
    
    public boolean visit(ForStatement node)
    {
        forCount++;
        return true;
    }
}
