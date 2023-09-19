class Rational {
    long numerator, denominator;

    class Illegal extends Exception {
        String reason;

        Illegal(String reason) {
            this.reason = reason;
        }
    }

    Rational() {
        // Default constructor, initialize to 0/1
        numerator = 0;
        denominator = 1;
    }

    Rational(long numerator, long denominator) throws Illegal {
        if (denominator == 0) {
            throw new Illegal("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplestForm();
    }

    private void simplestForm() {
        long computeGCD;
        computeGCD = GCD(Math.abs(numerator), denominator);
        numerator /= computeGCD;
        denominator /= computeGCD;
    }

    private long GCD(long a, long b) {
        if (a % b == 0) return b;
        else return GCD(b, a % b);
    }

    public void add(Rational x) {
        long newNumerator = (numerator * x.denominator) + (x.numerator * denominator);
        long newDenominator = (denominator * x.denominator);
        this.numerator = newNumerator;
        this.denominator = newDenominator;
        simplestForm();
    }

    public void subtract(Rational x) {
        long newNumerator = (numerator * x.denominator) - (x.numerator * denominator);
        long newDenominator = (denominator * x.denominator);
        this.numerator = newNumerator;
        this.denominator = newDenominator;
        simplestForm();
    }

    public void multiply(Rational x) {
        long newNumerator = numerator * x.numerator;
        long newDenominator = denominator * x.denominator;
        this.numerator = newNumerator;
        this.denominator = newDenominator;
        simplestForm();
    }

    public void divide(Rational x) throws Illegal {
        if (x.numerator == 0) {
            throw new Illegal("Cannot divide by zero");
        }
        long newNumerator = numerator * x.denominator;
        long newDenominator = denominator * x.numerator;
        this.numerator = newNumerator;
        this.denominator = newDenominator;
        simplestForm();
    }

    public boolean equals(Object x) {
        if (x instanceof Rational) {
            Rational other = (Rational) x;
            return this.numerator == other.numerator && this.denominator == other.denominator;
        }
        return false;
    }

    public int compareTo(Rational x) {
        // Compare two Rational numbers
        long diff = this.numerator * x.denominator - x.numerator * this.denominator;
        if (diff < 0) return -1;
        else if (diff > 0) return 1;
        else return 0;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        System.out.println("This is Rational class.");
        try {
            Rational r1 = new Rational(1, 2);
            Rational r2 = new Rational(3, 4);

            // Test the methods
            System.out.println("r1 = " + r1.toString());
            System.out.println("r2 = " + r2.toString());

            r1.add(r2);
            System.out.println("r1 + r2 = " + r1.toString());

            r1.subtract(r2);
            System.out.println("r1 - r2 = " + r1.toString());

            r1.multiply(r2);
            System.out.println("r1 * r2 = " + r1.toString());

            r1.divide(r2);
            System.out.println("r1 / r2 = " + r1.toString());

            System.out.println("r1 equals r2: " + r1.equals(r2));
            System.out.println("Comparison result: " + r1.compareTo(r2));
        } catch (Illegal e) {
            System.err.println("Illegal: " + e.reason);
        }
    }
}
