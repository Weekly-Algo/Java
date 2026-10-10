import java.util.*;

public class Main {
    static int[] weight = new int[1101];
    static int[] value = new int[1101];
    static boolean[] exists = new boolean[1101];

    static int lastId = 0;

    // 1번, 보석 추가
    static void addJewel(int w, int v) {
        lastId++;
        weight[lastId] = w;
        value[lastId] = v;
        exists[lastId] = true;
    }

    // 4번 진열, 총무게 W 이하로 고를 때 최대 가치
    static int display(int W) {
        // 하나도 고르지 않을 경우 , dp[0] 출력
        int[] dp = new int[W + 1];

        for (int id = 1; id <= lastId; id++) {
            if (!exists[id]) continue;

            int w = weight[id];
            int v = value[id];

            // 같은 보석을 중복 사용하지 않도록 뒤에서부터 계산!!
            for (int limit = W; limit >= w; limit--) {
                dp[limit] = Math.max(
                    dp[limit], // 보석을 안넣는 경우
                    dp[limit - w] + v // 보석을 넣는 경우
                );
            }
        }

        return dp[W];
    }

    // 5번 세트, 무게 차이가 D 이하인 두 보석의 조합 수
    static int countSets(int D) {
        int[] sorted = new int[lastId];
        int size = 0;

        // 남아 있는 보석의 무게만 모으기
        for (int id = 1; id <= lastId; id++) {
            if (exists[id]) {
                sorted[size++] = weight[id];
            }
        }

        // 0번부터 size - 1번까지 정렬
        Arrays.sort(sorted, 0, size);

        int count = 0;
        int left = 0;
        // 2개 미만일시, 0 출력
        for (int right = 0; right < size; right++) {
            // 무게 차이가 너무 크면 왼쪽 범위를 줄이기
            while (sorted[right] - sorted[left] > D) {
                left++;
            }

            count += right - left;
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder output = new StringBuilder();

        int q = sc.nextInt();

        for (int i = 0; i < q; i++) {
            int command = sc.nextInt();

            switch (command) {
                case 1: {
                    int n = sc.nextInt();

                    for (int j = 0; j < n; j++) {
                        int w = sc.nextInt();
                        int v = sc.nextInt();
                        addJewel(w, v);
                    }
                    break;
                }

                case 2: {
                    int w = sc.nextInt();
                    int v = sc.nextInt();
                    addJewel(w, v);
                    break;
                }

                case 3: {
                    int id = sc.nextInt();

                    if (!exists[id]) {
                        output.append(-1).append('\n');
                    } else {
                        output.append(value[id]).append('\n');
                        exists[id] = false;
                    }
                    break;
                }

                case 4: {
                    int W = sc.nextInt();
                    output.append(display(W)).append('\n');
                    break;
                }

                case 5: {
                    int D = sc.nextInt();
                    output.append(countSets(D)).append('\n');
                    break;
                }
            }
        }

        System.out.print(output);
    }
}