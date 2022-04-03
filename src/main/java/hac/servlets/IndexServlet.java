package hac.servlets;

import hac.exceptions.AuthenticateException;
import hac.poll.PollManager;
import hac.poll.PollSingleton;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexServlet", urlPatterns = "")
public class IndexServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        String path = sc.getRealPath(sc.getInitParameter("POLLFILE"));
        PollManager pollManager = null;
        try {
            pollManager = new PollSingleton().getInstance(path).getPollManager();
        } catch (AuthenticateException e) {
            throw new ServletException(e.getMessage());
        }
        getServletContext().setAttribute("pollManager", pollManager);
        response.setContentType("text/html");
        request.getRequestDispatcher("index.html").forward(request, response);
    }
}
