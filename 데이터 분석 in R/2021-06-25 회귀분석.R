### 회귀분석

## 1. 회귀분석
lm.model <- lm(Y~X1+X2, data)
summary(lm.model) # R^2 
accuracy(lm.model) #RMSE 작을수록 더 정확한 모형이다
idx <- sample(1:nrow(data), nrow(data)*0.7, replace=FALSE)
train <- data[idx,]
test <- data[-idx,]
lm.model2 <- lm(Y~X1+X2, train)
pre <- predict(lm.model2, newdata = test)
pre <- predict(lm.model2, newdata = test, interval = "confidence") 
#점추정 + 구간추정
pre <- as.data.frame(pre)
pre
result <- cbind(pre, data$Y)
result
result$accuracy1 <- ifelse (result$pre > result$Y, 1-(result$Y/result$pre), 1-(result$pre/result$Y) ) 
t<- mean(result$accuracy1)
t

## 2. 회귀분석 성능 향상
step(lm(Y~1, data), scope =list(lower=~1, upper = X1+X2+X3), direction="both")
summary(lm(Y~X1+X2, data))

## 3. 잔차분석
#1. 독립성 : lmtest :: dwtest(model) : 더빈왓슨값이 0에 가까우므로 독립성을 만족
#2. 정규성 : shaprio.test(resid(model)) : p-value가 매우 작으므로 귀무가설 기각, 정규성 X
#3. 등분산성 : par(mfrow=c(2,2)); plot(lm_model)

## 4. 잔차분석 해석
#Residuals vs Fitted(등분산성 가정확인) : 빨간선이 직선의 성향이라 잔차는 0을 중심으로 고르게 분포
#Scale-Location(등분산성 가정확인) : 빨간선의 기울기가 0에 가깝고 점들이 random한 형태로 나타나야 등분산성을 만족합니다. 표준화 잔차가 선에서 멀어질수록 이상치에 가까움
#Residuals vs Leverage(영향력 진단) : 쿡의 거리가 0.5이상이면 빨간 점선으로 표현된다. 점선 바깥쪽에 있고 숫자가 있는게 보통 이상치이다. 회귀직선에 영향을 미치는 점은 없다.