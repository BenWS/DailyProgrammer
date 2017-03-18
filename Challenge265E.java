//5-24-16: Troubleshooting Out of Bounds Exception in generatePerms method
//5-25-16: Conflicting indices between swapElements method and generatePerms method - managed to get
//method to work by changing "<" to "<="
//5-26-16: Continuing to debug application

package dailyprogrammer;

import java.util.Arrays;

public class Challenge265E {
	
	public static void main(String[] args) {
		
		int[] myArray = {1,2,3};
		
//		PermutationGenerator pg = new PermutationGenerator();
//		
//		int[][] returnArray = pg.generatePerms(myArray);
//		
//		System.out.println(returnArray[2]);
		
		PermutationGenerator pg = new PermutationGenerator();
		
		int[][] resultArray = pg.generatePerms(myArray);
		
		for (int i = 0; i < resultArray.length;i++) {
			System.out.println(Arrays.toString(resultArray[i]));
		}
		
	}

}

class PermutationGenerator {
	
	public int[] swapElements(int[] inputArray, int index_1, int index_2) {
		int[] outputArray = inputArray.clone();
		
		int a = inputArray[index_1];
		int b = inputArray[index_2];
		
		outputArray[index_1] = b;
		outputArray[index_2] = a;
		
		return outputArray;
	}
	
	public int[][] generatePerms(int[] orderedList) {
		
		//initializing permList
		int[][] permList;
		int[][] tempArray = {orderedList};
		int sizeFactor;
		
		for (int i = 0; i < orderedList.length;i++) {
			sizeFactor = (orderedList.length - i + 1);
			permList = tempArray.clone();
			tempArray = new int[sizeFactor*tempArray.length][];
			tempArray = this.generateSwaps(permList, i);
		}
		
		return tempArray;
	}
	
	public int[][] generateSwaps (int[][] inputArrays, int j) {		
		
		int numberOfSwaps = inputArrays[0].length - j;
		int numberOfArrays = inputArrays.length;
		
		int[][] returnArray = new int[numberOfSwaps * numberOfArrays][];
		
		int k = 0;
		
		//for the arrays within inputArrays
		for (int l = 0; l < numberOfArrays; l++) {
			//swap ith element with jth element
			for (int i = j; i < numberOfSwaps; i++) {
				returnArray[k] = this.swapElements(inputArrays[l], i, j);
				k++;
			}
		}
		
		return returnArray;	
	}
}
