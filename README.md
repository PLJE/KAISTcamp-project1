# KAISTcamp-project1   
## Team : 이주은, 양현호   
## Project1 - 탭 구조를 이용한 안드로이드 앱     
- TabLayout 과 ViewPager2를 이용하여 탭 구조 구현   
   
   
   
## [탭1] 휴대폰 주소록 불러오기   
### 화면   
- 휴대폰 기본 주소록앱에 저장된 번호, 사진, 이름 정보를 불러와 RecyclerView 형식으로 띄움
<img src = "https://user-images.githubusercontent.com/77712822/124542052-a070bd80-de5d-11eb-961e-ac890ab981e8.jpg" width="30%" height = "30%">    

### 기능       
- 가로로 스와이프하면 번호가 화면에서 삭제되고, 휴대폰 주소록 앱에서도 반영되어 번호가 삭제      
- Long click & drag 로 아이템의 위치가 변경   
- 전화 버튼을 클릭하면 휴대폰 다이얼 화면으로 넘어가 해당 번호를 띄워 전화 걸기 가능   
<img src = "https://user-images.githubusercontent.com/77712822/124542556-8edbe580-de5e-11eb-9f1c-28c821285e88.jpg" width="30%" height = "30%">  
    
    
## [탭2] 갤러리   
### 화면   
- 어플에 저장한 이미지를 두개의 CardView로 나누어서 보여주고, 하나의 카드뷰는 여러 이미지를 RecyclerView로 띄움   
<img src = "https://user-images.githubusercontent.com/77712822/124542132-c39b6d00-de5d-11eb-85d1-f36bdc0d5ecf.jpg" width="30%" height = "30%">
<img src = "https://user-images.githubusercontent.com/77712822/124542153-ce560200-de5d-11eb-8d13-2546e44d6214.jpg" width="30%" height = "30%">

### 기능 
- 이미지를 클릭하면 확대해서 보여줌  
- 스와이프하면 이미지를 넘길 수 있음   
      
         
## [탭3] 모스 부호 변환 및 인식기   
### 기능   
- 출력 : 영어로 메시지를 입력하면 모스부호를 카메라 플래시로 출력      
- 디코드 : OpenCV를 이용해 카메라로 빛을 인식하여 모스 부호를 해독하여 메시지    
<img src = "https://user-images.githubusercontent.com/77712822/124542175-da41c400-de5d-11eb-8d02-9aa23c29d6bf.jpg" width="30%" height = "30%">



