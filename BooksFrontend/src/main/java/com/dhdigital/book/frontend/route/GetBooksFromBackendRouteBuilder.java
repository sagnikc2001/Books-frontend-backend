package com.dhdigital.book.frontend.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.apache.camel.component.jackson.JacksonDataFormat;

import com.dhdigital.book.frontend.model.BookEnquiry;
import com.dhdigital.book.frontend.model.BooksBackend;

@Component
public class GetBooksFromBackendRouteBuilder extends RouteBuilder{
	

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
		 .bindingMode(RestBindingMode.json);
		
		rest()
		
//		To get books by ISBN using POST method - http://localhost:8082/books/getBook
		.post("/getBook")
		 .type(BookEnquiry.class)
		 .consumes("application/json")
		 .to("direct:get-books-from-backend");
		
		
		from("direct:get-books-from-backend")
//		 .log("Entered here")
		 .marshal().json(JsonLibrary.Jackson) //Marhsalling to json
		 .to("http://localhost:8080/api/books/get?bridgeEndpoint=true")
		 .unmarshal(new JacksonDataFormat(BooksBackend.class)) //Unmarshalling to Backend object
		 .to("bean:getBooksFromBackend?method=getBooksResponse");
//		 .log("Response from API : ${body}");
	}

}
