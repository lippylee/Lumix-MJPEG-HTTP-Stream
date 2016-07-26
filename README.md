# Panasonic Lumix GH3 (and probably GH4) wifi UDP MJPEG stream to http MJPEG stream converter

## Build dependencies
    Tomcat7+
    JDK
    brew (OSX)

## Build steps:
### Ubuntu
    sudo apt-get update && sudo apt-get install tomcat8
    javac -cp .:/usr/share/tomcat[Tomcat Version]/lib/servlet-api.jar MJPG.java
    mkdir WEB-INF/classes
    cp MJPG.class WEB-INF/classes/MJPG.class
    jar cvf MJPG WEB-INF/

### OSX
    brew cask install java docker
    brew install tomcat
    javac -cp .:/usr/local/Cellar/tomcat/[Tomcat Version]/libexec/lib/servlet-api.jar MJPG.java
    mkdir WEB-INF/classes
    cp MJPG.class WEB-INF/classes/MJPG.class
    jar cvf MJPG WEB-INF/

### Docker (Linux / OSX)
    docker build -t mjpg .

## To run:
    docker run -p 8080:8080 -p 49199:49199/udp mjpg

## To access stream:
Open http://localhost:8080/MJPG in your favorite browser
