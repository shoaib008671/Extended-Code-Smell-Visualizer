/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.DoStatement;

/**
 *
 * @author Bilal
 */
public class CountDoWhile extends ASTVisitor{
    
    private int countDo;
    
    public int getCountDo()
    {
        return countDo;
    }
    
    public boolean visit(DoStatement node)
    {
        countDo++;
        return true;
    }
    
}
