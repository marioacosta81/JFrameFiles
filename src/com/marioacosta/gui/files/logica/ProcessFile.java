/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marioacosta.gui.files.logica;

import com.marioacosta.gui.files.logica.model.ErrorLog;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.common.io.ByteSource;
import com.marioacosta.gui.files.logica.utils.FileUtils;
import com.marioacosta.gui.files.logica.utils.ListUtils;
import java.awt.Desktop;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *
 * @author MAAACOST
 */
public class ProcessFile {
    
    private static final String FILE_NAME_OUT = "informe.txt"; 
    
    
    public void leerArchivo(File folder) throws IOException{
        List<ErrorLog> listError = new ArrayList<>();
	for (File fileLog : folder.listFiles()) {
            byte[] array = Files.readAllBytes(Paths.get(fileLog.getAbsolutePath()));
            InputStream targetStream = ByteSource.wrap(array).openStream();
            Scanner sc = new Scanner(targetStream, StandardCharsets.UTF_8.name());
            long cont = 0L;
            while (sc.hasNextLine()) {
                cont++;
		String lineTraza = sc.nextLine();
                
                ErrorLog errorlog = new ErrorLog();
		errorlog.setServicio(folder.getName());
		errorlog.setArchivo(fileLog.getName());
		if (lineTraza.length() < 5) {
                    continue;
		}
		//String temp = lineTraza.split(" ").length<1?"":lineTraza.split(" ")[0];
                Pattern p = Pattern.compile("(ERROR)");
                Matcher m  = p.matcher(lineTraza);
                String temp =  m.find()?m.group(1):"";
		if (temp.equals("ERROR")  ) {
                    String lineExcepcion = "";
                    if(sc.hasNext()) {
			lineExcepcion = sc.nextLine();
                    }
                    errorlog.setTipoLog("ERROR");
                    errorlog.setTraza( cont + " ->" + lineTraza + "\n" +    ++cont + " ->" + lineExcepcion);
                    errorlog.setTrazaId(lineExcepcion);
                    errorlog.setOcurrencias(1);
                    if (listError.contains(errorlog)) {
                        aumentarOcurrencia(listError,errorlog);
                            continue;
                    }
                    listError.add(errorlog);
		}
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }		
	}	
	
        ListUtils<ErrorLog> listUtils = new ListUtils<ErrorLog>();
        listUtils.sortList(listError);
        
	for(ErrorLog error: listError) {
            FileUtils.writeLineInFile("**********************************************************************", FILE_NAME_OUT);
            FileUtils.writeLineInFile(error.toString(), FILE_NAME_OUT);
	}
        
        
        Desktop.getDesktop().open(new File(FILE_NAME_OUT));
        
        
        
    }
    
    private static void aumentarOcurrencia(List<ErrorLog> list, ErrorLog errorLog) {	
	for(ErrorLog e  :list) {
            if(e.equals(errorLog)) {
		e.setOcurrencias( e.getOcurrencias() +1  );
            }
        }
    }
    
}
