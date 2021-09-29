package ru.kpfu.itis.genatulin.hw2.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.kpfu.itis.genatulin.hw2.entities.User;
import ru.kpfu.itis.genatulin.hw2.storage.AbstractStorage;
import ru.kpfu.itis.genatulin.hw2.storage.UserAlreadyExistsException;
import ru.kpfu.itis.genatulin.hw2.storage.UsersStorage;
import ru.kpfu.itis.genatulin.hw2.validators.AbstractValidator;
import ru.kpfu.itis.genatulin.hw2.validators.EmailValidator;
import ru.kpfu.itis.genatulin.hw2.validators.PasswordValidator;

import java.io.IOException;

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("areFieldsEmpty", false);
        request.setAttribute("isEmailInvalid", false);
        request.setAttribute("isPasswordInvalid", false);
        request.setAttribute("userAlreadyExists", false);

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (email.length() == 0 || username.length() == 0 || password.length() == 0) {
            request.setAttribute("areFieldsEmpty", true);
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
        }
        else {
            AbstractValidator emailValidator = new EmailValidator();
            if (!emailValidator.validate(email)) {
                request.setAttribute("isEmailInvalid", true);
                request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            }
            else {
                AbstractValidator passwordValidator = new PasswordValidator();
                if (!passwordValidator.validate(password)) {
                    request.setAttribute("isPasswordInvalid", true);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
                }
                else {
                    String filename = request.getServletContext().getRealPath("/WEB-INF/users.json");
                    AbstractStorage<User> usersStorage = new UsersStorage(filename);
                    User user = new User(username, email, password);
                    try {
                        usersStorage.save(user);
                        response.sendRedirect(request.getContextPath() + "/calculator");
                    }
                    catch (UserAlreadyExistsException exception) {
                        request.setAttribute("userAlreadyExists", true);
                        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/signup.jsp");
                    }
                }
            }
        }
    }
}
