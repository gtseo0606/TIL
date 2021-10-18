opencv 동영상 동영상 사이즈 변경

# 5. 비디오 속성값을 변경한 avi를 실행해보자
import numpy as np
import cv2

filepath = 'C:\\Users\\Playdata\\Downloads\\myImg\\01_People.avi'
cp = cv2.VideoCapture(filepath)

print(cp.set(cv2.CAP_PROP_FRAME_WIDTH, 250))
print(cp.set(cv2.CAP_PROP_FRAME_HEIGHT, 200))
print(cp.set(cv2.CAP_PROP_FPS, 20))

print(cp.get(cv2.CAP_PROP_FPS))

while(cp.isOpened()):
   #프레임으로 리턴
   ret, frame = cp.read()
   
   if ret:
       cv2.imshow("MyFrame", frame)
       cv2.waitKey(1)
   else:
       cp.release()

cv2.destroyAllWindows()
