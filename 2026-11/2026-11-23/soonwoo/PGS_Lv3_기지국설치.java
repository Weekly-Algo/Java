class Solution {
    public int solution(int n, int[] stations, int w) {
        // n: 아파트의 개수 (~2억, 자연수)
        // station: 기지국이 설치된 아파트의 번호 배열(~1만, 오름차순 정렬됨)
        // 5G 기지국의 전파 도달 범위(~1만)
        
        int currLen = 0;  // 전파가 닿지 않는 연속된 범위의 길이
        int left = 1;     // 전파가 닿지 않는 왼쪽 위치
        int cnt = 0;      // 추가로 증설한 기지국의 개수
        
        int range = 2 * w + 1;  // w일 때, 전파 가능한 범위
        for (int currApt : stations) {
            if (left >= currApt - w) {
                left = currApt + w + 1;
                continue;
            }
            
            currLen = (currApt - w) - left;
            
            // cnt += (int) Math.ceil((float) currLen / range);
            cnt += (currLen + range - 1) / range;
            
            left = currApt + w + 1;
        }
        
        if (left <= n) {
            currLen = n - left + 1;
            cnt += (int) Math.ceil((float) currLen / range);
        }
        
        return cnt;
    }
}