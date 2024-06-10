package com.ohgiraffers.mapping.section02.embeded;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_book")
public class Book {

    @Id
    @Column(name = "book_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookNo;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publisher_date")
    private LocalDate publishedDate;

//    @Column(name = "regular_price")
//    private int reqularPrice;
//
//    @Column(name = "discount_rate")
//    private double discountRate;
//
//    public Book() { }
//
//    public Book(String bookTitle, String author, String publisher, LocalDate publishedDate, int reqularPrice, double discountRate) {
//        this.bookTitle = bookTitle;
//        this.author = author;
//        this.publisher = publisher;
//        this.publishedDate = publishedDate;
//        this.reqularPrice = reqularPrice;
//        this.discountRate = discountRate;
//    }

    // Embeded 로 Price 클래스 가져오는데, 상속과 비슷한 개념~!!
    @Embedded
    private Price price;

    protected Book(){}

    public Book(String bookTitle, String author, String publisher, LocalDate publishedDate, Price price) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.price = price;
    }

}
