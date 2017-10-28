#!/bin/sh

CURDIR=$(pwd)
export JRE_HOME="$CURDIR/java/jre1.8.0_152"
export PATH="$PATH:$JRE_HOME/bin"
export CLASSPATH="./:$JRE_HOME/lib"
export JAVA=$JRE_HOME/bin/java

nohup $JAVA -jar $JAVA_OPTS bin/acmeair-webapp-exec.jar > $CURDIR/huaweiair.log 2>&1 &
exit 0
