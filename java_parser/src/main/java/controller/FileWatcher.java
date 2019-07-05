package controller;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class FileWatcher {
    static public String pathToDir = System.getenv("HOMEPATH") + "/data/";
    WatchService watcher;
    WatchKey watchKey;
    WatchKey key;
    Path path;

    public FileWatcher() {
        path = Paths.get(pathToDir + "in");
        try {
            watcher = FileSystems.getDefault().newWatchService();
            watchKey = path.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> waitForFiles() {

        ArrayList<String> files = new ArrayList<>();
        try {
            while ((key = watcher.take()) != null) {
                for (WatchEvent event : key.pollEvents()) {
                    String[] fileName = event.context().toString().split(Pattern.quote("."));
                    if (fileName[fileName.length - 1].compareTo("dat") == 0  && fileName[fileName.length - 2].compareTo("done")!= 0) {
                        files.add(event.context().toString());
                    }

                }
                key.reset();
                return files;
            }

        } catch (InterruptedException e) { }
        return files;
    }

}

