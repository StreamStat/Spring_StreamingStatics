# 1. Node.js 공식 이미지 사용 (개발 환경)
FROM node:18

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. package.json과 package-lock.json 복사 후 의존성 설치
COPY package.json package-lock.json ./
RUN npm install

# 4. 모든 소스 코드 복사
COPY . .

# 5. Vite 개발 서버 실행 (Hot-reloading 지원)
EXPOSE 3000
CMD ["npm", "run", "dev"]
