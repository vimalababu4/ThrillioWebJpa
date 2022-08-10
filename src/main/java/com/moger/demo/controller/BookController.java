package com.moger.demo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.moger.demo.entities.Book;
import com.moger.demo.entities.User;
import com.moger.demo.service.BookService;

@Controller
public class  BookController{
	 String email;
	private BookService service;
	public BookController(BookService service) {
		super();
		this.service = service;
	}
	
	
	@GetMapping("/books")
	public String listBooks(Model model) {
		List<Book> books =service.getAllBooks();
		model.addAttribute("books", books);
		  model.addAttribute("message", email);
		return "book";
	}
	
	@GetMapping("/book/new")
	public String AddBook(Model model) {
		//create object to hold data
		Book book= new Book(); 
		model.addAttribute("book", book);
		return "Add_book";
				
	}
	@PostMapping("/book")
	public String saveBook(@ModelAttribute("book") Book book) {
		service.saveBookInfo(book);
		return  "redirect:/books";
		
	}
	@GetMapping("/book/edit/{id}")
	public String UpdateBook(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("book",service.getBookById(id));
		return "update";
		
	}

	@PostMapping("/book/{id}")
	public String updatingBook(@PathVariable("id") Long id,  @ModelAttribute("book") Book book,Model model) {
		
		service.updateBook(book, id);
		return  "redirect:/books";
		
	}
	@GetMapping("/book/{id}")
	public String DeleteBook(@PathVariable("id") Long id) {
		service.deleteBook(id);
		return "redirect:/books";
	
	}
	@GetMapping("/book/save/{id}")
	public String SaveBooks(@PathVariable("id") Long id, Model model) {
		Collection<Book> books= service.saveBooks(id);
		model.addAttribute("books", books);
		return "savedBooks";
		
	
	}
	// @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="invalid email or password")
	// @ExceptionHandler(IllegalArgumentException.class)
	@PostMapping("/user")
	 public String validateUser(@ModelAttribute(name="loginForm") User user, HttpSession session) {    //, HttpServletRequest request) {
		
				 email = user.getEmail();
				 String password = user.getPassword();
			     User u=service.authenticate(email, password);
			  
		
			     session.setAttribute("name", email);
		
		  		    //request.getSession().setAttribute("userId", Long.valueOf(u.getId()));
		    return "redirect:/books";
			     
		  
}
	
	
	@GetMapping("/user/new")
	public String AddUser(Model model) {
		
		User user= new User(); 
		model.addAttribute("registrationForm", user);
		return "register";
	}
	
	@PostMapping("/registration")
	public String RegisterUser(@ModelAttribute(name="registrationForm") User user) {
		service.saveUser(user);
		return "redirect:/user/new?success";
	}
	@GetMapping("/logout")
	public String logoutUser() {
		return "index";
	}
}



