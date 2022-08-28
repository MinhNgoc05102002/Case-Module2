package Controller;

import Model.Account.Account;
import Model.Account.AccountManager;
import Model.CategoryManager;
import Model.IO.ReadAndWriteFile;
import Model.Menu.Menu;

import java.util.Scanner;

public class HandleAdmin {
    public AccountManager accountManager = new AccountManager();
    public CategoryManager categoryManager = new CategoryManager();
    private Account thisUser = new Account();

    public HandleAdmin() {
        categoryManager.getListAllProduct().setListProduct(ReadAndWriteFile.readFileProduct());

        categoryManager.setListCategory(ReadAndWriteFile.readFileCategory(categoryManager.getListAllProduct()));

        accountManager.setListAccount(ReadAndWriteFile.readFileAccount(ReadAndWriteFile.PATH_ACCOUNT_ADMIN));
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
                    ReadAndWriteFile.writeToFileAccount(accountManager.getListAccount(), ReadAndWriteFile.PATH_ACCOUNT_ADMIN);
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
            Menu.showMainAdminMenu();
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    // Quan ly danh muc sp
                    categoryManagerMenu();
                    break;
                case 2:
                    // Quan ly sp
                    productManagerMenu();
                    break;
                case 0:
                    System.out.println("See you again");
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != 0);
    }

    public void productManagerMenu() {
        Scanner sc = new Scanner(System.in);

        int choose = -1;

        do {
            Menu.showProductManagerMenu();
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    // Hiển thị sp theo danh mục
                    categoryManager.displayProduct();
                    break;
                case 2:
                    // Tìm kiếm sp theo tên
                    categoryManager.getListAllProduct().findByName();
                    break;
                case 3:
                    // Thêm sp
                    categoryManager.getListAllProduct().addNewProduct();
                    break;
                case 4:
                    // Sửa sp
                    updateProduct();
                    break;
                case 5:
                    // Xóa sp
                    categoryManager.delProductForever();
                    break;
                case 6:
                    // Lọc sp theo khoảng giá
                    categoryManager.getListAllProduct().filter();
                    break;
                case 0:
                    ReadAndWriteFile.writeToFileCategory(categoryManager.getListCategory());
                    ReadAndWriteFile.writeToFileProduct(categoryManager.getListAllProduct().getListProduct());
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != 0);
    }

    public void updateProduct() {
        Scanner sc = new Scanner(System.in);

        int choose = -1;

        do {
            Menu.updateProductMenu();
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    // Sửa tên sp
                    categoryManager.changeProductName();
                    break;
                case 2:
                    // Sửa mô tả sp
                    categoryManager.changeProductDes();
                    break;
                case 3:
                    // sửa số lượng sp
                    categoryManager.changeProductAmount();
                    break;
                case 4:
                    // sửa giá sp
                    categoryManager.changeProductPrice();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != 0);
    }
    public void categoryManagerMenu() {
        Scanner sc = new Scanner(System.in);

        int choose = -1;

        do {
            Menu.showCategoryManagerMenu();
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    // Xem các danh mục sp hiện có
                    categoryManager.display();
                    break;
                case 2:
                    // Thêm danh mục sp
                    categoryManager.add();
                    break;
                case 3:
                    // Xóa danh mục sp
                    categoryManager.del();
                    break;
                case 4:
                    // Sua danh muc sp
                    updateCategory();
                    break;
                case 0:
                    ReadAndWriteFile.writeToFileCategory(categoryManager.getListCategory());
                    ReadAndWriteFile.writeToFileProduct(categoryManager.getListAllProduct().getListProduct());
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != 0);
    }

    public void updateCategory() {
        Scanner sc = new Scanner(System.in);

        int choose = -1;

        do {
            Menu.updateCategoryMenu();
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    // Sửa tên danh mục sp
                    categoryManager.changeCategoryName();
                    break;
                case 2:
                    // Sửa mô tả danh mục sp
                    categoryManager.changeCategoryDes();
                    break;
                case 3:
                    // Xóa sp khỏi danh mục sp
                    categoryManager.delProductInCategory();
                    break;
                case 4:
                    // Thêm sp vào danh mục sp
                    categoryManager.addProductToCategory();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != 0);
    }

}
