/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;

/**
 *
 * @author Bilal
 */
public class CountAssignmentStatement extends ASTVisitor{
    private int countAssig=0;
    public int getcount()
    {
        return countAssig;
    }
    public boolean visit(Assignment node)
    {
        countAssig++;
        return true;
    }
    
}
