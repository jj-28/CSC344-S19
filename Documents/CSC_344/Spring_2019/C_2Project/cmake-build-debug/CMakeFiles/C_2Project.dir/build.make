# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.12

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /cygdrive/c/Users/jeffr/.CLion2018.2/system/cygwin_cmake/bin/cmake.exe

# The command to remove a file.
RM = /cygdrive/c/Users/jeffr/.CLion2018.2/system/cygwin_cmake/bin/cmake.exe -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/C_2Project.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/C_2Project.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/C_2Project.dir/flags.make

CMakeFiles/C_2Project.dir/CMicroproject.c.o: CMakeFiles/C_2Project.dir/flags.make
CMakeFiles/C_2Project.dir/CMicroproject.c.o: ../CMicroproject.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/C_2Project.dir/CMicroproject.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/C_2Project.dir/CMicroproject.c.o   -c /cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project/CMicroproject.c

CMakeFiles/C_2Project.dir/CMicroproject.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/C_2Project.dir/CMicroproject.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project/CMicroproject.c > CMakeFiles/C_2Project.dir/CMicroproject.c.i

CMakeFiles/C_2Project.dir/CMicroproject.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/C_2Project.dir/CMicroproject.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project/CMicroproject.c -o CMakeFiles/C_2Project.dir/CMicroproject.c.s

# Object files for target C_2Project
C_2Project_OBJECTS = \
"CMakeFiles/C_2Project.dir/CMicroproject.c.o"

# External object files for target C_2Project
C_2Project_EXTERNAL_OBJECTS =

C_2Project.exe: CMakeFiles/C_2Project.dir/CMicroproject.c.o
C_2Project.exe: CMakeFiles/C_2Project.dir/build.make
C_2Project.exe: CMakeFiles/C_2Project.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable C_2Project.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/C_2Project.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/C_2Project.dir/build: C_2Project.exe

.PHONY : CMakeFiles/C_2Project.dir/build

CMakeFiles/C_2Project.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/C_2Project.dir/cmake_clean.cmake
.PHONY : CMakeFiles/C_2Project.dir/clean

CMakeFiles/C_2Project.dir/depend:
	cd /cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project /cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project /cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project/cmake-build-debug /cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project/cmake-build-debug /cygdrive/c/Users/jeffr/Documents/CSC_344/Spring_2019/C_2Project/cmake-build-debug/CMakeFiles/C_2Project.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/C_2Project.dir/depend

