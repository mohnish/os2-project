/*
 * @author: Mohnish Thallavajhula
 * @WKU ID: 800606747
 */
public class BufferList {
   // this class is used to create the Cache for the program
   int blockNum;
   byte[] buffer = new byte[FileSystem.BLOCK_SIZE];
   boolean dirtyBit;

   public BufferList(int blockNum, byte[] buffer, boolean dirtyBit) {
      this.blockNum = blockNum;
      this.buffer = buffer;
      this.dirtyBit = dirtyBit;
   }

   /**
    * @return the blockNum
    */
   public int getBlockNum() {
      return blockNum;
   }

   /**
    * @param blockNum
    *           the blockNum to set
    */
   public void setBlockNum(int blockNum) {
      this.blockNum = blockNum;
   }

   /**
    * @return the buffer
    */
   public byte[] getBuffer() {
      return buffer;
   }

   /**
    * @param buffer
    *           the buffer to set
    */
   public void setBuffer(byte[] buffer) {
      this.buffer = buffer;
   }

   /**
    * @return the dirtyBit
    */
   public boolean isNotDirtyBit() {
      return dirtyBit;
   }

   /**
    * @param dirtyBit
    *           the dirtyBit to set
    */
   public void setDirtyBit(boolean dirtyBit) {
      this.dirtyBit = dirtyBit;
   }
}
