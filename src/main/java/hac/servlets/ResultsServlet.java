package hac.servlets;

import hac.exceptions.AuthenticateException;
import hac.poll.PollManager;
import hac.poll.PollSingleton;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Results", value = "/results")
public class ResultsServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String c = new PollSingleton().getPollManager().getChartJson();
        out.write(c);
    }

}
