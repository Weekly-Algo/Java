import java.util.*;

public class LV3_베스트앨범 {

    public int[] solution(String[] genres, int[] plays) {
        // 총 플레이 수
        Map<String, Integer> totalPlayMap = new HashMap<>();

        // 장르와 그에 해당하는 고유번호 & 플레이 수
        Map<String, PriorityQueue<int[]>> musicMap = new HashMap<>();

        // 각각의 맵 채우기
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            // 총 플레이 수 넣기
            totalPlayMap.put(genre, totalPlayMap.getOrDefault(genre, 0) + play);

            // 장르에 고유번호와 플레이 수 넣기
            // 장르가 존재하지 않는다면 장르부터 추가
            if (!musicMap.containsKey(genre)) {
                musicMap.put(genre, new PriorityQueue<>((a, b) -> (b[1] - a[1]))); // 플레이수 기준 정렬
            }
            musicMap.get(genre).offer(new int[]{i, play});
        }

        // 총 플레이 수 맵에서 키 가져온 후 정렬
        List<String> genreList = new ArrayList(totalPlayMap.keySet());
        genreList.sort((g1, g2) -> (totalPlayMap.get(g2) - totalPlayMap.get(g1)));

        // 정답 배열에 고유번호 저장
        List<Integer> answerList = new ArrayList<>();

        for (String genre : genreList) {
            PriorityQueue<int[]> songs = musicMap.get(genre);
            answerList.add(songs.poll()[0]);

            // 노래가 2개 이상일 경우 하나 더 뽑아서 넣기
            if (!songs.isEmpty()) {
                answerList.add(songs.poll()[0]);
            }
        }

        // list를 배열로 바꾸기
        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}

