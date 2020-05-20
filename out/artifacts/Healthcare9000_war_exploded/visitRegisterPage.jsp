<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient page</title>

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
            <a><input type="button" value="Go back" onclick="history.go(-1)" class="btn btn-primary btn-lg"></a></p>


            </form>
        </div>

    </div>
</nav>


<div class="jumbotron">
    <div class="container">
        <h1>Results</h1>
    </div>

</div>


<br>

<table id="table" style="width:100%" style="border: 1px solid black" style="">


    <tr>
        <th><h3>ID</h3></th>
        <th><h3>SPECIALTY</h3></th>
        <th><h3>DATE</h3></th>
        <th><h3>PLACE</h3></th>
        <th><h3></h3></th>


    </tr>


    <c:forEach var="tmpVisit" items="${visitsList}">


        <c:url var="registerLink" value="PatientServlet">
            <c:param name="action" value="registerVisit"></c:param>
            <c:param name="visitId" value="${tmpVisit.getId()}"></c:param>
        </c:url>
        <tr>


            <td><h3>${tmpVisit.getId()}</h3></td>
            <td><h3>${tmpVisit.getSpecialty()}</h3></td>
            <td><h3>${tmpVisit.getDate()}</h3></td>
            <td><h3>${tmpVisit.getPlace()}</h3></td>
            <td>
                <a href="${registerLink}"
                   onclick="if(!(confirm('Register this visit ?'))) return false">
                    <button type="button" class="btn btn-primary btn-lg">Register</button>
                </a>
            </td>

        </tr>
    </c:forEach>


</table>


<footer>&copy; Healthcare 9000</footer>

</body>
</html>
