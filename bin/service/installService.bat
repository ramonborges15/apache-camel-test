set SERVICE_NAME=ApacheCamelMiddleware
set PR_INSTALL=C:\Users\ramon.sales\Documents\NetBeansProjects\apache-camel-app\bin\service\ApacheCamelMiddleware.exe
set PR_DESCRIPTION=Processo que realiza a trasferência dos arquivos da pasta "pedidos" para "saída".
 
REM Service log configuration
set PR_LOGPREFIX=%SERVICE_NAME%
set PR_LOGPATH=C:\Users\ramon.sales\Documents\NetBeansProjects\apache-camel-app\bin\service\log
set PR_STDOUTPUT=C:\Users\ramon.sales\Documents\NetBeansProjects\apache-camel-app\bin\service\log\stdout.txt
set PR_STDERROR=C:\Users\ramon.sales\Documents\NetBeansProjects\apache-camel-app\bin\service\log\stderr.txt
set PR_LOGLEVEL=Error
 
REM Path to java installation
set PR_JVM=C:\Program Files (x86)\Java\jre1.8.0_261\bin\client\jvm.dll
set PR_CLASSPATH=C:\Users\ramon.sales\Documents\NetBeansProjects\apache-camel-app\target\apache-camel-estudos.jar
 
REM Startup configuration
set PR_STARTUP=auto
set PR_STARTMODE=jvm
set PR_STARTCLASS=com.estudo.apache.camel.app.Iniciar
set PR_STARTMETHOD=start
set PR_STARTPATH=C:\Users\ramon.sales\Documents\NetBeansProjects\apache-camel-app\bin
 
REM Shutdown configuration
set PR_STOPMODE=jvm
set PR_STOPCLASS=com.estudo.apache.camel.app.Iniciar
set PR_STOPMETHOD=stop
set PR_STOPPATH=C:\Users\ramon.sales\Documents\NetBeansProjects\apache-camel-app\bin
 
REM JVM configuration
set PR_JVMMS=512
set PR_JVMMX=1024
set PR_JVMSS=4000

REM Install service
ApacheCamelMiddleware.exe //IS//%SERVICE_NAME%