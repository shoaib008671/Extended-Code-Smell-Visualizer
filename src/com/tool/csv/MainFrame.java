/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.csv;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.osgi.framework.internal.core.Tokenizer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 *
 * @author Muhammad
 */
public class MainFrame extends javax.swing.JFrame {
	
	
	
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    boolean flag = false;
    public String path;//contains the path of the selected projct..
    private ArrayList<String> files = new ArrayList<>(); //this array contains the all files names of the whole project... :-)
    //For Long Parameter Lis Smell..
    ArrayList<String> classname = new ArrayList<String>();
    ArrayList<String> method = new ArrayList<String>();
    ArrayList<String> packg = new ArrayList<String>();
    ArrayList<String> longParaMethodSig = new ArrayList<String>();
    private ArrayList<String> classinstance = new ArrayList<>();
    private static ArrayList<String> longParaListSmell = new ArrayList<String>();
    private ArrayList<Integer> TotalParameters = new ArrayList<>();
     
    public static String codeViewTitle = null;
    public static String codeViewCode = null;
    public static String longParaSuggestion = null;
     public static String primitiveobsessionsuggestion = null;

    //For Long Method Smell  7 arrays
    private String smell;
    private static ArrayList<String> longMethodpackag = new ArrayList<String>();
    private static ArrayList<String> longMethodclass = new ArrayList<String>();
    private static ArrayList<String> longMethodmethod = new ArrayList<String>();
    private static ArrayList<Integer> longMethodlines = new ArrayList<Integer>();

    //For Switch Case Smell.............. 13
    private static ArrayList<String> switchCaseClass = new ArrayList<String>();
    private static ArrayList<String> switchCasePackage = new ArrayList<String>();
    private static ArrayList<String> switchCaseName = new ArrayList<String>();
    private static ArrayList<Integer> switchCaseTotalNumber = new ArrayList<Integer>();
    private static ArrayList<String> switchStateBody = new ArrayList<String>();
    public static ArrayList<String> v_data = new ArrayList<String>();
    public static ArrayList<String> smell_list = new ArrayList<String>();
    public ArrayList<String[]> Feature_envy_data = new ArrayList<>(); //21
    
    
            
    //For Buttons
    public static int active_smell = 0;
    
    public static int longParaThreshold = 0;
    public static int longMethodThreshold = 0;
    private int largeClassThreshold = 0;
    private int CommentsThreshold = 0;
    private int switchThreshold = 0;

    public MainFrame() {
        CodeView cv = new CodeView();
        smell_list.add("Long Parameter List");
        smell_list.add("Long Method");
        smell_list.add("Switch Statements");
        smell_list.add("Lazy Class");
        smell_list.add("Feature Envy");
        smell_list.add("Data Class");
        smell_list.add("Large Class");
        smell_list.add("Comments");
        smell_list.add("Primitive Obsession");
        smell_list.add("Parrallel Inheritence");
        smell_list.add("Speculative Generality");
        smell_list.add("Message Chain");
        smell_list.add("Middle Man");
        smell_list.add("Shotgun Surgery");
        
        
        initComponents();
        relations.setEnabled(false);
        Relationship.setEnabled(false);
        
        //JScrollPane scroller = new JScrollPane(jPanel2);
       // this.getContentPane().add(scroller, BorderLayout.CENTER);
        this.setTitle("Extended Code Smell Visualizer");
        this.setSize(1024, 768);
        this.setIconImage(new ImageIcon(getClass().getResource("/com/tool/csv/insect-icon.png")).getImage());
        
    }
    
public String s ="";
    // function for constraints
    public void integerConstraint(java.awt.event.KeyEvent obj )
     {
         char c = obj.getKeyChar();
      if (!((c >= '0') && (c <= '9') ||
         (c == KeyEvent.VK_BACK_SPACE) ||
         (c == KeyEvent.VK_DELETE) || c == KeyEvent.VK_ENTER)) {
        getToolkit().beep();
        obj.consume();
      }
      
     }

    //Function for LongParameter_btn
    private void LongParameterListSmell() {
        clearAll();
        flag = false;
        ASTmaker();
        
        ArrayList<Integer> parameters = new ArrayList<>();
        int index =0;
        for(int k=0;k<TotalParameters.size();k++)
        {
            if(TotalParameters.get(k)>=longParaThreshold)
            {
                packg.set(index, packg.get(k));
                classname.set(index, classname.get(k));
                method.set(index, method.get(k));
                longParaListSmell.set(index, longParaListSmell.get(k));
                longParaMethodSig.set(index, longParaMethodSig.get(k));
                parameters.add(TotalParameters.get(k));
                index++;
            }
        }
        String col[] = {"Package", "Class", "Methods", "Parameters", "Parameter Count", "Impact Level"};
        Object[][] data = new Object[parameters.size()][col.length];
        
        for (int i = 0; i < parameters.size(); i++) {
            for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = packg.get(i);
                } else if (j == 1) {
                    data[i][j] = classname.get(i);
                } else if (j == 2) {
                    data[i][j] = method.get(i);
                } else if (j == 3) {
                    data[i][j] = longParaListSmell.get(i);
                } else if (j == 4) {
                    data[i][j] = parameters.get(i);
                } else if (j == 5) {
                    if (jTextField_LowImpact_low.getText().isEmpty() || jTextField_LowImpact_up.getText().isEmpty()
                            || jTextField_MedImpact_low.getText().isEmpty() || jTextField_MedImpact_up.getText().isEmpty()
                            || jTextField_MedImpact_low.getText().isEmpty() || jTextField_MedImpact_up.getText().isEmpty()) {
                        int impact;
                        impact = parameters.get(i);
                        if (impact >= 3 && impact <= 5) {
                            data[i][j] = "Low";
                        } else if (impact >= 6 && impact <= 7) {
                            data[i][j] = "Medium";
                        } else if (impact >= 8) {
                            data[i][j] = "High";
                        }
                    } else {
                        int impact;
                        impact = parameters.get(i);
                        if (impact >= Integer.parseInt(jTextField_LowImpact_low.getText()) && impact <= Integer.parseInt(jTextField_LowImpact_up.getText())) {
                            data[i][j] = "Low";
                        } else if (impact >= Integer.parseInt(jTextField_MedImpact_low.getText()) && impact <= Integer.parseInt(jTextField_MedImpact_up.getText())) {
                            data[i][j] = "Medium";
                        } else if (impact >= Integer.parseInt(jTextField_HighImpact_low.getText())) {
                            data[i][j] = "High";
                        }
                    }
                }
            }
        }
        jTable1.setModel(new DefaultTableModel(data, col));
        longParaSuggestion = " To remove â€œLong Parameter Listâ€� bad smell, following two ways can be used: \nâ€¢	If the parameters are replaceable by generating a call to an existing object, use â€œReplace Parameter with Method.â€�\nâ€¢	If the parameter list lacks a common logical object, use â€œIntroduce Parameter Objectâ€�.";
        if (!longParaListSmell.isEmpty()) {
            LongMethod_btn.setEnabled(true);
            totalsmells();

        } else {
            JOptionPane.showMessageDialog(this, "No 'Long Parameter List' Bad Smell Found..!!");
        }
    }

    private ArrayList<String> longMethodBody = new ArrayList<>();
    private ArrayList<Integer> longMethodComplexity = new ArrayList<>(); //23
    //Function for LongMethod_btn
    private void LongMethodSmell() {
        clearAll();
        CompilationUnit cu;

        for (String fileName : files) {
            cu = parse(fileName);
            longMethodBadSmell(fileName, cu);
            lazyClassSmell(fileName, cu);
        }
        ArrayList<Double> com = new ArrayList<>();
        for (int i = 0; i < longMethodlines.size(); i++)
        {
            com.add(longMethodComplexity(i));
        }
        ArrayList<Double> complexity = new ArrayList<>();
        int index =0;
        for(int k=0;k<com.size();k++)
        {
            if(com.get(k)>=longMethodThreshold)
            {
               
                longMethodpackag.set(index, longMethodpackag.get(k));
                longMethodclass.set(index, longMethodclass.get(k));
                longMethodmethod.set(index, longMethodmethod.get(k));
                longMethodlines.set(index, longMethodlines.get(k));
                longMethodBody.set(index, longMethodBody.get(k));
                complexity.add(com.get(k));
                index++;
            }
        }
        String col[] = {"Package", "Class", "Method","Lines", "Complexity", "Impact Level"};
        Object[][] data = new Object[complexity.size()][col.length];
        for (int i = 0; i < complexity.size(); i++) {
            for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = longMethodpackag.get(i);
                } else if (j == 1) {
                    data[i][j] = longMethodclass.get(i);
                } else if (j == 2) {
                    data[i][j] = longMethodmethod.get(i);
                } else if (j == 3) {
                    data[i][j] = longMethodlines.get(i);
                } else if (j == 4) {
                    data[i][j] = complexity.get(i);
                } else if (j == 5) {
                    if (jTextField_LowImpact_low.getText().isEmpty() || jTextField_LowImpact_up.getText().isEmpty()
                            || jTextField_MedImpact_low.getText().isEmpty() || jTextField_MedImpact_up.getText().isEmpty()
                            || jTextField_MedImpact_low.getText().isEmpty() || jTextField_MedImpact_up.getText().isEmpty()) {
                        double impact = 0;
                        impact = complexity.get(i);
                        if (impact >= 21 && impact < 28) {
                            data[i][j] = "Low";
                        } else if (impact >= 28 && impact < 34) {
                            data[i][j] = "Medium";
                        } else if (impact >= 34) {
                            data[i][j] = "High";
                        }
                    } else {
                        double impact = 0;
                        impact = complexity.get(i);
                        if (impact >= Integer.parseInt(jTextField_LowImpact_low.getText()) && impact < Integer.parseInt(jTextField_LowImpact_up.getText())) {
                            data[i][j] = "Low";
                        } else if (impact >= Integer.parseInt(jTextField_MedImpact_low.getText()) && impact < Integer.parseInt(jTextField_MedImpact_up.getText())) {
                            data[i][j] = "Medium";
                        } else if (impact >= Integer.parseInt(jTextField_HighImpact_low.getText())) {
                            data[i][j] = "High";
                        }
                    }
                }
            }
        }
        jTable1.setModel(new DefaultTableModel(data, col));
        longParaSuggestion = " Apply the â€œExtract Methodâ€� refactoring technique to get rid of â€œLong Methodâ€� bad smell. Preprocessing is needed: "
                + "\nâ€¢if the new method has " +
"a long parameter list after applying the â€œExtract Methodâ€� refactoring technique.\n"
                + " The occurrence of long parameter list is due to the" +
"presence of temporary variables. Use â€œReplace Temp with Queryâ€� refactoring technique to eliminate temporary variables.â€�\nâ€¢The long " +
"parameter list can be made short by using â€œIntroduce Parameter Objectâ€� or â€œReplace Method with Method Objectâ€�.";
        JOptionPane.showMessageDialog(null, "Total Long Method BadSmells Found= " + complexity.size());
    }

    //Function for SwitchStatement_btn
    private void SwitchStatementSmell() {
        clearAll();
        CompilationUnit cu;

        for (String fileName : files) {
            cu = parse(fileName);
            countSwitchCase(cu, fileName);
        }
        ArrayList<Integer> temp = new ArrayList<>();
        int index =0;
        for(int k =0 ; k<switchCaseName.size();k++)
        {
            if(switchCaseTotalNumber.get(k) >= switchThreshold)
            {
                switchCasePackage.set(index, switchCasePackage.get(k));
                switchCaseClass.set(index, switchCaseClass.get(k));
                switchCaseName.set(index, switchCaseName.get(k));
                temp.add(switchCaseTotalNumber.get(k));
                switchStateBody.set(index, switchStateBody.get(k));
                index++;
            }
        }
        String col[] = {"Package", "Class", "Case", "Count", "Impact Level"};
        Object[][] data = new Object[temp.size()][col.length];
        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = switchCasePackage.get(i);
                } else if (j == 1) {
                    data[i][j] = switchCaseClass.get(i);
                } else if (j == 2) {
                    data[i][j] = switchCaseName.get(i);
                } else if (j == 3) {
                    data[i][j] = temp.get(i);
                } else if (j == 4) {
                    if (jTextField_LowImpact_low.getText().isEmpty() || jTextField_LowImpact_up.getText().isEmpty()
                            || jTextField_MedImpact_low.getText().isEmpty() || jTextField_MedImpact_up.getText().isEmpty()
                            || jTextField_MedImpact_low.getText().isEmpty() || jTextField_MedImpact_up.getText().isEmpty()) {
                        int impact = 0;
                        impact = temp.get(i);
                        if (impact >= 4 && impact <= 6) {
                            data[i][j] = "Low";
                        } else if (impact >= 7 && impact <= 9) {
                            data[i][j] = "Medium";
                        } else if (impact >= 10) {
                            data[i][j] = "High";
                        }
                    } else {
                        int impact = 0;
                        impact = temp.get(i);
                        if (impact >= Integer.parseInt(jTextField_LowImpact_low.getText()) && impact <= Integer.parseInt(jTextField_LowImpact_up.getText())) {
                            data[i][j] = "Low";
                        } else if (impact >= Integer.parseInt(jTextField_MedImpact_low.getText()) && impact <= Integer.parseInt(jTextField_MedImpact_up.getText())) {
                            data[i][j] = "Medium";
                        } else if (impact >= Integer.parseInt(jTextField_HighImpact_low.getText())) {
                            data[i][j] = "High";
                        }
                    }
                }
            }
        }
        jTable1.setModel(new DefaultTableModel(data, col));
        longParaSuggestion = "Polymorphism should be used for the removal of switch case bad smell. This can be achieved by creating an inheritance structure. Use \n" +
"the â€œExtract Methodâ€� refactoring technique to extract each switch case into individual methods and then apply â€œMove Methodâ€� technique \n" +
"to move each one of the methods into respective classes. Apply â€œReplace Type Code with Subclassesâ€� technique to achieve inheritance. \n" +
"After doing this, apply â€œReplace Conditional with Polymorphismâ€�.";
        JOptionPane.showMessageDialog(null, "Total Switch Case Bad Smells Found= " + temp.size());
    }
    private void FeatureEnvySmell(int low_ins , int low_m) throws FileNotFoundException, IOException
    {
        clearAll();
        
        CompilationUnit cu;
        String col[] = {"Package", "Class", "Envy Method", "No of external fields","No of external Methods",  "Impact"};
        F_Envy fe =  new F_Envy();
        ArrayList<String> list_all_methods=new ArrayList<String>();
       
         for (String fileName : files) {
             File file  = new File(fileName);
             String source = FileUtils.readFileToString(file);
             ASTParser parser = ASTParser.newParser(AST.JLS3);
             parser.setSource(source.toCharArray());
             cu = (CompilationUnit) parser.createAST(null); 
            AST_declaration list = new AST_declaration();
            cu.accept(list);
            for(String m:list.get_method_list())
            {
                list_all_methods.add(m);
            }
         
         }
         
            for (int i=0;i<files.size();i++) {
            File file  = new File(files.get(i));
             String source = FileUtils.readFileToString(file);
             ASTParser parser = ASTParser.newParser(AST.JLS3);
             parser.setSource(source.toCharArray());
             cu = (CompilationUnit) parser.createAST(null);
            fe.accept_data(files,files.get(i),list_all_methods,low_ins,low_m);
            cu.accept(fe);
            }
            Feature_envy_data.clear();
         for(String[] a:fe.get_total_envy())
            {
                    Feature_envy_data.add(a);
            }
         
         ArrayList<String[]> te = new ArrayList<>();
         for(String[] a:Feature_envy_data)
         {
             if(Integer.parseInt(a[3])>=low_m  && Integer.parseInt(a[4])>=low_m)
                 te.add(a);
         }
         Object[][] data = new Object[te.size()][col.length];
        
         
         for(int i=0;i<te.size();i++){
        for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = te.get(i)[0];
                } else if (j == 1) {
                    data[i][j] = te.get(i)[1];
                } else if (j == 2) {
                    data[i][j] = te.get(i)[2];
                } else if (j == 3) {
                    data[i][j] = te.get(i)[3];
                } 
                else if (j == 4) {
                    data[i][j] = te.get(i)[4];}
                
                else if (j == 5) {
                  
                    {
                        int method_impact = Integer.parseInt(te.get(i)[4]);
                        int var_impact = Integer.parseInt(te.get(i)[3]);;
                        if (var_impact >= FeatureEnvy_Threshold.var_lower_low && var_impact <= FeatureEnvy_Threshold.var_lower_up ){
                            if((method_impact >= FeatureEnvy_Threshold.method_lower_low && method_impact <= FeatureEnvy_Threshold.method_lower_up)
                                    || (method_impact >= FeatureEnvy_Threshold.method_med_low && method_impact <= FeatureEnvy_Threshold.method_med_up))
                            {
                                data[i][5] = "Low";
                            }
                       else if(method_impact >= FeatureEnvy_Threshold.method_high_low)
                            {
                                data[i][5] = "Medium";
                            }
                            
                        } 
                        else if (var_impact >= FeatureEnvy_Threshold.var_med_low && var_impact <= FeatureEnvy_Threshold.var_med_up) 
                        {
                            data[i][5] = "Medium";
                        } 
                        else if (var_impact >= FeatureEnvy_Threshold.var_lower_low) 
                        {
                            if(method_impact >= FeatureEnvy_Threshold.method_lower_low && method_impact <= FeatureEnvy_Threshold.method_lower_up)
                            {
                                data[i][5] = "Low";
                            }
                            else if((method_impact >= FeatureEnvy_Threshold.method_med_low && method_impact <= FeatureEnvy_Threshold.method_med_up)
                                    || method_impact >= FeatureEnvy_Threshold.method_high_low)
                            {
                                data[i][5] = "Medium";
                            }
                            
                        }
                    
                }
            }
            
         }
          
    }    
         jTable1.setModel(new DefaultTableModel(data, col));
         longParaSuggestion = "Fortunately the cure is obvious; the method clearly wants to be elsewhere, so you use Move Method to get it there. Sometimes only \n" +
