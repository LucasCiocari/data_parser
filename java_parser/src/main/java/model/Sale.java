package model;

import java.util.ArrayList;

public class Sale {

    private String id;
    private ArrayList<Item> itemsSold;
    private String salesman;
    private Double total = 0.0;

    public Sale(String id, ArrayList<Item> itemsSold, String salesman) {
        this.id = id;
        this.itemsSold = itemsSold;
        this.salesman = salesman;

        computeTotal();
    }

    private void computeTotal() {
        for(Item i : itemsSold) total += i.getPrice() * i.getQuantity();
    }

    public String getId() {
        return id;
    }

    public ArrayList<Item> getItemsSold() {
        return itemsSold;
    }

    public String getSalesman() {
        return salesman;
    }

    public Double getTotal() {
        return total;
    }
}
