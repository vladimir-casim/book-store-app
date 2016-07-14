package com.library.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "id")
    List<Book> books = new ArrayList<Book>();

    @Column(name = "name")
    private String name;

    @Column(name = "booksCounter")
    private int booksCount;

    public Genre() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public void setBooksCount(int booksCount) {
        this.booksCount = booksCount;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", books=" + books +
                ", name='" + name + '\'' +
                ", booksCount=" + booksCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (id != genre.id) return false;
        if (booksCount != genre.booksCount) return false;
        if (books != null ? !books.equals(genre.books) : genre.books != null) return false;
        return !(name != null ? !name.equals(genre.name) : genre.name != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (books != null ? books.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + booksCount;
        return result;
    }
}
