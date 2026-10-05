//보석 리스트 생성

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Jewel {
    int weight; //무게
    int value; //가치
    boolean sold; //판매여부

    Jewel(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.sold = false;
    }
}

class Main {
    public static List<Jewel> jewels = new ArrayList<>(); //리스트 생성
    public static int[] weightCount = new int[3001]; //무게별로 현재 남아있는 보석의 개수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine()); //작업의 개수
        
        for(int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken()); //작업 단계
            
            //1. 보석 준비
            if(command == 1) {
                int N = Integer.parseInt(st.nextToken()); //보석의 개수
                
                for(int j = 0; j < N; j++) {
                    int weight = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());
                    jewels.add(new Jewel(weight, value));
                    weightCount[weight]++;
                }
            }

            //2. 보석 입고
            else if(command == 2) {
                int weight = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                jewels.add(new Jewel(weight, value));
                weightCount[weight]++;
            }

            //3. 보석 판매
            else if(command == 3) {
                int idx = Integer.parseInt(st.nextToken());
                if(idx > jewels.size()) { //범위 밖이라면
                    System.out.println(-1);
                } else {
                    Jewel jewel = jewels.get(idx - 1);
                    if(jewel.sold) { //판매 되었다면
                        System.out.println(-1);
                    } else {
                        System.out.println(jewel.value);
                        jewel.sold = true;
                        weightCount[jewel.weight]--;
                    }
                }
            }

            //4. 보석 진열
            else if(command == 4) {
                int weightMax = Integer.parseInt(st.nextToken());

                int[] dp = new int[weightMax + 1]; //무게를 최대 i까지 사용할 수 있을 때 얻을 수 있는 최대 가치
                
                for(Jewel jewel : jewels) { //현재 가지고 있는 모든 보석을 하나씩 확인
                    if(jewel.sold) continue; //이미 판매되었다면 건너뛰기
                    if(jewel.weight > weightMax) continue; //현재 보석의 무게가 초과이면 건너뛰기

                    for(int w = weightMax; w >= jewel.weight; w--) { //뒤에서부터 확인
                        int notSelect = dp[w]; //현재 보석을 선택하지 않는 경우
                        
                        int select = dp[w - jewel.weight] + jewel.value; //현재 보석을 선택하는 경우
                        
                        dp[w] = Math.max(notSelect, select); //두 경우 중 가치가 더 큰 것을 저장
                    }
                }
                System.out.println(dp[weightMax]); //최대 무게 이하에서 만들 수 있는 최대 가치 출력
            }

            //5. 세트 구성 > 시간 초과 > 정렬 + 투포인터
            else if(command == 5) {
                int D = Integer.parseInt(st.nextToken());

                long count = 0;
                long windowCount = 0;
                int left = 1;

                for(int right = 1; right <= 3000; right++) {

                    while(right - left > D) { //무게 차이가 D보다 큰 보석 제외
                        windowCount -= weightCount[left++];
                    }
                    
                    count += windowCount * weightCount[right]; //다른 무게의 보석과 만드는 세트
                    
                    count += (long) weightCount[right] * (weightCount[right] - 1) / 2; //같은 무게의 보석끼리 만드는 세트

                    windowCount += weightCount[right]; //현재 무게의 보석 추가
                }

                System.out.println(count);
            }
        }
        
    }
}