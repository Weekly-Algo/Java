import java.util.*;

class Solution {
    public int solution(int[] topping) {

        int ans = 0;

        int[] leftCount = new int[10001];
        int[] rightCount = new int[10001];

        int leftType = 0;
        int rightType = 0;

        // 처음에는 모든 토핑이 오른쪽에 있다고 생각
        // 처음 들어온 토핑 개수에 맞춰서 ++
        for (int t : topping) {
            if (rightCount[t] == 0) {
                rightType++;
            }

            rightCount[t]++;
        }

        // 하나씩 왼쪽으로 옮기면서 양쪽 토핑 종류 수 비교
        // 오른쪽 조각도 최소 1개의 토핑은 있어야 하니까
        // 범위를 topping 길이 - 1로 설정
        for (int i = 0; i < topping.length - 1; i++) {
            int curr = topping[i];

            if (leftCount[curr] == 0) {
                leftType++;
            }

            leftCount[curr]++;

            // 왼쪽에 해당 토핑 옮겼으니 오른쪽에서는 빼기
            rightCount[curr]--;

            // 해당 토핑 값에 해당하는 오른쪽 타입도 하나 빼기
            if (rightCount[curr] == 0) {
                rightType--;
            }

            // 왼쪽, 오른쪽 토핑 개수가 같으면 방법 찾은겨
            if (leftType == rightType) {
                ans++;
            }
        }

        return ans;
    }
}