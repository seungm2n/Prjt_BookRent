#!/bin/bash

ROOT_PATH="/home/ubuntu/app"
JAR="$ROOT_PATH/application.jar"

STOP_LOG="$ROOT_PATH/log/stop.log"

SERVICE_PID=$(pgrep -f $JAR)

NOW=$(date +%c)

if [ -z "$SERVICE_PID" ]; then
  echo "[$NOW] 서비스 NouFound" >> $STOP_LOG
else
  echo "[$NOW] 서비스 종료 " >> $STOP_LOG
  kill "$SERVICE_PID"
fi