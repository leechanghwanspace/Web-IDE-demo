#!/bin/bash

FILE_NAME=$1

# 파일 확장자에 따라 실행
case "${FILE_NAME##*.}" in
  "c")
    gcc "$FILE_NAME" -o program && ./program
    ;;
  "cpp")
    g++ "$FILE_NAME" -o program && ./program
    ;;
  "java")
    javac "$FILE_NAME" && java "${FILE_NAME%.*}"
    ;;
  "js")
    node "$FILE_NAME"
    ;;
  "py")
    python3 "$FILE_NAME"
    ;;
  *)
    echo "Unsupported file format: ${FILE_NAME##*.}"
    ;;
esac
