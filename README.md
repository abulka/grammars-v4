![CI](https://github.com/antlr/grammars-v4/workflows/CI/badge.svg)

# Grammars-v4

This repository is a collection of formal grammars written for [ANTLR v4](https://github.com/antlr/antlr4)

The root directory name is the all-lowercase name of the language or file format parsed by the grammar. For example, java, cpp, csharp, c, etc...

## ANDY NOTES

From Mac Notes "Antlr"

Antlr4 parsing
https://github.com/antlr/antlr4/blob/master/doc/getting-started.md

	cd /usr/local/lib
	$ curl -O https://www.antlr.org/download/antlr-4.12.0-complete.jar

	java -XshowSettings:properties -version

to get JAVA_HOME

edit .bashrc

	export JAVA_HOME=‘/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home’
	export CLASSPATH=".:/usr/local/lib/antlr-4.12.0-complete.jar:$CLASSPATH"
	alias antlr4='java -Xmx500M -cp "/usr/local/lib/antlr-4.12.0-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
	alias grun='java -Xmx500M -cp "/usr/local/lib/antlr-4.12.0-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'

Build a grammar for Java target

	git clone git@github.com:antlr/grammars-v4.git
	cd grammars-v4/

### CSharp
my issue https://github.com/antlr/grammars-v4/issues/3412
(resolved)

	cd grammars-v4/csharp
	cp Java/* .    <— java support files
	antlr4 CSharpLexer.g4 CSharpParser.g4 CSharpPreprocessorParser.g4  <- generates .java files
	javac *.java
	grun CSharp compilation_unit examples/C2430.cs -tree


### Typescript
my issue https://github.com/antlr/grammars-v4/issues/3413
(resolved - started to work ok the next morning, weird!)

	cd javascript/typescript/
	cp Java/* . 
	antlr4  TypeScriptLexer.g4 TypeScriptParser.g4
	javac *.java

	grun TypeScript program examples/Class.ts -tree

or

	cat examples/Function.ts | grun TypeScript program -gui



## FAQ

Please refer to the [grammars-v4 Wiki](https://github.com/antlr/grammars-v4/wiki)

## Code of Conduct

Please refer to [House Rules](https://github.com/antlr/grammars-v4/blob/master/House_Rules.md)
