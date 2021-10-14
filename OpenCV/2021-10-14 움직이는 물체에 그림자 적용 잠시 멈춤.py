opencv 동영상 움직이는 물체에 그림자 적용 잠시 멈춤
변화가 있는것은 흰색, 없는 것은 검은색으로

time.sleep(0.03)


# 동영상에 움직이는 물체의 그림자까지 나옴

import numpy as np
import cv2
import time

i = 0 # 카운트 변수
th = 30 # 차분이미지의 한계

cap = cv2.VideoCapture('C:\\Users\\Playdata\\Downloads\\myImg\\0105.mp4') # 배구영상
ret, bg = cap.read()
bg = cv2.cvtColor(bg, cv2.COLOR_BGR2GRAY)

while (cap.isOpened()): # 계속 새로운 장면을 넣음
   # 프레임 취득
   ret, frame = cap.read()
   
   # 그레이 스케일 변환
   gray= cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

   # 차이의 절대값을 계산
   mask = cv2.absdiff(gray, bg)
   
   # 차등 이미지를 두 값의 마스크 이미지를 산출
   # 변화가 있는것은 흰색, 없는 것은 검은색으로
   mask[mask<th] = 0 # 불리언 인덱스 : True에 해당하는 것만 추출
   mask[mask>=th] = 255 #변화가 크면 흰색

   # 프레임과 마스크 이미지 보기
   cv2.imshow("Background", bg) # 0.03초의 딜레이
   cv2.imshow("Frame", gray)
   cv2.imshow("Mask", mask)
   
   # 대기 (0.03sec) # 0.03초의 딜레이
   time.sleep(0.03) # 높아질수록 동영상 속도가 느려짐
   i += 1 # 카운트 1 증가 # 증가할수록 공의 그림자가 늘어남
   
   # 배경이미지 업데이트(간격)
   if (i>30):
       ret, bg = cap.read()
       bg = cv2.cvtColor(bg, cv2.COLOR_BGR2GRAY)
       i = 0

   if cv2.waitKey(1) & 0xFF ==  ord('q'): # q를 누르면 종료
       break
   
cap.release()
cv2.destroyAllWindows()
