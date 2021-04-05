package com.tool.csv.middleman;

import com.tool.csv.middleman.DesignAnalyzer;
import javax.swing.JPanel;

import org.eclipse.core.internal.runtime.Log;



import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class MainGui extends JFrame implements ActionListener {
	private JButton mBtnChooseFiles;
	private String mPath;
	private JFrame frame;
	private static ArrayList<String> middleManSmell = new ArrayList<String>();
	private ArrayList<String> mFilesArrayList = new ArrayList<String>();

	/**
	 * Create the panel.
	 */
	public MainGui() {
		
		setSize(568,400);  
		    getContentPane().setLayout(null);  
		    
		    JButton btnChoosefiles = new JButton("");
		    btnChoosefiles.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		JFileChooser chooser = new JFileChooser("."); 
			        chooser.setDialogTitle("Select Project");
			        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			        chooser.setAcceptAllFileFilterUsed(false);
			        if (chooser.showOpenDialog(mBtnChooseFiles) == JFileChooser.APPROVE_OPTION)
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
		    	}
		    	
		    );
		    btnChoosefiles.setBounds(208, 129, 89, 41);
		    getContentPane().add(btnChoosefiles);
		    
		    JButton btnRefector = new JButton("Refector");
		    btnRefector.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
	
	
		    		DesignAnalyzer da = new DesignAnalyzer(mFilesArrayList);
		    		
		    		
		    	}
		    });
		    btnRefector.setBounds(208, 193, 89, 49);
		    getContentPane().add(btnRefector);
		    setVisible(true);
	}

	private void settingListener() {
		mBtnChooseFiles.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	 
	
			
		
	}
	   public static void main(String[] args)
	   {
		   new MainGui();
		    

	   }
	   
	   
	   protected void ifFileChooserSelectedOk(JFileChooser chooser) {
			// TODO Auto-generated method stub
			mPath = chooser.getSelectedFile().toString();
			mPath = mPath.replace("\\", "/");
			File file = new File(mPath);
			fileFilter(file);
			if (mFilesArrayList.size() != 0) {
				JOptionPane.showMessageDialog(frame,
				"The selected path is: " + mPath,
				"Selected Project",
				JOptionPane.PLAIN_MESSAGE);
				for(int i = 0;i<mFilesArrayList.size();i++)
				{
					 //System.out.println(i+"_____________"+mFilesArrayList.get(i));
				}
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
