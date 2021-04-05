/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import java.util.ArrayList;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SwitchStatement;

/**
 *
 * @author Madiha
 */
class CountSwitchStatement extends ASTVisitor {
    
    private ArrayList<String> message = new ArrayList<String>();
    private ArrayList<Integer> counter = new ArrayList<Integer>();
    private ArrayList<String> switchBody = new ArrayList<String>();
    
    public boolean visit(SwitchStatement node) 
    {
        SimpleNameLister visitor1 = new SimpleNameLister();
        node.accept(visitor1);
        CountSwitchCases visitor = new CountSwitchCases();
        node.accept(visitor);
       
        switchBody.add(node.toString());
            message.add(visitor1.getSimpleName());
            counter.add(visitor.getLoopCount());
        
        return true;
    }
    public ArrayList<String> getMessage() 
    {
        return message;
    }
    public ArrayList<Integer> getCounter() 
    {
        return counter;
    }
    public ArrayList<String> getSwitchBody()
    {
        return switchBody;
    }
            
}
