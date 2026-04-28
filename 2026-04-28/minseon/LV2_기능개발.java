import java.util.*;

class Solution {

    public int[] solution(int[] progresses, int[] speeds) {

        //먼저 각각 얼마나 걸리는지부터 계산
        int[] days = new int[progresses.length];
        for(int i=0; i<progresses.length; i++) {
            // 남은 작업량 = 100 - progresses[i]
            // 걸리는 날짜 = 남은 작업량 / 하루 작업 속도
            // 나누어떨어지지 않으면 하루를 더 써야 하므로 올림 처리
            days[i] = (100 - progresses[i] + speeds[i] -1) / speeds[i];
        }

        int[] answer = new int[progresses.length]; //배포할 배열
        int idx = 0;

        //첫 번째 작업부터 배포
        int current = days[0];
        int count = 1;

        for(int d=1; d<progresses.length; d++) {
            // 현재 작업이 기준 날짜 안에 끝난다면
            // 앞 작업과 같이 배포 가능
            if(days[d] <= current) {
                count++;
                // 현재 작업이 기준 날짜보다 늦게 끝난다면
                // 이전까지 모은 기능들을 먼저 배포해야 함
            } else {
                answer[idx++] = count;
                // 새로운 배포 기준 날짜로 변경
                current = days[d];
                count = 1;
            }
        }

        answer[idx++] = count;

        return Arrays.copyOf(answer, idx);
    }
}