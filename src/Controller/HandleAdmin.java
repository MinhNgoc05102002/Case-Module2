package Controller;

import Model.Account.Account;
import Model.Account.AccountManager;
import Model.CategoryManager;
import Model.IO.ReadAndWriteFile;
import Model.Menu.Menu;

public class HandleAdmin {
    public AccountManager accountManager = new AccountManager();
    public CategoryManager categoryManager = new CategoryManager();
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

    public HandleAdmin() {
        categoryManager.getListAllProduct().setListProduct(ReadAndWriteFile.readFileProduct());

        categoryManager.setListCategory(ReadAndWriteFile.readFileCategory(categoryManager.getListAllProduct()));

        accountManager.setListAccount(ReadAndWriteFile.readFileAccount(ReadAndWriteFile.PATH_ACCOUNT_ADMIN));
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
                    // Quan ly danh muc sp
                    categoryManagerMenu();
                    break;
                case PRODUCT_MANAGER:
                    // Quan ly sp
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
                    // Hiển thị sp theo danh mục
                    categoryManager.displayProduct();
                    break;
                case FIND_PRODUCT_BY_NAME:
                    // Tìm kiếm sp theo tên
                    categoryManager.getListAllProduct().findByName();
                    break;
                case ADD_PRODUCT:
                    // Thêm sp
                    categoryManager.getListAllProduct().addNewProduct();
                    break;
                case UPDATE_PRODUCT:
                    // Sửa sp
                    updateProduct();
                    break;
                case DELETE_PRODUCT:
                    // Xóa sp
                    categoryManager.delProductForever();
                    break;
                case FILTER_PRODUCT:
                    // Lọc sp theo khoảng giá
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
                    // Xem các danh mục sp hiện có
                    categoryManager.display();
                    break;
                case ADD_CATEGORY:
                    // Thêm danh mục sp
                    categoryManager.add();
                    break;
                case DELETE_CATEGORY:
                    // Xóa danh mục sp
                    categoryManager.del();
                    break;
                case UPDATE_CATEGORY:
                    // Sua danh muc sp
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
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, hãy nhập lại !!!");
            }
        } while (choose != EXIT);
    }

}
