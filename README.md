1. **프로젝트 개요**  
   - 프로젝트 목적 : 유튜버, 치지직 스트리머의 마케팅 가치를 측정하기 위한 통계 사이트 
   - 주요 기능 : 유투버, 치지직 스트리머의 man-Value 측정
   - 대상 사용자 : 마케팅 종사자, 일반인

2. **기능 요구사항**  
   - 유튜브 및 치지직 BJ 통계 수집 방법 : Api와 크롤링을 이용하여 수집

3. **비기능 요구사항**  
   - 성능 및 확장성 
   - 보안 요구사항  
   - API 연동 및 데이터 처리 속도  

4. **기술 스택**  
   - 사용 언어 및 프레임워크
      - Back-End-Server : Docker, Java(17), Spring boot(3.4.2)[gradle,mybatis]
      - Front-end-server : React
   - 데이터베이스 
      - BackEnd :postgresql
   - 배포 방식  
      - Api
