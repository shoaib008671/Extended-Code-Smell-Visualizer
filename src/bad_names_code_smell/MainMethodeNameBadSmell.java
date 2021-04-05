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
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.tool.csv.middleman.CodeAnalyzerMethodDeclarationLister;
import com.tool.csv.middleman.FieldDeclarationLister;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainMethodeNameBadSmell extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private String mPath;
	
	private static  ArrayList<String> mFilesArrayList = new ArrayList();
	private ArrayList<String> mShortGlobalVariableName = new ArrayList<>();
	private ArrayList<String> getMethodeName = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMethodeNameBadSmell frame = new MainMethodeNameBadSmell();
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
	public MainMethodeNameBadSmell() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnSelectproject = new JButton("SelectProject");
		btnSelectproject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(".");
	    		chooser.setDialogTitle("Select Project");
		        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        chooser.setAcceptAllFileFilterUsed(false);
		        if (chooser.showOpenDialog(btnSelectproject) == JFileChooser.APPROVE_OPTION)
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
		contentPane.add(btnSelectproject, BorderLayout.NORTH);
		
		JButton btnRefector = new JButton("Refector");
		btnRefector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			getFilesInformation();
			
			}
		});
		contentPane.add(btnRefector, BorderLayout.CENTER);
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
		
			if (getMethodeName.size()>0) {
				for (int i= 0;i<getMethodeName.size();i++) {
					//System.out.println("  "+getMethodeName.get(i));
				if (getMethodeName.get(i).length()<=1) {
					mShortGlobalVariableName.add(getMethodeName.get(i));
					
					
				}
				}
			}
			
			System.out.println(""+mShortGlobalVariableName.size());
				if (mShortGlobalVariableName.size()>1) {
					for(int j = 0;j<mShortGlobalVariableName.size();j++)
					{
					System.out.println("Short Methode Names are"+mShortGlobalVariableName.get(j));
					}
					
				}
				else
				{
					System.out.println("No Methode Name found with short names");
				}
					
				}
				
			
	
		
		
	
	

	
	private void parseCode(CompilationUnit cu) {
		// TODO Auto-generated method stub
		CodeAnalyzerMethodDeclarationLister visitor = new CodeAnalyzerMethodDeclarationLister();
		  cu.accept(visitor);
		  getMethodeName .addAll(visitor.getGlobalMethodNameList());
		//  System.out.println("methodename"+getMethodeName.size());
		
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
	
}
