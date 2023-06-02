<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Students</title>
    <style>
        table {
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
        }
    </style>
</head>
<body>
    <h1>All Students</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date of Birth</th>
            <th>Adhaar Card Number</th>
            <th>City</th>
            <th>Languages Known</th>
            <th>Stream</th>
            <th>State</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${formattedDates[student]}</td>
                <td>${student.adhaarCardNumber}</td>
                <td>${student.city}</td>
                <td>
                    <c:forEach var="language" items="${student.languagesKnown}">
                        ${language}<br>
                    </c:forEach>
                </td>
                <td>${student.stream}</td>
                <td>${student.state}</td>
                <td><a href="/students/edit/${student.id}">Edit</a></td>
                <td>
                    <form action="/students/delete/${student.id}" method="post">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