"part of the method suffers from envy; in that case use Extract Method on the jealous bit and Move Method to give it a dream home. Of \n" +
"course not all cases are cut-and-dried. Often a method uses features of several classes, so which one should it live with? The \n" +
"heuristic we use is to determine which class has most of the data and put the method with that data. This step is often made easier \n" +
"if Extract Method is used to break the method into pieces that go into different places.";
          JOptionPane.showMessageDialog(null, "Total Feature Envy BadSmells Found= " + jTable1.getRowCount());
    }

    private void CommentsSmell(int low_impact) throws FileNotFoundException {
        clearAll();
        Comments_HC com = new Comments_HC();
        int count=0;
        String[] cols = new String[7];
        ArrayList<String[]> all = new ArrayList<String[]>();
        for (int i = 0; i < files.size(); i++) {
            
            if(com.Comments_counter(files.get(i) , low_impact)!=null){
                cols = com.Comments_counter(files.get(i) , low_impact);
                all.add(cols);
                count++;
            }
        }
        String col[] = {"Package", "Class", "Total Lines of Code", "Total Lines of Comments", "Percentage" , "Impact Level"};
         Object[][] data = new Object[all.size()][col.length];
         for(int i=0;i<count;i++){
             String[] temp = all.get(i);
        for (int j = 0; j < col.length; j++) {
            int total_c=Integer.parseInt(temp[2]);                 
            int total_li=Integer.parseInt(temp[5]);
            int impact = (total_c*100)/total_li;
                 int lowest;
                 if(!jTextField_LowImpact_low.getText().isEmpty()){
                 lowest = Integer.parseInt(jTextField_LowImpact_low.getText());}
                 else
                 lowest=0;
                 
                 if(impact>lowest){
                if (j == 0) {
                    data[i][j] = temp[0];
                } else if (j == 1) {
                    data[i][j] = temp[1];
                } else if (j == 2) {
                    data[i][j] = temp[5];
                } else if (j == 3) {
                    data[i][j] = temp[2];
                
                } else if (j == 4) {
                    if (jTextField_LowImpact_low.getText().isEmpty() || jTextField_LowImpact_up.getText().isEmpty()
                            || jTextField_MedImpact_low.getText().isEmpty() || jTextField_MedImpact_up.getText().isEmpty()
                            || jTextField_MedImpact_low.getText().isEmpty() || jTextField_MedImpact_up.getText().isEmpty()) {
                        
                        data[i][j]=""+impact+"%";
                        if (impact >= 1 && impact <= 18) {
                            data[i][5] = "Low";
                       } else if (impact >= 19 && impact <= 30) {
                            data[i][5] = "Medium";
                        } else if (impact >= 31) {
                            data[i][5] = "High";
                        }
                    } 
                    
                    else {
                         
                        data[i][j]=""+impact+"%";
                        if (impact >= Integer.parseInt(jTextField_LowImpact_low.getText()) && impact <= Integer.parseInt(jTextField_LowImpact_up.getText()) ) {
                            data[i][5] = "Low";
                        } else if (impact >= Integer.parseInt(jTextField_MedImpact_low.getText()) && impact <= Integer.parseInt(jTextField_MedImpact_up.getText())) {
                            data[i][5] = "Medium";
                        } else if (impact >= Integer.parseInt(jTextField_HighImpact_low.getText())) {
                            data[i][5] = "High";
                        }
                    }
                    
                }
            }}}
            
            jTable1.setModel(new DefaultTableModel(data, col));
            longParaSuggestion = "If you need a comment to explain what a block of code does, try Extract Method. If the method is already extracted but you still need \n" +
"a comment to explain what it does, use Rename Method. If you need to state some rules about the required state of the system, use \n" +
"Introduce Assertion.";
            JOptionPane.showMessageDialog(null, "Total Comments BadSmells Found= " + jTable1.getRowCount());
    }
    //when we select ok from browse button..
    private void ifFileChooserSelectedOk(JFileChooser chooser) {//this method is the part of browse button..
        path = chooser.getSelectedFile().toString();
        //path = path.replace("\\", "/");
        File file = new File(path);
        fileFilter(file);//method calling
        if (!files.isEmpty()) {
            jTextField_browse.setText(path);//showing in textfield...
            JOptionPane.showMessageDialog(null, "The selected path is: \n" + path, "Selected Project", JOptionPane.PLAIN_MESSAGE);
            reset_btnActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(null, "The selected path doesn't contain any Java Files", "Selected Project", JOptionPane.PLAIN_MESSAGE);
            path = null;
        }
    }

    //this method is the part of browse button..
    private void fileFilter(File dir) {//separating all the java files...and storing in the 'array list' tht iz 'files'..
        File directory = dir;
        File[] filePath = directory.listFiles();
        for (File f : filePath) {
            if (f.isDirectory()) {
                fileFilter(f);//recursive call...
            } else if (f.toString().endsWith(".java")) {
                String temp = f.toString().replace("\\", "\\\\");
                files.add(temp);
            }
        }
    }

    void clearAll() {

        switchCaseClass.clear();
        switchCasePackage.clear();
        switchCaseName.clear();
        switchCaseTotalNumber.clear();
        longMethodpackag.clear();
        longMethodclass.clear();
        longMethodmethod.clear();
        longMethodlines.clear();
        longMethodBody.clear();
        longMethodComplexity.clear();
        classname.clear();
        method.clear();
        packg.clear();
        longParaListSmell.clear();
        TotalParameters.clear();
        largeClassPackage.clear();
        largeClasses.clear();
        largeClassfields.clear();
        largeClassfieldCount.clear();
        dataClassPackage.clear();
        dataClasses.clear();
        getter.clear();
        setter.clear();
        constructor.clear();
        totalmethods.clear();
        longParaMethodSig.clear();
        switchStateBody.clear();
        largeClassMethods.clear();
        largeClassMethodsList.clear();
        lazyClassPackage.clear();
        lazyClassClas.clear();
        lazyClassLines.clear();
        lazyClassMethods.clear();
        lazyClassWeightMethod.clear();
        Feature_envy_data.clear();
        s_surgery_data.clear();
        BrainMethodpackag.clear();
        BrainMethodclass.clear();
        BrainMethodmethod.clear();
        BrainMethodlines.clear();
        brainMethodBody.clear();
        
        brainMethodComplexity.clear();
    }

    //For Long Parameterlist.....
    public void ASTmaker() {
        CompilationUnit cu;
        
        for (String fileName : files) {
            cu = parse(fileName);
            longParameterList(fileName, cu);
        }

    }

    private CompilationUnit parse(String fileName) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(getChars(fileName));
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        return cu;
    }

    private char[] getChars(String fileName) {
        char[] result = null;
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            String contents = "";
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine(); 
                if (nextLine.contains("//")) {
                    int beginIndex = nextLine.indexOf("//", 0);
                    String comment = nextLine.substring(beginIndex);

                    nextLine = nextLine.replace(comment, "");

                    if (comment != null) {
                        contents += nextLine;
                    }
                } else {
                    contents += nextLine;
                }
            }
            result = contents.toCharArray();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    private void longParameterList(String fileName, CompilationUnit cu) {
        ArrayList<String> smell1 = new ArrayList<String>();
        ArrayList<String> smell2 = new ArrayList<String>();
        ArrayList<Integer> smell3 = new ArrayList<Integer>();
        String filePath = fileName;
        String pkg;
        String pkgg;
        pkg = fileName.substring(0, fileName.lastIndexOf("\\") - 1);
        
        pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
        
        ArrayList<String> str = new ArrayList<String>();
        str.add(pkgg);
        while (!"src".equals(pkgg)) {
            pkg = pkg.substring(0, pkg.lastIndexOf("\\") - 1);
            pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
            str.add(pkgg);
        }
        String tempp = "";
        for (int i = str.size() - 2; i >= 0; i--) {
            tempp = tempp + "." + str.get(i);
        }
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        LongParameterListMethodDeclaration visitor = new LongParameterListMethodDeclaration();
        cu.accept(visitor);
        if (flag == true) {
            String oldClassCode = cu.toString();
            String newClassCode = oldClassCode.replace("", "");
            writeToJavaFile(filePath, newClassCode);

        }
        smell1.addAll(visitor.getParameters());
        longParaMethodSig.addAll(visitor.getMethodSig());
        if (!smell1.isEmpty()) {
            for (String temp : smell1) {
                packg.add(tempp);
                longParaListSmell.add(temp);
                classname.add(fileName);
                

            }
        }
        smell2.addAll(visitor.getmethod());
        if (!smell2.isEmpty()) {
            for (String temp : smell2) {
                method.add(temp);
            }
        }
        smell3.addAll(visitor.getTotalpara());
        if (!smell3.isEmpty()) {
            for (int temp : smell3) {
                    TotalParameters.add(temp);
                
            }
        }
    }

    void writeToJavaFile(String filePath, String data) {
        try {
            String filename = filePath;
            // JOptionPane.showMessageDialog(null, "file ka naaam==="+filename);
            //JOptionPane.showMessageDialog(null, "dataaaa==="+data);
            FileWriter fw = new FileWriter(filename, false);
            fw.write(data);
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    public void totalsmells() {
        int i = 0;
        for (int j = 0; j < jTable1.getRowCount(); j++) {
            String temp = "" + jTable1.getModel().getValueAt(j, 0);
            if (!"null".equals(temp)) {
                i++;   
            }
        }

        JOptionPane.showMessageDialog(this, "total bad smells are= " + i);
    }

    //For Long Method Smell...
    private void longMethodBadSmell(String fileName, CompilationUnit cu) {
        LongMethodMethodDeclaration visitor = new LongMethodMethodDeclaration();
        cu.accept(visitor);
        smell = visitor.getSmell();
        String pkg;
        String pkgg;
        pkg = fileName.substring(0, fileName.lastIndexOf("\\") - 1);
        pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
        ArrayList<String> str = new ArrayList<String>();
        str.add(pkgg);
        while (!"src".equals(pkgg)) {
            pkg = pkg.substring(0, pkg.lastIndexOf("\\") - 1);
            pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
            str.add(pkgg);
        }
        String temp = "";
        for (int i = str.size() - 2; i >= 0; i--) {
            temp = temp + "." + str.get(i);
        }
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        
        if (smell != null) {
            for(int i=0;i<visitor.getmethod().size();i++){
                longMethodpackag.add(temp);
                longMethodclass.add(fileName);
                longMethodmethod.add(visitor.getmethod().get(i));
                longMethodlines.add(visitor.getlines().get(i));
                longMethodBody.add(visitor.getMethodBody().get(i));
            }
        }
    }
    

    double longMethodComplexity(int i) {
        if(active_smell==2){
        double LOC = (longMethodlines.get(i) * 0.25);
        double ifconditions = ((longMethodComplexity.get(i)) * 0.75);
        //JOptionPane.showMessageDialog(null, "LOC and its result...."+longMethodlines+"..liness.."+""+LOC);
        //JOptionPane.showMessageDialog(null, "if result and final...."+ifResult+"..final value.."+""+ifconditions);
        double complexity = LOC + ifconditions;
        //JOptionPane.showMessageDialog(null, "complexity...."+complexity);
        return complexity;
        }
        else if(active_smell==14)
            
        {
            if(longMethodlines.get(i)>20){
            double LOC = (longMethodlines.get(i)/20 );
            
        double ifconditions = ((longMethodComplexity.get(i)) / 24);
        //JOptionPane.showMessageDialog(null, "LOC and its result...."+longMethodlines+"..liness.."+""+LOC);
        //JOptionPane.showMessageDialog(null, "if result and final...."+ifResult+"..final value.."+""+ifconditions);
        double complexity = (LOC + ifconditions);
        //JOptionPane.showMessageDialog(null, "complexity...."+complexity);
        return complexity;
        }
        }
        return 0;
    
    }



    //For Switch Case Smell...
    private void countSwitchCase(CompilationUnit cu, String fileName) {
        //String switchsmell=null;
        ArrayList<String> switchsmell = new ArrayList<String>();
        CountSwitchStatement visitor = new CountSwitchStatement();
        cu.accept(visitor);
        switchsmell.addAll(visitor.getMessage());
        String pkg;
        String pkgg;
        pkg = fileName.substring(0, fileName.lastIndexOf("\\") - 1);
        pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
        ArrayList<String> str = new ArrayList<String>();
        str.add(pkgg);
        while (!"src".equals(pkgg)) {
            pkg = pkg.substring(0, pkg.lastIndexOf("\\") - 1);
            pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
            str.add(pkgg);
        }
        String temp = "";
        for (int i = str.size() - 2; i >= 0; i--) {
            temp = temp + "." + str.get(i);
        }
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        if (switchsmell.size() != 0) {
            switchCaseName.addAll(visitor.getMessage());
            switchCaseTotalNumber.addAll(visitor.getCounter());
            switchStateBody.addAll(visitor.getSwitchBody());
            for (int i = 0; i < switchsmell.size(); i++) {
                //switchCaseSmell.add("Class Name: " + fileName + ""+ smell);
                switchCasePackage.add(temp);
                switchCaseClass.add(fileName);
            }
        }
    }
    private ArrayList<String> PrimitiveObsessionClassPackage = new ArrayList<>();
    private ArrayList<String> PrimitiveObsessionClasses = new ArrayList<>();
    private ArrayList<String> PrimitiveObsessionClassfields = new ArrayList<>();
    private ArrayList<Integer> PrimitiveObsessionClassfieldCount = new ArrayList<>();
    private ArrayList<Integer> TotalprimitiveDataTypes = new ArrayList<>();
    
    private ArrayList<String> clsswithNoInstances= new ArrayList<>();
   
   
    private void PrimitiveObsessionSmellDetection(){
        clearAll();
        int temp=0; 
        CompilationUnit cu = null;
        for (String fileName : files) {
            cu = parse(fileName);
          primitiveObsessionSmellCount(fileName, cu);
          
          
        }
        int sum=0;
        for(int i =0; i<TotalprimitiveDataTypes.size(); i++){
            sum+=TotalprimitiveDataTypes.get(i);
        }
        double average = sum/TotalprimitiveDataTypes.size();
        ArrayList<Integer> fields = new ArrayList<>();
        int index =0;
        for(int k=0;k<TotalprimitiveDataTypes.size();k++)
        {
            if(TotalprimitiveDataTypes.get(k)>= average)
            {
                PrimitiveObsessionClassPackage.set(index, PrimitiveObsessionClassPackage.get(k));
                PrimitiveObsessionClasses.set(index, PrimitiveObsessionClasses.get(k)); 
                fields.add(index, TotalprimitiveDataTypes.get(k));
                 TotalprimitiveDataTypes.set(index, TotalprimitiveDataTypes.get(k));
                index++;
            }
        }
        int largest=fields.get(0);
        
        for (int l=1;l<fields.size();l++)
        {
            int cur=fields.get(l);
            if (cur > largest)
            {
                largest =cur;
            }   
        }
        double dif = largest-average;
        dif= dif/3;
        int firstvalue =(int)average+(int)dif;
        int secondvalue = firstvalue +(int)dif;
        int thirdvalue = secondvalue +(int)dif;
        
        String col[] = {"Package", "Class", "No of Fields", "Weight of variables", "Impact Level"};
        Object[][] data = new Object[fields.size()][col.length];
        for (int i = 0; i < fields.size(); i++) {
            for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = PrimitiveObsessionClassPackage.get(i);
                } else if (j == 1) {
                    data[i][j] = PrimitiveObsessionClasses.get(i);
                } else if (j == 2) {
                    data[i][j] = fields.get(i);
                } else if (j == 3) {
                    data[i][j] = average;
                }
                else if (j == 4){
                    if (fields.get(i)>=average && fields.get(i)< firstvalue ){
                        data[i][j]="Low";
                    }
                    else if(fields.get(i)>=firstvalue && fields.get(i)<secondvalue){
                    data[i][j]="Medium";
                    }
                    else{
                        data[i][j]="High";
                    }
                }
            }
        }
         jTable1.setModel(new DefaultTableModel(data, col));
       longParaSuggestion = "If you have a large variety of primitive fields, it may be possible to logically group some of them into their own class. Even better, move the behavior associated with this data into the class too. For this task, try Replace Data Value with Object.\n" +
"If the values of primitive fields are used in method parameters, go with Introduce Parameter Object or Preserve Whole Object.\n" +
"When complicated data is coded in variables, use Replace Type Code with Class, Replace Type Code with Subclasses or Replace Type Code with State/Strategy. \n" ;
        JOptionPane.showMessageDialog(null, "Total Primitive Obsession Bad Smells Found= " + fields.size());
         
        TotalprimitiveDataTypes.clear();
    }
    
    
    
    
    private void primitiveObsessionSmellCount(String filename, CompilationUnit cu){
        SelectClassFiles visitor = new SelectClassFiles();
        cu.accept(visitor); 
        int a=visitor.getCounter();
        TotalprimitiveDataTypes.add(a);
        package_get pg = new package_get();
        String pkg;
        String pkgg;
        pkg = filename.substring(0, filename.lastIndexOf("\\") - 1);     
        pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
        ArrayList<String> str = new ArrayList<>();
        str.add(pkgg);
        while (!"src".equals(pkgg)) {
            pkg = pkg.substring(0, pkg.lastIndexOf("\\") - 1);
            pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
            str.add(pkgg);
        }
        String temp = "";
        for (int i = str.size() - 2; i >= 0; i--) {
            temp = temp + "." + str.get(i);
        }
        filename = filename.substring(filename.lastIndexOf("\\") + 1);
        if(TotalprimitiveDataTypes.size()!=0)
        {
            PrimitiveObsessionClassPackage.add(temp);
            PrimitiveObsessionClasses.add(filename);
            PrimitiveObsessionClassfields.add(visitor.getFieldName()); 
        } 
    }
    ArrayList<String> Parents = new ArrayList<>();
    ArrayList<String> Parallelpackages= new ArrayList<>();
    ArrayList<String> Children = new ArrayList<>();
    ArrayList<String> Childinfo= new ArrayList<>();
    ArrayList<String> values = new ArrayList<>();
    ArrayList<String> Classes= new ArrayList<>();
    ArrayList<String> Lines= new ArrayList<>();
    ArrayList<String> totalchilds = new ArrayList<>();
    ArrayList<ArrayList<String>> relation = new ArrayList<ArrayList<String>>();
    
    
    public void resetparallelinheritence(){
        Parents.clear();
        Parallelpackages.clear();
        Children.clear();
        Childinfo.clear();
        values.clear();
        Classes.clear();
        Lines.clear();
        totalchilds.clear();
        relation.clear();
        Hasrelation.clear();
        check1.clear();check2.clear();
        filespath.clear();
    }
     ArrayList<String> check1 = new ArrayList<String>();
     ArrayList<String> check2 = new ArrayList<String>();
    private void ParallelInheritenceSmellDetection() throws FileNotFoundException, IOException{
       for (String filename:files){
           getChildAndParents(filename);
       }
    // get the name of all the classes 
       
       for(int j=0;j<files.size();j++){
           String clas_name = files.get(j).substring(files.get(j).lastIndexOf("\\")+1, files.get(j).lastIndexOf(".java"));
           Classes.add(clas_name);
       }
       // These code of line making all the Relations of the inherited classes and add in an Array of ArrayList relation
       for(int i = 0; i < Parents.size(); i++)
       {
           if (!Classes.contains(Parents.get(i))) {
               continue;
           }
           ArrayList<String> tempList = new ArrayList<String>();
           String parent, child;
           parent = Parents.get(i);
           child = Children.get(i);
           tempList.add(parent);
           tempList.add(child);
           relation.add(tempList);
           while(Parents.contains(child)) // while loop to check if the Childs Class has anyother child
           {
               int index = Parents.indexOf(child);// get the index of the Child
               child = Children.get(index);
               tempList.add(child);
           }
          
       }
       ArrayList<String> tempRelation = new ArrayList<String>();
       // Following line of Code Remove thoes relations that are partially part of any other relation.
       for(int i = 0; i < relation.size(); i++)
       {
           boolean isUnique = true;      
           for(int j = 0; j < relation.size(); j++)
           {
               if(i == j)
               {
                   continue;
               }
               if(relation.get(j).containsAll(relation.get(i)))
               {
                   isUnique = false;
               }
           }
           if(!isUnique)
           {
               relation.remove(i);
               i--;
           }
       }
       
       for(int i = 0; i < relation.size(); i++)
       {
           List<String> r = relation.get(i);
           String token = r.get(0);
           int tokenIndex = 0;
           for(int j = 0; j < relation.size(); j++)
           {
               if (i == j) {
                   continue;
               }
               tokenIndex = relation.get(j).indexOf(token);
               if(tokenIndex > 0)
               {
                   List<String> temp = relation.get(j).subList(0, tokenIndex);
                   r.addAll(0, temp);
               }               
           }
       }
       
       ArrayList<String> className = new ArrayList<String>();
       ArrayList<Integer> counter = new ArrayList<Integer>();
       // Following line of codes check how many time a class has been parent of any other class and counterArray List save this value
       for(int i = 0; i < Parents.size(); i++)
       {
           if(className.contains(Parents.get(i)))
           {
               counter.set(className.indexOf(Parents.get(i)), counter.get(className.indexOf(Parents.get(i))) + 1);
           }
           else
           {
               className.add(Parents.get(i));
               counter.add(1);
           }
       }
       
       // count child on each level
       ArrayList<String> doneClasses = new ArrayList<>();
        
       ArrayList<ArrayList<Integer>> counters = new ArrayList<ArrayList<Integer>>(10);
       ArrayList<String> nameOfClass = new ArrayList<>(10);
       for(int i = 0; i < relation.size(); i++)
       {
           if(doneClasses.contains(relation.get(i).get(0)))
           {
               continue;
           }
           String currentClass = relation.get(i).get(0);
           ArrayList<Integer> tempCounter = new ArrayList<Integer>();
           counters.add(tempCounter);
           for(int k = 0; k < relation.size(); k++)
           {
               if(!relation.get(k).get(0).equals(currentClass))
               {
                   continue;
               }
               
                ArrayList<String> list = relation.get(k);
                for (int j = 0; j < list.size(); j++) {
                    if(j >= tempCounter.size() && tempCounter.size() < list.size() && !doneClasses.contains(list.get(j)))
                    {
                        tempCounter.add(1);
                        doneClasses.add(list.get(j));
                    }
                    else
                    {
                        if(j != 0 && !doneClasses.contains(list.get(j)))
                        {
                            tempCounter.set(j, tempCounter.get(j) + 1);
                            doneClasses.add(list.get(j));
                        }
                    }
                }
                if(!nameOfClass.contains(currentClass))
                {
                    nameOfClass.add(currentClass);
                }
           }
       }
       //count total childs
       
      
       for(int i=0;i<counters.size();i++){
           int total=0;
           for(int j=0;j<counters.get(i).size();j++){
              
               total=total+counters.get(i).get(j);
           }
           total= total-1;
       totalchilds.add(nameOfClass.get(i)+" has Total Childs " + total);
       }
       //JOptionPane.showMessageDialog(null, totalchilds);
       String saadi="";
       for(int i = 0; i < nameOfClass.size(); i++)
       {
           saadi= "At ";
           for(int j = 0; j < counters.get(i).size(); j++)
           {
               saadi= saadi.concat("Level "+j+" = ");
               saadi=saadi.concat(counters.get(i).get(j) + "\t")+" ";
                    
           }
           
           values.add(saadi);
           System.out.println();
       }
        for(int i =0;i<files.size();i++){
                String clas_name = files.get(i).substring(files.get(i).lastIndexOf("\\")+1, files.get(i).lastIndexOf(".java"));
                String saad = path+"\\"+clas_name+".java";
                String pkg;
                String pkgg;
                pkg = saad.substring(0, saad.lastIndexOf("\\") );
                pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
                ArrayList<String> str = new ArrayList<String>();
                str.add(pkgg);
                while (!"src".equals(pkgg)) {
                    pkg = pkg.substring(0, pkg.lastIndexOf("\\"));
                    pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
                    str.add(pkgg);
                }
        
                String temp1 = "";
                for(int j = str.size() - 2; j >= 0; j--) {
                    temp1 = temp1 + "." + str.get(j);
                    
                }
                if (relation.size()!=0){
                    Parallelpackages.add(temp1);
                }
         }
         
     String col[] = {"Package", "Classes", "Level of DIT", "Childs At Levels","Impact Level"};
        Object[][] data = new Object[nameOfClass.size()][col.length];
      
        int level=0;
        for(int i=0;i<nameOfClass.size();i++)
            for(int j=0;j<col.length;j++){
                if(j==0){
                    data[i][j]= Parallelpackages.get(i);
                }
                if (j==1){
                    data[i][j]=nameOfClass.get(i);
                }
                if(j==2){
                    String[]words=values.get(i).toString().split(" ");
                    
                    for (int m =0;m<words.length;m++){
                        if (words[m].equals("Level")){
                            level++;
                        }
                    }
                    data[i][j]=level-1;
                    level =level-1;
                }
                if (j==3){
                   data[i][j]=values.get(i);
                }
                if (j==4){
                    if (level<=2){
                        data[i][j]="Low";
                        level=0;
                    }
                    else if(level ==3){
                        data[i][j]= "Medium";
                        level=0;
                    }
                    else if(level >=3){
                        data[i][j]= "High";
                        level=0;
                    }
                }
            }
        if (relation.size()!=0)
        {
            JOptionPane.showMessageDialog(null, "Parallel Inheritence Smell Doesn't exists in Code.");
        }
        else{
            jTable1.setModel(new DefaultTableModel(data, col));
          relations.setVisible(true);
          Relationship.setVisible(true);
          int row = jTable1.getRowCount();
          int cols = jTable1.getColumnCount();
         
          for (int l=0;l<row;l++)
              {
                  String value1 = jTable1.getValueAt(l,1).toString();
                  String value2=jTable1.getValueAt(l,3).toString();
                  if (value1.trim().length()!=0 && value2.trim().length()!=0)
                  {
                      check1.add(value1);
                      check2.add(value2);
                  }
              }
          String myRegex = "[^0-9]";
        int index = 0;
        for (String s : check2)
        {
             check2.set(index++, s.replaceAll(myRegex, ""));
        }
          relations.setEnabled(true);
          Relationship.setEnabled(true);
        } 
    }  
