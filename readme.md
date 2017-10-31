GuessWho

Build instructions:

from the main directory:

1. `javac -d ./build src/*.java`
1. `cd build`
1. `jar cfm ../GuessWho.jar ../manifest.mf *.class`
1. `cd ..`
1. `java -jar GuessWho.jar`
