class Solution {
    int answer = 0; // 지금까지 찾은 최대 탐험 던전 수
    boolean[] visited; // 각 던전을 이미 탐험했는지 여부

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length]; // 처음엔 전부 false(미방문)로 초기화
        dfs(k, 0, dungeons); // 현재 피로도 k, 지금까지 탐험한 던전 수 0부터 시작
        return answer;
    }

    // k: 현재 남은 피로도
    // count: 지금까지 탐험한 던전 개수
    // dungeons: 던전 정보 배열
    private void dfs(int k, int count, int[][] dungeons) {
        // 현재까지 탐험한 개수로 최댓값 갱신 시도
        // (더 이상 갈 수 있는 던전이 없어도 여기서 최종적으로 기록됨)
        answer = Math.max(answer, count);

        // 모든 던전을 하나씩 이번 순서에 넣어볼지 시도
        for (int i = 0; i < dungeons.length; i++) {

            // 이미 방문했거나(visited[i]), 최소 필요 피로도(dungeons[i][0])보다
            // 현재 피로도 k가 부족하면 이 던전은 지금 못 감 -> 건너뛰기
            if (visited[i] || k < dungeons[i][0]) {
                continue;
            }

            // 조건을 만족하니 이 던전을 탐험한다고 가정
            visited[i] = true; // 방문 처리
            // 재귀: 피로도는 소모 피로도만큼 깎고(dungeons[i][1]), 탐험 개수는 +1
            dfs(k - dungeons[i][1], count + 1, dungeons);
            visited[i] = false; // 백트래킹
        }
    }
}