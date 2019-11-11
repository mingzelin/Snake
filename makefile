# super simple makefile
# call it using 'make NAME=name_of_code_file_without_extension'
# (assumes a .java extension)
NAME = "Main"

default:
	@echo "Compiling..."
	javac *.java

run:
	@echo "Running..."
	java $(NAME)

test:
	@echo Test 1: No args
	java $(NAME)


clean:
	rm -rf *.class
