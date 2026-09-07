import java.util.Arrays;

public class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // 일단 타임테이블을 정수로 바꾸고 오름차순 정렬
        int[] times = new int[timetable.length];
        
        for (int i=0; i<timetable.length; i++) {
            String[] parts = timetable[i].split(":");
            times[i] = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        }
        Arrays.sort(times);

        int idx = 0; // 아직 안탄놈 포인터
        int busTime = 9 * 60; // 첫 버스 도착시간
        int answer = 0;

        for (int i=0; i<n; i++) {
            int count = 0;
            // 이번 버스에 탈 수 있는 만큼(count<m) 대기열에서 태우기
            while (count < m && idx < times.length && times[idx] <= busTime) {
                idx++;
                count++;
            }

            if (i == n-1) { // 마지막 버스일 때만 정답 계산
                if (count < m) {
                    // 자리가 남았으면 버스 도착 시각에 바로 탈 수 있음
                    answer = busTime;
                } else {
                    // 자리가 꽉 찼으면 마지막으로 탄 크루보다 1분 일찍 가야 함
                    answer = times[idx - 1] - 1;
                }
            }

            busTime += t;
        }

        int hh = answer / 60;
        int mm = answer % 60;

        StringBuilder sb = new StringBuilder();
        if (hh < 10) sb.append("0");
        sb.append(hh).append(":");
        if (mm < 10) sb.append("0");
        sb.append(mm);

        return sb.toString();
    }
}