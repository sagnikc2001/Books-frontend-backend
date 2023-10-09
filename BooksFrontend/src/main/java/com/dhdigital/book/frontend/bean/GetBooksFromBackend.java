package com.dhdigital.book.frontend.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhdigital.book.frontend.model.BookEnquiry;
import com.dhdigital.book.frontend.model.BooksBackend;
import com.dhdigital.book.frontend.model.BooksFrontend;

@Service("getBooksFromBackend")
public class GetBooksFromBackend {
	
	@Autowired
	private BooksFrontend booksFrontend;
	
	
//	Mapping the values from backend object class to Frontend object class
	public void getBooksResponse(BooksBackend booksBackend){
		
		booksFrontend.setISBN(booksBackend.getIsbn());
		booksFrontend.setBOOKNAME(booksBackend.getBookName());
		booksFrontend.setGENRE(booksBackend.getGenre());
		booksFrontend.setAUTHOR(booksBackend.getAuthor());
		booksFrontend.setQTY(booksBackend.getQty());
		
		System.out.println(booksFrontend.toString());
	}

}
