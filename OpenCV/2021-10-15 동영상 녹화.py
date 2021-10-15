opencv 동영상 녹화
cv2.VideoWriter_fourcc



# ex7. 영상 녹화를 해보자

import numpy as np
import cv2

cp = cv2.VideoCapture(0)
fps = 30 # 초당 프레임 수
size = (640,480) # 동영상 사이즈

# 동영상 설정 출력파일
fourcc = cv2.VideoWriter_fourcc(*'XVID') # = ('X', 'V', 'I', 'D')
video = cv2.VideoWriter('C:\\Users\\Playdata\\Downloads\\myImg\\output1.avi',
              fourcc, fps, size)

while(cp.isOpened()):
   #프레임으로 리턴
   ret, frame = cp.read()
   
   if ret:
       cv2.imshow("MyFrame", frame)
       video.write(frame)
       if cv2.waitKey(1) == ord('q'):
           break

cp.release()
video.release()
cv2.destroyAllWindows()
