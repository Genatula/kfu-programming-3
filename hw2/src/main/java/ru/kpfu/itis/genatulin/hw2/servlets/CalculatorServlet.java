package ru.kpfu.itis.genatulin.hw2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/calculator")
public class CalculatorServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String firstParam = request.getParameter("a");
        String secondParam = request.getParameter("b");
        request.setAttribute("isValid", true);
        request.setAttribute("isDividerValid", true);
        request.setAttribute("isNumber", true);
        request.setAttribute("welcome", false);

        if (!request.getParameterMap().containsKey("a") && !request.getParameterMap().containsKey("b")) {
            request.setAttribute("welcome", true);
        }
        else if (!request.getParameterMap().containsKey("a") || !request.getParameterMap().containsKey("b")) {
            request.setAttribute("isValid", false);
        }
        else {
            try {
                double firstValue = Double.parseDouble(firstParam);
                try {
                    double secondValue = Double.parseDouble(secondParam);
                    if (secondValue == 0.0) {
                        request.setAttribute("isDividerValid", false);
                    }
                    else {
                        double result = firstValue / secondValue;
                        request.setAttribute("result", result);
                    }
                }
                catch (NumberFormatException e) {
                    request.setAttribute("isNumber", false);
                }
            }
            catch (NumberFormatException e) {
                request.setAttribute("isNumber", false);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/calculator.jsp").forward(request, response);
    }

    public void destroy() {
    }
}