class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int converage = 2 * w + 1; //기지국 하나가 커버할 수 있는 아파트 수

        int left = 1; //아직 확인하지 않은 가장 왼쪽의 아파트

        //기존 기지국을 하나씩 확인
        for(int station : stations) {
            int converageStart = station - w; //왼쪽 전파 시작 위치
            int length = converageStart - left; //미커버 구간의 길이
            
            if(length > 0) { //미커버 구간이 존재한다면
                //미커버 아파트를 기지국 하나가 커버할 수 있는 크기로 나누고 남는 아파트가 있으면 하나를 추가
                answer += (length + converage - 1) / converage;
            }

            left = station + w + 1; //오른쪽 전파 범위 다음으로 이동
        }

        int length = n - left + 1; //마지막 기지국 이후 남은 미커버 구간
        
        if(length > 0) {
            answer += (length + converage - 1) / converage; //남은 구간에 필요한 기지국 수를 구한다.
        }

        return answer;
    }
}