#!/bin/sh

xcodebuild -target Frankified -sdk iphonesimulator -configuration Debug clean build
osascript -e 'tell app "iPhone Simulator" to quit'