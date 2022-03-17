## 데이터

![image](https://user-images.githubusercontent.com/74644453/158055602-a2760ae7-a956-4989-9db6-83b81770e2f0.png)

## 데이터 분석
![statoil](https://user-images.githubusercontent.com/74644453/158808288-1900a7bb-6d90-4ee7-819d-83043c08f82e.png)



## Study

### 3/14

- import plotly.offline as py
- import plotly.graph_objs as go

![image](https://user-images.githubusercontent.com/74644453/158306783-fb2fa692-dd2f-4245-ab44-2d6714c2f6f0.png)


 - iplot </br>
외부 서버에 연결하지 않고 주피터 노트북 내부에 plotly 그래프를 그려줍니다. 노트북 내부에 그래프를 그리면 데이터 분석 과정을 한 곳에 유지할 수 있다는 장점이 있습니다. 표시된 그래프 위에 마우스를 놓으면 각각의 값을 확인하고 그래프를 확대하는 등의 인터렉티브 동작을 할 수 있습니다.

- Sequance().predict(X_test) 가능</br>
Sequance().predict_proba(X_test) 불가능 이유

- np.dstack() </br>

![image](https://user-images.githubusercontent.com/74644453/158306453-bc1cdff2-1b65-49e3-8bfb-b0945ed6f4bf.png)


### 3/17

ImageDataGenerator() # 이미지 증강
ImageDataGenerator.flow(train, y_train, batch_size=32) # 배치사이즈 만큼 데이터증강


1. get_model_notebook
2. combined_model
3. gen_flow_multi_inputs -> ImageDataGenerator.flow(batch_size)
4. train_model -> ImageDataGenerator.fit_generator()
5. gen_model_weights -> get_model_notebook + train_model

6. rain_models -> train_test_split + gen_model_weights 
                      + combined_model + fit_generator(gen_flow_multi_inputs) 
