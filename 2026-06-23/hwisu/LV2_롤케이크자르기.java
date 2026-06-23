class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        int[] rightCount = new int[10001]; // 오른쪽 형
        int[] leftCount = new int[10001];  // 왼쪽 동생
        
        int rightUnique = 0; 
        int leftUnique = 0; 
        
        // 형이 케이크를 다 가졌다고 가정
        for (int t : topping) {
            if (rightCount[t] == 0) {
                rightUnique++; // 처음 보는 토핑이면 종류 수 증가
            }
            rightCount[t]++;
        }
        
        // 왼쪽부터 하나씩 자르면서 동생에게 넘겨잇
        for (int t : topping) {
            // 형케이크에서 토핑 하나 제거
            rightCount[t]--;
            if (rightCount[t] == 0) {
                rightUnique--; // 해당 토핑이 아예 없어지면 종류 수 --
            }
            
            // 동생의 케이크에 토핑 하나 추가
            if (leftCount[t] == 0) {
                leftUnique++; // 동생에게 없던 토핑이면 종류 수 ++
            }
            leftCount[t]++;
            
            if (leftUnique == rightUnique) {
                answer++;
            }
        }
        
        return answer;
    }
}