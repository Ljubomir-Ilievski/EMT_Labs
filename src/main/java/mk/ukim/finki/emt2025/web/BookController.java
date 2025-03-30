package mk.ukim.finki.emt2025.web;


import mk.ukim.finki.emt2025.model.Book;
import mk.ukim.finki.emt2025.model.dto.BookDto;
import mk.ukim.finki.emt2025.service.impl.BookServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//http://localhost:8080/swagger-ui/index.html#/
@RestController
@RequestMapping(value = {"/api/books"})
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    List<Book> findAll(){
       return bookService.findByIsSoftDeleted();
    }
    @GetMapping("/{id}")
    ResponseEntity<Book> findById(@PathVariable(value = "id") Long bookId){
        return bookService.findById(bookId)
                .map(book -> {return ResponseEntity.ok(book);})
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/add")
    ResponseEntity<Book> save(@RequestBody BookDto bookDto){

        return bookService.save(bookDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());


    }

    @PutMapping("/update/{id}")
    ResponseEntity<Object> update(@PathVariable(value = "id") Long bookId, @RequestBody BookDto bookDto){

            return bookService.update(bookId, bookDto)
                    .map(book -> {return ResponseEntity.ok().build();})
                    .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Object> delete(@PathVariable(value = "id") Long bookId) {

        return bookService.deleteById(bookId)
                .map(book -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/softDelete/{id}")
    ResponseEntity<Book> softDelete(@PathVariable Long id){

        return bookService.softDeleteById(id)
                .map(book -> ResponseEntity.ok(book))
                .orElse(ResponseEntity.notFound().build());

    }




}
