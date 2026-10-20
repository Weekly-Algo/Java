import java.util.*;

class LV2_타겟_넘버 {
    public int solution(int[] numbers, int target) {
        int answer = dfs(numbers, target, 0, 0);
        return answer;
    }

    int dfs(int[] numbers, int target, int depth, int sum) {
        // 다 돌았을 때 값이 같으면 1 아니면 0
        if(depth == numbers.length) {
            if(sum == target)
                return 1;
            else
                return 0;
        }

        // 각 항을 더함
        int plus = dfs(numbers, target, depth + 1, sum + numbers[depth]);

        // 각 항을 뺌
        int minus = dfs(numbers, target, depth + 1, sum - numbers[depth]);

        return plus + minus;
    }
}