package servlets;

import models.Entry;
import views.AreaCheckResultView;
import views.AreaCheckResultsTableView;
import views.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервлет, осуществляющий проверку попадания точки в область на координатной плоскости и формирующий HTML-страницу с результатами проверки.
 *
 * Должен обрабатывать все запросы, содержащие сведения о координатах точки и радиусе области.
 */
public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Double x = Double.valueOf(req.getParameter("x"));
            Double y = Double.valueOf(req.getParameter("y"));
            Double r = Double.valueOf(req.getParameter("R"));
            Entry entry = new Entry(x, y, r, req.getRemoteAddr());
            View<List<Entry>> checkResultView = new AreaCheckResultsTableView();
            resp.getWriter().print(checkResultView.build(List.of(entry)));
            List<Entry> entries = (List<Entry>)getServletContext().getAttribute("entries");
            if (entries == null) {
                entries = new ArrayList<>();
                getServletContext().setAttribute("entries", entries);
            }
            entries.add(entry);
        } catch (NumberFormatException e) {
            resp.getWriter().print("ERROR EMAE");
        }

    }


}
