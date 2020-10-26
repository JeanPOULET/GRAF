UNAME := $(shell uname)
ifeq ($(UNAME), Linux)
DIREXIST = if test -d build; then echo 0;else mkdir build; fi
REMOVE = rm -rf build ; rm *.dot
JAVACMP = java -cp . m1graf2020.Main
else
DIREXIST = if not exist build mkdir build
REMOVE = del /s /q build ; cd .. && del *.dot
JAVACMP = java -cp m1graf2020;. m1graf2020.Main
endif
JFLAGS = -source 1.8 -target 1.8 -d build


SRCDIR = src/main/java/m1graf2020


all: folder Main Java

Main:
	javac $(JFLAGS)  $(SRCDIR)/*.java

Java:
	cd build && $(JAVACMP)

folder:
	$(DIREXIST)

clean:
	$(REMOVE)