package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * this class will extend InputStream class.
 * the class will help us to read the maze byte array and decompress the maze from the input file.
 */

public class MyDecompressorInputStream extends InputStream{
    private InputStream in;

    public MyDecompressorInputStream(InputStream inputStream) { //constructor
        this.in = inputStream;
    }


    @Override
    public int read() throws IOException { return 0; }


    @Override
    public int read(byte[] finalMazeAsByteArr) throws IOException { //this func read compressed array and turn it back to maze
        if(finalMazeAsByteArr == null)
        {throw new IOException("Argument Is Null");}
        //return super.read(b); TODO check if we need super

        int sizeOfMazeArr = ((int) Math.ceil((finalMazeAsByteArr.length-24) / 32.0))*4+24; // hold the size of the decompressed array that will be the whole maze
        byte[] tempArray = new byte[sizeOfMazeArr]; //this is the tempArray of maze. we copy it later to finalMazeAsByteArr

        in.read(tempArray); //put the compressed array in temp array

        byte[] detailsArray = Arrays.copyOfRange(tempArray, 0,  24); //hold the details of the size maze

        in.close();

        //we copy the 24 cells of details to both of array
        for (int i=0; i<24; i++)
        {
            finalMazeAsByteArr[i] = tempArray[i];
            detailsArray[i] = tempArray[i]; //copy here just cuz it easy to take from it
        }

        byte[] eachIntArr = new byte[4]; //put here 4 byte in every cell so we can turn them to 32 bit = 1 int
        int totalInt; //hold the int that we get from eachIntArr
        String addZeroStr; //hold the bytes in str so we can add zeros if we have less then 32 bits
        int sizeOfinalMazeAsByteArr = finalMazeAsByteArr.length-24; //hold the size of the finalMazeAsByteArr without the details
        int index = 0;

        for (int i=24; i<tempArray.length; i=i+4)
        {
            //put in eachIntArr 4 bytes
            eachIntArr[0] = tempArray[i];
            eachIntArr[1] = tempArray[i+1];
            eachIntArr[2] = tempArray[i+2];
            eachIntArr[3] = tempArray[i+3];

            totalInt = ByteBuffer.wrap(eachIntArr).getInt(); //convert the eachArray to one int number

            //int to binary string
            addZeroStr = Integer.toBinaryString(totalInt); //convert the int to binary num as string

            //checks the length of the string
            if (sizeOfinalMazeAsByteArr >=32 ) {
                sizeOfinalMazeAsByteArr -= 32;
                if (addZeroStr.length()<32) //if the string of bits shorter then size 32
                    addZeroStr = addZerosFunc (addZeroStr, 32-addZeroStr.length()); //add zeros
            }
            else //if we have less then 32 bits == sizeOfinalMazeAsByteArr<32
            {
                if (addZeroStr.length()<sizeOfinalMazeAsByteArr) //if the string of bits shorter then size of final array
                    addZeroStr = addZerosFunc (addZeroStr, sizeOfinalMazeAsByteArr-addZeroStr.length()); //we add zeros
            }


            for (int j=0; j<addZeroStr.length(); j++) //convert every char to int and add it to the finalArray as byte
            {
                String tempString = ""+addZeroStr.charAt(j); //convert each char to string
                int tempInt = Integer.parseInt(tempString); //string (with one char) --> int
                finalMazeAsByteArr[index+24] = (byte) tempInt; //int --> byte
                index++;
            }
        }


        return 0;
    }




    private String addZerosFunc (String strToAddZeros, int amountOfzeros) //this function add string zeros to bits string that shorter then 32 bits
    {
        String zero="";
        for (int i=0; i<amountOfzeros; i++)
            zero +="0";
        strToAddZeros = zero + strToAddZeros;
        return strToAddZeros;
    }


}
