/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marioacosta.gui.files.logica.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author MAAACOST
 */
public class FileUtils {
    
    public static void writeLineInFile(String line, String fileNameOut) throws IOException{
        try (FileWriter fileWriter = new FileWriter(fileNameOut, true);
                BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.newLine();
            writer.append(line);
            // writer.close();
	} catch (IOException ex) {
            throw ex;
	}
    }
    
    
    
    
}
