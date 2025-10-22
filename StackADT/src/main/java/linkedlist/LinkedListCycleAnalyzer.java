package linkedlist;

import support.CycleInfo;
import support.LLNode;

public class LinkedListCycleAnalyzer<T> {
    public static <T> CycleInfo detectCycleInfo(LLNode<T> head) {

        if (head == null) {
            return new CycleInfo(-1, 0);
        }

        LLNode<T> slow = head;
        LLNode<T> fast = head;

        //cycle
        while (fast != null && fast.getLink() != null) {
            slow = slow.getLink();           // move one
            fast = fast.getLink().getLink(); // move two

            if (slow == fast) {
                //Find cycle length
                int cycleLength = 1;
                LLNode<T> temp = slow.getLink();
                while (temp != slow) {
                    cycleLength++;
                    temp = temp.getLink();
                }

                // cycle entry index
                LLNode<T> ptr1 = head;
                LLNode<T> ptr2 = slow;
                int entryIndex = 0;
                while (ptr1 != ptr2) {
                    ptr1 = ptr1.getLink();
                    ptr2 = ptr2.getLink();
                    entryIndex++;
                }

                return new CycleInfo(entryIndex, cycleLength);
            }
        }

        // No cycle found
        return new CycleInfo(-1, 0);
    }
}