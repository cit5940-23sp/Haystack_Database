package haystack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import haystack.QuadraHashMap.QuadraticHashMap;
import photo.IPhoto;

public class IndexFile implements IIndexFile {

    QuadraticHashMap hm = new QuadraticHashMap(hmMaxSize);

    // EXAMPLE
    int indexID;
    private HaystackObjectStore haystack;

    IndexFile(int indexID) {
        this.indexID = indexID;
        String file_path = "database_" + String.valueOf(indexID) + ".txt";
        haystack = new HaystackObjectStore(file_path);

    }

    public HaystackObjectStore getHaystack() {
        return haystack;
    }

    @Override
    public Map<Integer, Integer> getFlags(IndexKey k) {
        IndexVal v = hm.get(k);
        Map<Integer, Integer> flags = new HashMap<Integer, Integer>();
        flags = v.getFlags();
        return flags;
    }

    @Override
    public long getOffset(IndexKey k) {
        IndexVal v = hm.get(k);
        long offset = v.getOffset();
        return offset;
    }

    @Override
    public long getSize(IndexKey k) {
        IndexVal v = hm.get(k);
        int size = v.getSize();
        return size;
    }

    @Override
    public byte[] getPhoto(int key, int alternateKey) {

        IndexVal v = hm.get(new IndexKey(key, alternateKey));
        if (v.getFlags().get(1) == 1) {
            return null;
        }
        byte[] ans;
        ans = haystack.getPhoto(v.getOffset(), v.getSize());

        return ans;
    }

    @Override
    public void deletePhoto(int key, int alternateKey) {

        IndexVal v = hm.get(new IndexKey(key, alternateKey));
        Map<Integer, Integer> flags = v.getFlags();
        flags.put(IPhoto.DELETED, 1);
        haystack.deletePhoto(v.getOffset());
    }

    @Override
    public void updatePhoto(int key, int alternateKey) {
        IndexVal v = hm.get(new IndexKey(key, alternateKey));
        Map<Integer, Integer> flags = v.getFlags();
        flags.put(IPhoto.EDITED, 1);
    }

    @Override
    public void updatePhotoSame(int key, int alternateKey, Photo inputPhoto) {
        IndexVal v = hm.get(new IndexKey(key, alternateKey));
        Map<Integer, Integer> flags = v.getFlags();
        flags.put(IPhoto.EDITED, 1);
        long offset = haystack.appendPhoto(inputPhoto);
        v.setOffset(offset);
        v.setSize(inputPhoto.getSize());
    }

    @Override
    public void addIndex(IndexKey newIndexKey, IndexVal newIndexVal) {
        if (!hm.isFull()) {
            hm.insert(newIndexKey, newIndexVal);
        }
    }

    @Override
    public int addPhoto(Photo inputPhoto) {
        long offset = haystack.appendPhoto(inputPhoto);
        if (offset == -1) {
            return -1;
        }
        IndexVal indexVal = new IndexVal(offset, inputPhoto.getSize());
        IndexKey indexKey = new IndexKey(inputPhoto.getKey(), inputPhoto.getAlternateKey());
        addIndex(indexKey, indexVal);
        return 0;
    }

    @Override
    public int compress(IndexFile newIndexFile) {
        return this.haystack.compress(newIndexFile);
    }

}
