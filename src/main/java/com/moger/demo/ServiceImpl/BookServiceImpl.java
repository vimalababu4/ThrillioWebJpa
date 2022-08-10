package com.moger.demo.ServiceImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moger.demo.entities.Book;
import com.moger.demo.entities.User;
import com.moger.demo.service.BookService;

@Service
public class BookServiceImpl implements BookService {
		private static List<Book>  bookmarks = new  ArrayList<>();
		//@Autowired
		@PersistenceContext
		private EntityManager em;
		
		@Transactional
		@Override
		public Book saveBookInfo(Book book) {
			Session session= em.unwrap(Session.class);
			session.save(book);
			return book;
		}
		
		@Transactional
		@Override  
		public List<Book> getAllBooks() {
			Session  session = em.unwrap(Session.class);
			Query<Book> query = session.createQuery("from Book", Book.class);
			List<Book> list =query.getResultList(); 
			return list;
		}
		
		@Transactional
		@Override
		public Book getBookById(Long id) {
			Session  session = em.unwrap(Session.class);
			Query<Book> query = session.createQuery("FROM Book  WHERE id='"+id+"' ", Book.class);
			Book bk = query.getSingleResult();
			return bk;
		}
		
		@Transactional
		@Override
		public Book updateBook(Book book, Long eid) {
			Session  session = em.unwrap(Session.class);
			Query<Book> query  = session.createQuery("update Book  set title=:title, image_url=:image_url, publication_year=:publication_year,publisher_id=:publisher_id,book_genre_id=:book_genre_id,amazon_rating=:amazon_rating" + " where id = '"+eid+"' ");
			query.setParameter("title", book.getTitle());
			query.setParameter("image_url", book.getImage_url());
			query.setParameter("publication_year", book.getPublicationYear());
			query.setParameter("publisher_id", book.getPublisherId());
			query.setParameter("book_genre_id", book.getGenre());
			query.setParameter("amazon_rating", book.getAmazonRating());
			int result = query.executeUpdate();
			return book;
		}
		
		@Transactional
		@Override
		public void deleteBook(Long id) {
			Session  session = em.unwrap(Session.class);
			Query<Book> query=session.createQuery("DELETE from Book WHERE id =:id");
			query.setParameter("id", id);
			 int result=query.executeUpdate();
			
			}
		

		@Override
		public Book getBooks(long bookId) {
			
			Session  session = em.unwrap(Session.class);
			Query<Book> query  =  session.createQuery("FROM Book b join b.book_user WHERE id='"+bookId+"' ", Book.class);
			
			Book bk = query.getSingleResult();
		
			return bk;
		}
	
		 
		public User authenticate(String email, String password) {
			
			Session  session = em.unwrap(Session.class);
			Query<User> query = session.createQuery("from User where email='"+email+"' ", User.class);
			//User u=query.getResultList().stream().findFirst().orElse(null);
			User u=query.getSingleResult();
			/* List results = query.getResultList();
		        if (results.isEmpty()) 
		        	return null;
		        else if (results.size() == 1) 
		        	return (User) results.get(0);
		        throw new NoResultException();*/
			return u;
			
			
		
		}

		@Override
		public User saveUser(User user) {
			Session session= em.unwrap(Session.class);
		
		
			session.save(user);
			return user;
		}


		@Override
		public Collection<Book> saveBooks(Long id) {
			
			Session session= em.unwrap(Session.class);
			
			Book bk=getBookById(id);
			
			bookmarks.add(bk);
		
			
			return bookmarks;
		}
		
}	

	

