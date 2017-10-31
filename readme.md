GuessWho

Build instructions:

from the main directory:

`javac -d ./build src/*.java`
`cd build`
`jar cfm ../GuessWho.jar ../manifest.mf *.class`
`cd ..`
`java -jar GuessWho.jar`
