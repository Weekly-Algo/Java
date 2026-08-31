import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int[] order) {
        Deque<Integer> stack = new ArrayDeque<>();
        int truck = 0;

        for (int i = 1;i <= order.length && truck < order.length;i++){

            if (order[truck] == i) {
                truck++;

                while (!stack.isEmpty()
                        && truck < order.length
                        && stack.peek() == order[truck]) {
                    stack.pop();
                    truck++;
                }
            } else {
                stack.push(i);
            }
        }

        return truck;
    }
}