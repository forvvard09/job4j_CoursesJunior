package ru.spoddubnyak;


import java.util.*;

/**
 * The class StructureDivisions implements the sorting of the directory of subdivisions in descending order and ascending.
 * The units themselves are the input data..
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 02.10.2017
 */
public class StructureDivisions {
    /**
     * property -  storage of root units.
     */
    private List<String> rootDivision = new ArrayList<>();
    /**
     * property -  storage of units.
     */
    private Map<String, HashSet> mapDivisions = new HashMap<>();

    /**
     * property -  string divider separator.
     */
    private String separator = "/";

    /**
     * Constructor it creates a new object StructureDivisions and generates unit data.
     *
     * @param stringArray input string Array containing a list of units
     */
    public StructureDivisions(String... stringArray) {
        this.init(stringArray);
    }

    /**
     * Getter for separator.
     *
     * @return separator string divider separator
     */
    public String getSeparator() {
        return this.separator;
    }

    /**
     * Setter for separator.
     *
     * @param separator - property separator
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    /**
     * Method method sorts ascending or descending a list of divisions.
     *
     * @param toEnd - if true - sorting by ascending, else - descending
     * @return List -   sorted list
     */
    public List sorting(boolean toEnd) {
        Collections.sort(rootDivision);
        return toEnd ? sortingToEnd(rootDivision) : sortingToStart(rootDivision);
    }

    /**
     * Method sorting by ascending .
     *
     * @param rootDivision - List root division
     * @return List -   sorted list
     */
    private List sortingToEnd(List<String> rootDivision) {
        List<String> result = new ArrayList<>();
        for (String s : rootDivision) {
            List<String> sortDivision = new ArrayList<>(getSet(s));
            Collections.sort(sortDivision);
            result.add(s);
            result.addAll(sortDivision);
        }
        return result;
    }

    /**
     * Method sorting by descending.
     *
     * @param rootDivision - List root division
     * @return List -   sorted list
     */
    private List sortingToStart(List<String> rootDivision) {
        Collections.reverse(rootDivision);
        List<String> result = new ArrayList<>();
        for (String s : rootDivision) {
            List<String> sortDivision = new ArrayList<>(getSet(s));
            Collections.sort(sortDivision, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int result = 0;
                    if (o1.length() != o2.length()) {
                        result = 1;
                    } else if (o1.compareTo(o2) > 0) {
                        result = -1;
                    }
                    return result;
                }
            });
            result.add(s);
            result.addAll(sortDivision);
        }
        return result;
    }

    /**
     * Method method sorts ascending or descending a list of divisions.
     *
     * @param arraysString - generates a list of subdivisions from the resulting array of strings
     */
    private void init(String... arraysString) {
        List<String> listArrayString = new ArrayList<>(Arrays.asList(arraysString));
        for (String s : listArrayString) {
            List<String> units = new ArrayList<>(Arrays.asList(s.split(getSeparator())));
            String element = "";
            for (int i = 0; i < units.size(); i++) {
                if (!mapDivisions.containsKey(units.get(0))) {
                    createNewNode(units.get(0));
                    i--;
                } else {
                    element = (0 == i) ? String.format("%s%s", element, units.get(i)) : String.format("%s%s%s", element, getSeparator(), units.get(i));
                    if (!element.equals(units.get(0))) {
                        getSet(units.get(0)).add(element);
                    }
                }
            }

        }
    }

    /**
     * Method add root division in rootDivision and mapDivisions.
     *
     * @param division root division
     */
    private void createNewNode(String division) {
        HashSet<String> hash = new HashSet<>();
        rootDivision.add(division);
        mapDivisions.put(division, hash);
    }

    /**
     * Method return Hash set for root divisions by string.
     *
     * @param keyOfMap root division in mapDivisions
     * @return value HashSet by key in mapDivisions
     */
    private HashSet getSet(String keyOfMap) {
        HashSet result = null;
        if (mapDivisions.containsKey(keyOfMap)) {
            result = mapDivisions.get(keyOfMap);
        }
        return result;
    }
}