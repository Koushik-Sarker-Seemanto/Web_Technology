/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = {"/book"})
public class BookServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        response.setContentType("application/json");
        String input_id=request.getParameter("id");
        
        try {
            Connection con = DBConnection.initDatabase();
            PreparedStatement st;
            if(input_id!=null){
                st = con.prepareStatement("select * from book where id="+input_id);
            }
            else
                st = con.prepareStatement("select * from book");
        ResultSet rs;
        rs = st.executeQuery();
        
        List<Book>result = new ArrayList<>();
         while(rs.next()){
            String id =rs.getString("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            Book book = new Book();
            book.setId(id);
            book.setTitle(title);
            book.setAuthor(author);
            result.add(book);

        }
          st.close(); 
            con.close();
            JSONArray jr = new JSONArray(result);
           try (PrintWriter out = response.getWriter()){
                out.println(jr);
           }
        } catch (Exception e) {
        }
    }

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
        //processRequest(request, response);
        response.setContentType("application/json");
    
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
            System.out.println(str);
        }
        JSONObject js;
        String id=null,title=null,author=null;
        try {
            js = new JSONObject(sb.toString());
            id = js.getString("id");
            title = js.getString("title");
            author = js.getString("author");
        } catch (JSONException ex) {
            Logger.getLogger(
                    SignUp_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(id);
        try {
            
            Connection con = DBConnection.initDatabase();
            PreparedStatement st = con.prepareStatement("insert into book values(?, ?, ?)"); 
            st.setString(1, id);
            st.setString(2, title);
            st.setString(3, author);
            st.executeUpdate(); 
  
            // Close all the connections 
            st.close(); 
            con.close(); 
            PrintWriter out = response.getWriter(); 
            out.println("<html><body><b>Successfully Inserted"
                        + "</b></body></html>"); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
