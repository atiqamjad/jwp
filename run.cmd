@rem = 'shell script
@echo off

 @echo off
 setlocal enabledelayedexpansion
 set CP="
 for /R ./lib %%a in (*.jar) do (set CP=!CP!;%%a)
 for /R ./conf %%a in (*.*) do (set CP=!CP!;%%a)
 set CP=!CP!"
 rem echo !CP!

 java -cp %CP% com.samsonych.project.gads.Main

goto :EOF
@rem ';
#!/bin/sh
CP=
for i in `ls ./lib/*.jar && ls ./conf/*.*`
do
  CP=${CP}:${i}
done
#echo ${CP}
java -cp ${CP} com.samsonych.project.gads.Main
