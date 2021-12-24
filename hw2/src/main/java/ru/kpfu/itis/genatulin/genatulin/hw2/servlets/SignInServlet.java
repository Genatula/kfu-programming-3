package ru.kpfu.itis.genatulin.genatulin.hw2.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.kpfu.itis.genatulin.genatulin.hw2.repositories.AbstractRepository;
import ru.kpfu.itis.genatulin.genatulin.hw2.entities.User;
import ru.kpfu.itis.genatulin.genatulin.hw2.repositories.UsersRepository;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SignInServlet", value = "/signin")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("incorrectCredentials", false);
        request.setAttribute("userDoesntExist", false);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            AbstractRepository<User> repository = UsersRepository.getInstance();
            if (repository.getEntry(email) == null) {
                request.setAttribute("userDoesntExist", true);
                request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
            }
            User user = repository.getEntry(email);
            if (!user.getPassword().equals(password)) {
                request.setAttribute("incorrectCredentials", true);
                request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/calculator");
    }
}
