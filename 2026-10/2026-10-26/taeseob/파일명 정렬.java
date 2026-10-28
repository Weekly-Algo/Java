import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        PriorityQueue<String[]> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a[0].compareToIgnoreCase(b[0]) == 0) {
                    if (Integer.parseInt(a[1]) == Integer.parseInt(b[1])) {
                        return Integer.compare(Integer.parseInt(a[4]), Integer.parseInt(b[4]));
                    }
                    return Integer.compare(Integer.parseInt(a[1]), Integer.parseInt(b[1]));
                }
                return a[0].compareToIgnoreCase(b[0]);
            });

        int i = 0; 

        for (String file : files) {
            int s = 0;

            while (s < file.length() && !Character.isDigit(file.charAt(s))) s++;

            int e = s;
            while (e < file.length() && e - s < 5 && Character.isDigit(file.charAt(e))) e++;

            String head = file.substring(0, s);
            String number = file.substring(s, e);
            String tail = file.substring(e);

            pq.offer(new String[]{head, number, tail, file, String.valueOf(i++)});
        }

        for (int j = 0; j < answer.length; j++) answer[j] = pq.poll()[3];

        return answer;
    }
}