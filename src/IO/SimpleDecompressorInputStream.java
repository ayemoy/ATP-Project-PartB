package IO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class SimpleDecompressorInputStream extends InputStream {

    private InputStream in;

    public SimpleDecompressorInputStream(InputStream inputStream) { //constructor
        this.in = inputStream;
    } //constructor

    @Override
    public int read() throws IOException { return 0; }

    @Override
    public int read(byte[] finalMazeAsByteArr) throws IOException { //this func read compressed array and turn it back to maze
        //return super.read(b); todo need?
        if(finalMazeAsByteArr == null) {throw new IOException("Argument Is Null");}

        byte[] arrayOfFirst25 = new byte[25]; //this is the tempArray of maze. we copy it later to finalMazeAsByteArr

        in.read(arrayOfFirst25); //put the compressed array in temp array
        byte[] arrLen = new byte[4];
        for (int i=0; i<arrLen.length; i++){
            arrLen[i] = 0;
            if (i==3){
                arrLen[i] = arrayOfFirst25[24];
            }
        }
        int sizeOfTempArray = convertByteToInt(arrLen);
//        int sizeOfTempArray =arrayOfFirst25[24];

        byte[] compressedArr = new byte[sizeOfTempArray]; //save only the compressed number' without the 24 first details
        in.read(compressedArr); //put the compressed array in temp array
        in.close();

        //put the 24 first index of details
        for (int i=0 ; i<24 ; i++)
        {
            finalMazeAsByteArr[i] = arrayOfFirst25[i];
        }

        int indexCompArr = 0; //start index of the compressed array
        int indexFinalArr = 24; //start index of finalMazeAsByteArr

        //int ifZero=0; //if we decompressed zero we write 0
        int ifOneOrZero=0; //if we decompressed one we write 1 if we decompressed zero we write 0
        int SizeOfCompressedArr = compressedArr.length;

        if(compressedArr[0] == 0)
        {
            indexCompArr++;
            SizeOfCompressedArr--;
            ifOneOrZero=1;
        }

        while (SizeOfCompressedArr>0)
        {
            if (indexCompArr == 100){
                int x=5;
            }
            if(compressedArr[indexCompArr] == 255 && compressedArr[indexCompArr+1] ==0) //if we have 255 times "one" or "zero" so we check if there is 0 after
            {
                int temp = compressedArr[indexCompArr] + compressedArr[indexCompArr+2]; //save the total appearance nagid 255+45=300
                for(int i=indexFinalArr ; i<indexFinalArr+temp ; i++) // we put x time each element
                {
                    finalMazeAsByteArr[i] = (byte) ifOneOrZero; //write the number of appearance of each->comp=[3] ->final=[1,1,1]
                }
                if(ifOneOrZero == 1){ifOneOrZero=0;} //turn from 1 to 0
                else{ifOneOrZero=1;} //turn 0 to 1

                indexFinalArr+=temp; //after we put x number we move on in x index in the final array
                indexCompArr+=3; //go 1 index in compressed arr
                SizeOfCompressedArr-=2; //minimize compressed array

            }
            else //if we dont see 255 in the compressed array
            {
                for(int i=indexFinalArr ; i<indexFinalArr+compressedArr[indexCompArr] ; i++) // we put x time each element
                {
                    finalMazeAsByteArr[i] = (byte) ifOneOrZero; //write the number of appearance of each->comp=[3] ->final=[1,1,1]
                }
                if(ifOneOrZero == 1){ifOneOrZero=0;} //turn from 1 to 0
                else{ifOneOrZero=1;} //turn 0 to 1

                indexFinalArr+=compressedArr[indexCompArr]; //after we put x number we move on in x index in the final array
                indexCompArr++; //go 1 index in compressed arr
                SizeOfCompressedArr--; //minimize compressed array
            }
        }
        return 0;
    }


    private int convertByteToInt(byte[] numToConvert) //convert byte to int
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(numToConvert);
        return byteBuffer.getInt();
    }
}
