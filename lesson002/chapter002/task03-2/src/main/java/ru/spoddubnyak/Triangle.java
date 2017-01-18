package ru.spoddubnyak;

/**
 * Class sphere - triangle.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 15.01.2017
 */
public class Triangle implements Strategy {
    @Override
    public String pic(int side) {
        int width = 0;
        String shape = "";

        while (width < side) {
            for (int i = 0; i < side - width; i++) {
                shape = String.format("%s%s", shape, " ");
            }
            for (int i = 0; i < width * 2 + 1; i++) {
                shape = String.format("%s%s", shape, "*");

            }
            shape = String.format("%s%s", shape, System.getProperty("line.separator"));
            width++;
        }
        return shape;
    }

    @Override
    public void draw(String shape) {
        System.out.println(shape);
    }
}
