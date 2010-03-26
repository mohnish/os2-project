/*
 * @author: Mohnish Thallavajhula
 * @WKU ID: 800606747
 */
public class BufferList {
   // this class is used to create the Cache for the program
   int blockNum;
   byte[] buffer = new byte[FileSystem.blockSize];
   boolean dirtyBit;

   public BufferList(int blockNum, byte[] buffer, boolean dirtyBit) {
      this.blockNum = blockNum;
      this.buffer = buffer;
      this.dirtyBit = dirtyBit;
   }
}
