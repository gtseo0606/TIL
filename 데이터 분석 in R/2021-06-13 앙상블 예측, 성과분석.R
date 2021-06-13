## 배깅, 부스팅, 랜덤포레스트 앙상블 예측, 성과분석

# 예측
pred<-predict(model, test[,-k], type="prob")
pred_1<-data.frame(pred$prob, Y=pred$class)

# 성과분석
pred.rf<-predict(model, test[,-k], type="class")
confusionMatrix(data=pred.rf, reference=test[,k], positive="No")