package Model.Menu;

public class Menu {
    public static void showStartMenu() {
        System.out.println("---------- START MENU ----------\n" +
                "1.ĐĂNG KÍ\n" +
                "2.ĐĂNG NHẬP\n" +
                "0. Exit\n" +
                "Mời nhập lựa chọn của bạn !");
    }

    // MENU USER
    public static void showMainUserMenu() {
        System.out.println("------ MAIN MENU USER ------\n" +
                "1.HIỂN THỊ TOÀN BỘ SẢN PHẨM THEO DANH MỤC\n" +
                //"1.XEM DANH MỤC SẢN PHẨM\n" +
                "2.TÌM KIẾM SẢN PHẨM THEO TÊN\n" +
                "3.HIỂN THỊ SẢN PHẨM THEO KHOẢNG GIÁ\n" +
                "4.THÊM SP VÀO GIỎ HÀNG\n" +
                "5.XEM GIỎ HÀNG\n" +
                "0.ĐĂNG XUẤT");
    }

    // MENU ADMIN
    public static void showMainAdminMenu() {
        System.out.println("---------- MAIN MENU ADMIN ----------\n" +
                "1.QUẢN LÝ DANH MỤC SẢN PHẨM\n" +
                "2.QUẢN LÝ SẢN PHẨM\n" +
                "0.ĐĂNG XUẤT\n" +
                "Mời nhập lựa chọn của bạn !");
    }

    public static void showCategoryManagerMenu() {
        System.out.println("---------- CATEGORY MANAGER MENU ----------\n" +
                "1.XEM CÁC DANH MỤC SẢN PHẨM \n" +
                "2.THÊM DANH MỤC SẢN PHẨM\n" +
                "3.XÓA DANH MỤC SẢN PHẨM\n" +
                "4.SỬA DANH MỤC SẢN PHẨM\n" +
                "0.Exit\n" +
                "Mời nhập lựa chọn của bạn !");
    }

    public static void showProductManagerMenu() {
        System.out.println("---------- PRODUCT MANAGER MENU ----------\n" +
                "1.HIỂN THỊ SẢN PHẨM THEO DANH MỤC \n" +
                "2.TÌM KIẾM SẢN PHẨM (THEO TÊN)\n" +
                "3.THÊM SẢN PHẨM\n" +
                "4.SỬA SẢN PHẨM\n" +
                "5. XÓA SẢN PHẨM\n" +
                "6. LỌC SP THEO KHOẢNG GIÁ\n" +
                "0. EXIT\n" +
                "Mời nhập lựa chọn của bạn !");
    }


    public static void updateCategoryMenu() {
        System.out.println("---------- UPDATE CATEGORY MENU ----------\n" +
                "1.SỬA TÊN DANH MỤC SẢN PHẨM \n" +
                "2.SỬA MÔ TẢ DANH MỤC SP\n" +
                "3.XÓA SP KHỎI DANH MỤC SP\n" +
                "4.THÊM SP VÀO DANH MỤC SP\n" +
                "0. EXIT\n" +
                "Mời nhập lựa chọn của bạn !");
    }

    public static void updateProductMenu() {
        System.out.println("---------- UPDATE CATEGORY MENU ----------\n" +
                "1.SỬA TÊN SẢN PHẨM \n" +
                "2.SỬA MÔ TẢ SP\n" +
                "3.SỬA SỐ LƯỢNG SP\n" +
                "4.SỬA GIÁ SP \n" +
//                "4.THÊM SP VÀO DANH MỤC SP\n" +
                "0. EXIT\n" +
                "Mời nhập lựa chọn của bạn !");
    }
}
