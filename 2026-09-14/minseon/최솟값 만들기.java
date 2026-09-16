import java.util.*;

class Solution {
    public int solution(int []A, int []B) {

        // 배열수만큼 곱한거 더했을 때
        // 가장 최소값이 나오도록

        // A배열은 오름차순 (작은 값부터)
        // B배열은 내림차순 (큰 값부터)

        Arrays.sort(A);
        Arrays.sort(B);

        // 결과값
        int ans = 0;

        // 배열 돌면서 곱한거 더하기
        for (int i=0; i < A.length; i++) {
            // A는 작은 값부터
            // B는 큰 값부터 사용
            ans += A[i] * B[B.length - 1 - i];
        }

        // 결과 반환
        return ans;
    }

}