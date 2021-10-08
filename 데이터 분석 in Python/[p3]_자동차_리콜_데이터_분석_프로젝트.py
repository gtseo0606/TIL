#!/usr/bin/env python
# coding: utf-8

# # [Project 3] 자동차 리콜 데이터 분석

# ---

# ## 프로젝트 목표
# - 한국교통안전공단 자동차 결함 리콜 데이터를 분석하여 유의미한 정보 도출
# - 탐색적 데이터 분석을 수행하기 위한 데이터 정제, 특성 엔지니어링, 시각화 방법 학습

# ---

# ## 프로젝트 목차
# 1. **데이터 읽기:** 자동차 리콜 데이터를 불러오고 Dataframe 구조를 확인<br>
#     1.1. 데이터 불러오기<br>
# <br> 
# 2. **데이터 정제:** 결측치 확인 및 기초적인 데이터 변형<br>
#     2.1. 결측치 확인<br>
#     2.2. 중복값 확인<br>
#     2.3. 기초적인 데이터 변형<br>
# <br>
# 3. **데이터 시각화:** 각 변수 별로 추가적인 정제 또는 feature engineering 과정을 거치고 시각화를 통하여 데이터의 특성 파악<br>
#     3.1. 제조사별 리콜 현황 출력<br>
#     3.2. 모델별 리콜 현황 출력<br>
#     3.3. 월별 리콜 현황 출력<br>
#     3.4. 생산연도별 리콜 현황 출력<br>
#     3.5. 4분기 제조사별 리콜 현황 출력<br>
#     3.6. 하반기 생산연도별 리콜 현황 출력<br>
#     3.7. 워드 클라우드를 이용한 리콜 사유 시각화<br>

# ---

# ## 데이터 출처
# -  https://www.data.go.kr/data/3048950/fileData.do

# ---

# ## 프로젝트 개요
# 
# 리콜(recall)은 제품의 설계, 제조 단계에서 결함이 발견되었을 시 문제 예방의 차원에서 판매자가 무상으로 수리, 점검 및 교환을 해주는 소비자 보호 제도입니다. 집집마다 개인용 자동차를 보유하게 되면서 자동차는 어느덧 우리 삶의 일상재가 되었지만, 안전성에 대해서는 산발적인 문제 제기가 계속되고 있고, 이에 따라 전격적인 자동차 리콜 사태도 종종 발생하여 화제를 모으곤 합니다.
# 
# 이번 프로젝트에서는 한국교통안전공단에서 제공한 2020년 자동차 결함 리콜 데이터를 활용하여 유의미한 패턴 및 인사이트를 발굴하고 시각화하는 실습을 진행하도록 하겠습니다.

# ---

# ## 1. 데이터 읽기

# 필요한 패키지 설치 및 `import`한 후 `pandas`를 사용하여 데이터를 읽고 어떠한 데이터가 저장되어 있는지 확인합니다.

# ### 1.1. 데이터 불러오기

# In[1]:


import numpy as np 
import pandas as pd 
import matplotlib.pyplot as plt
get_ipython().system('pip install seaborn==0.9.0')
import seaborn as sns
print(sns.__version__)
# missingno라는 라이브러리가 설치되어 있을 경우 import
try: 
    import missingno as msno
# missingno라는 라이브러리가 설치되어 있지 않을 경우 설치 후 import
except: 
    get_ipython().system('pip install missingno')
    import missingno as msno


# In[2]:


# pd.read_csv를 통하여 dataframe 형태로 읽어옵니다.
df = pd.read_csv("./data/한국교통안전공단_자동차결함 리콜현황_20201231.csv", encoding="euc-kr")


# In[3]:


# 상위 5개 데이터를 출력합니다.
df.head()


# In[4]:


# 상위 10개 데이터를 출력합니다.
df.head(10)


# In[5]:


# 하위 5개 데이터를 출력합니다.
df.tail()


# In[6]:


# dataframe 정보를 요약하여 출력합니다. 
df.info()


# ---

# ## 2. 데이터 정제

# 데이터를 읽고 확인했다면 결측값(missing data), 중복값(duplicates)을 처리하고 열 이름 변경과 같은 기초적인 데이터 변형을 진행해봅시다.

# ### 2.1. 결측치 확인

# `missingno.matrix()` 함수를 이용하여 결측치를 시각화해봅시다.

# In[7]:


import matplotlib.font_manager as fm

font_dirs = ['/usr/share/fonts/truetype/nanum', ]
font_files = fm.findSystemFonts(fontpaths=font_dirs)

for font_file in font_files:
    fm.fontManager.addfont(font_file)


# In[8]:


sns.set(font="NanumBarunGothic", 
        rc={"axes.unicode_minus":False})
msno.matrix(df)
plt.show()


