![image](https://user-images.githubusercontent.com/74644453/209628435-ec84c893-c432-4c60-9801-d21cbaf31ade.png)



1. Operator : conv2d 3x3

Input = 224x224x3

t,c,n,s = (-, 32, 1, 2)

+ 3x3x3x32

-> 112x112x32

=======================

Operator : bottleneck
t : expansion factor
c : output channel의 수
n : 반복 횟수
s : stride
hx w x k 
-> h x w x (tk)
-> h/s x w/s x (tk)
-> h/s x w/s x k`(channel)

========================

2. Operator : bottleneck
Input = 112x112x32
t,c,n,s = (1, 16, 1, 1)
-> 112x112x(1x32)
-> (112/1)x(112/1)x(1x32)
-> (112/1)x(112/1)x16

3. Operator : bottleneck
Input = 112x112x16
t,c,n,s = (6, 24, 2, 2)
-> 112x112x(6x32) = 112x112x192
-> (112/2)x(112/2)x(6x32) = 56x56x192
-> (112/2)x(112/2)x24 = 56x56x24

4. Operator : bottleneck
Input = 56x56x24
t,c,n,s = (6, 32, 4, 2)
-> 56x56x(6x24) = 56x56x144
-> (56/2)x(56/2)x(6x24) = 28x28x144
-> (56/2)x(56/2)x32 = 28x28x32

5. Operator : bottleneck
Input = 28x28x32
t,c,n,s = (6, 64, 4, 2)
-> 28x28x(6x32) = 28x28x192
-> (28/2)x(28/2)x(6x32) = 14x14x192
-> (28/2)x(28/2)x64 = 14x14x64

6. Operator : bottleneck
Input = 14x14x64
t,c,n,s = (6, 96, 3, 1)
-> 14x14x(6x64) = 14x14x384
-> (14/1)x(14/1)x(6x64) = 14x14x384
-> (14/1)x(14/1)x96 = 14x14x96

7. Operator : bottleneck
Input = 14x14x96
t,c,n,s = (6, 160, 3, 2)
-> 14x14x(6x96) = 14x14x576
-> (14/2)x(14/2)x(6x96) = 7x7x576
-> (14/2)x(14/2)x160 = 7x7x160

8. Operator : bottleneck
Input = 7x7x160
t,c,n,s = (6, 320, 1, 1) 
-> 7x7x(6x160) = 7x7x960
-> (7/1)x(7/1)x(6x160) = 7x7x960
-> (7/1)x(7/1)x320 = 7x7x320

9. Operator : conv2d 1x1
Input = 7x7x320
t,c,n,s = (-, 1280, 1, 1) 
+ 1x1x1280
-> 7x7x1280

10. Operator : avg_pooling 7x7
Input = 7x7x1280 
-> 1x1x1280

11. Operator : conv2d 1x1
Input = 1x1x1280 
+ 1x1xk
-> 1x1xk
