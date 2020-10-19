UNAME := $(shell uname)
ifeq ($(UNAME), Linux)
DIREXIST = test -d build; then ; mkdir build
REMOVE =
else
DIREXIST = if not exist build mkdir build
REMOVE = del /s /q build
endif
JFLAGS = -source 1.8 -target 1.8 -d build

SRCDIR = src/main/java/m1graf2020


all: folder Main Java

Main:
	javac $(JFLAGS)  $(SRCDIR)/Edge.java $(SRCDIR)/Node.java $(SRCDIR)/Graf.java $(SRCDIR)/Main.java

Java:
	cd build && java -cp m1graf2020;. m1graf2020.Main && echo $(UNAME)

folder:
	$(DIREXIST)

clean:
	$(REMOVE)