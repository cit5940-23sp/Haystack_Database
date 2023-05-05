package haystack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import graph.TupleForHaystack;
import photo.IPhoto;

public class ListOfHaystacks implements IListOfHaystacks{

    int remainingSpace;
    List<IndexFile> listOfHaystacks;
    int currentHaystack;
    int curKey;
    int curAltKey;
    TreeMap<Integer, Integer> haystackToLastKey;  
    
    
    public ListOfHaystacks(){
        
        listOfHaystacks = new ArrayList<IndexFile>();
        
        currentHaystack = 0;
    
        IndexFile index = new IndexFile(0);
        
        listOfHaystacks.add(index);
        
        remainingSpace = IHaystackObjectStore.MAXIMUM_BYTES;
        
        curKey = 0;
        
        curAltKey = 0;
        
        haystackToLastKey = new TreeMap<Integer, Integer>(); 
        
    }

    @Override
    public List<Integer> addPhotoToHaystack(Photo inputPhoto) {
        // TODO Auto-generated method stub
        
        inputPhoto.setKey(curKey);
        
        inputPhoto.setAlternateKey(curAltKey);
        
        int haystackID = assignHaystack(inputPhoto);

        haystackToLastKey.put(haystackID, curKey);
        
        IndexFile index = listOfHaystacks.get(haystackID);

        index.addPhoto(inputPhoto);
        
        List<Integer> returnVal = new ArrayList<Integer>();
        
        returnVal.add(haystackID);
        returnVal.add(curKey);
        returnVal.add(curAltKey);
        
        
        curKey++;
        
        return returnVal;
        
    }

    
    private int findHaystack(int key, int alternateKey) {
        
        
        for (Map.Entry<Integer, Integer> entry : haystackToLastKey.entrySet()) {
            
            Integer lastKey = entry.getValue();
            
            if (lastKey >= key) {
                return entry.getKey();
            }
            
        }
        
        return -1;
        
    }
    
    public byte[] getPhotoFromHaystack(int key, int alternateKey) {
        // TODO Auto-generated method stub
        
        int haystackID = findHaystack(key, alternateKey);
        
        System.out.println("HaystackID: " + haystackID);
        
        IndexFile index = listOfHaystacks.get(haystackID);
        
        byte[] imageByte = index.getPhoto(key, alternateKey);
        
        return imageByte;
        
    }
    
    @Override
    public void deletePhotoFromHaystack(int key, int alternateKey) {
        
        int haystackID = findHaystack(key, alternateKey);
        
        IndexFile index = listOfHaystacks.get(haystackID);
        
        index.deletePhoto(key, alternateKey);
        
    }
    
    @Override
    public int updatePhotoInHaystack(Photo photoToUpdate, int key, int alternateKey) {
        
        int haystackID = findHaystack(key, alternateKey);
        
        int newHaystackID = assignHaystack(photoToUpdate);
        
        if (newHaystackID == haystackID) {
            
            IndexFile index = listOfHaystacks.get(haystackID);
            
            index.updatePhotoSame(key, alternateKey, photoToUpdate);
            
        } else {
            
            IndexFile oldIndex = listOfHaystacks.get(haystackID);
            
            IndexFile newIndex = listOfHaystacks.get(newHaystackID);
            
            oldIndex.updatePhoto(key, alternateKey);
            
            newIndex.addPhoto(photoToUpdate);
        }
        

        
        return newHaystackID;
        
        
    }
    
    @Override
    public int assignHaystack(Photo inputPhoto) {
        int sizeOfPhoto = inputPhoto.getSize();
        
        int sizeOfEntry = sizeOfPhoto + IPhoto.META_DATA_LENGTH;
        
        if (sizeOfEntry > remainingSpace) {
            createNewHaystack();
        }
        
        remainingSpace -= sizeOfEntry;
        
        return currentHaystack;
    }
    
    @Override
    public IndexFile createNewHaystack() {
        
        currentHaystack ++;
        
        IndexFile index = new IndexFile(currentHaystack);
        
        listOfHaystacks.add(index);

        remainingSpace = IHaystackObjectStore.MAXIMUM_BYTES;
        
        return index;
 
    }

    @Override
    public int compressHaystacks() {
        IndexFile index = new IndexFile(curKey);
        

        curKey++;
        for(IndexFile indexFile: listOfHaystacks) {
            if(indexFile.compress(index) == -1) {
                //if indexFile return -1, that means that the pass-in newIndexFile is full, pass in new one try again
                IndexFile anotherIndexFile = createNewHaystack();
                indexFile.compress(anotherIndexFile);
            }
        }
        return 0;
        
    }



}
