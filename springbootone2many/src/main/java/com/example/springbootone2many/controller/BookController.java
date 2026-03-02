package com.example.springbootone2many.controller;

import com.example.springbootone2many.entity.Book;
import com.example.springbootone2many.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping()
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book saveBook = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveBook);
    }

    @GetMapping()
    public List<Book> getAllBook(){
        return bookService.getAllBook();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        Book book = bookService.getBookById(id);
//        book.getChapter().remove(1);
//        bookService.saveBook(book);
        if (book != null){
            return ResponseEntity.ok(book);
        }
        else {
            return ResponseEntity.notFound().build();
        }
//        return bookService.getBookById(id)
//                .map(ResponseEntity::ok)  // If found → return Book with 200 OK
//                .orElse(ResponseEntity.notFound().build()); // If not found → return 404
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book) {
        Book updateBook = bookService.updateBook(id, book);
        if (updateBook != null){
            return ResponseEntity.ok(updateBook);
        }
        else {
            return ResponseEntity.notFound().build();
        }

//        return bookService.updateBook(id, book)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable long id){
        boolean isDeleted = bookService.deleteBook(id);
        if (isDeleted){
            return ResponseEntity.ok("Book deleted successfully with id : " +id);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("book is not found with id : " +id);
        }
    }
}
