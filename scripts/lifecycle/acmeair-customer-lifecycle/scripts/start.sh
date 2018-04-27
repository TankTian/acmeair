#!/bin/sh

CURDIR=$(pwd)
export JRE_HOME="$CURDIR/java/jre1.8.0_152"
export PATH="$PATH:$JRE_HOME/bin"
export CLASSPATH="./:$JRE_HOME/lib"
export JAVA=$JRE_HOME/bin/java

nohup $JAVA -Dspring.profiles.active=jpa -jar bin/acmeair-customer-service-exec.jar > $CURDIR/customer.log 2>&1 &
exit 0