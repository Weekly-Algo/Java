import java.util.*;

class Solution {
    public int solution(int[] order) {
        int n = order.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int current = 1; // 메인 벨트에서 다음으로 내릴 상자 번호
        int count = 0;

        for (int i=0; i<n; i++) {
            int target = order[i];

            // 목표 번호가 아직 메인 벨트에 있다면, 목표까지 보조 벨트에 쌓기
            while (current <= target) {
                stack.push(current);
                current++;
            }

            // 보조 벨트(스택)의 맨 위가 목표와 같아야 실을 수 있음
            if (!stack.isEmpty() && stack.peek() == target) {
                stack.pop();
                count++;
            } else {
                break;
            }
        }

        return count;
    }
}