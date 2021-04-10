#!/bin/bash
SRC=../src
PACKAGE=$SRC/clientserver
rm -rf ../javadoc
javadoc \
    -public \
    -link https://docs.oracle.com/en/java/javase/11/docs/api/ \
    -d ../javadoc \
    $PACKAGE/*