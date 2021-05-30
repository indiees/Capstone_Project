#!/bin/bash

cd /home/ec2-user/ral
killall -s KILL node -q || echo 'no node process was running'