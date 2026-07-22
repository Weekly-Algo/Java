import java.util.*;

class MenuSolution {
    static int s; //선택 수
    static char[] sel; //선택 배열
    static List<String> list;
    static Map<String, Integer> map;

    public String[] solution(String[] orders, int[] course) {
        list = new ArrayList<>(); //리스트 생성

        for(int i = 0; i < course.length; i++) {
            map = new HashMap<>(); //맵 생성
            s = course[i];
            sel = new char[s]; //선택 배열 만들기

            for(int j = 0; j < orders.length; j++) {
                char[] cArr = orders[j].toCharArray(); //문자 배열 만들기
                Arrays.sort(cArr);
                //조합
                comb(cArr, 0, 0);
            }

            //최대 빈도 고르기
            int max = 0;
            for(String key : map.keySet()) {
                int value = map.get(key);
                if(value >= 2 && value > max) max = value;
            }

            for(String key : map.keySet()) {
                int value = map.get(key);
                if(max >= 2 && value == max) list.add(key);
            }
        }

        Collections.sort(list); // 오름차순 정렬
        String[] result = list.toArray(new String[0]);
        return result;
    }

    public void comb(char[] cArr, int idx, int count) {
        if(count == s) { //모두 선택되었다면
            String courseStr = String.valueOf(sel);
            map.put(courseStr, map.getOrDefault(courseStr, 0) + 1); //map 개수 높이기
            return;
        }

        if(idx == cArr.length) { //배열 끝까지 왔다면
            return;
        }

        //선택
        sel[count] = cArr[idx];
        comb(cArr, idx + 1, count + 1);

        //미선택
        comb(cArr, idx + 1, count);
    }
}

public class PRG_L2_메뉴_리뉴얼 {
    public static void main(String[] args) {
        MenuSolution sol = new MenuSolution();
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

        String[] result = sol.solution(orders, course);
        System.out.println(Arrays.toString(result));
    }
}
