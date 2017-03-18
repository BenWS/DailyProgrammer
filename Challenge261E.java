package dailyprogrammer;

import java.util.Arrays;

public class Challenge261E {
	
	public static void main(String[] args) {
			
		int[] array1 = {8, 1, 6, 3, 5, 7, 4, 9, 2};
		int[] array2 = {2, 7, 6, 9, 5, 1, 4, 3, 8};
		int[] array3 = {3, 5, 7, 8, 1, 6, 4, 9, 2};
		int[] array4 = {8, 1, 6, 7, 5, 3, 4, 9, 2}; 
		
		MatrixProcessor mp = new MatrixProcessor();
		
		mp.outputResults(array1);
		mp.outputResults(array2);
		mp.outputResults(array3);
		mp.outputResults(array4);
		
	}

}

class MatrixProcessor {
	
	public int[][] produceMatrix (int[] inputArray) {
		//method for producing matrix from 1-dim array
		
		//get array size
		int matrixSize = Double.valueOf((Math.sqrt(inputArray.length))).intValue();
		
		//initiate matrix
		int[][] matrix = new int[matrixSize][matrixSize];
		
		for (int i = 0; i < inputArray.length;i++) {
				matrix[i%matrixSize][(matrixSize - 1) - i/matrixSize] = inputArray[i];
		}
		
		return matrix;
	}
	
	public boolean checkSums(int[][] inputMatrix) {
		//checking the sum of the rows, columns
		
		//matrix is magic until proven otherwise
		boolean isMagic = true;
		
		int sum = 0;
		
		//check columns
		for (int i = 0; i < inputMatrix[0].length;i++) {
			for (int j = 0; j < inputMatrix[0].length; j++) {
				sum = sum + inputMatrix[i][j];
			}	
			if (sum != 15) {isMagic = false;}
			sum =0;
		}
		
		//check rows
		for (int i = 0; i < inputMatrix[0].length;i++) {
			for (int j = 0; j < inputMatrix[0].length; j++) {
				sum = sum + inputMatrix[j][i];
			}
			if (sum != 15) {isMagic = false;}
			sum =0;
		}
		
		//check diagonal
		for (int i = 0; i < inputMatrix[0].length;i++) {
			sum = sum + inputMatrix[i][i];
		}
		if (sum != 15) {isMagic = false;}
		
		return isMagic;
		
	}
	
	public void outputResults(int[] inputArray) {
		
		
		//if inputArray yields Magic Matrix
		if (this.checkSums(this.produceMatrix(inputArray))) {
			System.out.println(Arrays.toString(inputArray) + " ==> true");
		} else {
			System.out.println(Arrays.toString(inputArray) + " ==> false");
		}
		
	}
}