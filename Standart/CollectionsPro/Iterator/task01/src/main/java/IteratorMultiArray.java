import java.util.Iterator;

/**
 * Class implements an iterator for a two-dimensional array.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 21.04.2017
 */
public class IteratorMultiArray implements Iterator {

    private final int[][] values;
    private int index = 0;

    public IteratorMultiArray(final int[][] values) {
        this.values = values;

    }

    @Override
    public boolean hasNext() {
        return values[values.length].length < index;
    }

    @Override
    public Object next() {
        int result = 0;
        for (int i = 0; i < values.length; i++) {
            for(int j = 0; j < values[i].length; j++) {
                   result = values[i][j];
            }
        }
        return result;
    }
}
