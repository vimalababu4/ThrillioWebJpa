package com.moger.demo.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class BookUser { 
	
    @EmbeddedId
    private BookUserId id;

    public BookUser() {
		super();
	}
	@ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id", nullable =false)
    private Book book;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable=false)
    private User user;
   

    public BookUser(Book book, User user) {
        this.id = new BookUserId(book.getId(), user.getId());
        this.book = book;
        this.user = user;
   
    }

    
}
