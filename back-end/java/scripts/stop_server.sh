#!/bin/bash

cd /home/ec2-user/ral
killall -9 java -q || echo 'no java process was running'