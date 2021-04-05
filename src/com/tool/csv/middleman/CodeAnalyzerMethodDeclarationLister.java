package com.tool.csv.middleman;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class CodeAnalyzerMethodDeclarationLister  extends ASTVisitor{
	
	public ArrayList<String> globalMethodNameList = new
			ArrayList<String>();
			public ArrayList<String> getGlobalMethodNameList() {
			return globalMethodNameList;
			}
			public boolean visit(MethodDeclaration node) {
			globalMethodNameList.add(node.getName().toString());
			return true;
			}

}
