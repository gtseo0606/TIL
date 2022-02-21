## 데이터

![화면 캡처 2022-02-13 210226](https://user-images.githubusercontent.com/74644453/153752157-37c450e5-29ec-4810-b203-d1ba889b1e56.png)

## 데이터 전처리

![Home Credit Default Risk](https://user-images.githubusercontent.com/74644453/154936287-0b667684-b8a2-42ed-a640-6edd6c3d8691.png)

- application_train -> 8 1+2+7 group=SK_ID_CURR 9 missing_value 삭제
- bureau -> 1cat, 2num group=SK_ID_BUREAU, 
- bureau_balance ->3cat, 4num group=SK_ID_BUREAU, 5cat+num outer,  7group = SK_ID_CURR
- bureau + bureau_balance -> 6bureau[['SK_ID_BUREAU', 'SK_ID_CURR']] + bureau_by_loan

## 데이터 분석

### 1. EDA
 
##### 1-1) 타겟변수 : value_counts(), astype(int).plot.hist()
##### 1-2) 결측치 : 
##### 1-3) 컬럼 타입 : .dtypes.value_counts()
##### 1-4) 범주형 변수 : get_dummies(), LabelEncoder()
##### 1-5) 이상치 : SimpleImputer()
##### 1-6) 상관관계 : .corr()['target'].sort_values()
##### 1-7) pairplot
- plt.hist(series, edgecolor='k', bins=25)
- sns.kdeplot(series, bw_method=0.5, label='target=1')
- plt.bar(series/str, series/str)
- sns.barplot(ax, array, list)
- sns.heatmap(series.corr(), cmap=diverging_palette(), annot=True, center=0, square=True)
- sns.pairplot(dataframe, hue='y', palette = 'Set1', diag_kind='kde')

### 2. Feature Engineering = feature 구성(feature추가) + feature선택(중요feature선택/차원감소)

##### 2-1) Polynomial Features (feature추가) : PolynomialFeatures(degree=3), .corr()
##### 2-2) Domain Knowledge Features (feature추가) : feature1/feature2
##### 2-3) A 데이터프레임 & B 데이터프레임 병합 : A.merge(B, on = 'x1', how ={'left', 'outer', 'right'}, left_on='x2', )
how ='left'는 왼쪽df 전부 + 왼쪽df인덱스와 동일한 인덱스의 오른쪽df만
##### 2-4) 숫자형 변수 : for col in df, df.select_dtypes('number'), 
groupby('x2').agg(['count', 'mean', 'max', 'min', 'sum']).reset_index(), .level[0], train['target'].corr(train[col])
##### 2-5) 범주형 변수 : df.select_dtypes('object'), pd.get_dummies(), groupby().agg(['sum', 'mean']), 
##### 2-6) (feature선택) :  (결측치가 많다) , (서로 상관관계가 높다)


