import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];

        // 스택에는 아직 가격이 떨어지지 않은 시점의 인덱스를저장
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i=0; i<n; i++) {
            // 현재 가격이 스택 top 시점의 가격보다 낮다면,
            // top 시점은 지금(i초)에 가격이 떨어진 것이므로 기간을 확정
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx; // 몇 초 동안 가격이 떨어지지 않았는지
            }
            stack.push(i); // 현재 인덱스는 아직 답이 확정되지 않았으므로 스택에 저장
        }

        // 순회가 끝난 후 스택에 남은 인덱스들은 끝까지 가격이 떨어지지 않은 경우
        int lastIndex = n - 1;
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = lastIndex - idx;
        }

        return answer;
    }
}