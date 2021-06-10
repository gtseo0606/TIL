install.packages("lmtest")
library(lmtest)
install.packages("zoo")
library(zoo)
install.packages("sandwich")
library(sandwich)
install.packages("lattice")
library(lattice)
install.packages("astsa")
library(astsa)

getwd()
setwd("C:/Users/hbh2/Desktop")
book<-read.table("book.txt", header = F)
book<-ts(book, start = c(1))
time<-1:length(book)
time2<-time*time

model1<-lm(book ~ time) #모델 1
dwtest(model1)
summary(model1)
ts.plot(book, fitted(model1), xlab="day", ylab= "sold book", lty = 1:2, main = "일별 판매부수")
legend("topleft", legend = c("book", "fitted"), lty = 1:2)
ts.plot(resid(model1), type = "o", xlab = "day", ylab = "residual", main = "선형모형 적합 후 잔차"); abline(h=0)
acf(resid(model1), main = "잔차의 ACF")
acf2(resid(model1), main = "잔차의 ACF 와 PACF")

model2<-lm(book ~ time+time2) #모델 2
dwtest(model2)
summary(model2)
ts.plot(book, fitted(model2), xlab="day", ylab= "sold book", lty = 1:2, main = "일별 판매부수와 2차 추세모형 예측값")
legend("topleft", legend = c("book", "fitted"), lty = 1:2)
ts.plot(resid(model1), type = "o", xlab = "day", ylab = "residual", main = "2차 추세모형 적합 후 잔차"); abline(h=0)
acf(resid(model1), main = "잔차의 ACF")

lnbook<-log(book) 
model3<-lm(lnbook ~ time) #모델 3
summary(model3)

model4<-lm(lnbook ~ time+time2) #모델 4
summary(model4)

## Example 2.2 지시함수를 이용한 계절모형 적합
library(astsa) # acf2
library(lmtest) # dwtest
z<- scan()
dep <- ts(z, frequency = 12, start = c(1984, 1))
ts.plot(dep, ylab = "depart", main = "그림 2-10 백화점 매출액")
lndep<-log(dep)
ts.plot(lndep, ylab = "log depart", main = "그림 2-10 로그매출액")
# 분산의 크기를 줄이기 위해 로그이용
trend<- time(lndep)-1984
trend
y = factor(cycle(lndep)) #factor를 이용한 월별 factor 생성 / 1~12가 반복
y
reg <- lm(lndep ~ 0+trend+y) #선형계절추세모형
dwtest(reg)
summary(reg)

model.matrix(reg) # 모형 계획행렬
resid=ts(resid(reg), start = c(1984, 1), frequency = 12)
ts.plot(resid(reg), ylab="residual", main = "그림 2-11 잔차"); abline(h=0)
acf2(resid(reg), main="잔차의 ACF & PACF")

## Example 2.4 자기회귀오차모형
library(astsa) #acf2
dept<-scan()
n<-1:length(dept)
time<-ts(dept, frequency = 12, start = c(1984,1))
dept.ts<-ts(dept, frequency = 12, start = c(1984, 1))
lndept = log(dept.ts)
y=factor(cycle(time))
fit<-lm(lndept~0+time+y)
anova(fit)
summary(fit)
resid=ts(resid(fit), start = c(1984,1), frequency = 12)
acf2(resid, main="Residual ACF & PACF")

autoreg<- arima(residuals(fit), order=c(3,0,0))
summary(autoreg)
plot(resid(autoreg), main="그림 2-17 자기회귀모형 적합 후 잔차")
abline(h=0)


#-----------------------------------------------------------------------------------------------------------------------
## Example 3.1 단순지수평활법
install.packages("forecast")
library(forecast)
install.packages("Rcpp")
library(Rcpp)
install.packages("colorspace")
install.packages("ggplot2")
install.packages("gtable")
install.packages("scales")
install.packages("R6")
install.packages("lifecycle")
install.packages("munsell")
install.packages("glue")
install.packages("tibble")
install.packages("ellipsis")
install.packages("magrittr")
install.packages("pillar")
install.packages("crayon")
install.packages("pkgconfig")

install.packages("rlang")
library(rlang)


z<-read.table("mindex.txt", header = F)
z<-scan("C:/Users/hbh2/Desktop/mindex.txt")
mindex <- ts(z, start = c(1986, 1), frequency = 12) # 1986년부터 시작하는 월별 자료
#최적 평활상수 구하기
w <- c(seq(0.1, 0.8, 0.1), seq(0.81, 0.99, 0.01)) # 0.1에서 0.8까지 0.1간격, 0.81에서 0.99까지 0.01간격
sse <- sapply(w, function(x)
    return(sum(ses(mindex, alpha = x, h = 6)$residuals^2))) # x=평활상수, h는 6시차까지 예측
sse
w1 <- w[-c(1:6)] # x축은 0.7 ~ 1.0 / 그래프에서 w1= weight
sse1 <- sse1[-c(1:6)]
plot(w1, sse1, type = "o", xlab = "weight", ylab = "sse",
     main = "그림 3-2 1 시차 후 예측오차의 제곱합") 
