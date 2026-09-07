import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // 시간을 분으로 바꿀 배열 생성!
        int[] a = new int[timetable.length];

        // 시간 분으로 바꾸기!
        for (int i = 0; i < a.length; i++)
            // 앞에 두개는 시간이니까 *60!
            a[i] = Integer.parseInt(timetable[i].substring(0, 2)) * 60
                    + Integer.parseInt(timetable[i].substring(3));

        Arrays.sort(a); // 정렬하기

        // idx = 현재 확인중인 크루 인덱스 , last = 마지막으로 버스에 탄 크루 시간
        int idx = 0, last = 0;

        // 셔틀 운행! (n-1)번
        for (int bus = 0; bus < n; bus++) {
            int time = 540 + bus * t; // 셔틀 도착시간
            int cnt = 0; // 현재 셔틀에 탄 인원수

            // 크루가 남아있고 & 셔틀출발전에 도착하고 & 버스가 자리에 있으면 태움!
            while (idx < a.length && a[idx] <= time && cnt < m) {
                last = a[idx++];
                cnt++;
            }

            // 마지막 셔틀이라면? -> 중요!
            if (bus == n - 1) {
                if (cnt < m) { // 자리남았으면 그냥 그 시간이 답
                    return String.format("%02d:%02d", time / 60, time % 60);
                } else { // 자리 없으면 마지막에 탄 크루보다 1분만 빠르면 됨 (얌체 ㄹㅈㄷ)
                    int answer = last - 1;
                    return String.format("%02d:%02d", answer / 60, answer % 60);
                }
            }
        }

        return "";
    }
}