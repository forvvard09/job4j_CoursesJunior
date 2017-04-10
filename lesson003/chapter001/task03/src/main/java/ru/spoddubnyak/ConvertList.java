package ru.spoddubnyak;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Test class сonverts a two-dimensional array into a collection of List back.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 03.04.2017
 */
public class ConvertList {

    /**
     * Method converts a two-dimensional array into a collection List.
     *
     * @param array two-dimensional array for converting
     * @return arrayToList result List
     */
    public List<Integer> toList(int[][] array) {
        int sizeList = array.length * array[0].length;
        List<Integer> arrayToList = new ArrayList<>(sizeList);
        for (int[] i : array) {
            for (int j : i) {
                arrayToList.add(j);
            }
        }
        return arrayToList;
    }

    /**
     * Method converts the collection List into a two-dimensional array consisting of rows lines.
     *
     * @param list collection List
     * @param rows number of lines in a two-dimensional array
     * @return array result array
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int lengthList = list.size();
        int column;
        if (lengthList % rows != 0) {
            column = lengthList / rows + 1;
        } else {
            column = lengthList / rows;
        }
        int[][] array = new int[rows][column];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                if (index < lengthList) {
                    array[i][j] = list.get(index);
                    index++;
                } else {
                    array[i][j] = 0;
                }
            }
        }
        return array;
    }

    /**
     * Method converts the collection List into a two-dimensional array consisting of rows lines with an iterator.
     *
     * @param list collection List
     * @param rows number of lines in a two-dimensional array
     * @return array result array
     */
    public int[][] toArrayByIterator(List<Integer> list, int rows) {
        int lengthList = list.size();
        int column;
        if (lengthList % rows != 0) {
            column = lengthList / rows + 1;
        } else {
            column = lengthList / rows;
        }
        int[][] array = new int[rows][column];
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                if (iterator.hasNext()) {
                    array[i][j] = iterator.next();
                } else {
                    array[i][j] = 0;
                }
            }
        }
        return array;
    }

    /**
     * Method converts the collection List<int[]> in colection List<Integer>.
     *
     * @param list List<int[]>
     * @return list List<Inteher>
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> arrayToList = new ArrayList<>();
        for (int[] array : list) {
            for (int elementArray : array) {
                arrayToList.add(elementArray);
            }
        }
        return arrayToList;
    }
}