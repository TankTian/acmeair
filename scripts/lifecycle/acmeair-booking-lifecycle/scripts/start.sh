#!/bin/sh

CURDIR=$(pwd)
export JRE_HOME="$CURDIR/java/jre1.8.0_152"
export PATH="$PATH:$JRE_HOME/bin"
export CLASSPATH="./:$JRE_HOME/lib"
export JAVA=$JRE_HOME/bin/java

nohup $JAVA -Dspring.profiles.active=mongodb -Dspring.data.mongodb.host=mongodb -Dspring.data.mongodb.port=32717 -jar bin/acmeair-booking-service-exec.jar > $CURDIR/booking.log 2>&1 &
exit 0