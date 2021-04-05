/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import java.util.ArrayList;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 *
 * @author Bilal
 */
public class LazyClassSmellDetector extends ASTVisitor{
    private int totalMethods;
    private int WeightPerMethod;
    private ArrayList<Integer> MethodsComplexity = new ArrayList<>();
    public int getTotalMethods()
    {
        return totalMethods;
    }
    public int getWeightMethod()
    {
        return WeightPerMethod;
    }
    public ArrayList<Integer> getMethodsComplexity()
    {
        return MethodsComplexity;
    }
    
    public boolean visit(MethodDeclaration node)
    {
        totalMethods++; //total methods of a class
        CountIf ifVisitor = new CountIf(); //visit if else
        node.accept(ifVisitor);
        int ifConditions = ifVisitor.getTotal();
        CountFor forVisitor = new CountFor(); //visit for statements
        node.accept(forVisitor);
        int forConditions = forVisitor.getForCount();
        CountWhile whileVisitor = new CountWhile(); //visit while statements
        node.accept(whileVisitor);
        int whileConditions = whileVisitor.getWhileCount();
        CountDoWhile doWhileVisitor = new CountDoWhile(); //visit do while statements
        node.accept(doWhileVisitor);
        int doConditions = doWhileVisitor.getCountDo();
        CountSwitch switchVisitor = new CountSwitch(); //visit switch statements
        node.accept(switchVisitor);
        int switchConditions = switchVisitor.getSwitch();
        int comp = 0 ;
        comp = ifConditions + forConditions + whileConditions + doConditions + switchConditions +1 ;
        MethodsComplexity.add(comp);
        WeightPerMethod = WeightPerMethod + comp;
        return true;
    }
    
}
