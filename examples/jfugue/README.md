# JFugue usage examples

This directory includes a few examples of how to use JFugue.
Included are two programs in Java, Wordsongs and LCompose, which
demonstrate how simple it is to work with JFugue.

## Wordsongs
This program takes in a textfile as an input, and translates that file
into "music" by way of analyzing the string tokens that make up the file.

Compilation:

    javac -cp ../../lib/jfugue.jar:. Wordsongs.java

Running:

    java -cp ../../lib/jfugue.jar:. Wordsongs anInputFile.txt

## LCompose
This program allows a user to interactively compose music using Lindenmayer
Systems, a formalism that we'll also need to look at for one of the modules
in the assignment.

Compilation:

    javac -cp ../../lib/jfugue.jar:. LCompose.java

Running:

    java -cp ../../lib/jfugue.jar:. LCompose

