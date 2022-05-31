# 🚨 번호판 신GO 🚨
- 팀명 : 시티시티 뱅뱅
- YOLOv5기반 이륜차 번호판 훼손 신고 서비스

## 프로젝트 기간
2022-05-07 ~ 2022-05-31


## 프로젝트 주제
YOLOv5기반 이륜차 번호판 훼손 신고 서비스 

## 프로젝트 상세설명
- 경찰의 단속과 cctv단속을 피하려 고의로 번호판을 훼손시키는 등 무질서한 이륜차의 운행으로 사고 횟수가 증가되어 번호판 훼손 신고가 필요하다.

## 사용 기술

- Android  
Java  
Firebase  
Node.js  
JavaScript  
Kakao 지도 API

- Web  
JSP/Servlet  
HTML/CSS/JS  
JSON  
Google Charts  
Kakao 지도 API  

- 데이터 전처리
Keras  
NumPy  
OpenCV  
Matplotlib  

- 딥러닝 
YOLOv5  
PyTorch  

- OCR 
Easy OCR  


- DB  
Oracle  
MyBatis  


## 구현 방법

- YOLOv5를 활용한 차종인식
- OpenCV를 활용한 이미지 처리
- OCR을 활용한 번호판 텍스트 추출
- 지도API를 활용한 신고 위치 시각화


## 주요기능  

### 딥러닝  

데이터 전처리  
  - 총 3개 클래스에 해당하는 사진 데이터 : 10,000장  
  - 저화질, 중복이미지, 크기가 작은 이미지 삭제  
  - 이미지 증식 및 배경 합성  
  
라벨링  
  - 번호판 이미지 crop  
  - 알고리즘을 통한 자동 라벨링  

이미지 객체 탐지  
 - 이미지 객체 탐지에 최적화된 알고리즘 사용 : YOLOv5  
  - 성능 부분에서 가장 우수한 YOLOv5x 모델 사용  

텍스트 인식  
 - Tesseract보다 정확도 면에서 향상된 EasyOCR 오픈소스 사용  
 - GPU 연산을 지원하기 때문에 성능(속도)면에서도 개선  
 - 추가학습이 용이하고 추가폰트 학습 가능  
 - 정확도 향상을 위한 번호판 내 텍스트 세분화(지역, 숫자 등)  

### 안드로이드 기반의 App 설계  
  - 안드로이드 기반의 UI/UX 설계
  - 회원가입, 로그인 페이지
  - 메인 페이지 : 촬영 바로가기, 신고 바로가기, 나의 신고 바로가기, 전체 신고 건수 확인(오늘 신고 건수, 한 달 누적 신고 건수)
  - 자동 신고 페이지
  - 수동 신고 페이지
  - 나의 신고 내역 페이지

### 번호판 신GO의 신고 내역을 관리하기 위한 WEBPAGE 설계  
  - 메인 페이지(관리자 로그인)
  - 신고 내역 지도 시각화
  - 통계
  - 신고 리스트

## 서비스 흐름도
<img src="https://user-images.githubusercontent.com/99248442/171129382-76fc9fe6-81e6-47d3-8bbe-4d8d399c8404.png" width="900">


## 팀원 단위 업무 분담
|이름|분야|분담 업무|
|:-------:|:-----------|:-----------------------------------------------|
|박예지|팀장/프론트엔드| 
프로젝트 총괄 및 일정계획  
앱 및 웹 개발(프론트/백)  
 - 앱 및 웹 UI/UX 설계  
 - 나의 신고 내역 페이지  
 - 신고 페이지 및 기능 구현  
 - 스플래시스크린 기능 구현  
 - 웹 지도 페이지 및 기능 구현  
PPT 제작  |
|김유진|백엔드|  |
|김지현|프론트엔드| |
|이상현|프론트엔드| |
|이유상|백엔드| |
|정현호|백엔드| |

## 서비스 페이지(앱)
<img src="https://user-images.githubusercontent.com/99248442/171130277-0907c1ab-258c-4211-9787-89fd82e821e2.png" width="900">


## 서비스 페이지(웹)
<img src="https://user-images.githubusercontent.com/99248442/171130688-2c66f4eb-59ce-45b7-8a76-395c63d8a4cb.png" width="900">


## 페이지 링크