ArrayList<String> Objects= new ArrayList<String>();
ArrayList<String> AllClasses = new ArrayList<String>();
ArrayList<String> MessageChainLine = new ArrayList<String>();
ArrayList<String> FileName = new ArrayList<String>();
ArrayList<String> MessageChainPackage = new ArrayList<String>();
private void MessageChainBadSmell() throws FileNotFoundException
{
        clearAll();
        CompilationUnit cu;   
        for (String fileName : files)
        {
            cu = parse(fileName);
            GetAllClasses(fileName,cu);
        } 
        for (String filename :files)
        {
            getallobjects(filename);
        }
         //Removing Special Characters
        String myRegex = "[^a-zA-Z0-9]";
        int index = 0;
        for (String s : Objects)
        {
             Objects.set(index++, s.replaceAll(myRegex, ""));
        }
        for (int k=0;k<Objects.size();k++)
        {
            if(Objects.get(k).isEmpty())
            {
                Objects.remove(k);
            }
        }
          for (String filename :files){
            getallMessageChainLines(filename);
        }  
        String col[] = {"Package", "Class Name ", "Message Chain Line", "Impact"};
        Object[][] data = new Object[MessageChainLine.size()][col.length];
        for (int i=0;i<MessageChainLine.size();i++)
            for (int j=0;j<col.length;j++)
            {
                if (j==0)
                {
                    data[i][j]= MessageChainPackage.get(i);
                }
                if (j == 1)
                {
                    data[i][j]= FileName.get(i);
                }
                if (j==2)
                {
                    data[i][j]= MessageChainLine.get(i);
                }
                if (j==3)
                {   
                    int noofdots=0;
                    for (int m=0;m<MessageChainLine.get(i).length();m++)
                    {
                        String value = MessageChainLine.get(i);
                        char ch = value.charAt(m);
                        if (ch == '.')
                        {
                            noofdots++;
                        }
                    }
                    if (noofdots>=Integer.parseInt(jTextField_LowImpact_low.getText()) && noofdots<= Integer.parseInt(jTextField_LowImpact_up.getText()))
                    {
                        data[i][j]="Low";
                    } 
                    if (noofdots >= Integer.parseInt(jTextField_MedImpact_low.getText()) && noofdots <= Integer.parseInt(jTextField_MedImpact_up.getText()))
                    {
                        data[i][j]="Medium";
                    }
                    else
                    {
                    data[i][j]="High";
                    }
                }
            }
         if (MessageChainLine.isEmpty())
         {
             JOptionPane.showMessageDialog(null,"Smell Does Not Exist" );
         }
         else
         {
            jTable1.setModel(new DefaultTableModel(data, col));
         }
        
}
    private void getallobjects(String file) throws FileNotFoundException{
        
        String fileName = file;
        String line ="";
        File f = new File(file);
        int i=0;
        Scanner sc = new Scanner(f);
         while(sc.hasNext())
        {
                line = sc.nextLine();
                String[] temp = line.trim().split(" ");
                if (temp.length<2)
                {
                    continue;
                }
                else
                {
                    //
                    for(int j=0;j<temp.length;j++)
                        if (AllClasses.contains(temp[j])&&line.contains("=") &&line.contains("new") &&line.endsWith(");"))
                            if (!Objects.contains(temp[j+1]))
                        {
                            Objects.add(temp[j+1]);
                        }
                }
                
        }           
    }
    private void GetAllClasses(String file,CompilationUnit cu) throws FileNotFoundException
    {
        
             String line = "";
             File files = new File(file);
             Scanner sc = new Scanner(files);
             while(sc.hasNext())
             {
                 line=sc.nextLine().trim();
                 if(line.startsWith("//"))
                 {
                     continue;
                 
                 }
                 if (line.startsWith("/*"))
                 {
                     
                     while(sc.hasNext())
                     {
                         if (line.endsWith("*/"))
                         {
                             line=sc.nextLine().trim();
                             break;
                              
                         }
                            
                         line=sc.nextLine().trim();
                     }
                 }
                   
                     
                 String temp [] = line.split("[\\s | \\{]");
                 for (int i=0;i<temp.length;i++)
                 {
                     if (temp[i].equals("private")||temp[i].equals("public")||temp[i].equals("protected"))
                     {
                         if (temp[i+1].equals("final"))
                         {
                              if (temp[i+2].equals("class"))
                             {
                                 AllClasses.add(temp[i+3]);
                                 break;
                             }
                         }
                     }
                     if (temp[i].equals("private")||temp[i].equals("public")||temp[i].equals("protected"))
                     {
                         if (temp[i+1].equals("class"))
                         {
                             AllClasses.add(temp[i+2]);
                             break;
                         }
                   
                     }
                     if (temp[i].equals("class"))
                     {
                         AllClasses.add(temp[i+1]);
                         break;
                     }
                      if (temp[i].equals("private")||temp[i].equals("public")||temp[i].equals("protected"))
                     {
                         if (temp[i+1].equals("abstract"))
                         {
                              if (temp[i+2].equals("class"))
                             {
                                 AllClasses.add(temp[i+3]);
                                 break;
                             }
                         }
                     }
                     break;  
                 }
               
             }
    }

private void getallMessageChainLines(String file) throws FileNotFoundException
{
        String line ="";
        int noofdots=0;
        int last_index=0;
        String Array="";
        File files = new File(file);
        Scanner sc = new Scanner(files);
        while(sc.hasNext())
        {
                line = sc.nextLine();
                line=line.trim();
                String[] temp = line.split("[.(]");
                ArrayList<String> temp2 = new ArrayList<>();
                for(String t : temp)
                {
                     if(Objects.contains(t))
                     {
                          temp2.add(t);
                     }
                }
                    int counter=0;
                    for (int h =0;h<temp2.size();h++){
                        for(int k = 0; k < Objects.size(); k++)
                        {
                            if(Objects.get(k).endsWith(temp2.get(h).trim()))
                            {
                                counter++;
                            }
                        }
                    }
                    if (temp.length >=2  && line.trim().endsWith(");") && !MessageChainLine.contains(line) &&counter >=(Integer.parseInt(jTextField_LowImpact_low.getText())) )
                    {
                        counter = 0;
                        MessageChainLine.add(line);
                        String filname=files.getName();
                        FileName.add(filname);
                        String pkg;
                        String pkgg;
                        pkg = file.substring(0, file.lastIndexOf("\\") - 1);
                        pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
                         ArrayList<String> str = new ArrayList<String>();
                      str.add(pkgg);
                     while (!"src".equals(pkgg)) 
                     {
                         pkg = pkg.substring(0, pkg.lastIndexOf("\\")-1);
                         pkgg = pkg.substring(pkg.lastIndexOf("\\")+1);
                         str.add(pkgg);
                      }
                     String temp1 = "";
                     for (int k = str.size() - 1; k >= 0; k--) 
                     {
                          temp1 = temp1 + "." + str.get(k);
                      }
                        MessageChainPackage.add(temp1);
                    }
                }            
} 
    private void getChildAndParents(String fileName) throws FileNotFoundException, IOException{
     String path = fileName;
     String root="";
     String ChildNode="";
     FileReader fr =new FileReader(path);
     BufferedReader br = new BufferedReader(fr);
     String line;
         while ((line= br.readLine())!=null){ 
             if (line.contains("class") && line.contains("extends")){
                 String[] tokens = line.split("\\s|\\{|\\< |\\>");
                 for(int i = 0; i < tokens.length; i++)
                 {
                     if(tokens[i].equals("extends"))
                     {
                         String s1 = tokens[i - 1];
                         String s2 = tokens[i + 1];
                         if(s1.endsWith("{"))
                         {
                             s1 = s1.substring(0, s1.length() - 1);
                         }
                         if(s2.endsWith("{"))
                         {
                             s2 = s2.substring(0, s2.length() - 1);
                         }
                         Children.add(s1);
                         Parents.add(s2);
                     }
                 }
             }         
         }    
         
    }
    private static void readLinesUsingFileReader() throws IOException
{
    File file = new File("c:/temp/data.txt");
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);
    String line;
    while((line = br.readLine()) != null){
        if(line.contains("password")){
            System.out.println(line);
        }
    }
    br.close();
    fr.close();
}
    private void LargeClassSmellDetection()
    {
        
        clearAll();
        CompilationUnit cu;

        for (String fileName : files) {
            cu = parse(fileName);
            
            largeClassSmellCount(fileName, cu);
        }
        ArrayList<Integer> fields = new ArrayList<>();
        int index =0;
        for(int k=0;k<largeClassfieldCount.size();k++)
        {
            if(largeClassfieldCount.get(k)>=LargeClassThreshold.var_lower_low && 
                    largeClassMethods.get(k) >= LargeClassThreshold.method_lower_low)
            {
                largeClassPackage.set(index, largeClassPackage.get(k));
                
                largeClasses.set(index, largeClasses.get(k));
                fields.add(index, largeClassfieldCount.get(k));
                largeClassMethods.set(index, largeClassMethods.get(k));
                largeClassfields.set(index, largeClassfields.get(k));
                
                largeClassMethodsList.set(index, largeClassMethodsList.get(k));
                index++;
            }
        }
        String col[] = {"Package", "Class", "No of Fields", "No of Method", "Impact Level"};
        Object[][] data = new Object[fields.size()][col.length];
        for (int i = 0; i < fields.size(); i++) {
            for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = largeClassPackage.get(i);
                } else if (j == 1) {
                    data[i][j] = largeClasses.get(i);
                } else if (j == 2) {
                    data[i][j] = fields.get(i);
                } else if (j == 3) {
                    data[i][j] = largeClassMethods.get(i);
                }
                else if (j == 4) {
                     
                        double var_impact = 0;
                        double method_impact = 0;
                        var_impact = fields.get(i);
                        method_impact = largeClassMethods.get(i);
                        if (var_impact >= LargeClassThreshold.var_lower_low && var_impact <= LargeClassThreshold.var_lower_up ){
                            if((method_impact >= LargeClassThreshold.method_lower_low && method_impact <= LargeClassThreshold.method_lower_up)
                                    || (method_impact >= LargeClassThreshold.method_med_low && method_impact <= LargeClassThreshold.method_med_up))
                            {
                                data[i][j] = "Low";
                            }
                            else if(method_impact >= LargeClassThreshold.method_high_low)
                            {
                                data[i][j] = "Medium";
                            }
                            
                        } 
                        else if (var_impact >= LargeClassThreshold.var_med_low && var_impact <= LargeClassThreshold.var_med_up) 
                        {
                            data[i][j] = "Medium";
                        } 
                        else if (var_impact >= LargeClassThreshold.var_lower_low) 
                        {
                            if(method_impact >= LargeClassThreshold.method_lower_low && method_impact <= LargeClassThreshold.method_lower_up)
                            {
                                data[i][j] = "Medium";
                            }
                            else if((method_impact >= LargeClassThreshold.method_med_low && method_impact <= LargeClassThreshold.method_med_up)
                                    || method_impact >= LargeClassThreshold.method_high_low)
                            {
                                data[i][j] = "High";
                            }
                            
                        }
                    
                }
            }
        }
        jTable1.setModel(new DefaultTableModel(data, col));
        longParaSuggestion = "As with a class with a huge wad of variables, the usual solution for a class with too much code is either to Extract Class or Extract \n" +
"Subclass. A useful trick is to determine how clients use the class and to use Extract Interface for each of these uses. That may give \n" +
"you ideas on how you can further break up the class.";
        JOptionPane.showMessageDialog(null, "Total Large Class BadSmells Found= " + fields.size());
    }
    private ArrayList<String> largeClassPackage = new ArrayList<>();
    private ArrayList<String> largeClasses = new ArrayList<>();
    private ArrayList<String> largeClassfields = new ArrayList<>();
    private ArrayList<Integer> largeClassfieldCount = new ArrayList<>();
    private ArrayList<Integer> largeClassMethods = new ArrayList<>();
    private ArrayList<String> largeClassMethodsList = new ArrayList<>(); //29
    private void largeClassSmellCount(String fileName, CompilationUnit cu)
    {
        SelectClassFiles visitor = new SelectClassFiles();
        cu.accept(visitor);
        LargeClassMethods visitor1 = new LargeClassMethods();
        cu.accept(visitor1);
        largeClassfieldCount.add(visitor.getCounter());
        String pkg;
        String pkgg;
        pkg = fileName.substring(0, fileName.lastIndexOf("\\") - 1);
        pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
        ArrayList<String> str = new ArrayList<String>();
        str.add(pkgg);
        while (!"src".equals(pkgg)) {
            pkg = pkg.substring(0, pkg.lastIndexOf("\\") - 1);
            pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
            str.add(pkgg);
        }
        String temp = "";
        for (int i = str.size() - 2; i >= 0; i--) {
            temp = temp + "." + str.get(i);
        }
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        if(largeClassfieldCount.size() !=0)
        {
            largeClassPackage.add(temp);
            largeClasses.add(fileName);
            largeClassfields.add(visitor.getFieldName());
            largeClassMethods.add(visitor1.getTotalMethods());
            largeClassMethodsList.add(visitor1.getMethodsList());
        }  
    }
    
    ArrayList<Integer> getter = new ArrayList<>();
    ArrayList<Integer> setter = new ArrayList<>();
    ArrayList<Integer> constructor = new ArrayList<>();
    ArrayList<Integer> totalmethods = new ArrayList<>();
    private ArrayList<String> dataClassPackage = new ArrayList<>();
    private ArrayList<String> dataClasses = new ArrayList<>(); //35
    
    private void DataClassSmellDataSetter()
    {
        setTextFieldEnable();
        clearAll();
        CompilationUnit cu;

        for (String fileName : files) 
        {
            cu = parse(fileName);
            largeClassSmellCount(fileName, cu);
            dataClassSmell(fileName, cu);
        }
        
        ArrayList<Integer> totaldata = new ArrayList<>();
        
        int index =0;
        for(int k=0;k<totalmethods.size();k++)
        {
            
            if(totalmethods.get(k) == getter.get(k) + setter.get(k) + constructor.get(k) && totalmethods.get(k)>0
                    && getter.get(k) !=0 && setter.get(k)!=0 && constructor.get(k)!=0)
            {
                dataClassPackage.set(index, dataClassPackage.get(k));
                dataClasses.set(index, dataClasses.get(k));
                largeClassfieldCount.set(index, largeClassfieldCount.get(k));
                totaldata.add(index, totalmethods.get(k));
                constructor.set(index, constructor.get(k));
                getter.set(index, getter.get(k));
                setter.set(index, setter.get(k));
                index++;
            }
        }
        String col[] = {"Package", "Class", "No of Fields", "Total Methods", "Constructors", "Setters", "Getters"};
        Object[][] data = new Object[totaldata.size()][col.length];
        for (int i = 0; i < totaldata.size(); i++) {
            for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = dataClassPackage.get(i);
                } else if (j == 1) {
                    data[i][j] = dataClasses.get(i);
                } else if (j == 2) {
                    data[i][j] = largeClassfieldCount.get(i);
                } else if (j == 3) {
                    data[i][j] = totaldata.get(i);
                } else if (j == 4) {
                    data[i][j] = constructor.get(i);
                } else if (j == 5) {
                    data[i][j] = getter.get(i);
                } else if (j == 6) {
                    data[i][j] = setter.get(i);
                }
            }
        }
        jTable1.setModel(new DefaultTableModel(data, col));
        longParaSuggestion = "In early stages these classes may have public fields. If so, you should immediately apply Encapsulate Field before anyone notices. If \n" +
