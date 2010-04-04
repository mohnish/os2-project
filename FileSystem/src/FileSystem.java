/*
 * @author: Mohnish Thallavajhula
 * @WKU ID: 800606747
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSystem {

   /*
    * NUM_OF_BLOCKS = number of blocks; NUM_OF_BLOCKS = blockSize = size of each
    * block FILE_NAME = Seagate changes to any of these 3 values will
    * automatically reflect to the entire program
    */
   public static final int BLOCK_SIZE = 64;
   public static final int NUM_OF_BLOCKS = 20;
   public static final char[] FILE_NAME = "Seagate".toCharArray();
   static ArrayList<Direntry> direntryContents = new ArrayList<Direntry>();

   public static void main(String[] args) throws IOException {
      /*
       * creating a disk object, which will be used later to access the read,
       * write, sync methods
       */

      Disk disk = new Disk(FileSystem.FILE_NAME, FileSystem.NUM_OF_BLOCKS);
      /*
       * the 'while' loop allows the program to repeatedly display the main menu
       * even after performing a particular action, say 'write' or 'read'
       */
      while (true) {
         // main menu
         System.out.println("\nDo you want to:\n1. Write\n2. Read\n3. Sync data to disk\n4. Bitmap");
         System.out.println("5. Create File\n6. Create Directory");
         Scanner readInput = new Scanner(System.in);
         // menu option value is stored in 'readInputValue' variable
         int readInputValue = readInput.nextInt();
         // Write module
         if (readInputValue == 1) {
            System.out.println("Write Module");
            System.out.println("Enter the block number:");
            Scanner readBlockNum = new Scanner(System.in);
            // storing the entered block number to 'blockNum'
            int blockNum = readBlockNum.nextInt();
            if (blockNum >= FileSystem.NUM_OF_BLOCKS) {
               /*
                * Checking if the entered value exceeds the available number of
                * blocks
                */
               System.out.println("Entered block number exceeds the number of blocks.");
            } else {
               System.out.println("Enter the data you wish to enter:");
               BufferedReader readData = new BufferedReader(new InputStreamReader(System.in));
               try {
                  byte[] buffer = null;
                  /*
                   * accepting the data entered by the user trimming any white
                   * spaces in it and then converting the string into bytes
                   * copyBuffer function is used to make the 'buffer' byte array
                   * size blockSize again if this is not done, the byte array
                   * being sent to 'writeBlock' will be of the size of the
                   * number of characters, entered at the CLI this will be a
                   * problem since accepting such an input will not help in
                   * overwriting the contents of the disk of a specific block
                   */
                  buffer = copyBuffer(readData.readLine().trim().getBytes());
                  /*
                   * sending the blockNum and buffer to writeBlock method
                   * available in 'disk' object
                   */
                  disk.writeBlock(blockNum, buffer);
               } catch (IOException ex) {
                  System.out.println("Unable to read input data: " + ex);
               }
            }
         } else if (readInputValue == 2) { // Read module
            System.out.println("Read Module");
            System.out.println("Enter the block number:");
            Scanner readBlockNum = new Scanner(System.in);
            int blockNum = readBlockNum.nextInt();
            if (blockNum >= FileSystem.NUM_OF_BLOCKS) {
               System.out.println("Entered block number exceeds the number of blocks.");
            } else {
               // sending the blockNum and buffer values to the readBlock method
               byte buffer[] = new byte[FileSystem.BLOCK_SIZE];
               disk.readBlock(blockNum, buffer);
            }
         } else if (readInputValue == 3) {// Sync module
            System.out.println("Writing data from cache to disk ...");
            disk.syncDisk();
            System.out.println("Data Synchronization finished.");
         } else if (readInputValue == 4) {
            disk.bitmap();
         } else if (readInputValue == 5) {
            createFile(disk);
         } else if (readInputValue == 6) {
            System.out.println("Enter directory name: ");
            Scanner readData = new Scanner(System.in);
            String dirName = readData.next().trim().toLowerCase();
            createDirectory(dirName);
         } else {
            /*
             * performing sync in order to increase efficiency of the disk doing
             * so, will make sure that the contents of the cache are written to
             * disk when the hard disk is powered off
             */
            System.out.print("\nAvailable data from cache will be written to disk.\n");
            disk.syncDisk();
            System.out.print("Exiting the File System.");
            disk.raFile.close(); // closing the random access file
            System.exit(0); // exiting the program
         }
      }
   }

   public static byte[] copyBuffer(byte[] buffer) {
      /*
       * this method is used to make the size of the input data same as the size
       * of the block
       */
      byte[] localBuffer = new byte[FileSystem.BLOCK_SIZE];
      for (int loop = 0; loop < buffer.length; loop++) {
         localBuffer[loop] = buffer[loop];
      }
      return localBuffer;
   }

   private static void createFile(Disk disk) throws IOException {
      // test - safe to delete the below 3 lines
      Scanner readFileName = null;
      String dirName = "";
      String fileName = "";
      boolean flag = false;
      direntryContents.clear();

      for (int loop = 5; loop <= 9; loop++) {
         direntryContents.add(new Direntry("dir" + loop, "file" + loop, loop, 5));
      }

      // displaying directory names
      for (Direntry direntry : direntryContents) {
         System.out.println("/" + direntry.getDirectoryName());
      }
      System.out.println("Choose from the above available directories: ");
      Scanner readInput = new Scanner(System.in);
      dirName = readInput.next().trim().toLowerCase();
      
      for(Direntry direntry: direntryContents){
         if (dirName.equalsIgnoreCase(direntry.getDirectoryName())) {
            // entered directory name matches with an existing one
            flag = true;
            break;
         }
      }

      if (flag) {
         // go ahead with file creation
         System.out.println("Enter the file name you wish to create:");
         readFileName = new Scanner(System.in);
         fileName = readFileName.next().trim().toLowerCase();
         // run bitmap now for knowing the block with free space
         disk.bitmap();
         byte[] buffer = new byte[FileSystem.BLOCK_SIZE];
         disk.raFile.seek(0);
         disk.raFile.read(buffer);
         /*
          * test part. we are using 999 just as a fake number to make sure that
          * a value other than 999 is set to location. this is helpful in order
          * to check if the location is set or not
          */
         int location = 999;
         int[] localBuffer = new int[FileSystem.BLOCK_SIZE];
         for (int loop = 0; loop < buffer.length; loop++) {
            localBuffer[loop] = (int) buffer[loop];
            if (localBuffer[loop] == 0) {
               // this will the block where the file will be located
               location = loop;
               break;
            }
         }
         
         if (location != 999) {
            // location is set then add
            direntryContents.add(new Direntry(dirName, fileName, 0, location));
         } else{
            // location is not set. so no space available on disk
            System.out.println("Oops. Disk ran out of space. To create a new file, delete an existing file.");
         }
      } else {
         System.out.println("No such directory name found. Check the spelling and try again.");
         createFile(disk);
      }

      for (Direntry direntry : direntryContents) {
         System.out.println(direntry.getDirectoryName() + " " + direntry.getLocalFileName() + " " + direntry.getLocalFileSize() + " " + direntry.getLocation());
      }
   }

   private static void createDirectory(String dirName) {
      // TODO
   }
}
