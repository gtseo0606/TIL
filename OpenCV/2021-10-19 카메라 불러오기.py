opencv 동영상 카메라 불러오기

# 4. 카메라 불러오기
import numpy as np
import cv2

cp = cv2.VideoCapture(0)

while(cp.isOpened()):
   #프레임으로 리턴
   ret, frame = cp.read()
   
   if ret:
       cv2.imshow("MyFrame", frame)
       cv2.waitKey(1)
   else:
       cp.release()
cv2.destroyAllWindows()
