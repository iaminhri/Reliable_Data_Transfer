public class UnreliableChannel {

    Packet packet;
    Receiver receiver;
    Sender sender;
    int check;
    boolean ACK;

    /**
     * Constructor
     * @param packet -> receives a packet from sender
     * @param sender -> receives a reference of a sender.
     */
    public UnreliableChannel(Packet packet, Sender sender){
        this.check = 1;
        this.packet = packet;
        this.sender = sender;
        this.ACK = false;
    }

    /**
     * Unreliable data channel sends packet to receiver.
     * @param packet -> a modified data stream is sent based on probability.
     *               Based on generated random number, the random value < 0.5
     *               will choose bitFlip data transformation else,
     *               random value >= 0.5 will choose messageDrop method.
     */
    public void udt_send_to_receiver(Packet packet){
        Packet packet_to_send;
        if(Math.random() < 0.5) {
            System.out.println("Packet magically got wrong number of bits: ");
            packet_to_send = bitFlip(packet);
            System.out.print("Corrupted packet: ");
        }
        else{
            System.out.println("Message magically got dropped: ");
            packet_to_send = dropMessage(packet);
            System.out.print("Corrupted packet: ");
        }
        this.receiver = new Receiver(packet_to_send, this);
    }

    /**
     * the following function retransmits data from sender to receiver.
     * @param packet
     */
    public void udt_send_to_receiver_Resend(Packet packet){
        this.receiver = new Receiver(packet, this);
    }

    /**
     * Sends Acknowledgments to sender which the Unreliable channel got from
     * receiver.
     * @param ACK -> If the packet is invalid, sends true to sender else false.
     */
    public void udt_Send_To_Sender(boolean ACK){
        this.ACK = ACK;
        this.sender.receiveACKFromChannel(ACK);
        this.ACK = false;
    }

    /**
     * Flips the bits of packet payload
     * @param packet -> takes a packet as input
     * @return -> returns a modified randomly flipped data inside a packet payload
     */
    public Packet bitFlip(Packet packet){
        String packetToString = packet.payload;
        int rand = (int) (Math.random() * (packetToString.length()) - 0);

        char [] temp = packetToString.toCharArray();

        if(temp[rand] == '0')
            temp[rand] = '1';
        else
            temp[rand] = '0';

        packetToString = toString(temp);
        packet.payload = packetToString;
        packet.setCorrupted(true);
        return packet;
    }

    /**
     * Drops the message of a packet entirely.
     * @param packet -> takes a packet as input
     * @return a packet by dropping the payload / message section of the packet.
     */
    public Packet dropMessage(Packet packet){
        packet.payload = "";
        packet.isCorrupted = true;
        return packet;
    }

    /**
     * Converts an character array to string.
     * @param arr -> takes an array input
     * @return a string
     */
    public String toString(char[] arr){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            str.append(arr[i]);
        }
        return "" + str;
    }
}
