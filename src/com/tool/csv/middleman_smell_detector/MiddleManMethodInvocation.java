package com.tool.csv.middleman_smell_detector;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;


public class MiddleManMethodInvocation extends ASTVisitor {
	
	private boolean flag = false;
	private ArrayList<String> _globalMethodNameList;
	public MiddleManMethodInvocation(ArrayList<String>
	globalMethodNameList) {
	_globalMethodNameList = globalMethodNameList;
	}
	public boolean getFlag() {
	return flag;
	}
	public boolean visit(MethodInvocation node) {
	if (_globalMethodNameList.contains(node.getName().toString())) {
	flag = true;
	}
	return true;
	}

}
