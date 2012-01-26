# JFugue usage examples

This directory includes a few examples of how to use JFugue.
Included are three programs in Java, Wordsongs, LCompose, and IntervalPlayer, which
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

## IntervalPlayer
This program demonstrates the use of JFugue's interval notation to play the 12 
major scales from the same MusicString.

Compilation:

    javac -cp ../../lib/jfugue.jar:. IntervalPlayer.java
    
Running:

    java -cp ../../lib/jfugue.jar:. IntervalPlayer

