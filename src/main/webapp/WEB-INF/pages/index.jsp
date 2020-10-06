<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Entry" %>
<%@ page import="java.util.List" %>
<%@ page import="views.View" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Лабка по ПИПку</title>
    <link rel="stylesheet" type="text/css" href="resources/css/header.css">
    <link rel="stylesheet" type="text/css" href="resources/css/grid.css">
    <link rel="stylesheet" type="text/css" href="resources/css/main.css">
    <jsp:useBean id="table" class="views.AreaCheckResultsTableView" scope="page"/>
</head>
<body>
<div class="header">
    <div>Девятилов Роман Александрович</div>
    <div>P3212</div>
    <div>3143</div>
</div>
<div class="holder">
<%--    <%!--%>
<%--        final String ENTRIES_ATTRIBUTE_NAME() {--%>
<%--            return "entries"; // костыль?????--%>
<%--        }--%>
<%--    %>--%>
<%--    <%--%>
<%--        if (pageContext.getAttribute(ENTRIES_ATTRIBUTE_NAME()) == null) {--%>
<%--            pageContext.setAttribute(ENTRIES_ATTRIBUTE_NAME(), new ArrayList<Entry>());--%>
<%--        }--%>
<%--    %>--%>
    <table>
        <tr class="grid-row">
            <td class="image grid-cell">
                <canvas id="canvas"></canvas>
            </td>
            <td class="input-form grid-cell">
                <form action="check" method="get">

                    <div class="input-group">
                        <label for="x-input">X</label>
                        <input type="text" name="x" id="x-input">
                    </div>
                    <div class="input-group">
                        <label for="y-input">Y</label>
                        <input type="text" name="y" id="y-input">
                    </div>

                    <div class="input-group input-radio-group">
                        <table>
                            <tr>
                                <td><span>R:</span></td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-radio">
                                        <input id="radio-1" type="radio" name="R" value="1" checked>
                                        <label for="radio-1">1</label>
                                    </div>
                                </td>
                                <td>
                                    <div class="form-radio">
                                        <input id="radio-2" type="radio" name="R" value="1.5">
                                        <label for="radio-2">1.5</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-radio">
                                        <input id="radio-3" type="radio" name="R" value="2">
                                        <label for="radio-3">2</label>
                                    </div>
                                </td>
                                <td>
                                    <div class="form-radio">
                                        <input id="radio-4" type="radio" name="R" value="2.5">
                                        <label for="radio-4">2.5</label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-radio">
                                        <input id="radio-5" type="radio" name="R" value="3">
                                        <label for="radio-5">3</label>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div class="form-buttons">
                        <button id="submit" type="submit">Отправить</button>
<%--                        <button id="reset" class="enabled-button">Очистить таблицу</button>--%>
                    </div>
                    <div id="error-message" class="result-failed">Некорректные данные</div>
                </form>
            </td>
        </tr>
        <tr class="grid-row">
            <td id="result-table" class="grid-row">
<%--                <%--%>
<%--                    System.out.println(pageContext.getAttribute(ENTRIES_ATTRIBUTE_NAME()));--%>
<%--                %>--%>
                <%= table.build((List<Entry>)getServletConfig().getServletContext().getAttribute("entries"))%>
            </td>
        </tr>
    </table>
</div>
<script src="resources/js/index.js"></script>
<script src="resources/js/canvas.js"></script>
</body>
</html>