#!/bin/bash -xe

#SBT
ORG="com.bilal-fazlani"
PLUGIN_NAME="myplugin"
SCALA_VERSION="3.0.0-M2"
PLUGIN_VERSION="0.1.0-SNAPSHOT"

#PLUGIN
REFRESH="5s"
EXTENSION="sh"
PLUGINS_DIR="$HOME/projects/bitbar-plugins"

#OTHERS
PLUGIN_FILENAME="$PLUGIN_NAME.$REFRESH.$EXTENSION"

rm -rf ~/.ivy2/local/"$ORG"/"$PLUGIN_NAME"_"$SCALA_VERSION"/
PUBLISH_PLUGIN="true" sbt ";clean ;publishLocal"
coursier bootstrap -f -o "$PLUGINS_DIR"/"$PLUGIN_FILENAME" --standalone "$ORG":"$PLUGIN_NAME"_"$SCALA_VERSION":"$PLUGIN_VERSION"
chmod +x "$PLUGINS_DIR"/"$PLUGIN_FILENAME"
