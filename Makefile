UNAME := $(shell uname)
ifeq ($(UNAME), Linux)
DIREXIST = if test -d build; then echo 0;else mkdir build; fi
REMOVE = rm -rf build
JAVACMP = java -cp . m1graf2020.Main
else
DIREXIST = if not exist build mkdir build
REMOVE = del /s /q build
JAVACMP = java -cp m1graf2020;. m1graf2020.Main
endif
JFLAGS = -source 1.8 -target 1.8 -d build


SRCDIR = src/main/java/m1graf2020


all: folder Main Java

Main:
	javac $(JFLAGS)  $(SRCDIR)/Edge.java $(SRCDIR)/Node.java $(SRCDIR)/Graf.java $(SRCDIR)/Main.java $(SRCDIR)/UndirectedGraf.java

Java:
	cd build && $(JAVACMP)

folder:
	$(DIREXIST)

clean:
	$(REMOVE)