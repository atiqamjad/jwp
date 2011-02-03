#!/bin/sh
cd conf
CP=
for i in `ls ../lib/*.jar && ls *.properties *.txt`
do
  CP=${CP}:${i}
done
#echo ${CP}
java -cp ${CP} com.samsonych.Main
