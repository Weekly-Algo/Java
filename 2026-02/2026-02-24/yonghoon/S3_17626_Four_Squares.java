import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_17626_Four_Squares {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            arr[i] = i;
            for(int j = 1; j * j <= i; j++) {
                arr[i] = Math.min(arr[i], arr[i - j * j] + 1);
            }
        }

        System.out.println(arr[n]);
    }
}
