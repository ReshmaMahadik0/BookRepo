package com.example.springbootone2many.service;

import com.example.springbootone2many.entity.Book;
import com.example.springbootone2many.entity.Chapter;
import com.example.springbootone2many.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        // return bookRepository.save(book);
        if (book.getChapter() != null){
            for(Chapter chapter : book.getChapter()){
                chapter.setBook(book);
            }
        }
        return bookRepository.save(book);
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    // public Book getBookById(long id){
    // return bookRepository.findById(id).orElse(null);
    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(long id, Book updateBook){
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setBookName(updateBook.getBookName());

            if (updateBook.getChapter() != null) {
                book.getChapter().clear();  //remove old chapter

                for (Chapter chapter : updateBook.getChapter()) {
                    chapter.setBook(book); // set parent book
                    book.getChapter().add(chapter);  //add new chapter
                }
            }
            return bookRepository.save(book);
        }
        else{
                throw new RuntimeException("book not found with id : " + id);
            }
  }

//    public Optional<Book> updateBook(long id, Book updateBook){
//        return bookRepository.findById(id).map(existingBook ->{
//                existingBook.setBookName(updateBook.getBookName());
//                return bookRepository.save(updateBook);
//        });
//
//    }

    public boolean deleteBook(long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            bookRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
