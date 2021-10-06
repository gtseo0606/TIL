opencv 팽창 -> 수축

# cv2.morphologyEx(src, op, kenel[,dst[, anchor[, iterations[, borderType[, borderValue]]]]]) -> dst
# op = option
#=> cv2.MORPH_CLOSE : 팽창 -> 수축
#=> cv2.MORPH_OPEN : 수축 -> 팽창
#=> cv2.MORPH_BLACKHAT : 입력한 이미지와 처리된 이미지의 차이를 표시
#=> cv2.MORPH_GRADIENT : 외곽선
#=> cv2.MORPH_TOPHAT : 9x9 의 커널 크기

# 팽창 -> 작업 -> 수축(노이즈도 제거됨), 수축 후 팽창
import cv2
import numpy as np
from matplotlib import pyplot as plt

ret, img_th = cv2.threshold(img, 110, 255, cv2.THRESH_BINARY)
kernel = np.ones((3,3), dtype= np.uint8)

img_d = cv2.dilate(img_th, kernel)
img_e = cv2.erode(img_th, kernel)
img_res = cv2.morphologyEx(img_th, cv2.MORPH_OPEN, kernel)

plt.subplot(121),plt.imshow(img),plt.title('Original')
plt.subplot(122),plt.imshow(img_res),plt.title('img_res')
