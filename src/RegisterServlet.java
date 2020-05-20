import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("get");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("post");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");


        registerPatient(request, response);


    }

    public void registerPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.getDatabaseConnection();

        Patient patient = new Patient(name, surname, login, password, email);

        if (databaseConnector.addPatient(patient, connection)) {

            System.out.println("ok");

            out.println("<meta http-equiv='refresh' content='2;URL=loginPage.html'>");
            out.println("<p><h1>User registered correctly. Log in and register first visit !</h1></p>");


        } else {

            System.out.println("error");

            out.println("<meta http-equiv='refresh' content='2;URL=registerPage.html'>");
            out.println("<p><h1>This user already exist !</h1></p>");

        }

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
