#!/bin/sh

CURDIR=$(pwd)
export JRE_HOME="$CURDIR/java/jre1.8.0_152"
export PATH="$PATH:$JRE_HOME/bin"
export CLASSPATH="./:$JRE_HOME/lib"
export JAVA=$JRE_HOME/bin/java

$JAVA -Dspring.profiles.active=sc -jar bin/acmeair-webapp-exec.jar