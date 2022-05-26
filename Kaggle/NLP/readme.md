# CountVectorizer vs DTM(Document-Term Matrix)
DTM : 단어가 각 문서별로 몇번나왔는지 행렬로 구성
CountVector : DTM+여러 매개변수(조건) # stop_word, max/min_df, ngram_range, analyzer
stop_word
max/min_df : 특정횟수이상/이하 무시
ngram_range : (3, 3)로 설정하면 3개의 단어를 한 묶음 ex. ['algorithm analyzes training' 'algorithm correctly determine']
analyzer = 'char'

CountVectorizer().get_feature_names_out() # ['algorithm analyzes training' 'algorithm correctly determine']
문서와 문서로부터 추출한 키워드들을 SBERT를 통해서 수치화
문서와 가장 유사한 키워드들은 문서를 대표하기 위한 좋은 키워드문서와 가장 유사한 키워드들은 문서를 대표하기 위한 좋은 키워드



2. 텍스트 전처리
2-3. 어간 추출(Stemming)/표제어(Lemma)
1) 어간(stem) : 단어의 의미를 담고 있는 단어의 핵심 부분.

2-5. 정규 표현식 텍스트 전처리
text = """100 John    PROF 101 James   STUD 102 Mac   STUD"""

ex1.
re.split('\s+', text)  # s는 공백을 의미하기 때문에 최소 1개 이상의 공백인 패턴을 찾아냅니다.
['100', 'John', 'PROF', '101', 'James', 'STUD', '102', 'Mac', 'STUD']
ex2.
re.findall('\d+',text)  #  해당 입력으로부터 숫자만을 뽑아온다
['100', '101', '102]


4. 카운트 기반 단어표현
4-2. BoW
4-3. DTM
4-4. TF-IDF
TF-IDF는 모든 문서에서 자주 등장하는 단어는 중요도가 낮다고 판단하며, 특정 문서에서만 자주 등장하는 단어는 중요도가 높다고 판단합니다.
TF-IDF 값이 낮으면 중요도가 낮은 것이며, TF-IDF 값이 크면 중요도가 큰 것입니다.

d=문서(문장)의 중 1개, t=단어의 수, n=총 문서(문장)의 수
(1) tf(d,t) : 특정 문서 d에서의 특정 단어 t의 등장 횟수. ex) 첫번째 문장에서 "바나나"가 2번 등장
(2) df(t) : 특정 단어 t가 등장한 문서의 수. ex) 문서 5개 중 "바나나"가  문서2, 3에 등장=2 // 문서 100만개 중 "바나나"가 문서 10만개에 등장=100,000
(3) idf(d, t) : df(t)에 반비례하는 수. ex) log(n/(df(t)+1)) # 문서 100만개 중 "바나나"가 문서 10만개에 등장=log(1,000,000/100,000)=log10=1

5.  벡터의 유사도
5-1. 코사인 유사도
TfidfVectorizer -> cosine_similarity(tfidf_matrix, tfidf_matrix) 
5-2. 유클리디안, 자카드 유사도


19. 토픽모델링(LSA/LDA)
19-1. Latent Semantic Analysis(LSA)(잠재 의미 분석) 
입력 : DTM, tfidf
과정 : TruncatedSVD=U*S*Vt로 벡터의 정보량을 줄인다. = DTM(문서수*단어수)->Vt(토픽수*단어수) = [0,1,0,1]->[0,0.2,0,0.2]
출력 : 동일한 행렬크기(4*9->4*9) but 행렬값(벡터의 정보량)은 줄어듦 // 각 토픽에서 값이 큰 5개의 단어 추출 // 토픽기반 단어들의 잠재적인 의미를 끌어낸다
특징 : 
LSA를 사용해서 문서의 수를 원하는 토픽의 수로 압축
BoW에 기반한 DTM이나 TF-IDF는 기본적으로 단어의 빈도 수를 이용한 수치화 방법이기 때문에 단어 간 의미(유사도)를 고려하지 못한다는 단점

예시 : 
DTM(문서 수*단어 수) -> FullSVD = U(문서 수*문서 수)* S(문서수*단어수) * Vt(단어 수*단어 수) ex) 4*9 = 4*4 * 4*9 * 9*9
                             -> TruncatedSVD = 직교행렬U(문서 수*토픽 수)* 대각행렬S=토픽(카테고리)!!!(토픽수*토픽수) * 전치행렬Vt(토픽수*단어 수) ex) 4*9 = 4*2 * 2*2 * 2*9
