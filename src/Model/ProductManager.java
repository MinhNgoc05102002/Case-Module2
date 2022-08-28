package Model;

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

    // 1. Thêm mới 1 sp
    public void add(Product p) {
        listProduct.add(p);
    }
    public void addNewProduct() {
        Scanner sc = new Scanner(System.in);
        int check;
        String id;
        do {
            System.out.println("Nhập id sản phẩm: ");
            id = sc.nextLine();
            check = 0;
            for (Product p : listProduct) {
                if (p.getId().equals(id)) {
                    check = 1;
                    System.out.println("id sản phẩm đã tồn tại, mời bạn nhập lại !!!");
                }
            }
        } while (check == 1);
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

    // 2. Hien thi sp => Hien thi theo danh muc sp: da co
    public void display() {
        System.out.println("------------- DANH SÁCH SẢN PHẨM ----------");
        for(Product p:listProduct) {
            System.out.println(p);
        }
    }

    // 3. Tim kiem va hien thi thong tin 1 sp:
    public Product findById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id san pham: ");
        String id = sc.nextLine();
        System.out.println("==> DANH SÁCH SẢN PHẨM TÌM ĐƯỢC: ");
        for(Product p:listProduct) {
            if(p.getId().equals(id)) {
                System.out.println(p);
                return p;
            }
        }
        System.out.println("Không tìm thấy sản phẩm có mã " + id);
        return null;
    }

    public void findByName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên sản phẩm cần tìm: ");
        String name = sc.nextLine();
        name.toLowerCase();
        int check = 0;
        System.out.println("==> DANH SÁCH SẢN PHẨM TÌM ĐƯỢC: ");
        for(Product p:listProduct) {
            if(p.getName().toLowerCase().contains(name)) {
                System.out.println(p);
                check = 1;
            }
        }
        if(check == 0) System.out.println("Không tìm thấy sản phẩm có tên " + name);
    }

    // 4. Xoa sp
//    public void delProduct() {
//        Scanner sc = new Scanner(System.in);
//        String id = sc.next();
//        int check = 0;
//        for(int i=0; i<listProduct.size(); i++) {
//            if(listProduct.get(i).getId().equals(id)) {
//                listProduct.remove(i);
//                check = 1;
//            }
//        }
//        if(check == 0) System.out.println("Khong tim thay san pham co ma " + id);
//    }

    public void delProduct(String id) {
        for(int i=0; i<listProduct.size(); i++) {
            if(listProduct.get(i).getId().equals(id)) {
                listProduct.remove(i);
            }
        }
    }

    // 6. Hiển thị sp theo khoảng giá
    public void filter() {
        double lowerLimit, upperLimit;
        Scanner sc = new Scanner(System.in);
        System.out.print("Bạn muốn hiển thị sản phẩm có giá từ: ");
        lowerLimit = Double.parseDouble(sc.nextLine());
        System.out.print("Đến giá: ");
        upperLimit = Double.parseDouble(sc.nextLine());

        System.out.println("==> Danh sách sản phẩm có giá từ " + lowerLimit + " đến " + upperLimit + ": ");
        for(Product p:listProduct) {
            if(p.getPrice() <= upperLimit && p.getPrice() >= lowerLimit) {
                System.out.println(p);
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