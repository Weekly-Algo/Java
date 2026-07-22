import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G5_1931_회의실_배정 {
    static class Time {
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Time> timelist = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            timelist.add(new Time(start, end));
        }

        // 끝나는 시간 기준으로 정렬
        // 빨리 끝날수록 회의 더 많이 가능
        // 끝나는 시간이 같으면 시작 시간이 빠른거 우선
        Collections.sort(timelist, (o1, o2) -> {
            if(o1.end == o2.end)
                return o1.start - o2.start;
            return o1.end - o2.end;
        });

        int cnt = 0; // 회의 개수
        int prevTime = 0; // 이전 회의 끝난 시간

        for(Time t : timelist) {
            // 회의 시작 시간이 이전 회의 끝난 시간보다 뒤일때만 가능함
            if (t.start >= prevTime) {
                cnt++;
                prevTime = t.end;
            }
        }

        System.out.println(cnt);
    }
}
