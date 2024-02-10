n, m = map(int, input().split())

# Создаем список ребер
edges = []
for i in range(m):
    a, b = map(int, input().split())
    edges.append((a, b, 1))  # вес каждого ребра равен 1

# Сортируем список ребер по возрастанию весов
edges.sort(key=lambda x: x[2])

# Используем алгоритм Крускала для построения минимального остовного дерева
parent = list(range(n+1))  # список для хранения родительских вершин
rank = [0]*(n+1)  # список для хранения рангов деревьев

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    xroot = find(x)
    yroot = find(y)
    if xroot == yroot:
        return
    if rank[xroot] < rank[yroot]:
        parent[xroot] = yroot
    elif rank[xroot] > rank[yroot]:
        parent[yroot] = xroot
    else:
        parent[yroot] = xroot
        rank[xroot] += 1

new_plan = []  # список ребер в новом плане
for edge in edges:
    a, b, w = edge
    if find(a) != find(b):
        union(a, b)
        new_plan.append((a, b))
        if len(new_plan) == n-1:  # все вершины добавлены в дерево
            break

# Выводим количество рейсов в новом плане и список ребер
print(len(new_plan))
for edge in new_plan:
    print(edge[0], edge[1])
