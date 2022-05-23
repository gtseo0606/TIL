
![nlp](https://user-images.githubusercontent.com/74644453/169780456-380cc6c7-9102-4032-96d2-8c9e7df98cc0.png)

## 요약 1


1)
1. 카테고리 분리(Men/Tops/T-shirts) -> Men, Tops, T-shirts 
2. wordCount = 구두점제거 + 불용어 제거 + 3자 미만 제거
3.  _stop_words.ENGLISH_STOP_WORDS vs set(stopwords.words('english'))

4. TfidfVectorizer(min_df=10, max_features=180000, tokenizer=tokenize, ngram_range=(1, 2))
-> TfidfVectorizer().get_feature_names(), TfidfVectorizer().idf_

5. TfidfVectorizer -> TruncatedSVD -> TSNE -> scatter그래프
ex) vz_sample = TfidfVectorizer().fit_transform(list(combined_sample['item_description'])) 
-> TruncatedSVD(n_components=30, random_state=42).fit_transform(vz_sample) 
-> TSNE(n_components=2, verbose=1, random_state=42, n_iter=500).fit_transform(svd_tfidf)

6. TfidfVectorizer() -> MiniBatchKMeans -> TSNE -> scatter 그래프
7. CountVectorizer() ->LatentDirichletAllocation() -> TSNE -> scatter 그래프


## 요약 2



