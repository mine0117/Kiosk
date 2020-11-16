## [ 키완얼로 바로가기](https://k3b107.p.ssafy.io) :computer:


## 목차 :scroll:

- [개요](#개요)
- [기능](#기능)
- [구조도](#구조도)
  - [ERD](#erd)
- [기술 스택](#기술-스택)
- [팀소개](#팀소개)


<br>

## 개요 :black_nib:

<br>

#### :one: 매장 방문시 매일 귀찮게 QR 코드를 찍지 마세요 !

이젠 회원가입 한번이면 얼굴 인식을 통해 QR 코드, 수기 명부 체크 없이 방문 기록을 남길 수 있습니다.

<br>

#### :two: 키오스크 사용시 메뉴 선택이 힘드셨죠 ?

최근 주문 메뉴, 현재 시간대 HOT 음료, 현재 시간대 HOT 푸드 를 추천 해줍니다.


<br>



### __:heart: 이제 키완얼을 사용하여 편하게 매장을 이용해 보세요 :heart:__

<br>
<br>
<br>

## 기능

<br>

### 얼굴인식 

:heart_eyes: 실시간 영상 처리가 가능한 OpenCV

:smile: 얼굴에 68개의 특징점을 이용하여 얼굴을 검출하는 Dlib

:kissing: KNN Classifier를 이용한 얼굴 인식

<br>


#### 이를 통해 키완얼 고객의 얼굴을 학습하여 최고의 맞춤 컨텐츠를 제공합니다!


<br>

<hr>


### 객체탐지 :eyes:

![객체탐지](README.img/움직임.gif)



<img src = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQc27qUvAtzOlHVcvO_s8vMCDVK0MYFFQqRUw&usqp=CAU"> 

<br>

:black_heart: 키완얼은 YOLO ver2를 사용하여 실시간 객체 탐지를 처리하였습니다 

<br>

### 추천서비스 :poultry_leg: :pizza:

:one: 고객이 이전에 먹었던 메뉴 

:two: 고객이 방문한 시간대의 인기 푸드

:three: 고객이 방문한 시간대의 인기 음료

:heavy_plus_sign: 더 많은 추천 서비스를 기획중입니다 :)

<br>



![흐름도](README.img/흐름도.png)

## 구조도 

### [ERD Diagram](https://www.erdcloud.com/d/gaP6MY5NPWYAGpX7J)

![ERD](README.img/ERD.JPG)


## 기술 스택

<br>

![기술스택](README.img/기술 스택.JPG)


<br>

## Web Architecture

![기술스택](README.img/Web Architecture.JPG)

## Embedded System

![기술스택](README.img/Embedded System.JPG)



<br>

## 팀소개 :runner: :walking: 

|  Name  |             Role             |
| :----: | :--------------------------: |
| 박재림 |   팀장 / 백엔드 / 얼굴인식   |
| 김성웅 |      백엔드 / 객체 탐지      |
| 박수철 |    백엔드 / 서버 / 디버깅    |
| 이동현 | 프론트엔드 / 서버 / 키오스크 |
| 이진용 | 프론트엔드 / API / 회원관리  |

<br>