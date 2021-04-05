package com.tool.csv.middleman;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;


//import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
//import com.tool.csv.middleman.CodeAnalyzerMethodDeclarationLister;
//import com.tool.csv.middleman.FieldDeclarationLister;
import com.tool.csv.middleman_smell_detector.MiddleManMethodDeclaration;








public class DesignAnalyzer 
{
	
	private static ArrayList < String > mMiddleManSmellArrayList = new ArrayList < String > ();
	private ArrayList < String > globalVariableList = new ArrayList < String > ();
	private ArrayList<String> globalMethodNameList = new ArrayList<String>();
	private String smell;
	private static ArrayList < String > middleManSmell = new
			 ArrayList < String > ();
	

	public DesignAnalyzer(ArrayList<String> fileArrayList) {
		//System.out.println("adadsadsda");
		CompilationUnit cu = null;
		String source;
		ArrayList<String> list_all_methods=new ArrayList<String>();
		for (String fileName : fileArrayList) {
            File file  = new File(fileName);
            
			try {
				source = FileUtils.readFileToString(file);
				  ASTParser parser = ASTParser.newParser(AST.JLS3);
		            parser.setSource(source.toCharArray());
		            cu = (CompilationUnit) parser.createAST(null); 
		            parseCode(cu);
		           
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
	}
	
		//System.out.println("sad"+fileArrayList.size());
		for(String name : fileArrayList)
		{
			middleMan(name, cu);
		}
		//System.out.println("middleman"+middleManSmell.size());
		if (middleManSmell !=null && (middleManSmell.size()!=0)) {
			for (int i=0;i<middleManSmell.size();i++) {
			//	System.out.println("______"+middleManSmell.get(i));
				
			}
		}
			else {
				System.out.println("No middle man smell found in your selected project");
			}
		}
		

	


	private void parseCode(CompilationUnit cu) {
		  FieldDeclarationLister visitor = new FieldDeclarationLister();
		  cu.accept(visitor);
		  globalVariableList.addAll(visitor.getGlobalVariableList());
		 // System.out.println("Toatal VariableList__"+globalVariableList.size());
		  CodeAnalyzerMethodDeclarationLister visitor1 = new
		  CodeAnalyzerMethodDeclarationLister();
		  cu.accept(visitor1);
		  globalMethodNameList.addAll(visitor1.getGlobalMethodNameList());
		 // System.out.println("Total Methodes"+globalMethodNameList.size());
		 }
	
	private void middleMan(String fileName, CompilationUnit cu) {
		  com.tool.csv.middleman_smell_detector.MiddleManMethodDeclaration visitor = new MiddleManMethodDeclaration(globalMethodNameList);
		  cu.accept(visitor);
		  smell = visitor.getSmell();
		  
		  fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
		  if (smell != null) {
		   middleManSmell
		    .add("Class Name: " + fileName + " " +
		     smell);
		  }
		 }
	

}
