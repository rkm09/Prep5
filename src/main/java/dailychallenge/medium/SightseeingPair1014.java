package dailychallenge.medium;

public class SightseeingPair1014 {
    public static void main(String[] args) {

    }

//    DP; time: O(n), space: O(n)
    public static int maxScoreSightseeingPair(int[] values) {
        int n = values.length, maxScore = 0;
        int[] maxLeftScore = new int[n];
//        the left score at the first index is just the value at the first index
        maxLeftScore[0] = values[0];
        for(int i = 1 ; i < n ; i++) {
            int currentRightScore = values[i] - i;
//            update the value of the right score with the value of the best left score seen so far and the current right score
            maxScore = Math.max(maxScore, maxLeftScore[i - 1] + currentRightScore);
            int currentLeftScore = values[i] + i;
            maxLeftScore[i] = Math.max(maxLeftScore[i - 1], currentLeftScore);
        }
        return maxScore;
    }
}

/*
You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i between them.
The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the sightseeing spots, minus the distance between them.
Return the maximum score of a pair of sightseeing spots.
Example 1:
Input: values = [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
Example 2:
Input: values = [1,2]
Output: 2

Constraints:
2 <= values.length <= 5 * 104
1 <= values[i] <= 1000
 */
