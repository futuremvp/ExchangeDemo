#!/bin/sh

TEST_TARGET_EXECUTABLE_PATH="$TARGET_BUILD_DIR/$EXECUTABLE_PATH"

if [ ! -e "$TEST_TARGET_EXECUTABLE_PATH" ]; then
  echo ""
  echo "  ------------------------------------------------------------------------"
  echo "  Missing executable path: "
  echo "     $TEST_TARGET_EXECUTABLE_PATH."
  echo "  The product may have failed to build or could have an old xcodebuild in your path (from 3.x instead of 4.x)."
  echo "  ------------------------------------------------------------------------"
  echo ""
  exit 1
fi

# export DYLD_ROOT_PATH="$SDKROOT"
# export DYLD_FRAMEWORK_PATH="$CONFIGURATION_BUILD_DIR"
# export IPHONE_SIMULATOR_ROOT="$SDKROOT"
# 
# export NSDebugEnabled=YES
# export NSZombieEnabled=YES
# export NSDeallocateZombies=NO
# export NSHangOnUncaughtException=YES
# export NSAutoreleaseFreedObjectCheckEnabled=YES
# 
# export DYLD_FRAMEWORK_PATH="$CONFIGURATION_BUILD_DIR"
# export CFFIXED_USER_HOME="${HOME}/Library/Application Support/iPhone Simulator/$IPHONESIMULATOR_PLATFORM_VERSION"
# 
# RUN_CMD="\"$TEST_TARGET_EXECUTABLE_PATH\" -RegisterForSystemEvents"
# 
# echo "Running: $RUN_CMD"
# set +o errexit # Disable exiting on error so script continues if tests fail
# eval $RUN_CMD
# RETVAL=$?
# set -o errexit
# 
# unset DYLD_ROOT_PATH
# unset DYLD_FRAMEWORK_PATH
# unset IPHONE_SIMULATOR_ROOT

export APP_BUNDLE_PATH=$TARGET_BUILD_DIR/$EXECUTABLE_FOLDER_PATH

pushd $SRCROOT/../mobile-core-acceptence-test
echo "Running: cucumber"
# [[ -s "$HOME/.rvm/scripts/rvm" ]] && . "$HOME/.rvm/scripts/rvm"
# rvm use 1.9.3
cucumber -f junit -o iOS/reports -r iOS/Frank/features
RETVAL=$?
# cucumber -r iOS/Frank/features
popd

exit $RETVAL