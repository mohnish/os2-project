
public class Bitmap {
   int blockNum;
   boolean dataIsPresent;

   public Bitmap(int blockNum, boolean dataIsPresent) {
      this.blockNum = blockNum;
      this.dataIsPresent = dataIsPresent;
   }

   /**
    * @return the blockNum
    */
   public int getBlockNum() {
      return blockNum;
   }

   /**
    * @param blockNum the blockNum to set
    */
   public void setBlockNum(int blockNum) {
      this.blockNum = blockNum;
   }

   /**
    * @return the dataIsPresent
    */
   public boolean isDataPresent() {
      return dataIsPresent;
   }

   /**
    * @param dataIsPresent the dataIsPresent to set
    */
   public void setDataIsPresent(boolean dataIsPresent) {
      this.dataIsPresent = dataIsPresent;
   }
   
}