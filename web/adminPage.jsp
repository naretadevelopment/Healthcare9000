<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="apple-touch-icon" href="apple-touch-icon.png">

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        body {
            padding-top: 50px;
            padding-bottom: 20px;
        }

        table, th, td {
            border: 1px solid black;
            text-align: center;
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

    </style>
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/main.css">

    <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>


</head>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>


        </div>


        <div id="navbar" class="navbar-collapse collapse navbar-right">
            </button>
            <a class="navbar-brand" href="index.html#index" class="btn btn-primary btn-lg">Go back</a></p>

            </form> -->
        </div>

    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <h1> Hello Admin</h1>
    </div>
</div>


<div>
    <div class="jumbotron">
        <div class="container">
            <h2> Patients</h2>


        </div>


        <form action="RegisterServlet" method="post">


            <label><h4>name:</h4></label>
            <input type="text" name="name" required>

            <label><h4>surname:</h4></label>
            <input type="text" name="surname" required>


            <label><h4>login:</h4></label>
            <input type="text" name="login" required>

            <label><h4>password:</h4></label>
            <input type="password" name="password" required>

            <label><h4>email:</h4></label>
            <input type="email" name="email" required>


            <input type="submit" value="Add patient" class="btn btn-primary btn-lg">

        </form>
    </div>

    <table id="patientsTable" style="width:100%" style="border: 1px solid black" style="">


        <tr>
            <th><h3>ID</h3></th>
            <th><h3>NAME</h3></th>
            <th><h3>SURNAME</h3></th>
            <th><h3>EMAIL</h3></th>
            <th><h3></h3></th>


        </tr>


        <c:forEach var="tmpPatient" items="${patientsList}">


            <c:url var="deletePatientLink" value="AdminServlet">
                <c:param name="action" value="deletePatient"></c:param>
                <c:param name="patientId" value="${tmpPatient.getId()}"></c:param>
            </c:url>


            <tr>


                <td><h3>${tmpPatient.getId()}</h3></td>
                <td><h3>${tmpPatient.getName()}</h3></td>
                <td><h3>${tmpPatient.getSurname()}</h3></td>
                <td><h3>${tmpPatient.getEmail()}</h3></td>

                <td>
                    <a href="${deletePatientLink}"
                       onclick="if(!(confirm('Delete this account ?'))) return false">
                        <button type="button" class="btn-danger">Delete</button>
                    </a>
                </td>


            </tr>
        </c:forEach>


    </table>


    <br>
</div>
<div>


</div>
<div class="jumbotron">
    <div class="container">
        <h2> Visits</h2>
    </div>


    <form action="AdminServlet" method="post">


        <label><h4>specialty:</h4></label>

        <select id="specialty" required name="specialty">
            <option value="surgeon">surgeon</option>
            <option value="internist">internist</option>
            <option value="pediatrician">pediatrician</option>
            <option value="psychiatrist">psychiatrist</option>
            <option value="1 of 10 dentist">1 of 10 dentist</option>
            <option value="ophthalmologist">ophthalmologist</option>
            <option value="gynecologist">gynecologist</option>
            <option value="urologist">urologist</option>
            <option value="orthopaedist">orthopaedist</option>
            <option value="plastic surgeon">plastic surgeon</option>

        </select>


        <label><h4>date:</h4></label>
        <input type="datetime-local" name="date" required>


        <label for="place"><h4>Choose clinic:</h4></label>

        <select id="place" required name="place">
            <option value="Clinic 1">Clinic 1</option>
            <option value="Clinic 2">Clinic 2</option>
            <option value="Clinic 3">Clinic 3</option>
            <option value="Clinic 4">Clinic 4</option>
            <option value="Clinic 5">Clinic 5</option>

        </select>


        <input type="submit" value="Add visit" class="btn btn-primary btn-lg">

    </form>


</div>


<table id="visitsTable" style="width:100%" style="border: 1px solid black" style="">


    <tr>
        <th><h3>ID</h3></th>
        <th><h3>PATIENT ID</h3></th>
        <th><h3>SPECIALTY</h3></th>
        <th><h3>DATE</h3></th>
        <th><h3>PLACE</h3></th>
        <th><h3>VISIT CONFIRMED</h3></th>
        <th><h3></h3></th>


    </tr>


    <c:forEach var="tmpVisit" items="${visitsList}">


        <c:url var="deleteVisitLink" value="AdminServlet">
            <c:param name="action" value="deleteVisit"></c:param>
            <c:param name="visitId" value="${tmpVisit.getId()}"></c:param>
        </c:url>


        <tr>


            <td><h3>${tmpVisit.getId()}</h3></td>
            <td><h3>${tmpVisit.getPatientId()}</h3></td>
            <td><h3>${tmpVisit.getSpecialty()}</h3></td>
            <td><h3>${tmpVisit.getDate()}</h3></td>
            <td><h3>${tmpVisit.getPlace()}</h3></td>
            <td><h3>${tmpVisit.isConfirmed()}</h3></td>


            <td>
                <a href="${deleteVisitLink}"
                   onclick="if(!(confirm('Delete this visit ?'))) return false">
                    <button type="button" class="btn-danger">Delete</button>
                </a>
            </td>


        </tr>
    </c:forEach>


</table>
</div>

<br>


<footer>&copy; Healthcare 9000</footer>

</body>
</html>
