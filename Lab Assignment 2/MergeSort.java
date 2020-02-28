/*
 * Name: Jonic Mecija, SeungJun Jeon
 * 
 * CSULB
 * CECS 328: Data Structures and Algorithms
 * Lab Programming Assignment #2
 * 
 * Due Date: March 1st, 2020
 * 
 * Description: This project implements the merge sort algorithm using arrays.
 * 
 */

public class MergeSort {
	
	// variable to count number of comparisons
    static int count= 0;

    // sorting function 
    static int sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            int middle = (l + r) / 2;
            sort(arr, l, middle); 	  	// sort the left hand side
            sort(arr, middle + 1, r); 	// sort right hand side
            count +=merge(arr, l, middle, r); // merge the two arrays
        }
        return count;
    }

    static int merge(int arr[], int leftStart, int middle, int rightEnd)
    {
        // Find sizes of two sub-arrays to be merged
        int n1 = middle - leftStart + 1;
        int n2 = rightEnd - middle;
        int count = 0;
        
        // Create temporary arrays for Left and Right values
        int LeftArr[] = new int [n1];
        int RightArr[] = new int [n2];

        // copy data into temporary arrays (Left and Right values)
        for (int i = 0; i < n1; i++)
        {
            LeftArr[i] = arr[leftStart + i];
        }

        for (int j = 0; j < n2; j++)
        {
            RightArr[j] = arr[middle + 1 + j];
        }
        // Data is now split into left and right values
        int counter=0;

        // **** Merge the temporary arrays **** //


        // Initial indexes of first and second sub-arrays
        int j = 0, i = 0, k = leftStart;
        
        while (j < LeftArr.length && i < RightArr.length)
        {
            if (LeftArr[j] <= RightArr[i]) {
                arr[k] = LeftArr[j];
                j++;
            }//Compares two values then adds the lower value to the vector
            else {
                arr[k] = RightArr[i];
                i++;
            }//If the left value is greater than add the right value to the vector
            counter++; k++;
        }
        count +=counter;
        
        //Adds the remaining elements to the vector
        while (j < LeftArr.length) {
            arr[k] = LeftArr[j];
            j++; k++;
        }
        
       
        while (i < RightArr.length) {
            arr[k] = RightArr[i];
            k++; i++;
        }//Adds the remaining elements to the vector

        return count;
    }

    // Code to print array
    static void printArray(int arr[])
    {
        int n = arr.length;

        for (int i = 0; i < n; i++)
        {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

    // Merge sort test
    public static void main(String args[])
    {
    	// test arrays
        //int arr[] = {4,5,1,7,3,3,8};
        //int arr[] = {5,5,5,5};
        //int arr[] = {1,2,1};
        //int arr[] = {1};
        //int arr[] = {1,2,3,4,5,6,7};
        int count;
        
        // takes in user input and parses data
        int arr[] = new int [args.length];
        for (int i = 0; i < args.length;i++)
        {
            arr[i] = Integer.parseInt(args[i]);
        }
        
        // display output 
        System.out.println("Given Array");
        printArray(arr);

        count = sort(arr, 0, arr.length-1);

        System.out.println("\nSorted array");
        printArray(arr);

        System.out.println("\nNumber of Comparisons: ");
        System.out.println(count);
    }

}