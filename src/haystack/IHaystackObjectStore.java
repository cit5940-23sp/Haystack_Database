package haystack;

import java.io.IOException;
import java.io.RandomAccessFile;


public interface IHaystackObjectStore {

    public static final int MAXIMUM_BYTES = 30000;

    /**
     * Write to the end of disk file
     * 
     * @param newPhoto
     * @return offset (starting of the newly appended file)
     */
    public long appendPhoto(Photo newPhoto);

    /**
     * Retrieve photo from current haystack using offset.
     * 
     * @param offset file position in haystack
     * @param the    size of the photo
     * @return the byte[] data of the photo if photo is found and accessible, return
     *         null if photo is not found or not accessible
     */
    public byte[] getPhoto(long offset, int size);

    /**
     * Delete photo by changing the flag
     * 
     * @param offset photo position in haystack
     * @return -1 if fail to delete(photo not found, invalid offset/magic number),
     *         return 0 on success
     */
    public int deletePhoto(long offset);

    /**
     * Edit photo by changing the flag
     * 
     * @param offset
     * @return
     */
    public int updatePhoto(long offset);

    /**
     * Check magic number 
     * @param rand
     * @return 0 for success; -1 for failed 
     * @throws IOException
     */
    public int checkMagicNumber(RandomAccessFile rand) throws IOException;

    /**
     * Check key 
     * @param rand
     * @return 0 for success; -1 for failed 
     * @throws IOException
     */
    public int checkKey(RandomAccessFile rand) throws IOException;

    /**
     * Reads the size of the photo 
     * @param rand
     * @return size of photo
     * @throws IOException
     */
    public int readSize(RandomAccessFile rand) throws IOException;
    
    /**
     * Compaction of databases 
     * @param newIndex
     * @return 0 for success; -1 for failed 
     */
    public int compress(IndexFile newIndex);
    

}
