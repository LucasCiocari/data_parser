package app;

import model.*;

import java.io.*;

public class App {

    public static void main(String[] args) {

        DataWrapper workData = new DataWrapper();

        String path = System.getenv("HOMEPATH") + "\\data\\";
        System.out.println(path);
        File fileName = new File(path + "\\in\\" + "infos.dat");
        try {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
    }
}
