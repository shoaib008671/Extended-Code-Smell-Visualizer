/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SwitchStatement;

/**
 *
 * @author Bilal
 */
public class CountSwitch extends ASTVisitor{
    
    private int switchCounter;
    private int cases;
    
    public int getSwitch()
    {
        return (switchCounter + cases);
    }
    
    public boolean visit(SwitchStatement node)
    {
        switchCounter++;
        CountSwitchCases visitor = new CountSwitchCases();
        node.accept(visitor);
        cases = visitor.getLoopCount();
        return true;
    }
    
}
