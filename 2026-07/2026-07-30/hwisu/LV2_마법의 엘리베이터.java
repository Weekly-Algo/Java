class Solution {
    public int solution(int storey) {
        return dfs(storey);
    }
    
    private int dfs(int storey) {
        if (storey == 0) return 0;
        
        int digit = storey % 10;
        int quotient = storey / 10;
        
        if (digit < 5) {
            return digit + dfs(quotient);
        } else if (digit > 5) {
            return (10 - digit) + dfs(quotient + 1);
        } else {
            // digit == 5인 경우 두 가지 경우 모두 계산해서 최솟값 선택
            int down = 5 + dfs(quotient);
            int up = 5 + dfs(quotient + 1);
            return Math.min(down, up);
        }
    }
}