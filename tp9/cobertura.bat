
echo Exécution en ligne de commandes de la classe de tests
rem java -cp .;./junit-4.11.jar;./hamcrest-core-1.3.jar;./+libs/hsqldb1.8.jar junit.textui.TestRunner question3.AuditeurDAOTests

rem del /Q cobertura.ser
rem del /Q cobertura_q1.ser
del /Q cobertura_q3.ser
rd /S/Q instr_tp
rd /S/Q coverage
echo Instrumention des .class afin en vue de produire une trace de l'execution
rem pause
rem call ./cobertura-2.1.1/cobertura-instrument.bat --basedir . --destination instr_tp3 -cp .;./junit.jar  *
rem call ./cobertura-2.1.1/cobertura-instrument.bat --basedir . --destination instr_tp3 -cp .;../junit-4.11.jar;../hamcrest-core-1.3.jar
call ./cobertura-2.1.1/cobertura-instrument.bat --basedir . --auxClasspath ./junit-4.11.jar;./hamcrest-core-1.3.jar;./+libs/hsqldb1.8.jar;./+libs/jdom.jar -cp . --destination instr_tp


echo Execution en ligne de commandes de la classe de tests instrumentee
rem pause
java -cp ./cobertura-2.1.1/cobertura-2.1.1.jar;./cobertura-2.1.1/lib/slf4j-api-1.7.5.jar;./instr_tp;./junit-4.11.jar;./hamcrest-core-1.3.jar;./+libs/hsqldb1.8.jar;. -Dnet.sourceforge.cobertura.datafile=cobertura_q3.ser junit.textui.TestRunner question3.AuditeurDAOTests

echo Generation du rapport au format HTML
rem pause
rem call ./cobertura-2.1.1/cobertura-merge.bat --datafile cobertura.ser cobertura_q1.ser cobertura_q2.ser
call ./cobertura-2.1.1/cobertura-report.bat --format html --datafile cobertura_q3.ser --destination coverage .

echo Quelles sont les classes au faible taux de couverture ?
rem pause
call ./cobertura-2.1.1/cobertura-check.bat --branch 50 --totalline 70

rem del /Q cobertura.ser
rem del /Q cobertura_q1.ser
del /Q cobertura_q3.ser
rd /S/Q instr_tp
echo Execution du navigateur par defaut
rem pause
start ./coverage/index.html