# Sportdec backend coding challenge

## Introduction
The coding challenge to create a commandline API mashup of the Github and Twitter APIs.

## Getting Started

### Prerequisite
 - [Maven](https://maven.apache.org/download.cgi) (3.5.3): 
 - [Java 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) 

### How to build
Run maven install to generate the .jar file:

    mvn clean install

It will generate the file 

    sportdec-app-1.0.0-jar-with-dependencies.jar

### How to set up

Before run the system,  you have to require a Twitter `consumer key`, `secret key`, `access token` and `access token secret`.

Now that you have the keys, you have to set up the `sportdec.properties` file like mentioned bellow:

    consumer.key=CONSUMER_KEY
    consumer.secret=CONSUMER_SECRET
    access.token=ACCESS_TOKEN
    access.token.secret=ACCESS_TOKEN_SECRET

The `sportdec.properties` file have to be in the same directory of the `.jar` file.

Ex.:

    root/sportdec-app-1.0.0-jar-with-dependencies.jar
    root/sportdec.properties

### How to run
After set up the environment, you can run the application using the command line: 

    java -jar sportdec-app-1.0.0-jar-with-dependencies.jar [-w <arg>] [-o <arg>] [-s <arg>] 

Where:

    -w, --word  <arg>   Required. The word to search.
    -o, --order <arg>   Determines if the first search result returned is [desc] or [asc]. Default: [desc]
    -s, --sort  <arg>   Sorts the results by number of [stars], [forks], or
                        [help-wanted-issues] or how recently the items were
                        [updated]. Default: [best match]

You don't need to inform `-o` or `-s` but if you inform you must inform the argument to the command.

### How to use
After execue the application, using the followed command, e.g.:

    java -jar .\sportdec-app-1.0.0-jar-with-dependencies.jar -w futball

You will see a list of projects as followed:
![enter image description here](https://www.photobox.co.uk/my/photo/full?photo_id=501661793096)

You may select the projects you want to look for the tweets by typing the id of the projects. If you want to list more than one project, you may separate the ids by space.
