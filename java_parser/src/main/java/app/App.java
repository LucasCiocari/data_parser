package app;

import model.DataWrapper;
import model.Sale;

import java.io.*;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class App {

    public static void main(String[] args) {

        DataWrapper workData = new DataWrapper();

        String pathToDir = System.getenv("HOMEPATH") + '/';
        Path path = Paths.get(pathToDir);

        WatchService watcher = null;
        try {

            watcher = FileSystems.getDefault().newWatchService();
            WatchKey watchKey = path.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
            WatchKey key;
            while((key = watcher.take()) != null) {
                for (WatchEvent event : key.pollEvents()) {

                    File fileName = new File(pathToDir + event.context());

                    BufferedReader file = new BufferedReader(new FileReader(fileName));
                    String line;
                    String[] data;

                    while(null != (line = file.readLine())){
                        data = line.split("ç");
                        if(data[0].compareTo("001") == 0) {
                            workData.addSeller(data);
                        }
                        else if (data[0].compareTo("002") == 0) {
                            workData.addCustomer(data);
                        }
                        else if (data[0].compareTo("003") == 0) {
                            workData.addSale(data);
                        }
                    }
                    
                    Sale max = workData.getSales().get(0);
                    Sale min = max;

                    for(Sale sale : workData.getSales()) {
                        if(sale.getTotal() > max.getTotal()){
                            max = sale;
                        }
                        if(sale.getTotal() < min.getTotal()) {
                            min = sale;
                        }
                    }
                    System.out.println(workData.getSellers().size());
                    System.out.println(workData.getCustomers().size());
                    System.out.println(max.getId());
                    System.out.println(min.getSalesman());

                    file.close();
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
