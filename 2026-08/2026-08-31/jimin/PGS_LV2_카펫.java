class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow; //전체 칸 수
        
        //가로 정해보며 구하기
        for(int width = 3; width <= total; width++) {
            if(total % width != 0) continue; //나누어 떨어지지 않는다면

            int height = total / width;

            //노란색 개수
            int yellowCount = (width - 2) * (height - 2);

            if(width >= height && yellowCount == yellow) {
                return new int[] {width, height};
            }

        }

        return new int[]{};
    }
}