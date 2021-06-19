## 函荐 积己

# 1. 函荐 积己, 犁裹林拳

#规过 1
data <- transform(data, new_X1 <- X1+X2+X3)

#规过 2
data<-within(data,
             {X1_Class=character(0) #integer(0)
             X1_Class[ X1 %in% c("LS", "ST", "RS", "RW") ]="Forward"+
               X1_Class[ X1 %in% c("LAM", "CAM", "RAM", "RM") ]="Midfielder"} )

#规过 3
data<- data %>% mutate(X1 = dplyr :: case_when(X1 == "LS" ~ "Forward",
                                               (X1 %in% c("LAM", "CAM") ~ "Midfielder"))
                       
#2. 单捞磐 屈怕 函券

#规过1
data$X1_Class<-factor(data$X1_Class, 
                     levels=c("Forward", "Midfielder"), 
                     labels=c("Forward", "Midfielder"))