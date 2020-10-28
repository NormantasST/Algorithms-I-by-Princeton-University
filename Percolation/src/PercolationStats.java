import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_MULTIPLIER = 1.96;
    private final double[] percolationStats;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        // Checking for valid input
        if (trials <= 0)
            throw new IllegalArgumentException("Trials is under or equal to 0");
        else if (n <= 0)
            throw new IllegalArgumentException("grid size is under or equal to 0");

        percolationStats = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int[] permutationArray = StdRandom.permutation(n * n);
            int permutationIndex = 0;
            while (!percolation.percolates()) {
                int positionX = (permutationArray[permutationIndex] / n) + 1;
                int positionY = (permutationArray[permutationIndex] % n) + 1;
                percolation.open(positionX, positionY);
                permutationIndex++;
            }
            percolationStats[i] = (double) percolation.numberOfOpenSites() / (n * n);

        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolationStats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolationStats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_MULTIPLIER * stddev() / Math.sqrt(percolationStats.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_MULTIPLIER * stddev() / Math.sqrt(percolationStats.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats localPercolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.print("mean                    = ");
        System.out.println(localPercolationStats.mean());

        System.out.print("stddev                  = ");
        System.out.println(localPercolationStats.stddev());

        System.out.print("95% confidence interval = [");
        System.out.print(localPercolationStats.confidenceLo());
        System.out.print(", ");
        System.out.print(localPercolationStats.confidenceHi());
        System.out.println("]");

    }
}
