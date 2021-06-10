# 결측치

## 1. 결측치, NA 개수 확인

### 방법 1
summary(data)

### 방법 2
dlookr :: diagnose(data)

### 방법 3
apply(data, 2, function(x) sum(is.na(x)))


## 2. 결측치 삭제

### 방법 1
na.omit(data)

### 방법 2
data[complete.cases(data),] # 전체 행 삭제
data[complete.cases(data[,11]),] #일부 행(11번째) 삭제


## 3. 결측치 대체

### 방법 1
data$X1 <- inputate_na(data, X1, method = “mean”) #평균값 대체, “median” #중앙값 대체, “mode” #최빈값

### 방법 2
DMwR :: centralImputation(data) # 수치형은 중앙값으로, 범주형은 최빈값으로 대체

### 방법 3
levels(data$X1)
levels(data$X1)[k] <- NA

table(data$X1, useNA="always")

### 방법 4
data$X1 <- ifelse(data$X1 == "", NA, data$X1) # "" -> NA
data$X1 <- ifelse(is.na(data$X1) == TRUE, 0, data$X1) # NA -> 0
