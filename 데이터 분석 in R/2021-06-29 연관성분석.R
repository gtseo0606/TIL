## 연관성 분석
## arules 패키지
m <- apriori(data, parameter = list(support = 0.01, confidence = 0.6)))
# 최소지지도(support) 이상의 빈발항목집합을 찾고 연관규칙을 계산
# 값이 클수록 유용한 규칙을 나타내는 신뢰도(confidence)와 합쳐, n개의 연관규칙이 생성된다.

# 최소지지도와 신뢰도를 높여서 규칙의 개수를 줄일 수 있다.
small <- subset(m, subset = rhs %in% "income=small" & lift > 1.2)
length(small)
large <- subset(m, subset = rhs %in% "income=large" & lift > 1.2)
length(large)

inspect(head(sort(small, by = "confidence"), n = 3))
inspect(head(sort(large, by = "confidence"), n = 3)) #n=상위 3개의 연관규칙만 확인

# lift(향상도)는 1이면 좌측항과 우측항의 연관성은 없다. 1보다 크면 유용한 규칙이다.
# 좌측항의 결과를 가진 사람의 income은 small이다
# income이 small인 사람은 남자고 미국국적이다. 이런사람에게 저축상품이나 재무상담을 추천해줄수있다.
