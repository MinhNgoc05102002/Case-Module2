package controller;

import model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager {
    private ArrayList<Product> listProduct;

    public ProductManager() {
        listProduct = new ArrayList<>();
    }

    public boolean containsProduct(String id){
        for(Product p : listProduct) {
            if(p.getId().equals(id)) return true;
        }
        return false;
    }

    public void add(Product p) {
        listProduct.add(p);
    }
    public void addNewProduct() {
        Scanner sc = new Scanner(System.in);
        boolean check;
        String id;
        do {
            System.out.println("Nhập id sản phẩm: ");
            id = sc.nextLine();
            check = false;
            for (Product p : listProduct) {
                if (p.getId().equals(id)) {
                    check = true;
                    System.out.println("id sản phẩm đã tồn tại, mời bạn nhập lại !!!");
                }
            }
        } while (check);
        System.out.println("Nhập tên sản phẩm: ");
        String name = sc.nextLine();
        System.out.println("Nhập mô tả sản phẩm: ");
        String des = sc.nextLine();
        System.out.println("Nhập số lượng sản phẩm: ");
        int amount = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập giá sản phẩm: ");
        double price = Double.parseDouble(sc.nextLine());

        listProduct.add(new Product(id, name, des, amount, price));
    }

    public void display() {
        for(Product p:listProduct) {
            p.display();
        }
    }

    public Product findById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập id sản phẩm: ");
        String id = sc.nextLine();
        System.out.println("DANH SÁCH SẢN PHẨM TÌM ĐƯỢC: ");
        for(Product p:listProduct) {
            if(p.getId().equals(id)) {
                System.out.println(p);
                return p;
            }
        }
        System.out.println("\tTrống (không tìm thấy sản phẩm có mã " + id + ")");
        return null;
    }

    public void findByName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên sản phẩm cần tìm: ");
        String name = sc.nextLine();
        name.toLowerCase();
        boolean check = false;
        System.out.println("==> DANH SÁCH SẢN PHẨM TÌM ĐƯỢC: ");
        for(Product p:listProduct) {
            if(p.getName().toLowerCase().contains(name)) {
                p.display();
                check = true;
            }
        }
        if(check == false) System.out.println("Không tìm thấy sản phẩm có tên " + name);
    }

    public void delProduct(String id) {
        for(int i=0; i<listProduct.size(); i++) {
            if(listProduct.get(i).getId().equals(id)) {
                listProduct.remove(i);
            }
        }
    }

    public void filter() {
        double lowerLimit, upperLimit;
        Scanner sc = new Scanner(System.in);
        System.out.print("Bạn muốn hiển thị sản phẩm có giá từ: ");
        lowerLimit = Double.parseDouble(sc.nextLine());
        System.out.print("Đến giá: ");
        upperLimit = Double.parseDouble(sc.nextLine());

        System.out.println("DANH SÁCH SẢN PHẨM CÓ GIÁ TỪ " + lowerLimit + " ĐẾN " + upperLimit + ": ");
        for(Product p:listProduct) {
            if(p.getPrice() <= upperLimit && p.getPrice() >= lowerLimit) {
                p.display();
            }
        }
    }

    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
    }
}
