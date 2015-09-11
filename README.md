# Link-state-and-Distance-vector-routing-algorithms
Source code

Implemented two routing algorithms that are used in the Internet and comparing them by analyzing a sample network 
topology in Java.

Editor Used : NetBeans
JDK Version : java version "1.8.0_20"

Compilation Of the Projects.

1. Files in the projects are dependent on each other as we use the reference of one file into another.
2. For compilation use the "Makefile" file that will automatically make the classes of the java files. 
3. It is must for all the files to get compiled before running this project.

For compile and execute:-

javac Distance_vect.java Link_state.java Server_UDP
java Distance_vect
java Link_state
java Server_UDP

Syntax for input in Link-state:-
Enter the command in this format:
link_state file-name node1 node2


Syntax for input in Distance-vector:-
Enter the command in this format:
distance_vector initial_node file-name node1 node2

