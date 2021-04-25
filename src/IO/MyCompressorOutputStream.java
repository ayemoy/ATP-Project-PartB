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
    public void write(byte[] b) throws IOException {
        super.write(b);
        //copies the first 24 bytes
        byte[] compressed = Arrays.copyOfRange(bytes, 0, 24);

        int size = bytes.length-24;
        int i = 24;
        while(size>0)
        {
            if (size >= 32)
            {
                compressed = convertBatch(bytes, i,32,compressed);
                size-=32;
                i+=32;
            }
            else
            {
                int batchSize = size%32;
                compressed = convertBatch(bytes, i, size, compressed);
                size-=batchSize;
            }
        }

        if (out instanceof ObjectOutputStream)
            ((ObjectOutputStream) out).writeObject(compressed);
        else
            out.write(compressed);
        out.flush();
        out.close();
    }

}


}
