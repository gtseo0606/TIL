대회 link : https://dacon.io/competitions/official/235930/overview/description

데이터 link : https://www.notion.so/2c2f10cdd08e49cfbd31d1772ef577a1

코드 link : https://dacon.io/codeshare/5998

코드 Review : 딥러닝 모델인 MLP를 강화한 모델로 최종 9등/752을 수상했습니다. 단순 MLP 모델로는 한계를 느껴 MLP의 복잡성을 증가시켰습니다. 먼저 노드 수를 증가시켰다 줄이는 방식(UP/DOWN-block)을 적용했고 이후 Down-block에서 Skip-Connection과 Layer-Norm을 적용하는 방식을 이용했습니다. 위 두 방법을 결합하여 모든 거래의 데이터를 학습한 후, 사기 거래 데이터를 찾아내는 모델을 만들었습니다. 그 결과, 처음으로 제 코드를 저작권으로 등록해봤고 상금은 전액 세이브더칠드런에 기부할 수 있었습니다.


![image](https://user-images.githubusercontent.com/74644453/177106458-6ec79057-11bd-45d3-9d23-4261abaec4c3.png)
![image](https://user-images.githubusercontent.com/74644453/185560192-4c258b2a-99dd-4cfd-bc0f-1e25810177e0.png)

![image](https://user-images.githubusercontent.com/74644453/189901049-5716df77-29c2-45f6-a1b6-aaa2dc35fd77.png)

![Untitled (4)](https://user-images.githubusercontent.com/74644453/189900716-809a6dc9-50e3-4905-a1ac-36bfd57e4f69.png)

![image](https://user-images.githubusercontent.com/74644453/190955371-613008cd-5c8d-4e7e-bfe9-7a8d0ec85b77.png)
