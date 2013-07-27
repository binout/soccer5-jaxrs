#!/bin/bash

mvn -q clean install -Dmaven.test.skip=true &&  bees app:deploy -a soccer5 -t java -R java_version=1.7 -R class=net.binout.soccer5.Soccer5Server -R classpath="*:lib/*:soccer5-bin/*:soccer5-bin/lib/*" target/soccer5-bin.zip