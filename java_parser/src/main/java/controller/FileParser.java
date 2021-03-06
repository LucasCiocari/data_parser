package controller;

import model.DataWrapper;
import org.joda.time.DateTime;

import java.io.*;
import java.util.regex.Pattern;

public class FileParser {
    BufferedReader file;
    String fileName;

    public FileParser(String fileName) {
        this.fileName = fileName;
        try {
            file = new BufferedReader(new FileReader(new File(FileWatcher.pathToDir + "in/" + fileName)));
        } catch (FileNotFoundException e) {
            file = null;
        }
    }

    public void reopenFile() {
        try {
            file.ready();
            file.close();
        } catch (IOException e) {
            //File is closed, so just open it
        }
        try {
            file = new BufferedReader(new FileReader(new File(FileWatcher.pathToDir + fileName)));
        } catch (FileNotFoundException e) {
            file = null;
        }
    }


    public void closeFile() {
        try {
            file.close();
        } catch (IOException e) {
            //File is already closed.
        }
    }

    public DataWrapper parseDataIntoModel() {
        DataWrapper workData = new DataWrapper();
        String line;
        String[] data;

        try {
            while ((line = file.readLine()) != null) {
                data = line.split("ç");
                if (data[0].compareTo("001") == 0) {
                    workData.addSeller(data);
                } else if (data[0].compareTo("002") == 0) {
                    workData.addCustomer(data);
                } else if (data[0].compareTo("003") == 0) {
                    workData.addSale(data);
                }
            }
        } catch (IOException e) { }
        return workData;
    }

    public void generateResult(DataWrapper workData) {
        workData.computeMinMax();
        new File(FileWatcher.pathToDir + "out").mkdirs();
        try {
            FileWriter fw = new FileWriter(FileWatcher.pathToDir + "out/" + this.fileName.split(Pattern.quote("."))[0] + ".done.dat");
            fw.write("Report of "+ this.fileName + " on " + DateTime.now() + " :\n");
            fw.write("=\n");
            fw.write("Number of sellers: " + workData.getSellers().size() + "\n");
            fw.write("Number of customers: "+ workData.getCustomers().size() + "\n");
            fw.write("Id of the best sale: " + workData.getMax().getId() + "\n");
            fw.write("The worst salesman: " + workData.getMin().getSalesman() + "\n");
            fw.close();
            System.out.println("Generated file ~/data/out/" + this.fileName.split(Pattern.quote("."))[0] + ".done.dat");
        } catch (IOException e) {
            System.out.println("Failed to generate output.");
        }
    }

}
