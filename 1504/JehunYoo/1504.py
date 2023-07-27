import sys
import heapq
input = sys.stdin.readline
INF = int(1e9)

def shortest(graph, start, end) -> int:
    distance = [INF for _ in range(N + 1)]
    distance[start] = 0
    heap = []
    heapq.heappush(heap, (0, start))

    while heap:
        dist, node = heapq.heappop(heap)
        if distance[node] < dist:
            continue
        for neighbor, d in enumerate(graph[node][1:], 1):
            if d != INF and (shorter := dist + d) < distance[neighbor]:
                distance[neighbor] = shorter
                heapq.heappush(heap, (shorter, neighbor))
    
    return distance[end]

N, E = map(int, input().split())
graph = [[INF for _ in range(N + 1)] for _ in range(N + 1)]
for i in range(N + 1):
    graph[i][i] = 0
for _ in range(E):
    a, b, c = map(int, input().split())
    graph[a][b] = graph[b][a] = c
v1, v2 = map(int, input().split())

bridge = shortest(graph, v1, v2)
answer = min(shortest(graph, 1, v1) + bridge + shortest(graph, v2, N), shortest(graph, 1, v2) + bridge + shortest(graph, v1, N))

if answer < INF:
    print(answer)
else:
    print(-1)