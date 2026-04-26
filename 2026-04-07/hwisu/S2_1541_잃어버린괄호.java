import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_1541_잃어버린괄호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        // 마이너스를 기준으로 분리
        String[] minus = input.split("-");
        int result = 0;

        for (int i = 0; i < minus.length; i++) {
            int sum = 0;

            // 플러스를 기준으로 다시 분리
            //  '+'는 정규표현식 메타 문자이므로 이스케이프(\\+) 처리가 필수....
            String[] plus = minus[i].split("\\+");

            // 플러스로 연결된 숫자들을 모두 더하기
            for (String str : plus) {
                sum += Integer.parseInt(str);
            }

            // 수식의 가장 첫 번째 부분은 결과값에 더하고, 나머지는 모두 빼기
            if (i == 0) {
                result += sum;
            } else {
                result -= sum;
            }
        }

        System.out.println(result);

	} // main

}