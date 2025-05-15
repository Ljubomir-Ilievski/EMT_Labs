package mk.ukim.finki.emt2025.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt2025.model.dto.CreateBookDto;
import mk.ukim.finki.emt2025.model.dto.DisplayBookDto;
import mk.ukim.finki.emt2025.model.views.BooksPerAuthorView;
import mk.ukim.finki.emt2025.repository.BooksPerAuthorRepository;
import mk.ukim.finki.emt2025.service.application.BookApplicationService;
import mk.ukim.finki.emt2025.service.domain.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//http://localhost:8080/swagger-ui/index.html#/
@RestController
@RequestMapping(value = {"/api/books"})
@Tag(name = "Book API", description = "Manipulate with the books")
public class BookController {

    private final BookApplicationService bookService;
    private final AuthorService authorService;

    public BookController(BookApplicationService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @Operation(summary = "List Books", description = "List all of the books in the DB, is the response is empty, there are no books in the DB")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully Listed All of the books"
                    )

            }
    )
    @GetMapping
    List<DisplayBookDto> findAll(){
        /*Iterator<String> httpIterator = httpServletRequest.getHeaderNames().asIterator();
        while (httpIterator.hasNext()){
            System.out.println(httpIterator.next());
       }*/

       // System.out.println(userDetails.getUsername());
        //System.out.println((String) httpServletRequest.getSession().getAttribute("user"));
       return bookService.findByIsSoftDeleted();
    }


    @Operation(summary = "Find Book", description = "Find a book by its ID, a 404 code will be returned if the id send is invalid.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found and returned the book."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "A book with the specified ID doesn't exist."
                    )

            }
    )
    @GetMapping("/{id}")
    ResponseEntity<DisplayBookDto> findById(@PathVariable(value = "id") Long bookId){
        return bookService.findById(bookId)
                .map(book -> {return ResponseEntity.ok(book);})
                .orElse(ResponseEntity.notFound().build());

    }

    @Operation(summary = "Add book", description = "Add a whole new book.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully added the book"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request, All parameters need to be send."
                    )

            }
    )
    @PostMapping("/add")
    ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto createBookDto){

        return bookService.save(createBookDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());


    }
    @Operation(summary = "Update Book", description = "Update a specific book, null values will be ignored.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Book Updated successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request, All parameters need to be send. But you can set null values to those who you dont want changed."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book with that specific ID was not found."
                    )

            }
    )
    @PutMapping("/update/{id}")
    ResponseEntity<Object> update(@PathVariable(value = "id") Long bookId, @RequestBody CreateBookDto createBookDto){

            return bookService.update(bookId, createBookDto)
                    .map(book -> {return ResponseEntity.ok().build();})
                    .orElse(ResponseEntity.notFound().build());

    }
    @Operation(summary = "Delete Book", description = "Delete a specific book.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Book Deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book with that specific ID was not found."
                    )

            }
    )
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Object> delete(@PathVariable(value = "id") Long bookId) {

        return bookService.deleteById(bookId)
                .map(book -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.notFound().build());

    }

    @Operation(summary = "Soft Delete a Book", description = "Tag a book for soft deletion, this book we be interpreted as deleted but kept in the DB.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Book soft Deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book with that specific ID was not found."
                    )

            }
    )
    @DeleteMapping("/softDelete/{id}")
    ResponseEntity<DisplayBookDto> softDelete(@PathVariable Long id){

        return bookService.softDeleteById(id)
                .map(book -> ResponseEntity.ok(book))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/newest")
    List<DisplayBookDto> getNewestBooks(){

        return bookService.findNewest10Books();

    }

    @GetMapping("/by-author")
    List<BooksPerAuthorView> getBookCountPerAuthor(){
        List<BooksPerAuthorView> booksPerAuthorViews = authorService.bookCountPerAuthor();
        //booksPerAuthorViews.get(0).getNumber_of_books();
        return booksPerAuthorViews;
    }








}
