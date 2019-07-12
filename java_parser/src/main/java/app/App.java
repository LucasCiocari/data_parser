package app;

import controller.FileParser;
import controller.FileWatcher;
import model.DataWrapper;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
    	
    	
        DataWrapper workData = new DataWrapper();

        FileWatcher watcher = new FileWatcher();
        ArrayList<String> files;
        while (true) {
            files = watcher.waitForFiles();
      
            for (String fileName : files) {
                System.out.println("Found file " + fileName);
                FileParser parser = new FileParser(fileName);
                workData = parser.parseDataIntoModel();
                parser.closeFile();
                parser.generateResult(workData);
            }
            System.out.println("Listening for new files...");
        }
    }
}
