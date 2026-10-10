import java.util.*;

public class Main {

    static class Jewel {
        int weight;
        long value;
        boolean active;

        Jewel(int weight, long value) {
            this.weight = weight;
            this.value = value;
            this.active = true;
        }
    }

    static List<Jewel> jewels = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int Q = sc.nextInt();

        // 보석 번호를 1번부터 사용하기 위해 0번은 비워둠
        jewels.add(null);

        for (int q = 0; q < Q; q++) {

            int command = sc.nextInt();

            // 1. 보석 준비
            if (command == 1) {

                int N = sc.nextInt();

                for (int i = 0; i < N; i++) {
                    int w = sc.nextInt();
                    long v = sc.nextLong();

                    jewels.add(new Jewel(w, v));
                }
            }

            // 2. 보석 입고
            else if (command == 2) {

                int w = sc.nextInt();
                long v = sc.nextLong();

                jewels.add(new Jewel(w, v));
            }

            // 3. 보석 판매
            else if (command == 3) {

                int idx = sc.nextInt();

                // 존재하지 않거나 이미 판매된 경우
                if (idx <= 0 || idx >= jewels.size()
                        || !jewels.get(idx).active) {

                    System.out.println(-1);

                } else {

                    Jewel jewel = jewels.get(idx);

                    System.out.println(jewel.value);

                    jewel.active = false;
                }
            }

            // 4. 진열
            else if (command == 4) {

                int W = sc.nextInt();

                System.out.println(getMaxValue(W));
            }

            // 5. 세트 구성
            else if (command == 5) {

                int D = sc.nextInt();

                System.out.println(countSets(D));
            }
        }

        sc.close();
    }


    // 무게 W 이하에서 최대 가치 구하기
    static long getMaxValue(int W) {

        long[] dp = new long[W + 1];

        for (int i = 1; i < jewels.size(); i++) {

            Jewel jewel = jewels.get(i);

            // 판매된 보석 제외
            if (!jewel.active) {
                continue;
            }

            int weight = jewel.weight;
            long value = jewel.value;

            // 너무 무거운 보석 제외
            if (weight > W) {
                continue;
            }

            // 0/1 배낭 문제이므로 뒤에서부터 갱신
            for (int w = W; w >= weight; w--) {
                dp[w] = Math.max(
                        dp[w],
                        dp[w - weight] + value
                );
            }
        }

        return dp[W];
    }


    // 무게 차이가 D 이하인 보석 쌍 개수
    static long countSets(int D) {

        List<Integer> weights = new ArrayList<>();

        // 현재 남아 있는 보석의 무게만 저장
        for (int i = 1; i < jewels.size(); i++) {

            Jewel jewel = jewels.get(i);

            if (jewel.active) {
                weights.add(jewel.weight);
            }
        }

        if (weights.size() < 2) {
            return 0;
        }

        Collections.sort(weights);

        long count = 0;
        int left = 0;

        for (int right = 0; right < weights.size(); right++) {

            // 무게 차이가 D보다 크면 left 이동
            while (weights.get(right) - weights.get(left) > D) {
                left++;
            }

            // left부터 right-1까지 모두 세트 가능
            count += right - left;
        }

        return count;
    }
}