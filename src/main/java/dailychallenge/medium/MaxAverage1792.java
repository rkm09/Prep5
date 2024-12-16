package dailychallenge.medium;

import java.util.PriorityQueue;

public class MaxAverage1792 {
    public static void main(String[] args) {
        int[][] classes = {{1,2},{3,5},{2,2}};
        System.out.println(maxAverageRatio(classes, 2));
    }

//    max heap; time: O(nlogn), space: O(n)
    public static double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>((a,b) -> Double.compare(b[0],a[0]));
        for(int[] singleClass : classes) {
            int passes = singleClass[0];
            int totalStudents = singleClass[1];
            double gain = calculateGain(passes, totalStudents);
            maxHeap.offer(new double[]{gain, passes, totalStudents});
        }
//        distribute extra students
        while(extraStudents-- > 0 && !maxHeap.isEmpty()) {
            double[] current = maxHeap.poll();
            int passes = (int) current[1];
            int totalStudents = (int) current[2];
            maxHeap.offer(new double[] {
                    calculateGain(passes + 1, totalStudents + 1), passes + 1, totalStudents + 1
            });
        }
//        calculate the final average pass ratio
        double totalPassRatio = 0;
        while(!maxHeap.isEmpty()) {
            double[] current = maxHeap.poll();
            int passes = (int) current[1];
            int totalStudents = (int) current[2];
            totalPassRatio += (double) passes / totalStudents;
        }
//        don't forget the final division by size
        return totalPassRatio / classes.length ;
    }

//    compute the gain(impact) of adding an  extra student(be cautious of the brackets)
    private static double calculateGain(int passes, int totalStudents) {
        return (double) (passes + 1) / (totalStudents + 1) - (double) passes / totalStudents;
    }
}

/*
There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.
You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.
The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.
Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.
Example 1:
Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
Example 2:
Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485

Constraints:
1 <= classes.length <= 105
classes[i].length == 2
1 <= passi <= totali <= 105
1 <= extraStudents <= 105
 */

/*
Time complexity: O(nlogn)
Building the max heap: Inserting each class into the max heap takes O(logn) time per insertion, and since there are n classes, this step takes O(nlogn) time.
Distributing extra students: Each insertion and removal from the max heap takes O(logn) time. Since we perform this operation k⋅ times, this step takes O(k⋅logn) time.
Calculating the final average pass ratio: This involves iterating through the heap, which takes O(nlogn) time in the worst case.
Overall, the dominant factor is the initial heap construction and the distribution of extra students, leading to a time complexity of O(klogn+nlogn)=O(nlogn).
Space complexity: O(n)
The space complexity is determined by the max heap, which stores n elements (one for each class). Additionally, the lambda function and other local variables consume constant space.
 */