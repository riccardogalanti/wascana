Eclipse CDT for Windows Development.

Wascana is based on the MinGW (Minimal Gnu for Windows) gcc and g++ compilers from tdragon.net and the binutils and msys from mingw.org. Wascana will use CDT's new internal debugger (EDC) instead of gdb.

To complete the IDE, SDKs (libraries and headers) are included to support Windows development. These packages mainly come from the Fedora mingw cross development packages. Additional packages may come from other sources.

Wascana is installed into an Eclipse C/C++ IDE that can be downloaded from eclipse.org. A specific Eclipse C/C++ IDE for Windows may be added in the future that prepopulates the repository URL.

An NSIS based setup executable will be developed and hosted here that combines the Sun JRE and the C/C++ IDE. It will include options to install some or all of the tools and SDKs at install time. There's a 100MB limit to file size at google code or we'd put the whole thing in.

Initial 1.0 release will be synced with the Helios Eclipse release train (CDT 7.0).