#!/bin/sh

osascript -e 'tell app "iPhone Simulator" to quit'
xcodebuild -sdk iphonesimulator -configuration Debug -scheme UnitTests clean
xcodebuild -sdk iphonesimulator -configuration Debug -scheme UnitTests build TEST_AFTER_BUILD=YES
