package org.app.utils.calc;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class FFT {

    private static Complex[] fft(Complex[] a, boolean invert) {
        int n = a.length;
        if (n == 1) return a;

        Complex[] a0 = new Complex[n / 2], a1 = new Complex[n / 2];
        for (int i = 0, j = 0; i < n; i += 2, ++j) {
            a0[j] = a[i];
            a1[j] = a[i + 1];
        }
        fft(a0, invert);
        fft(a1, invert);

        double ang = 2 * PI / n * (invert ? -1 : 1);
        Complex w = new Complex(1, 0), wn = new Complex(cos(ang), sin(ang));
        Complex[] y = new Complex[n];
        for (int i = 0; i < n / 2; ++i) {
            y[i] = a0[i].plus(w.times(a1[i]));
            y[i + n / 2] = a0[i].minus(w.times(a1[i]));
            if (invert) {
                y[i] = y[i].scale(0.5);
                y[i + n / 2] = y[i + n / 2].scale(0.5);
            }
            w = w.times(wn);
        }
        return y;
    }

    public static Complex[] fft(Complex[] x) {
        return fft(x, false);
    }


    // compute the inverse FFT of x[], assuming its length n is a power of 2
    public static Complex[] ifft(Complex[] x) {
        return fft(x, true);
    }
}