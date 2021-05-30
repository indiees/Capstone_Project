#!/bin/bash

cd /home/ec2-user/ral
mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V