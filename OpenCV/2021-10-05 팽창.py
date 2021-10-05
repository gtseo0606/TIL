opencv 팽창

# 팽창(밝은 색이 팽창)
import cv2
import numpy as np
from matplotlib import pyplot as plt

img = cv2.imread('C:\\Users\\Playdata\\Downloads\\myImg\\eye.jpg')
#5*5
kernel = np.ones((5,5), np.uint8)
dilate = cv2.dilate(img, kernel, iterations = 2) #반복횟수

plt.subplot(121),plt.imshow(img),plt.title('Original')
plt.subplot(122),plt.imshow(dilate),plt.title('Dilate')
