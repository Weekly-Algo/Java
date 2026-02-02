import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class BOJ_S5_1181_단어정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        HashSet<String> set = new HashSet<>();

        int max = 0;
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            set.add(str); //set에 넣기
            if(str.length() > max){
                max = str.length(); //최대값 구하기
            }
        }

        List<String>[] list = new ArrayList[max];
        for(int i = 0; i < max; i++){
            list[i] = new ArrayList<>();
        }

        int setSize = set.size();
        String[] strArr = set.toArray(new String[0]);

        for(int i = 0; i < setSize; i++){
            list[strArr[i].length()-1].add(strArr[i]); //해당하는 길이 인덱스에 단어 넣기
        }

        List<String> answer = new ArrayList<>();
        for(int i = 0; i < max; i++){
            String[] strArr2 = list[i].toArray(new String[0]);
            Arrays.sort(strArr2);
            for(int j = 0; j < strArr2.length; j++){
                answer.add(strArr2[j]);
            }
        }

        for(int i = 0; i < answer.size(); i++){
            System.out.println(answer.get(i));
        }
    }
}
