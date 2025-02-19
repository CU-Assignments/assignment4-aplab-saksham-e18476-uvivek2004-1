code:-

  '''
import java.util.*;

public class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Step 1: Create a list of events (start and end points of buildings)
        List<int[]> events = new ArrayList<>();
        for (int[] building : buildings) {
            events.add(new int[] {building[0], building[2], 1});  // start of a building
            events.add(new int[] {building[1], building[2], -1}); // end of a building
        }

        // Step 2: Sort the events based on the x-coordinate
        // If two events have the same x-coordinate, the one with higher height should come first
        // and in case of the end event, lower height should come first
        events.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; // Sort by x-coordinate
            return a[2] == b[2] ? b[1] - a[1] : a[2] - b[2]; // Sort by height if x is same
        });

        // Step 3: Use a max-heap to keep track of current heights
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(0); // Add a dummy value to represent ground level

        // Step 4: Iterate through events and compute the skyline
        int prevMaxHeight = 0;
        for (int[] event : events) {
            int x = event[0];
            int height = event[1];
            int type = event[2];

            if (type == 1) { // start of a building
                maxHeap.add(height);
            } else { // end of a building
                maxHeap.remove(height);
            }

            int currentMaxHeight = maxHeap.peek();
            // If the maximum height changes, it's a key point in the skyline
            if (currentMaxHeight != prevMaxHeight) {
                result.add(Arrays.asList(x, currentMaxHeight));
                prevMaxHeight = currentMaxHeight;
            }
        }

        return result;
    }
}




  '''
