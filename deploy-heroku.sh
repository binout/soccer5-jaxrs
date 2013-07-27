#!/bin/bash

#Deploy app on http://rocky-everglades-5890.herokuapp.com/
#More info : https://devcenter.heroku.com/articles/java

mvn -q clean install -Dmaven.test.skip=true && git push heroku master