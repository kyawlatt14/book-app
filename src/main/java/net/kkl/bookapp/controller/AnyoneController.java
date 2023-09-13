package net.kkl.bookapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.kkl.bookapp.common.BookAppResponse;
import net.kkl.bookapp.common.dto.CommentDTO;
import net.kkl.bookapp.service.AnyoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/anyone")
@RequiredArgsConstructor
@Validated
public class AnyoneController {

    private final AnyoneService anyoneService;

    /*get books from db*/
    @GetMapping("books")
    public ResponseEntity<BookAppResponse> getBooks(@RequestParam(defaultValue = "0")int pageNo,
                                                    @RequestParam(defaultValue = "10")int pageSize) {
        return ResponseEntity.ok(anyoneService.getBooks(pageNo,pageSize));
    }

    /*save comment into db*/
    @PostMapping("comment")
    public ResponseEntity<BookAppResponse> commentOnBook(@RequestBody @Valid CommentDTO commentDTO) {
        return ResponseEntity.ok(anyoneService.commentOnBook(commentDTO));
    }

    /*get book-detail from db*/
    @GetMapping("book-detail")
    public ResponseEntity<BookAppResponse> bookDetail(@RequestParam (name = "id")Long bookId) {
        return ResponseEntity.ok(anyoneService.bookDetail(bookId));
    }

    @GetMapping("welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("You are welcome!");
    }
}