# `isna()` 함수를 이용하여 결측치를 확인해봅시다.

# In[9]:


# 각 열 별로 결측치의 갯수를 반환합니다. (True:1, False:0)
df.isna().sum() 


# ### 2.2. 중복값 확인

# `duplicated()` 함수를 이용하여 중복값을 확인해봅시다.

# In[10]:


df[df.duplicated(keep=False)]


# `drop_duplicates()` 함수를 이용하여 중복값을 제거합니다.

# In[11]:


print("Before:", len(df))
df = df.drop_duplicates()
print("After:", len(df))


# ### 2.3. 기초적인 데이터 변형

# 현재 `생산기간`, `생산기간.1`, `리콜개시일` 열은 모두 `object` 타입, 즉 문자열로 인식되고 있습니다. 분석을 위해 연도, 월, 일을 각각 정수형으로 저장합니다. <br>
# 추가적으로 분석의 편리를 위해 열 이름을 영어로 바꿔줍니다.

# In[12]:


def parse_year(s):
    return int(s[:4])
def parse_month(s):
    return int(s[5:7])
def parse_day(s):
    return int(s[8:])

# Pandas DataFrame에서는 row별로 loop를 도는 것이 굉장히 느리기 때문에, apply() 함수를 이용하여 벡터 연산을 진행합니다.
df['start_year'] = df['생산기간'].apply(parse_year)
df['start_month'] = df['생산기간'].apply(parse_month)
df['start_day'] = df['생산기간'].apply(parse_day)

df['end_year'] = df['생산기간.1'].apply(parse_year)
df['end_month'] = df['생산기간.1'].apply(parse_month)
df['end_day'] = df['생산기간.1'].apply(parse_day)

df['recall_year'] = df['리콜개시일'].apply(parse_year)
df['recall_month'] = df['리콜개시일'].apply(parse_month)
df['recall_day'] = df['리콜개시일'].apply(parse_day)

df.head(3)


# In[13]:


# 불필요한 열은 버리고, 열 이름을 재정의합니다.
df = df.drop(columns=['생산기간', '생산기간.1', '리콜개시일']).rename(columns={'제작자': "manufacturer", "차명": "model", "리콜사유": "cause"})
df.head(3)


# 본 분석에서는 2020년의 데이터만을 대상으로하므로, 그 외의 데이터가 있다면 삭제해주겠습니다.

# In[14]:


# 2019년의 데이터가 함께 존재함을 알 수 있습니다.
df.recall_year.min(), df.recall_year.max()


# In[15]:


# 2020년의 데이터만을 남겨줍니다.
df = df[df['recall_year']==2020]
len(df)


# ---

# ## 3. 데이터 시각화

# 각 column의 변수별로 어떠한 데이터 분포를 하고 있는지 시각화를 통하여 알아봅시다.

# ### 3.1. 제조사별 리콜 현황 출력

# 제조사별 리콜 건수 분포를 막대 그래프로 확인해보겠습니다.

# In[16]:


df.groupby("manufacturer").count()["model"].sort_values(ascending=False)


# In[17]:


pd.DataFrame(df.groupby("manufacturer").count()["model"].sort_values(ascending=False)).rename(columns={"model": "count"})


# In[18]:


tmp = pd.DataFrame(df.groupby("manufacturer").count()["model"].sort_values(ascending=False)).rename(columns={"model": "count"})


# In[19]:


plt.figure(figsize=(20,10))
# 한글 출력을 위해서 폰트 옵션을 설정합니다.
sns.set(font="NanumBarunGothic", 
        rc={"axes.unicode_minus":False},
        style='darkgrid')
ax = sns.countplot(x="manufacturer", data=df, palette="Set2", order=tmp.index)
plt.xticks(rotation=270)
plt.show()


# In[20]:


tmp.index


# ### 3.2. 모델별 리콜 현황 출력

# 차량 모델별 리콜 건수 분포를 막대 그래프로 확인해보겠습니다.

# In[21]:


pd.DataFrame(df.groupby("model").count()["start_year"].sort_values(ascending=False)).rename(columns={"start_year": "count"}).head(10)


# 모델은 굉장히 많으므로, 상위 50개 모델만 뽑아서 시각화를 진행해보겠습니다.

# In[22]:


tmp = pd.DataFrame(df.groupby("model").count()["manufacturer"].sort_values(ascending=False))
tmp = tmp.rename(columns={"manufacturer": "count"}).iloc[:50]


# In[23]:


# 그래프의 사이즈를 조절합니다.
plt.figure(figsize=(10,5))

# seaborn의 countplot 함수를 사용하여 출력합니다.
sns.set(font="NanumBarunGothic", 
        rc={"axes.unicode_minus":False},
        style='darkgrid')
