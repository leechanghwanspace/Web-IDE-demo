# Ubuntu 기반 이미지 사용
FROM ubuntu:20.04

# 작업 디렉토리 설정
WORKDIR /usr/src/app

# 비대화식 모드 설정
ARG DEBIAN_FRONTEND=noninteractive

# 필수 패키지 설치
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    python3 \
    python3-pip \
    gcc \
    g++ \
    curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*


# Node.js 설치 (공식 스크립트 사용)
RUN curl -fsSL https://deb.nodesource.com/setup_16.x | bash - && \
    apt-get install -y nodejs && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# 실행 스크립트 복사
COPY . .

# 실행 스크립트 설정
CMD ["sh", "run.sh"]
