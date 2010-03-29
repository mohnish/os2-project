/**
 * @author Mohnish Thallavajhula
 * 
 */
public class Direntry {
   String directoryName;
   String localFileName;
   int localFileSize;
   int location;

   public Direntry(String directoryName,
                   String localFileName,
                   int localFileSize,
                   int location) {
      this.directoryName = directoryName;
      this.localFileName = localFileName;
      this.localFileSize = localFileSize;
      this.location = location;
   }

   /**
    * @return the directoryName
    */
   public String getDirectoryName() {
      return directoryName;
   }

   /**
    * @param directoryName
    *           the directoryName to set
    */
   public void setDirectoryName(String directoryName) {
      this.directoryName = directoryName;
   }

   /**
    * @return the localFileName
    */
   public String getLocalFileName() {
      return localFileName;
   }

   /**
    * @param localFileName
    *           the localFileName to set
    */
   public void setLocalFileName(String localFileName) {
      this.localFileName = localFileName;
   }

   /**
    * @return the localFileSize
    */
   public int getLocalFileSize() {
      return localFileSize;
   }

   /**
    * @param localFileSize
    *           the localFileSize to set
    */
   public void setLocalFileSize(int localFileSize) {
      this.localFileSize = localFileSize;
   }

   /**
    * @return the location
    */
   public int getLocation() {
      return location;
   }

   /**
    * @param location
    *           the location to set
    */
   public void setLocation(int location) {
      this.location = location;
   }

}
