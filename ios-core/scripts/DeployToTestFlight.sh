#!/bin/sh

TEST_FLIGHT_TEAM_TOKEN=f4864c99d40b3b7f044a3afb4ff4f581_OTAyODAyMDEyLTA1LTE2IDIzOjM4OjQ3LjMwODMyMw
TEST_FLIGHT_API_TOKEN=02c88e8bc090887bbc294ef6b2a23d4c_NDQxMjkzMjAxMi0wNS0xNSAwMDo1MDozMy43Mjc1MDQ
SIGNING_IDENTITY="iPhone Distribution: ThoughtWorks Inc"
PROVISIONING_PROFILE="scripts/In_House.mobileprovision"

case $TARGET_NAME in
  *Production*) PRODUCT_NAME=Exchange;;
  *) PRODUCT_NAME=$TARGET_NAME;;
esac

ARCHIVE_PARENT_DIR=$HOME/Library/Developer/Xcode/Archives
ARCHIVE_PARENT_DIR=/`ls -td $ARCHIVE_PARENT_DIR/* | head -1`
ARCHIVE_NAME=`ls -td $ARCHIVE_PARENT_DIR/*.xcarchive | head -1`

DEST_DIR="archives"
mkdir -p "$DEST_DIR"
ARCHIVE_DIR=`pwd`/archives

ARCHIVE_APPLICATION=$ARCHIVE_NAME/Products/Applications/${PRODUCT_NAME}.app
DSYM="$ARCHIVE_NAME/dSYMs/${PRODUCT_NAME}.app.dSYM"

echo "Creating .ipa for ${PRODUCT_NAME}"

/bin/rm "archives/${PRODUCT_NAME}.ipa"
xcrun -sdk iphoneos \
  PackageApplication \
  "${ARCHIVE_APPLICATION}" \
  -o "${ARCHIVE_DIR}/${PRODUCT_NAME}.ipa" \
  --sign "${SIGNING_IDENTITY}" \
  --embed "${PROVISIONING_PROFILE}" \
  -v

echo "Created .ipa for ${PRODUCT_NAME}"

echo "Zipping .dSYM for ${PRODUCT_NAME}"

/bin/rm "archives/${PRODUCT_NAME}.dSYM.zip"
/usr/bin/zip -r "archives/${PRODUCT_NAME}.dSYM.zip" "${DSYM}"

echo "Created .dSYM for ${PRODUCT_NAME}"

echo "Uploading to TestFlight"

curl "http://testflightapp.com/api/builds.json" \
  -F file=@"archives/${PRODUCT_NAME}.ipa" \
  -F dsym=@"archives/${PRODUCT_NAME}.dSYM.zip" \
  -F api_token="${TEST_FLIGHT_API_TOKEN}" \
  -F team_token="${TEST_FLIGHT_TEAM_TOKEN}" \
  -F notify=True \
  -F distribution_lists='All' \
  -F notes="Build uploaded automatically."

echo "Uploaded to TestFlight"
