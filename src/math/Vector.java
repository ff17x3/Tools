package math;

/**
 * Created by Florian on 05-May-17.
 */
public class Vector {
	public final double c[] = new double[3];

	public Vector() {

	}

	public Vector(double x, double y, double z) {
		set(x, y, z);
	}

	public Vector set(double x, double y, double z) {
		c[0] = x;
		c[1] = y;
		c[2] = z;
		return this;
	}

	public Vector set(Vector v) {
		c[0] = v.c[0];
		c[1] = v.c[1];
		c[2] = v.c[2];
		return this;
	}


	public Vector add(Vector a) {
		c[0] += a.c[0];
		c[1] += a.c[1];
		c[2] += a.c[2];
		return this;
	}

	public Vector subtract(Vector a) {
		c[0] -= a.c[0];
		c[1] -= a.c[1];
		c[2] -= a.c[2];
		return this;
	}


	public Vector multiplyScalar(double a) {
		c[0] *= a;
		c[1] *= a;
		c[2] *= a;
		return this;
	}

	public Vector normalize() {
		return multiplyScalar(1 / getLength());
	}

	public double getLength() {
		return Math.sqrt(Math.pow(c[0], 2) + Math.pow(c[1], 2) + Math.pow(c[2], 2));
	}

	public static Vector CP(Vector a, Vector b) {
		Vector v = new Vector();
		v.c[0] = a.c[1] * b.c[2] - a.c[2] * b.c[1];
		v.c[1] = a.c[2] * b.c[0] - a.c[0] * b.c[2];
		v.c[2] = a.c[0] * b.c[1] - a.c[1] * b.c[0];
		return v;
	}

	public static double dotProduct(Vector a, Vector b) {
		return b.c[0] * a.c[0] + b.c[1] * a.c[1] + b.c[2] * a.c[2];
	}

	public static Vector subtract(Vector a, Vector b) {
		return new Vector(a.c[0] - b.c[0], a.c[1] - b.c[1], a.c[2] - b.c[2]);
	}

	public static Vector multiplyScalar(Vector v, double a) {
		return new Vector(v.c[0] * a, v.c[1] * a, v.c[2] * a);
	}


}
