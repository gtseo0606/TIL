# 목적
주제단어 추출로 어떤글이 어떤 작가것인지 예측한다


예) id, 작가1, 작가2, 작가3


"id02310",0.403493538995863,0.287808366106543,0.308698094897594


## 로그손실률
예측한 값(1958,3) 실제값(1958,3)


ex) 예측한 값 0.6, 0.35, 0.25  실제값 1, 0, 0


sum(-0.1/1958*{(log*예측한값)*실제값})


## 요약 정리
0-1) 전처리->희소행렬->(전처리)->모델링->점수
tokenize() -> stopwords.words() -> stemmer() ->희소행렬 CountVectorizer()/LemmaCountVectorizer()/TfidfVectorizer() -> 모델 LatentDirichletAllocationlda.fit()/로지스틱회귀/MultinomialNB -> 로그손실률 -> GridSearchCV -> 로그손실률
                                                                                                                                                          -> 전처리 decomposition.TruncatedSVD(n_components=120).fit_transform(문장) + StandardScaler().fit_transform(문장) -> 모델 SVC/xgb.XGBClassifier/딥러닝 -> 로그손실률 -> GridSearchCV -> 로그손실률
 
0-2) 딕셔너리화->밀집행렬->전처리->모델링(LSTM/GRU)->점수
문장 밀집표현화 Glove 딕셔너리화 -> xtrain문장->전처리-> text.Tokenizer() -> token.fit_on_texts() -> token.texts_to_sequences(xtrain) (단어 -> 숫자 시퀀스) -> .pad_sequences(xtrain_seq, maxlen=70) (0패딩) -> s-esdlstm/gru-dd-dd-da -> Bidirectional(LSTM) ->



## 요약1

token(분리)화(nltk.word_tokenize()) ->불용어 제거(nltk.corpus.stopwords.words('english')) -> 형태소분석=기본단어화=stemming(nltk.stem.PorterStemmer()/lancaster/snowball.stem("running"))
-> WordNetLemmatizer().lemmatize("leaves") -> 희소행렬화(컴퓨터언어화) Bag of Words(CountVectorizer(min_df=0).fit_transform(sentence).toarray())
-> 주제단어 가중치화 LatentDirichletAllocationlda.fit(tf) + components_ # tf=희소행렬CountVectorizer + WordNetLemmatizer() + .fit_transform(text) + np.asarray(tf.sum(axis=0)).ravel() + tf.get_feature_names()
-> WordCloud

## 요약2
1. 문장 희소행렬화 TfidfVectorizer().fit_transform(문장) -> 로지스틱회귀/MultinomialNB -> 로그손실률 -> GridSearchCV -> 로그손실률

2. 문장 희소행렬화 CountVectorizer().fit_transform(문장) -> 로지스틱회귀/MultinomialNB -> 로그손실률 -> GridSearchCV -> 로그손실률

3. 문장 희소행렬화 TfidfVectorizer().fit_transform(문장) + decomposition.TruncatedSVD(n_components=120).fit_transform(문장) + StandardScaler().fit_transform(문장) -> SVC/xgb.XGBClassifier-> 로그손실률 -> GridSearchCV -> 로그손실률

4. 문장 희소행렬화 CountVectorizer().fit_transform(문장) -> xgb.XGBClassifier + .fit(xtrain_ctv.tocsc(), ytrain) -> 로그손실률 -> GridSearchCV -> 로그손실률

5. 문장 밀집표현화 Glove 딕셔너리화 -> xtrain문장 -> 소문자화(str(문장).lower())+분리(tokenize())+불용어제거() ->딕셔너리에 있는 단어만 추출 후 벡터화 -> xgb.XGBClassifier/딥러닝(s-ddb-ddb-da)

6. LSTM / GRU
text.Tokenizer() -> token.fit_on_texts() -> token.texts_to_sequences(xtrain) (단어 -> 숫자 시퀀스) -> .pad_sequences(xtrain_seq, maxlen=70) (0패딩) -> s-esdlstm/gru-dd-dd-da -> Bidirectional(LSTM)

7. 앙상블

## 요약3

