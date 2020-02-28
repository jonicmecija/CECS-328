/*
    CECS 328 Project 1 Insertion Sort Using Doubly Linked List
    Name: Seungjun Jeon
          Jonic Mecija

    Due Date: 2/9/19

 */


public class InsertionSort {
    static class Node //Node Class
    {
        String word;
        Node next, prev;
    }

    static Node Push(Node head_ref, String new_word)// Function used to Push strings into Doubly Linked List
    {
        Node new_node = new Node();
        new_node.word = new_word;
        new_node.next = (head_ref);
        new_node.prev = null;
        if ((head_ref) != null)
            head_ref.prev = new_node;
        head_ref = new_node;
        return head_ref;
    }

    static void Insertion_Sort(Node head_ref, int n) {
        Node Current; //Temp Node that points to the Head
        Node i; //Node that points to the string at I
        int j, ptr;
        Current = head_ref;
        ptr = 0;
        for (j = 2; j < n + 1; j++) {
            while (ptr < j - 1) {
                Current = Current.next; //Shifts to the Next Node until it matches with J
                ptr++;
            }
            ptr = 0;  //Resets the ptr value for next For Loop iteration
            String Key = Current.word;  //Stores the string for "J" to Key , Key = A[j]
            i = Current.prev; // I points to "J-1"
            while (i != null & (i.word.compareTo(Key) > 0)) {
                i.next.word = i.word; // A[i+1] = A[i]
                i = i.prev; // i = i - 1
            }
            i.next.word = Key;// A[i+1] = Key

            Current = head_ref; //Reset the Current for the next For Loop Iteration
        }
    }

    public static void main(String[] args) {
        Node head = null; // Empty Doubly Linked List

        if (args.length > 20) //Error Checking for more than 20 words
            System.out.print("Too many words inputted\n");
        else
            for(int i = 0; i < args.length; i++) //i < args.length
            {
                 head = Push(head, args[i]);

            }
            Insertion_Sort(head,args.length); // Sorts the Doubly Linked List by Insertion Sort

            while(head != null) // Displays the words in sorted order
            {
                System.out.print(head.word + " ");
                head = head.next;
            }
    }
}