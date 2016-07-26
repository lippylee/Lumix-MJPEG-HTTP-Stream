# Panasonic Lumix GH3 (and probably GH4) wifi UDP MJPEG stream to http MJPEG stream converter

## Build dependencies
    Tomcat7+
    JDK
    brew (OSX)

## Build steps:
Replace [Tomcat version] with the version of tomcat you want to install on either platform, for Ubuntu you can use either tomcat7 or tomcat8, for OSX, brew automatically installs the latest stable version, you can get the version number by running:

    ls /usr/local/Cellar/tomcat

### Ubuntu
    sudo apt-get update && sudo apt-get install tomcat[Tomcat Version]
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
    
## To run:

### OSX
    cp MJPG.war /usr/local/Cellar/tomcat/[Tomcat Version]/libexec/webapps/
    
### Ubuntu
    cp MJPG.war /var/lib/tomcat8/webapps

### Docker (Ubuntu / OSX)
    docker build -t mjpg .
    docker run -p 8080:8080 -p 49199:49199/udp mjpg

## To access stream:
Open http://localhost:8080/MJPG/MJPG in your favorite browser
