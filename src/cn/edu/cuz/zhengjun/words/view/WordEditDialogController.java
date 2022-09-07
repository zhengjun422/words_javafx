package cn.edu.cuz.zhengjun.words.view;

import cn.edu.cuz.zhengjun.words.model.Word;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WordEditDialogController {

//    @FXML
//    private TextField idField;
    @FXML
    private TextField lemmaField;
    @FXML
    private TextField sensesField;
    @FXML
    private TextField phoneticField;

    private Word word;
    private Stage dialogStage;
    private boolean okClicked = false;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // isEdit: true, edit a book; false, creat a book
    public void setWord(Word word, boolean isEdit) {
        this.word = word;

        if(isEdit) {
//            idField.setText(word.getId() + "");
            lemmaField.setText(word.getLemma());
            sensesField.setText(word.getSenses());
            phoneticField.setText(word.getPhonetic());
        }
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
//            word.setId(Integer.parseInt(idField.getText()));
            word.setLemma(lemmaField.getText());
            word.setSenses(sensesField.getText());
            word.setPhonetic(phoneticField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

//        if (idField.getText() == null || idField.getText().length() == 0) {
//            errorMessage += "id不能为空!\n";
//        } else {
//            int id = 0;
//            try {
//                id = Integer.parseInt(idField.getText());
//                if(id < 0){
//                    errorMessage += "id不能小于0\n";
//                }
//            } catch (NumberFormatException e){
//                errorMessage += "id必须为整数!\n";
//            }
//        }

        if (lemmaField.getText() == null || lemmaField.getText().length() == 0) {
            errorMessage += "单词不能为空!\n";
        }

        if (sensesField.getText() == null || sensesField.getText().length() == 0) {
            errorMessage += "中文释义不能为空!\n";
        }

        if (phoneticField.getText() == null || phoneticField.getText().length() == 0) {
            errorMessage += "音标不能为空!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("数据非法");
            alert.setHeaderText("请检查数据的合法性");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }
}