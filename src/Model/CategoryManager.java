package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryManager {
    private ProductManager listAllProduct;
    private ArrayList<Category> listCategory;

    public CategoryManager() {
        listCategory = new ArrayList<>();
        listAllProduct = new ProductManager();
    }

    public void updateCategory() {
        for(Category c : listCategory) {
            c.setProductList(new ProductManager());
            for(Product p : listAllProduct.getListProduct()) {
                if(p.getCategoryId().contains(c.getId())) {
                    c.getProductList().add(p);
                }
            }
        }
    }

    public boolean containsProductId(String id) {
        for(Product p : listAllProduct.getListProduct()) {
            if(p.getId().equals(id)) return true;
        }
        return false;
    }

    public boolean containsCategoryId(String id) {
        for(Category c : listCategory) {
            if(c.getId().equals(id)) return true;
        }
        return false;
    }

    // Hien thi sp theo danh mục sp
    public void displayProduct() {
        for(int i=0; i<listCategory.size(); i++) {
            System.out.println("\n" + i + ". " + "Id: " + listCategory.get(i).getId() + " | " + listCategory.get(i).getCategoryName());
            listCategory.get(i).display();
        }
        System.out.println("\n------------ DANH SÁCH TẤT CẢ SẢN PHẨM ------------");
        listAllProduct.display();
    }
    // 1. Xem danh muc sp
    public void display() {
        System.out.println("Danh sách các danh mục sản phẩm: ");
        for(int i=0; i<listCategory.size(); i++) {
            System.out.println(i + ". " + "Id: " + listCategory.get(i).getId() + " | " + listCategory.get(i).getCategoryName());
        }
    }

    // 2. Thêm danh mục sp
    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập id của danh mục sản phẩm: ");
        String id;
        do{
            id = sc.nextLine();
            if(containsCategoryId(id)) {
                System.out.println("id của danh mục sản phẩm đã tồn tại, mời nhập lại: ");
            }
        } while(containsCategoryId(id));

        System.out.println("Nhập tên danh mục sản phẩm: ");
        String categoryName = sc.nextLine() ;
        System.out.println("Nhập mô tả của danh mục sản phẩm: ");
        String description = sc.nextLine();

        listCategory.add(new Category(id, categoryName, description));
        System.out.println("Thêm thành công!!!");
    }

    // 3. Xóa danh mục sp
    public void del() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập id của danh mục sản phẩm muốn xóa: ");
        String id = sc.nextLine();
        if(!containsCategoryId(id)) {
            System.out.println("Không tồn tại danh mục sản phẩm có mã là: " + id);
            return;
        }
        for(int i=0; i<listCategory.size(); i++) {
            if(listCategory.get(i).getId().equals(id)) {
                for(Product p:listCategory.get(i).getProductList()) {
                    p.delCategory(id);
                }
                listCategory.remove(i);
            }
        }
        System.out.println("Đã xóa danh mục sản phẩm thành công !!!");
    }

    public void changeCategoryName() {
        System.out.println("Nhập id danh mục sản phẩm muốn sửa: ");
        Scanner sc = new Scanner(System.in);
        String cateId = sc.nextLine();

        if(!containsCategoryId(cateId)) {
            System.out.println("Category Id " + cateId + " không tồn tại");
            return;
        }
        for(Category c : listCategory) {
            if(c.getId().equals(cateId)) {
                System.out.println("Nhập tên danh mục sản phẩm mới: ");
                String newName = sc.nextLine();
                c.setCategoryName(newName);
                System.out.println("Sửa tên danh mục sản phẩm thành công !!!");
            }
        }
    }

    public void changeCategoryDes() {
        System.out.println("Nhập id danh mục sản phẩm muốn sửa: ");
        Scanner sc = new Scanner(System.in);
        String cateId = sc.nextLine();

        if(!containsCategoryId(cateId)) {
            System.out.println("Category Id " + cateId + " không tồn tại");
            return;
        }
        for(Category c : listCategory) {
            if(c.getId().equals(cateId)) {
                System.out.println("Nhập mô tả danh mục sản phẩm mới: ");
                String newDes = sc.nextLine();
                c.setDescription(newDes);
                System.out.println("Sửa mô tả danh mục sản phẩm thành công !!!");
            }
        }
    }

    public void delProductForever() {
        System.out.println("Nhập id sản phẩm muốn xóa");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        int check = 0;
        for(int i=0; i<listAllProduct.getListProduct().size(); i++) {
            if(listAllProduct.getListProduct().get(i).getId().equals(id)) {
                check = 1;
                listAllProduct.delProduct(id);
                for(Category c:listCategory) {
                    c.del(id);
                }
            }
        }
        if(check == 0) System.out.println("Sản phẩm có mã " + id + " không tồn tại!");
    }

    public void delProductInCategory() {
        System.out.println("Nhập id danh mục sản phẩm muốn sửa: ");
        Scanner sc = new Scanner(System.in);
        String cateId = sc.nextLine();
        if(!containsCategoryId(cateId)) {
            System.out.println("Category Id " + cateId + " không tồn tại");
            return;
        }
        for(Category c : listCategory) {
            if(c.getId().equals(cateId)) {
                System.out.println("Nhập id sản phẩm muốn xóa: ");
                String productId = sc.nextLine();
                c.del(productId);
                for(Product p:listAllProduct.getListProduct()) {
                    if(p.getId().equals(productId)) {
                        p.delCategory(cateId);
                    }
                }
            }
        }
    }


    public void addProductToCategory() {
        System.out.println("Nhập id danh mục sản phẩm muốn sửa: ");
        Scanner sc = new Scanner(System.in);
        String cateId = sc.nextLine();
        if(!containsCategoryId(cateId)) {
            System.out.println("Category Id " + cateId + " không tồn tại");
            return;
        }
        for(Category c : listCategory) {
            if(c.getId().equals(cateId)) {
                String productId = "";
                do{
                    System.out.println("Nhập id sản phẩm muốn thêm: ");
                    productId = sc.nextLine();
                    if(containsProductId(productId) == false) System.out.println("Không tồn tại sản phẩm có mã " + productId + ".Mời nhập lại.");
                } while(!containsProductId(productId));

                if(c.getProductListManager().containsProduct(productId)) return;

                for(Product p:listAllProduct.getListProduct()) {
                    if(p.getId().equals(productId)) {
                        p.addToCategory(cateId);
                        c.add(p);
                    }
                }
            }
        }
    }

    public void changeProductName() {
        System.out.println("Nhập id sản phẩm muốn sửa: ");
        Scanner sc = new Scanner(System.in);
        String productId = sc.nextLine();
        int check = 0;
        for(Product p : listAllProduct.getListProduct()) {
            if(p.getId().equals(productId)) {
                check = 1;
                System.out.println("Nhập tên mới: ");
                String newName = sc.nextLine();
                p.setName(newName);

                updateCategory();
            }
        }
        if(check == 0) {
            System.out.println("Product Id " + productId + " không tồn tại");
        }
    }

    public void changeProductDes() {
        System.out.println("Nhập id sp muốn sửa: ");
        Scanner sc = new Scanner(System.in);
        String productId = sc.nextLine();
        int check = 0;
        for(Product p : listAllProduct.getListProduct()) {
            if(p.getId().equals(productId)) {
                check = 1;
                System.out.println("Nhập mô tả mới: ");
                String newDes = sc.nextLine();
                p.setDescription(newDes);

                updateCategory();
            }
        }
        if(check == 0) {
            System.out.println("Product Id " + productId + " không tồn tại");
        }
    }

    public void changeProductAmount() {
        System.out.println("Nhập id sp muốn sửa: ");
        Scanner sc = new Scanner(System.in);
        String productId = sc.nextLine();
        int check = 0;
        for(Product p : listAllProduct.getListProduct()) {
            if(p.getId().equals(productId)) {
                check = 1;
                System.out.println("Nhập số lượng mới: ");
                int newAmount = Integer.parseInt(sc.nextLine());
                p.setAmount(newAmount);

                updateCategory();
            }
        }
        if(check == 0) {
            System.out.println("Product Id " + productId + " không tồn tại");
        }
    }

    public void changeProductPrice() {
        System.out.println("Nhập id sản phẩm muốn sửa: ");
        Scanner sc = new Scanner(System.in);
        String productId = sc.nextLine();
        int check = 0;
        for(Product p : listAllProduct.getListProduct()) {
            if(p.getId().equals(productId)) {
                check = 1;
                System.out.println("Nhập giá mới: ");
                double newPrice = Double.parseDouble(sc.nextLine());
                p.setPrice(newPrice);

                updateCategory();
            }
        }
        if(check == 0) {
            System.out.println("Product Id " + productId + " không tồn tại");
        }
    }

    public ProductManager getListAllProduct() {
        return listAllProduct;
    }

    public void setListAllProduct(ProductManager allProduct) {
        this.listAllProduct = allProduct;
    }

    public ArrayList<Category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(ArrayList<Category> listCategory) {
        this.listCategory = listCategory;
    }
}
