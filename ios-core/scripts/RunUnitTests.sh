#!/bin/sh

osascript -e 'tell app "iPhone Simulator" to quit'
xcodebuild -sdk iphonesimulator -configuration Debug -target UnitTests clean
xcodebuild -sdk iphonesimulator -configuration Debug -target UnitTests build TEST_AFTER_BUILD=YES | 'Third Party/OCUnit2JUnit/ocunit2junit.rb'