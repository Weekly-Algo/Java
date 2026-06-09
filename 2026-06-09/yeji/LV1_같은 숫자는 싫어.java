import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        List<Integer> list =  new ArrayList<>(); //리스트 생성
        list.add(arr[0]); // 첫 번째 숫자는 무조건 포함
        
        //연속적으로 나타나는 숫자는 하나만 남기고 저장 안함
        for(int i = 1; i < arr.length ; i++){
            if(arr[i] == arr[i-1]) {
            }
            else
                list.add(arr[i]);
        }
        // 리스트 크기의 정답 배열 생성
        int[] answer = new int[list.size()]; 
        
        // 리스트 값 배열에 저장
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;

    }
}