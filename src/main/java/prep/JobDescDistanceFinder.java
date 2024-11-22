package prep;

import java.util.*;

public class JobDescDistanceFinder {
    Map<String, List<Integer>> indexMap;
    public static void main(String[] args) {
        JobDescDistanceFinder finder = new JobDescDistanceFinder(Arrays.asList("the", "java", "skill", "is", "important", "for", "java", "development"));
        int distance = finder.distance1("the", "important");
        int dist1 = finder.distance1("important","java");
        int dist2 = finder.distance1("java","does not exist");
        System.out.println(distance);
        System.out.println(dist1);
        System.out.println(dist2);
    }

//   two pointer; time: O(m + n), space: O(n)
    public JobDescDistanceFinder(List<String> words) {
        indexMap = new HashMap<>();
        int i = 0;
        for(String word : words) {
            indexMap.computeIfAbsent(word, k -> new ArrayList<>()).add(i++);
        }
    }
    public int distance(String wordOne, String wordTwo) {
        if(!indexMap.containsKey(wordOne) || !indexMap.containsKey(wordTwo))
            return -1;
        List<Integer> positionsOne = indexMap.get(wordOne);
        List<Integer> positionsTwo = indexMap.get(wordTwo);
        int i = 0, j = 0;
        int minDistance = Integer.MAX_VALUE;
//        both lists are sorted, so can be dealt with in a single pass
        while(i < positionsOne.size() && j < positionsTwo.size()) {
            int posOne = positionsOne.get(i);
            int posTwo = positionsTwo.get(j);
            minDistance = Math.min(minDistance, Math.abs(posOne - posTwo));
            if(posOne < posTwo)
                i++;
            else
                j++;
        }
        return minDistance;
    }

//
    public int distance1(String wordOne, String wordTwo) {
        if(!indexMap.containsKey(wordOne) || !indexMap.containsKey(wordTwo))
            return -1;
        List<Integer> positionsOne = indexMap.get(wordOne);
        List<Integer> positionsTwo = indexMap.get(wordTwo);
        int minDistance = Integer.MAX_VALUE;
        for(int pos : positionsOne) {
            minDistance = Math.min(minDistance, Math.abs(pos - binarySearch(positionsTwo, pos)));
        }
        return minDistance;
    }

    private int binarySearch(List<Integer> posList, int val) {
        int left = 0;
        int right = posList.size() - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(posList.get(mid) < val)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return posList.get(left);
    }
}

/*
/* This class will be given a list of words (might be tokenized from a paragraph of job text), and will provide a method
that takes two words and returns the shortest distance (in words) between those two words in the provided text.
Example:
JobDescDistanceFinder finder = new JobDescDistanceFinder(Arrays.asList("the", "java", "skill", "is", "important", "for", "java", "development"));
assert(finder.distance("important", "the") == 4);
assert(finder.distance("important", "java") == 2);
assert(finder.distance("java", "doesntExist") == -1);
 */

/*
Two-Pointer vs. Binary Search Approach
Two-Pointer Approach
Time Complexity: O(m + k), where m and k are the lengths of the position lists for wordOne and wordTwo.
How It Works: Since both position lists are sorted, we can use two pointers to scan through both lists in a single pass, calculating the minimum distance as we go. This is efficient and guarantees finding the shortest distance with only one traversal through each list.
Binary Search Approach
Time Complexity: O(m * log k) or O(k * log m), depending on which list is iterated and which is searched.
How It Works: For each position in one list (say, positionsOne), we can perform a binary search in the other list (positionsTwo) to find the closest element. For each of the m elements in positionsOne, binary searching in positionsTwo takes O(log k) time. This results in O(m * log k). Similarly, we could reverse the roles, leading to O(k * log m).
 */