package cn.edu.cuz.zhengjun.words.db;

import cn.edu.cuz.zhengjun.words.model.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WordsDao {

    public static ObservableList<Word> getAllWords(){
        Connection dbConn = OpenDB.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        ObservableList<Word> words = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT * from words order by id";
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

//    public static boolean insertBook(Book book){
//        Connection dbConn = OpenDB.getConnection();
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        boolean result = false;
//
//        Date date=new Date();     //获取一个Date对象
//        DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
//        String punchTime = simpleDateFormat.format(date);
//
//
//        try {
//            String SQL = "insert into books(users_id, name, url, create_time, location_id, pages) values('"
//                    + UserDao.user.getId() + "','"
//                    + book.getName() + "','"
//                    + book.getUrl() + "','"
//                    + punchTime + "','"
//                    + book.getLocation_id() + "','"
//                    + book.getPages()+ "')";
//            stmt = dbConn.createStatement();
//            int count = stmt.executeUpdate(SQL);
//
//            if (count > 0) {
//                result = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
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
//        return result;
//    }
//
//    public static boolean updateBook(Book book){
//        Connection dbConn = OpenDB.getConnection();
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        boolean result = false;
//
//        try {
//            String SQL0 = "update reading_record set pagination='" + book.getPages()
//                    +"' where book_id='" + book.getId() + "' and pagination>'" + book.getPages() +"'";
//            String SQL1 = "update books set name='" + book.getName()
//                    + "',url='" + book.getUrl()
//                    + "',location_id='"+ book.getLocation_id()
//                    + "',pages='"+ book.getPages()
//                    +"' where id='" + book.getId() + "'";
//
//            dbConn.setAutoCommit(false);
//
//            stmt = dbConn.createStatement();
//            stmt.executeUpdate(SQL0);
//            int count = stmt.executeUpdate(SQL1);
//
//            dbConn.commit();//提交JDBC事务
//            dbConn.setAutoCommit(true);
//
//            if (count > 0) {
//                result = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                dbConn.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        } finally {
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
//        return result;
//    }
//
//    public static boolean deleteBook(int id){
//        Connection dbConn = OpenDB.getConnection();
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        boolean result = false;
//
//        try {
//            String SQL0 = "delete from reading_record where book_id='"+ id + "'";
//            String SQL1 = "delete from books where id='"+ id + "'";
//
//            dbConn.setAutoCommit(false);
//
//            stmt = dbConn.createStatement();
//            stmt.executeUpdate(SQL0);
//            int count = stmt.executeUpdate(SQL1);
//
//            dbConn.commit();//提交JDBC事务
//            dbConn.setAutoCommit(true);
//
//            if (count > 0) {
//                result = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                dbConn.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        } finally {
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
//        return result;
//    }
}