"you have collection fields, check to see whether they are properly encapsulated and apply Encapsulate Collection if they aren't. Use \n" +
"Remove Setting Method on any field that should not be changed.\n" +
"Look for where these getting and setting methods are used by other classes. Try to use Move Method to move behavior into the data \n" +
"class. If you can't move a whole method, use Extract Method to create a method that can be moved. After a while you can start using \n" +
"Hide Method on the getters and setters.";
        JOptionPane.showMessageDialog(null, "Total Data Class BadSmells Found= " + totaldata.size());
    }
     
    private void dataClassSmell(String fileName, CompilationUnit cu)
    {
        LongMethodMethodDeclaration visitor = new LongMethodMethodDeclaration();
        cu.accept(visitor);
        totalmethods.add(visitor.getTotalMethods());
        String pkg;
        String pkgg;
        pkg = fileName.substring(0, fileName.lastIndexOf("\\") - 1);
        pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
        ArrayList<String> str = new ArrayList<String>();
        str.add(pkgg);
        while (!"src".equals(pkgg)) {
            pkg = pkg.substring(0, pkg.lastIndexOf("\\") - 1);
            pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
            str.add(pkgg);
        }
        String temp = "";
        for (int i = str.size() - 2; i >= 0; i--) {
            temp = temp + "." + str.get(i);
        }
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        if(totalmethods.size() !=0)
        {
            dataClassPackage.add(temp);
            dataClasses.add(fileName);
            constructor.add(visitor.getConstructor());
            getter.add(visitor.getGetter());
            setter.add(visitor.getSetter());
        }
    }
    
    private void lazyClassSmellDataSetter()
    {
        
        clearAll();
        CompilationUnit cu;

        for (String fileName : files) 
        {
            cu = parse(fileName);
            int lines = cu.toString().split("\n").length;
            lazyClassLines.add(lines);
            lazyClassSmell(fileName, cu);
        }
        ArrayList<String> temp = new ArrayList<>();
        
        int index =0;
        for(int k=0;k<lazyClassClas.size();k++)
        {
            
            if(lazyClassLines.get(k) <= LazyClassThreshold.LOC && lazyClassMethods.get(k) <= LazyClassThreshold.total_methods
                    && lazyClassWeightMethod.get(k) <= LazyClassThreshold.wmc)
            {
                lazyClassPackage.set(index, lazyClassPackage.get(k));
                temp.add(index, lazyClassClas.get(k));
                lazyClassLines.set(index, lazyClassLines.get(k));
                lazyClassMethods.set(index, lazyClassMethods.get(k));
                lazyClassWeightMethod.set(index, lazyClassWeightMethod.get(k));
                index++;
            }
        }
        String col[] = {"Package", "Class", "No of Lines", "Methods per Class", "Weightd Method per Class"};
        Object[][] data = new Object[temp.size()][col.length];
        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = lazyClassPackage.get(i);
                } else if (j == 1) {
                    data[i][j] = temp.get(i);
                } else if (j == 2) {
                    data[i][j] = lazyClassLines.get(i);
                } else if (j == 3) {
                    data[i][j] = lazyClassMethods.get(i);
                } else if (j == 4) {
                    data[i][j] = lazyClassWeightMethod.get(i);
                }
            }
        }
        jTable1.setModel(new DefaultTableModel(data, col));
        longParaSuggestion = "If you have subclasses that aren't doing enough, try to use Collapse Hierarchy. Nearly useless components should be subjected to \n" +
"inline class.";
        JOptionPane.showMessageDialog(null, "Total Lazy Class BadSmells Found= " + temp.size());
    }
    ArrayList<String> lazyClassPackage = new ArrayList<>();
    ArrayList<String> lazyClassClas = new ArrayList<>();
    ArrayList<Integer> lazyClassLines = new ArrayList<>();
    ArrayList<Integer> lazyClassMethods = new ArrayList<>();
    ArrayList<Integer> lazyClassWeightMethod = new ArrayList<>();// 40
    private void lazyClassSmell(String fileName, CompilationUnit cu)
    {
        LazyClassSmellDetector visitor = new LazyClassSmellDetector();
        cu.accept(visitor);
        String pkg;
        String pkgg;
        pkg = fileName.substring(0, fileName.lastIndexOf("\\") - 1);
        pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
        ArrayList<String> str = new ArrayList<String>();
        str.add(pkgg);
        while (!"src".equals(pkgg)) {
            pkg = pkg.substring(0, pkg.lastIndexOf("\\") - 1);
            pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
            str.add(pkgg);
        }
        String temp = "";
        for (int i = str.size() - 2; i >= 0; i--) {
            temp = temp + "." + str.get(i);
        }
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        for(int i=0;i<visitor.getMethodsComplexity().size();i++){
                longMethodComplexity.add(visitor.getMethodsComplexity().get(i));
            }
        if(lazyClassLines.size() != 0)
        {
            lazyClassPackage.add(temp);
            lazyClassClas.add(fileName);
            
            lazyClassMethods.add(visitor.getTotalMethods());
            lazyClassWeightMethod.add(visitor.getWeightMethod());
        }
     //   ###rintln("total........"+visitor.getTotalMethods());
        
    }
    
   
    
    private void setTextFieldDisable()
    {
        jTextField_LowImpact_low.setEnabled(false);
        jTextField_LowImpact_up.setEnabled(false);
        jTextField_MedImpact_low.setEnabled(false);
        jTextField_MedImpact_up.setEnabled(false);
        jTextField_HighImpact_low.setEnabled(false);
        jTextField_HighImpact_up.setEnabled(false);
    }
    
    private void setTextFieldEnable()
    {
        jTextField_LowImpact_low.setEnabled(true);
        jTextField_LowImpact_up.setEnabled(true);
        jTextField_MedImpact_low.setEnabled(true);
        jTextField_MedImpact_up.setEnabled(true);
        jTextField_HighImpact_low.setEnabled(true);
        jTextField_HighImpact_up.setEnabled(true);
    }
    
    private boolean checkThresholdLimit()
    {
        if(!jTextField_LowImpact_low.getText().isEmpty() &&  !jTextField_LowImpact_up.getText().isEmpty()
                && !jTextField_MedImpact_low.getText().isEmpty() && !jTextField_MedImpact_up.getText().isEmpty())
        {
            if((Integer.parseInt(jTextField_LowImpact_low.getText()) > Integer.parseInt(jTextField_LowImpact_up.getText()))
                    || (Integer.parseInt(jTextField_MedImpact_low.getText()) > Integer.parseInt(jTextField_MedImpact_up.getText())))
            {
                return false;
            }
            else
            {
                return true;
            }
                    
        }
        else{
            return false;
        }
    }
    
    // ************************ Extended Code Smells ******************************
    
        // ************************ ShotgunSurgery Code Smells Function  ******************************
    public ArrayList<String[]> s_surgery_data = new ArrayList<>();
    private void shotgunsurgery(int low_ins , int low_m) throws FileNotFoundException, IOException
{
    
    clearAll();
        
        CompilationUnit cu;
        String col[] = {"Package", "Class", "ShotgunSurgery Method", "No of defined Methods","No of calling methods",  "Impact"};
        s_Surgery ss =  new s_Surgery();
        ArrayList<String> list_all_methods=new ArrayList<String>();
       
         for (String fileName : files) {
             File file  = new File(fileName);
             String source = FileUtils.readFileToString(file);
             ASTParser parser = ASTParser.newParser(AST.JLS3);
             parser.setSource(source.toCharArray());
             cu = (CompilationUnit) parser.createAST(null); 
            AST_declaration list = new AST_declaration();
            cu.accept(list);
            for(String m:list.get_method_list())
            {
                list_all_methods.add(m);
            }
         
         }
         
            for (int i=0;i<files.size();i++) {
            File file  = new File(files.get(i));
             String source = FileUtils.readFileToString(file);
             ASTParser parser = ASTParser.newParser(AST.JLS3);
             parser.setSource(source.toCharArray());
             cu = (CompilationUnit) parser.createAST(null);
            ss.accept_data(files,files.get(i),list_all_methods,low_ins,low_m);
            cu.accept(ss);
            }
            s_surgery_data.clear();
         for(String[] a:ss.get_total_ssurgery())
            {
                    s_surgery_data.add(a);
            }
         
         ArrayList<String[]> te = new ArrayList<>();
         for(String[] a:s_surgery_data)
         {
             if(Integer.parseInt(a[3])>=low_m  && Integer.parseInt(a[4])>=low_m)
                 te.add(a);
         }
         Object[][] data = new Object[te.size()][col.length];
        
         
         for(int i=0;i<te.size();i++){
        for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = te.get(i)[0];
                } else if (j == 1) {
                    data[i][j] = te.get(i)[1];
                } else if (j == 2) {
                    data[i][j] = te.get(i)[2];
                } else if (j == 3) {
                    data[i][j] = te.get(i)[3];
                } 
                else if (j == 4) {
                    data[i][j] = te.get(i)[4];}
                
                else if (j == 5) {
                  
                    {
                        int method_impact = Integer.parseInt(te.get(i)[4]);
                        int var_impact = Integer.parseInt(te.get(i)[3]);;
                        if (var_impact >= Shotgun_Threshold.var_lower_low && var_impact <= Shotgun_Threshold.var_lower_up ){
                            if((method_impact >= Shotgun_Threshold.method_lower_low && method_impact <= Shotgun_Threshold.method_lower_up)
                                    || (method_impact >= Shotgun_Threshold.method_med_low && method_impact <= Shotgun_Threshold.method_med_up))
                            {
                                data[i][5] = "Low";
                            }
                       else if(method_impact >= Shotgun_Threshold.method_high_low)
                            {
                                data[i][5] = "Medium";
                            }
                            
                        } 
                        else if (var_impact >= Shotgun_Threshold.var_med_low && var_impact <= Shotgun_Threshold.var_med_up) 
                        {
                            data[i][5] = "Medium";
                        } 
                        else if (var_impact >= Shotgun_Threshold.var_lower_low) 
                        {
                            if(method_impact >= Shotgun_Threshold.method_lower_low && method_impact <= Shotgun_Threshold.method_lower_up)
                            {
                                data[i][5] = "Low";
                            }
                            else if((method_impact >= Shotgun_Threshold.method_med_low && method_impact <= Shotgun_Threshold.method_med_up)
                                    || method_impact >= Shotgun_Threshold.method_high_low)
                            {
                                data[i][5] = "Medium";
                            }
                            
                        }
                    
                }
            }
            
         }
          
    }    
         jTable1.setModel(new DefaultTableModel(data, col));
         longParaSuggestion = "Fortunately the cure is obvious; the method clearly wants to be elsewhere, so you use Move Method to get it there. Sometimes only \n" +
"part of the method suffers from envy; in that case use Extract Method on the jealous bit and Move Method to give it a dream home. Of \n" +
"course not all cases are cut-and-dried. Often a method uses features of several classes, so which one should it live with? The \n" +
"heuristic we use is to determine which class has most of the data and put the method with that data. This step is often made easier \n" +
"if Extract Method is used to break the method into pieces that go into different places.";
          JOptionPane.showMessageDialog(null, "Total Shotgun Surgery BadSmells Found= " + jTable1.getRowCount());
   
    
    
}
    
    
        // ************************ Brainmethod Code Smells Function  ******************************
    
   
    private static ArrayList<String> BrainMethodpackag = new ArrayList<String>();
    private static ArrayList<String> BrainMethodclass = new ArrayList<String>();
    private static ArrayList<String> BrainMethodmethod = new ArrayList<String>();
    private static ArrayList<Integer> BrainMethodlines = new ArrayList<Integer>();
    
        public static int BrainMethodThreshold = 0;
        
          private ArrayList<String> brainMethodBody = new ArrayList<>();
    private ArrayList<Integer> brainMethodComplexity = new ArrayList<>();

 private void BrainMethodSmell() {
        clearAll();
        CompilationUnit cu;

        for (String fileName : files) {
            cu = parse(fileName);
            longMethodBadSmell(fileName, cu);
            lazyClassSmell(fileName, cu);
        }
        ArrayList<Double> com = new ArrayList<>();
        for (int i = 0; i < longMethodlines.size(); i++)
        {
            com.add(longMethodComplexity(i));
        }
        ArrayList<Double> complexity = new ArrayList<>();
        int index =0;
        for(int k=0;k<com.size();k++)
        {
            if(com.get(k)>=BrainMethodThreshold)
            {
               
                longMethodpackag.set(index, longMethodpackag.get(k));
                longMethodclass.set(index, longMethodclass.get(k));
                longMethodmethod.set(index, longMethodmethod.get(k));
                longMethodlines.set(index, longMethodlines.get(k));
                longMethodBody.set(index, longMethodBody.get(k));
                complexity.add(com.get(k));
                index++;
            }
        }
        String col[] = {"Package", "Class", "Brain Method","Lines","if Statements","Complexity", "Impact Level"};
        Object[][] data = new Object[complexity.size()][col.length];
        for (int i = 0; i < complexity.size(); i++) {
            for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = longMethodpackag.get(i);
                } else if (j == 1) {
                    data[i][j] = longMethodclass.get(i);
                } else if (j == 2) {
                    data[i][j] = longMethodmethod.get(i);
                } else if (j == 3) {
                    data[i][j] = longMethodlines.get(i);
                }  else if (j == 4) {
                    data[i][j] = longMethodComplexity.get(i);
                }
                else if (j == 5) {
                    data[i][j] = complexity.get(i);
                } else if (j == 6) {
                    if (jTextField_LowImpact_low.getText().isEmpty() || jTextField_LowImpact_up.getText().isEmpty()
                            || jTextField_MedImpact_low.getText().isEmpty() || jTextField_MedImpact_up.getText().isEmpty()
                            || jTextField_MedImpact_low.getText().isEmpty() || jTextField_MedImpact_up.getText().isEmpty()) {
                        double impact = 0;
                        impact = complexity.get(i);
                        if (impact >= 8 && impact < 10) {
                            data[i][j] = "Low";
                        } else if (impact >= 10 && impact < 15) {
                            data[i][j] = "Medium";
                        } else if (impact >= 15) {
                            data[i][j] = "High";
                        }
                    } else {
                        double impact = 0;
                        impact = complexity.get(i);
                        if (impact >= Integer.parseInt(jTextField_LowImpact_low.getText()) && impact < Integer.parseInt(jTextField_LowImpact_up.getText())) {
                            data[i][j] = "Low";
                        } else if (impact >= Integer.parseInt(jTextField_MedImpact_low.getText()) && impact < Integer.parseInt(jTextField_MedImpact_up.getText())) {
                            data[i][j] = "Medium";
                        } else if (impact >= Integer.parseInt(jTextField_HighImpact_low.getText())) {
                            data[i][j] = "High";
                        }
                    }
                }
            }
        }
        jTable1.setModel(new DefaultTableModel(data, col));
        longParaSuggestion = " Apply the â€œExtract Methodâ€� refactoring technique to get rid of â€œLong Methodâ€� bad smell. Preprocessing is needed: "
                + "\nâ€¢if the new method has " +
"a long parameter list after applying the â€œExtract Methodâ€� refactoring technique.\n"
                + " The occurrence of long parameter list is due to the" +
"presence of temporary variables. Use â€œReplace Temp with Queryâ€� refactoring technique to eliminate temporary variables.â€�\nâ€¢The long " +
"parameter list can be made short by using â€œIntroduce Parameter Objectâ€� or â€œReplace Method with Method Objectâ€�.";
        JOptionPane.showMessageDialog(null, "Total Brain Method BadSmells Found= " + complexity.size());
    }
    
    
 
 
 
 
   
           
                // ************************ Divergent Change Code Smells Function  ******************************

            
        
   public static int DivergentChangeCThreshold = 0;
 
 private void DivergentChangeSmellDetection()
    {
        clearAll();
        CompilationUnit cu;
        
       
        
        for (String fileName : files) {
            cu = parse(fileName);
            
            DivergentChangeClassSmellCount(fileName, cu);
        }
        ArrayList<Integer> fields = new ArrayList<>();
        int index =0;
       // int methodno=0;
      //  methodno=largeClassMethodsList.size()-largeClassMethods.size();
        for(int k=0;k<largeClassfieldCount.size();k++)
        {
            if(largeClassfieldCount.get(k)>=DivergentChangeThreshold.var_lower_low && 
                    largeClassMethods.get(k) >= DivergentChangeThreshold.method_lower_low )
            {
                DivergentChangeClassPackage.set(index, DivergentChangeClassPackage.get(k));
                
                DivergentChangeClassClasses.set(index, DivergentChangeClassClasses.get(k));
                fields.add(index, largeClassfieldCount.get(k));
                largeClassMethods.set(index, largeClassMethods.get(k));
                DivergentChangeClassfields.set(index, DivergentChangeClassfields.get(k));
                
                largeClassMethodsList.set(index, largeClassMethodsList.get(k));
                DivergentChangeClassMethodsList.set(index, DivergentChangeClassMethodsList.get(k));
                

                index++;
                
            }
          
        }
        
       
        
        String col[] = {"Package", "Class", "No of Fields", "No of Method","No of defined method", "Impact Level"};
        Object[][] data = new Object[fields.size()][col.length];
        for (int i = 0; i < fields.size(); i++) {
            for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = DivergentChangeClassPackage.get(i);
                } else if (j == 1) {
                    data[i][j] = DivergentChangeClassClasses.get(i);
                } else if (j == 2) {
                    data[i][j] = fields.get(i);
                } else if (j == 3) {
                    data[i][j] = largeClassMethods.get(i);
                }else if (j == 4) {
                    data[i][j] = fields.get(i)*DivergentChangeClassMethodsList.size()/largeClassMethods.get(i);
                }
                else if (j == 5) {
                     
                        double var_impact = 0;
                        double method_impact = 0;
                        var_impact = fields.get(i);
                        method_impact = largeClassMethods.get(i);
                        if (var_impact >= DivergentChangeThreshold.var_lower_low && var_impact <= DivergentChangeThreshold.var_lower_up ){
                            if((method_impact >= DivergentChangeThreshold.method_lower_low && method_impact <= DivergentChangeThreshold.method_lower_up)
                                    || (method_impact >= DivergentChangeThreshold.method_med_low && method_impact <= DivergentChangeThreshold.method_med_up))
                            {
                                data[i][j] = "Low";
                            }
                            else if(method_impact >= DivergentChangeThreshold.method_high_low)
                            {
                                data[i][j] = "Medium";
                            }
                            
                        } 
                        else if (var_impact >= DivergentChangeThreshold.var_med_low && var_impact <= DivergentChangeThreshold.var_med_up) 
                        {
                            data[i][j] = "Medium";
                        } 
                        else if (var_impact >= DivergentChangeThreshold.var_lower_low) 
                        {
                            if(method_impact >= DivergentChangeThreshold.method_lower_low && method_impact <= DivergentChangeThreshold.method_lower_up)
                            {
                                data[i][j] = "Medium";
                            }
                            else if((method_impact >= DivergentChangeThreshold.method_med_low && method_impact <= DivergentChangeThreshold.method_med_up)
                                    || method_impact >= DivergentChangeThreshold.method_high_low)
                            {
                                data[i][j] = "High";
                            }
                            
                        }
                    
                }
            }
        }
        jTable1.setModel(new DefaultTableModel(data, col));
        longParaSuggestion = "As with a class with a huge wad of variables, the usual solution for a class with too much code is either to Extract Class or Extract \n" +
