<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.Vacunas" %>
<%@page import="com.emergentes.modelo.GestorVacunas" %>
<%
    if(session.getAttribute("agenda") == null){
        GestorVacunas objeto1 = new GestorVacunas();
        
        objeto1.insertarPaciente(new Vacunas(1,"Brunito Diaz",25,140,"si"));
        objeto1.insertarPaciente(new Vacunas(1,"Juancito Pinto",30,152,"no"));
        
        session.setAttribute("agenda", objeto1);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL - PrimerParcial </title>
    </head>
    <body>
        <table border="2" ALIGN="center">
            <th>
                PRIMER PARCIAL TEM-742
                <br><br>
                Nombre: Univ. Rider Yanarico Cocarico
                <br><br>
                Carnet: 12636549 LP
            </th>   
        </table>
        
        <h1 ALIGN="center">Registro de Vacunas</h1>
        <a href="Controller?op=nuevo" > Nuevo</a>
        <table border="1"  ALIGN="center">
            <tr>
                <th>Id</th>   
                <th>Nombre</th>  
                <th>Peso</th>
                <th>Talla</th>
                <th>Vacuna</th>
                <th></th>  
                <th></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.agenda.getLista()}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nombre}</td>
                    <td>${item.peso}</td>
                    <td>${item.talla}</td>
                    <td>${item.vacuna}</td>
                    <td><a href="Controller?op=modificar&id=${item.id}">Modificar</a></td>
                    <td><a href="Controller?op=eliminar&id=${item.id}">Eliminar</a></td>
                </tr>
            </c:forEach>  
            
        </table>
    </body>
</html>