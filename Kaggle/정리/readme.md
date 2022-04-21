
### [Kaggle] Statoil, C-CORE Iceberg Classifier Challenge
![statoil](https://user-images.githubusercontent.com/74644453/163803127-620104d8-2c2e-4cb7-9c18-0d13796c2a46.png)

- 이미지 모델링
np.asarray(df['band_1'].reshape(75,75)) -> smooth(denoise()) -> np.asarray(df['band_1'].reshape(75,75,1)) -> np.concatenate() -> train_test_split() 
- 크기변경-> skimage 노이즈 제거 -> 차원늘리기 -> 결합 -> 분해
model, partial_model = S-CMD-BFIM-DAD-MCA
model.fit_generator(ImageDataGenerator().flow())
model.load_weights(filepath)
model.evaluate()

partial_model.fit_generator(ImageDataGenerator().flow())
combined_model
combined_model.load_weights(filepath)
combined_model.evaluate()


### [Kaggle] TensorFlow Speech Recognition Challenge
- 오디오 모델링
wavefile.read('')->signal.spectrogram()-> librosa.load('') -> librosa.feature.melspectrogram -> ipd.Audio(samples[4000:13000], rate=sample_rate) # 침묵제거
-> fft -> signal.resample() -> ipd.Audio(resampled, rate = sample_rate) # 차원축소 -> pca


