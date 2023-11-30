#!/usr/bin/env bash

JAR_NAME=$(ls $PROJECT_ROOT/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$PROJECT_ROOT/build/libs/$JAR_NAME


APP_LOG="$PROJECT_ROOT/log/application.log"
ERROR_LOG="$PROJECT_ROOT/log/error.log"
DEPLOY_LOG="$PROJECT_ROOT/log/deploy.log"

TIME_NOW=$(date +%c)

# build 파일 복사
echo "$TIME_NOW > $JAR_FILE 파일 복사" >> $DEPLOY_LOG
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

# jar 파일 실행
echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
nohup java -jar $JAR_FILE > $APP_LOG 2> $ERROR_LOG &