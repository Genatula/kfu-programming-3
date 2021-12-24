package ru.kpfu.itis.genatulin.genatulin.hw2.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.kpfu.itis.genatulin.genatulin.hw2.repositories.AbstractRepository;
import ru.kpfu.itis.genatulin.genatulin.hw2.entities.User;
import ru.kpfu.itis.genatulin.genatulin.hw2.repositories.UsersRepository;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ShowAllUsersServlet", value = "/all")
public class ShowAllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AbstractRepository<User> repository = null;
        try {
            repository = UsersRepository.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            if (repository.getAllEntries() == null) {
                request.setAttribute("noUsersExist", true);
            }
            else {
                request.setAttribute("noUsersExist", false);
                request.setAttribute("users", repository.getAllEntries());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/all.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
