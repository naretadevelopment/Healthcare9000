import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/AdminServlet")

public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        System.out.println(action);


        switch (action) {


            case "deleteVisit":
                deleteVisits(request, response);
                break;


            case "deletePatient":
                deletePatients(request, response);
                break;


        }


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        addVisits(request, response);


    }


    void addVisits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.getDatabaseConnection();

        PrintWriter out = response.getWriter();

        String specialty = request.getParameter("specialty");
        String date = request.getParameter("date");
        String place = request.getParameter("place");

        Visit visit = new Visit(specialty, date, place, false);


        if (databaseConnector.addVisit(visit, connection)) {
            ;


            out.println("<meta http-equiv='refresh' content='1;URL=LoginServlet?login=" + GlobalVariables.adminLogin + "&password=" + GlobalVariables.adminPassword + "'>");//redirects after 1 seconds
            out.println("<p style='color:red;'><h1>Visit added successfully!</h1></p>");
        } else {

            out.println("<meta http-equiv='refresh' content='1;URL=LoginServlet?login=" + GlobalVariables.adminLogin + "&password=" + GlobalVariables.adminPassword + "'>");//redirects after 1 seconds
            out.println("<p style='color:red;'><h1> Can not add visit!</h1></p>");

        }


        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    void deleteVisits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int visitId = Integer.valueOf(request.getParameter("visitId"));


        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.getDatabaseConnection();

        databaseConnector.deleteVisit(connection, visitId);

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String login = GlobalVariables.adminLogin;
        String password = GlobalVariables.adminPassword;


        PrintWriter out = response.getWriter();
        out.println("<meta http-equiv='refresh' content='1;URL=LoginServlet?login=" + login + "&password=" + password + "'>");
        out.println("<p style='color:red;'><h1>Deleted successfully!</h1></p>");

    }


    void deletePatients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int patientId = Integer.valueOf(request.getParameter("patientId"));


        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.getDatabaseConnection();

        databaseConnector.deletePatient(connection, patientId);

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String login = GlobalVariables.adminLogin;
        String password = GlobalVariables.adminPassword;


        PrintWriter out = response.getWriter();
        out.println("<meta http-equiv='refresh' content='1;URL=LoginServlet?login=" + login + "&password=" + password + "'>");
        out.println("<p style='color:red;'><h1>Deleted successfully!</h1></p>");

    }


}
