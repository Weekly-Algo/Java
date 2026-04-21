package BeforeSubmit;

public class PGS_LV2_서버증설횟수 {
    class Solution {
        public int solution(int[] players, int m, int k) {
            int answer = 0;

            int[] server = new int[24]; // 현재 서버

            for(int i = 0; i < 24; i++) {
                if(players[i] >= (server[i]+1)*m){
                    int tmp = players[i] / m - server[i];
                    answer += tmp;
                    for(int j = 0 ; j < k; j++) {
                        if(i+j >= 24) continue;
                        server[i+j] += tmp;
                    }
                }
            }


            return answer;
        }
    }
}
