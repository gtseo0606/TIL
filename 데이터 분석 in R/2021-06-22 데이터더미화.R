### 더미화
## 1. 변수 수치화 후 더미화
# 방법 1
data1 <- data %>% mutate(X1 = as.numeric(X1), X2 = as.numeric(X2))

dummy <- dummyVars("~X1+X2", data1)

new_df <- data.frame(predict(dummy, newdata = data1))

data2 <- cbind(data, new_df)
