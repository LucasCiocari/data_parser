package app;

import model.Customer;
import model.Item;
import model.Sale;
import model.Seller;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        ArrayList<Sale> sales = new ArrayList<Sale>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        ArrayList<Item> items = new ArrayList<Item>();

        String path = System.getenv("HOMEPATH") + "\\data\\";
        System.out.println(path);
        File fileName = new File(path + "\\in\\" + "infos.dat");
        try {
            BufferedReader file = new BufferedReader(new FileReader(fileName));
            String line;
            String[] data;
            String[] line_items;
            while(null != (line = file.readLine())){
                data = line.split("ç");
                if(data[0].compareTo("001") == 0) {
                    sellers.add(new Seller(data[1], data[2], Double.parseDouble(data[3])));
                }
                else if (data[0].compareTo("002") == 0) {
                    customers.add(new Customer(data[1], data[2], data[3]));
                }
                else if (data[0].compareTo("003") == 0) {
                    data[2] = data[2].split(Pattern.quote("["))[1];
                    data[2] = data[2].split("]")[0];

                    line_items = data[2].split(",");
                    items.clear();
                    for(String item : line_items) {
                        String[] i = item.split("-");
                        items.add(new Item(i[0], Integer.parseInt(i[1]), Double.parseDouble(i[2])));
                    }
                    sales.add(new Sale(data[1], items, data[3]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(customers.size());
        System.out.println(sellers.size());
        Sale max = sales.get(0);
        Sale min = sales.get(0);

        for(Sale sale : sales) {
            if(sale.getTotal() > max.getTotal()){
                max = sale;
            }
            if(sale.getTotal() < min.getTotal()) {
                min = sale;
            }
        }

        System.out.println(max.getId());
        System.out.println(min.getSalesman());
    }
}
