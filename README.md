# Panasonic Lumix GH3 (and probably GH4) wifi UDP MJPEG stream to http MJPEG stream converter

## Build dependencies
    Tomcat7+
    JDK
    brew (OSX)

## Build steps:
### Ubuntu
    sudo apt-get update && sudo apt-get install tomcat8
    javac -cp .:/usr/share/tomcat[Tomcat Version]/lib/servlet-api.jar MJPG.java
    cp MJPG.class WEB-INF/classes/MJPG.class
    jar cvf MJPG WEB-INF/
    docker build -t mjpg .

### OSX
    brew cask install java
    brew install tomcat docker
    javac -cp .:/usr/local/Cellar/tomcat/[Tomcat Version]/lib/servlet-api.jar MJPG.java
    cp MJPG.class WEB-INF/classes/MJPG.class
    jar cvf MJPG WEB-INF/
    docker build -t mjpg .

## To run:
    docker run -p 8080:8080 -p 49199:49199 mjpg

## To access stream:
Open http://localhost:8080/MJPG/MJPG in your favorite browser
