/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;


import DAO.Senha;
import DAO.SenhaController;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



/**
 *
 * @author Maike Nunes
 */
public class Visualizador extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mnunesorPU");
        SenhaController senhaDao = new SenhaController(emf);
        List<Senha> listaSenhas  = senhaDao.consultaUltimasSenhas();
        
        
        JSONArray arr = new JSONArray();

        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        JSONObject obj_json;
        Senha senha;
        for (int x=0; x< listaSenhas.size(); x++) {
            obj_json = new JSONObject();
            senha = listaSenhas.get(x);
            obj_json.put("cod", senha.getChave());
//            obj_json.put("setor", listaSenhas.get(x).getIdsetor().toString());
//            obj_json.put("prioridade", listaSenhas.get(x).getIdtipoAtendimento().toString());
//            obj_json.put("quando", format.format(listaSenhas.get(x).getDthrChamada()));
            arr.add(obj_json);
        }

        response.getWriter().write(arr.toString());
    }

}
