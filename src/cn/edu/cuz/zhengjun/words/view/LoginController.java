package cn.edu.cuz.zhengjun.words.view;

import cn.edu.cuz.zhengjun.words.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField loginNameTf;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void initialize(){

    }

    @FXML
    private void onLoginButtonClicked() throws Exception{

    }

    @FXML
    void onRegisterButtonClicked() throws Exception{
        loginNameTf.setText("");
        passwordField.setText("");
        Main.getInstance().loginToRegister();
    }
}
