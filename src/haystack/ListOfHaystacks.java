package haystack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.TupleForHaystack;
import photo.IPhoto;

public class ListOfHaystacks implements IListOfHaystacks{

    int remainingSpace;
    List<IndexFile> listOfHaystacks;
    int currentHaystack;
    int curKey;
    
    
    ListOfHaystacks(){
        
        listOfHaystacks = new ArrayList<IndexFile>();
        
        currentHaystack = 0;
//        
//        String file_path = "Database_0.txt";
//        
        IndexFile index = new IndexFile(0);
        
        listOfHaystacks.add(index);
        
        remainingSpace = IHaystackObjectStore.MAXIMUM_BYTES;
        
        curKey = 0;
        
    }

    @Override
    public int addPhotoToHaystack(Photo inputPhoto) {
        // TODO Auto-generated method stub
        
        inputPhoto.setKey(curKey);
        
        curKey++;
        
        inputPhoto.setAlternateKey(0);
        
        int haystackID = assignHaystack(inputPhoto);
        
        IndexFile index = listOfHaystacks.get(haystackID);
        
        
        //TEST 
        index.addPhoto(inputPhoto);
        
        return haystackID;
        
    }

    public byte[] getPhotoFromHaystack(int key, int alternateKey, int haystackID) {
        // TODO Auto-generated method stub
        
        IndexFile index = listOfHaystacks.get(haystackID);
        
        byte[] imageByte = index.getPhoto(key, alternateKey);
        
        return imageByte;
        
    }
    
    @Override
    public void deletePhotoFromHaystack(int key, int alternateKey, int haystackID) {
        
        IndexFile index = listOfHaystacks.get(haystackID);
        
        index.deletePhoto(key, alternateKey);
        
    }
    
    @Override
    public int assignHaystack(Photo inputPhoto) {
        // TODO Auto-generated method stub
        int sizeOfPhoto = inputPhoto.getSize();
        
        int sizeOfEntry = sizeOfPhoto + IPhoto.META_DATA_LENGTH;
        
        if (sizeOfEntry > remainingSpace) {
            createNewHaystack();
        }
        
        remainingSpace -= sizeOfEntry;
        
        return currentHaystack;
    }
    
    @Override
    public void createNewHaystack() {
        // TODO Auto-generated method stub
        
        currentHaystack ++;
        
        IndexFile index = new IndexFile(currentHaystack);
        
        listOfHaystacks.add(index);

        remainingSpace = IHaystackObjectStore.MAXIMUM_BYTES;
        
        
    }

    @Override
    public void compressHaystacks() {
        // TODO Auto-generated method stub
        
    }



}
