package sudoku.problemdomain;

// Builds out the X and Y values
public class Coordinates {
    public final int x;
    public final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // equals comes from the Object class in Java, every class extends from Object
    // We want so store these coordinate objects in a hashmap
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    // Generates a unique identifier based on the x and y coordinates
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}