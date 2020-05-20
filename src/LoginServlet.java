
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();


        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.getDatabaseConnection();


        String login = request.getParameter("login");
        String password = request.getParameter("password");


        if (login.equals(GlobalVariables.adminLogin) && password.equals(GlobalVariables.adminPassword)) {


            ArrayList<Visit> visitsList = databaseConnector.getVisits(connection);

            ArrayList<Patient> patientsList = databaseConnector.getPatients(connection);


            goToAdminPage(request, response, visitsList, patientsList);


        } else {


            ArrayList<Patient> patientsList = databaseConnector.validatePatient(connection, login, password);

            if (!patientsList.isEmpty()) {

                int id = patientsList.get(0).getId();
                String name = patientsList.get(0).getName();
                String surname = patientsList.get(0).getSurname();


                GlobalVariables.id = id;
                GlobalVariables.login = login;
                GlobalVariables.password = password;


                ArrayList<Visit> patientsVisitsList = databaseConnector.getPatientVisits(connection, id);


                goToPatientPage(request, response, name, surname, patientsVisitsList);
            } else {


                out.println("<meta http-equiv='refresh' content='3;URL=registerPage.html'>");//redirects after 3 seconds
                out.println("<p style='color:red;'><h1>This user does not exist !</h1></p>");


            }
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("post");

    }


    private void goToPatientPage(HttpServletRequest request, HttpServletResponse response, String name, String surname, ArrayList patientsVisitsList) throws ServletException, IOException {


        request.setAttribute("name", name);
        request.setAttribute("surname", surname);

        request.setAttribute("patientsVisitsList", patientsVisitsList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("patientPage.jsp");

        dispatcher.forward(request, response);


    }

    private void goToAdminPage(HttpServletRequest request, HttpServletResponse response, ArrayList visitsList, ArrayList patientsList) throws ServletException, IOException {


        request.setAttribute("visitsList", visitsList);

        request.setAttribute("patientsList", patientsList);


        RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage.jsp");

        dispatcher.forward(request, response);


    }

}