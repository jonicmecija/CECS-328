/*
 * Name: Jonic Mecija, SeungJun Jeon
 * 
 * CSULB
 * CECS 328: Data Structures and Algorithms
 * Lab Programming Assignment #3
 * 
 * Due Date: April 12th, 2020
 * 
 * Description: This project outputs a max heap array using a buildMaxHeap algorithm.
 * 		Implementation is as described in Introduction to Algorithms, Thomas H. Cormen.
 * 		This project does not output a heap sorted array.
 */

public class BuildMaxHeap {
	
	// "floats" down the heap making sure sub-tress of A[i] obey maxHeapify
	static void maxHeapify(int arr[], int i, int heapSize) {
		int left = left(i);
		int right = right(i);
		int largest = i;
		
		// checks to see the element in left node is greater than arr[i]
		if (left < heapSize && arr[left] > arr[i]) {
			largest = left;
		}
		else { largest = i;}
		
		// checks to see the element in right node is greater than arr[largest]
		if (right < heapSize && arr[right] > arr[largest]) {
			largest = right;
		}
		
		// if the largest isnt the same as i, exchange the node elements
		if (largest != i) {
			int exchange = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = exchange; 
            maxHeapify(arr, largest, heapSize); // run maxHeapify on the sub-trees of those nodes
		}		
	}
	
	// builds a maxHeap from a given array
	public static void buildMaxHeap(int arr[]) {
		
		// half of the nodes don't have children, so don't perform maxHeapify on those 
		for(int i = arr.length/2-1; i >= 0; i--) {
			maxHeapify(arr,i,arr.length);
		}
	}
    
	// calculate left heap node of index i
	static int left(int i) { return 2*i+1; }
	
	// calculate right heap node of index i
	static int right(int i) { return 2*i+2; }
	
	// Prints an array
    static void printArray(int arr[]) {
    	
        int n = arr.length; 
        for (int i=0; i<n; i++) { 
            System.out.print(arr[i]+" "); 
        }
        System.out.println(); 
    } 
    
    // Calculates the height of the heap
    // n-elements heap has height floor(lg n)
    static int findHeight(int n) { 
        return (int)Math.floor(Math.log(n)/Math.log(2)); 
    } 
    
	public static void main(String args[]) {
		
		// test arrays
		
		// int arr[] = {2,3,5,8,13,21,34,55}; 
		// int arr[] = {4,5,1,7,3,3,8};
        // int arr[] = {5,5,5,5};
        
        // takes in user input and parses data
        int arr[] = new int [args.length];
        for (int i = 0; i < args.length;i++)
        {
            arr[i] = Integer.parseInt(args[i]);
        }
        
        int n = arr.length;
        int height = findHeight(n);
        
        // Display output 
        System.out.println("Inputted array is"); 
        printArray(arr); 
        
        buildMaxHeap(arr);
  
        System.out.println("Max Heap Array:"); 
        printArray(arr); 
        
        System.out.println("Height: "+ height);
        
	}

}
