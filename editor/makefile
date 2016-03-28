MAIN=TextEditor
CLASSPATH=".:./txtlib.jar"

run: compile
	java -cp ${CLASSPATH} ${MAIN}
compile: TextEditor.class
TextEditor.class: TextEditor.java
	javac -cp ${CLASSPATH} ${MAIN}.java

clean:
	-rm -f *.class *~
