package com.library.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre = new Genre();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author = new Author();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher = new Publisher();

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Transient
    private byte[] photo;

    @Column(name = "page_count")
    private int pageCount;

    @Column(name = "isbn")
    @Transient
    private int isbn;

    @Column(name = "publish_year")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishYear;

    @Column(name = "language")
    private String language;

    @Column(name = "price")
    @Transient
    private Double price;

    @Column(name = "weight")
    @Transient
    private Double weight;

    @Column(name = "photo")
    private String imageFile;

    @Column(name = "bookFile")
    private String bookFilePath;

    public Book() {
    }

    public Book(Genre genre, Author author, Publisher publisher) {
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
    }

    public Book(Genre genre, Author author, Publisher publisher, String title, String description, byte[] photo, int pageCount, int isbn, Date publishYear, Double price, Double weight, String language) {
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.price = price;
        this.weight = weight;
        this.language = language;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }


    public String getBookFilePath() {
        return bookFilePath;
    }

    public void setBookFilePath(String bookFilePath) {
        this.bookFilePath = bookFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (photo != book.photo) return false;
        if (pageCount != book.pageCount) return false;
        if (isbn != book.isbn) return false;
        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (publishYear != null ? !publishYear.equals(book.publishYear) : book.publishYear != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        return !(weight != null ? !weight.equals(book.weight) : book.weight != null);

    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", genre=" + genre +
                ", author=" + author +
                ", publisher=" + publisher +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", pageCount=" + pageCount +
                ", isbn=" + isbn +
                ", publishYear=" + publishYear +
                ", language='" + language + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", imageFile='" + imageFile + '\'' +
                '}';
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public Date getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Date publishYear) {
        this.publishYear = publishYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
