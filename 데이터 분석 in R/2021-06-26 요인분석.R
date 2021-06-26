## 요인분석

## 1. 역문항 처리
data1 <- data
data1 <- data1 %>% mutate(A1=6-A1,
                          A2=6-A2
                          A3=6-A3,
                          A4=6-A4,
                          A5=6-A5) %>% select_all

## 2. 요인회전 및 요인적재값
library(GPArotation)
data_EFA <- principal(data1, nfactors=5, rotate='varimax')
print(data_EFA$loadings, sort=T, cutoff=0.5)

## 3. 고유값
evalue <- data.frame(values=data_EFA$values)
head(evalue, 5)

## 4. 각 변수에 대한 공통성
communality <- data.frame(communality=data_EFA$communality)
head(communality, 5)

## 5. 표본의 적절성 (최소 0.6이상, 보통 0.8이상)
KMO(data1)

## 5-1. 현재 데이터로 요인분석을 해도 되는 지 적합성 확인
#(p-value > 0.05)
cortest.bartlett(data1)

## 5-2. 개별 신뢰도 분석
round(psych :: alpha(data1)$total$raw_alpha, 3)

## 5-3. X1, X2에 따라 Y에 대한 평균 비교
data2 <- data1 %>% mutate(Y=rowMeans(data1, na.rm=T)) %>% select_all

## 방법 1
# 일원배치분산분석(p-value > 0.05이면 통계적으로 유의한 평균의 차이가 없다 
# -> 등분산성을 만족->anova)
lawstat :: levene.test(data1$Y, data2$X1, location="mean", correction.method="zero.correction")
data2_aov<-aov(Y~X1, data=data2)
anova(data2_aov) #

## 방법 2
# 독립t검정(X2에 따라 Y에 대해 통계적으로 유의한 평균의 차이가 없다.)
var.test(Y~X2, data=data2) #p-value > 0.05이면 등분산성 만족
t.test(Y~X2, var.equal=T, data=data2) 

## 6. 요인에 대한 상관분석,상관도표

## 6-1. 요인 계산
data2 <- data2 %>% mutate(X1 = rowMeans(data2, na.rm=T)) %>% select_all

## 6-2. 상관분석
#(probability values <0.05이면 통계적으로 유의한 상관관계가 있다.)
data2_corr <- corr.test(data2, use="pairwise", method="pearson", alpha=0.5)
data2_corr

## 6-3. 상관관계 그래프
plot(data2, lower.panel=NULL)