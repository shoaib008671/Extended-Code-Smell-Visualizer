/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tool.csv;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

/**
 *
 * @author Saad
 */
public class SearchDirectories {
    public ArrayList<String> getfiles(String Dir) throws IOException{
        String Directory = Dir;
        ArrayList<String> filesdata= new ArrayList<>();
         File dir = new File(Directory);
         List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
         
                for(int i =0;i<files.size();i++){
                    filesdata.add(files.get(i).toString());
                }
    
        
        return filesdata;
        
    }
}
