#!/bin/sh

xcodebuild -target Frankified -sdk iphonesimulator -configuration Debug clean build
RETVAL=$?
osascript -e 'tell app "iPhone Simulator" to quit'
exit $RETVAL