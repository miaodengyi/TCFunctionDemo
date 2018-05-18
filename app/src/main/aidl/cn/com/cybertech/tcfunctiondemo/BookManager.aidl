// BookManager.aidl
package cn.com.cybertech.tcfunctiondemo;
import cn.com.cybertech.tcfunctiondemo.Book;

// Declare any non-default types here with import statements

interface BookManager {

    List<Book> getBooks();
    void addBook(inout Book book);
}