tfidf (11,314 × 1,000)  -> U(11314*20)* S(20,20)*Vt(20, 1000)
                              
축소된 VT는 2 × 9의 크기를 가지는데, 이는 잘 생각해보면 토픽의 수 t × 단어의 개수의 크기입니다. VT의 각 열은 잠재 의미를 표현하기 위해 수치화된 각각의 단어 벡터





9. 워드임베딩
9-1. 희소표현/밀집표현(워드임베딩)
희소표현
Ex) 강아지 = [ 0 0 0 0 1 0 0 0 0 0 0 0 ... 중략 ... 0] # 이때 1 뒤의 0의 수는 9995개. 차원은 10,000
밀집표현
Ex) 강아지 = [0.2 1.8 1.1 -2.1 1.1 2.8 ... 중략 ...] # 이 벡터의 차원은 128

워드 임베딩 방법론으로는 LSA, Word2Vec, FastText, Glove 등이 있습니다. 

9-2. 분산 표현(Distributed Representation)=Word2Vec=CBOW(input(주변단어)/ouput(중심단어)를 제공받아 가중치W를 학습)+ Skip-Gram
요약하면 희소 표현이 고차원에 각 차원이 분리된 표현 방법이었다면, 분산 표현은 저차원에 단어의 의미를 여러 차원에다가 분산 하여 표현합니다.
이런 표현 방법을 사용하면 단어 벡터 간 유의미한 유사도를 계산할 수 있습니다. 
이를 위한 대표적인 학습 방법이 Word2Vec입니다.
분산 표현
Ex) 강아지 = [0.2 0.3 0.5 0.7 0.2 ... 중략 ... 0.2]


9-3. Word2Vec 원핫인코딩베이스
1개문장-> n개의 단어->원핫인코딩->input/output에 넣기->projection-layer=룩업테이블=M=v=(input*가중치Wvm)/평균->softmax(M*W'mv)->cross-entropy->실제값과 예측값 비교

예시.
Word2Vec(sentences=result, size=100, window=5, min_count=5, workers=4, sg=0)
199992개의 문장 * 16,477개의 단어 -> 100개의 벡터로 압축 -> 윈도우개수*2개의 예측값(예측한 단어)
size = 워드 벡터의 특징 값. 즉, 임베딩 된 벡터의 차원//CBOW에서 투사층의 크기=M은 (워드)임베딩하고 난 벡터의 차원
window = 컨텍스트 윈도우 크기(주변단어개수)
min_count = 단어 최소 빈도 수 제한 (빈도가 적은 단어들은 학습하지 않는다.)
workers = 학습을 위한 프로세스 수
sg = 0은 CBOW, 1은 Skip-gram.

특징.
- Word2Vec의 학습 방식= CBOW(Continuous Bag of Words)+ Skip-Gram  
- CBOW는 주변단어를 통해 중간에 있는 단어(하나의 단어)를 예측
- Skip-Gram은 중간에 있는 단어(하나의 단어)를 통해 주변 단어들을 예측
- Word2Vec는 입력한 단어에 대해서 가장 유사한 단어들을 출력하는 model.wv.most_similar을 지원

9-5. Glove(Global Vectors for Word Representation) = LSA(토픽기반 단어통계정보) + Word2Vec(단어간 유사도) =동시등장확률!!
Glove 단어수*단어수(내용은 윈도우주변단어의 개수)=단어의 동시 등장 행렬
임베딩 된 중심 단어와 주변 단어 벡터의 내적이 전체 코퍼스에서의 동시 등장 확률이 되도록 만드는 것

glove.most_similar()는 입력 단어의 가장 유사한 단어들의 리스트를 리턴

9-6. FastText
Word2Vec는 단어를 쪼개질 수 없는 단위로 생각한다면, FastText는 하나의 단어 안에도 여러 단어들이 존재하는 것으로 간주합니다. 내부 단어. 즉, 서브워드(subword)를 고려
apple = <ap + app + ppl + ppl + le> + <app + appl + pple + ple> + <appl + pple> + , ..., +<apple>
