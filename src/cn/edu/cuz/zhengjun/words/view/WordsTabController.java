package cn.edu.cuz.zhengjun.words.view;

import cn.edu.cuz.zhengjun.words.db.WordsDao;
import cn.edu.cuz.zhengjun.words.model.Word;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

        initTableViewData();
    }

    void initTableViewData(){
        wordsTableView.setItems(WordsDao.getAllWords(""));
    }

    @FXML
    private void onSearchButtonClicked(){
        wordsTableView.setItems(WordsDao.getAllWords(wordsSearchTf.getText()));
    }
}
