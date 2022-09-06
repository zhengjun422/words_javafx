package cn.edu.cuz.zhengjun.words.view;

import cn.edu.cuz.zhengjun.words.Main;
import cn.edu.cuz.zhengjun.words.db.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        if(UserDao.login(loginNameTf.getText(),passwordField.getText())) {
            loginNameTf.setText("");
            passwordField.setText("");
            if(UserDao.user.getType() == 0) {
                Main.getInstance().loginToMainFrame();
            } else {
                Main.getInstance().loginToAdminFrame();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("登录失败");
            alert.setHeaderText("登录名或密码错误");
            alert.setContentText("请重新输入登录名和密码");
            alert.showAndWait();
        }
    }

    @FXML
    void onRegisterButtonClicked() throws Exception{
        loginNameTf.setText("");
        passwordField.setText("");
        Main.getInstance().loginToRegister();
    }
}
