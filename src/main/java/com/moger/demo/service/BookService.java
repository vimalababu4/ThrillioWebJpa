package com.moger.demo.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.moger.demo.entities.Book;
import com.moger.demo.entities.User;

public interface BookService {
	
		Book saveBookInfo(Book emp);
		List<Book> getAllBooks();
		Book getBookById(Long id);
		Book updateBook(Book book,Long id);
		void deleteBook(Long id);
		Book getBooks(long userId);
		User authenticate(String email, String password);
		User saveUser(User user);
		Collection<Book> saveBooks(Long id);
		
	}
