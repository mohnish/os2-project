/*
 * @author: Mohnish Thallavajhula
 * @WKU ID: 800606747
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileSystem {

   // nblocks = number of blocks; nblocks = blockSize = size of each block
   // fileName = Seagate :-)
   // changes to any of these 3 values will automatically reflect to the entire
   // program
   public static int blockSize = 64;
   public static int nblocks = 100;
   public static char[] fileName = "Seagate".toCharArray();

   public static void main(String[] args) throws IOException {
      System.out.println("***Virtual File System***");
      // creating a disk object, which will be used later to access the read,
      // write, sync methods
      Disk disk = new Disk(FileSystem.fileName, FileSystem.nblocks);
      // the 'while' loop allows the program to repeatedly display the main menu
      // even after performing a particular action, say 'write' or 'read'
      while (true) {
         System.out.println("\nChoose one of the options:");
         // main menu
         System.out.println("1. Write\n2. Read\n3. Sync data to disk\n4. Exit");
         Scanner readInput = new Scanner(System.in);
         // menu option value is stored in 'readInputValue' variable
         int readInputValue = readInput.nextInt();
         // Write module
         if (readInputValue == 1) {
            System.out.println("Write Module | Virtual File System");
            System.out.println("Available number of blocks: " + FileSystem.nblocks);
            System.out.println("Block Size: " + FileSystem.blockSize);
            System.out.println("Enter the block number:");
            Scanner readBlockNum = new Scanner(System.in);
            // storing the entered block number to 'blockNum'
            int blockNum = readBlockNum.nextInt();
            if (blockNum >= FileSystem.nblocks) {
               // Checking if the entered value exceeds the available number of
               // blocks
               System.out.println("Entered block number exceeds the number of blocks.");
            } else {
               System.out.println("Enter the data you wish to enter:");
               BufferedReader readData = new BufferedReader(new InputStreamReader(System.in));
               try {// accepting the data entered by the user
                  // trimming any white spaces in it
                  // and then converting the string into bytes
                  byte[] buffer = readData.readLine().trim().getBytes();
                  // copyBuffer function is used to make the 'buffer' byte array
                  // size blockSize again
                  // if this is not done, the byte array being sent to
                  // 'writeBlock'
                  // will be of the size of the number of characters, entered at
                  // the CLI
                  // this will be a problem since accepting such an input will
                  // not help in
                  // overwriting the contents of the disk of a specific block
                  buffer = copyBuffer(buffer);
                  // sending the blockNum and buffer to writeBlock method
                  // available in 'disk' object
                  disk.writeBlock(blockNum, buffer);
               } catch (IOException ex) {
                  System.out.println("Unable to read input data. " + ex);
               }
            }
         } else if (readInputValue == 2) {// Read module
            System.out.println("Read Module | Virtual File System");
            System.out.println("Available number of blocks: " + FileSystem.nblocks);
            System.out.println("Block Size: " + FileSystem.blockSize);
            System.out.println("Enter the block number:");
            Scanner readBlockNum = new Scanner(System.in);
            int blockNum = readBlockNum.nextInt();
            if (blockNum >= FileSystem.nblocks) {
               System.out.println("Entered block number exceeds the number of blocks.");
            } else {
               // sending the blockNum and buffer values to the readBlock method
               byte buffer[] = new byte[FileSystem.blockSize];
               disk.readBlock(blockNum, buffer);
            }
         } else if (readInputValue == 3) {// Sync module
            System.out.println("Data from cache is being written to disk ...");
            disk.syncDisk();
            System.out.println("Sync Finished. Data Synchronization complete.");
         } else {
            // Exiting the program - instead of 4 the user can actually enter
            // any integer > 3
            System.out.println("Data from cache will be written to disk now.\n" + " Exiting Virtual File System...");
            disk.syncDisk();// performing sync in order to increase efficiency
            // of the disk
            // doing so, will make sure that the contents of the cache are
            // written to disk
            // when the hard disk is powered off
            disk.raFile.close();// closing the random access file
            System.exit(0);// exiting the program
         }
      }
   }

   public static byte[] copyBuffer(byte[] buffer) {// this method is used to
      // make the size of the input
      // data same as
      // the size of the block
      byte[] localBuffer = new byte[FileSystem.blockSize];
      for (int loop = 0; loop < buffer.length; loop++) {
         localBuffer[loop] = buffer[loop];
      }
      return localBuffer;
   }
}
