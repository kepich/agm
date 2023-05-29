package org.app.method.impl;

public class Complex {
    public double r;
    public double i;

    public Complex(double r, double i) {
        this.r = r;
        this.i = i;
    }

    public Complex(double r) {
        this.r = r;
        this.i = 0;
    }

    public Complex add(Complex c) {
        return new Complex(r + c.r, i + c.i);
    }

    public Complex sub(Complex c) {
        return new Complex(r - c.r, i - c.i);
    }

    public Complex mul(Complex c) {
        return new Complex(r * c.r - i * c.i, r * c.i + i * c.r);
    }
}
