[![Build Status](https://travis-ci.org/sand1p/1timeshare.svg?branch=master)](https://travis-ci.org/sand1p/1timeshare)
# 1timeshare
## Description: 
Sometimes we need to share our credentials with our dear one's, We share it over messengers such as facebook, Whatss app. And those credentials stays there forever. I have attempted a credential share channel. Where credentials will be temporarily stored in encrypted form. And a link will be provided to you. You can share that link with intended recipient. Once that link is accessed, credentials will be returned. And the link will expired, immediately.

## Features: 
    1. Allows to share credentials securely.
    2. Link expires once accessed, if not accessed then in 5 minutes.
    3. Data is stored at server side in encrypted form.
    4. End to End encryption is implemented.
### Project Tracking and RoadMap: https://trello.com/b/32CuMOih/1timeshare
## How to run application
### Prerequisites (platform setup): 
    1. java 8 or latest
    2. sbt 
### Steps to run application in dev mode: 
    1. Install sbt in your local environment. 
    2. Checkout the repository.
    3. Enter the project directory.
    4. Execute following commands in CLI.
    sbt : to enter into sbt console. Execute following commands inside sbt console.
      - clean : to clean previous executables. 
      - compile : to compile sbt application.
      - run -Dhttp.port=PORT_NUMBER -Dconfig.file=application.conf    : to deploy application locally PORT_NUMBER : e.g. 9111
    5. Now open any REST client. And execute following APIs
### API List
      1. Upload Password, to save the password this API us used. 
         Request: 
           URI: localhost:9000/secret/
           Request Method: POST
           Body:
           { 
             "value" : "password",
             "expireAt" : 20190303004900
           }
           value: passoword to share.
           expireAt: Time when password expires.  
         Response:    
          i. Status: 200 
             Body:
              {
                "link": "http://localhost:9000/secret/ed73ba9b-0267-41d4-aabe-5337d3356b9b"
              }  
              
           Note: Open this link in the browser to receive Password.
          ii. Status: 400 
              Body:
              {
                 "result": "Invalid request"
              } 
                
        2. Get password, to get the password this API is used.
            Request:
              URI: http://localhost:9000/secret/UUID_String
              Request Method: GET   
              Response:
              i. Status: 200
               Body: 
                {
                   "password": "password"
                }
                Success: Password that is protected by the UUID
               ii. Status: 404 Not Found 
                Body: 
                 {
                     "result": "Link is no longer valid."
                 }
## How to build
      Steps: 
      1. Clone project in any directory 
      2. Enter directory 
      3. Execute Command: sbt dist 
          zip file will be created and the location will be printed in logs.
      4. Unzip file, you will get and jar to deploy
      5. deploy using command on any linux machine:  
        nohup bin/vcs -J-Xms512M -J-Xmx1G -Dpidfile.path=RUNNING_PID -Dlogger.file=prod/logback.xml -Dconfig.file=prod/prod.conf 
        -Dhttp.port=9000 &> vcs.out & tailf vcs.out
        -J-Xms512M :
        -J-Xmx1G  :
        -Dpidfile.path = RUNNING_PID : to store the java process ID.
        -Dlogger.file = logback.xml : Logging configurations 
        -Dconfig.file = prod/prod.conf : Application configuration file.
        -Dhttp.port = 9000   : port on which process will be running
        Logs will be written in vcs.out file.
           
##  Tech stack
### Scala 
### Play Framework
### PlaySpecs
### Json 
### Travis CI 
### Docker Support 
### Heroku 
