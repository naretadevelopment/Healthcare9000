import java.sql.*;
import java.util.ArrayList;


public class DatabaseConnector {


    public Connection getDatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Healthcare9000Database?characterEncoding=latin1&useConfigs=maxPerformance", "root", "password123");
            return connection;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public ArrayList<Patient> getPatients(Connection connection) {

        ArrayList<Patient> patientsList = new ArrayList();

        try {
            String query = " select * from patients_table ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {


                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");

                Patient patient = new Patient(id, name, surname, login, password, email);
                patientsList.add(patient);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return patientsList;
    }


    public ArrayList<Patient> validatePatient(Connection connection, String login, String password) {

        ArrayList<Patient> patientsList = new ArrayList();

        try {
            String query = " select * from patients_table where login=? and password =?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(login));
            preparedStatement.setString(2, String.valueOf(password));


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {


                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");

                Patient patient = new Patient(id, name, surname, login, password, email);
                patientsList.add(patient);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return patientsList;
    }


    public ArrayList<Visit> getVisits(Connection connection) {

        ArrayList<Visit> visitsList = new ArrayList();

        try {
            String query = " select * from visits_table ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {


                int id = resultSet.getInt("id");
                int patientId = resultSet.getInt("patientId");
                String specialty = resultSet.getString("specialty");
                String date = resultSet.getString("date");
                String place = resultSet.getString("place");
                boolean confirmed = resultSet.getBoolean("confirmed");


                Visit visit = new Visit(id, patientId, specialty, date, place, confirmed);

                visitsList.add(visit);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return visitsList;
    }


    public void cancelVisit(Connection connection, int id) {


        try {


            String query = " update visits_table set patientId=? ,confirmed =? where id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 0);
            preparedStatement.setBoolean(2, false);
            preparedStatement.setInt(3, id);

            preparedStatement.execute();


        } catch (Exception e) {
            System.out.println(e);
        }


    }


    public void registerVisit(Connection connection, int patientId, int id) {


        try {


            String query = " update visits_table set patientId=? ,confirmed =? where id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setBoolean(2, true);
            preparedStatement.setInt(3, id);


            preparedStatement.execute();


        } catch (Exception e) {
            System.out.println(e);
        }


    }


    public ArrayList<Visit> getPatientVisits(Connection connection, int patientId) {

        ArrayList<Visit> visitsList = new ArrayList();

        try {
            String query = " select * from visits_table where patientId=? and confirmed = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(patientId));
            preparedStatement.setBoolean(2, true);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {


                int id = resultSet.getInt("id");
                String specialty = resultSet.getString("specialty");
                String date = resultSet.getString("date");
                String place = resultSet.getString("place");
                boolean confirmed = resultSet.getBoolean("confirmed");


                Visit visit = new Visit(id, patientId, specialty, date, place, confirmed);

                visitsList.add(visit);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return visitsList;
    }


    public ArrayList<Visit> searchVists(Connection connection, String speciality, String date, String place) {


        ArrayList<Visit> visitsList = new ArrayList();

        System.out.println(date);


        try {

            ResultSet resultSet;
            PreparedStatement preparedStatement;


            if (!place.equals("all clinics")) {


                String query = " select * from visits_table where specialty=? and date>?  and place=? and confirmed=?";


                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, speciality);
                preparedStatement.setString(2, date);
                preparedStatement.setString(3, place);

                preparedStatement.setBoolean(4, false);
                resultSet = preparedStatement.executeQuery();


            } else {

                String query = " select * from visits_table where specialty=?  and date>?and confirmed=?";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, speciality);
                preparedStatement.setString(2, date);
                preparedStatement.setBoolean(3, false);

                resultSet = preparedStatement.executeQuery();


            }


            while (resultSet.next()) {


                int resultId = resultSet.getInt("id");
                int resultPatientId = resultSet.getInt("patientId");
                String resultSpecialty = resultSet.getString("specialty");
                String resultDate = resultSet.getString("date");
                String resultPlace = resultSet.getString("place");
                boolean resultConfirmed = resultSet.getBoolean("confirmed");


                Visit visit = new Visit(resultId, resultPatientId, resultSpecialty, resultDate, resultPlace, resultConfirmed);

                visitsList.add(visit);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return visitsList;


    }


    public boolean addPatient(Patient patient, Connection connection) {
        String name = patient.getName();
        String surname = patient.getSurname();
        String login = patient.getLogin();
        String password = patient.getPassword();
        String email = patient.getEmail();


        try {
            String query = " insert into patients_table(name,surname,login,password,email)"
                    + " values (?, ?, ?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, email);

            preparedStatement.execute();

            return true;

        } catch (Exception e) {
            System.out.println(e);

            return false;


        }


    }


    public boolean addVisit(Visit visit, Connection connection) {
        String specialty = visit.getSpecialty();
        String date = visit.getDate();
        String place = visit.getPlace();
        Boolean confirmed = visit.isConfirmed();


        try {
            String query = " insert into patients_table(name,surname,login,password,email)"
                    + " values (?, ?, ?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, specialty);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, place);
            preparedStatement.setBoolean(4, confirmed);


            preparedStatement.execute();

            return true;

        } catch (Exception e) {
            System.out.println(e);

            return false;


        }


    }


    public void deleteVisit(Connection connection, int visitId) {


        try {
            String query = "delete  from visits_table where id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, visitId);


            preparedStatement.execute();


        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public void deletePatient(Connection connection, int patientId) {


        try {
            String query = "delete  from patients_table where id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);

            preparedStatement.execute();


        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
