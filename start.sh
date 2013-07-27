#!/bin/bash

mvn -q clean install -Dmaven.test.skip=true && java -cp "target/lib/*:target/soccer5.jar" net.binout.soccer5.Soccer5Server