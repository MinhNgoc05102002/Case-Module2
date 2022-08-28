package Model.IO;

import Model.Account.Account;
import Model.Category;
import Model.Product;
import Model.ProductManager;

import java.io.*;
import java.util.ArrayList;

public class ReadAndWriteFile {

    public static final String PATH_ACCOUNT_USER = "src//Model//IO//AccountUser.txt";
    public static final String PATH_ACCOUNT_ADMIN = "src//Model//IO//AccountAdmin.txt";
    public static final String PATH_PRODUCT = "src//Model//IO//Product.txt";
    public static final String PATH_CATEGORY = "src//Model//IO//Category.txt";
    public static void writeToFileAccount(ArrayList<Account> listAcc, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath); // no append
            BufferedWriter bw = new BufferedWriter(fw);
            for (Account a : listAcc) {
                bw.write(a.toString());
                bw.newLine();
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeToFileProduct(ArrayList<Product> listProduct) {
        try {
            FileWriter fw = new FileWriter(PATH_PRODUCT); // no append
            BufferedWriter bw = new BufferedWriter(fw);
            for (Product p : listProduct) {
                bw.write(p.toString());
                bw.newLine();
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFileCategory(ArrayList<Category> listCate) {
        try {
            FileWriter fw = new FileWriter(PATH_CATEGORY); // no append
            BufferedWriter bw = new BufferedWriter(fw);
            for (Category c : listCate) {
                bw.write(c.toString());
                bw.newLine();
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Account> readFileAccount(String filePath) {
        ArrayList<Account> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if(line == null) break;

                String txt[] = line.split(",");
                String name = txt[0];
                String pass = txt[1];
                String phone = txt[2];
                String address = txt[3];

                ArrayList<String> cart = new ArrayList<>();
                for(int i=4; i < txt.length; i++) {
                    cart.add(txt[i]);
                }

                Account newAccount = new Account(name, pass, phone, address);
                newAccount.setCart(cart);

                list.add(newAccount);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static ArrayList<Product> readFileProduct() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(PATH_PRODUCT);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if(line == null) break;

                String[] txt = line.split(",");
                ArrayList<String> arr = new ArrayList<>();
                String id = txt[0];
                String name = txt[1];
                String description = txt[2];
                String amount = txt[3];
                String price = txt[4];
                ArrayList<String> categoryId = new ArrayList<>();
                for(int i=5; i < txt.length; i++) {
                    categoryId.add(txt[i]);
                }
                list.add(new Product(id, name, description, Integer.parseInt(amount), Double.parseDouble(price), categoryId));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static ArrayList<Category> readFileCategory(ProductManager productManager) {
        ArrayList<Category> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(PATH_CATEGORY);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if(line == null) break;

                String txt[] = line.split(",");
                String id = txt[0];
                String name = txt[1];
                String description = txt[2];
                list.add(new Category(id, name, description));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Category c : list) {
            for(Product p : productManager.getListProduct()) {
                if(p.getCategoryId().contains(c.getId())) {
                    c.getProductList().add(p);
                }
            }
        }
        return list;
    }
}
