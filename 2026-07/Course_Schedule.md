## 위상 정렬이란?

**위상 정렬(Topological Sort)**은 순서가 정해진 작업들을, 조건을 지키면서 일렬로 나열하는 알고리즘이야.

예를 들어 다음 조건이 있다고 해보자.

```text
A를 먼저 해야 B를 할 수 있음
B를 먼저 해야 C를 할 수 있음
```

그래프로 표현하면 다음과 같아.

```text
A → B → C
```

가능한 순서는 다음 하나야.

```text
A, B, C
```

Course Schedule 문제에서는:

```text
선수 과목 → 수강할 과목
```

형태로 그래프를 만든다.

예를 들어:

```text
[1, 0]
```

은 1번 강의를 듣기 전에 0번 강의를 들어야 한다는 뜻이므로:

```text
0 → 1
```

이야.

---

## 위상 정렬을 사용할 수 있는 그래프

위상 정렬은 다음 조건을 만족하는 그래프에서만 가능해.

- 방향이 있는 그래프
- 사이클이 없는 그래프

이를 **DAG**라고 불러.

```text
Directed Acyclic Graph
방향 비순환 그래프
```

다음 그래프는 위상 정렬이 가능해.

```text
0 → 1 → 2
```

하지만 다음 그래프는 불가능해.

```text
0 → 1
↑   ↓
└───┘
```

0을 하려면 1이 필요하고, 1을 하려면 다시 0이 필요하기 때문이야.

Course Schedule 문제는 결국 다음을 묻는 문제야.

> 선수 과목 그래프에 사이클이 있는가?

사이클이 없으면 모든 수업을 들을 수 있으므로 `true`, 사이클이 있으면 `false`야.

---

# 진입 차수란?

위상 정렬에서 중요한 개념이 **진입 차수(indegree)**야.

진입 차수는 어떤 정점으로 들어오는 화살표의 개수야.

```text
0 → 1
2 → 1
```

1번으로 들어오는 화살표가 2개 있으므로:

```text
indegree[1] = 2
```

Course Schedule 관점에서는 진입 차수를 이렇게 이해하면 돼.

> 해당 강의를 듣기 전에 먼저 들어야 하는 선수 과목의 개수

위 그래프에서 1번 강의를 들으려면 0번과 2번을 먼저 들어야 하므로 진입 차수는 2야.

진입 차수가 0인 강의는 선수 과목이 없다는 뜻이므로 바로 들을 수 있어.

---

# 위상 정렬 과정

다음 그래프를 보자.

```text
0 → 1 → 3
 \      ↑
  → 2 ──┘
```

각 강의의 진입 차수는 다음과 같아.

```text
0: 0
1: 1
2: 1
3: 2
```

먼저 진입 차수가 0인 0번 강의를 큐에 넣어.

```text
queue = [0]
```

0번을 수강하면 0번에서 연결된 1번과 2번의 선수 과목 하나가 해결돼.

```text
1번 진입 차수: 1 → 0
2번 진입 차수: 1 → 0
```

그러면 1번과 2번도 들을 수 있어.

```text
queue = [1, 2]
```

1번을 처리하면 3번의 진입 차수가 줄어.

```text
3번 진입 차수: 2 → 1
```

아직 0이 아니므로 큐에는 넣지 않아.

다음으로 2번을 처리하면:

```text
3번 진입 차수: 1 → 0
```

이제 3번도 큐에 넣을 수 있어.

최종적으로 4개의 강의를 모두 처리했기 때문에 모든 강의를 들을 수 있어.

---

