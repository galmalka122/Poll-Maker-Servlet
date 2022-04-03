package hac.servlets;

import hac.poll.PollManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


// a simple demo servlet to remove from your repo upon submission!
@WebServlet(name = "Poll", urlPatterns = "/poll")
public class PollServlet extends HttpServlet {

    private PollManager pollManager;

    @Override
    public void init() throws ServletException {
        pollManager = (PollManager) getServletContext().getAttribute("pollManager");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String poll = pollManager.getPollJson();
            out.write(poll);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] allcookies = request.getCookies();
        boolean found = false;
        // if there are cookies, check if the cookie visited is present
        if (allcookies != null)

            for (Cookie c : allcookies) {
                if ((found = c.getName().equals("voted") == true)) {
                    response.setStatus(401);
                    request.getRequestDispatcher("results").forward(request, response);
                }
            }
        if(!found) {
            Cookie c = new Cookie("voted", "true");
            c.setMaxAge(60 * 60 * 2);
            c.setHttpOnly(false);
            response.addCookie(c);
            request.getRequestDispatcher("vote").forward(request, response);
        }
    }

}
