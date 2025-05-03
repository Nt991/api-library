package com.api.library.conrollers;


import com.api.library.entities.Book;
import com.api.library.services.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    List<Book> findAllBooks() {
       return bookService.getBooks();
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<Book> getBook(@PathVariable("book-id") long bookId) {
        Book book = bookService.getBook(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/booksPrices")
    public List<Book> getBookByPrices(@RequestParam double priceStart,@RequestParam double priceEnd ) {
        List<Book> books = bookService.getBooksByPrices(priceStart,priceEnd);
        return books;
    }

    @PatchMapping("/{book-id}")
    public ResponseEntity<Book> updateBorrowingStatus(@PathVariable("book-id") long bookId){
          Book updatedBook= bookService.updateBorrowingStatus(bookId);
          return new ResponseEntity<>(updatedBook,HttpStatus.OK);
    }

    @DeleteMapping("/{book-id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("book-id") long bookId) {
        try {
            bookService.deleteBook(bookId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            // Return a 404 Not Found if the book doesn't exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Catch other exceptions and return a 500 Internal Server Error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
