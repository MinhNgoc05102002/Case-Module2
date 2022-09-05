package Model;

import java.util.ArrayList;

public class Product {
    private String id;
    private String name;
    private String description;
    private int amount;
    private double price;
    private ArrayList<String> categoryId = new ArrayList<>();

    public Product() {

    }

    public Product(String id, String name, String description, int amount, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
    }

    public void delCategory(String id) {
        for(int i=0; i<categoryId.size(); i++) {
            if(categoryId.get(i).equals(id))
                categoryId.remove(i);
        }
    }

    public Product(String id, String name, String description, int amount, double price, ArrayList<String> categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.categoryId = categoryId;
    }

    public void display() {
         System.out.println(id + ", " + name + " (( " + description + ")) | Số lượng: " + amount + " | Giá tiền: " + price);
    }

    @Override
    public String toString() {
        String productString = "";
        productString +=id + ',' +
                        name + ',' +
                        description + ',' +
                        amount + ',' +
                        price;
        for(String s : categoryId) {
            productString += "," + s;
        }
        return productString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<String> getCategoryId() {
        return categoryId;
    }

    public void addToCategory(String categoryId) {
        this.categoryId.add(categoryId);
    }
}
