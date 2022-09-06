package cn.edu.cuz.zhengjun.words.view;

import cn.edu.cuz.zhengjun.words.Main;
import cn.edu.cuz.zhengjun.words.db.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        if(registerNameTf.getText()==null || registerNameTf.getText().length()==0 ||
                passwordField.getText()==null || passwordField.getText().length()==0      ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("注册失败");
            alert.setHeaderText("登录名和密码不能为空或者长度为0");
            alert.setContentText("请重新输入登录名和密码");
            alert.showAndWait();
        } else {
            if(UserDao.register(registerNameTf.getText(), passwordField.getText())) {
                registerToLogin();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("注册失败");
                alert.setHeaderText("登录名已经存在，请更换登录名");
                alert.setContentText("请重新输入登录名和密码");
                alert.showAndWait();
            }
        }
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
