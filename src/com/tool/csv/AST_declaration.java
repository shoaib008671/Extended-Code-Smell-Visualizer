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
 * @author Madiha
 */
class AST_declaration extends ASTVisitor {
    public int total_methods=0;
    private String method;
    private ArrayList<String> longMethodBody = new ArrayList<>();
    private ArrayList<String> brainMethodBody = new ArrayList<>();
    private ArrayList<String> Method_List = new ArrayList<>();
   
    public int getTotal_methods() 
    {
        return total_methods;
    }
    
    public ArrayList<String> get_method_list()
    {
        return Method_List;
    }
    public String getmethod() 
    {
        return method;
    }
   
    

    public boolean visit(MethodDeclaration node) {
        
        method=node.getName().toString();
        Method_List.add(method);
        total_methods++;
        return true;
    }
    

}
