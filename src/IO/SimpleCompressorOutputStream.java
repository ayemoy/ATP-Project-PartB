package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) { this.out = out; } //constructor

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] bytesArray) throws IOException {
       // super.write(b); todo need?
        if(bytesArray == null)
        {
            throw new IOException("The Bytes Array Is Empty");
        }

        //byte[] finalCompressedArray = Arrays.copyOfRange(bytesArray, 0, 24); //copy the 24 first byte to the new compressed array

        for(int i=0 ; i<24 ; i++) //write the 24 first byte to file
        {
            //bytesArray[i];
        }

        int counter = 0; //save the number of  0  instance - how many times 0 appear
        //int counter1 = 0; //save the number of  1 instance - how many times 1 appear

        int sizeOfBytesArray = bytesArray.length - 24; //save the size of the bytesArray without the 24 first bytes
        int indexArray = 24; //save the start index to take 32 bytes for convert
        int startWithZero = 0;

        if((int)bytesArray[indexArray] != 0)
        {
            write(0);
        }

        while (sizeOfBytesArray > 0) //while the original bytesArray not empty
        {
            counter++;
            while((int)bytesArray[indexArray+1] == (int)bytesArray[indexArray])
            {
                counter++;

                if(counter>=255) //if we have 255 instances
                {
                    write(counter);
                    write(0);
                    counter=0;
                }
                indexArray++;
                sizeOfBytesArray--;
            }
            write(counter);
            counter=0;





        }

    }





    }
}
