import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/PatientServlet")

public class PatientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8"); // polskie znaki

        String action = request.getParameter("action");

        System.out.println(action);


        switch (action) {

            case "cancelVisit":
                cancelVisits(request, response);
                break;


            case "registerVisit":


                registerVisits(request, response);
                break;


            case "deletePatient":


                deletePatients(request, response);
                break;


        }


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    void cancelVisits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int visitId = Integer.valueOf(request.getParameter("visitId"));


        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.getDatabaseConnection();

        databaseConnector.cancelVisit(connection, visitId);

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        PrintWriter out = response.getWriter();

        String login = GlobalVariables.login;
        String password = GlobalVariables.password;

        out.println("<meta http-equiv='refresh' content='1;URL=LoginServlet?login=" + login + "&password=" + password + "'>");
        out.println("<p ><h1>Cancelled successfully!</h1></p>");


    }


    void registerVisits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int visitId = Integer.valueOf(request.getParameter("visitId"));


        System.out.println(visitId);

        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.getDatabaseConnection();

        int patientId = GlobalVariables.id;

        databaseConnector.registerVisit(connection, patientId, visitId);

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        PrintWriter out = response.getWriter();

        String login = GlobalVariables.login;
        String password = GlobalVariables.password;

        out.println("<meta http-equiv='refresh' content='1;URL=LoginServlet?login=" + login + "&password=" + password + "'>");
        out.println("<p><h1>Registered successfully!</h1></p>");


    }


    void deletePatients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int patientId = GlobalVariables.id;


        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.getDatabaseConnection();

        databaseConnector.deletePatient(connection, patientId);

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.println("<meta http-equiv='refresh' content='1;URL=index.html'>");
        out.println("<p><h1>Account deleted successfully!</h1></p>");

    }


}
