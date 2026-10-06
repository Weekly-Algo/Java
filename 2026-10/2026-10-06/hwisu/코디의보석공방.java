import java.io.*;
import java.util.*;

public class Main {

    static final int MAX_WEIGHT = 3000;

    static int[] weight = new int[1200];
    static int[] value = new int[1200];
    static boolean[] alive = new boolean[1200];

    // 현재 공방에 남아 있는 보석을 무게별로 몇 개 가지고 있는지
    static int[] weightCnt = new int[MAX_WEIGHT + 1];

    static int lastId = 0;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int Q = fs.nextInt();

        for (int q = 0; q < Q; q++) {
            int command = fs.nextInt();

            if (command == 1) {
                int N = fs.nextInt();

                for (int i = 1; i <= N; i++) {
                    int w = fs.nextInt();
                    int v = fs.nextInt();

                    weight[i] = w;
                    value[i] = v;
                    alive[i] = true;

                    weightCnt[w]++;
                }

                lastId = N;

            } else if (command == 2) {
                int w = fs.nextInt();
                int v = fs.nextInt();

                lastId++;

                weight[lastId] = w;
                value[lastId] = v;
                alive[lastId] = true;

                weightCnt[w]++;

            } else if (command == 3) {
                int id = fs.nextInt();

                if (id > lastId || !alive[id]) {
                    sb.append(-1).append('\n');
                    continue;
                }

                sb.append(value[id]).append('\n');

                alive[id] = false;
                weightCnt[weight[id]]--;

            } else if (command == 4) {
                int W = fs.nextInt();

                sb.append(display(W)).append('\n');

            } else if (command == 5) {
                int D = fs.nextInt();

                sb.append(countSets(D)).append('\n');
            }
        }

        System.out.print(sb);
    }

    static int display(int W) {
        int[] dp = new int[W + 1];

        for (int id = 1; id <= lastId; id++) {
            if (!alive[id]) {
                continue;
            }

            int w = weight[id];
            int v = value[id];

            if (w > W) {
                continue;
            }

            // 같은 보석을 여러 번 사용하지 않도록 뒤에서부터 갱신
            for (int j = W; j >= w; j--) {
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }

        return dp[W];
    }

    static long countSets(int D) {
        int[] prefix = new int[MAX_WEIGHT + 1];

        for (int w = 1; w <= MAX_WEIGHT; w++) {
            prefix[w] = prefix[w - 1] + weightCnt[w];
        }

        long count = 0;

        for (int w = 1; w <= MAX_WEIGHT; w++) {
            int cnt = weightCnt[w];

            if (cnt == 0) {
                continue;
            }

            // 같은 무게인 보석끼리의 조합
            count += (long) cnt * (cnt - 1) / 2;

            // 무게가 더 큰 보석 중 차이가 D 이하인 경우
            int right = Math.min(MAX_WEIGHT, w + D);
            int other = prefix[right] - prefix[w];

            count += (long) cnt * other;
        }

        return count;
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if (len <= 0) {
                    return -1;
                }
            }

            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;

            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            int num = 0;

            while (c > ' ') {
                num = num * 10 + (c - '0');
                c = read();
            }

            return num * sign;
        }
    }
}