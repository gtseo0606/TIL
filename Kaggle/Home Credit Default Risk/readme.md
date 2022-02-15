## 데이터

![화면 캡처 2022-02-13 210226](https://user-images.githubusercontent.com/74644453/153752157-37c450e5-29ec-4810-b203-d1ba889b1e56.png)


## 데이터 분석
1. EDA 
1-1) 타겟변수 : value_counts(), astype(int).plot.hist(), 
1-2) 결측치 : 
1-3) 컬럼 타입 : .dtypes.value_counts()
1-4) 범주형 변수 : get_dummies(), LabelEncoder()
1-5) 이상치 : SimpleImputer()
1-6) 상관관계 : .corr()['target'].sort_values()
plt.hist(series, edgecolor='k', bins=25)
sns.kdeplot(series, bw_method=0.5, label='target=1')
plt.bar(series/str, series/str)
sns.barplot(ax, array, list)
sns.heatmap(series.corr(), cmap=diverging_palette(), annot=True, center=0, square=True)
sns.pairplot(dataframe, hue='y', palette = 'Set1', diag_kind='kde')
1-7) pairplot
