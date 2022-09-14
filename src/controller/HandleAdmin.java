package controller;

import model.account.Account;
import storage.ReadAndWriteFile;
import model.menu.Menu;

public class HandleAdmin {
    public AccountManager accountManager = AccountManager.getInstance();
    public CategoryManager categoryManager = CategoryManager.getInstance();
    private Account thisUser = new Account();

    public final int DEFAULT = -1;
    public final int EXIT = 0;
    public final int REGISTER = 1;
    public final int LOGIN = 2;
    public final int CATEGORY_MANAGER = 1;
    public final int PRODUCT_MANAGER = 2;
    public final int DISPLAY_PRODUCT = 1;
    public final int FIND_PRODUCT_BY_NAME = 2;
    public final int ADD_PRODUCT = 3;
    public final int UPDATE_PRODUCT = 4;
    public final int DELETE_PRODUCT = 5;
    public final int FILTER_PRODUCT = 6;
    public final int DISPLAY_CATEGORY = 1;
    public final int ADD_CATEGORY = 2;
    public final int DELETE_CATEGORY = 3;
    public final int UPDATE_CATEGORY = 4;

    public HandleAdmin() {   }

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
                    ReadAndWriteFile.writeToFileAccount(accountManager.getListAccount(), ReadAndWriteFile.PATH_ACCOUNT_ADMIN);
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != EXIT);
    }

    public void mainMenu() {
        int choose = DEFAULT;
        do {
            Menu.showMainAdminMenu();
            choose = Menu.inputChoose();
            switch (choose) {
                case CATEGORY_MANAGER:
                    categoryManagerMenu();
                    break;
                case PRODUCT_MANAGER:
                    productManagerMenu();
                    break;
                case EXIT:
                    System.out.println("Goodbye " + thisUser.getUserName() + ". See you again");
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != EXIT);
    }

    public void productManagerMenu() {
        int choose = DEFAULT;
        do {
            Menu.showProductManagerMenu();
            choose = Menu.inputChoose();
            switch (choose) {
                case DISPLAY_PRODUCT:
                    categoryManager.displayProduct();
                    break;
                case FIND_PRODUCT_BY_NAME:
                    categoryManager.getListAllProduct().findByName();
                    break;
                case ADD_PRODUCT:
                    categoryManager.getListAllProduct().addNewProduct();
                    break;
                case UPDATE_PRODUCT:
                    updateProduct();
                    break;
                case DELETE_PRODUCT:
                    categoryManager.delProductForever();
                    break;
                case FILTER_PRODUCT:
                    categoryManager.getListAllProduct().filter();
                    break;
                case EXIT:
                    ReadAndWriteFile.writeToFileCategory(categoryManager.getListCategory());
                    ReadAndWriteFile.writeToFileProduct(categoryManager.getListAllProduct().getListProduct());
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != EXIT);
    }

    public void updateProduct() {
        int choose = DEFAULT;
        do {
            Menu.updateProductMenu();
            choose = Menu.inputChoose();
            switch (choose) {
                case 1:
                    categoryManager.changeProductName();
                    break;
                case 2:
                    categoryManager.changeProductDes();
                    break;
                case 3:
                    categoryManager.changeProductAmount();
                    break;
                case 4:
                    categoryManager.changeProductPrice();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != EXIT);
    }
    public void categoryManagerMenu() {
        int choose = DEFAULT;
        do {
            Menu.showCategoryManagerMenu();
            choose = Menu.inputChoose();
            switch (choose) {
                case DISPLAY_CATEGORY:
                    categoryManager.display();
                    break;
                case ADD_CATEGORY:
                    categoryManager.add();
                    break;
                case DELETE_CATEGORY:
                    categoryManager.del();
                    break;
                case UPDATE_CATEGORY:
                    updateCategory();
                    break;
                case EXIT:
                    ReadAndWriteFile.writeToFileCategory(categoryManager.getListCategory());
                    ReadAndWriteFile.writeToFileProduct(categoryManager.getListAllProduct().getListProduct());
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != EXIT);
    }

    public void updateCategory() {
        int choose = DEFAULT;
        do {
            Menu.updateCategoryMenu();
            choose = Menu.inputChoose();
            switch (choose) {
                case 1:
                    categoryManager.changeCategoryName();
                    break;
                case 2:
                    categoryManager.changeCategoryDes();
                    break;
                case 3:
                    categoryManager.delProductInCategory();
                    break;
                case 4:
                    categoryManager.addProductToCategory();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != EXIT);
    }

}
