package haystack;

import java.io.IOException;
import java.io.RandomAccessFile;

import photo.IPhoto;

public interface IHaystackObjectStore {

    // private int haystackID;
    // private List<String> haystackObject;
    public static final int MAXIMUM_BYTES = Integer.MAX_VALUE;

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

//    /**
//     * Set the photo as public or private 
//     */
//    void setPublicPrivate(int offset);

    public int migrate();

    public int readMetaData(RandomAccessFile rand) throws IOException;

    public int checkMagicNumber(RandomAccessFile rand) throws IOException;

    public int checkKey(RandomAccessFile rand) throws IOException;

    public boolean checkIsDeleted(RandomAccessFile rand) throws IOException;
    
    public int readSize(RandomAccessFile rand) throws IOException;
    
    public int compress(IndexFile newIndex);
    

}
