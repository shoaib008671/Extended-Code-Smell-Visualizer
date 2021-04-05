package com.tool.csv.middleman_smell_detector;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;

public class MiddleManBlock extends ASTVisitor{
	
	private ArrayList<String> _globalMethodNameList;
	private boolean flag;
	public MiddleManBlock(ArrayList<String> globalMethodNameList) {
	_globalMethodNameList = globalMethodNameList;
	}
	public boolean getFlag() {
	return flag;
	}
	public boolean visit(Block node) {
		int numberOfStatements = node.statements().size();
		if (numberOfStatements == 1) {
		MiddleManReturnStatement visitor = new
		MiddleManReturnStatement(
		_globalMethodNameList);
		node.accept(visitor);
		flag = visitor.getFlag();
		}
		return true;
		}
	
	

}
