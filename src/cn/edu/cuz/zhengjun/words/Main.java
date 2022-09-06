package cn.edu.cuz.zhengjun.words;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    Stage loginStage;
    Stage registerStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/login.fxml"));
        Parent root = loader.load();
        loginStage = primaryStage;
        loginStage.setTitle("登录");
        loginStage.setScene(new Scene(root, 300, 275));
        loginStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void loginToRegister() throws Exception {
        if (registerStage == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/register.fxml"));
            Parent root = loader.load();
            registerStage = new Stage();
            registerStage.setTitle("注册");
            registerStage.setScene(new Scene(root, 300, 275));
        }

        registerStage.show();
        loginStage.close();
    }

    public void registerToLogin() throws Exception {
        if (loginStage == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/login.fxml"));
            Parent root = loader.load();
            loginStage = new Stage();
            loginStage.setTitle("登录");
            loginStage.setScene(new Scene(root, 300, 275));
        }

        loginStage.show();
        registerStage.close();
    }
}
