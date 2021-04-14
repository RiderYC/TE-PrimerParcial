package com.emergentes.controlador;

import com.emergentes.modelo.GestorVacunas;
import com.emergentes.modelo.Vacunas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vacunas objPaciente = new Vacunas();
        int id;
        int pos;
        String op = request.getParameter("op");
        
        if(op.equals("nuevo")){
            HttpSession ses = request.getSession();
            GestorVacunas agenda = (GestorVacunas) ses.getAttribute("agenda");
            objPaciente.setId(agenda.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miPaciente", objPaciente);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorVacunas agenda = (GestorVacunas) ses.getAttribute("agenda");
            pos = agenda.ubicarPaciente(id);
            objPaciente = agenda.getLista().get(pos);
            request.setAttribute("op", op);
            request.setAttribute("miPaciente", objPaciente);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorVacunas agenda = (GestorVacunas) ses.getAttribute("agenda");
            pos = agenda.ubicarPaciente(id);
            agenda.eliminarPaciente(pos);
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vacunas objPacinete = new Vacunas();
        int pos;
        String op = request.getParameter("op");
        
        if(op.equals("grabar")){
            objPacinete.setId(Integer.parseInt(request.getParameter("id")));
            objPacinete.setNombre(request.getParameter("nombre"));
            objPacinete.setPeso(Integer.parseInt(request.getParameter("peso")));
            objPacinete.setTalla(Integer.parseInt(request.getParameter("talla")));
            objPacinete.setVacuna(request.getParameter("vacuna"));
            
            HttpSession ses = request.getSession();
            GestorVacunas agenda = (GestorVacunas) ses.getAttribute("agenda");
            
            String opg = request.getParameter("opg");
            if(opg.equals("nuevo")){
                agenda.insertarPaciente(objPacinete);
            }
            else{
                pos = agenda.ubicarPaciente(objPacinete.getId());
                agenda.modificarPaciente(pos, objPacinete);
            }
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
    }
}