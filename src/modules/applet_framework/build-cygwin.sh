#!/bin/bash

echo "Building..."
javac -cp `cygpath -wp ../../lib/jfugue.jar:.` *.java
