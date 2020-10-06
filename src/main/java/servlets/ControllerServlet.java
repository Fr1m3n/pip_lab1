package servlets;

import models.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Сервлет, определяющий тип запроса, и, в зависимости от того, содержит ли запрос информацию о координатах точки и радиусе,
 * делегирующий его обработку одному из перечисленных ниже компонентов.
 *
 * Все запросы внутри приложения должны передаваться этому сервлету (по методу GET или POST в зависимости от варианта задания),
 * остальные сервлеты с веб-страниц напрямую вызываться не должны.
 */
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("x") != null &&
            req.getParameter("y") != null &&
            req.getParameter("r") != null) {
            getServletContext().getRequestDispatcher("/check").forward(req, resp);
        } else {
            if (getServletContext().getAttribute("entries") == null) {
                getServletContext().setAttribute("entries", new ArrayList<Entry>());
            }
            req.setAttribute("entries", getServletContext().getAttribute("entries"));
            getServletContext().getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
        }
    }

}