ax = sns.countplot(x="model", data=df[df.model.isin(tmp.index)], palette="Set2", order=tmp.index)
plt.xticks(rotation=270)
plt.show()


# ### 3.3. 월별 리콜 현황 출력

# 월별 리콜 건수 분포를 막대 그래프로 확인해보겠습니다.

# In[24]:


pd.DataFrame(df.groupby("recall_month").count()["start_year"].sort_values(ascending=False)).rename(columns={"start_year": "count"})


# In[25]:


# 그래프의 사이즈를 조절합니다.
plt.figure(figsize=(10,5))

# seaborn의 countplot 함수를 사용하여 출력합니다.
sns.set(style="darkgrid")
ax = sns.countplot(x="recall_month", data=df, palette="Set2")
plt.show()


# ### 3.4. 생산연도별 리콜 현황 출력

# 이번에는 생산연도별 리콜 현황을 꺾은선 그래프로 알아보겠습니다.

# In[26]:


tmp = pd.DataFrame(df.groupby("start_year").count()["model"]).rename(columns={"model": "count"}).reset_index()

# 그래프의 사이즈를 조절합니다.
plt.figure(figsize=(10,5))

# seaborn의 countplot 함수를 사용하여 출력합니다.
sns.set(style="darkgrid")
sns.lineplot(data=tmp, x="start_year", y="count")
plt.show()


# In[27]:


tmp


# ### 3.5. 4분기 제조사별 리콜 현황 출력

# 가장 최근 데이터인 2020년 4분기(10, 11, 12월) 제조사별 리콜 현황을 시각화해봅시다.

# In[28]:


# 논리연산을 이용한 조건을 다음과 같이 사용하면 해당 조건에 맞는 데이터를 출력할 수 있습니다.
df[df.recall_month.isin([10,11,12])].head()


# In[29]:


# 그래프를 출력합니다.
plt.figure(figsize=(20,10))
sns.set(font="NanumBarunGothic", 
        rc={"axes.unicode_minus":False},
        style='darkgrid')
ax = sns.countplot(x="manufacturer", data=df[df.recall_month.isin([10,11,12])], palette="Set2")
plt.xticks(rotation=270)
plt.show()


# ### 3.6. 하반기 생산연도별 리콜 현황 출력

# 이번에는 2020년 하반기(7~12월)에 개시된 리콜 건들을 생산 개시 연도를 기준으로 시각화해봅시다.

# In[30]:


# 해당 column을 지정하여 series 형태로 출력할 수 있습니다.
df[df.recall_month>=7].head()


# In[31]:


# 그래프를 출력합니다.
plt.figure(figsize=(10,5))
sns.set(style="darkgrid")
ax = sns.countplot(x="start_year", data=df[df.recall_month>=7], palette="Set2")
plt.show()


# ### 3.7. 워드 클라우드를 이용한 리콜 사유 시각화

# 워드 클라우드를 이용하여 리콜 사유를 시각화해보도록 하겠습니다.

# In[32]:


# 워드 클라우드 생성을 도와주는 패키지를 가져옵니다.
try:
    from wordcloud import WordCloud, STOPWORDS
except:
    get_ipython().system('pip install wordcloud')
    from wordcloud import WordCloud, STOPWORDS


# In[33]:


# 문법적인 성분들을 배제하기 위해 stopwords들을 따로 저장해둡니다.
set(STOPWORDS)


# 영어를 사용할 때는 상관 없지만, 우리말을 쓸 때에는 적합하지 않습니다. 여기서는 예시로 몇 개의 stopwords들을 수기로 저장해보겠습니다.

# In[34]:


# 손으로 직접 리콜 사유와 관련이 적은 문법적 어구들을 배제해보겠습니다.
spwords = set(["동안", "인하여", "있는", "경우", "있습니다", "가능성이", "않을", "차량의", "가", "에", "될", "이",
               "인해", "수", "중", "시", "또는", "있음", "의", "및", "있으며", "발생할", "이로", "오류로", "해당"])


# In[35]:


# 리콜 사유에 해당하는 열의 값들을 중복 제거한 뒤 모두 이어붙여서 text라는 문자열로 저장합니다.
text = ""

for c in df.cause.drop_duplicates():
    text += c

text[:100]


# 워드 클라우드를 생성하고 시각화해보겠습니다.

# In[36]:


# 한글을 사용하기 위해서는 폰트를 지정해주어야 합니다.
wc1 = WordCloud(max_font_size=200, stopwords=spwords, font_path='/usr/share/fonts/truetype/nanum/NanumGothic.ttf',
                background_color='white', width=800, height=800)
wc1.generate(text)

plt.figure(figsize=(10, 8))
plt.imshow(wc1)
plt.tight_layout(pad=0)
plt.axis('off')
plt.show()

