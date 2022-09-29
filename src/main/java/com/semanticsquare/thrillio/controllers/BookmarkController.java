package com.semanticsquare.thrillio.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.manager.BookmarkManager;
import com.semanticsquare.thrillio.manager.UserManager;


@WebServlet(urlPatterns = {"/bookmark", "/bookmark/save", "/bookmark/mybooks"})
public class BookmarkController extends HttpServlet {
	/*private static BookmarkController instance = new BookmarkController();
	private BookmarkController () {}
	public static BookmarkController getInstance() {
		return instance;
	}*/
	
	public BookmarkController() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		System.out.println("Servlet path: " + request.getServletPath());
		
		if (request.getSession().getAttribute("userId") != null) {
			long userId = (long) request.getSession().getAttribute("userId");
			
			if (request.getServletPath().contains("save")) {
				// save
				dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				
				String bid = request.getParameter("bid");
				
				User user = UserManager.getInstance().getUser(userId);
				Bookmark bookmark = BookmarkManager.getInstance().getBook(Long.parseLong(bid));
				BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
				
				Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(true, userId);
				request.setAttribute("books", list);
				
			} else if (request.getServletPath().contains("mybooks")) {
				// mybooks
				dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				
				Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(true, userId);
				request.setAttribute("books", list);
				
			} else {
				dispatcher = request.getRequestDispatcher("/browse.jsp");
				
				Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(false, userId);
				request.setAttribute("books", list);
			}
		} else {
			dispatcher = request.getRequestDispatcher("/browse.jsp");
		}
		
		dispatcher.forward(request, response);
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
		
	}
	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
		BookmarkManager.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
		
	}
	public void share(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().share(user, bookmark);
		
	}
}
