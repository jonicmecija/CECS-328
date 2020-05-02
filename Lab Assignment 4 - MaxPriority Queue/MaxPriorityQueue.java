/*
 * Name: Jonic Mecija, SeungJun Jeon
 * 
 * CSULB
 * CECS 328: Data Structures and Algorithms
 * Lab Programming Assignment #4
 * 
 * Due Date: May 1st, 2020
 * 
 * Description: This project implements a max priority queue using max heaps.
 *  	Implementation is as described in Introduction to Algorithms, Thomas H. Cormen.
 */


import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MaxPriorityQueue {
	
	static void maxHeapify(List<Integer> arr, int i, int heapSize) {
		int left = 2*i+1;;
		int right = 2*i+2;;
		int largest = i;
		
		// checks to see the element in left node is greater than arr[i]
		if (left < heapSize && arr.get(left) > arr.get(i)) {
			largest = left;
		}
		else { largest = i;}
		
		// checks to see the element in right node is greater than arr[largest]
		if (right < heapSize && arr.get(right) > arr.get(largest)) {
			largest = right;
		}
		
		// if the largest isnt the same as i, exchange the node elements
		if (largest != i) {
			int exchange = arr.get(i); 
            arr.set(i, arr.get(largest));
            arr.set(largest, exchange); 
            maxHeapify(arr, largest, heapSize); // run maxHeapify on the sub-trees of those nodes
		}		
	}
	
	// builds a maxHeap from a given array
	public static void buildMaxHeap(List<Integer> arr) {
		
		// half of the nodes don't have children, so don't perform maxHeapify on those 
		for(int i = arr.size()/2-1; i >= 0; i--) {
			maxHeapify(arr,i,arr.size());
		}
	}
	
	// returns the element of int arr with the largest key
	public static int maximum(List<Integer> arr) {
		return arr.get(0);
	}
	
	// removes and returns the element of arr with the largest key
	public static int extractMax(List<Integer> arr) {
		
		// error checking
		if(arr.size() <= 0) {
			System.out.println("Error. Heap underflow.");
		}
		
		// set max value to return
		int max = arr.get(0);
		
		// set first element to equal the last element
		arr.set(0, arr.get(arr.size()-1));
		
		//decrement heap size
		int heapSize = arr.size() - 1;
		
		// run maxHeapify on heap (arr)
		maxHeapify(arr,0,heapSize);

		arr.remove(arr.size()-1);

		return max;
		
	}
	
	// add node with minimum value
	// call heap increaseKey with newKey at the new node
	public static List<Integer> insert(List<Integer> arr, int newKey) {
		int newHeapSize = arr.size()+1;

		arr.add(Integer.MIN_VALUE);
		return increaseKey(arr, newHeapSize-1, newKey);
	}
	
	public static List<Integer> increaseKey(List<Integer> arr, int indexOfCurrent, int newKey) {
		// output error if new key < old key
		
		int currentKey = arr.get(indexOfCurrent);
		if (newKey < currentKey) {
			System.out.println("Error. New key is less than old key.");
		}
		else {
				arr.set(indexOfCurrent,newKey);
				
				//checking if parent is less than child, if so then switch
				while(indexOfCurrent > 0 && (arr.get(parent(indexOfCurrent)) < arr.get(indexOfCurrent))) {
					int temp = arr.get(parent(indexOfCurrent));
					arr.set(parent(indexOfCurrent), arr.get(indexOfCurrent));
					arr.set(indexOfCurrent, temp);
					indexOfCurrent = parent(indexOfCurrent);
				}
				System.out.print("Outputted Max Heap: ");
				printList(arr);
			} 
		
		return arr;
	}
	
	public static int parent(int i) {
		return (i-1)/2;
	}
	public static void menu() {
		
		System.out.println("Choose from the following options: \n");
		System.out.print("1. Insert"
				+ "\n2. Maximum"
				+ "\n3. Extract-Max"
				+ "\n4. Increase-Key"
				+ "\n5. Exit\n\n\n");
	}
    public static void printList(List<Integer> arr) {
        for(int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i)+" ");
        }
        System.out.println(); 

    }    
	
    
    
    
    
    public static void main(String[] args) {
        
        // prompt user for input then format from string to List
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an array of integers separated by spaces: ");
        String input = scanner.nextLine();     
        String [] separate = input.split(" ");    
        int [] intArr = new int[separate.length];       
        for(int i = 0; i<separate.length; i++){        
            intArr[i] = Integer.parseInt(separate[i]);
        }
        List<Integer> nums = new ArrayList<>(intArr.length);
        for(int i : intArr) {
			nums.add(Integer.valueOf(i));
		} 
        
 
		// buildMaxHeap of inputted array
		buildMaxHeap(nums);
        System.out.print("Outputted Max Heap: ");
        printList(nums);
        
        // display menu and take input
        menu();
		Scanner in = new Scanner(System.in);
		int sel = in.nextInt();
		
		// continuous display of menu
		while (sel != 5) {
			
			if (sel == 1)
				{ 	  
					System.out.println("Input the integer you want inserted: ");
					int newkey = in.nextInt();
					System.out.print("Outputted Max Heap: ");
					insert(nums,newkey);
					printList(nums);
				}
			
			else if (sel == 2)
				{
					int max = maximum(nums);
					System.out.println("Maximum value is: " + max);
				}
			
			else if (sel == 3)
				{
					int extractmax = extractMax(nums);
					System.out.println("Maximum value is: " + extractmax);
					System.out.print("Outputted Max Heap: ");
					printList(nums);
				}
			
			else if (sel == 4) {
					System.out.print("Input the index of the node you want to increase: ");
					int index = in.nextInt();
					System.out.print("Input the new value: ");
					int value = in.nextInt();
					increaseKey(nums,index,value);
				}
			
			else{break;}
			menu();
			sel = in.nextInt();
		}
		in.close();
		scanner.close();
	}
}
