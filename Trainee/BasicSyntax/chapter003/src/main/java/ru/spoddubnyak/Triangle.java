package ru.spoddubnyak;

public class Triangle {
    public Point a;
    public Point b;
    public Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point getA() {
        return this.a;
    }

    public Point getB() {
        return this.b;
    }

    public Point getC() {
        return this.c;
    }

    // forward area of the triangle - the formula of Heron
    public double area() {
        double sideTriangleAB = this.getA().distanceTo(this.getB());
        double sideTriangleBC = this.getB().distanceTo(this.getC());
        double sideTriangleAC = this.getA().distanceTo(this.getC());
        double semiperimeterTriangle = (sideTriangleAB + sideTriangleAC + sideTriangleBC) / 2;
        double areaTriangle = Math.sqrt(semiperimeterTriangle * (semiperimeterTriangle - sideTriangleAB) * (semiperimeterTriangle - sideTriangleBC) * (semiperimeterTriangle - sideTriangleAC));
        if (areaTriangle == 0)
            throw new IllegalArgumentException("It is impossible to calculate the area of a triangle.");
        return areaTriangle;
    }
}