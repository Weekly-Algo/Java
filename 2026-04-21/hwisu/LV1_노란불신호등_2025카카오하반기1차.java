class Solution {
    public int solution(int[][] signals) {
        // 신호등 주기의 최소공배수, 이 시간까지 없으면 안 겹침
        int maxTime = 1;
        
        for (int i = 0; i < signals.length; i++) {
            // 현재 신호등의 전체 주기 (초록 + 노랑 + 빨강)
            int cycle = signals[i][0] + signals[i][1] + signals[i][2];
            maxTime = lcm(maxTime, cycle);
        }

        // 1부터 maxTime까지
        for (int time = 1; time <= maxTime; time++) {
            boolean isAllYellow = true;
            // 모든 신호등이 노란색이라고 가정하고, 아니면 false로 변경

            // 현재 각 신호등이 무슨 색인지 하나씩 검사
            for (int i = 0; i < signals.length; i++) {
                int green = signals[i][0];  // 초록불 지속 시간
                int yellow = signals[i][1]; // 노란불 지속 시간
                int red = signals[i][2];    // 빨간불 지속 시간
                int cycle = green + yellow + red; // 전체 주기

                // 현재 시간(time)이 주기 안에서 몇 번째 초인지 확인
                // 문제에서 1초부터 시작하기 때문에 -1 처리
                int timeInCycle = (time - 1) % cycle;

                // 노란불 == '초록불 직후' 부터 '노란불이 끝나기 전'까지
                if (timeInCycle >= green && timeInCycle < (green + yellow)) {
                    // 노란불, 다음 신호등을 검사
                } else {
                    // 노란불이 아닌 경우
                    isAllYellow = false;
                    break; // 하나라도 노란불이 아니면 실패
                }
            }

            if (isAllYellow) {
                return time;
            }
        }

        return -1;
    }


    // 최대공약수(GCD): 유클리드 호제법 (나머지가 0이 될 때까지 나누는 방법)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    // 최소공배수(LCM): 두 수를 곱한 뒤, 최대공약수로 나누기
    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}