package com.tool.csv.middleman;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;

public class FieldDeclarationLister extends ASTVisitor{
	
	private ArrayList<String> globalVariableList = new ArrayList<String>();
	public ArrayList<String> getGlobalVariableList() {
	return globalVariableList;
	}
	
	public boolean visit(FieldDeclaration node) {
		VariableDeclarationFragmentLister visitor = new
		VariableDeclarationFragmentLister();
		node.accept(visitor);
		globalVariableList.add(visitor.getGlobalVariable());
		return true;
		}

}
