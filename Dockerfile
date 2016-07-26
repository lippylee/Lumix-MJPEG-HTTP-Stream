FROM tomcat:8.5.4-jre8
COPY MJPG.war /usr/local/tomcat/webapps/MJPG.war
EXPOSE 49199:49199
