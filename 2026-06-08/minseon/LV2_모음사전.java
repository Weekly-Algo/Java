import java.util.*;

class Solution {
    public int solution(String word) {
        //문자 배열
        String[] words = {"A", "E", "I", "O", "U"};
        List<String> list = new ArrayList<>();

        //가능한 사전 배열 다 만들어두기
        //최대 길이 "UUUUU" -> 총 5번 돌아야 됨
        //ex) AAAAA -> AAAAE -> AAAAI -> AAAAO -> AAAAU -> AAAE -> AAAEA -> AAAEE
        for(String a : words) {
            list.add(a);

            for(String b : words) {
                list.add(a + b);

                for(String c : words) {
                    list.add(a + b + c);

                    for(String d : words) {
                        list.add(a + b + c + d);

                        for(String e : words) {
                            list.add(a + b + c + d + e);
                        }
                    }
                }
            }
        }

        //list 배열 0부터 시작이니까 +1씩
        return list.indexOf(word) + 1;
    }
}