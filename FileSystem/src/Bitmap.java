
public class Bitmap {
   int blockNum;
   int dataIsPresent;

   public Bitmap(int blockNum, int dataIsPresent) {
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
   public int isDataPresent() {
      return dataIsPresent;
   }

   /**
    * @param dataIsPresent the dataIsPresent to set
    */
   public void setDataIsPresent(int dataIsPresent) {
      this.dataIsPresent = dataIsPresent;
   }

}