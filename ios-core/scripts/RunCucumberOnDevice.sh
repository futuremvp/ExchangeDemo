#!/bin/sh

xcodebuild -target Frankified -sdk iphoneos -configuration Debug clean build
RETVAL=$?
exit $RETVAL