w[which.min(sse)] # 최적 평활상수값 / 그래프를 그리면 가장 낮은(최소) 0.90이 최적평활상수

fit1 <- ses(mindex, alpha = 0.9, h = 6) # 최적 평활상수(0.9)가 alpha이자 x
fit1
acf(resid(fit1), lag.max = 12) 
# resid 오차항에 대한 자기상관계수가 있는지 최대 12차까지의 자기상관을 알아본다 / 그래프를 보니 자기상관이잇다
t.test(resid(fit1), mu = 0)
#예측오차의 평균이 0인지 아닌지 알아본다
# p값이 0.9095이므로 기각하지 못한다 = 오차들이 랜덤하게 발생되었다고 볼 수 있다
plot(fit1, xlab = "year", ylab = "mindex",
     main = "그림 3-1 중간재 출하지수와 단순지수평활값 alpha = 0.9",
     lty = 1, col = "black") #예측오차로 시계열 그림
lines(fitted(fit1), col = "red", lty =2)
legend("topright", legend = c("Mindex", "alpha = 0.9"),
       lty = 1:2, col = c("black", "red"))
plot(fit1$residuals, ylab = "residual",
     main = "그림 3-4 예측오차의 시계열그림 : alpha = 0.9") ; abline(h= 0)

fit2 <- ses(mindex, alpha = 0.2, h = 6) #최적 평활지수를 0.2로 둠
t.test(resid(fit2), mu = 0)
acf(resid(fit2), lag.max = 12)
plot(fit2, xlab = "year", ylab = "mindex",
     main = "그림 3-3 중간재 출하지수와 단순지수평활값 alpha = 0.2",
     lty = 1, col = "black") #예측오차로 시계열 그림
lines(fitted(fit2), col = "red", lty =2)
legend("topright", legend = c("Mindex", "alpha = 0.2"),
       lty = 1:2, col = c("black", "red"))
plot(fit2$residuals, ylab = "residual",
     main = "그림 3-5 예측오차의 시계열그림 : alpha = 0.2") ; abline(h= 0)

# 두 모형의 비교 / 예측의 정확도를 판정해주는 accurarcy에 대한 요약된 통계량을 알아본다  
round(rbind(accuracy(fit1), accuracy(fit2)), digits = 3) # ACF1 = 1시차에서의 오차의 자기상관 / 결과는 0.9가 모든 지표에서 0.2보다 예측오차에 잇어서 더 낫다

# 추정된 alpha를 이용한 단순지수평활법


#------------------------------------------------------------------------------
## Example 3.2 : 이중지수평활법

z<- scan("C:/Users/hbh2/Desktop/stock.txt")
stock <- ts(z, start = c(1984, 1), frequency = 12)

# 1 모수 이중지수평활
fit4<-holt(stock, alpha=0.6, beta = 0.6, h=6) #알파,베타는 평활상수, 상이하게 함  
fit4$model
plot(fit4, xlab = "year", ylab = "Index",
     main = "그림 3-6 주가지수와 이중지수평활값 alpha = beta = 0.6") #예측오차로 시계열 그림
lines(fitted(fit4), col = "red", lty =2)
legend("topleft", lty = 1:2, col = c("black", "red"), c("Index", "Double"))
plot(resid(fit4), main = "그림 3-7 예측오차의 시계열그림") ; abline(h= 0)
acf(resid(fit4))
t.test(resid(fit4), mu = 0)

# 2 모수 이중지수평활 alpha, beta
fit5 = holt(stock, h = 6)
fit5$model
plot(fit5, xlab = "year", ylab = "Index", lty=1, col = "black",
     main = "중간재 출하지수와 이중지수평활값 : alpha, beta estimated") #예측오차로 시계열 그림
lines(fitted(fit5), col = "red", lty =2)
legend("topleft", lty = 1:2, col = c("black", "red"), c("Index", "Double"))
plot(resid(fit5), main = "예측오차의 시계열그림: alpha, beta estimate") ; abline(h= 0)
acf(resid(fit5))
t.test(resid(fit5), mu = 0)

#--------------------------------------------------------------------------------
## Example 3.3 : Holt-Winters Method
library(forecast)
library(astsa)

z<- scan("C:/Users/hbh2/Desktop/koreapass.txt")
pass <- ts(z, start = c(1981, 1), frequency = 12)

# Holt Winters additive model 가법형 모델
fit6 <- hw(pass, seasonal="additive", h = 12)
fit6$model
plot(fit6, xlab = "year", ylab = "passenger", lty=1, col = "blue",
     main = "그림 3-8 가법모형") #예측오차로 시계열 그림
lines(fit6$fitted, col = "red", lty =2)
legend("topleft", lty = 1:2, col = c("blue", "red"), c("Pass", "Additive"))
ts.plot(resid(fit6), ylab="residual",
        main = "그림 3-10 가법모형의 예측오차") ; abline(h= 0)
