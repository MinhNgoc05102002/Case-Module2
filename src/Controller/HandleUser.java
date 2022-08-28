package Controller;

import Model.Account.Account;
import Model.Account.AccountManager;
import Model.CategoryManager;
import Model.IO.ReadAndWriteFile;
import Model.Menu.Menu;
import Model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class HandleUser {
    public AccountManager accountManager = new AccountManager();
    public CategoryManager categoryManager = new CategoryManager();
    private Account thisUser = new Account();

    public HandleUser() {
        categoryManager.getListAllProduct().setListProduct(ReadAndWriteFile.readFileProduct());

        categoryManager.setListCategory(ReadAndWriteFile.readFileCategory(categoryManager.getListAllProduct()));

        accountManager.setListAccount(ReadAndWriteFile.readFileAccount(ReadAndWriteFile.PATH_ACCOUNT_USER));
    }

    public void startMenu() {
        Scanner sc = new Scanner(System.in);

        int choose = -1;

        do {
            Menu.showStartMenu();
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    accountManager.register();
                    break;
                case 2:
                    if(accountManager.logIn(thisUser))
                        mainMenu();
                    break;
                case 0:
                    System.out.println("See you again");
                    ReadAndWriteFile.writeToFileProduct(categoryManager.getListAllProduct().getListProduct());
                    ReadAndWriteFile.writeToFileAccount(accountManager.getListAccount(), ReadAndWriteFile.PATH_ACCOUNT_USER);
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != 0);
    }

    public void mainMenu() {
        Scanner sc = new Scanner(System.in);
        int choose = -1;
        do {
            Menu.showMainUserMenu();
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    // Hien thi sp theo danh mục
                    categoryManager.displayProduct();
                    break;
                case 2:
                    // tim kiem sp theo ten
                    categoryManager.getListAllProduct().findByName();
                    break;
                case 3:
                    // hien thi sp theo khoang gia
                    categoryManager.getListAllProduct().filter();
                    break;
                case 4:
                    // them sp vao gio hang
                    addToCart();
                    break;
                case 5:
                    // xem gio hang
                    displayCart();
                    break;
                case 0:
                    ReadAndWriteFile.writeToFileProduct(categoryManager.getListAllProduct().getListProduct());
                    ReadAndWriteFile.writeToFileAccount(accountManager.getListAccount(), ReadAndWriteFile.PATH_ACCOUNT_USER); // ? ko hiểu sao lại lưu dc this user từ đây
                    System.out.println("See you again");
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != 0);
    }

    public void addToCart() {
        Product product;
        do {
            product = categoryManager.getListAllProduct().findById();
        } while(product == null);

        if(product.getAmount() <= 0) {
            System.out.println("Sản phẩm đã hết hàng. Quý khách hãy lựa chọn sản phẩm khác.");
            return;
        }


        Scanner sc = new Scanner(System.in);
        int amount = 0;
        do {
            System.out.println("Nhập số lượng: ");
            amount = sc.nextInt();
            if(amount <= 0) System.out.println("Số lượng nhập ko hợp lệ. Hãy nhập lại");
            if (amount > product.getAmount()) System.out.println("Hiện trong kho chỉ còn " + product.getAmount() + " sản phẩm. Quý khách vui lòng nhập lại số lượng");
        } while (amount <= 0 || amount > product.getAmount());

        thisUser.addToCart(product, amount);

//        for(Account a:accountManager.getListAccount()) {
//            if(a.getUserName().equals(thisUser.getUserName())) {
//                a.addToCart(buyProduct); // mua 2 lần
//            }
//        }
    }

    public void displayCart() {
        double totalPrice = 0;
        for(String s : thisUser.getCart()) {
            String[] arr = s.split("_");
            String productId = arr[0];
            int quantity = Integer.parseInt(arr[1]);
            for(Product p:categoryManager.getListAllProduct().getListProduct()) {
                if(p.getId().equals(productId)) {
                    System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice() + " Số lượng: " + quantity);
                    totalPrice += p.getPrice()*quantity;
                }
            }
        }
        System.out.println("------------>Tổng thanh toán: " + totalPrice);
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
