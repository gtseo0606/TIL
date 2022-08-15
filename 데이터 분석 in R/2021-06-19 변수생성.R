## 변수 생성

# 1. 변수 생성, 재범주화

#방법 1
data <- transform(data, new_X1 <- X1+X2+X3)

#방법 2
data<-within(data,
             {X1_Class=character(0) #integer(0)
             X1_Class[ X1 %in% c("LS", "ST", "RS", "RW") ]="Forward"+
               X1_Class[ X1 %in% c("LAM", "CAM", "RAM", "RM") ]="Midfielder"} )

#방법 3
data<- data %>% mutate(X1 = dplyr :: case_when(X1 == "LS" ~ "Forward",
                                               (X1 %in% c("LAM", "CAM") ~ "Midfielder"))
                       
#2. 데이터 형태 변환

#방법1
data$X1_Class<-factor(data$X1_Class, 
                     levels=c("Forward", "Midfielder"), 
                     labels=c("Forward", "Midfielder"))
