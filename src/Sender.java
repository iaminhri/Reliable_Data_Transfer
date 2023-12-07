public class Sender {
    /**
     * Sender will be sending data
     * The data gets transformed during transit.
     * sender is able to retransmit data if it receives some indication the data wasn't received by receiver
     * to transform data during transit,
     * use these two methods, flipped bits randomly
     * messages could be dropped entirely.
     *
     * rdt_send(data)
     * packet = new packet(data)
     * udt_send(packet)
     */

    Packet packet;
    String savedPayload;
    int sequence;
    public UnreliableChannel dataChannel;
    boolean ACK = false;

    /**
     * Sender constructor, receives packets from application class.
     * @param packet -> A packet contains, payload, sequence number, checksum, and isCorrupt?
     */
    public Sender(Packet packet){
        this.packet = packet;
        this.savedPayload = packet.payload;
        this.sequence = this.packet.sequence;
        rdt_send(); // sends data to a unreliable channel.
    }

    // sends data to unreliable channel.
    public void rdt_send(){
        this.dataChannel = new UnreliableChannel(this.packet, this);
        this.dataChannel.udt_send_to_receiver(this.packet);
    }

    // Retransmits the correct data.
    public void resend_data(){
        System.out.println("Sender: Resending Packet...");
        Packet newPacket = new Packet(savedPayload, sequence);
        this.dataChannel.udt_send_to_receiver_Resend(newPacket);
    }

    /**
     * The function is called from Unreliable Channel,
     * If the function receives true, meaning the data that receiver received is
     * faulty or Receiver sends a NAK, and this function helps resend the data.
     * @param ACK -> true means NAK, false means ACK.
     */
    public void receiveACKFromChannel(boolean ACK){
        this.ACK = ACK;
        if(ACK){
            resend_data(); //Resends data.
        }
    }
}
