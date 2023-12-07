public class Application {
    public static void main(String[] args) {
        // integer ascending data set.
        int []array = {1,2,3,4,5,6,7,8,9};
        String []bitsArray = new String[array.length];

        // converts the integers into binary bits, i.e: which is easier to manipulate.
        for (int i = 0; i < array.length; i++){
            bitsArray[i] = String.format("%4s", Integer.toBinaryString(array[i])).replace(' ', '0');
        }

        // Prints the initial data that is to be sent by sender via unreliable channel and received by receiver from unreliable channel.
        System.out.println("Sending Sequence of Data: ");
        for(int i = 0; i < bitsArray.length; i++){

            if(i < bitsArray.length - 1){
                System.out.print("i = " + i + ": " + bitsArray[i] + ", ");
            }
            else{
                System.out.print("i = " + i + ": " + bitsArray[i]);
            }
        }
        System.out.println();
        System.out.println();

        // Sends new packet for each data in bitsArray to sender class.
        for(int i = 0; i < bitsArray.length; i++){
            new Sender(new Packet(bitsArray[i], i));
        }
    }
}
