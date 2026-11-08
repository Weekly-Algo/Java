
#     상, 하, 좌, 우
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def bfs(graph, start, end, distance):
    # bfs는 큐 기반 + while은 외우는 게 편하다. 일반적으로 맞다.
    queue = deque([start])
    r, c = start
    distance[r][c] = 0
    n = len(graph)
    while queue:
        cr, cc = queue.popleft()
        if (cr, cc) == end:
            return distance[cr][cc]
        # 이동을 할 거야 근데 조건이 맵 밖으로 나가면 안 되고, 방문한 적 없어야 하고, 1인 곳은 불가
        for i in range(len(dr)):
            nr = cr + dr[i]
            nc = cc + dc[i]
            # row가 맵 밖이면
            if not (0 <= nr < n):
                continue
            # col가 맵 밖이면
            if not (0 <= nc < n):
                continue
            # 방문한 적 있으면
            if distance[nr][nc] != -1:
                continue
            # 이동하려는 곳이 1이면
            if graph[nr][nc] == 1:
                continue
            # 위에서 안 걸렸으면 이동한다
            queue.append((nr, nc))
            distance[nr][nc] = distance[cr][cc] + 1
    return 1

T = int(input())

for test_case in range(1, T + 1):
    # 입력
    N = int(input()) # 5
    # 그래프 만들자
    graph = [
        list(map(int, input().strip()))
        for _ in range(N)
    ]

    # 방문 가능여부만 확인하는 거였으면 visited로 했겠지만 최단거리이므로 distance로
    distance = [
        [-1] * N
        for _ in range(N)
    ]

    # 시작점이랑 도착점 찾아
    start = None
    end = None
    for r in range(N):
        for c in range(N):
            if graph[r][c] == 2:
                start = (r, c)
            if graph[r][c] == 3:
                end = (r, c)
    # 풀이
    min_dist = bfs(graph, start, end, distance) - 1

    # 출력
    print(f"#{test_case} {min_dist}")