package views;

import models.Entry;

import java.util.List;
import java.util.stream.Collectors;

public class AreaCheckResultsTableView implements View<List<Entry>> {
    @Override
    public String build(List<Entry> model) {
        StringBuilder sb = new StringBuilder("<table>");
        sb.append("<tr>")
                .append("<th>IP</th>\n")
                .append("<th>X</th>\n")
                .append("<th>Y</th>\n")
                .append("<th>R</th>\n")
                .append("<th>Result</th>\n")
                .append("</tr>")
                .append(
                        model.stream()
                                .map(e -> new AreaCheckResultView().build(e))
                                .map(e -> "<tr>" + e + "</tr>")
                                .collect(Collectors.joining())
                )
                .append("</table>");
//                .append("<a href='/main'>Return</a>");
//        System.out.println(sb.toString());
        return sb.toString();
    }
}
