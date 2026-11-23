import java.util.*;

class LV3_기지국_설치 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        // 기지국 하나가 커버하는 범위
        int power = w * 2 + 1;

        int start = 1;
        for(int station : stations) {
            int length = station - w - start;

            // 기지국 설치
            answer += length / power;
            if(length % power > 0)
                answer++;

            start = station + w + 1;
        }

        int length = n - start + 1;
        answer += length / power;
        if(length % power > 0)
            answer++;

        return answer;
    }
}