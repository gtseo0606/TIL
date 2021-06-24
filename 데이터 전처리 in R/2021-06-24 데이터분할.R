#### 데이터 분할

### 1. train/test 데이터 나누기

## 방법 1
idx <- sample(1:nrow(data), size = nrow(data)*0.7, replace=FALSE)
train <- data[idx,]
test <- data[-idx,]

train1 <- train[,c("X1","X2")] #원하는 변수만 사용
test1 <- test[,c("X1","X2")]

## 방법 2
part <- caret :: createDataPartition(data$Y, p=0.7)
idx <- as.vector(part[[1]])
train <- data[idx,]
test <- data[-idx,]

### 2. train/validate/test 데이터 나누기

## 방법 1
n <- nrow(data)
idx <- 1:n

training_idx <- sample(idx, n*0.60)
idx <- setdiff(idx, training_idx)

validate_idx <- sample(idx, n*0.20)

test_idx <- setdiff(idx, validate_idx)

training <- data[training_idx, ]
validation <- data[validate_idx, ]
test <- data[test_idx, ]