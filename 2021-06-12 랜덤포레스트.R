# 랜덤포레스트

rf.model <- randomForest(data.Y~., data=train, ntree=50, mtry = sqrt(변수개수), importance=T)
rf.model #OOB 에러추정치로 낮을수록 분류모델의 성능이 좋다.
plot(rf.model, 'I') #ntree 개수

names(rf.model)
rf.model$importance
importance(rf.model)
order(importance(rf.model)[,MeanDecreaseAccuracy], decreasing=T) 

#중요변수 순으로 정렬
varImpPlot(rf.model) # 변수중요도 그래프

#caret 패키지
pre <- predict(rf.model, test[,-1], type="class")
confusionMatrix(data=pre, reference=test[,1], positive='1') 
#정분류율(Accuracy), 민감도(Sensitivity), 특이도(Specificity)

#ROCR 패키지
pre.roc <- prediction(as.numeric(pre), as.numeric(test[,1]))
plot(performance(pre.roc, "tpr", "fpr"))
abline(a=0, b=1, lty=2, col="black")
performance(pre.roc, "auc")@y.values[[1]] 