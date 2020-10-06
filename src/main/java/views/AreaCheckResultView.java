package views;

import models.Entry;

public class AreaCheckResultView implements View<Entry> {


    @Override
    public String build(Entry model) {
        StringBuilder sb = new StringBuilder();
        sb.append(wrap(model.getIp()));
        sb.append(wrap(model.getX()));
        sb.append(wrap(model.getY()));
        sb.append(wrap(model.getR()));
        sb.append(wrap(model.calculate()));
        return sb.toString();
    }

    private String wrap(Object s) {
        return "<td>" + s.toString() + "</td>\n";
    }
}
