package Controller;

import Model.Customer;
import Severce.CustomerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns= "/Customers")
public class CustomerServlet extends HttpServlet {
    CustomerDAO cd=new CustomerDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "create":
                showSave(request,response);
                break;
            case "delete":
                showDelete(request, response);
                break;
            case "edit":
                showEdit(request,response);
            default:
                showList(request,response);
        }
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        Customer customer=cd.finById(id);
        RequestDispatcher rd=request.getRequestDispatcher("Customer/edit.jsp");
        request.setAttribute("dsCanSua",customer);
        rd.forward(request,response);
    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            cd.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Customer> customerList = cd.finAll();
        request.setAttribute("danhSach", customerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Customer/List.jsp");
        dispatcher.forward(request, response);
    }


    private void showSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd=request.getRequestDispatcher("Customer/create.jsp");
        rd.forward(request,response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd=request.getRequestDispatcher("Customer/List.jsp");
        List<Customer> customertList= cd.finAll();
        request.setAttribute("danhSach",customertList);
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "create":
                saveCustomer(request,response);
                break;
            case "edit":
                try {
                    editCustomer(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                showList(request,response);

        }
    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id= Integer.parseInt(request.getParameter("id"));
        String name= request.getParameter("name");
        int age= Integer.parseInt(request.getParameter("age"));
       Customer customer=new Customer(id,name,age);
       cd.edit(customer);
        response.sendRedirect("/Customers");


    }

    private void saveCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        int age= Integer.parseInt(request.getParameter("age"));
        try {
            cd.add(new Customer(id,name,age));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/Customers");
    }

}
