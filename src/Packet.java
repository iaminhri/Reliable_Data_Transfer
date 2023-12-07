import java.util.zip.CRC32;

public class Packet {
    String payload;
    int sequence;
    long checksum;
    boolean isCorrupted;

    /**
     * Packet constructor. Each new packet is created in the application class.
     * @param payload -> receives a payload or message
     * @param sequence -> receives the sequence number of the message
     */
    public Packet(String payload, int sequence){
        this.payload = payload;
        this.sequence = sequence;
        this.isCorrupted = false; // by default the payload is not corrupted.
        this.checksum = calculateChecksum(); // calculates the checksum of non-corrupted payload.
    }

    // Sets the corrupted data field.
    public void setCorrupted(boolean corrupted) {
        isCorrupted = corrupted;
    }

    // display's packet output
    public void displayPacket(){
        System.out.println("Packet( data: " + payload + " Seq_Num: " + this.sequence + " corrupted: " + isCorrupted + " )");
    }

    // getter for checksum data.
    public long getChecksum(){
        return this.checksum;
    }

    // Calculates checksum of the payload of this packet.
    public long calculateChecksum(){
        CRC32 crc32 = new CRC32();
        crc32.update(payload.getBytes());
        return crc32.getValue();
    }

}
