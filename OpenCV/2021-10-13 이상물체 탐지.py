opencv 동영상 이상물체 탐지

diff01 = cv2.absdiff(frame1, frame2)
cv2.bitwise_and(diff01, diff02)
cv2.medianBlur
cv2.countNonZero(mask)


# 10. 감시카메라 등에서 움직이는 물체를 악성물질이라 탐지하고 사진으로 찍어서 저장해보자
#1. 차분법을 이용한 함수로 생성

import numpy as np
import cv2
import time

def MyFrame_sub(frame1, frame2, frame3, th):
   #1-1 프레임의 절대 차이값 지정
   diff01 = cv2.absdiff(frame1, frame2) #흰
   diff02 = cv2.absdiff(frame2, frame3)

   # 1-2 차분이미지 교집합
   diff_res = cv2.bitwise_and(diff01, diff02) # diff1 반환
   
   # 차등 이미지를 두 값의 마스크 이미지를 산출
   diff_res [ diff_res < th] = 0    # 10이하면 검
   diff_res [ diff_res >= th] = 255 # 10이상이면 흰
   
   #블러링 작업
   mask = cv2.medianBlur(diff_res, 5) # 소금후추
   return diff_res
   
# 2. 악성물질 (의심물질)을 판정값
fw = 1000

#3. 카메라 연결
cap = cv2.VideoCapture(0)

#4. 프레임 3개를 지정한 후 흑백으로 변경
f1= cv2.cvtColor(cap.read()[1], cv2.COLOR_BGR2GRAY)
f2= cv2.cvtColor(cap.read()[1], cv2.COLOR_BGR2GRAY)    
f3= cv2.cvtColor(cap.read()[1], cv2.COLOR_BGR2GRAY)

CNT = 0 # 반복문 안에 파일이름으로 카운트 frame0.jpg, frame1.jpg

while cap.isOpened():
   #5. 영상을 읽어서 MyFrame_sub(frame1, frame2, frame3)으로 차분한다
   #mask = MyFrame_sub(f1, f2, f3, fw)
   mask = MyFrame_sub(f1, f2, f3, th=10)
   
   #6. 5번의 결과로 얻은 이미지 하얀색만 픽셀수를 추출 cv2.countNonZero() => 배경말고 객체만 추출
   res = cv2.countNonZero(mask)

   #7. 6번의 결과값 res 2번 fw보다 크면 의심물체로 판정후 이미지저장
   if res > fw :
       print("의심되는 물체 : ", CNT)
       filename= 'C:\\myclip\\frame' +str(CNT) + ".jpg"
       cv2.imwrite(filename, f2)
       CNT += 1
       
   #8. 프레임 확인
   cv2.imshow("Flame2", f2)
   cv2.imshow("Mask", mask)
   
   #9. 배경이미지업데이트
   f1 = f2
   f2 = f3
   f3 = cv2.cvtColor(cap.read()[1], cv2.COLOR_BGR2GRAY)

   if cv2.waitKey(1) & 0xFF ==  ord('q'): # q를 누르면 종료
       break

cap.release()
cv2.destroyAllWindows()
