package chapter1_fundamentals.C1_2_DataAbsType;

/******************************************************************************
 *  Compilation:  javac Vector.java
 *  Execution:    java Vector
 *  Dependencies: StdOut.java
 *
 *  Implementation of a vector of real numbers.
 *
 *  This class is implemented to be immutable: once the client program
 *  initialize a Vector, it cannot change any of its fields
 *  (d or data[i]) either directly or indirectly. Immutability is a
 *  very desirable feature of a data type.
 *
 *  % java Vector
 *     x     = [ 1.0 2.0 3.0 4.0 ]
 *     y     = [ 5.0 2.0 4.0 1.0 ]
 *     z     = [ 6.0 4.0 7.0 5.0 ]
 *   10z     = [ 60.0 40.0 70.0 50.0 ]
 *    |x|    = 5.477225575051661
 *   <x, y>  = 25.0
 *
 *
 *  Note that Vector is also the name of an unrelated Java library class
 *  in the package java.util.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;

/**
 * Euclidean vector
 * <p>
 * Created by SylvanasSun on 2017/3/3.
 */
public class Vector {


    private int d;               // dimension of the vector
    private double[] data;       // array of vector's components


    /**
     * Initializes a d-dimensional zero vector.
     *
     * @param d the dimension of the vector
     */
    public Vector(int d) {
        this.d = d;
        data = new double[d];
    }

    /**
     * Initializes a vector from either an array or a vararg list.
     * The vararg syntax supports a constructor that takes a variable number of
     * arugments such as Vector x = new Vector(1.0, 2.0, 3.0, 4.0).
     *
     * @param a the array or vararg list
     */
    public Vector(double... a) {
        d = a.length;

        // defensive copy so that client can't alter our copy of data[]
        data = new double[d];
        for (int i = 0; i < d; i++)
            data[i] = a[i];
    }

    /**
     * Returns the length of this vector.
     *
     * @return the dimension of this vector
     * @deprecated Replaced by {@link #dimension()}.
     */
    @Deprecated
    public int length() {
        return d;
    }

    /**
     * Returns the dimension of this vector.
     *
     * @return the dimension of this vector
     */
    public int dimension() {
        return d;
    }

    /**
     * Returns the do product of this vector with the specified vector.
     *
     * @param that the other vector
     * @return the dot product of this vector and that vector
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public double dot(Vector that) {
        if (this.d != that.d) throw new IllegalArgumentException("Dimensions don't agree");
        double sum = 0.0;
        for (int i = 0; i < d; i++)
            sum = sum + (this.data[i] * that.data[i]);
        return sum;
    }

    /**
     * Returns the magnitude of this vector.
     * This is also known as the L2 norm or the Euclidean norm.
     *
     * @return the magnitude of this vector
     */
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * Returns the Euclidean distance between this vector and the specified vector.
     *
     * @param that the other vector
     * @return the Euclidean distance between this vector and that vector
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public double distanceTo(Vector that) {
        if (this.d != that.d) throw new IllegalArgumentException("Dimensions don't agree");
        return this.minus(that).magnitude();
    }

    /**
     * Returns the sum of this vector and the specified vector.
     *
     * @param that the vector to add to this vector
     * @return the vector whose value is {@code (this + that)}
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public Vector plus(Vector that) {
        if (this.d != that.d) throw new IllegalArgumentException("Dimensions don't agree");
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++)
            c.data[i] = this.data[i] + that.data[i];
        return c;
    }

    /**
     * Returns the difference between this vector and the specified vector.
     *
     * @param that the vector to subtract from this vector
     * @return the vector whose value is {@code (this - that)}
     * @throws IllegalArgumentException if the dimensions of the two vectors are not equal
     */
    public Vector minus(Vector that) {
        if (this.d != that.d) throw new IllegalArgumentException("Dimensions don't agree");
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++)
            c.data[i] = this.data[i] - that.data[i];
        return c;
    }

    /**
     * Returns the ith cartesian coordinate.
     *
     * @param i the coordinate index
     * @return the ith cartesian coordinate
     */
    public double cartesian(int i) {
        return data[i];
    }

    /**
     * Returns the scalar-vector product of this vector and the specified scalar
     *
     * @param alpha the scalar
     * @return the vector whose value is {@code (alpha * this)}
     * @deprecated Replaced by {@link #scale(double)}.
     */
    @Deprecated
    public Vector times(double alpha) {
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++)
            c.data[i] = alpha * data[i];
        return c;
    }

    /**
     * Returns the scalar-vector product of this vector and the specified scalar
     *
     * @param alpha the scalar
     * @return the vector whose value is {@code (alpha * this)}
     */
    public Vector scale(double alpha) {
        Vector c = new Vector(d);
        for (int i = 0; i < d; i++)
            c.data[i] = alpha * data[i];
        return c;
    }

    /**
     * Returns a unit vector in the direction of this vector.
     *
     * @return a unit vector in the direction of this vector
     * @throws ArithmeticException if this vector is the zero vector
     */
    public Vector direction() {
        if (this.magnitude() == 0.0) throw new ArithmeticException("Zero-vector has no direction");
        return this.times(1.0 / this.magnitude());
    }


    /**
     * Returns a string representation of this vector.
     *
     * @return a string representation of this vector, which consists of the
     * the vector entries, separates by single spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < d; i++)
            s.append(data[i] + " ");
        return s.toString();
    }

    /**
     * Unit tests the {@code Vector} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        double[] xdata = {1.0, 2.0, 3.0, 4.0};
        double[] ydata = {5.0, 2.0, 4.0, 1.0};
        Vector x = new Vector(xdata);
        Vector y = new Vector(ydata);

        StdOut.println("   x       = " + x);
        StdOut.println("   y       = " + y);

        Vector z = x.plus(y);
        StdOut.println("   z       = " + z);

        z = z.times(10.0);
        StdOut.println(" 10z       = " + z);

        StdOut.println("  |x|      = " + x.magnitude());
        StdOut.println(" <x, y>    = " + x.dot(y));
        StdOut.println("dist(x, y) = " + x.distanceTo(y));
        StdOut.println("dir(x)     = " + x.direction());

    }


}
