opencv 동영상 속성 확인하기

# 4. 카메라 불러오기
import numpy as np
import cv2

filepath = 'C:\\Users\\Playdata\\Downloads\\myImg\\0102.mp4'
cp = cv2.VideoCapture(filepath) # 1. 주소를 읽어옴

# 속성값을 확인
width = cp.get(cv2.CAP_PROP_FRAME_WIDTH)
height = cp.get(cv2.CAP_PROP_FRAME_HEIGHT)
fps = cp.get(cv2.CAP_PROP_FPS)
frame_num = cp.get(cv2.CAP_PROP_FRAME_COUNT)
   
print(width, height, fps, frame_num)    
   
play_time = frame_num / fps
print("play_time [sec]", play_time)
 
