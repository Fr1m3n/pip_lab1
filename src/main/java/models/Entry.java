package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Entry {

    private final Double x;
    private final Double y;
    private final Double r;

    private final String ip;

    private Boolean result;

    public Entry(Double x, Double y, Double r, String ip) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.ip = ip;
    }

    public boolean calculate() { // lazy calculation
        if (result == null) {
            result = check();
        }
        return result;
    }

    private boolean check() {
        if (x >= 0) {
            if (y >= 0) {
                return check1();
            } else {
                return check4();
            }
        } else {
            if (y > 0) {
                return check2();
            } else {
                return check3();
            }
        }
    }

    private boolean check1() {
        return Math.sqrt(x * x + y * y) <= r;
    }

    private boolean check2() {
        return y <= x / 2 + (r / 2);
    }

    private boolean check3() {
        return x == 0 && y >= -r ||
                y == 0 && x >= -r / 2 ||
                x >= -r / 2 && y >= -r;
    }

    private boolean check4() {
        return false;
    }

}
