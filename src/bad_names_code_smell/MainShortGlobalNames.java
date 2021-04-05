package bad_names_code_smell;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import com.github.javaparser.JavaParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
//import com.sun.javafx.collections.ArrayListenerHelper;
import com.tool.csv.middleman.CodeAnalyzerMethodDeclarationLister;
import com.tool.csv.middleman.FieldDeclarationLister;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainShortGlobalNames extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private String mPath;
	private ArrayList<String> globalVariableList = new ArrayList<>();
	private static  ArrayList<String> mFilesArrayList = new ArrayList();
	private ArrayList<String> mShortGlobalVariableName = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainShortGlobalNames frame = new MainShortGlobalNames();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainShortGlobalNames() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnSelectProject = new JButton("Select Project");
		btnSelectProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	    		JFileChooser chooser = new JFileChooser(".");
	    		chooser.setDialogTitle("Select Project");
		        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        chooser.setAcceptAllFileFilterUsed(false);
		        if (chooser.showOpenDialog(btnSelectProject) == JFileChooser.APPROVE_OPTION)
		        {
		            ifFileChooserSelectedOk(chooser);
		            
		            
		        }
		        else
		        {
		            JOptionPane.showMessageDialog(frame,
		"Haven't selected anything yet!!!",
		"Selected Project",
		JOptionPane.PLAIN_MESSAGE);
		        }
			}

			
		});
		contentPane.add(btnSelectProject, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Refector");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getFilesInformation();
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
	}
	private void ifFileChooserSelectedOk(JFileChooser chooser) {
		mPath = chooser.getSelectedFile().toString();
		mPath = mPath.replace("\\", "/");
		File file = new File(mPath);
		fileFilter(file);
		if (mFilesArrayList.size() != 0) {
			JOptionPane.showMessageDialog(frame,"The selected path is: " + mPath,"Selected Project",JOptionPane.PLAIN_MESSAGE);
			System.out.println("The Size of selected file array List is____"+mFilesArrayList.size());
			
			} else {
			JOptionPane.showMessageDialog(frame,"The selected path doesn't contain any Java Files",
			"Selected Project",
			JOptionPane.PLAIN_MESSAGE);
			mPath = null;
			}
		
	}
	
	
	
	private void fileFilter(File dir) {
		File directory = dir;
		File[] filePath = directory.listFiles();
		for (File f : filePath) {
		if (f.isDirectory()) {
		fileFilter(f);
		} else if (f.toString().endsWith(".java")) {
		String temp = f.toString().replace("\\", "\\\\");
		mFilesArrayList.add(temp);
		}
		}
}
	
	private static class ClassVisitor extends VoidVisitorAdapter<Void> {
	    @Override
	    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
	        /* here you can access the attributes of the method.
	         this method will be called for all methods in this
	         CompilationUnit, including inner class methods */
	        System.out.println(n.getFields());
	        super.visit(n, arg);
	    }
	  }
	
	
	public void getFilesInformation()
	{
		CompilationUnit cu = null;
		String source;
		ArrayList<String> list_all_methods=new ArrayList<String>();
		for (String fileName : mFilesArrayList) {
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
			if (globalVariableList.size()>0) {
				for (int i= 0;i<globalVariableList.size();i++) {
				if (globalVariableList.get(i).length()<=1) {
					mShortGlobalVariableName.add(globalVariableList.get(i));
					
				}
					
				}
				
				if (mShortGlobalVariableName.size()>=0) {
					for(int j = 0;j<mShortGlobalVariableName.size();j++)
					{
					System.out.println("Short Variable Name list are__________________________"+mShortGlobalVariableName.get(j));
					}
					
				}
				else
				{
					System.out.println("No Short Variable name are found here");
				}
				
			}
	}
	
	
	
	private void parseCode(CompilationUnit cu) {
		  FieldDeclarationLister visitor = new FieldDeclarationLister();
		  cu.accept(visitor);
		  globalVariableList .addAll(visitor.getGlobalVariableList());
		 
		 }
}


