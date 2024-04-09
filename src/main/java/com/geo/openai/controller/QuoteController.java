package com.geo.openai.controller;

import com.geo.openai.model.AuthorQuotes;
import com.geo.openai.service.IQuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quote")
public class QuoteController {
    private IQuoteService quoteService;

    public QuoteController(IQuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("")
    public ResponseEntity<AuthorQuotes> generateAuthorQuotes(@RequestParam(value = "author", defaultValue = "Jean-paul Sartre") String author,
                                                 @RequestParam(value = "nbQuotes", defaultValue = "3") Integer nbQuotes) {

        return ResponseEntity.ok(quoteService.getAuthorQuotes(author, nbQuotes));
    }
}
