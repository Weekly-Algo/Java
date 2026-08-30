import java.util.*;
// 1부터 n 까지
// LIFO 구조
class Solution {
    public int solution(int[] order) {
        int n = order.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int box = 1;
        int answer = 0;

        for (int pick : order) {
            while ((stack.isEmpty() || stack.peek() != pick) && box <= n) {
                stack.push(box);
                box++;
            }

            if (!stack.isEmpty() && stack.peek() == pick) {
                stack.pop();
                answer++;
            } else {
                break;
            }
        }

        return answer;
    }
}