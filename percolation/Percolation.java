package percolation;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    char[][] grid;
    int openSites;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.openSites = 0;
        this.grid = new char[n][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                this.grid[i][j] = 'F';
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(!isOpen(row, col)) {
            grid[row][col] = 'O';
            openSites++;
        }
        
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (grid[row][col] == 'O') {
            return true;
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (grid[row][col] == 'F') {
            return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {

    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation sites = new Percolation(10);
        while (!sites.percolates()) {
            sites.open(StdRandom.uniformInt(10), StdRandom.uniformInt(10));
        }
    }
}
