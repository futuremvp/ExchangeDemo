default: proj

.PHONY: clean
clean:
	xcodebuild -project ExchangeDemo.xcodeproj -alltargets clean

.PHONY: test
test:
	sh ./scripts/RunUnitTests.sh

.PHONY: cucumber
cucumber:
	sh ./scripts/RunCucumber.sh

.PHONY: archive
archive:
	xcodebuild -scheme $(TARGET_NAME) -configuration Release -sdk iphoneos clean
	xcodebuild -scheme $(TARGET_NAME) -configuration Release -sdk iphoneos archive

.PHONY: testflight
testflight:
	sh ./scripts/DeployToTestFlight.sh

.PHONY: proj
proj:
	sh ./scripts/check_no_xcode_build_settings.sh ExchangeDemo.xcodeproj
