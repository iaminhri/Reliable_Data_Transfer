Hello,

The program is in JAVA. 

Should be easy to run with Intellij. Open the project from Intellij and select a jdk and run the application class.

You can also run it in linux terminal assuming JDK is installed in linux openjdk 17.0.9 2023-10-17.
linux command to run:
~ javac Application.java
~ java Application

Run the main function of the Application Class. Rest of the class is to acheive the simulation of Reliable data transfer using Unreliable Channel.

You will have 5 classes in total,
1. Application
2. Packet
3. Sender
4. Receiver
5. UnreliableChannel

The output is pretty much self explanatory.
For a given sequence of Data,

Sequence of Data: 
i = 0: 0001, i = 1: 0010, i = 2: 0011, i = 3: 0100, i = 4: 0101, i = 5: 0110, i = 6: 0111, i = 7: 1000, i = 8: 1001

only first packet output is shown here,

new Packet("0001", 0);

1. Packet magically got wrong number of bits: 
2. Corrupted packet: Packet( data: 1001 Seq_Num: 0 corrupted: true )
3. Sender: Resending Packet...
4. Original Packet: Packet( data: 0001 Seq_Num: 0 corrupted: false )

For each packet: the following happens,
line 1 mentions the data transformation method.
line 2 shows the corrupted packet received by receiver.
line 3 shows the Sender resending the packet
line 4 shows the original resent packet received by receiver.

you can match the sequence number with the i's value from sequence of Data field.


