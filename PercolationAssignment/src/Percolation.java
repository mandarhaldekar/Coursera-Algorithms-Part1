
public class Percolation {

	private int N;  //Grid size
	private int openSites[];
	private WeightedQuickUnionUF weightedQuickUnionUF;
	private int numberOfOpenSites;
	
	
	/**
	 * Constructor that initialises NxN grid
	 * @param N
	 */
	public Percolation(int N){
		numberOfOpenSites = 0;
		this.N = N;
		weightedQuickUnionUF = new WeightedQuickUnionUF(N*N+2);
		openSites = new int[N*N]; //Two extra for virtual top and bottom and one extra as we are starting from N=1
		for(int i = 0; i < N*N ; i++)
				openSites[i] = 0;
		
	}
	
	/**
	 * Check if site fits in NxN model
	 * @param i: row
	 * @param j: column
	 * @return True is indices are between 1 and N
	 */
	private boolean isWithinBounds(int i, int j){
		
		return ((i >= 1 && i <= N) && (j >= 1 && j <= N));
	}
	
	/**
	 * Checks bounds of indices, opens site(i,j) and connects it with adjacent open sites
	 * @param i : row number
	 * @param j : column number
	 */
	public void open(int i, int j){
		
		if(!isWithinBounds(i, j))
			throw new IndexOutOfBoundsException("Indices limit exceeded");
		
		if(openSites[xyToOneD(i, j)] == 1) //Already open
			return;
		else{
			
			//open it 
			openSites[xyToOneD(i, j)] = 1;
			
			//increment number of open sites
			numberOfOpenSites++;
			
			//Connect to adjacent open sites
			if( isWithinBounds(i+1, j) && openSites[xyToOneD(i+1, j)] == 1){
				weightedQuickUnionUF.union(xyToOneD(i, j), xyToOneD(i+1, j));
			}
			
			if(isWithinBounds(i-1, j) && openSites[xyToOneD(i-1, j)] == 1 ){
				weightedQuickUnionUF.union(xyToOneD(i-1, j), xyToOneD(i, j));
			}
			
			if( isWithinBounds(i, j-1) && openSites[xyToOneD(i, j-1)] == 1){
				weightedQuickUnionUF.union(xyToOneD(i, j), xyToOneD(i, j-1));
			}
			
			if(isWithinBounds(i, j+1) && openSites[xyToOneD(i, j+1)] == 1 ){
				weightedQuickUnionUF.union(xyToOneD(i, j+1), xyToOneD(i, j));
			}
			
			//Checking for top and bottom row
			if(i == 1){
				weightedQuickUnionUF.union(xyToOneD(i, j), N*N);  //connect to virtual top
			}
			if(i == N){
				weightedQuickUnionUF.union(xyToOneD(i, j), N*N+1);  //connect to virtual top
			}
		}
			
		
	}
	
	/**
	 * Checks if site(i,j) is an open site
	 * @param i
	 * @param j
	 * @return True if site(i,j) is open otherwise False
	 */
	public boolean isOpen(int i, int j){
		if(openSites[xyToOneD(i, j)] == 1)
			return true;
		return false;
	}
	
	/**
	 * Checks if site(i,j) is a Full site
	 * @param i
	 * @param j
	 * @return True if site(i,j) is Full, otherwise False
	 */
	public boolean isFull(int i,int j){
		int toprow = 1;
		for(int k = 1;k <= N; k++){
			if(openSites[xyToOneD(toprow,k)] == 1 && weightedQuickUnionUF.connected(xyToOneD(i, j),xyToOneD(toprow, k)))
				return true;
		}
		return false;
	}
	
	/**
	 *Checks if system percolates.  
	 * @return True if system percolates otherwise false 
	 */
	public boolean percolates(){
		//if virtual top and bottom are connected then system percolates
		return weightedQuickUnionUF.connected(N*N, N*N+1);
	}
	/**
	 * converts 2-D index to 1-D
	 * @param i : row
	 * @param j : column
	 * @return corresponding 1-D index
	 */
	private int xyToOneD(int i, int j){
		return (i-1) * N + (j - 1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
