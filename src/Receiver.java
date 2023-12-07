import java.util.zip.CRC32;

public class Receiver {
    /**
     * The receiver receives some data, if there is some issue with the data
     * receiver must recognize it.
     * receiver checks for errors
     * Communicate with the sender to resend the data.
     * receiver confirms the integrity of the final completed transmission.
     */

    Packet packet;
    UnreliableChannel ACKChannel;

    public Receiver(Packet packet, UnreliableChannel ACKChannel){
        this.packet = packet;
        this.ACKChannel = ACKChannel;

        // Uses same recovery mechanism for both transformation of data.
        // Checks if the checksum of the sending packet is equals to packet payload checksum
        if(!isValidChecksum()){
            this.packet.displayPacket();
            this.ACKChannel.udt_Send_To_Sender(true); // sends a NAK if checksum is invalid.
        }
        else{
            System.out.print("Original Packet: ");
            this.packet.displayPacket(); //if the checksum is valid then prints data to console.
        }
    }

    // checks if packet payloads checksum is equals to packet's checksum.
    public boolean isValidChecksum(){
        long calculateChecksum = calculateChecksum(this.packet.payload);
        return calculateChecksum == this.packet.getChecksum();
    }

    // Calculates checksum of a packet payload.
    public long calculateChecksum(String data){
        CRC32 crc32 = new CRC32();
        crc32.update(data.getBytes());
        return crc32.getValue();
    }
}
