import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final String[][] percolationGrid;
    private final int size; // The actual size of the grid
    private final WeightedQuickUnionUF unionDataset;
    private int numberOfOpenSites = 0;

    public Percolation(int n) {
        // Constructor
        if (n <= 0)
            throw new IllegalArgumentException("Value is too small");

        size = n + 1;
        unionDataset = new WeightedQuickUnionUF(size * size);
        percolationGrid = new String[size][size];
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++) {
                percolationGrid[y][x] = "blocked";
            }


    }

    private void connectToOutside() {
        // Position (0,0) is used to check if all elements are connected
        for (int x = 1; x < size; x++) {
            int position = size + x;
            if (isOpen(1, x))
                unionDataset.union(0, position + size);

        }
    }

    private boolean inGrid(int row, int col) {
        return (row > 0 && row < size && col > 0 && col < size);
    }

    private void checkForValidArgument(int row, int col) {
        if (!inGrid(row, col))
            throw new IllegalArgumentException("Value is invalid");
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkForValidArgument(row, col);
        percolationGrid[row][col] = "open";
        numberOfOpenSites++;
        int currentPositionInDatabase = row * size + col;
        if (row == 1)
            unionDataset.union(0, size + col);

        if (inGrid(row + 1, col) && isOpen(row + 1, col))
            unionDataset.union(currentPositionInDatabase, (row + 1) * size + col);

        if (inGrid(row - 1, col) && isOpen(row - 1, col))
            unionDataset.union(currentPositionInDatabase, (row - 1) * size + col);

        if (inGrid(row, col + 1) && isOpen(row, col + 1))
            unionDataset.union(currentPositionInDatabase, row * size + col + 1);

        if (inGrid(row, col - 1) && isOpen(row, col - 1))
            unionDataset.union(currentPositionInDatabase, row * size + col - 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkForValidArgument(row, col);
        return percolationGrid[row][col].equals("open");
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkForValidArgument(row, col);
        return unionDataset.find(0) == unionDataset.find(row * size + col);
    }


    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        int bottomRow = percolationGrid.length - 1;
        for (int x = 1; x < percolationGrid[0].length; x++)
            if (isFull(bottomRow, x))
                return true;
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        System.out.println("Testing Percolation manually on a 2x2 grid");
        Percolation test = new Percolation(2);
        System.out.println("Running T1");
        if (test.percolates()) {
            System.out.println("Test 1: Does percolate");
        }
        test.open(1, 2);
        System.out.println("Running T2");
        if (test.percolates()) {
            System.out.println("Test 2: Does percolate");
        }
        test.open(1, 1);
        System.out.println("Running T3");
        if (test.percolates()) {
            System.out.println("Test 3: Does percolate");
        }
        test.open(2, 2); // Should percolate
        System.out.println("Running T4");
        if (test.percolates()) {
            System.out.println("Test 4: Does percolate");
        }

    }
}
