package com.example;

import views.CliView;
import controller.Controller;
import model.db;

public class Main {
    public static void main(String[] args) {
        db database = new db();
        CliView view = new CliView();
        Controller controller = new Controller();
        controller.start();
    }
}
