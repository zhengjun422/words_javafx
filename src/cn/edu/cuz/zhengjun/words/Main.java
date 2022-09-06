package cn.edu.cuz.zhengjun.words;

import cn.edu.cuz.zhengjun.words.db.UserDao;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    Stage loginStage;
    Stage registerStage;
    Stage mainFrameStage;
    Stage adminFrameStage;

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

    public void loginToMainFrame() throws Exception {
        if (mainFrameStage == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/mainFrame.fxml"));
            Parent root = loader.load();
            mainFrameStage = new Stage();
            mainFrameStage.setTitle("学单词");
            mainFrameStage.setScene(new Scene(root, 1200, 800));
            initMenu(mainFrameStage.getScene());
        }

        mainFrameStage.show();
        loginStage.close();
    }

    public void loginToAdminFrame() throws Exception {
        if (adminFrameStage == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/adminFrame.fxml"));
            Parent root = loader.load();
            adminFrameStage = new Stage();
            adminFrameStage.setTitle("后台管理系统");
            adminFrameStage.setScene(new Scene(root, 1200, 800));
            initMenu(adminFrameStage.getScene());
        }

        adminFrameStage.show();
        loginStage.close();
    }

    public void mainFrameToLogin() throws Exception {
        if (loginStage == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/login.fxml"));
            Parent root = loader.load();
            loginStage = new Stage();
            loginStage.setTitle("登录");
            loginStage.setScene(new Scene(root, 300, 275));
        }

        loginStage.show();
        if(UserDao.user.getType() == 0) {
            mainFrameStage.close();
            mainFrameStage = null;
        }else {
            adminFrameStage.close();
            adminFrameStage = null;
        }
    }

    void initMenu(Scene scene) {
        MenuBar menuBar = new MenuBar();

        Menu menuSystem = new Menu("系统");

        MenuItem menuLogout = new MenuItem("退出账号");
        menuLogout.setOnAction((ActionEvent t) -> {
            try {
                mainFrameToLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        menuSystem.getItems().add(menuLogout);

        MenuItem menuExit = new MenuItem("关闭");
        menuExit.setOnAction(actionEvent -> Platform.exit());
        menuSystem.getItems().add(menuExit);

        menuBar.getMenus().addAll(menuSystem);

        ((BorderPane) scene.getRoot()).setTop(menuBar);
    }
}
