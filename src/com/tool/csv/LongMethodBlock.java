/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ReturnStatement;

/**
 *
 * @author Madiha
 */
class LongMethodBlock extends ASTVisitor {
    
    private int numberOfStatements;
    private int counter;
    private boolean flag=false;
    private int noOfSetterSt;
    public int getNumberOfStatements() 
    {
        return numberOfStatements;
    }
    public int getCounter()
    {
        return counter;
    }
    public boolean getFlag()
    {
        return flag;
    }
    public int getSetterSt()
    {
        return noOfSetterSt;
    }
    public boolean visit(Block node) 
    {
        CountStatements visitor1 = new CountStatements();
        node.accept(visitor1);
        
        if(node.statements().size() == 1 && visitor1.getReturnCount()==1)
        {
            flag =true;
        }
        CountAssignmentStatement visitor2 = new CountAssignmentStatement();
        node.accept(visitor2);
        LongMethodBlock visitor = new LongMethodBlock();
        if(node.statements().size()==visitor2.getcount())
        {
            noOfSetterSt = node.statements().size();
        }
        
        int temp = visitor.getNumberOfStatements();
        numberOfStatements = node.statements().size() + temp;
        
        
        return true;
    }

}
