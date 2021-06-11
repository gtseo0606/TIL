#다중선형회귀분석

result <- lm(Y~., data= data2)
summary(result)
anova(result)

## 1. 변수선택(MASS, car 패키지)
step <- MASS :: stepAIC(result, direction="both") 
# 제거된 독립변수가 없다. 모든 독립변수가 Y에 영향을 줌

step <- stepAIC(result, scope=list(upper=~ .^2, lower= ~1)) 
# 상수모형에서 2차상호작용을 포함한 모형까지

summary(step)

## 2. 표준화 회귀계수
reg <- data.frame(scale(data2))
ms.result <- lm(Y~., data= reg)

summary(ms.result) # x3,x2,x1 순으로 y에 크게 영향을 준다.

## 3. VIF, 더빈-왓슨
round(vif(step), 3) 
# VIF가 10이하면 다중공산성에 문제는 없다.

vif(lm(Y~., data=data)) > 10 
# 실행시 FALSE로 나오면 다중공산성에 문제는 없다.

## 4. 오차항의 1차 자기상관관계
durbinWatsonTest(step) #p-value > 0.05이므로 자기상관관계가 없음

## 5. 더미변수 처리
data3 <- data2 %>%sjmisc :: to_dummy(X1, suffix= "label") 
%>% bind_cols(data2) %>% select_all

dummy_reg <- lm(Y~., data= data3)
summary(dummy_reg)
anova(dummy_reg)

## 6. 모형평가
yhat_lm <- predict(model, newdata=validation)
rmse(validation$Y, yhat_lm)