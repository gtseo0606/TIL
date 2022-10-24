대회 link : https://dacon.io/competitions/official/235942/overview/description


코드 link : https://dacon.io/codeshare/5960

리뷰 : 개인적으로는 최대 성능보단 최소 코드 길이와 시간을 가진 최적의 코드를 제작하고자 했습니다. 하지만 수행 과정에서 45개의 지점을 한꺼번에 돌리니 RAM이 터지는 하드웨어 문제가 발생했습니다. 그리고 GridSearchCV를 통한 최적화 과정에서는 시간 초과 문제가 생겼습니다.  램 문제는 Pycaret을 통한 CatBoost 모델 선정으로, 시간문제는 RandomizedSearchCV로 해결할 수 있었습니다. 더욱이, 성능 향상을 위해 변수 파트는 휴일여부 등 날짜 관련 변수를 제작했습니다. 데이터 파트는 저는 두 연도 모두 비슷하지 않은 지점은 10년과 11년의 평균값을 구해 선택폭을 넓혔습니다. 그 결과, RMSE가 38764.40에서 33675.98로 향상해 기대 이상의 결과인 최종 2위로 대회를 마감했습니다.


![image](https://user-images.githubusercontent.com/74644453/179498022-4be1e564-96b5-487f-bd03-c9873d85d588.png)
![리자몽2 쇼핑몰지점별매출액](https://user-images.githubusercontent.com/74644453/188373700-54b9628d-3fee-4c30-91b2-233e2251cbc4.png)


