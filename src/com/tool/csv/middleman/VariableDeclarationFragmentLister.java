package com.tool.csv.middleman;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class VariableDeclarationFragmentLister extends ASTVisitor{
	
	String globalVariable;
	public String getGlobalVariable() {
	return globalVariable;
	}
	public boolean visit(VariableDeclarationFragment node) {
	globalVariable = node.getName().toString();
	return true;
	}
	
	}
