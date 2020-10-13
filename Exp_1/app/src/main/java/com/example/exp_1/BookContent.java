package com.example.exp_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookContent {
    public static class Book{
        public Integer id;
        public String title;
        public String desc;

        public Book(Integer id, String title, String desc){
            this.id = id;
            this.title = title;
            this.desc = desc;
        }

        @Override
        public String toString(){
            return title;
        }
    }

    public static List<Book> ITEMS = new ArrayList<Book>();
    public static Map<Integer, Book> ITEMS_MAP = new HashMap<Integer, Book>();
    static {
        addItem(new Book(1, "烤鸭 从入门到精通", "适合初学者的烤鸭入门书"));
        addItem(new Book(2, "高性能高并发叉烧实战", "针对高性能方面的叉烧制作介绍书，能够优化叉烧制作时间，并且一次性做出多条叉烧"));
        addItem(new Book(3, "烤制模式 可复用面向对象烧腊基础", "介绍了包括工厂模式在内的24种常用的烤制模式"));
    }

    private static void addItem(Book book){
        ITEMS.add(book);
        ITEMS_MAP.put(book.id, book);
    }
}
