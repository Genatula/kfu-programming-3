package ru.kpfu.itis.genatulin.hw2.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.kpfu.itis.genatulin.hw2.entities.User;
import ru.kpfu.itis.genatulin.hw2.storage.AbstractStorage;
import ru.kpfu.itis.genatulin.hw2.storage.UsersStorage;

import java.io.IOException;

@WebServlet(name = "ShowAllUsersServlet", value = "/all")
public class ShowAllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getServletContext().getRealPath("/WEB-INF/users.json");
        AbstractStorage<User> storage = new UsersStorage(filename);

        if (storage.getAll() == null) {
            request.setAttribute("noUsersExist", true);
        }
        else {
            request.setAttribute("noUsersExist", false);
            request.setAttribute("users", storage.getAll());
        }

        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/all.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
