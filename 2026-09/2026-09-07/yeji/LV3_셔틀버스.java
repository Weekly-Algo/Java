import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] crewTimes = new int[timetable.length];

        // 크루 도착 시간을 분으로 변환
        for (int i = 0; i < timetable.length; i++) {
            crewTimes[i] = toMinutes(timetable[i]);
        }

        // 도착 시간 순으로 정렬
        Arrays.sort(crewTimes);

        int crewIdx = 0;
        int busTime = 9 * 60;
        int answer = 0;

        // 셔틀을 한 대씩 확인
        for (int bus = 0; bus < n; bus++) {
            int count = 0;
            int lastCrewTime = 0;

            // 현재 셔틀에 탈 수 있는 크루를 최대 m명까지 태움
            while (crewIdx < crewTimes.length
                    && crewTimes[crewIdx] <= busTime
                    && count < m) {

                lastCrewTime = crewTimes[crewIdx];
                crewIdx++;
                count++;
            }

            // 마지막 셔틀에서 콘의 도착 시간 결정
            if (bus == n - 1) {
                // 자리가 남으면 셔틀 시간에 도착
                if (count < m) {
                    answer = busTime;
                } 
                // 자리가 없으면 마지막 탑승자보다 1분 일찍 도착
                else {
                    answer = lastCrewTime - 1;
                }
            }

            // 다음 셔틀 시간
            busTime += t;
        }

        return toTime(answer);
    }

    // 시간 -> 분
    private int toMinutes(String time) {
        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        return hour * 60 + minute;
    }

    // 분 -> 시간
    private String toTime(int time) {
        int hour = time / 60;
        int minute = time % 60;

        return String.format("%02d:%02d", hour, minute);
    }
}