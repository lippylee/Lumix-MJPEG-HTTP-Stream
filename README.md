# UDP MJPEG stream to http MJPEG stream converter for wifi enabled  Panasonic Lumix G/GH series cameras

This servlet converts the UDP stream from a G series Lumix camera into a HTTP MJPEG stream that can be easily added to any broadcast / capture application.

Note that this is an early release and there may be mistakes in either the code or instructions here. If you find any, please raise an issue or open a pull request with the changes. Thanks!

You can test this out quickly by pulling the docker image as follows:
    docker pull lippylee/lumixmjpg
    docker run -p 8080:8080 -p 49199:49199/udp lumixmjpg
    
The docker image can be found [here](https://hub.docker.com/r/lippylee/lumixmjpg/)

### Build dependencies
So far I've built with the following:

#### Core
    Tomcat7+/8+
    JDK 1.8
    
#### Non core
    brew (OSX)
    docker

### Build steps:
Replace [Tomcat version] with the version of tomcat you want to install on either platform, for Ubuntu you can use either tomcat7 or tomcat8, for OSX, brew automatically installs the latest stable version, you can get the version number by running:

    ls /usr/local/Cellar/tomcat

#### Ubuntu
    sudo apt-get update && sudo apt-get install tomcat[Tomcat Version]
    javac -cp .:/usr/share/tomcat[Tomcat Version]/lib/servlet-api.jar MJPG.java
    mkdir WEB-INF/classes
    cp MJPG.class WEB-INF/classes/MJPG.class
    jar cvf MJPG WEB-INF/

#### OSX
    brew cask install java docker
    brew install tomcat
    javac -cp .:/usr/local/Cellar/tomcat/[Tomcat Version]/libexec/lib/servlet-api.jar MJPG.java
    mkdir WEB-INF/classes
    cp MJPG.class WEB-INF/classes/MJPG.class
    jar cvf MJPG WEB-INF/
    
#### docker
    docker build -t lumixmjpg .
    
### Running the stream converter

#### OSX
    cp MJPG.war /usr/local/Cellar/tomcat/[Tomcat Version]/libexec/webapps/
    
#### Ubuntu
    cp MJPG.war /var/lib/tomcat8/webapps

#### Docker (Ubuntu / OSX)
    docker pull lippylee/lumixmjpg
    docker run -p 8080:8080 -p 49199:49199/udp lumixmjpg

### To access stream:
Open [http://localhost:8080/MJPG/MJPG](http://localhost:8080/MJPG/MJPG) in your favorite browser

## Credits:
Inspired by code from: http://www.personal-view.com/talks/discussion/6703/control-your-gh3-from-a-web-browser-now-with-video-/p1

## License
This project is licensed under the [MIT license](http://opensource.org/licenses/mit-license.php).
