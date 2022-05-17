# 목적 
주제단어 추출로 어떤글이 어떤 작가것인지 예측한다

예) id, 작가1, 작가2, 작가3


"id02310",0.403493538995863,0.287808366106543,0.308698094897594


## 요약

token(분리)화(nltk.word_tokenize()) ->불용어 제거(nltk.corpus.stopwords.words('english')) -> 형태소분석=기본단어화=stemming(nltk.stem.PorterStemmer()/lancaster/snowball.stem("running"))
-> WordNetLemmatizer().lemmatize("leaves") -> 희소행렬화(컴퓨터언어화) Bag of Words(CountVectorizer(min_df=0).fit_transform(sentence).toarray())
-> 주제단어 가중치화 LatentDirichletAllocationlda.fit(tf) + components_ # tf=희소행렬CountVectorizer + WordNetLemmatizer() + .fit_transform(text) + np.asarray(tf.sum(axis=0)).ravel() + tf.get_feature_names()
-> WordCloud

