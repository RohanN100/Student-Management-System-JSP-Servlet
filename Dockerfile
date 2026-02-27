FROM tomcat:9.0

COPY StudentCRUD2.war /usr/local/tomcat/webapps/

EXPOSE 8080