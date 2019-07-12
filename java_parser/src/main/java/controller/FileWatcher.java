package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class FileWatcher {
    static public String pathToDir = System.getenv("HOMEPATH") + "/data/";
    private WatchService watcher;
    private WatchKey key;
    private Path path;
    ArrayList<String> files;
    

    public FileWatcher() {
        path = Paths.get(pathToDir + "in");
        try {
            watcher = FileSystems.getDefault().newWatchService();
            path.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        files = new ArrayList<String>();
        File[] directoryFiles = new File(pathToDir + "in").listFiles();
       
        for (File file : directoryFiles) {
            if (file.isFile()) {
            	if(isValidFile(file.getName())) {
            		files.add(file.getName());	
            	}
            }
        }
    }
    
    private boolean isValidFile(String file) {
    	String[] fileName = file.split(Pattern.quote("."));
        if (fileName[fileName.length - 1].compareTo("dat") == 0  && fileName[fileName.length - 2].compareTo("done")!= 0) {
        	return true;
        }
        return false;
    }

    public ArrayList<String> waitForFiles() {
        ArrayList<String> returnFiles = new ArrayList<String>();    	

        if(files.isEmpty()) {
	        try {
	            while ((key = watcher.take()) != null) {
	                for (WatchEvent<?> event : key.pollEvents()) {
	                	if(isValidFile(event.context().toString()))
	                    {
	                    	returnFiles.add(event.context().toString());
	                    }
	
	                }
	                key.reset();
	                break;
	            }
	
	        } catch (InterruptedException e) { System.out.println("FileWatcher failed, try again.");}
        }
        else {
        	for (String file : files) {
        		returnFiles.add(file);
        	}
        	files.clear();     	
        }
 
        return returnFiles;
    }

}

