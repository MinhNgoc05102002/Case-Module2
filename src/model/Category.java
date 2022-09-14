package model;

import controller.ProductManager;

import java.util.ArrayList;

public class Category {
    private String id;
    private String categoryName;
    private String description;
    private ProductManager productList;

    public ProductManager getProductListManager() {
        return productList;
    }

    public Category() {
        productList = new ProductManager();
    }

    public Category(String id, String categoryName, String description) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        productList = new ProductManager();
    }

    public void add(Product p) {
        if(!productList.containsProduct(p.getId()))
            productList.add(p);
    }

    public void display() {
        productList.display();
    }

    public void delProduct(String id) {
        if(!productList.containsProduct(id)) {
            return;
        }
        for(int i=0; i<productList.getListProduct().size(); i++) {
            if(productList.getListProduct().get(i).getId().equals(id)) {
                productList.getListProduct().remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return  id + ',' +
                categoryName + ',' +
                description;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Category) {
            Category another = (Category) obj;
            if(this.id.equals(another.id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Product> getProductList() {
        return productList.getListProduct();
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductList(ProductManager productList) {
        this.productList = productList;
    }
}
