/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ConditionalExpression;

/**
 *
 * @author Bilal
 */
public class CountConditions extends ASTVisitor{
    int count;
    
    public int getCount()
    {
        return count;
    }
    public boolean visit(ConditionalExpression node)
    {
        
        count++;
        return true;
    }
    
}
