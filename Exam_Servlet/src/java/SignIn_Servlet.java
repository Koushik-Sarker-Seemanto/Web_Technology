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
import org.json.*;

/**
 *
 * @author user
 */
@WebServlet(urlPatterns = {"/signin"})
public class SignIn_Servlet extends HttpServlet {

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
            out.println("<title>Servlet SignIn_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignIn_Servlet at " + request.getContextPath() + "</h1>");
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
        String input_phone=request.getParameter("phone");
        String input_pass=request.getParameter("password");
        PreparedStatement st;
        try {
            Connection con = DBConnection.initDatabase();
            if(input_pass!=null && input_phone!=null){
                st = con.prepareStatement("select * from user where phone="+input_phone+"&& password="+input_pass);
            }
            else{
                st=null;
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Missing phone & password");
            }
                
//            else
//                st = con.prepareStatement("select * from user");
        ResultSet rs;
        rs = st.executeQuery();
        List<User>result = new ArrayList<>();
         while(rs.next()){
            String name =rs.getString("name");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            String password = rs.getString("password");
             System.out.println(name);
            User user = new User(); // Creating a user object to fill with user data (I imagine that you have a user class in your model)
            user.setName(name);
            user.setPhone(phone);
            user.setEmail(email);
            user.setPassword(password);
            //Add the retrived user to the list
            result.add(user);

        }
         if(result.size() == 1){
            response.setStatus(HttpServletResponse.SC_OK);
            }
            else{
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Wrong email password");
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
        String phone = null,password=null;
        try {
            js = new JSONObject(sb.toString());
            phone = js.getString("phone");
            password = js.getString("password");
        } catch (JSONException ex) {
            Logger.getLogger(
                    SignUp_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        String input_phone=phone;
        String input_pass=password;
        PreparedStatement st;
        try {
            Connection con = DBConnection.initDatabase();
            if(input_pass!=null && input_phone!=null){
                st = con.prepareStatement("select * from user where phone="+input_phone+"&& password="+input_pass);
            }
            else{
                st=null;
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Missing phone & password");
            }
                
//            else
//                st = con.prepareStatement("select * from user");
        ResultSet rs;
        rs = st.executeQuery();
        List<User>result = new ArrayList<>();
         while(rs.next()){
            String name =rs.getString("name");
            phone = rs.getString("phone");
            String email = rs.getString("email");
            password = rs.getString("password");
            System.out.println(name);
            User user = new User(); // Creating a user object to fill with user data (I imagine that you have a user class in your model)
            user.setName(name);
            user.setPhone(phone);
            user.setEmail(email);
            user.setPassword(password);
            //Add the retrived user to the list
            result.add(user);

        }
         if(result.size() == 1){
            response.setStatus(HttpServletResponse.SC_OK);
            }
            else{
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Wrong email password");
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
