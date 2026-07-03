deploy:
	mvn tomcat7:deploy
undeploy:
	mvn tomcat7:undeploy
redeploy:
	mvn tomcat7:redeploy
install: stop-tomcat
	curl https://archive.apache.org/dist/tomcat/tomcat-7/v7.0.109/bin/apache-tomcat-7.0.109.tar.gz -o tomcat.tar.gz
	tar -xvzf tomcat.tar.gz
	cp users.xml apache-tomcat-*/conf/tomcat-users.xml
start-tomcat: run-tomcat
run-tomcat:
	cd apache-tomcat-*/bin && ./startup.sh
stop-tomcat:
	cd apache-tomcat-*/bin && ./shutdown.sh
restart-tomcat: stop-tomcat
	cd apache-tomcat-*/bin && ./startup.sh
