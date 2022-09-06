package cn.edu.cuz.zhengjun.words.view;

import cn.edu.cuz.zhengjun.words.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML
    private TextField registerNameTf;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void initialize(){
    }

    @FXML
    private void onRegiterButtonClicked() throws Exception{

    }

    @FXML void onLoginButtonClicked() throws Exception{
        registerToLogin();
    }

    void registerToLogin() throws Exception{
        registerNameTf.setText("");
        passwordField.setText("");
        Main.getInstance().registerToLogin();
    }
}
