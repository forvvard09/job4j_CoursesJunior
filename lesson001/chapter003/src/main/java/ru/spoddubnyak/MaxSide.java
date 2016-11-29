package ru.spoddubnyak;

public class MaxSide {

    // We get a side of the triangle
    public double getMaxSide(Triangle triangle) {
        double sideAB = triangle.getA().distanceTo(triangle.getB());
        double sideBC = triangle.getB().distanceTo(triangle.getC());
        double sideAC = triangle.getA().distanceTo(triangle.getC());
        double[] sideArray = {sideAB, sideBC, sideAC};
        return findingMaxSide(sideArray);
    }

    // Finding the maximum side
    public double findingMaxSide(double[] sourceArray) {
        double maxSide = sourceArray[0];
        for (int i = 1; i < sourceArray.length; i++) {
            if (maxSide < sourceArray[i]) {
                maxSide = sourceArray[i];
            }
        }
        return maxSide;
    }
}