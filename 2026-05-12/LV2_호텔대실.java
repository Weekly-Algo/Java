import java.util.*;

class Solution {
    public int solution(String[][] book_time) {

        int[] time = new int[24 * 60 + 11];

        for (String[] book : book_time) {

            String[] start = book[0].split(":");
            String[] end = book[1].split(":");

            int startTime = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            int endTime = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]) + 10;

            time[startTime]++;
            time[endTime]--;
        }

        int answer = 0;
        int room = 0;

        for (int i = 0; i < time.length; i++) {
            room += time[i];
            answer = Math.max(answer, room);
        }

        return answer;
    }
}