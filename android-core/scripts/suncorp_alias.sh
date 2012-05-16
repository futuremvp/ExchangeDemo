#!/bin/bash

SUNCORP_PACKAGE="au.com.suncorpbank.app"
SUNCORP_ACTIVITY="au.com.suncorpbank.activity.MainActivity"

SUNCORP_PROJECT_DIR=$1
SUNCORP_PROJECT_SRC="$SUNCORP_PROJECT_DIR"

alias reaandroid="cd $SUNCORP_PROJECT_DIR"
alias install_to_multi_devices="sh ./scripts/install_to_multi_devices.sh ./bin/android_core-release.apk"
alias clean-app='cd $SUNCORP_PROJECT_SRC&&ant clean'

alias scp-test='clean-app&&cd $SUNCORP_PROJECT_SRC&&ant generate-testing&&install_to_multi_devices $SUNCORP_PACKAGE $SUNCORP_ACTIVITY'
alias scp-rel='clean-app&&cd $SUNCORP_PROJECT_SRC&&ant generate-production&&install_to_multi_devices $SUNCORP_PACKAGE $SUNCORP_ACTIVITY'
alias scp-unit-test='cd $SUNCORP_PROJECT_SRC&&ant test'