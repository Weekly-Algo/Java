package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_S3_4779_칸토어집합 {
    static String str;
    static int N;
    static boolean[] exist;
    public static void main(String[] agrs) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        while((str = br.readLine()) != null) { // 입력 값이 더이상 없을 때까지 반복
            N = Integer.parseInt(str);
            exist = new boolean[(int)Math.pow(3, N)]; // 3^N 크기의 boolean 배열을 만들었다!
            Arrays.fill(exist, true); // 처음에는 "-"가 전부다 존재하므로 true로 채운다
            erase(0, exist.length - 1); // 지우기 시작
            for(int i = 0; i < exist.length; i++){
                if(exist[i]) ans.append("-");
                else ans.append(" ");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }

    static void erase(int start, int end){
        int len = (end - start + 1) / 3; // 다음번에 탐색할 길이! (현재 길으이 1/3)
        if(len < 1) return;

        for(int i = start + len; i < start + len * 2; i++){
            exist[i] = false;
        }

        erase(start, start + len - 1);
        erase(start + len, start + (len * 2 - 1));
        erase(start + len * 2, start + (len * 3 - 1));
    }

}
