# Support Vector Machine(SVM)
## e1071 패키지

tune.svm(data.Y~., data=data, gamma = 10^(-6:-1), cost = 10^(1:2)) 
# best parameters를 제공
# gamma = 초평면의 기울기, cost = 과적합을 막는 정도, default=1

svm.model <- svm(data.Y~., data=data, kenel = "radial", gamma = 0.01, cost = 10)
# kernel = radial, linear, polynomial, sigmoid 정확도에 큰영향을 주진 않음

summary(svm.model) 
# Number of Support Vector(전체 중 나눠진 개수), Number of Classes(클러스터 수)

## caret 패키지
pre <- predict(svm.model, test[,-1], type="class")
confusionMatrix(data=pre, reference=test[,1], positive='1') 
# 정분류율(Accuracy), 민감도(Sensitivity), 특이도(Specificity)

## ROCR 패키지
pre.roc <- prediction(as.numeric(pre), as.numeric(test[,1]))
plot(performance(pre.roc, "tpr", "fpr"))
abline(a=0, b=1, lty=2, col="black")

performance(pre.roc, "auc")@y.values[[1]] 
