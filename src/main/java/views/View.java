package views;

public interface View<T> {

    String build(T model);

}
