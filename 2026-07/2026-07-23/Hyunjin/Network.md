이 문제는 **BFS로 연결된 컴퓨터들을 한 묶음씩 탐색해서 네트워크 개수를 세는 문제**야.

## 알고리즘 핵심

1. 모든 컴퓨터를 0번부터 확인한다.
2. 아직 방문하지 않은 컴퓨터를 발견하면 새로운 네트워크이므로 `answer++`
3. 그 컴퓨터에서 BFS를 시작해 연결된 모든 컴퓨터를 방문 처리한다.
4. 끝까지 반복하면 `answer`가 네트워크 개수가 된다.

예를 들어:

```text
0 - 1    2
```

0번에서 BFS를 시작하면 0번과 1번이 방문돼.
2번은 아직 방문하지 않았으므로 새로운 네트워크가 되어 정답은 `2`야.

## 코드

```java
import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                bfs(i, computers, visited);
            }
        }

        return answer;
    }

    private void bfs(int start, int[][] computers, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next = 0; next < computers.length; next++) {
                if (computers[current][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}
```

## 코드 설명

```java
import java.util.*;
```

`Queue`, `ArrayDeque` 같은 자료구조를 사용하기 위해 불러와.

```java
class Solution {
```

프로그래머스에서 사용하는 클래스야.

```java
public int solution(int n, int[][] computers) {
```

`n`은 컴퓨터 개수, `computers`는 연결 상태 배열이야.

```java
boolean[] visited = new boolean[n];
```

각 컴퓨터를 방문했는지 저장해.

```java
int answer = 0;
```

네트워크 개수를 저장해.

```java
for (int i = 0; i < n; i++) {
```

모든 컴퓨터를 하나씩 확인해.

```java
if (!visited[i]) {
```

아직 방문하지 않은 컴퓨터라면 새로운 네트워크야.

```java
answer++;
```

네트워크 개수를 1 증가시켜.

```java
bfs(i, computers, visited);
```

현재 컴퓨터와 연결된 모든 컴퓨터를 방문 처리해.

```java
return answer;
```

최종 네트워크 개수를 반환해.

---

```java
private void bfs(int start, int[][] computers, boolean[] visited) {
```

BFS를 실행하는 메서드야.

```java
Queue<Integer> queue = new ArrayDeque<>();
```

탐색할 컴퓨터 번호를 저장할 큐를 만들어.

```java
queue.offer(start);
```

시작 컴퓨터를 큐에 넣어.

```java
visited[start] = true;
```

시작 컴퓨터를 방문 처리해.

```java
while (!queue.isEmpty()) {
```

큐가 빌 때까지 반복해.

```java
int current = queue.poll();
```

큐에서 현재 탐색할 컴퓨터를 꺼내.

```java
for (int next = 0; next < computers.length; next++) {
```

현재 컴퓨터와 연결된 컴퓨터가 있는지 전부 확인해.

```java
if (computers[current][next] == 1 && !visited[next]) {
```

두 컴퓨터가 연결되어 있고, 다음 컴퓨터를 아직 방문하지 않았다면 실행해.

```java
visited[next] = true;
```

다음 컴퓨터를 방문 처리해.

```java
queue.offer(next);
```

다음 컴퓨터도 탐색하기 위해 큐에 넣어.

## 가장 중요한 부분

```java
if (!visited[i]) {
    answer++;
    bfs(i, computers, visited);
}
```

방문하지 않은 컴퓨터를 발견할 때마다 새로운 네트워크로 세고, BFS로 같은 네트워크 전체를 방문하는 구조야.
