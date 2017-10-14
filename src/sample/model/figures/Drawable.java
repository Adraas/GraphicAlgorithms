package sample.model.figures;

public interface Drawable<T, D> {

    void draw(D coordinate);

    T getDots();
}
