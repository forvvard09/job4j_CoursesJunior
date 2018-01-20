package ru.spoddubnyak;

/**
 * Class sphere - square.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 15.01.2017
 */
public class Square implements Strategy {

    @Override
    public String pic(int side) {
        int width = 0;
        String shape = "";
        while (width < side) {
            for (int i = 0; i < side; i++) {
                shape = String.format("%s%s", shape, "[]");
            }
            shape = shape + System.getProperty("line.separator");
            width++;
        }
        return shape;
    }

    @Override
    public void draw(String shape) {
        System.out.println(shape);
    }
}
