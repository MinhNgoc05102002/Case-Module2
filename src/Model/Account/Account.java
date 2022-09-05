package Model.Account;

import Model.Product;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    public static final String USERNAME_REGEX = "^[_a-z0-9]{8,}$"; // chữ thường hoặc số, ít nhất 8 kí tự
    public static final String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})"; // có chữ và số, ít nhất 8 kí tự
    public static final String PHONE_REGEX = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
    private String userName;
    private String passWord;
    private String phoneNumber;
    private String address;

    private ArrayList<String> cart = new ArrayList<>();

    public Account() {
    }

    public Account(String userName, String passWord, String phoneNumber, String address) {
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validateUserName(String userName) {
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    public static boolean validatePhone(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    public void addToCart(Product product, int amount) {
        int check = 0;
        for(int i=0; i<cart.size(); i++) {
            String[] arr = cart.get(i).split("_");
            if(arr[0].equals(product.getId())) {
                int quantity = Integer.parseInt(arr[1]);
                quantity += amount;
                cart.set(i, arr[0] + "_" +quantity) ;
                check = 1;
                break;
            }
        }
        if(check == 0) cart.add(product.getId() + "_" + amount);
    }

    @Override
    public String toString() {
        String accountString = "";
        accountString +=
                        userName + ',' +
                        passWord + ',' +
                        phoneNumber + ',' +
                        address;
        for(String s : cart) {
            accountString += "," + s;
        }
        return accountString;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getCart() {
        return cart;
    }

    public void setCart(ArrayList<String> cart) {
        this.cart = cart;
    }

}
