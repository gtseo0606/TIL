### 분산분석(anova)

## x1의 범주가 y 평균값의 차이가 있는가?
  ex) 귀무가설 : x1(2개의 범주 : 포워드, 미드필더)에 대해 y(연봉)의 평균은 모두 같다.

## X별 Y 평균의 차이가 있는지 통계적으로 검증
summary(aov(Y~X1, data)) #1원배치 

summary(aov(Y~X1+X2+X1:X2, data)) #2원배치 X1:X2=상호작용효과
# p-value <0.05 이면 귀무가설 기각
# 적어도 어느 한 포지션은 통계적으로 유의한 차이가 있다. 
# 연봉 평균값에 차이가 있다.

TukeyHSD(aov(Y~X1, data)) # 사후검정 p adj > 0.05면 유의한 차이가 있다. 

## X에 따른 Y의 차이가 있는지 통계적으로 검증
var.test(Y~X1, data, alternative = "two.sided") 
#p-value > 0.05이면 귀무가설을 기각하지 않음 = 등분산 가정을 만족

t.test(Y~X1, data, alternative = "two.sided", var.equal = TRUE)
#p-value > 0.05이기에 X1과 X1이 아닌 데이터 간 Y에는 통계적으로 유의한 차이가 없다.