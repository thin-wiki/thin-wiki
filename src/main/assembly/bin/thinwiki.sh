#!/bin/bash
# shellcheck disable=SC2046
# shellcheck disable=SC2164
APP_NAME=thin-wiki
SHELL_PATH=$(
  cd $(dirname "$0")
  pwd
)
WORKPLACE=$(
  cd $(dirname "${SHELL_PATH:-$0}")
  pwd
)

cd ${WORKPLACE}
#echo $(pwd)

PID_FILE=./app.pid
APP_FILE=./lib/thin-wiki.jar
JAVA_OPS="-Dthin.root=./lib"

function checkStatus() {
  if [ ! -f "$PID_FILE" ]; then
    return 1
  fi

  APP_PID=$(cat ${PID_FILE})
  #  echo "check pid : ${APP_PID}"
  EXIST_APP_PID=$(ps -ax | awk '{ print $1 }' | grep -e "^${APP_PID}$")

  if [ "${EXIST_APP_PID}" = "" ]; then
    return 1
  fi
  return 0
}

function status() {
  checkStatus
  if [ $? == 0 ]; then
    echo "${APP_NAME} is running..."
  else
    echo "${APP_NAME} is stopped"
  fi
}

function start() {
  checkStatus
  if [ $? == 0 ]; then
    echo "${APP_NAME} is running..."
    return
  fi
  echo "start ${APP_NAME} success..."
  nohup java -jar ${JAVA_OPS} ${APP_FILE} >/dev/null 2>&1 &
  #    java -jar ${JAVA_OPS} ${APP_FILE}
}

function restart() {
  checkStatus
  if [ $? != 0 ]; then
    start
    return
  fi
  APP_PID=$(cat ${PID_FILE})
  while true; do
    checkStatus
    if [ $? != 0 ]; then
      start
      break
    fi
    kill ${APP_PID}
    sleep 1
  done
}

function stop() {
  checkStatus
  if [ $? != 0 ]; then
    echo "${APP_NAME} is stopped"
    return
  fi

  APP_PID=$(cat ${PID_FILE})
  echo "stop ${APP_NAME}, pid: ${APP_PID}"
  kill ${APP_PID}
}

case $1 in
start)
  start
  ;;
stop)
  stop
  ;;
restart)
  restart
  ;;
status)
  status
  ;;
*)

  echo -e "\033[0;31m Usage: \033[0m  \033[0;34m sh  $0  {start|stop|restart|status}"
  ;;
esac
