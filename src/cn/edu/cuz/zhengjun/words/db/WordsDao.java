package cn.edu.cuz.zhengjun.words.db;

import cn.edu.cuz.zhengjun.words.model.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class WordsDao {

    public static ObservableList<Word> getAllWords(String text){
        Connection dbConn = OpenDB.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        ObservableList<Word> words = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT * from words where lemma like '%"
                    + text + "%' order by lemma";
            stmt = dbConn.createStatement();
            rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                words.add(new Word(rs.getInt("id"),
                        rs.getString("lemma"),
                        rs.getString("senses"),
                        rs.getString("phonetic")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (dbConn != null)
                try {
                    dbConn.close();
                } catch (Exception e) {
                }
        }

        return words;
    }

//    public static ObservableList<Word> getWordssByUserID(int user_id){
//        Connection dbConn = OpenDB.getConnection();
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        ObservableList<Book> books = FXCollections.observableArrayList();
//
//        try {
//            String SQL = "SELECT books.id, books.users_id, books.name, books.url, books.create_time, books.location_id, books.pages," +
//                    " book_location.location" +
//                    " from books inner join book_location " +
//                    " on books.location_id=book_location.id  where books.users_id = '" + user_id + "'";
//            stmt = dbConn.createStatement();
//            rs = stmt.executeQuery(SQL);
//
//            // Iterate through the data in the result set and display it.
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                int readPagination = ReadingRecordDao.getReadPaginationByBookID(id);
//
//                books.add(new Book(rs.getInt("id"),
//                        rs.getInt("users_id"),
//                        rs.getString("name"),
//                        rs.getString("url"),
//                        DateUtils.getDateTime(rs.getTimestamp("create_time")),
//                        rs.getInt("location_id"),
//                        rs.getString("location"),
//                        rs.getInt("pages"),
//                        readPagination));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            if (rs != null)
//                try {
//                    rs.close();
//                } catch (Exception e) {
//                }
//            if (stmt != null)
//                try {
//                    stmt.close();
//                } catch (Exception e) {
//                }
//            if (dbConn != null)
//                try {
//                    dbConn.close();
//                } catch (Exception e) {
//                }
//        }
//
//        return books;
//    }

//    public static ObservableList<Book> searchBooksByUserIDAndBookName(int user_id, String searchText){
//        Connection dbConn = OpenDB.getConnection();
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        ObservableList<Book> books = FXCollections.observableArrayList();
//
//        try {
//            String SQL = "SELECT books.id, books.users_id, books.name, books.url, books.create_time, books.location_id, books.pages," +
//                    " book_location.location" +
//                    " from books inner join book_location" +
//                    " on books.location_id=book_location.id  where books.users_id = '" + user_id
//                    + "' and name like '%" + searchText + "%'";    // name like '%java%'
//            stmt = dbConn.createStatement();
//            rs = stmt.executeQuery(SQL);
//
//            // Iterate through the data in the result set and display it.
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                int readPagination = ReadingRecordDao.getReadPaginationByBookID(id);
//
//                books.add(new Book(rs.getInt("id"),
//                        rs.getInt("users_id"),
//                        rs.getString("name"),
//                        rs.getString("url"),
//                        rs.getString("create_time"),
//                        rs.getInt("location_id"),
//                        rs.getString("location"),
//                        rs.getInt("pages"),
//                        readPagination));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            if (rs != null)
//                try {
//                    rs.close();
//                } catch (Exception e) {
//                }
//            if (stmt != null)
//                try {
//                    stmt.close();
//                } catch (Exception e) {
//                }
//            if (dbConn != null)
//                try {
//                    dbConn.close();
//                } catch (Exception e) {
//                }
//        }
//
//        return books;
//    }

    public static boolean insertWrod(Word word){
        Connection dbConn = OpenDB.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        boolean result = false;

        try {
            String SQL = "insert into words(lemma, senses, phonetic) values('"
                    + word.getLemma() + "','"
                    + word.getSenses() + "','"
                    + word.getPhonetic()+ "')";
            stmt = dbConn.createStatement();
            int count = stmt.executeUpdate(SQL);

            if (count > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (dbConn != null)
                try {
                    dbConn.close();
                } catch (Exception e) {
                }
        }

        return result;
    }

    public static boolean updateBook(Word word){
        Connection dbConn = OpenDB.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        boolean result = false;

        try {
            String sql = "update words set lemma='" + word.getLemma()
                    + "',senses='" + word.getSenses()
                    + "',phonetic='"+ word.getPhonetic()
                    +"' where id='" + word.getId() + "'";
            stmt = dbConn.createStatement();
            stmt.executeUpdate(sql);
            int count = stmt.executeUpdate(sql);
            if (count > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (dbConn != null)
                try {
                    dbConn.close();
                } catch (Exception e) {
                }
        }

        return result;
    }

    public static boolean deleteWord(int id){
        Connection dbConn = OpenDB.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        boolean result = false;

        try {
            String sql = "delete from words where id='"+ id + "'";

            stmt = dbConn.createStatement();
            int count = stmt.executeUpdate(sql);

            if (count > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (dbConn != null)
                try {
                    dbConn.close();
                } catch (Exception e) {
                }
        }

        return result;
    }
}
