from collections import deque
import copy

# 노드의 개수 입력받기
v = int(input()) # 5
# 모든 노드에 대한 진입차수는 0으로 초기화
indegree = [0] * (v + 1) # [0, 0, 0, 0, 0, 0]
# 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트(그래프) 초기화
graph = [[] for i in range(v + 1)] # [[], [], [], [], [], []]
# 각 강의 시간을 0으로 초기화
time = [0] * (v + 1) # [0, 0, 0, 0, 0, 0]

# 방향 그래프의 모든 간선 정보를 입력받기
# 노드 - 간선 - 시간 - 진입차수 - 그래프
for i in range(1, v + 1): #1~5
    data = list(map(int, input().split())) #[10 -1] #[10 1 -1] #[4 1 -1] #[4 3 1 -1]
    time[i] = data[0] # 첫 번째 수는 시간 정보를 담고 있음 #time[1~5]=10 #time[1~5]=10 #time[1~5]=4 
    for x in data[1:-1]:   #X #x=1 #x=1 #x=3 1
        indegree[i] += 1   #X #indegree[1~5]=1 #indegree[1~5]=1 #indegree[1~5]=2
        graph[x].append(i) #X #graph[1]=[1~5] #graph[1]=[1~5] #graph[3]=[1~5] graph[1]=[1~5]

# 위상 정렬 함수
def topology_sort():
    result = copy.deepcopy(time) # 알고리즘 수행 결과를 담을 리스트 #[10, 10, 10, 10, 10, 10] 
    q = deque() # 큐 기능을 위한 deque 라이브러리 사용

    # 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
    for i in range(1, v + 1): #1~5
        if indegree[i] == 0: #indegree[1~5] == 0
            q.append(i) #q=deque([1~5]) #q=deque([])

    # 큐가 빌 때까지 반복
    while q:
        # 큐에서 원소 꺼내기
        now = q.popleft() #now=1~5 #X
        # 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
        for i in graph[now]: #X #i=[[],[1],[],[],[]]
            result[i] = max(result[i], result[now] + time[i]) #result[0]=max(result[0], result[2]+time[0])
            indegree[i] -= 1 #indegree[0] = -1
            # 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
            if indegree[i] == 0: #indegree[0] == 0
                q.append(i) #X 

    # 위상 정렬을 수행한 결과 출력
    for i in range(1, v + 1):
        print(result[i])

topology_sort()
