import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] crewArr = new int[timetable.length];

        //도착시간을 분으로 변환
        for(int i = 0; i < timetable.length; i++) {
            String[] time = timetable[i].split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            int total = hour * 60 + minute;
            crewArr[i] = total;
        }

        //오름차순으로 정렬 후 큐에 넣기
        Arrays.sort(crewArr);
        Queue<Integer> queue = new ArrayDeque<>();
        for(int crewTime : crewArr) {
            queue.offer(crewTime);
        }

        int busTime = 9 * 60; //첫 번째 셔틀 시간은 9시
        int lastCrewTime = 0; //마지막으로 탑승한 크루의 시간

        for(int i = 0; i < n; i++) { //셔틀이 운행되는 횟수만큼 반복
            int count = 0;
            //현재 셔틀에 탈 수 있는 크루를 태우기
            while(!queue.isEmpty() && count < m && queue.peek() <= busTime) {
                lastCrewTime = queue.poll();
                count++;
            }

            //마지막 셔틀이라면
            if(i == n - 1) {
                //자리가 남아있다면
                if(count < m) {
                    lastCrewTime = busTime;
                } else { //셔틀이 꽉찼다면
                    lastCrewTime = lastCrewTime - 1; //마지막 탑승자보다 먼저오기
                }
            }

            //다음 셔틀 시간
            busTime += t;

        }

        //시간 변환
        int hour = lastCrewTime / 60;
        int minute = lastCrewTime % 60;

        return String.format("%02d:%02d", hour, minute);
    }
}