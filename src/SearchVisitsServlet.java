import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/SearchVisitsServlet")

public class SearchVisitsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8"); // polskie znaki
        PrintWriter out = response.getWriter();


        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.getDatabaseConnection();


        String specialty = request.getParameter("specialty");
        String date = request.getParameter("date");
        String place = request.getParameter("place");


        System.out.println(specialty + " " + date + " " + place);


        ArrayList<Visit> visitsList = databaseConnector.searchVists(connection, specialty, date, place);

        if (!visitsList.isEmpty()) {

            goToVisitRegisterPage(request, response, visitsList);
        } else {

            out.println("<meta http-equiv='refresh' content='1;URL=searchVisitPage.html'>");//redirects after 3 seconds
            out.println("<p> <h1>There are not visits with this parameters !</h1></p>");



        }

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private void goToVisitRegisterPage(HttpServletRequest request, HttpServletResponse response, ArrayList visitsList) throws ServletException, IOException {


        request.setAttribute("visitsList", visitsList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("visitRegisterPage.jsp");

        dispatcher.forward(request, response);


    }



}
