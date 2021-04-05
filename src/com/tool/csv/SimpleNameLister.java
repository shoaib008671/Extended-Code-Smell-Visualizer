/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;

import java.util.ArrayList;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;

/**
 *
 * @author Madiha
 */
class SimpleNameLister extends ASTVisitor {
    
    ArrayList<String> name = new ArrayList<String>();
    
    public String getSimpleName() 
    {	
        return name.get(0);
    }
    public boolean visit(SimpleName node) 
    {	
        name.add(node.getIdentifier().toString());
        return true;
    }
}