"Subclass. A useful trick is to determine how clients use the class and to use Extract Interface for each of these uses. That may give \n" +
"you ideas on how you can further break up the class.";
        JOptionPane.showMessageDialog(null, "Total DivergentChange Class BadSmells Found= " + fields.size());
    }
    private ArrayList<String> DivergentChangeClassPackage = new ArrayList<>();
    private ArrayList<String> DivergentChangeClassClasses = new ArrayList<>();
    private ArrayList<String> DivergentChangeClassfields = new ArrayList<>();
    private ArrayList<Integer> DivergentChangeClassfieldCount = new ArrayList<>();
    private ArrayList<Integer> DivergentChangeClassMethods = new ArrayList<>();
    private ArrayList<String> DivergentChangeClassMethodsList = new ArrayList<>(); //29
    private void DivergentChangeClassSmellCount(String fileName, CompilationUnit cu)
    {
        SelectClassFiles visitor = new SelectClassFiles();
        cu.accept(visitor);
        DivergentChangeClassMethods visitor1 = new DivergentChangeClassMethods();
        cu.accept(visitor1);
        largeClassfieldCount.add(visitor.getCounter());
        String pkg;
        String pkgg;
        pkg = fileName.substring(0, fileName.lastIndexOf("\\") - 1);
        pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
        ArrayList<String> str = new ArrayList<String>();
        str.add(pkgg);
        while (!"src".equals(pkgg)) {
            pkg = pkg.substring(0, pkg.lastIndexOf("\\") - 1);
            pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
            str.add(pkgg);
        }
        String temp = "";
        for (int i = str.size() - 2; i >= 0; i--) {
            temp = temp + "." + str.get(i);
        }
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        if(largeClassfieldCount.size() !=0)
        {
            DivergentChangeClassPackage.add(temp);
            DivergentChangeClassClasses.add(fileName);
            DivergentChangeClassfields.add(visitor.getFieldName());
            largeClassMethods.add(visitor1.getTotalMethods());
            largeClassMethodsList.add(visitor1.getMethodsList());
            DivergentChangeClassMethodsList.add(visitor1.getMethodsList2());
            
        }  
    }
    
    
    
    
    
    
    
    //Method for largeclass smell button
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        LongParameter_btn = new javax.swing.JButton();
        LongMethod_btn = new javax.swing.JButton();
        SwitchStatement_btn = new javax.swing.JButton();
        FeatureEnvy_btn = new javax.swing.JButton();
        DataClass_btn = new javax.swing.JButton();
        LazyClass_btn = new javax.swing.JButton();
        LargeClass_btn = new javax.swing.JButton();
        Comments_btn = new javax.swing.JButton();
        obsession_btn1 = new javax.swing.JButton();
        obsession_btn2 = new javax.swing.JButton();
        speculative_Generality = new javax.swing.JButton();
        obsession_btn4 = new javax.swing.JButton();
        Message_Chain = new javax.swing.JButton();
        middleman_button = new javax.swing.JButton();
        s_g_v_b = new javax.swing.JButton();
        short_method_b = new javax.swing.JButton();
        s_Surgery_btn = new javax.swing.JButton();
        brainmethodButton = new javax.swing.JButton();
        divergChangeButton = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Relationship = new javax.swing.JButton();
        relations = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField_browse = new javax.swing.JTextField();
        browse = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel_threshold = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField_LowImpact_low = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField_LowImpact_up = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jTextField_MedImpact_low = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_MedImpact_up = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTextField_HighImpact_low = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_HighImpact_up = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        detect_btn = new javax.swing.JButton();
        reset_btn = new javax.swing.JButton();
        chart_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1150, 720));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Extended Code Smell Visualizer");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/insect-icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(79, 79, 79))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Code Smells", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        LongParameter_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/swatch_10_RK9811-00_240_24.jpg"))); // NOI18N
        LongParameter_btn.setText("Long Parameter List");
        LongParameter_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LongParameter_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LongParameter_btn.setIconTextGap(12);
        LongParameter_btn.setPreferredSize(new java.awt.Dimension(110, 110));
        LongParameter_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LongParameter_btnActionPerformed(evt);
            }
        });

        LongMethod_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/images (2).jpg"))); // NOI18N
        LongMethod_btn.setText("Long Method");
        LongMethod_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LongMethod_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LongMethod_btn.setIconTextGap(12);
        LongMethod_btn.setPreferredSize(new java.awt.Dimension(110, 110));
        LongMethod_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LongMethod_btnActionPerformed(evt);
            }
        });

        SwitchStatement_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/yellow.jpg"))); // NOI18N
        SwitchStatement_btn.setText("Switch Statements");
        SwitchStatement_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SwitchStatement_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SwitchStatement_btn.setIconTextGap(12);
        SwitchStatement_btn.setPreferredSize(new java.awt.Dimension(110, 110));
        SwitchStatement_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwitchStatement_btnActionPerformed(evt);
            }
        });

        FeatureEnvy_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/orange.png"))); // NOI18N
        FeatureEnvy_btn.setText("Feature Envy");
        FeatureEnvy_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FeatureEnvy_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        FeatureEnvy_btn.setIconTextGap(12);
        FeatureEnvy_btn.setPreferredSize(new java.awt.Dimension(110, 110));
        FeatureEnvy_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeatureEnvy_btnActionPerformed(evt);
            }
        });

        DataClass_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/cyan.gif"))); // NOI18N
        DataClass_btn.setText("Data Class");
        DataClass_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DataClass_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DataClass_btn.setIconTextGap(12);
        DataClass_btn.setPreferredSize(new java.awt.Dimension(110, 110));
        DataClass_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataClass_btnActionPerformed(evt);
            }
        });

        LazyClass_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/magenta.gif"))); // NOI18N
        LazyClass_btn.setText("Lazy Class");
        LazyClass_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LazyClass_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LazyClass_btn.setIconTextGap(12);
        LazyClass_btn.setPreferredSize(new java.awt.Dimension(110, 110));
        LazyClass_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LazyClass_btnActionPerformed(evt);
            }
        });

        LargeClass_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/green.gif"))); // NOI18N
        LargeClass_btn.setText("Large Class");
        LargeClass_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LargeClass_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LargeClass_btn.setIconTextGap(12);
        LargeClass_btn.setPreferredSize(new java.awt.Dimension(110, 110));
        LargeClass_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LargeClass_btnActionPerformed(evt);
            }
        });

        Comments_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/gray.gif"))); // NOI18N
        Comments_btn.setText("Comments");
        Comments_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Comments_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Comments_btn.setIconTextGap(12);
        Comments_btn.setPreferredSize(new java.awt.Dimension(110, 110));
        Comments_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Comments_btnActionPerformed(evt);
            }
        });

        obsession_btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/gray.gif"))); // NOI18N
        obsession_btn1.setText("Primitive Obsession");
        obsession_btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        obsession_btn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        obsession_btn1.setIconTextGap(12);
        obsession_btn1.setPreferredSize(new java.awt.Dimension(110, 110));
        obsession_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obsession_btn1ActionPerformed(evt);
            }
        });

        obsession_btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/gray.gif"))); // NOI18N
        obsession_btn2.setText("Parrallel Inheritence");
        obsession_btn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        obsession_btn2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        obsession_btn2.setIconTextGap(12);
        obsession_btn2.setPreferredSize(new java.awt.Dimension(110, 110));
        obsession_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obsession_btn2ActionPerformed(evt);
            }
        });

        speculative_Generality.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/gray.gif"))); // NOI18N
        speculative_Generality.setText("Speculative Generality");
        speculative_Generality.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        speculative_Generality.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        speculative_Generality.setIconTextGap(12);
        speculative_Generality.setPreferredSize(new java.awt.Dimension(110, 110));
        speculative_Generality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speculative_GeneralityActionPerformed(evt);
            }
        });

        obsession_btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/gray.gif"))); // NOI18N
        obsession_btn4.setText("Code Metrices");
        obsession_btn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        obsession_btn4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        obsession_btn4.setIconTextGap(12);
        obsession_btn4.setPreferredSize(new java.awt.Dimension(110, 110));
        obsession_btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                obsession_btn4ActionPerformed(evt);
            }
        });

        Message_Chain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/gray.gif"))); // NOI18N
        Message_Chain.setText("Message Chain");
        Message_Chain.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Message_Chain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Message_Chain.setIconTextGap(12);
        Message_Chain.setPreferredSize(new java.awt.Dimension(110, 110));
        Message_Chain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Message_ChainActionPerformed(evt);
            }
        });

        middleman_button.setText("Middleman");
        middleman_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                middleman_buttonActionPerformed(evt);
            }
        });

        s_g_v_b.setText("Short Global Var. Names");
        s_g_v_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_g_v_bActionPerformed(evt);
            }
        });

        short_method_b.setText("Short Methode Names");
        short_method_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                short_method_bActionPerformed(evt);
            }
        });

        s_Surgery_btn.setText("Shotgun Surgery");
        s_Surgery_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_Surgery_btnActionPerformed(evt);
            }
        });

        brainmethodButton.setText("Brain Method");
        brainmethodButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brainmethodButtonActionPerformed(evt);
            }
        });

        divergChangeButton.setText("Divergent Change");
        divergChangeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divergChangeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(s_Surgery_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(obsession_btn4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(Message_Chain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(speculative_Generality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(obsession_btn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(obsession_btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LongMethod_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(LongParameter_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FeatureEnvy_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DataClass_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LazyClass_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LargeClass_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Comments_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SwitchStatement_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(middleman_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(s_g_v_b, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(short_method_b, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(brainmethodButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(divergChangeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(LongParameter_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(LongMethod_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LargeClass_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LazyClass_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FeatureEnvy_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DataClass_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SwitchStatement_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Comments_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(obsession_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(obsession_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(speculative_Generality, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Message_Chain, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(obsession_btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(middleman_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(s_g_v_b)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(short_method_b)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(s_Surgery_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brainmethodButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(divergChangeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        Relationship.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Relationship.setForeground(new java.awt.Color(255, 0, 0));
        Relationship.setText("Get Dependent Classes");
        Relationship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RelationshipActionPerformed(evt);
            }
        });
        jPanel4.add(Relationship);

        relations.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        relations.setForeground(new java.awt.Color(255, 0, 0));
        relations.setText("Get Relations");
        relations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relationsActionPerformed(evt);
            }
        });
        jPanel4.add(relations);

        jLabel2.setText("Choose Source Folder:");
        jPanel4.add(jLabel2);

        jTextField_browse.setEditable(false);
        jTextField_browse.setPreferredSize(new java.awt.Dimension(170, 30));
        jPanel4.add(jTextField_browse);

        browse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/browse.png"))); // NOI18N
        browse.setText("Browse");
        browse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        browse.setIconTextGap(8);
        browse.setMaximumSize(new java.awt.Dimension(100, 25));
        browse.setMinimumSize(new java.awt.Dimension(100, 25));
        browse.setPreferredSize(new java.awt.Dimension(110, 30));
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });
        jPanel4.add(browse);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Code Smell Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Set Threshould Value", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Low Impact", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 11))); // NOI18N

        jTextField_LowImpact_low.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_LowImpact_lowActionPerformed(evt);
            }
        });
        jTextField_LowImpact_low.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_LowImpact_lowFocusLost(evt);
            }
        });
        jTextField_LowImpact_low.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_LowImpact_lowKeyTyped(evt);
            }
        });

        jLabel9.setText("Lower Limit:");

        jTextField_LowImpact_up.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_LowImpact_upFocusLost(evt);
            }
        });
        jTextField_LowImpact_up.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_LowImpact_upKeyTyped(evt);
            }
        });

        jLabel3.setText("Upper Limit:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_LowImpact_low, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_LowImpact_up)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField_LowImpact_low, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_LowImpact_up, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel_threshold.add(jPanel3);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medium Impact", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 11))); // NOI18N

        jTextField_MedImpact_low.setEditable(false);
        jTextField_MedImpact_low.setFocusable(false);
        jTextField_MedImpact_low.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_MedImpact_lowFocusLost(evt);
            }
        });
        jTextField_MedImpact_low.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_MedImpact_lowKeyTyped(evt);
            }
        });

        jLabel4.setText("Lower Limit:");

        jTextField_MedImpact_up.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_MedImpact_upFocusLost(evt);
            }
        });
        jTextField_MedImpact_up.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_MedImpact_upKeyTyped(evt);
            }
        });

        jLabel5.setText("Upper Limit:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_MedImpact_low, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_MedImpact_up)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_MedImpact_low, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_MedImpact_up, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel_threshold.add(jPanel11);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "High Impact", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 11))); // NOI18N

        jTextField_HighImpact_low.setEditable(false);
        jTextField_HighImpact_low.setFocusable(false);
        jTextField_HighImpact_low.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_HighImpact_lowFocusLost(evt);
            }
        });
        jTextField_HighImpact_low.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_HighImpact_lowKeyTyped(evt);
            }
        });

        jLabel6.setText("Lower Limit:");

        jTextField_HighImpact_up.setEditable(false);
        jTextField_HighImpact_up.setFocusable(false);

        jLabel7.setText("Upper Limit:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_HighImpact_low, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_HighImpact_up)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_HighImpact_low, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_HighImpact_up, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel_threshold.add(jPanel5);

        detect_btn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        detect_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/detect.png"))); // NOI18N
        detect_btn.setText("Detect");
        detect_btn.setIconTextGap(8);
        detect_btn.setPreferredSize(new java.awt.Dimension(80, 30));
        detect_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detect_btnActionPerformed(evt);
            }
        });

        reset_btn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        reset_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/reset.png"))); // NOI18N
        reset_btn.setText("Reset");
        reset_btn.setIconTextGap(8);
        reset_btn.setPreferredSize(new java.awt.Dimension(80, 35));
        reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btnActionPerformed(evt);
            }
        });

        chart_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tool/csv/Pie-Chart-icon.png"))); // NOI18N
        chart_btn.setText("View Chart");
        chart_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chart_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chart_btn.setIconTextGap(12);
        chart_btn.setPreferredSize(new java.awt.Dimension(80, 30));
        chart_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chart_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detect_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(reset_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(chart_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(detect_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel_threshold.add(jPanel8);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel_threshold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel_threshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );

        jMenuBar1.setPreferredSize(new java.awt.Dimension(56, 28));

        jMenu1.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseActionPerformed
        // TODO add your handling code here:
        flag = false;
        JFileChooser chooser = new JFileChooser("..");
        chooser.setDialogTitle("Select Project Source Folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            files.clear();
            ifFileChooserSelectedOk(chooser);//calling the method ..
            
        } else {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_browseActionPerformed
 
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        v_data.clear();
        int col_count = jTable1.getColumnCount();
        int s_row = jTable1.getSelectedRow();
        String FilePath = null;
        String Class = null;
        v_data.add(smell_list.get(active_smell-1));
        for(int i=0;i<col_count;i++){
             if(jTable1.getColumnName(i).equals("Class"))
             {
                Class = jTable1.getModel().getValueAt(s_row, i).toString();
                break;
             }
         }
        if(active_smell ==1 ||active_smell ==2 ||active_smell ==3 ||active_smell ==4 ||active_smell ==5 ||active_smell ==6 ||active_smell ==7 ||active_smell ==8 ||active_smell ==13 ||active_smell ==14 ){
        String[] extras = new String[7];
         for (String file : files) {
                if (file.contains(Class)) {
                    FilePath = file;
                    if(active_smell==8){
                        Comments_HC c = new Comments_HC();
                        try {
                            extras = c.Comments_counter(file, 0);
                            
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
               
            }}
            v_data.add("File Path   "+FilePath+"\n");
            for(int i=0;i<col_count;i++)
            {
                v_data.add(jTable1.getColumnName(i)+"   "+jTable1.getModel().getValueAt(s_row, i).toString()+"\n");     
            }
            if(active_smell==8)
                v_data.add(extras[6]);
            if(active_smell==5){
                v_data.add(Feature_envy_data.get(s_row)[5]);
                v_data.add(Feature_envy_data.get(s_row)[6]);
                
            }
             if(active_smell==13){
                v_data.add(s_surgery_data.get(s_row)[5]);
                v_data.add(s_surgery_data.get(s_row)[6]);
                
            }
            if(active_smell==2)
            {          
                v_data.add("\n"+longMethodBody.get(s_row));
                
            }
             if(active_smell==14)
            {          
                v_data.add("\n"+longMethodBody.get(s_row));
            }
            if(active_smell==1)
            {
                v_data.add("\n"+longParaMethodSig.get(s_row));
            }
            if(active_smell==3)
            {
                v_data.add("\nCode : \n"+switchStateBody.get(s_row));
            }
            
            if(active_smell==7)
            {
                v_data.add("\nFields : \n"+largeClassfields.get(s_row));
                v_data.add("\nMethods : \n"+largeClassMethodsList.get(s_row));
            }
            if(active_smell==4)
            {
                File fil = new File(FilePath);
                String content ="";
                try{
                    Scanner sc = new Scanner(fil);
                    while(sc.hasNext())
                    {
                        String s = sc.nextLine();
                        content = content + s + "\n";
                    }
                }catch(Exception e)
                {
                    System.err.println(e);
                }
                
                v_data.add("\nCode : \n"+content);
            }    
            if(active_smell==6)
            {
                File fil = new File(FilePath);
                String content ="";
                try{
                    Scanner sc = new Scanner(fil);
                    while(sc.hasNext())
                    {
                        String s = sc.nextLine();
                        content = content + s + "\n";
                    }
                }catch(Exception e)
                {
                    System.err.println(e);
                }
                
                v_data.add("\nCode : \n"+content);
            }    
        }
            if (active_smell== 9){
                int row = jTable1.getSelectedRow();
                int coloums = jTable1.getColumnCount();
                String filename ="";
               
                for (int col =0;col<coloums;col++)
                {
                    Object o = jTable1.getValueAt(row, col);
                            {
                                
                                if (col ==0)
                                {
                                    s="Package : "+o.toString();
                                    String content = s+ "\n";
                                    v_data.add(content);
                                }
                                if (col ==1)
                                {
                                        s="ClassName: "+o.toString();
                                        String content = s+ "\n";
                                        v_data.add(content);
                                }
                                if (col ==2)
                                {   
                                    s="Total Primitive DataTypes: "+o.toString();
                                        String content = s+ "\n";
                                        v_data.add(content);
                                      
                                }
                                if (col ==3)
                                {
                                         s="Average Value of Primitive DataTypes : "+o.toString();
                                        String content = s+ "\n";
                                        v_data.add(content);
                                }
                                if (col ==4)
                                {
                                        s="Impact of Smell: "+o.toString();
                                        String content = s+ "\n";
                                        v_data.add(content);
                                }
                                
                            }
                }
            }
            if (active_smell == 11){
                int row = jTable1.getSelectedRow();
                int coloums = jTable1.getColumnCount();
               
                for (int col=0;col<coloums;col++){
                    Object o = jTable1.getValueAt(row, col);
                    if (col ==0){
                        s="Package : "+o.toString();
                        String content = s+ "\n";
                        v_data.add(content);
                    }
                    else if (col ==1){
                       s="Class Name: "+o.toString();
                        String content = s+ "\n";
                        v_data.add(content);
                    }
                    else if (col == 2){
                        s="File Path: "+o.toString();
                        String content = s+ "\n";
                        v_data.add(content);
                    }
                   
                
                }
                
            }
            new CodeView(this,true).setVisible(true);
            
    }//GEN-LAST:event_jTable1MouseClicked

    private void detect_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detect_btnActionPerformed
        // TODO add your handling code here:
        if (!checkThresholdLimit() && active_smell !=4 && active_smell !=5 && active_smell !=6 && active_smell !=7 && active_smell != 9 && active_smell != 11 && active_smell != 13  && active_smell != 15) 
        {
            JOptionPane.showMessageDialog(this, "Check Threshold Values" ,"Error",JOptionPane.ERROR_MESSAGE );
        }

         
        
        else  if (active_smell == 1 ) {
            JOptionPane.showMessageDialog(null, files.size());
            longParaThreshold = Integer.parseInt(jTextField_LowImpact_low.getText());
            LongParameterListSmell();
        } else if (active_smell == 2) {
            longMethodThreshold = Integer.parseInt(jTextField_LowImpact_low.getText());
            LongMethodSmell();
        } else if (active_smell == 3) {
            switchThreshold = Integer.parseInt(jTextField_LowImpact_low.getText());
            SwitchStatementSmell();
        } else if (active_smell == 4) {
            lazyClassSmellDataSetter();
        } else if (active_smell == 5) {
            try {
                setTextFieldDisable();
                
                FeatureEnvySmell(FeatureEnvy_Threshold.var_lower_low,FeatureEnvy_Threshold.method_lower_low);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (active_smell == 6) {
                DataClassSmellDataSetter();
           
        } else if (active_smell == 7) {
                LargeClassSmellDetection();
        } else if (active_smell == 8) {
             try {
                CommentsThreshold = Integer.parseInt(jTextField_LowImpact_low.getText());
                CommentsSmell(CommentsThreshold);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }


        }else if (active_smell == 9){
            
            PrimitiveObsessionSmellDetection();
            
        }  
        else if(active_smell == 11){
            try {
                Speculative_Generality();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        else if(active_smell == 10){
            try {
                ParallelInheritenceSmellDetection();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (active_smell ==12){
            try {
                MessageChainBadSmell();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         else if (active_smell == 13){
            try {
                // JOptionPane.showMessageDialog(null, "you are near to gain gool", "Message for you", JOptionPane.PLAIN_MESSAGE);
                
                
                
                shotgunsurgery(Shotgun_Threshold.var_lower_low,Shotgun_Threshold.method_lower_low);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
            else if (active_smell == 14) {
            BrainMethodThreshold = Integer.parseInt(jTextField_LowImpact_low.getText());
            BrainMethodSmell();
        }
        else if (active_smell == 15) {
                DivergentChangeSmellDetection();
        }
        else {
            JOptionPane.showMessageDialog(null, "Select Any Smell...!!!", "Select Smell", JOptionPane.PLAIN_MESSAGE);
        }
        
        
         
    }//GEN-LAST:event_detect_btnActionPerformed

    private void jTextField_LowImpact_upFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_LowImpact_upFocusLost
        // TODO add your handling code here:
        if(!jTextField_LowImpact_up.getText().isEmpty())
        {
            int val = Integer.parseInt(jTextField_LowImpact_up.getText());
            
                if(active_smell != 2)
                {
                    jTextField_MedImpact_low.setText(""+(val+1));
                }
                else
                {
                    jTextField_MedImpact_low.setText(""+val);
                }
                jTextField_MedImpact_up.requestFocus();
            
        }
        
        
    }//GEN-LAST:event_jTextField_LowImpact_upFocusLost

    private void jTextField_MedImpact_upFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_MedImpact_upFocusLost
        // TODO add your handling code here:
  //      jTextField_HighImpact_low.setText(jTextField_MedImpact_up.getText());
        if(!jTextField_MedImpact_up.getText().isEmpty())
        {
            int val = Integer.parseInt(jTextField_MedImpact_up.getText());
            if(active_smell != 2)
                {
                    jTextField_HighImpact_low.setText(""+(val+1));
                }
                else
                {
                    jTextField_HighImpact_low.setText(""+val);
                }
        }
    }//GEN-LAST:event_jTextField_MedImpact_upFocusLost

    private void reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btnActionPerformed
        // TODO add your handling code here:
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Code Smell Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
        jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Threshould Value", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
        jTextField_LowImpact_low.setText("");
        jTextField_LowImpact_up.setText("");
        jTextField_MedImpact_low.setText("");
        jTextField_MedImpact_up.setText("");
        jTextField_HighImpact_low.setText("");
        jTextField_HighImpact_up.setText("");
        active_smell = 0;
        Object[][] data = null;
        jTable1.setModel(new DefaultTableModel(null, data));
        relations.setEnabled(false);
        Relationship.setEnabled(false);
        resetSpeculativesmell();
        newResults.clear();
        resetparallelinheritence();
        Hasrelation.clear();
        check1.clear();
        check2.clear();
        
    }//GEN-LAST:event_reset_btnActionPerformed
    private void resetSpeculativesmell(){
        abstractclasses.clear();
        abstractclassesLine.clear();
        speculativeClasses.clear();
        SpeculativeClassPackages.clear();
        pathofallfiles.clear();
        pathofalljavafiles.clear();
        classpaths.clear();
    }
    private void jTextField_LowImpact_lowFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_LowImpact_lowFocusLost
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_jTextField_LowImpact_lowFocusLost

    private void jTextField_MedImpact_lowFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_MedImpact_lowFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField_MedImpact_lowFocusLost

    private void jTextField_HighImpact_lowFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_HighImpact_lowFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField_HighImpact_lowFocusLost

    private String chartTitle = "";
    private void chart_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chart_btnActionPerformed
        // TODO add your handling code here:
        int low =0;
        int medium =0;
        int high =0;
        if(jTable1.getRowCount() >0)
        {
            PiePlot3D p;
            JFreeChart chart;
            if(active_smell == 4)
            {
                int lazyClasses = jTable1.getRowCount();
                int others = files.size() - lazyClasses;
                String lowKey = "Other Classes";
                String mediumKey = "Lazy Classes";
                DefaultPieDataset data = new DefaultPieDataset();
                data.setValue(lowKey, others);
                data.setValue(mediumKey, lazyClasses);
                chart = ChartFactory.createPieChart3D(chartTitle+" Pie Chart", data, true, true, false);
        //        PiePlot plot =(PiePlot)chart.getPlot();
                p = (PiePlot3D) chart.getPlot();
                p.setSectionPaint(lowKey, Color.green);
                p.setSectionPaint(mediumKey, Color.red);
            }
            else if(active_smell == 6)
            {
                int lazyClasses = jTable1.getRowCount();
                int others = files.size() - lazyClasses;
                String lowKey = "Other Classes";
                String mediumKey = "Data Classes";
                DefaultPieDataset data = new DefaultPieDataset();
                data.setValue(lowKey, others);
                data.setValue(mediumKey, lazyClasses);
                chart = ChartFactory.createPieChart3D(chartTitle+" Pie Chart", data, true, true, false);
        //        PiePlot plot =(PiePlot)chart.getPlot();
                p = (PiePlot3D) chart.getPlot();
                p.setSectionPaint(lowKey, Color.green);
                p.setSectionPaint(mediumKey, Color.red);
            }
            else
            {
                    for(int i=0;i<jTable1.getRowCount();i++)
                    {
                    String val =jTable1.getValueAt(i, jTable1.getColumnCount()-1).toString();
                    if(val.equals("Low"))
                    {
                        low++;
                    }
                    else if(val.equals("Medium"))
                    {
                        medium++;
                    }
                    else
                    {
                        high++;
                    }
                }

                String lowKey = "Low Impact";
                String mediumKey = "Medium Impact";
                String highKey = "High Impact";
                DefaultPieDataset data = new DefaultPieDataset();
                data.setValue(lowKey, low);
                data.setValue(mediumKey, medium);
                data.setValue(highKey, high);
                chart = ChartFactory.createPieChart3D(chartTitle+" Pie Chart", data, true, true, false);
        //        PiePlot plot =(PiePlot)chart.getPlot();
                p = (PiePlot3D) chart.getPlot();
                p.setSectionPaint(lowKey, Color.green);
                p.setSectionPaint(mediumKey, Color.blue);
                p.setSectionPaint(highKey, Color.red);
            }
            
            p.setStartAngle(270);
            p.setForegroundAlpha(0.3f);
            p.setBackgroundAlpha(0.1f);
            p.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            p.setNoDataMessage("No data available");
            p.setCircular(true);
            p.setLabelGap(0.02);
            p.setDirection(Rotation.ANTICLOCKWISE);
            chart.setBackgroundPaint(Color.white);
        //    p.setInteriorGap(0.33);
            chart.setAntiAlias(true);
            chart.setBorderVisible(true);
            chart.setTextAntiAlias(true);
          //  plot.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame = new ChartFrame(chartTitle+" Pie Chart", chart);
            frame.setIconImage(new ImageIcon(getClass().getResource("/com/tool/csv/insect-icon.png")).getImage());
            frame.setSize(450,450);
            frame.setResizable(false);
            frame.setBounds(450, 150, 450, 450);
            frame.setVisible(true);


            final Rotator rotator = new Rotator(p);
            rotator.start();
        }
    }//GEN-LAST:event_chart_btnActionPerformed

    private void jTextField_LowImpact_lowKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_LowImpact_lowKeyTyped
        // TODO add your handling code here:
        integerConstraint(evt);
    }//GEN-LAST:event_jTextField_LowImpact_lowKeyTyped

    private void jTextField_LowImpact_upKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_LowImpact_upKeyTyped
        // TODO add your handling code here:
        integerConstraint(evt);
    }//GEN-LAST:event_jTextField_LowImpact_upKeyTyped

    private void jTextField_MedImpact_lowKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_MedImpact_lowKeyTyped
        // TODO add your handling code here:
        integerConstraint(evt);
    }//GEN-LAST:event_jTextField_MedImpact_lowKeyTyped

    private void jTextField_MedImpact_upKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_MedImpact_upKeyTyped
        // TODO add your handling code here:
        integerConstraint(evt);
    }//GEN-LAST:event_jTextField_MedImpact_upKeyTyped

    private void jTextField_HighImpact_lowKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_HighImpact_lowKeyTyped
        // TODO add your handling code here:
        integerConstraint(evt);
    }//GEN-LAST:event_jTextField_HighImpact_lowKeyTyped

    private void jTextField_LowImpact_lowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_LowImpact_lowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_LowImpact_lowActionPerformed
ArrayList<String> List1= new ArrayList<>();
ArrayList<String> filespath = new ArrayList<>();
ArrayList<String> List2= new ArrayList<>();
ArrayList<String> Roots= new ArrayList<>();
ArrayList<String> InstancesList=new ArrayList<>();
ArrayList<String> Hasrelation=new ArrayList<>();
    private void RelationshipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RelationshipActionPerformed
       for(int i=0;i<relation.size();i++)
       {
          List1.add( relation.get(i).get(0));
          List2.add(relation.get(i).get((1)));
       }
       
       List<String> depdupe1 = new ArrayList<>(new LinkedHashSet<>(List1));
       List<String> depdupe2 =new ArrayList<>(new LinkedHashSet<>(List2)); 
          SearchDirectories a= new SearchDirectories();
          ArrayList<String> allfiles= new ArrayList<>();
        try {
            allfiles=a.getfiles(path);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        for (int i=0;i<depdupe1.size();i++)
            for(int j =0;j<allfiles.size();j++){
                if (allfiles.get(j).endsWith(depdupe1.get(i)+".java")){
                    filespath.add(allfiles.get(j));
                }
            }
       
      CompilationUnit cu = null;
      for(String fileName: filespath){
          cu=parse(fileName);
        getinstances(fileName, cu );
    }
      
      for (int i=0;i<Hasrelation.size();i++){
          String rel =Hasrelation.get(i).toString();
          String [] words= rel.split(" ");
          String root1= words[1];
          String root2 = words[words.length-1];
          if(totalchilds.contains(root1)){
              String[] words1=totalchilds.get(i).toString().split(" ");
              String childofroot1 =words1[words1.length-1];
              JOptionPane.showMessageDialog(null, root1+ " has Child "+childofroot1);
          }
      }
      if (Hasrelation.size()==0){
          JOptionPane.showMessageDialog(null, "No RelationShip Exits between Hirearchies");
      }else {
      String col[] = {"Relationship Between Two Roots"};
      Object[][] data = new Object[Hasrelation.size()][col.length]; 
      for (int i=0;i<Hasrelation.size();i++)
          for(int j=0;j<col.length;j++){
              if (j==0){ 
                  data[i][j]= Hasrelation.get(i).toString().replaceAll(".java", " Class");       
              }
          }
        jTable1.setModel(new DefaultTableModel(data, col));
        Hasrelation.clear(); 
        relation.clear();
        InstancesList.clear();
      }
    }//GEN-LAST:event_RelationshipActionPerformed

    private void relationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relationsActionPerformed
       String col[] = {"Relation Between Classes"};
        Object[][] data = new Object[relation.size()][col.length];

        for (int i=0;i<relation.size();i++){
            for(int j =0;j<col.length;j++){
                data[i][j]=relation.get(i);
            }
        }
        jTable1.setModel(new DefaultTableModel(data, col));
    }//GEN-LAST:event_relationsActionPerformed

    private void obsession_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obsession_btn2ActionPerformed
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            setTextFieldEnable();
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parralel Inheritence", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Threshould Value", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 10;
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
            chartTitle = obsession_btn1.getText()+" Impact";
            chartTitle = Comments_btn.getText()+" Impact";
            jTextField_LowImpact_low.setText("1");
            jTextField_LowImpact_up.setText("2");
            jTextField_MedImpact_low.setText("3");
            jTextField_MedImpact_up.setText("3");
            jTextField_HighImpact_low.setText("4");
            jTextField_HighImpact_up.setText("So on...");

        }
    }//GEN-LAST:event_obsession_btn2ActionPerformed

    private void obsession_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obsession_btn1ActionPerformed
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Primitive Obsession", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Threshould Value", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 9;
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
            chartTitle = obsession_btn1.getText()+" Impact";
            setTextFieldDisable();

        }
    }//GEN-LAST:event_obsession_btn1ActionPerformed

    private void Comments_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Comments_btnActionPerformed
        // TODO add your handling code here:
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            setTextFieldEnable();
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comments", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Set Threshould Value - Percentage of Comments in a Class", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 8;
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
            chartTitle = Comments_btn.getText()+" Impact";
            jTextField_LowImpact_low.setText("18");
            jTextField_LowImpact_up.setText("26");
            jTextField_MedImpact_low.setText("27");
            jTextField_MedImpact_up.setText("36");
            jTextField_HighImpact_low.setText("37");
            jTextField_HighImpact_up.setText("So on...");
        }
    }//GEN-LAST:event_Comments_btnActionPerformed

    private void LargeClass_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LargeClass_btnActionPerformed
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Large Class", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Threshould Value", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 7;
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
            chartTitle = LargeClass_btn.getText()+" Impact";
            setTextFieldDisable();
            new LargeClassThreshold(this, true).setVisible(true);
            jTextField_LowImpact_low.setText("");
            jTextField_LowImpact_up.setText("");
            jTextField_MedImpact_low.setText("");
            jTextField_MedImpact_up.setText("");
            jTextField_HighImpact_low.setText("");
            jTextField_HighImpact_up.setText("");

        }
    }//GEN-LAST:event_LargeClass_btnActionPerformed

    private void LazyClass_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LazyClass_btnActionPerformed
        // TODO add your handling code here:
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lazy Class", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Threshould Value", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 4;
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
            chartTitle = LazyClass_btn.getText();
            //   new LazyClassThreshold(this, true).setVisible(true);
            setTextFieldDisable();
            new LazyClassThreshold(this, true).setVisible(true);
            jTextField_LowImpact_low.setText("");
            jTextField_LowImpact_up.setText("");
            jTextField_MedImpact_low.setText("");
            jTextField_MedImpact_up.setText("");
            jTextField_HighImpact_low.setText("");
            jTextField_HighImpact_up.setText("");

        }
    }//GEN-LAST:event_LazyClass_btnActionPerformed

    private void DataClass_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataClass_btnActionPerformed
        // TODO add your handling code here:
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {

            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Class", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No Threshould Value", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 6;
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
            chartTitle = DataClass_btn.getText();
            jTextField_LowImpact_low.setText("");
            jTextField_LowImpact_up.setText("");
            jTextField_MedImpact_low.setText("");
            jTextField_MedImpact_up.setText("");
            jTextField_HighImpact_low.setText("");
            jTextField_HighImpact_up.setText("");
            setTextFieldDisable();
        }
    }//GEN-LAST:event_DataClass_btnActionPerformed

    private void FeatureEnvy_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FeatureEnvy_btnActionPerformed
        // TODO add your handling code here:
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Feature Envy", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Threshould Value", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 5;
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
            chartTitle = FeatureEnvy_btn.getText()+" Impact";
            setTextFieldDisable();
            new FeatureEnvy_Threshold(this, true).setVisible(true);
            jTextField_LowImpact_low.setText("");
            jTextField_LowImpact_up.setText("");
            jTextField_MedImpact_low.setText("");
            jTextField_MedImpact_up.setText("");
            jTextField_HighImpact_low.setText("");
            jTextField_HighImpact_up.setText("");
        }
    }//GEN-LAST:event_FeatureEnvy_btnActionPerformed

    private void SwitchStatement_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SwitchStatement_btnActionPerformed
        // TODO add your handling code here:
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Switch Statement", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Set Threshould Value - Number of Cases of a Switch", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 3;
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
            chartTitle = SwitchStatement_btn.getText()+" Impact";
            setTextFieldEnable();
            jTextField_LowImpact_low.setText("4");
            jTextField_LowImpact_up.setText("6");
            jTextField_MedImpact_low.setText("7");
            jTextField_MedImpact_up.setText("9");
            jTextField_HighImpact_low.setText("10");
            jTextField_HighImpact_up.setText("So on...");
        }
    }//GEN-LAST:event_SwitchStatement_btnActionPerformed

    private void LongMethod_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LongMethod_btnActionPerformed
        // TODO add your handling code here:
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Long Method", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Set Threshould Value - Complexity of Methods", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 2;
            chartTitle = LongMethod_btn.getText()+" Impact";
            setTextFieldEnable();
            jTextField_LowImpact_low.setText("21");
            jTextField_LowImpact_up.setText("28");
            jTextField_MedImpact_low.setText("28");
            jTextField_MedImpact_up.setText("34");
            jTextField_HighImpact_low.setText("34");
            jTextField_HighImpact_up.setText("So on...");
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
        }
    }//GEN-LAST:event_LongMethod_btnActionPerformed

    private void LongParameter_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LongParameter_btnActionPerformed
        // TODO add your handling code here:

        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Long Parameter List", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Set Threshould Value - Number of Parameters", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 1;
            chartTitle = LongParameter_btn.getText()+" Impact";
            setTextFieldEnable();
            jTextField_LowImpact_low.setText("3");
            jTextField_LowImpact_up.setText("5");
            jTextField_MedImpact_low.setText("6");
            jTextField_MedImpact_up.setText("7");
            jTextField_HighImpact_low.setText("8");
            jTextField_HighImpact_up.setText("So on...");
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
        }
        longParaSuggestion = " To remove â€œLong Parameter Listâ€� bad smell, following two ways can be used: \nâ€¢	If the parameters are replaceable by generating a call to an existing object, use â€œReplace Parameter with Method.â€�\nâ€¢	If the parameter list lacks a common logical object, use â€œIntroduce Parameter Objectâ€�.";
    }//GEN-LAST:event_LongParameter_btnActionPerformed

    private void speculative_GeneralityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speculative_GeneralityActionPerformed
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Speculative Generality", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Threshould Value", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 11;
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
            chartTitle = obsession_btn1.getText()+" Impact";
            setTextFieldDisable();

        }
    }//GEN-LAST:event_speculative_GeneralityActionPerformed
  public static ArrayList<String> methodsNo = new ArrayList<String> ();
  public static ArrayList<String> Comments = new ArrayList<String>();
  ArrayList<ArrayList<Float>> saad = new ArrayList<ArrayList<Float>> ();
  ArrayList<Integer> WordsPerline = new ArrayList<>();
    ArrayList<Float> AverageWordsPerline = new ArrayList<>();
