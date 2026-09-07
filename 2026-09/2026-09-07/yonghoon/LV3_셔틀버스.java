import java.util.*;

class LV3_셔틀버스 {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        // timetable을 정수 배열로 변환 후 정렬
        int[] crewTime = new int[timetable.length];

        for(int i = 0; i < timetable.length; i++) {
            String[] strTime = timetable[i].split(":");
            int hour = Integer.parseInt(strTime[0]);
            int min = Integer.parseInt(strTime[1]);

            crewTime[i] = hour * 60 + min;
        }

        Arrays.sort(crewTime);

        // 셔틀 시간 체크
        int start = 9 * 60; // 시작시간
        int idx = 0;
        int conTime = 0; // 콘이 타야하는 시간

        for(int i = 0; i < n; i++) {
            int busTime = start + t * i; // 셔틀이 오는 시간
            int crew = 0; // 해당 시간에 셔틀을 타는 크루
            int lastTime = 0; // 마지막 크루원이 셔틀을 타는 시간

            // 각 크루가 해당 시간에 오는 셔틀에 탈 수 있는지 체크
            for(int j = 1; j <= m; j++) {
                // 셔틀을 탈 수 있음
                if(idx < crewTime.length && crewTime[idx] <= busTime) {
                    lastTime = crewTime[idx];
                    idx++;
                    crew++;
                }
                else { // 셔틀을 탈 수 없음
                    break;
                }
            }

            // 마지막 시간일때만 체크하면됨
            if(i == n - 1) {
                // 크루원이 다 찼으면 마지막 사람 시간에서 1을 빼줘야함
                if(crew == m)
                    conTime = lastTime - 1;
                    // 크루원이 다 안찼으면 해당 버스 시간에 타면 됨
                else
                    conTime = busTime;
            }
        }

        int hour = conTime / 60;
        int minute = conTime % 60;

        answer = String.format("%02d:%02d", hour, minute);
        return answer;
    }
}