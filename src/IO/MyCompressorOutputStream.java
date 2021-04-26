package IO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;

public class MyCompressorOutputStream extends OutputStream { //this class exstend the outputstream class (not ours)
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream outputStream) { //constructor
        this.out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] bytesArray) throws IOException {
        //super.write(bytesArray); //TODO check if we need the super?
        byte[] finalCompressedArray = Arrays.copyOfRange(bytesArray, 0, 24); //copy the 24 first byte to the new compressed array

        int sizeOfBytesArray = bytesArray.length - 24; //save the size of the bytesArray without the 24 first bytes
        int indexInBytesArray = 24; //save the start index to take 32 bytes for convert

        while (sizeOfBytesArray > 0) //while the original bytesArray not empty
        {
            if (sizeOfBytesArray >= 32) {
                byte[] arrayToConvert = Arrays.copyOfRange(bytesArray, indexInBytesArray, indexInBytesArray + 32); //copy only 32 bytes from original array
                finalCompressedArray =convertByteArrayToInt(arrayToConvert ,arrayToConvert.length ,finalCompressedArray); //send to this function for int conversion
                sizeOfBytesArray -= 32;
                indexInBytesArray += 32;
            }

            else
            {
                int newSizeOfBytesArray = sizeOfBytesArray%32; //minimize the original size array
                byte[] arrayToConvert = Arrays.copyOfRange(bytesArray, indexInBytesArray,indexInBytesArray+newSizeOfBytesArray); //copy the rest of bytes from original array
                finalCompressedArray =convertByteArrayToInt(arrayToConvert ,arrayToConvert.length ,finalCompressedArray); //send to this function for int conversion
                sizeOfBytesArray-=newSizeOfBytesArray;
            }

            if (out instanceof ObjectOutputStream)
                ((ObjectOutputStream) out).writeObject(finalCompressedArray);
            else
                out.write(finalCompressedArray);
            out.flush();
            out.close();

        }
    }


    private byte[] convertByteArrayToInt(byte[] arrayToConvert ,int arrayToConvertSize ,byte[] finalCompressedArray)
    {
        String arrConvertedAsString  = Arrays.toString(arrayToConvert); //make the 32 bits array to string
        String cleanArrConvertedAsString = arrConvertedAsString.replaceAll("[^0-9.]", ""); //replace all the not number to empty string

        if(arrayToConvertSize<32) //if the array that we want to convert small then 32, we put 0 to do it size 32
        {
            int additionSize = 32-arrayToConvertSize;
            cleanArrConvertedAsString = String.join("", Collections.nCopies(additionSize,"0")) + cleanArrConvertedAsString;
        }

        //converts the binary string toint
        int stringAsInt = (int)Long.parseLong(cleanArrConvertedAsString,2); //convert cleanArrConvertedAsString to int

        //converts the  int to 4 bytes
        ByteBuffer bytes4 = ByteBuffer.allocate(4);
        bytes4.putInt(stringAsInt);


        //concatenates the 4 bytes to the compressed byte array
        byte[] finalArray = new byte[finalCompressedArray.length + 4];
        System.arraycopy(finalCompressedArray, 0, finalArray, 0, finalCompressedArray.length);
        System.arraycopy(bytes4.array(), 0, finalArray, finalCompressedArray.length, 4);
        return finalArray;
    }


}


}
