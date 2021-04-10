#!/bin/bash
SRC=../src/
cd $SRC || exit
mkdir "_build"
javac clientserver/* -d _build
cd _build || exit
jar -c  --file=../../client-server.jar --main-class=clientserver/CLI clientserver
cd .. || exit
rm -rf _build