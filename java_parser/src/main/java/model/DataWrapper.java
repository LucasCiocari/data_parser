package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DataWrapper {

    private ArrayList<Seller> sellers;
    private ArrayList<Sale> sales;
    private ArrayList<Customer> customers;
    private Sale max;
    private Sale min;

    public DataWrapper() {
        sellers = new ArrayList<Seller>();
        sales = new ArrayList<Sale>();
        customers = new ArrayList<Customer>();
    
    }

    public Sale getMax() {
        if (max == null){
            this.computeMinMax();
        }
        return max;
    }

    public Sale getMin() {
        if (min == null){
            this.computeMinMax();
        }
        return min;
    }

    public void computeMinMax(){
        if(!sales.isEmpty()) {
            max = this.getSales().get(0);
            min = max;

            for (Sale sale : this.getSales()) {
                if (sale.getTotal() > max.getTotal()) {
                    max = sale;
                }
                if (sale.getTotal() < min.getTotal()) {
                    min = sale;
                }
            }
        }
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void addSeller(String[] newSeller) {
        sellers.add(new Seller(newSeller[1], newSeller[2], Double.parseDouble(newSeller[3])));
    }

    public void addCustomer(String[] newCustomer) {
        customers.add(new Customer(newCustomer[1], newCustomer[2], newCustomer[3]));
    }

    public void addSale(String[] newSale) {

        newSale[2] = newSale[2].split(Pattern.quote("["))[1];
        newSale[2] = newSale[2].split("]")[0];

        ArrayList<Item> items = new ArrayList<Item>();;
        String[] line_items = newSale[2].split(",");
        items.clear();
        for(String item : line_items) {
            String[] i = item.split("-");
            items.add(new Item(i[0], Integer.parseInt(i[1]), Double.parseDouble(i[2])));
        }

        sales.add(new Sale(newSale[1], items, newSale[3]));
    }

}
