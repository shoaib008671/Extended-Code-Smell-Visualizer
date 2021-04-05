package com.tool.csv.middleman_smell_detector;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ReturnStatement;


public class MiddleManReturnStatement extends ASTVisitor{
	
	private boolean flag;
	private ArrayList<String> _globalMethodNameList;
	public MiddleManReturnStatement(ArrayList<String> globalMethodNameList)
	{
	_globalMethodNameList = globalMethodNameList;
	}
	public boolean getFlag() {
	return flag;
	}
	
	public boolean visit(ReturnStatement node) {
		MiddleManMethodInvocation visitor = new
		MiddleManMethodInvocation(
		_globalMethodNameList);
		node.accept(visitor);
		flag = visitor.getFlag();
		return true;
		}

}
