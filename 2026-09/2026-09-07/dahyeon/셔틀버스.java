import java.util.*;
// 셔틀을 타고 도착 시간 중 제일 늦은 시각
// n 회 t 분 최대 m 명
// 콘은 늘 대기열 마지막 !
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {

        LinkedList<Integer> times = new LinkedList<>();
        // 시간 분해하고 분 단위로 합치기
        for(String s : timetable){
            String[] time = s.split(":");
            int hour = Integer.parseInt(time[0]);
            int min = Integer.parseInt(time[1]);
            times.add(hour*60 + min);
        }

        Collections.sort(times);

        // 9시
        int busTime = 540;
        // 버스에 몇명 탔는지 인원 체크
        int cnt = 0;
        // 마지막에 탄 사람
        int lastPerson = 0;

        for(int i = 0; i < n; i++){
            busTime = 540 + i * t;
            cnt = 0;

            // 맨 앞사람 도착 시간이 버스 시간 보다 같거나 빠르고
            // 최대 m 이 되지 않았다면, 계속 태우기
            while(!times.isEmpty() && times.peek() <= busTime && cnt < m){
                // 태운 사람 계속 시간 기록
                lastPerson = times.poll();
                cnt++;
            }
        }

        int ans;
        // 버스가 자리가 남았다면, 마지막 버스 시간에 맞춰서 오면 됨
        if(cnt < m) {
            ans = busTime;
        }
        // 꽉 찼다면 마지막 사람보다 1분전에 와야 함
        else {
            ans = lastPerson - 1;

        }

        int hour = ans/60;
        int min = ans%60;

        return String.format("%02d:%02d", hour, min);
    }
}