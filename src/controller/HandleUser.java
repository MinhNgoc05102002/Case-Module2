package controller;

import model.account.Account;
import storage.ReadAndWriteFile;
import model.menu.Menu;
import model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class HandleUser {
    public AccountManager accountManager = new AccountManager();
    public CategoryManager categoryManager = new CategoryManager();
    private Account thisUser = new Account();
    public final int DEFAULT = -1;
    public final int EXIT = 0;
    public final int REGISTER = 1;
    public final int LOGIN = 2;
    public final int DISPLAY_PRODUCT_BY_CATEGORY = 1;
    public final int FIND_PRODUCT_BY_NAME = 2;
    public final int FILTER_PRODUCT = 3;
    public final int ADD_TO_CART = 4;
    public final int DISPLAY_CART = 5;

    public HandleUser() {
        categoryManager.getListAllProduct().setListProduct(ReadAndWriteFile.readFileProduct());

        categoryManager.setListCategory(ReadAndWriteFile.readFileCategory(categoryManager.getListAllProduct()));

        accountManager.setListAccount(ReadAndWriteFile.readFileAccount(ReadAndWriteFile.PATH_ACCOUNT_USER));
    }

    public void startMenu() {
        int choose = DEFAULT;
        do {
            Menu.showStartMenu();
            choose = Menu.inputChoose();
            switch (choose) {
                case REGISTER:
                    accountManager.register();
                    break;
                case LOGIN:
                    if(accountManager.logIn(thisUser))
                        mainMenu();
                    break;
                case EXIT:
                    System.out.println("See you again");
                    ReadAndWriteFile.writeToFileProduct(categoryManager.getListAllProduct().getListProduct());
                    ReadAndWriteFile.writeToFileAccount(accountManager.getListAccount(), ReadAndWriteFile.PATH_ACCOUNT_USER);
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != EXIT);
    }

    public void mainMenu() {
        int choose = DEFAULT;
        do {
            Menu.showMainUserMenu();
            choose = Menu.inputChoose();
            switch (choose) {
                case DISPLAY_PRODUCT_BY_CATEGORY:
                    categoryManager.displayProduct();
                    break;
                case FIND_PRODUCT_BY_NAME:
                    categoryManager.getListAllProduct().findByName();
                    break;
                case FILTER_PRODUCT:
                    categoryManager.getListAllProduct().filter();
                    break;
                case ADD_TO_CART:
                    addToCart();
                    break;
                case DISPLAY_CART:
                    displayCart();
                    break;
                case EXIT:
                    ReadAndWriteFile.writeToFileProduct(categoryManager.getListAllProduct().getListProduct());
                    ReadAndWriteFile.writeToFileAccount(accountManager.getListAccount(), ReadAndWriteFile.PATH_ACCOUNT_USER); // ? ko hiểu sao lại lưu dc this user từ đây
                    System.out.println("Goodbye " + thisUser.getUserName() + ". See you again");
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != EXIT);
    }

    public void addToCart() {
        Product product;
        do {
            product = categoryManager.getListAllProduct().findById();
            if(product == null) System.out.println("Mời nhập lại.");
        } while(product == null);

        if(product.getAmount() <= 0) {
            System.out.println("Sản phẩm đã hết hàng. Quý khách hãy lựa chọn sản phẩm khác.");
            return;
        }

        int amount;
        do {
            System.out.println("Nhập số lượng: ");
            amount = Menu.inputChoose();
            if(amount <= 0) System.out.println("Số lượng nhập ko hợp lệ. Hãy nhập lại");
            if (amount > product.getAmount()) System.out.println("Hiện trong kho chỉ còn " + product.getAmount() + " sản phẩm. Quý khách vui lòng nhập lại số lượng");
        } while (amount <= 0 || amount > product.getAmount());

        thisUser.addToCart(product, amount);
    }

    public void displayCart() {
        double totalPrice = 0;
        for(String s : thisUser.getCart()) {
            String[] arr = s.split("_");
            String productId = arr[0];
            int quantity = Integer.parseInt(arr[1]);
            for(Product p:categoryManager.getListAllProduct().getListProduct()) {
                if(p.getId().equals(productId)) {
                    System.out.println("Id: " + p.getId() + ". " + p.getName() + " | Giá: " + p.getPrice() + " | Số lượng: " + quantity);
                    totalPrice += p.getPrice()*quantity;
                }
            }
        }
        System.out.println("======================================> Tổng thanh toán: " + totalPrice);
        if(totalPrice == 0) return;
        System.out.println("Nhập ''mua'' để thanh toán giỏ hàng, nhấn phím bất kì để trở lại: ");

        Scanner sc = new Scanner(System.in);
        String choose = sc.next();
        if(choose.equals("mua")) {
            for(String s : thisUser.getCart()) {
                String[] arr = s.split("_");
                String productId = arr[0];
                int quantity = Integer.parseInt(arr[1]);
                for(Product p:categoryManager.getListAllProduct().getListProduct()) {
                    if(p.getId().equals(productId)) {
                        if(p.getAmount() - quantity < 0) p.setAmount(0);
                        else p.setAmount(p.getAmount() - quantity);
                        categoryManager.updateCategory();
                    }
                }
            }
            thisUser.setCart(new ArrayList<>());
            for(Account user : accountManager.getListAccount()) {
                if(user.getUserName().equals(thisUser.getUserName())) {
                    user.setCart(new ArrayList<>());
                }
            }
            System.out.println("Đặt hàng thành công!!!");
        }
    }

}
