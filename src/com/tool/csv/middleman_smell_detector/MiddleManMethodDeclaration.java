package com.tool.csv.middleman_smell_detector;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
public class MiddleManMethodDeclaration  extends ASTVisitor {
	
	private ArrayList<String> _globalMethodNameList;
	private boolean flag;
	private String smell;
	public MiddleManMethodDeclaration(ArrayList<String>
	globalMethodNameList) {
	_globalMethodNameList = globalMethodNameList;
	}
	public String getSmell() {
	return smell;
	}
	public boolean visit(MethodDeclaration node) {
		MiddleManBlock visitor = new
		MiddleManBlock(_globalMethodNameList);
		node.accept(visitor);
		flag = visitor.getFlag();
		if (flag == true) {
		smell = "Method Name: " + node.getName().toString();
		// System.out.println(node.getName().toString() +
		// " method consistes of a Middle Chain Bad Smell");
		}
		return true;
	}

}
