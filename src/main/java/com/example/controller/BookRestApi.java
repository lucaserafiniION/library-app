package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.db.BookService;

@RestController
public class BookRestApi {
	@Autowired
	private BookService bookService;
  
    @GetMapping("/books/search/autocomplete")  
    @ResponseBody  
    public List<String> autocomplete(@RequestParam("term") String term) {  
        return bookService.searchAutocomplete(term);  
    } 

}
