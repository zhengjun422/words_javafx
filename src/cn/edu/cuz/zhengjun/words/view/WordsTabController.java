package cn.edu.cuz.zhengjun.words.view;

import cn.edu.cuz.zhengjun.words.Main;
import cn.edu.cuz.zhengjun.words.db.WordsDao;
import cn.edu.cuz.zhengjun.words.model.Word;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.Optional;

public class WordsTabController {
    @FXML
    private TableView<Word> wordsTableView;
    @FXML
    private TableColumn<Word, Integer> idColumn;
    @FXML
    private TableColumn<Word, String> lemmaColumn;
    @FXML
    private TableColumn<Word, String> sensesColumn;
    @FXML
    private TableColumn<Word, String> phoneticColumn;
    @FXML
    private TextField wordsSearchTf;

    @FXML
    private void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Word, Integer>("id"));
        lemmaColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("lemma"));
        sensesColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("senses"));
        phoneticColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("phonetic"));

        wordsTableView.setRowFactory(new Callback<TableView<Word>, TableRow<Word>>() {
            @Override
            public TableRow<Word> call(TableView<Word> param) {
                TableRow<Word> row = new TableRow<>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(event.getClickCount() == 2 && !row.isEmpty()){
                            Word word = row.getItem();
                            showEditWordDialog(word);
                        }
                    }
                });
                return row;
            }
        });

        initTableViewData();
    }

    void initTableViewData(){
        wordsTableView.setItems(WordsDao.getAllWords(wordsSearchTf.getText()));
    }

    @FXML
    private void onSearchButtonClicked(){
        initTableViewData();
    }

    @FXML
    private void onNewWordClicked(){
        Word word= new Word();
        boolean okClicked = Main.getInstance().showWordEditDialog(word, false);
        if (okClicked) {
            System.out.println(word);
            if (WordsDao.insertWrod(word)){
                initTableViewData();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("?????????????????????");
                alert.setHeaderText(null);
                alert.setContentText("??????????????????,id???????????????????????????!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void onEditWordClicked(){
        Word word = wordsTableView.getSelectionModel().getSelectedItem();
        showEditWordDialog(word);
    }

    void showEditWordDialog(Word word){
        if (word != null) {
            boolean okClicked = Main.getInstance().showWordEditDialog(word, true);
            if (okClicked) {
                System.out.println(word);
                if (WordsDao.updateBook(word)) {
                    initTableViewData();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("?????????????????????");
                    alert.setHeaderText(null);
                    alert.setContentText("??????????????????,id???????????????????????????!");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("????????????");
            alert.setHeaderText("??????????????????");
            alert.setContentText("???????????????????????????!");
            alert.showAndWait();
        }
    }

    @FXML
    private void onDeleteWordClicked(){
        int selectedIndex = wordsTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("???????????????????");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if (WordsDao.deleteWord(wordsTableView.getItems().get(selectedIndex).getId())) {
                    wordsTableView.getItems().remove(selectedIndex);
                } else {
                    Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                    warningAlert.setTitle("?????????????????????");
                    warningAlert.setHeaderText(null);
                    warningAlert.setContentText("????????????!");
                    warningAlert.showAndWait();
                }
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("????????????");
            alert.setHeaderText("??????????????????");
            alert.setContentText("???????????????????????????!");
            alert.showAndWait();
        }
    }
}