acf(resid(fit6), main = "Residual ACF")
t.test(resid(fit6), mu = 0)

# Holt Winter multiplivative model 승법형 모델
fit7 <- hw(pass, seasonal="multiplicative", h = 12)
fit7$model
plot(fit7, xlab = "year", ylab = "passenger", lty=1, col = "blue",
     main = "그림 3-8 승법모형") #예측오차로 시계열 그림
lines(fit7$fitted, col = "red", lty =2)
legend("topleft", lty = 1:2, col = c("blue", "red"), c("Pass", "Multiplicative"))
ts.plot(resid(fit7), ylab="residual",
        main = "그림 3-11 승법모형의 예측오차") ; abline(h= 0)
acf(resid(fit7), main = "Residual ACF")
t.test(resid(fit7), mu = 0)

#------------------------------------------------------------------------------
## Example 4.1 : 분해법
library(forecast) # auto.arima
library(lubridate) # smd

z<-scan("C:/Users/hbh2/Desktop/food.txt")
t<- 1:length(z)
food<-ts(z, start = c(1981, 1), frequency = 12)
fit <- lm(food ~ t) #종속 food 설명변수 t
anova(fit) #F값으로 선형이 적합하다
summary(fit) 
trend <- fitted(fit)
ts.plot(food, trend, col = 1:2, lty = 1:2, xlab = "time", ylab = "food",
        main= "그림 4-1 원시계열과 분해법에 의한 추세분석")
legend("topleft", lty = 1:2, col = 1:2, c("원시계열", "추세성분"))
adjtrend = food/trend
y = factor(cycle(adjtrend)) #지시변수
fit41 <- auto.arima(adjtrend, max.p = 2, xreg = model.matrix(~ 0 + y)[,-12],
                    seasonal = F, max.d = 0, max.q = 0)
# 비계절형 아리마모형을 찾는 걸 막아준다
# ar1, ar2 최대시차 2, 잔차부분에 잇어서 시계열자료라 독립되지 못하고 상관관계가 남아있고 그것을 ar2까지
# 지시변수(가변수)로 디자인행렬을 이용 / 각 행,렬이 독립적이지 않다. 
# 12번째가 의존적이라고 생각해서 제거해줌
#differnce 최대 차분의 개수, q 이동평균의 최대항 수
#AIC, BIC를 기준으로 데이터가 적합한 아리마 모형을 단계적으로 추정해줌 
fit41
seasonal <- fit41$fitted
pred = trend*seasonal
irregular = food/pred # food = t c i s, pred = t c s, i만 남게됨
ts.plot(seasonal, main = "그림 4-2 분해법에 의한 계절성분")
ts.plot(irregular, main = "그림 4-3 분해법에 의한 불규칙성분"); abline(
h=1.0)
acf(irregular, main = "불규칙성분의 ACF")
ts.plot(food, pred, lty=1:2, ylab = "food", col = c("blue", "red"),
        main = "그림 4-4 원시계열과 예측값")
legend("topleft", lty = 1:2, col = 1:2, c("원시계열", "추세성분"))

date <- ymd("810101") + months(1:length(food)-1)
table4<-data.frame(date, food, trend, seasonal, irregular)
table4

#------------------------------------------------------------------------------
## Example 4.3 이동평균법을 이용한 계절조정
library(forecast)
z <- scan()
food <- ts(z, start=c(1981,1), frequency=12)
m <- decompose(food, type=c("additive"))
trend <- trendcycle(m)
seasonal <- seasonal(m)
irregular <- remainder(m)
adjseasonal <- food-seasona

ts.plot(food, trend, ylab="food", lty=1:2, col=c("blue","red"), main="그림 4-7 원시계열과 추세순환 성분")
legend("topleft", lty =1:2, col=c("blue", "red"), c("원시계열", "추세성분"))

ts.plot(food, seasonal, ylab="food", lty=1:2, col=c("blue","red"), main="그림 4-8 원시계열과 계절성분")
legend("topleft", lty =1:2, col=c("blue", "red"), c("원시계열", "계절성분"))

ts.plot(food, irregular, ylab="food", lty=1:2, col=c("blue","red"), main="그림 4-9 원시계열과 추세순환 성분")
legend("topleft", lty =1:2, col=c("blue", "red"), c("원시계열", "불규칙성분"))

ts.plot(food, adjseasonal, ylab="food", lty=1:2, col=c("blue","red"), main="그림 4-10 원시계열과 추세순환 성분")
legend("topleft", lty =1:2, col=c("blue", "red"), c("원시계열", "계절조정"))

# decompose 말고 stl을 이용한 분해법
food.stl <-stl(food, "periodic")
plot(food.stl)
food.sa <- seasadj(food.stl)
ts.plot(food, food.sa, ylab="food", lty=1:2, col=c("blue","red"), main="Figure 4.10 원시계열과 계절조정")
legend("topleft", lty =1:2, col=c("blue", "red"), c("원시계열", "계절조정"))

