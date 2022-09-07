package controller;

import model.account.Account;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
    private ArrayList<Account> listAccount;

    public AccountManager(){
        listAccount = new ArrayList<Account>();
    }

    public boolean containsAccount(String username) {
        for(Account acc:listAccount){
            if(acc.getUserName().equals(username))
                return true;
        }
        return false;
    }

    // 1. Them tai khoan (dang ki)
    public void register() {
        Scanner sc = new Scanner(System.in);
        String userName;
        do{
            do{
                System.out.println("Nhập username: ");
                userName = sc.nextLine();
                if(!Account.validateUserName(userName))
                    System.out.println("Username không hợp lệ. Username không được phép chứa kí tự đặc biệt, không chứa chữ in hoa và phải có độ dài ít nhất 8 kí tự.");
            } while(!Account.validateUserName(userName));
            if(containsAccount(userName)) System.out.println("Tên người dùng đã tồn tại, mời nhập lại !!!");
        } while(containsAccount(userName));
        String passWord;
        do {
            System.out.println("Nhập mật khẩu: ");
            passWord = sc.nextLine();
            if(!Account.validatePassword(passWord))
                System.out.println("Password không hợp lệ. Password không được phép chứa kí tự đặc biệt, phải chứa chữ và số và phải có độ dài ít nhất 8 kí tự.");
        } while(!Account.validatePassword(passWord));

        String phoneNumber;
        do {
            System.out.println("Nhập SĐT");
            phoneNumber = sc.nextLine();
            if(!Account.validatePhone(phoneNumber))
                System.out.println("SĐT không hợp lệ");
        } while(!Account.validatePhone(phoneNumber));

        System.out.println("Nhập địa chỉ");
        String address = sc.nextLine();
        listAccount.add( new Account(userName, passWord, phoneNumber, address));
    }

    // 2. Xoa tai khoan
    public void delAcc(String userName) {
        if(!containsAccount(userName)){
            System.out.println("Tài khoản ko tồn tại!!!");
            return;
        }

        for(int i=0; i<listAccount.size(); i++) {
            if(listAccount.get(i).getUserName().equals(userName)) {
                listAccount.remove(i);
            }
        }
    }

    // 3. Dang nhap
    public boolean logIn(Account thisUser) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập username: ");
        String userName = sc.nextLine();
        for (Account a:listAccount) {
            if(a.getUserName().equals(userName)) {
                System.out.println("Nhập mật khẩu: ");
                String passWord = sc.nextLine();
                if (a.getPassWord().equals(passWord)) {
                    System.out.println("Đăng nhập thành công!!!");

                    thisUser.setUserName(a.getUserName());
                    thisUser.setPassWord(a.getPassWord());
                    thisUser.setAddress(a.getAddress());
                    thisUser.setPhoneNumber(a.getPhoneNumber());
                    thisUser.setCart(a.getCart());
                    return true;
                }
                else {
                    System.out.println("Sai mật khẩu !!!");
                    return false;
                }
            }
        }
        System.out.println("Tài khoản không tồn tại");
        return false;
    }

    public void setListAccount(ArrayList<Account> listAccount) {
        this.listAccount = listAccount;
    }

    public ArrayList<Account> getListAccount() {
        return listAccount;
    }
}
