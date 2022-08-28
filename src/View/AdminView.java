package View;

import Controller.HandleAdmin;
import Model.Menu.Menu;
import Model.ProductManager;

import java.util.Scanner;

public class AdminView {
    public static void main(String[] args) {
        HandleAdmin handleAdmin = new HandleAdmin();
        handleAdmin.startMenu();
    }
}