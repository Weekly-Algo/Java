import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G5_5430_AC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            String p = br.readLine(); // 해야할 작업
            int n = Integer.parseInt(br.readLine()); // 수의 개수

            String str = br.readLine(); // 수 배열
            str = str.substring(1, str.length() - 1); // [] 제거하기
            StringTokenizer st = new StringTokenizer(str, ","); // "," 기준으로 파싱

            // 수 배열 입력
            Deque<Integer> deque = new ArrayDeque<>();

            for(int i = 0; i < n; i++)
                deque.offer(Integer.parseInt(st.nextToken()));

            // 작업 수행
            int flag = 1;
            boolean err = false; // error 발생했는지
            for(int i = 0; i < p.length(); i++) {
                char c = p.charAt(i);

                // 뒤집기
                // 이전에 배열을 모두 뒤집으니 시간초과 발생 -> flag를 통해 앞뒤만 구분
                if(c == 'R') {
                    if(flag == 1)
                        flag = -1;
                    else
                        flag = 1;
                }

                // 맨 앞 원소 삭제
                // flag == 1 -> 맨 앞 원소 제거
                // flag == -1 -> 맨 뒤 원소 제거
                else {
                    // 숫자가 없으면 에러
                    if(deque.isEmpty()) {
                        err = true;
                        break;
                    }

                    if(flag == 1)
                        deque.pollFirst();
                    else
                        deque.pollLast();
                }
            }

            if(err) // 에러 발생
                sb.append("error").append("\n");
            else if(flag == 1) { // 앞에서부터 출력
                sb.append("[");
                while(!deque.isEmpty()) {
                    sb.append(deque.pollFirst());
                    if(!deque.isEmpty())
                        sb.append(",");
                }
                sb.append("]\n");
            } else { // 뒤에서부터 출력
                sb.append("[");
                while(!deque.isEmpty()) {
                    sb.append(deque.pollLast());
                    if(!deque.isEmpty())
                        sb.append(",");
                }
                sb.append("]\n");
            }
        }

        System.out.println(sb);
    }
}
