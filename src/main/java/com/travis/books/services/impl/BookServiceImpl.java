package com.travis.books.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travis.books.domain.Book;
import com.travis.books.domain.BookEntity;
import com.travis.books.repositories.BookRepository;
import com.travis.books.services.BookService;

@Service
public class BookServiceImpl implements BookService 
{

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) 
    {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(final Book book) 
    {

        final BookEntity bookEntity = toBookEntity(book);

        final BookEntity savedBookEntity = bookRepository.save(bookEntity);

        return bookEntityToBook(savedBookEntity);

    }

    private BookEntity toBookEntity(Book book) 
    {
      return BookEntity.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();


    }

     private Book bookEntityToBook(BookEntity bookEntity) 
    {
      return Book.builder()
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .build();


    }
    
}
