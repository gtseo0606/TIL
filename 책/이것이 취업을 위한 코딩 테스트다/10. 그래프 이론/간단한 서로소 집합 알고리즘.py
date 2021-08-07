# 특정 원소가 속한 집합을 찾기
def find_parent(parent, x): # parent,1 parent,4
    # 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
    if parent[x] != x: # 1 != 1 
        return find_parent(parent, parent[x]) # parent, 1
    return x #1 #4

# 두 원소가 속한 집합을 합치기
def union_parent(parent, a, b): # parent,1,4 parent,2,3 parent,2,4 parent,5,6
    a = find_parent(parent, a) #1 
    b = find_parent(parent, b) #4
    if a < b: #1<4
        parent[b] = a
    else:
        parent[a] = b

# 노드의 개수와 간선(Union 연산)의 개수 입력 받기
v, e = map(int, input().split()) # 6, 4
parent = [0] * (v + 1) # 부모 테이블 초기화하기 # 7

# 부모 테이블상에서, 부모를 자기 자신으로 초기화
for i in range(1, v + 1): #1, 7
    parent[i] = i 

# Union 연산을 각각 수행
for i in range(e): # 4
    a, b = map(int, input().split()) # 1,4 2,3 2,4 5,6
    union_parent(parent, a, b) # parent,1,4 parent,2,3 parent,2,4 parent,5,6

# 각 원소가 속한 집합 출력하기
print('각 원소가 속한 집합: ', end='')
for i in range(1, v + 1):
    print(find_parent(parent, i), end=' ')

print()

# 부모 테이블 내용 출력하기
print('부모 테이블: ', end='')
for i in range(1, v + 1):
    print(parent[i], end=' ')
