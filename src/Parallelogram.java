public class Parallelogram {
    private double sideA;      
    private double sideB;   
    private double angle;     

    public Parallelogram(double sideA, double sideB, double angle) {
        if (sideA <= 0 || sideB <= 0) {
            throw new IllegalArgumentException("Sides must be greater than zero");
        }
        if (angle <= 0 || angle >= 180) {
            throw new IllegalArgumentException("Angle must be between 0 and 180 degrees");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.angle = angle;
    }

    public double calculatePerimeter() {
        return 2 * (sideA + sideB);
    }

    //S = a * b * sin(α)
    public double calculateArea() {
        double angleInRadians = Math.toRadians(angle);
        return sideA * sideB * Math.sin(angleInRadians);
    }

    public int compareTo(Parallelogram other) {
        double thisArea = this.calculateArea();
        double otherArea = other.calculateArea();
        
        double epsilon = 0.0001; 
        
        if (Math.abs(thisArea - otherArea) < epsilon) {
            return 0; 
        } else if (thisArea > otherArea) {
            return 1;
        } else {
            return -1; 
        }
    }

    public boolean isSimilarTo(Parallelogram other) {
        double epsilon = 0.0001; 
        
        boolean anglesEqual = Math.abs(this.angle - other.angle) < epsilon;
        
        double ratio1 = this.sideA / other.sideA;
        double ratio2 = this.sideB / other.sideB;
        
        boolean similarVariant1 = Math.abs(ratio1 - ratio2) < epsilon && anglesEqual;
        
        double ratio3 = this.sideA / other.sideB;
        double ratio4 = this.sideB / other.sideA;
        boolean similarVariant2 = Math.abs(ratio3 - ratio4) < epsilon && anglesEqual;
        
        return similarVariant1 || similarVariant2;
    }

    public void printInfo() {
        System.out.printf("Parallelogram:\n" +
                     "Side A: %.2f\n" +
                     "Side B: %.2f\n" +
                     "Angle: %.2f\n" +
                     "Perimeter: %.2f\n" +
                     "Area: %.2f\n\n",
                     sideA, sideB, angle, calculatePerimeter(), calculateArea());
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getAngle() {
        return angle;
    }

    public static void main(String[] args) {
        Parallelogram p1 = new Parallelogram(5, 8, 60);
        p1.printInfo();
        
        Parallelogram p2 = new Parallelogram(10, 16, 60);
        p2.printInfo();
        
        Parallelogram p3 = new Parallelogram(6, 9, 45);
        p3.printInfo();
        
        System.out.println("\nComparing parallelograms");
        int comparison = p1.compareTo(p2);
        if (comparison == 0) {
            System.out.println("Parallelogram 1 and Parallelogram 2 have equal areas");
        } else if (comparison > 0) {
            System.out.println("Parallelogram 1 is larger than Parallelogram 2");
        } else {
            System.out.println("Parallelogram 1 is smaller than Parallelogram 2");
        }
        
        System.out.println("\nSimilarity check");
        if (p1.isSimilarTo(p2)) {
            System.out.println("Parallelogram 1 and Parallelogram 2 are similar");
        } else {
            System.out.println("Parallelogram 1 and Parallelogram 2 are NOT similar");
        }
        
        if (p1.isSimilarTo(p3)) {
            System.out.println("Parallelogram 1 and Parallelogram 3 are similar");
        } else {
            System.out.println("Parallelogram 1 and Parallelogram 3 are NOT similar");
        }
        
        System.out.println("\nRectangle (special case)");
        Parallelogram rectangle = new Parallelogram(4, 6, 90);
        rectangle.printInfo();
    }
}