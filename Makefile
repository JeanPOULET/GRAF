UNAME := $(shell uname -s)
SHELL=CMD
ifeq ($(UNAME), Linux)
DIREXIST = if test -d build; then echo 0;else mkdir build; fi
REMOVE = rm -rf build
JAVACMP = java -cp . Main
else
DIREXIST = if not exist build mkdir build
REMOVE = del /s /q build
JAVACMP = java -cp m1graf2020;. Main
endif
JFLAGS = -source 1.8 -target 1.8 -d build


SRCDIR_PW2 = src/main/java/m1graf2020
SRCDIR_PW3 = src/main/java/maximumflow
MAINDIR = src/main/java


all: folder Main Java

Main:
	javac $(JFLAGS)  $(SRCDIR_PW2)/*.java $(SRCDIR_PW3)/*.java $(MAINDIR)/Main.java

Java:
	cd build && $(JAVACMP)

folder:
	$(DIREXIST)

clean:
	$(REMOVE)