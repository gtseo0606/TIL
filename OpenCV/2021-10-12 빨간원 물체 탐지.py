opencv 동영상 빨간원으로 움직이는 물체 탐지
cv2.cvtColor(img, cv2.COLOR_BGR2HSV), label = ​connectedComponentsWithStats(), cv2.circle(frame, (int(x), int(y)), 20, (0,0,255), 10)​ 

# 4. 결과보기

import numpy as np
import cv2
import time

# ex12) HSV 형식의 컬러를 이용해서물체 감지를 해보


# 1. 사용자 함수를 생성한 다음 넘겨받은 이미지를 hsv컬러공간을 만들자 (빨강)
def myColor(img):
   # 1-1.img의 색공간을 hsv로 변경
   hsv_img = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
   
   # 1-2. HSV 영역에서 빨간색 공간 치역1
   hsv_min = np.array([0,127,0])
   # [-10, 100, 100] R [50, 100, 100] G [110, 100, 100] B
   hsv_max = np.array([30,255,255])
   # [10, 100, 100] R [70, 255, 255] G [130, 255, 255] B
   mask1 = cv2.inRange(hsv_img, hsv_min, hsv_max) #HSV이미지에서 빨간색만 추출하기 위한 임계값
   
   # 1-3. HSV 영역에서 빨간색 공간 치역2
   hsv_min = np.array([150,127,0])
   hsv_max = np.array([179,255,255])
   mask2 = cv2.inRange(hsv_img, hsv_min, hsv_max)
   #return (mask1+ mask2)
   
   # 1-4. 지정된 마스크구현 cv2.dilate(), cv2.erode()
   
   mask = mask1 + mask2
   kernel = np.ones((6,6), np.uint8)
   mask= cv2.dilate(mask, kernel) #팽창하고(밝게 하고)
   mask= cv2.erode(mask, kernel) #수축시킨다 (어둡게 한다)
   return mask
   

# 추가 함수 connectedComponentsWithStats()
def my_calc(mask):
   if np.count_nonzero(mask) <= 0:
       return (-20, 20)
   
   #https://eehoeskrap.tistory.com/279
   #https://bkshin.tistory.com/entry/OpenCV-24-%EC%97%B0%EC%86%8D-%EC%98%81%EC%97%AD-%EB%B6%84%ED%95%A0-%EA%B1%B0%EB%A6%AC-%EB%B3%80%ED%99%98-%EB%A0%88%EC%9D%B4%EB%B8%94%EB%A7%81-%EC%83%89-%EC%B1%84%EC%9A%B0%EA%B8%B0-%EC%9B%8C%ED%84%B0%EC%85%B0%EB%93%9C-%EA%B7%B8%EB%9E%A9%EC%BB%B7-%ED%8F%89%EA%B7%A0-%EC%9D%B4%EB%8F%99-%ED%95%84%ED%84%B0
   lable = cv2.connectedComponentsWithStats(mask)
   # retval, labels, stats, centroids 를 반환합니다.
   # retval : 객체 수 + 1 (배경 포함)
             #label 0(배경)(픽셀수: 305810), label 1(객체)(픽셀수: 1039), label 2(객체)(픽셀수: 351)
   # 참고 retval: 객체 개수. N을 반환하면 [0, N-1]의 레이블이 존재 하며, 0은 배경을 의미. (실제 흰색 객체 개수는 N-1개)
   # labels : 객체에 번호가 지정된 레이블 맵
   # stats : x, y, width, height, area
            #x,y는 좌표, width, height는 동영상 크기, area는 면적, 픽셀의 수
   # centroids : 무게 중심 좌표
   
   n= lable[0]-1 # 객체(라벨) 수
   
   data = np.delete(lable[2], 0, 0) # stats, index = 0, axis=0 를 제거 : 제거한것은 배경(라벨0)임 => '객체(라벨)1,2의 통계데이터'
   center = np.delete(lable[3], 0, 0) # centroids, index = 0, axis=0 를 제거 : 제거한것은 배경(라벨0)임 => '객체(라벨)1,2의 중심좌표'
   max_index= np.argmax(data[: ,4]) # 모든행의 4번째 열(픽셀수) + 가장 큰 값의 인덱스 번호 => '가장 큰 픽셀수의 인덱스'
   center[max_index] # '가장 큰 픽셀수의 중심좌표'를 인식


# 2. 카메라 캡쳐    
filepath = "C:\\Users\\Playdata\\Downloads\\myImg\\01_People.avi"

# 3. 동영상 로드
cp = cv2.VideoCapture(0)
data = []
start = time.time()

while(cp.isOpened()):
   #프레임으로 리턴
   ret, frame = cp.read()
   #print(type(ret),type(frame))
   
   #3. 데이터 읽어서 1번의 함수를통해 값을 변환 [빨강영역 추출]
   mask_res= myColor(frame)
   
   x,y = my_calc(mask_res)
   data.append([time.time()-start, x, y])
   
   # 3-1. 동그라미
   cv2.circle(frame, (int(x), int(y)), 20, (0,0,255), 10)
                                     #원 크기 = 20,   원두께 10
   
   #4. 결과보기
   cv2.imshow("MyFrame", frame)
   cv2.imshow("Mask", mask_res)
   
   if cv2.waitKey(1) & 0xFF ==  ord('q'): # q를 누르면 종료
       break

# 5. 파일저장
np.savetxt("C:\\myclip\\data.csv", np.array(data), delimiter=',')

cp.release()
cv2.destroyAllWindows()
   
