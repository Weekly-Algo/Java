좋아. 이 코드는 **위상 정렬 + DP**를 이용해서 각 건물을 완성하는 데 필요한 최소 시간을 구하는 풀이야.

## 1. 문제 분석

각 건물은 자기 건설 시간이 있고, 먼저 지어야 하는 건물이 있을 수 있어.

예를 들어:

```text
1번 건물을 지은 뒤 2번 건설 가능
2번 건물을 지은 뒤 3번 건설 가능
```

이라면 관계는 다음과 같아.

```text
1 → 2 → 3
```

이처럼 건물 사이에 선행 순서가 있으므로 **위상 정렬**을 사용해.

또한 하나의 건물을 짓기 위해 여러 건물이 필요할 수 있어.

```text
1 → 3
2 → 3
```

3번 건물은 1번과 2번이 모두 완성되어야 시작할 수 있어. 따라서 두 건물 중 **더 늦게 완성되는 시간**을 기준으로 해야 해.

그래서 다음처럼 최댓값을 구해.

```java
Math.max(기존 시간, 새로운 선행 건물의 완성 시간)
```

---

## 2. 사용한 배열과 자료구조

```java
ArrayList<ArrayList<Integer>> A
```

건물 간 연결 관계를 저장하는 그래프야.

```text
A.get(1) = [2, 3]
```

이라면 1번 건물을 지은 뒤 2번과 3번을 지을 수 있다는 뜻이야.

```java
int[] indegree
```

각 건물을 짓기 전에 필요한 선행 건물 개수야.

```java
int[] selfBuild
```

각 건물 자체를 짓는 데 필요한 시간이야.

```java
int[] result
```

각 건물을 **짓기 시작하기 전까지 걸린 시간**이야.

최종 완성 시간은 다음과 같아.

```text
result[i] + selfBuild[i]
```

---

# 3. 코드 해석

## 입력 준비

```java
BufferedReader br =
    new BufferedReader(new InputStreamReader(System.in));
```

입력을 빠르게 받기 위한 객체야.

```java
int N = Integer.parseInt(br.readLine());
```

건물의 개수를 입력받아.

---

## 그래프 생성

```java
ArrayList<ArrayList<Integer>> A = new ArrayList<>();
```

건물 간 연결 관계를 저장할 그래프를 생성해.

```java
for (int i = 0; i <= N; i++) {
    A.add(new ArrayList<>());
}
```

건물 번호가 `1번부터 N번`까지이므로 `N + 1`개의 리스트를 만들어.

0번 인덱스는 사용하지 않아.

---

## 배열 생성

```java
int[] indegree = new int[N + 1];
```

각 건물의 선행 건물 개수를 저장해.

```java
int[] selfBuild = new int[N + 1];
```

각 건물의 건설 시간을 저장해.

---

## 건물 정보 입력

```java
for (int i = 1; i <= N; i++) {
```

1번부터 N번 건물까지 정보를 입력받아.

```java
StringTokenizer st = new StringTokenizer(br.readLine());
```

입력 한 줄을 공백 기준으로 나눠.

```java
selfBuild[i] = Integer.parseInt(st.nextToken());
```

첫 번째 숫자는 현재 건물의 건설 시간이므로 저장해.

예를 들어:

```text
10 1 2 -1
```

이라면 `10`은 건설 시간이야.

---

```java
while (true) {
    int preTemp = Integer.parseInt(st.nextToken());
```

현재 건물보다 먼저 지어야 하는 건물 번호를 하나씩 가져와.

```java
if (preTemp == -1)
    break;
```

`-1`이 나오면 현재 건물의 입력이 끝난 거야.

```java
A.get(preTemp).add(i);
```

선행 건물에서 현재 건물로 연결해.

예를 들어 1번을 먼저 지어야 2번을 지을 수 있다면:

```text
1 → 2
```

형태로 저장해.

```java
indegree[i]++;
```

현재 건물 `i`의 선행 건물 개수를 1 증가시켜.

---

## 처음부터 건설 가능한 건물 찾기

```java
Queue<Integer> queue = new LinkedList<>();
```

현재 바로 지을 수 있는 건물을 저장하는 큐야.

```java
for (int i = 1; i <= N; i++) {
    if (indegree[i] == 0) {
        queue.offer(i);
    }
}
```

선행 건물이 없는 건물은 바로 지을 수 있으므로 큐에 넣어.

---

## 위상 정렬 및 시간 계산

```java
int[] result = new int[N + 1];
```

각 건물을 시작하기 전까지 필요한 시간을 저장해.

```java
while (!queue.isEmpty()) {
```

건설 가능한 건물이 남아 있는 동안 반복해.

```java
int now = queue.poll();
```

현재 건설할 수 있는 건물을 큐에서 꺼내.

```java
for (int next : A.get(now)) {
```

현재 건물 `now`를 먼저 지어야 하는 다음 건물들을 확인해.

```java
indegree[next]--;
```

현재 건물이 완성됐으므로 다음 건물의 선행 조건 하나가 해결된 거야.

---

## 가장 중요한 계산

```java
result[next] = Math.max(
    result[next],
    result[now] + selfBuild[now]
);
```

`result[now] + selfBuild[now]`은 현재 건물 `now`가 완성되는 시간이야.

다음 건물에 여러 선행 건물이 있다면 가장 늦게 완성되는 건물까지 기다려야 하므로 `Math.max()`를 사용해.

예를 들어:

```text
1번 완성 시간: 20
2번 완성 시간: 35
```

두 건물을 모두 지어야 3번을 지을 수 있다면 3번은 35초 이후에 시작해야 해.

---

```java
if (indegree[next] == 0) {
    queue.offer(next);
}
```

다음 건물의 모든 선행 건물이 처리됐다면 이제 지을 수 있으므로 큐에 넣어.

---

## 결과 출력

```java
for (int i = 1; i <= N; i++) {
    System.out.println(result[i] + selfBuild[i]);
}
```

`result[i]`는 건설 시작 전까지 걸린 시간이고, `selfBuild[i]`는 자기 건설 시간이야.

따라서 둘을 더하면 건물의 최종 완성 시간이 돼.

---

## 핵심 흐름

```text
선행 건물이 없는 건물을 큐에 추가
→ 큐에서 건물을 꺼냄
→ 다음 건물의 진입 차수를 감소
→ 다음 건물의 시작 가능 시간을 최댓값으로 갱신
→ 진입 차수가 0이면 큐에 추가
```

즉, 이 문제는 **건물 순서는 위상 정렬로 처리하고, 건설 시간은 가장 오래 걸리는 선행 경로를 DP로 계산하는 문제**야.