ArrayList<Double> hamad =new ArrayList<Double>();
ArrayList<Double> Characters = new ArrayList<Double>();
ArrayList<String> results = new ArrayList<>();
public static ArrayList<String> information = new ArrayList<String>();
    private void obsession_btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_obsession_btn4ActionPerformed
        CompilationUnit cu ; 
        int methodscounteryyy=0;
        
        for (String fileName : files) {
            try {
                cu = parse(fileName);
                int methodscounter= getmethod(fileName, cu);
                getComments(fileName);
                int words = NumberOfWords(fileName);   
                getaverageword(fileName,methodscounter,words);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        
      
          WriteCSVFile();
    }//GEN-LAST:event_obsession_btn4ActionPerformed

    private void Message_ChainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Message_ChainActionPerformed
       if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Message Chain", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Threshould Value", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 12;
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
            chartTitle = obsession_btn1.getText()+" Impact";
            setTextFieldDisable();
            jTextField_LowImpact_low.setText("3");
            jTextField_LowImpact_up.setText("3");
            jTextField_MedImpact_low.setText("4");
            jTextField_MedImpact_up.setText("4");
            jTextField_HighImpact_low.setText("5");
            jTextField_HighImpact_up.setText("So on...");
          }
    }//GEN-LAST:event_Message_ChainActionPerformed
 private void middleman_buttonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }  private void s_g_v_bActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    } 
        private void smellthreebActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }   
    private void short_method_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_short_method_bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_short_method_bActionPerformed

    private void s_Surgery_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_Surgery_btnActionPerformed
 if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
           jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Shotgun Surgery", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Threshould Value", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 13;
          Object[][] data = null;
          jTable1.setModel(new DefaultTableModel(null, data));
         chartTitle = s_Surgery_btn.getText()+" Impact";
         setTextFieldDisable();
         new Shotgun_Threshold(this, true).setVisible(true);
           jTextField_LowImpact_low.setText("");
           jTextField_LowImpact_up.setText("");
          jTextField_MedImpact_low.setText("");
           jTextField_MedImpact_up.setText("");
          jTextField_HighImpact_low.setText("");
          jTextField_HighImpact_up.setText("");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_s_Surgery_btnActionPerformed

    private void brainmethodButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brainmethodButtonActionPerformed
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Brain Method", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Set Threshould Value - Complexity of Methods", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 14;
            chartTitle = brainmethodButton.getText()+" Impact";
            setTextFieldEnable();
            jTextField_LowImpact_low.setText("8");
            jTextField_LowImpact_up.setText("10");
            jTextField_MedImpact_low.setText("10");
            jTextField_MedImpact_up.setText("15");
            jTextField_HighImpact_low.setText("15");
            jTextField_HighImpact_up.setText("So on...");
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
        }
    }//GEN-LAST:event_brainmethodButtonActionPerformed

    private void divergChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divergChangeButtonActionPerformed
        if (jTextField_browse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Haven't selected anything yet!!!", "Selected Project", JOptionPane.PLAIN_MESSAGE);
        } else {
            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Divergent Change", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14)));
            jPanel_threshold.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Threshould Value", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12)));
            active_smell = 15;
            Object[][] data = null;
            jTable1.setModel(new DefaultTableModel(null, data));
            chartTitle = divergChangeButton.getText()+" Impact";
            setTextFieldDisable();
            new DivergentChangeThreshold(this, true).setVisible(true);
            jTextField_LowImpact_low.setText("");
            jTextField_LowImpact_up.setText("");
            jTextField_MedImpact_low.setText("");
            jTextField_MedImpact_up.setText("");
            jTextField_HighImpact_low.setText("");
            jTextField_HighImpact_up.setText("");

        }
    }//GEN-LAST:event_divergChangeButtonActionPerformed
   
  

    
    private int NumberOfWords(String Filename) throws FileNotFoundException, IOException{
       BufferedReader bufferedReader = new BufferedReader(new FileReader(Filename));

		StringBuffer stringBuffer = new StringBuffer();
		String line = null;

		int counter = 0;
		while ((line = bufferedReader.readLine()) != null) {

			stringBuffer.append(line).append("\n");
			if(line.trim().length() != 0)
			{
				counter++;
			}
		}
		
		return stringBuffer.toString().split(" ").length + counter - 1;
    }
    
    private int getmethod(String filename,CompilationUnit cu){
        LargeClassMethods visitor1 = new LargeClassMethods();
        cu.accept(visitor1);
        methodsNo.add(visitor1.getMethodsList()+ "\n");
        int totalmethods = visitor1.getTotalMethods();
        filename = filename.substring(filename.lastIndexOf("\\")+1);
        methodsNo.add(filename +" Contain Total Methods are :" + totalmethods+ "\n");  
        return totalmethods;
        
    } 
    private static final String File_Header = "SLOC-P,SLOC-L,Characters,Statments,i_iterator,NoOfTabs,NoOfSpaces,AverageLineLength(C),AverageWordLineLength(W),Comments,TotalWords,Total Methods,EL/NEL,Tab/Char,Space/Char,EL/Chars,UpperCase%,Char/Words,Space/SLOC-P,Statments/SLOC-L,words/SLOC-L,Words/Statments,Author,Tabs / SLOC-P,Comments/SLOC-L,Tabs + Spaces,SlOC-P - SLOC-L, WS/NWS, File Path,Long ParameterSmell,Long Method Smell,Large Class Smell,Lazy Class Smell,Feature Envy Smell, Data Class Smell,Switch Statments Smell , Comments,Primitive Obsession, Parallel Inheritence, Speculative Generality, Message Chain";
    private void WriteCSVFile(){
        String[][] finalresults = new String[newResults.size()][];
        for (int i = 0; i < newResults.size(); i++) {
            ArrayList<String> row = newResults.get(i);
            finalresults[i] = row.toArray(new String[row.size()]);
        }
        //JOptionPane.showMessageDialog(null, finalresults);
        
        String value ="";
               value= JOptionPane.showInputDialog(null,"Enter The File Name You Want To Write");
               
        if (value ==null)
        {
            return;
        }
        else 
        {
             value = path +"\\"+value+".csv";
             try {
             classname.clear();
             LongParameter_btn.doClick();
             detect_btn.doClick();
             ArrayList<String> temp1 = new ArrayList<>();
             ArrayList<String> temp_1 = new ArrayList<>();
             int row = jTable1.getRowCount();
             int coloum = jTable1.getColumnCount();
             for (int i=0;i<row;i++)
             {
                 String value1 =jTable1.getValueAt(i, 1).toString();
                 String value1_1 = jTable1.getValueAt(i, coloum-1).toString();
                 temp1.add(value1);   
                 temp_1.add(value1_1);
             }
          
             LongMethod_btn.doClick();
             detect_btn.doClick();
             ArrayList<String> temp2 = new ArrayList<>();
             ArrayList<String> temp2_2 = new ArrayList<>();
             row = jTable1.getRowCount();
             coloum = jTable1.getColumnCount();
             for (int i=0;i<row;i++)
             {
                 String value1 =jTable1.getValueAt(i, 1).toString();
                 String value2_2 = jTable1.getValueAt(i, coloum-1).toString();
                 temp2.add(value1);    
                 temp2_2.add(value2_2);
             }
             LargeClass_btn.doClick();
             detect_btn.doClick();
             ArrayList<String> temp3 = new ArrayList<>();
             ArrayList<String>temp3_1 = new ArrayList<>();
             row = jTable1.getRowCount();
             coloum = jTable1.getColumnCount();
             for (int i=0;i<row;i++)
             {
                 String value1 =jTable1.getValueAt(i, 1).toString();
                 String value1_2 = jTable1.getValueAt(i, coloum-1).toString();
                 temp3_1.add(value1_2);
                 temp3.add(value1);             
             }
          
                 LazyClass_btn.doClick();
                 detect_btn.doClick();
                 ArrayList<String> temp4 = new ArrayList<>();
                 ArrayList<String> temp4_1 = new ArrayList<>();
                 row = jTable1.getRowCount();
                 coloum = jTable1.getColumnCount();
                 for (int i=0;i<row;i++)
                 {
                     String value1 =jTable1.getValueAt(i, 1).toString();
                     String value1_1 = jTable1.getValueAt(i, coloum-1).toString();
                     temp4_1.add(value1_1);
                     temp4.add(value1);             
                 }
          
                 FeatureEnvy_btn.doClick();
                 detect_btn.doClick();
                 ArrayList<String> temp5 = new ArrayList<>();
                 ArrayList<String> temp5_1 = new ArrayList<>();
                 row = jTable1.getRowCount();
                 coloum = jTable1.getColumnCount();
                 for (int i=0;i<row;i++)
                 {
                     String value1 =jTable1.getValueAt(i, 1).toString();
                     String value1_1 =jTable1.getValueAt(i, coloum-1).toString();
                     value1 = value1+".java";
                     temp5.add(value1);    
                     temp5_1.add(value1_1);
                 }
                 DataClass_btn.doClick();
                 detect_btn.doClick();
                 ArrayList<String> temp6 = new ArrayList<>();
                 ArrayList<String> temp6_1 = new ArrayList<>();
                 row = jTable1.getRowCount();
                 coloum = jTable1.getColumnCount();
                 for (int i=0;i<row;i++)
                 {
                     String value1 =jTable1.getValueAt(i, 1).toString();
                     String value1_1 =jTable1.getValueAt(i, coloum-1).toString();
                     temp6.add(value1);
                     temp6_1.add(value1_1);
                 }
          
                
          
                 SwitchStatement_btn.doClick();
                 detect_btn.doClick();
                 ArrayList<String> temp8 = new ArrayList<>();
                 ArrayList<String> temp8_1 = new ArrayList<>();
                 row = jTable1.getRowCount();
                 coloum = jTable1.getColumnCount();
                 for (int i=0;i<row;i++)
                 {
                     String value1 =jTable1.getValueAt(i, 1).toString();
                     String value1_1 = jTable1.getValueAt(i, coloum-1).toString();
                     temp8.add(value1);             
                     temp8_1.add(value1_1);
                 }
                 Comments_btn.doClick();
                 detect_btn.doClick();
                 ArrayList<String> temp9 = new ArrayList<>();
                  ArrayList<String> temp9_1 = new ArrayList<>();
                 row = jTable1.getRowCount();
                 coloum = jTable1.getColumnCount();
                 for (int i=0;i<row;i++)
                 {
                     String value1 =jTable1.getValueAt(i, 1).toString();
                     String value1_1 = jTable1.getValueAt(i, coloum-1).toString();
                     temp9.add(value1);             
                     temp9_1.add(value1_1);
                 }
                 obsession_btn1.doClick();
                 detect_btn.doClick();
                 ArrayList<String> temp10 = new ArrayList<>();
                 ArrayList<String> temp10_1 = new ArrayList<>();
                 row = jTable1.getRowCount();
                 coloum = jTable1.getColumnCount();
                 for (int i=0;i<row;i++)
                 {
                     String value1 =jTable1.getValueAt(i, 1).toString();
                     String value1_1 = jTable1.getValueAt(i, coloum-1).toString();
                     temp10.add(value1);             
                     temp10_1.add(value1_1);
                 }
                 /*obsession_btn2.doClick();
                 detect_btn.doClick();
                 ArrayList<String> temp11 = new ArrayList<>();
                 ArrayList<String> temp11_1 = new ArrayList<>();
                 row = jTable1.getRowCount();
                 coloum = jTable1.getColumnCount();
                 for (int i=0;i<row;i++)
                 {
                     String value1 =jTable1.getValueAt(i, 1).toString();
                     String value1_1 = jTable1.getValueAt(i, coloum-1).toString();
                     temp11.add(value1);             
                     temp11_1.add(value1_1);
                 }
                 */
                 speculative_Generality.doClick();
                 detect_btn.doClick();
                 ArrayList<String> temp12 = new ArrayList<>();
                 ArrayList<String> temp12_1 = new ArrayList<>();
                 row = jTable1.getRowCount();
                 if (row ==0)
                 {
                     for (int i=0;i< files.size();i++)
                     {
                         temp12.add("NO");
                         temp12_1 .add ("hulal");
                     }
                     
                 }
                 else
                 {
                     coloum = jTable1.getColumnCount();
                     for (int i=0;i<row;i++)
                    {
                         String value1 =jTable1.getValueAt(i, 1).toString();
                         String value1_1 = jTable1.getValueAt(i, coloum-1).toString();
                         temp12.add(value1);             
                         temp12_1.add(value1_1);
                    }
                 }
                 
                 Message_Chain.doClick();
                 detect_btn.doClick();
                 ArrayList<String> temp13 = new ArrayList<>();
                 ArrayList<String> temp13_1 = new ArrayList<>();
                 row = jTable1.getRowCount();
                 coloum = jTable1.getColumnCount();
                 for (int i=0;i<row;i++)
                 {
                     String value1 =jTable1.getValueAt(i, 1).toString();
                     String value1_1 = jTable1.getValueAt(i, coloum-1).toString();
                     temp13.add(value1);             
                     temp13_1.add(value1_1);
                 }
                 FileWriter writer = new FileWriter(value);
                 writer.append(File_Header);
                 writer.append('\n');
                 for (int i=0;i<finalresults.length;i++)
                 {
                     for (int j=0;j<finalresults[i].length;j++)
                     {
                            writer.append(finalresults[i][j]);
                    
                             if (j < finalresults[i].length - 1)
                             {
                                 writer.append(',');
                             }
                             boolean flag = false;
                             if (j == finalresults[i].length - 2) {
                                for (int k=0;k<temp1.size();k++) 
                                {   
                                     String file = temp1.get(k);
                                     if (finalresults[i][j].endsWith(file)) {
                                         //writer.append("Yes");
                                         //writer.append("\\");
                                         writer.append(temp_1.get(k));
                                         flag = true;
                                         break;
                                      }
                                }
                              if (!flag) 
                              {
                                     writer.append("NO");
                              }
                              flag = false;
                              for (int l =0;l<temp2.size();l++) 
                              {
                                     String file = temp2.get(l);
                                     if (finalresults[i][j].endsWith(file)) {
                                     writer.append(",");
                                    // writer.append("yes");
                                    // writer.append("\\");
                                     writer.append(temp2_2.get(l));
                                     flag = true;
                                     break;
                                    }
                             }
                            if (!flag) 
                            {
                                writer.append(",");
                                writer.append("NO");

                            }
                                flag = false;
                                for (int m =0;m<temp3.size();m++) 
                                {
                                    String file = temp3.get(m);
                                    if (finalresults[i][j].endsWith(file)) {
                                    writer.append(",");
                                   // writer.append("yes");
                                   // writer.append("\\");
                                    writer.append(temp3_1.get(m));
                                    flag = true;
                                    break;
                                }
                          }
                            if (!flag) 
                            {
                                writer.append(",");
                                writer.append("NO");
                            }
                             flag = false;
                        for (int n =0;n<temp4.size();n++) 
                        {
                            String file = temp4.get(n);
                            if (finalresults[i][j].endsWith(file)) {
                                writer.append(",");
                                writer.append("yes");
                               // writer.append("\\");
                               // writer.append(temp4_1.get(n));
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) 
                        {
                            writer.append(",");
                            writer.append("NO");
                        }
                         flag = false;
                        for (int b =0;b<temp5.size();b++) 
                        {
                            String file = temp5.get(b);
                            if (finalresults[i][j].endsWith(file)) {
                                writer.append(",");
                                //writer.append("yes");
                               // writer.append("\\");
                                writer.append(temp5_1.get(b));
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) 
                        {
                            writer.append(",");
                            writer.append("NO");
                        }
                         flag = false;
                        for (int v =0;v<temp6.size();v++) 
                        {
                            String file = temp6.get(v);
                            if (finalresults[i][j].endsWith(file)) {
                                writer.append(",");
                                writer.append("Yes");
                                //writer.append("\\");
                                //writer.append(temp6_1.get(v));
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) 
                        {
                            writer.append(",");
                            writer.append("NO");
                        } 
                      
                        flag = false;
                        for (int c=0;c<temp8.size();c++) 
                        {
                            String file = temp8.get(c);
                            if (finalresults[i][j].endsWith(file)) {
                                writer.append(",");
                              //  writer.append("yes");
                                //writer.append("\\");
                                writer.append(temp8_1.get(c));
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) 
                        {
                            writer.append(",");
                            writer.append("NO");
                        }
                        flag = false;
                        for (int w=0;w<temp9.size();w++) 
                        {
                            String file = temp9.get(w);
                            if (finalresults[i][j].endsWith(file)) {
                                writer.append(",");
                               // writer.append("yes");
                               // writer.append("\\");
                                writer.append(temp9_1.get(w));
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) 
                        {
                            writer.append(",");
                            writer.append("NO");
                        }
                         flag = false;
                        for (int w=0;w<temp10.size();w++) 
                        {
                            String file = temp10.get(w);
                            if (finalresults[i][j].endsWith(file)) {
                                writer.append(",");
                              //  writer.append("yes");
                               // writer.append("\\");
                                writer.append(temp10_1.get(w));
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) 
                        {
                            writer.append(",");
                            writer.append("NO");
                        }
                         /*
                         flag = false;
                        for (int w=0;w<temp11.size();w++) 
                        {
                            String file = temp11.get(w);
                            if (finalresults[i][j].endsWith(file)) {
                                writer.append(",");
                              //  writer.append("yes");
                              //  writer.append("\\");
                                writer.append(temp11_1.get(w));
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) 
                        {
                            writer.append(",");
                            writer.append("NO");
                        }
                        */
                        flag = false;
                        for (int w=0;w<temp12.size();w++) 
                        {
                            String file = temp12.get(w);
                            if (finalresults[i][j].endsWith(file)) {
                                writer.append(",");
                              //  writer.append("yes");
                              //  writer.append("\\");
                                writer.append(temp12_1.get(w));
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) 
                        {
                            writer.append(",");
                            writer.append("NO");
                        }
                       
                        flag = false;
                        for (int w=0;w<temp13.size();w++) 
                        {
                            String file = temp13.get(w);
                            if (finalresults[i][j].endsWith(file)) {
                                writer.append(",");
                             //   writer.append("yes");
                             //   writer.append("\\");
                                writer.append(temp13_1.get(w));
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) 
                        {
                            writer.append(",");
                            writer.append("NO");
                        }
                        
                    }
                    
                   
                }
            }
            writer.flush();
            writer.close();
            JOptionPane.showMessageDialog(null, "File has Been Created at" + value);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
        
    }
    public int counter =0;
    public double source_files=0;
    private void getComments(String Filename){
           try {
            counter = 0;
            int comm=0;
            int block = 0;
            int LC= 0;
            source_files=0;
             String line="";
            String coments="";
            File files = new File(Filename);
            Scanner sc = new Scanner(files);
            String mth=null;
            boolean flag = true;
            Comments.add("File "+ Filename.substring(Filename.lastIndexOf("\\")+1)+"\n\n");
            while (sc.hasNext()){
               line = sc.nextLine();   
                source_files++;  
                if (line.contains("//"))
                {
                    coments = coments+source_files+" "+ line + "\n";
                    counter++;
                    LC++;   
                }
                else if (line.contains("/*")){
                    counter++;
                    block++;
                    coments = coments + source_files +"  "+ line+ "\n";
                    while (!line.contains("*/")){
                        counter++;
                        block++;
                        line=sc.nextLine();
                        source_files++;
                        coments= coments+source_files+ "  "+ line+"\n";              
                    }
                }
            }
            Comments.add(coments+ "\n\n");
            Comments.add(Filename.substring(Filename.lastIndexOf("\\")+1)+" Contains Comments "+counter+ " AND LOC is: "+source_files );
            
           }catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     ArrayList<ArrayList<String>> newResults = new ArrayList<ArrayList<String>>();
    private void getaverageword(String Filename,int methodscounterr,int wordss){
        try {
            int totalmethods= methodscounterr;
            double uppercaseletters=0;
            String line="";
            double sum =0;
            int widelines=0;
            double noofspaces =0;
            double emptylines=0;
            double totalwords=0;
            double SLOC_L=0;
            String author="";
            int statments =0;
            int nooftabs =0;
            int wordcount =0;
            int totalLines=0;
            int i_iteratorcount=0;
            double averagewords=0;
            double totalcharacters =0;
            double charperLine=0;
            double tabsoverSLOCP=0;
            double commentsoverslocl=0;
            double tabsandspaces=0;
            
            File files = new File(Filename);
            Scanner sc = new Scanner(files);
            
            while(sc.hasNext()){
                line = sc.nextLine();
                line.trim();
                //counting words per line 
                
                totalLines++;
                if (line.isEmpty()){
                    emptylines++;
                    continue;
                }
                else
                {
                line.trim();
                String words[] = line.split(" ");       
                WordsPerline.add(words.length);
                   wordcount=0;
                    for (String word : words) 
                    {
                        double wordLength = word.length();
                        sum += wordLength+1;
                        Characters.add(sum);                        
                        sum =0;
                        wordLength=0;
                    }
               
                    //calulate no of spacaes 
                 for (int i =0;i<line.length();i++){
                     char ch = line.charAt(i);
                     if (ch ==' '){
                         noofspaces++;
                     }
                     
                     
                     if (ch == '\t'){
                         nooftabs++;
                     }
                     if (ch==';')
                     {
                        statments++;
                     }
                 }
                   
                    if (line.isEmpty()){
                        widelines++;
                    }
                    
                    
                    if (line.contains("i++")||line.contains("++i"))
                    {
                        i_iteratorcount++;
                    }
                   /* if (line.contains("@author"))
                    {
                        String []array = line.split(" ");
                        for (int i=0;i<array.length;i++){
                            if(array[i].equals("@author"))
                            {
                                author=array[i+1];
                            }
                        }
                    }*/
                    for(int i=0;i<line.length();i++){
                        char ch = line.charAt(i);
                        if (ch>=65 && ch<90)// condition for counting upper case characters
                        {
                            uppercaseletters++;
                        }
                    }
                }
            }
            //counting average words
         totalwords=wordss;
            
           //counting avearge characters
           for(int j=0;j<Characters.size();j++){
               totalcharacters = totalcharacters + Characters.get(j);
            }
           totalcharacters= totalcharacters-1;
           double percentofUppercase = (uppercaseletters/totalcharacters)*100;
           
           charperLine = totalcharacters/totalLines;
           averagewords = totalwords/totalLines;
           SLOC_L = source_files-emptylines;
           double ratioOfELoverNEL= emptylines/source_files;
           double ratioOfSpacesOverChar = (double)noofspaces/totalcharacters;
         
           double rationofELOverTC =emptylines/totalcharacters;
          
           percentofUppercase = Math.round(percentofUppercase*100.0)/100.0;
           double ratioofCharoverWords = totalcharacters/totalwords;
           ratioofCharoverWords= Math.round(ratioofCharoverWords*100.0)/100.0;
           double rationofSpacesOverSLOCP= noofspaces/source_files;
           rationofSpacesOverSLOCP= Math.round(rationofSpacesOverSLOCP*100.0) /100.0;
           double rationofStatmentsOverSLOCL= statments/SLOC_L;
           rationofStatmentsOverSLOCL= Math.round(rationofStatmentsOverSLOCL*100.0)/100.0;
           double ratioofWordsoverSLOCL = totalwords/SLOC_L;
           ratioofWordsoverSLOCL = Math.round(ratioofWordsoverSLOCL*100.0)/100.0;
           double ratioofwordsOverStatments = totalwords/statments;
           ratioofwordsOverStatments = Math.round(ratioofwordsOverStatments*100.0)/100.0;
           double ratioofComentsOverSLOCL = counter/SLOC_L;
           ratioofComentsOverSLOCL = Math.round(ratioofComentsOverSLOCL*100.0)/100.0;
           double ratioofTabsoverChar =(double) nooftabs/totalcharacters;
           tabsoverSLOCP= nooftabs/source_files;
           commentsoverslocl=Comments.size() / SLOC_L; 
           tabsandspaces = nooftabs + noofspaces;
           double SLOCPminusSLOCL = source_files - SLOC_L;
           double widespaces =nooftabs + noofspaces;
           double NonWidespaces = totalcharacters - widespaces;
           double ratioofWSoverNWS= widespaces / NonWidespaces;
           
         //  JOptionPane.showMessageDialog(null, "Filename :" + Filename+" Words :" +averagewords+ " Characters are " + totalcharacters+ " spaces " + noofspaces+" no of tabs "+ nooftabs +" Wide Lines "+ widelines+ " Comments: "+ counter+ " SLOC-P:" +source_files);
          // String value =Filename+" ,"+ source_files+ ","+ SLOC_L + "," + totalcharacters + "," + statments+ " ,"+ i_iteratorcount + " , "+ nooftabs+ " ,"+noofspaces+ ","+ Math.floor(charperLine)+", "+ Math.floor(averagewords)+" ,"+counter+","+totalwords+", "+Math.round(ratioOfELoverNEL*100.0)/100.0+","+ratioofComentsOverSLOCL+","+ratioofTabsoverChar+", "+ratioOfSpacesOverChar+", "+rationofELOverTC+", "+percentofUppercase+ "%,"+ratioofCharoverWords + ","+rationofSpacesOverSLOCP+","+rationofStatmentsOverSLOCL+","+ratioofWordsoverSLOCL+","+ratioofwordsOverStatments+"";
          // JOptionPane.showMessageDialog(null,Filename+" ,"+ source_files+ ","+ SLOC_L + "," + totalcharacters + "," + statments+ " ,"+ i_iteratorcount + " , "+ nooftabs+ " ,"+noofspaces+ ","+ Math.floor(charperLine)+", "+ Math.floor(averagewords)+" ,"+counter+","+totalwords+", "+Math.round(ratioOfELoverNEL*100.0)/100.0+", "+ratioOfSpacesOverChar+", "+rationofELOverTC+", "+percentofUppercase+ "%,"+ratioofCharoverWords + ","+rationofSpacesOverSLOCP+","+rationofStatmentsOverSLOCL+","+ratioofWordsoverSLOCL+","+ratioofwordsOverStatments+"" );
           results = new ArrayList<>();
          
           results.add(String.valueOf(source_files));
           results.add(String.valueOf(SLOC_L));
           results.add(String.valueOf(totalcharacters));
           results.add(String.valueOf(statments));
           results.add(String.valueOf(i_iteratorcount));
           results.add(String.valueOf(nooftabs));
           results.add(String.valueOf(noofspaces));
           results.add(String.valueOf(Math.floor(charperLine)));
           results.add(String.valueOf(Math.floor(averagewords)));
           results.add(String.valueOf(counter));
           results.add(String.valueOf(totalwords));
           results.add(String.valueOf(totalmethods));
           results.add(String.valueOf(Math.round(ratioOfELoverNEL*100.0)/100.0));
           results.add(String.valueOf(ratioofTabsoverChar));
           results.add(String.valueOf(ratioOfSpacesOverChar));
           results.add(String.valueOf(rationofELOverTC));
           results.add(String.valueOf(percentofUppercase));
           results.add(String.valueOf(ratioofCharoverWords));
           results.add(String.valueOf(rationofSpacesOverSLOCP));
           results.add(String.valueOf(ratioofComentsOverSLOCL));
           results.add(String.valueOf(ratioofWordsoverSLOCL));
           results.add(String.valueOf(ratioofwordsOverStatments));
           results.add(author);
           results.add(String.valueOf(tabsoverSLOCP));
           results.add(String.valueOf(commentsoverslocl));
           results.add(String.valueOf(tabsandspaces));
           results.add(String.valueOf(SLOCPminusSLOCL));
           results.add(String.valueOf(ratioofWSoverNWS));
           results.add(Filename);
          
           
           results.add("\n");
           if(newResults.isEmpty())
           {
                newResults.add(results);
           }
           else
           {
               int index = newResults.size();
               newResults.add(index, results);
           }
           
           WordsPerline.clear();
           Characters.clear();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    public void getinstances(String filename, CompilationUnit cu){
         String file = filename;
         Instance_creation obj = new Instance_creation();
         cu.accept(obj);
         int totalins= obj.get_total();
         String filenamee=file.substring(file.lastIndexOf("\\")+1);
         String fileme = filenamee.replace(".java", "");
         fileme=fileme.trim();
        
         
         InstancesList=obj.match_this(filespath, file);
         if (InstancesList.size()==0)
         {
             return;
         }
         for (int l=0;l<InstancesList.size();l++)
         {
             if (!check1.contains(InstancesList.get(l)))
             {
                 InstancesList.remove(l);
             }
         }
        HashSet<String> hashSet = new HashSet<String>(InstancesList);
        InstancesList.clear();
        InstancesList.addAll(hashSet);
         for (int m =0;m<InstancesList.size();m++)
         {
             getRelation(fileme,InstancesList.get(m));
         }  
        
     }
private void getRelation(String FirstFile,String SecondFile)
{
    String fileme =FirstFile.trim();
    int index=0;
    int index1=0;
    String val ="";
    String val2 ="";
    String instFil= SecondFile.trim();
    for (int i=0;i<check1.size();i++)
    {
        index = check1.indexOf(fileme);
        index1 = check1.indexOf(instFil);
        val = (check2.get(index));
        val2 = (check2.get(index1));
        val= val.trim();
        val2=val2.trim();
    }
    if (!InstancesList.isEmpty())
    {
        if (val.equals(val2))
        {
            Hasrelation.add(fileme+ " Class has Parallel Inheritence Hirearchy Smell With "+ instFil+ " Class");
        }
            
    }
    
}
    
ArrayList<String> abstractclassesLine = new ArrayList<>();
ArrayList<String> pathofallfiles = new ArrayList<>();
ArrayList<String> pathofalljavafiles = new ArrayList<>();
ArrayList<String> abstractclasses = new ArrayList<>();
ArrayList<String> speculativeClasses = new ArrayList<>();
public ArrayList<String> classpaths = new ArrayList<String>();
ArrayList<String> SpeculativeClassPackages = new ArrayList<String>();
    private void Speculative_Generality() throws IOException {
        //get paths of all that files that are in directory
         SearchDirectories a= new SearchDirectories();
         pathofallfiles= a.getfiles(path); 
         // purify all the classes that end with java
         for(int i=0;i<pathofallfiles.size();i++){
             if (pathofallfiles.get(i).endsWith(".java")){
                pathofalljavafiles.add(pathofallfiles.get(i));
             }
         }
         // pass each file to parse and extract lines that contain public abstract class name
        for (String filename :pathofalljavafiles){
            parsefile(filename);
        }

        //gettting name of all the abstract classes
        for (int j=0;j<abstractclassesLine.size();j++){
            String  line=abstractclassesLine.get(j);
           String parts[]= line.split("\\s|\\{|\\<|\\>");
           for(int i=0;i<parts.length;i++){
               if (parts[i].equals("class")){
                   abstractclasses.add(parts[i+1]);
                   
               }
           }
        }
        
        
        
        for (String filename:pathofalljavafiles){
           getChildAndParents(filename);
       }
        
        //Removing repitative elements from parents arrayList
        for(int k=1;k<Parents.size();k++){
            String a1= Parents.get(k);
            String a2= Parents.get(k-1);
            if (a1.equals(a2)){
                Parents.remove(a1);
            }
        }   
        getSpeculativeClasses();
        String col[] = {"Package", "Abstract Classes", "Class Path"};
        Object[][] data = new Object[speculativeClasses.size()][col.length];
         for (int i = 0; i <speculativeClasses.size(); i++) {
            for (int j = 0; j < col.length; j++) {
                if (j == 0) {
                    data[i][j] = SpeculativeClassPackages.get(i);
                }
                else if (j==1){
                     data[i][j]= speculativeClasses.get(i);
                }
                else if (j==2){
                     data[i][j] = classpaths.get(i);
                     
                            }
                }
            }
         if (speculativeClasses.size()==0){
             JOptionPane.showMessageDialog(null, "Bad Smell In your project Does Not exist");
         }
         else{
          jTable1.setModel(new DefaultTableModel(data, col));
    }
         longParaSuggestion= "To alleviate this bad smell from the code, it is suggested that the abstract\n" +
"classes that are not doing much should use Collapse Hierarchy, that is, when\n" +
"superclass and subclass are not handling anything much different separately, they\n" +
"should be merged using collapse hierarchy.";
 }
ArrayList<String> SpeculativeFiles = new ArrayList<String>();
    
    private void getSpeculativeClasses() throws FileNotFoundException
    {
       //Removing T Syapa :P
        String value="";
       /* for (int m =0;m<abstractclasses.size();m++){
             if (abstractclasses.get(m).endsWith("T")){
                abstractclasses.set(m, abstractclasses.get(m).substring(0,abstractclasses.get(m).length()-1));    
             }
         }*/
        for(int l=0;l<abstractclasses.size();l++)
            if(!Parents.contains(abstractclasses.get(l))){
               speculativeClasses.add(abstractclasses.get(l));
            }
       for (int i =0;i< speculativeClasses.size();i++){
           for(int j =0;j< pathofalljavafiles.size();j++){
              String path = pathofalljavafiles.get(j);
             
             String gettedpath = getpath(path,speculativeClasses.get(i)); 
             if (!gettedpath.isEmpty())
             {
                  classpaths.add(gettedpath);
             }
            
           }
        }
        for (int n =0;n< classpaths.size();n++){
            getPackage(classpaths.get(n));
        }
    }
    private String getpath(String pathofjava,String classname) throws FileNotFoundException
    {
        File files = new File (pathofjava);
        Scanner sc = new Scanner (files);
        String line ="";
        String pathofClassfile="";
        while(sc.hasNext())
        {  
            line = sc.nextLine();
            line= line.trim();
            
            String temp[]= line.split("\\s|\\{ |\\< |\\>");
            if (temp.length<3)
            {
                continue;
            }
            for(int i=0;i<line.length();i++)
            {
                if (temp[i].equals("private")||temp[i].equals("public"))
                    if (temp[i+1].equals("abstract")&& temp[i+2].equals("class"))
                        if(temp[i+3].contains(classname))
                        {
                            return pathofClassfile= pathofjava;
                        }
                if(temp[i].equals("abstract") && temp[i+1].equals("class"))
                    if(temp[i+2].contains(classname))
                    {
                      return pathofClassfile=pathofjava;
                    }
                break;
            }
        }
        return pathofClassfile ;
    }
    private void getPackage(String filepath){
        String pkg;
        String pkgg;
         pkg = filepath.substring(0, filepath.lastIndexOf("\\"));  
         pkgg = pkg.substring(pkg.lastIndexOf("\\")+1);
         ArrayList<String> str = new ArrayList<>();
        str.add(pkgg);
        while (!"src".equals(pkgg)) {
            pkg = pkg.substring(0, pkg.lastIndexOf("\\"));
            pkgg = pkg.substring(pkg.lastIndexOf("\\") + 1);
            str.add(pkgg);
        }
        String temp = "";
        for (int i = str.size() - 1; i >= 0; i--) {
            temp = temp + "." + str.get(i);
        }
        if (speculativeClasses.size()!=0){
            SpeculativeClassPackages.add(temp);
    }
    }
    
    
    private void parsefile(String fileName) throws IOException{
        File files = new File(fileName);
        String line ="";
        Scanner sc = new Scanner (files);      
        while (sc.hasNext()){
                line = sc.nextLine();
               String temp[] = line.trim().split(" ");
               for (int i=0;i<temp.length;i++)
               {
                   if (temp[i].equals("public")||temp[i].equals("private"))
                       if (temp[i+1].equals("abstract") && temp[i+2].equals("class"))
                       {
                           abstractclassesLine.add(line);
                           SpeculativeFiles.add(files.getName().trim());
                           break;
                       }
                   if (temp[i].equals("abstract")&& temp[i+1].equals("class"))
                       
                   {
                       abstractclassesLine.add(line);
                       SpeculativeFiles.add(files.getName());
                       break;
                   }
                   break;
               }
              /* if (line.contains("public ")|| line.contains("private "))
                    if (line.contains("abstract"))
                        if(line.contains("class "))
                            abstractclassesLine.add(line); */
               }
        
        }
    
    
    
    class Rotator extends Timer implements ActionListener {

    /** The plot. */
    private PiePlot3D plot;

    /** The angle. */
    private int angle = 270;

    /**
     * Constructor.
     *
     * @param plot  the plot.
     */
    Rotator(final PiePlot3D plot) {
        super(100, null);
        this.plot = plot;
        addActionListener(this);
    }

    /**
     * Modifies the starting angle.
     *
     * @param event  the action event.
     */
    public void actionPerformed(final ActionEvent event) {
        this.plot.setStartAngle(this.angle);
        this.angle = this.angle + 1;
        if (this.angle == 360) {
            this.angle = 0;
        }
    }

}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                /* if ("Nimbus".equals(info.getName())) {
                 javax.swing.UIManager.setLookAndFeel(info.getClassName());
                 break;
                 }*/
                UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Comments_btn;
    private javax.swing.JButton DataClass_btn;
    private javax.swing.JButton FeatureEnvy_btn;
    private javax.swing.JButton LargeClass_btn;
    private javax.swing.JButton LazyClass_btn;
    private javax.swing.JButton LongMethod_btn;
    private javax.swing.JButton LongParameter_btn;
    private javax.swing.JButton Message_Chain;
    public javax.swing.JButton Relationship;
    private javax.swing.JButton SwitchStatement_btn;
    private javax.swing.JButton brainmethodButton;
    private javax.swing.JButton browse;
    private javax.swing.JButton chart_btn;
    private javax.swing.JButton detect_btn;
    private javax.swing.JButton divergChangeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_threshold;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_HighImpact_low;
    private javax.swing.JTextField jTextField_HighImpact_up;
    private javax.swing.JTextField jTextField_LowImpact_low;
    private javax.swing.JTextField jTextField_LowImpact_up;
    private javax.swing.JTextField jTextField_MedImpact_low;
    private javax.swing.JTextField jTextField_MedImpact_up;
    private javax.swing.JTextField jTextField_browse;
    private javax.swing.JButton middleman_button;
    private javax.swing.JButton obsession_btn1;
    private javax.swing.JButton obsession_btn2;
    private javax.swing.JButton obsession_btn4;
    public javax.swing.JButton relations;
    private javax.swing.JButton reset_btn;
    private javax.swing.JButton s_Surgery_btn;
    private javax.swing.JButton s_g_v_b;
    private javax.swing.JButton short_method_b;
    private javax.swing.JButton speculative_Generality;
    // End of variables declaration//GEN-END:variables
	}
