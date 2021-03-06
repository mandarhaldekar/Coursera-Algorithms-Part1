public class PercolationStats {

	private int N; // dimension of grid is NxN
	private int T; // number of computations
	private double percolationThreshold[];

	/**
	 * perform T independent computational experiments on an N-by-N grid
	 * 
	 * @param N
	 *            : Grid dimension
	 * @param T
	 *            : number of computations
	 */
	public PercolationStats(int N, int T) {

		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException(
					"Zero or negative values not accepted");

		this.N = N;
		this.T = T;
		percolationThreshold = new double[T];

		for (int i = 0; i < T; i++) {
			int openSites = 0; // To keep track of open sites
			Percolation percolation = new Percolation(N);
			while (!percolation.percolates()) {
				int x = StdRandom.uniform(1, N + 1);
				int y = StdRandom.uniform(1, N + 1);

				if (!percolation.isOpen(x, y)) {
					openSites++;
					percolation.open(x, y);
				}

			}
			// percolation probability
			percolationThreshold[i] = (double) openSites / (N * N);

		}

	}

	/**
	 * Calculates mean of percolation threshold
	 * 
	 * @return mean percolation threshold
	 */
	public double mean() {
		return StdStats.mean(percolationThreshold);
		// return this.mean;

	}

	/**
	 * Calculates standard deviation of percolation threshold
	 * 
	 * @return standard deviation of percolation threshold
	 */
	public double stddev() {
		return StdStats.stddev(percolationThreshold);
		// return this.stddev;
	}

	/**
	 * Calculates lower bound of 95% confidence interval for the percolation
	 * threshold
	 * 
	 * @return lower bound of 95% confidence interval for the percolation
	 *         threshold
	 */
	public double confidenceLo() {
		return StdStats.mean(percolationThreshold) - 1.96
				* StdStats.stddev(percolationThreshold) / Math.sqrt(T);
		// return this.lowConfidenceInterval;
	}

	/**
	 * Calculates upper bound of 95% confidence interval for the percolation
	 * threshold
	 * 
	 * @return upper bound of 95% confidence interval for the percolation
	 *         threshold
	 */
	public double confidenceHi() {
		return StdStats.mean(percolationThreshold) + 1.96
				* StdStats.stddev(percolationThreshold) / Math.sqrt(T);
		// return this.highConfidenceInterval;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(args[0]);

		int T = Integer.parseInt(args[1]);
		System.out.println("N : " + N + "\tT :" + T);
		PercolationStats percolationStats = new PercolationStats(N, T);

		System.out.println("Mean ::" + percolationStats.mean());
		System.out.println("Standard Deviation ::" + percolationStats.stddev());
		System.out.println("Low Confidence Interval ::"
				+ percolationStats.confidenceLo());
		System.out.println("Higher Confidenc Interval ::"
				+ percolationStats.confidenceHi());

	}

}
