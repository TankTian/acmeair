#!/bin/sh
PNAME="acmeair-webapp-exec.jar"
PROCESS=`ps -ef|grep ${PNAME}|grep -v grep|grep -v PPID|awk '{ print $2}'`
for i in $PROCESS
do
  echo "Kill the ${PNAME} process [ $i ]"
  kill -9 $i
done

