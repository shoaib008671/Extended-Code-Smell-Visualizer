/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tool.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Waseem Anjum
 */
public class package_get {
    
public String get_pkg(String f) throws FileNotFoundException
{
            File files = new File(f);
            String pkg=null;
            Scanner sc = new Scanner(files);
            String mth=null;
            boolean flag = true;
            
            while(sc.hasNext())
            {
                String line = sc.nextLine();
                
                if(line.contains("package")  &&  flag == true){
                   pkg = line.substring(line.lastIndexOf(" ")+1);
                   pkg=pkg.substring(0, pkg.lastIndexOf(";"));
                   flag=false;
               }
    }
            return pkg;
            
            
}
public String get_clas(String file)
{
    String clas = file.substring(file.lastIndexOf("\\")+1);
     clas=clas.substring(0, clas.indexOf(".java"));
    return clas;
}


}
