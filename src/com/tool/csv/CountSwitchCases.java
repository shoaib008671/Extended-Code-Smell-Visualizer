/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SwitchCase;

/**
 *
 * @author Madiha
 */
class CountSwitchCases extends ASTVisitor {
    
    private int switchCases = 0;
    
    public int getLoopCount() 
    {
        return switchCases;
    }
    public boolean visit(SwitchCase node) 
    {
        switchCases++;
        return true;
    }
}
