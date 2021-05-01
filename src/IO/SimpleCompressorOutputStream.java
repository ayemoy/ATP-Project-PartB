package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
        if(bytesArray == null) {throw new IOException("The Bytes Array Is Empty");}

        ArrayList<Integer> finalCompressedArray = new ArrayList<>();

        for(int i=0 ; i<24 ; i++) //write the 24 first byte to file
        {
            int temp = (int) bytesArray[i];
            finalCompressedArray.add(temp);
        }

        int counter = 0; //save the number of  0 or 1 instance - how many times 0 appear

        int sizeOfBytesArray = bytesArray.length - 24; //save the size of the bytesArray without the 24 first bytes
        int indexArray = 24; //save the start index to take 32 bytes for convert
        int lastIndexArr = bytesArray.length - 1; //save the last index of the array

        if((int)bytesArray[indexArray] != 0){finalCompressedArray.add(0);}//if the array  start with 1 we write 0 instances of 0

        while (sizeOfBytesArray > 0) //while the original bytesArray not empty
        {
            counter++; //count the first element in the array (no matter  if 0 or 1)
            if(lastIndexArr != indexArray) //this way we sure the we dont compare the last element with nothing
            {
                while((int)(bytesArray[indexArray+1]) == (int)(bytesArray[indexArray])) //check if 2 element is the same
                {
                    counter++;
                    if(counter>=255) //if we have 255 instances
                    {
                        finalCompressedArray.add(counter);
                        finalCompressedArray.add(0);//need to write zero if we have 255 of each
                        counter=0; //start count all over again
                    }
                    indexArray++; //move on in array
                    sizeOfBytesArray--; //minimize size array
                    if(indexArray == lastIndexArr)
                    {
                        counter++;
                        finalCompressedArray.add(counter);
                        sizeOfBytesArray--;
                        break;
                    }
                }
                finalCompressedArray.add(counter);
                counter=0;
                indexArray++; //move on in array
                sizeOfBytesArray--;
            }
            else{
                finalCompressedArray.add(counter);
                sizeOfBytesArray--;
            }
        }
        for(int i=0; i<finalCompressedArray.size() ; i++) //write all the compressed array
        {
            out.write(finalCompressedArray.get(i));
        }
    }
}
