package hac.servlets;

import hac.poll.PollManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VoteServlet", value = "/vote")
public class VoteServlet extends HttpServlet {

    private PollManager pollManager;

    @Override
    public void init() throws ServletException {
        pollManager = (PollManager) getServletContext().getAttribute("pollManager");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String answer = request.getParameter("answer");
        pollManager.updateVotes(answer);
        request.getRequestDispatcher("results").forward(request,response);
    }
}
