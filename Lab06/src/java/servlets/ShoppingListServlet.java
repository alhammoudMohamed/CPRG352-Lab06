package servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ShoppingListServlet extends HttpServlet {

    private List<String> items = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("items", items);

        String logout = request.getParameter("logout");
        if (logout != null) {

            session.invalidate();
            items = new ArrayList<String>();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);

        } else {

            if (session.getAttribute("username") != null) {
                String user = (String) session.getAttribute("username");

                request.setAttribute("username", user);

                //response.sendRedirect("shoppingList");
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String register = request.getParameter("register");
        String addButton = request.getParameter("addItem");
        String deleteButton = request.getParameter("deleteButton");

        HttpSession session = request.getSession();

        if (register != null) {
            String username = request.getParameter("username");
            boolean usernameNotEmpty = isValidInput(username);

            if (usernameNotEmpty) {

                session.setAttribute("username", username);
                response.sendRedirect("shoppingList");
            }

        } else if (addButton != null) {

            String itemField = request.getParameter("itemToAdd");
            boolean itemFieldNotEmpty = isValidInput(itemField);

            if (itemFieldNotEmpty) {

                // List<String> items = new ArrayList();
                //session.setAttribute("items", items);
                items.add(itemField);

                response.sendRedirect("shoppingList");
            } else {
                response.sendRedirect("shoppingList");
            }

        } else if (deleteButton != null) {

            //////
            String deletedValue = request.getParameter("item");
            items.remove(deletedValue);

            response.sendRedirect("shoppingList");

        }
    }

    private boolean isValidInput(String input) {
        boolean valid = false;
        if (input.equals("") || input == null) {
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

}
