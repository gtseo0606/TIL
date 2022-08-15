###상관분석
##1. 피어슨 상관분석
#방법 1
cor(Y, X1) #양/음의 상관관계를 가진다.

cor.test(Y, X1) #p-value < 0.05이면 두 변수간 상관관계가 유의하다.

cor(data[,-k]) # 상관계수행렬

#시각화
plot(data[,-k]) #상관행렬 그래프

corrplot :: corrplot(cor(data[,-k])) #히트맵

corrgram :: corrgram(data[,-k], upper.panel = panel.conf) #히트맵 + 상관계수값
