### 분석



색상변환(rgb->gray)->배경제거=마스크화(0이 검정 1이 흰색)->라벨부여+컬러맵->셀크기(픽셀)10이하제거->겹쳐잇는 객체 분리(ndimage.binary_opening)
-> RLE 인코딩

# 요약

### 1. label 읽어오기 
### 2. 이미지경로검색/분리(path  -> ImageId, ImageType, TrainingSplit, Stage)
### 3. train/test 
### 4. groupby(['Stage', 'ImageId']) -> image/mask # 변수: 'Stage', 'ImageId','masks','images' 
### 5. 숫자화 + img/mask 각각합치기 #np.sum(np.stack([imread(c_img) for c_img in in_img_list], 0), 0)/255.0)
### 6. .imshow()
### 7. 데이터 차원증가 #np.expand_dims(c_row['images'],0), np.expand_dims(np.expand_dims(c_row['masks'],-1),0)
### 8. cnn(sbc) + dice_coef -> .imshow()
### 9. test의 img+모델로 mask 예측 # test_img_df['masks'] = test_img_df['images'].map(lambda x: simple_cnn.predict(np.expand_dims(x, 0))[0, :, :, 0]))
### 10. .imshow()
### 11. rle 인코딩 #mask로 rle변수를 만듦
