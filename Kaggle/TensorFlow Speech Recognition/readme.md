## 데이터

### 데이터의 특징

총 65,000개의 1초 내외의 음성파일

- 31개 폴더 : "Yes", "No", "Up", "Down", "Left", "Right", "On", "Off", "Stop", "Go", "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Bed", "Bird", "Cat", "Dog", "Happy", "House", "Marvin", "Sheila", "Tree", "Wow", "_background_noise_"존재 </br>



- 20개 core words : "Yes", "No", "Up", "Down", "Left", "Right", "On", "Off", "Stop", "Go", "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", and "Nine"</br>


- 나머지 10개가 auxiliary words : "_background_noise_"는 제외  : 보조단어</br>


- core words 중 "Yes", "No", "Up", "Down", "Left", "Right", "On", "Off", "Stop", "Go"가 Test에서 예측을 위해 쓰임


## Output

![image](https://user-images.githubusercontent.com/74644453/159149340-7ecf5c62-9088-4e75-bf8c-80f0ed7c9d98.png)
<br/>
![image](https://user-images.githubusercontent.com/74644453/159149330-d8b2403a-656d-4307-b1f2-852575e0f449.png)
<br/>

#### 오디오 모델링
<br/>

wavefile.read('')->signal.spectrogram()-> librosa.load('') -> librosa.feature.melspectrogram -> ipd.Audio(samples[4000:13000], rate=sample_rate) # 침묵제거
-> fft -> signal.resample() -> ipd.Audio(resampled, rate = sample_rate) # 차원축소 -> pca -> np.pad+.resample+.log_spectrogram -> model.predict(앞에것)
<br/>

![오디오 모델링](https://user-images.githubusercontent.com/74644453/164616685-e070d4ae-e219-436e-b5eb-1e27ca3bf851.png)
<br/>


