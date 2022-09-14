package controller;

import model.Category;
import model.Product;
import storage.ReadAndWriteFile;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryManager {
    private ProductManager listAllProduct;
    private ArrayList<Category> listCategory;
    private static CategoryManager instance = null;
    public static CategoryManager getInstance() {
        if(instance == null) {
            instance = new CategoryManager();
        }
        return instance;
    }

    private CategoryManager() {
        listCategory = new ArrayList<>();
        listAllProduct = new ProductManager();

        listAllProduct.setListProduct(ReadAndWriteFile.readFileProduct());

        listCategory = ReadAndWriteFile.readFileCategory(listAllProduct);
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

    public boolean containsCategoryId(String id) {
        for(Category c : listCategory) {
            if(c.getId().equals(id)) return true;
        }
        return false;
    }

    public void displayProduct() {
        for(int i=0; i<listCategory.size(); i++) {
            System.out.println("\n" + i + ". " + "Id: " + listCategory.get(i).getId() + " | " + listCategory.get(i).getCategoryName());
            listCategory.get(i).display();
        }
        System.out.println("\n------------ DANH SÁCH TẤT CẢ SẢN PHẨM ------------");
        listAllProduct.display();
    }

    public void display() {
        System.out.println("Danh sách các danh mục sản phẩm: ");
        for(int i=0; i<listCategory.size(); i++) {
            System.out.println(i + ". " + "Id: " + listCategory.get(i).getId() + " | " + listCategory.get(i).getCategoryName());
        }
    }


    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập id của danh mục sản phẩm: ");
        String id;
        do{
            id = sc.nextLine();
            if(containsCategoryId(id)) {
                System.out.println("Id của danh mục sản phẩm đã tồn tại, mời nhập lại: ");
            }
        } while(containsCategoryId(id));

        System.out.println("Nhập tên danh mục sản phẩm: ");
        String categoryName = sc.nextLine() ;
        System.out.println("Nhập mô tả của danh mục sản phẩm: ");
        String description = sc.nextLine();

        listCategory.add(new Category(id, categoryName, description));
        System.out.println("Thêm thành công!!!");
    }


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
        if(!listAllProduct.containsProduct(id)) {
            System.out.println("Sản phẩm có mã " + id + " không tồn tại!");
            return;
        }
        listAllProduct.delProduct(id);
        for(Category c:listCategory)
            c.delProduct(id);
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
                c.delProduct(productId);
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
                System.out.println("Nhập id sản phẩm muốn thêm: ");
                productId = sc.nextLine();
                if(listAllProduct.containsProduct(productId) == false) {
                    System.out.println("Không tồn tại sản phẩm có mã " + productId);
                    return;
                }

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
        if(!listAllProduct.containsProduct(productId)) {
            System.out.println("Product Id " + productId + " không tồn tại");
            return;
        }
        for(Product p : listAllProduct.getListProduct()) {
            if(p.getId().equals(productId)) {
                System.out.println("Nhập tên mới: ");
                String newName = sc.nextLine();
                p.setName(newName);

                updateCategory();
            }
        }
    }

    public void changeProductDes() {
        System.out.println("Nhập id sp muốn sửa: ");
        Scanner sc = new Scanner(System.in);
        String productId = sc.nextLine();
        if(!listAllProduct.containsProduct(productId)) {
            System.out.println("Product Id " + productId + " không tồn tại");
            return;
        }
        for(Product p : listAllProduct.getListProduct()) {
            if(p.getId().equals(productId)) {
                System.out.println("Nhập mô tả mới: ");
                String newDes = sc.nextLine();
                p.setDescription(newDes);

                updateCategory();
            }
        }
    }

    public void changeProductAmount() {
        System.out.println("Nhập id sp muốn sửa: ");
        Scanner sc = new Scanner(System.in);
        String productId = sc.nextLine();
        if(!listAllProduct.containsProduct(productId)) {
            System.out.println("Product Id " + productId + " không tồn tại");
            return;
        }
        for(Product p : listAllProduct.getListProduct()) {
            if(p.getId().equals(productId)) {
                System.out.println("Nhập số lượng mới: ");
                int newAmount = Integer.parseInt(sc.nextLine());
                p.setAmount(newAmount);

                updateCategory();
            }
        }
    }

    public void changeProductPrice() {
        System.out.println("Nhập id sản phẩm muốn sửa: ");
        Scanner sc = new Scanner(System.in);
        String productId = sc.nextLine();
        if(!listAllProduct.containsProduct(productId)) {
            System.out.println("Product Id " + productId + " không tồn tại");
            return;
        }
        for(Product p : listAllProduct.getListProduct()) {
            if(p.getId().equals(productId)) {
                System.out.println("Nhập giá mới: ");
                double newPrice = Double.parseDouble(sc.nextLine());
                p.setPrice(newPrice);

                updateCategory();
            }
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
