package IO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class SimpleDecompressorInputStream extends InputStream {

    private InputStream in;

    public SimpleDecompressorInputStream(InputStream inputStream) { //constructor
        this.in = inputStream;
    } //constructor

    @Override
    public int read() throws IOException { return 0; }


    //this func read compressed array the we do in simplecompressor and turn it back to maze
    //we get empty byte array with zeros, in size of our maze, and we need to decompressed to it the compressed array
    @Override
    public int read(byte[] finalMazeAsByteArr) throws IOException {         //return super.read(b); todo need?
   /*     if(finalMazeAsByteArr == null) {throw new IOException("Argument Is Null");}

        //byte[] arrayOfFirst25 = new byte[25]; //this is the tempArray of maze. we copy it later to finalMazeAsByteArr

        //////////////
        //open a new byte array,in the size of finalMazeAsByteArr that we read to it a temporary compressed array that we want to decompress
        byte[] tempArray = new byte[finalMazeAsByteArr.length]; //hold the compressed array, and in the empty index we have zeros
        //tempArray = [24 | 4,6,55,3,5,7,255,0,77,53,195,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

        in.read(tempArray); //we read the compressed array into the tempReverseArray
        in.close();

        //this variable count the number of the zeros from the end of the tempArray until we see another number (another number=our compressed array)
        int zerosCounter = 0;
        //in this way we will know what size we need to open our compressed array

        for (int i=tempArray.length-1; i>=0;i--) //we run from the end to the start of the temp array
        { //count the number of the zeros in tempArray
            if (tempArray[i] == 0){
                zerosCounter++;
            }
            else{
                break;
            }
        }

        int SizeOfCompressedArr = finalMazeAsByteArr.length - zerosCounter; //now we have the size of our compressed array that we want to decompressed
        byte[] compressedArr = new byte[SizeOfCompressedArr]; //hold  the compressed array compressedArr=[24 | 4,6,55,3,5,7,255,0,77,53,195]

        //copy the compressed array from tempArray to the compressedArr
        //tempArray = [24 | 4,6,55,3,5,7,255,0,77,53,195,0,0,0,0,0,0] -> compressedArr=[24 | 4,6,55,3,5,7,255,0,77,53,195]
        for (int i=0 ; i<compressedArr.length ; i++)
        {
            compressedArr[i] = tempArray[i];
        }


        for (int i=0 ; i<24 ; i++) //copy the 24 first details to the finalMazeAsByteArr
        {
            finalMazeAsByteArr[i] = compressedArr[i];
        }
        //////////////

       // in.read(arrayOfFirst25); //put the compressed array in temp array
        //byte[] arrLen = new byte[4];
        //for (int i=0; i<arrLen.length; i++){
            //arrLen[i] = 0;
            //if (i==3){
                //arrLen[i] = arrayOfFirst25[24];
            //}
       // }
        //int sizeOfTempArray = convertByteToInt(arrLen);
//        int sizeOfTempArray =arrayOfFirst25[24];

        //in.read(compressedArr); //put the compressed array in temp array


        //put the 24 first index of details

        //compressedArr=[24 | 4,6,55,3,5,7,255,0,77,53,195]
        int indexCompArr = 24; //start index of the compressed array
        int indexFinalArr = 24; //start index of finalMazeAsByteArr

        //int ifZero=0; //if we decompressed zero we write 0
        int ifOneOrZero=0; //if we decompressed one we write 1 if we decompressed zero we write 0
        //int SizeOfCompressedArr = compressedArr.length-24;

        if(compressedArr[24] == 0)
        {
            indexCompArr++;
            SizeOfCompressedArr--;
            ifOneOrZero=1;
        }

        while (SizeOfCompressedArr-24>0)
        {
            if(compressedArr[indexCompArr] == 255 && compressedArr[indexCompArr+1] ==0) //if we have 255 times "one" or "zero" so we check if there is 0 after
            {//compressedArr=[24 | 4,6,55,3,5,7,255,0,77,53,195]
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
            {//compressedArr=[24 | 4,6,55,3,5,7,255,0,77,53,195]
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
        return 0;*/
        if(finalMazeAsByteArr == null) {throw new IOException("Argument Is Null");}

        byte[] tempArray = new byte[finalMazeAsByteArr.length]; //hold the compressed array, and in the empty index we have zeros
        in.read(tempArray); //we read the compressed array into the tempReverseArray
        in.close();

        //this variable count the number of the zeros from the end of the tempArray until we see another number (another number=our compressed array)
        int zerosCounter = 0;
        //in this way we will know what size we need to open our compressed array

        for (int i=tempArray.length-1; i>=0;i--) //we run from the end to the start of the temp array
        { //count the number of the zeros in tempArray
            if (tempArray[i] == 0){
                zerosCounter++;
            }
            else{
                break;
            }
        }
        int SizeOfCompressedArr = finalMazeAsByteArr.length - zerosCounter; //now we have the size of our compressed array that we want to decompressed
        byte[] compressedArr = new byte[SizeOfCompressedArr]; //hold the compressed array compressedArr=[24 | 4,6,55,3,5,7,255,0,77,53,195]
        for (int i=0 ; i<compressedArr.length ; i++)
        {
            compressedArr[i] = tempArray[i];
        }


        for (int i=0 ; i<24 ; i++) //copy the 24 first details to the finalMazeAsByteArr
        {
            finalMazeAsByteArr[i] = compressedArr[i];
        }
        int indexCompArr = 24; //start index of the compressed array
        int indexFinalArr = 24; //start index of finalMazeAsByteArr
        int ifOneOrZero=0; //if we decompressed one we write 1 if we decompressed zero we write 0

        if(compressedArr[24] == 0)
        {
            indexCompArr++;
            ifOneOrZero=1;
        }

        while (indexCompArr < compressedArr.length){
            /*if(compressedArr[indexCompArr] == 255 && compressedArr[indexCompArr+1] ==0) { //if we have 255 times "one" or "zero" so we check if there is 0 after
                int temp = compressedArr[indexCompArr] + compressedArr[indexCompArr+2]; //save the total appearance nagid 255+45=300
                for(int i=indexFinalArr ; i<indexFinalArr+temp ; i++) // we put x time each element
                {
                    finalMazeAsByteArr[i] = (byte) ifOneOrZero; //write the number of appearance of each->comp=[3] ->final=[1,1,1]
                }
                if(ifOneOrZero == 1){ifOneOrZero=0;} //turn from 1 to 0
                else{ifOneOrZero=1;} //turn 0 to 1

                indexFinalArr+=temp; //after we put x number we move on in x index in the final array
                indexCompArr+=3; //go 3 index in compressed arr
            }*/

            int temp = compressedArr[indexCompArr];
            if (compressedArr[indexCompArr] < 0)
            {
                temp = 256 + compressedArr[indexCompArr];
            }

            for(int i=indexFinalArr ; (i<indexFinalArr+temp && i<finalMazeAsByteArr.length) ; i++) // we put x time each element
            {
                finalMazeAsByteArr[i] = (byte) ifOneOrZero; //write the number of appearance of each->comp=[3] ->final=[1,1,1]
            }
            if(ifOneOrZero == 1){ifOneOrZero=0;} //turn from 1 to 0
            else{ifOneOrZero=1;} //turn 0 to 1

            indexFinalArr+=temp; //after we put x number we move on in x index in the final array
            indexCompArr++; //go 1 index in compressed arr

        }
        return 0;
    }


    private int convertByteToInt(byte[] numToConvert) //convert byte to int
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap(numToConvert);
        return byteBuffer.getInt();
    }
}
