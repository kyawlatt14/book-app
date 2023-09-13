package net.kkl.bookapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.kkl.bookapp.common.BookAppResponse;
import net.kkl.bookapp.common.dto.BookDTO;
import net.kkl.bookapp.common.dto.UpdateBookDTO;
import net.kkl.bookapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    /*save book into db*/
    @PostMapping(value = "add-book",consumes = { "multipart/form-data" })
    public ResponseEntity<BookAppResponse> addBook(@ModelAttribute @Valid BookDTO bookDTO) throws IOException {
        return ResponseEntity.ok(userService.addBook(bookDTO));
    }

    /*delete book from db*/
    @PostMapping("delete-book")
    public ResponseEntity<BookAppResponse> deleteBook(@RequestParam (name = "id")Long bookId) {
        return ResponseEntity.ok(userService.deleteBook(bookId));
    }

    /*get books from db*/
    @GetMapping("books")
    public ResponseEntity<BookAppResponse> getBooks(@RequestParam(defaultValue = "0")int pageNo,
                                                    @RequestParam(defaultValue = "10")int pageSize) {
        return ResponseEntity.ok(userService.getBooks(pageNo,pageSize));
    }

    /*update book from db*/
    @PostMapping(value = "update-book",consumes = { "multipart/form-data" })
    public ResponseEntity<BookAppResponse> updateBook(@ModelAttribute UpdateBookDTO updateBookDTO) throws IOException{
        return ResponseEntity.ok(userService.updateBook(updateBookDTO));
    }
}
