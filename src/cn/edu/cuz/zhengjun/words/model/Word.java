package cn.edu.cuz.zhengjun.words.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Word {
    private IntegerProperty id;
    private StringProperty lemma;
    private StringProperty senses;
    private StringProperty phonetic;


    public Word(){
        this(0, null, null, null);
    }

    public Word(int id, String lemma, String senses, String phonetic) {
        this.id = new SimpleIntegerProperty(id);
        this.lemma = new SimpleStringProperty(lemma);
        this.senses = new SimpleStringProperty(senses);
        this.phonetic = new SimpleStringProperty(phonetic);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getLemma() {
        return lemma.get();
    }

    public StringProperty lemmaProperty() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma.set(lemma);
    }

    public String getSenses() {
        return senses.get();
    }

    public StringProperty sensesProperty() {
        return senses;
    }

    public void setSenses(String senses) {
        this.senses.set(senses);
    }

    public String getPhonetic() {
        return phonetic.get();
    }

    public StringProperty phoneticProperty() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic.set(phonetic);
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", lemma=" + lemma +
                ", senses=" + senses +
                ", phonetic=" + phonetic +
                '}';
    }
}
