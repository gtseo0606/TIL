#### 데이터 시각화

### 1.독립변수(X)별 종속변수(Y) 시각화

##방법 1
data_list <- (colnames(data) != "Y") %>% which()

lst <- map(data_list, function(i) {df_list <- colnames(data)[i]

data %>% select(df_list, count) %>% rename(aa <- df_list) 
     %>% geom_point(alpha=.2, color = "#008ABC" 
               + labs(title = paste0(df_list, "vs Y"), 
                      + x = df_list, y = "", color = df_list)
               + theme_bw() 
               + theme(legend.positive = "bottom")})

grid.arrange(grobs = lst, ncol = 2)

## 방법 2
# 종속변수를 잘 설명하는 독립변수 선택(회귀분석, 예측분석)
pairs(data %>% sample_n(min(1000, nrow(data))),
      lower.panel=function(x,y){points(x,y); abline(0, 1, col='red')},
      upper.panel = panel.cor) 

## 방법 3
# 변수와 빈도수
# ggplot2, dplyr, gridExtra 패키지

p1 <- data %>% ggplot(aes(Y)) + geom_bar()
p2 <- data %>% ggplot(aes(factor(Y), X1)) + geom_boxplot()
p3 <- data %>% ggplot(aes(factor(Y), X1)) + geom_point(alpha=0.1) + geom_smooth()

grid.arrange(p1,p2,p3, ncol=2)