# 전체 코드

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];

            graph.get(prerequisiteCourse).add(course);
            indegree[course]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();

        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0) {
                queue.offer(course);
            }
        }

        int completedCourses = 0;

        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            completedCourses++;

            for (int nextCourse : graph.get(currentCourse)) {
                indegree[nextCourse]--;

                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        return completedCourses == numCourses;
    }
}
```

# 코드 한 줄씩 설명

## 1. 라이브러리 가져오기

```java
import java.util.ArrayDeque;
```

큐로 사용할 `ArrayDeque` 클래스를 가져와.

위상 정렬에서는 현재 수강 가능한 강의를 순서대로 처리하기 위해 큐를 사용해.

```java
import java.util.ArrayList;
```

그래프의 연결 관계를 저장하기 위해 `ArrayList`를 사용해.

```java
import java.util.Deque;
```

큐 변수의 타입으로 `Deque` 인터페이스를 사용해.

```java
import java.util.List;
```

그래프를 `List<List<Integer>>` 형태로 만들기 위해 `List`를 가져와.

---

## 2. Solution 클래스

```java
class Solution {
```

LeetCode에서 제출할 클래스야.

LeetCode는 보통 `Solution` 클래스 안에 문제에서 요구한 메서드를 작성하는 형태야.

---

## 3. 메서드 선언

```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
```

모든 강의를 수강할 수 있는지 판단하는 메서드야.

매개변수는 다음과 같아.

```text
numCourses
```

전체 강의 개수야.

강의 번호는:

```text
0 ~ numCourses - 1
```

이야.

```text
prerequisites
```

선수 과목 관계가 들어 있는 2차원 배열이야.

예를 들어:

```java
prerequisites = new int[][] {
    {1, 0},
    {2, 0}
};
```

의미는 다음과 같아.

```text
1번을 듣기 전에 0번을 들어야 함
2번을 듣기 전에 0번을 들어야 함
```

반환값은 `boolean`이야.

```text
모든 강의 수강 가능 → true
모든 강의 수강 불가능 → false
```

---

## 4. 그래프 생성

```java
List<List<Integer>> graph = new ArrayList<>();
```

강의 간 연결 관계를 저장할 그래프를 만들어.

자료형이 조금 복잡해 보일 수 있어.

```java
List<List<Integer>>
```

바깥쪽 리스트는 전체 강의를 의미하고, 안쪽 리스트는 해당 강의 다음에 들을 수 있는 강의들을 의미해.

예를 들어:

```text
0 → 1
0 → 2
```

라면:

```java
graph.get(0) = [1, 2]
```

가 돼.

즉, 0번 강의를 선수 과목으로 요구하는 강의는 1번과 2번이라는 뜻이야.

---

## 5. 강의별 리스트 만들기

```java
for (int i = 0; i < numCourses; i++) {
```

0번 강의부터 마지막 강의까지 반복해.

```java
graph.add(new ArrayList<>());
```

각 강의마다 연결된 강의를 저장할 빈 리스트를 하나씩 추가해.

강의가 4개라면 처음에는 다음과 같은 상태야.

```text
graph[0] = []
graph[1] = []
graph[2] = []
graph[3] = []
```

이 과정을 하지 않고 바로 `graph.get(0).add(...)`를 실행하면, 0번째 리스트 자체가 존재하지 않아서 오류가 발생해.

```java
}
```

그래프 초기화 반복문의 끝이야.

---

## 6. 진입 차수 배열 생성

```java
int[] indegree = new int[numCourses];
```

각 강의의 진입 차수를 저장하는 배열이야.

강의가 4개면 다음과 같이 생성돼.

```text
indegree = [0, 0, 0, 0]
```

Java의 `int` 배열은 처음 생성하면 모든 값이 자동으로 0이 돼.

`indegree[2]`는 2번 강의를 듣기 전에 필요한 선수 과목 개수를 의미해.

---

## 7. 선수 과목 정보 순회

```java
for (int[] prerequisite : prerequisites) {
```

`prerequisites` 배열에 들어 있는 선수 과목 관계를 하나씩 꺼내.

예를 들어:

```java
prerequisites = {
    {1, 0},
    {2, 0}
}
```

이면 첫 번째 반복에서는:

```java
prerequisite = {1, 0}
```

두 번째 반복에서는:

```java
prerequisite = {2, 0}
```

이 돼.

이 형태를 **향상된 for문**이라고 해.

---

## 8. 현재 과목 꺼내기

```java
int course = prerequisite[0];
```

현재 수강하려는 강의 번호를 가져와.

예를 들어:

```java
prerequisite = {1, 0}
```

이면:

```java
course = 1
```

이야.

---

## 9. 선수 과목 꺼내기

```java
int prerequisiteCourse = prerequisite[1];
```

먼저 들어야 하는 선수 과목 번호를 가져와.

```java
prerequisite = {1, 0}
```

이면:

```java
prerequisiteCourse = 0
```

이야.

따라서 관계는 다음과 같아.

```text
0 → 1
```

---

## 10. 그래프에 연결 관계 저장

```java
graph.get(prerequisiteCourse).add(course);
```

선수 과목에서 현재 과목으로 향하는 방향을 그래프에 저장해.

예를 들어:

```text
course = 1
prerequisiteCourse = 0
```

이면 다음 코드를 실행한 것과 같아.

```java
graph.get(0).add(1);
```

그래프 상태는:

```text
graph[0] = [1]
```

이 돼.

의미는 다음과 같아.

> 0번 강의를 들은 다음 1번 강의의 조건을 줄일 수 있다.

방향을 반대로 저장하지 않도록 주의해야 해.

```java
graph.get(course).add(prerequisiteCourse);
```

로 작성하면 `1 → 0`이 되어 문제의 의미와 반대가 돼.

---

## 11. 진입 차수 증가

```java
indegree[course]++;
```

현재 강의에 필요한 선수 과목 개수를 1 증가시켜.

```text
0 → 1
```

이라면 1번 강의로 들어오는 화살표가 하나이므로:

```text
indegree[1] = 1
```

이 돼.

예를 들어:

```text
0 → 3
1 → 3
```

이라면 3번 강의는 선수 과목이 두 개이므로:

```text
indegree[3] = 2
```

가 돼.

```java
}
```

모든 선수 과목 관계를 그래프에 저장하는 반복문의 끝이야.

---

## 12. 큐 생성

```java
Deque<Integer> queue = new ArrayDeque<>();
```

현재 바로 들을 수 있는 강의를 저장할 큐를 만들어.

큐는 먼저 들어간 값이 먼저 나오는 자료구조야.

```text
FIFO
First In, First Out
```

예를 들어:

```text
offer(0)
offer(1)
poll() → 0
poll() → 1
```

이 돼.

`ArrayDeque`는 Java에서 큐를 구현할 때 자주 사용해.

---

## 13. 모든 강의 검사

```java
for (int course = 0; course < numCourses; course++) {
```

0번부터 마지막 강의까지 진입 차수를 확인해.

---

## 14. 선수 과목이 없는지 확인

```java
if (indegree[course] == 0) {
```

현재 강의의 진입 차수가 0인지 확인해.

진입 차수가 0이라는 건 선수 과목이 없다는 뜻이야.

따라서 지금 바로 들을 수 있어.

---

## 15. 큐에 강의 추가

```java
queue.offer(course);
```

바로 들을 수 있는 강의를 큐에 넣어.

예를 들어 진입 차수가 다음과 같다면:

```text
indegree = [0, 1, 0, 2]
```

0번과 2번이 큐에 들어가.

```text
queue = [0, 2]
```

```java
}
```

`if`문의 끝이야.

```java
}
```

강의 검사 반복문의 끝이야.

---

## 16. 수강 완료한 강의 수

```java
int completedCourses = 0;
```

지금까지 처리한 강의 수를 저장해.

위상 정렬이 끝난 뒤 이 값이 전체 강의 수와 같은지 확인할 거야.

---

## 17. 큐가 빌 때까지 반복

```java
while (!queue.isEmpty()) {
```

큐에 수강 가능한 강의가 남아 있는 동안 반복해.

```java
queue.isEmpty()
```

는 큐가 비어 있으면 `true`를 반환해.

앞에 `!`가 있으므로:

```text
큐가 비어 있지 않으면 반복
```

이라는 뜻이야.

---

## 18. 큐에서 강의 꺼내기

```java
int currentCourse = queue.poll();
```

큐의 가장 앞에 있는 강의를 꺼내.

예를 들어:

```text
queue = [0, 2]
```

라면:

```text
currentCourse = 0
queue = [2]
```

가 돼.

`poll()`은 값을 꺼내면서 큐에서도 제거해.

---

## 19. 수강 완료 수 증가

```java
completedCourses++;
```

현재 강의를 들었으므로 완료한 강의 수를 1 증가시켜.

```text
0 → 1 → 2
```

처럼 처리돼.

---

## 20. 현재 강의 이후의 강의 확인

```java
for (int nextCourse : graph.get(currentCourse)) {
```

현재 강의를 선수 과목으로 사용하는 강의들을 하나씩 확인해.

예를 들어:

```text
graph[0] = [1, 2]
```

이고 현재 강의가 0번이라면:

```text
nextCourse = 1
nextCourse = 2
```

순서로 반복해.

0번을 수강했으므로 1번과 2번 강의의 선수 과목 조건을 하나씩 줄일 수 있어.

---

## 21. 다음 강의의 진입 차수 감소

```java
indegree[nextCourse]--;
```

현재 강의를 수강했으므로 다음 강의에 필요한 선수 과목 수를 1 감소시켜.

예를 들어:

```text
0 → 1
```

에서 0번을 수강하면:

```text
indegree[1]: 1 → 0
```

이 돼.

---

## 22. 모든 선수 과목을 들었는지 확인

```java
if (indegree[nextCourse] == 0) {
```

진입 차수를 감소시킨 결과가 0인지 확인해.

0이 됐다면 해당 강의에 필요한 선수 과목을 모두 들었다는 뜻이야.

이제 그 강의도 수강할 수 있어.

---

## 23. 다음 강의를 큐에 추가

```java
queue.offer(nextCourse);
```

새롭게 수강 가능해진 강의를 큐에 넣어.

나중에 큐에서 꺼내 해당 강의 이후의 연결 관계를 처리해.

```java
}
```

`if`문의 끝이야.

```java
}
```

현재 강의와 연결된 다음 강의들을 확인하는 반복문의 끝이야.

```java
}
```

큐가 빌 때까지 실행하는 `while`문의 끝이야.

---

## 24. 모든 강의를 처리했는지 확인

```java
return completedCourses == numCourses;
```

처리한 강의 수와 전체 강의 수가 같은지 비교해.

같다면:

```java
true
```

를 반환해.

다르다면:

```java
false
```

를 반환해.

예를 들어 강의가 총 4개이고 모두 처리했다면:

```text
completedCourses = 4
numCourses = 4

4 == 4 → true
```

사이클 때문에 2개밖에 처리하지 못했다면:

```text
completedCourses = 2
numCourses = 4

2 == 4 → false
```

가 돼.

---

# 사이클이 있을 때 큐가 멈추는 이유

다음 관계를 생각해보자.

```text
0 → 1
1 → 0
```

진입 차수는:

```text
indegree[0] = 1
indegree[1] = 1
```

이야.

진입 차수가 0인 강의가 없으므로 처음부터 큐가 비어 있어.

```text
queue = []
```

따라서 `while`문을 한 번도 실행하지 못해.

```text
completedCourses = 0
```

전체 강의는 2개이므로:

```java
return 0 == 2;
```

결과는 `false`야.

조금 더 큰 그래프에서 일부만 사이클인 경우에도, 사이클에 속한 강의들은 진입 차수가 끝까지 0이 되지 않아. 그래서 처리한 강의 수가 전체 강의 수보다 작게 남는 거야.

---

# 핵심 코드만 다시 보기

```java
graph.get(prerequisiteCourse).add(course);
```

선수 과목에서 다음 과목으로 그래프 연결:

```text
선수 과목 → 수강할 과목
```

```java
indegree[course]++;
```

현재 과목에 필요한 선수 과목 수 증가.

```java
if (indegree[course] == 0) {
    queue.offer(course);
}
```

선수 과목이 없는 강의를 큐에 넣음.

```java
indegree[nextCourse]--;
```

선수 과목 하나를 수강했으므로 다음 강의의 조건을 하나 줄임.

```java
if (indegree[nextCourse] == 0) {
    queue.offer(nextCourse);
}
```

모든 선수 과목을 들은 강의를 큐에 넣음.

```java
return completedCourses == numCourses;
```

모든 강의를 처리했다면 사이클이 없으므로 `true`.
