import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_1074_Z {
    static int N, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        N = (int)Math.pow(2, N);

        zCount(r, c, N);
        System.out.println(cnt);
    }

    static void zCount(int r, int c, int size) {
        // 종료 조건
        if(size == 1)
            return;

        int newSize = size / 2;

        // 좌상
        if(r < newSize && c < newSize)
            zCount(r, c, newSize);
        // 우상
        else if(c >= newSize)
            zCount(r, c - newSize, newSize);
        // 좌하
        else if(c < newSize) {
            cnt += ((size * size / 4) * 2);
            zCount(r - newSize, c, newSize);
        }
        // 우하
        else {
            cnt += ((size * size / 4) * 3);
            zCount(r - newSize, c - newSize, newSize);
        }
    }
}
