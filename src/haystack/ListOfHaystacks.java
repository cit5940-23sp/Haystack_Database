package haystack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.TupleForHaystack;

public class ListOfHaystacks implements IListOfHaystacks{

    int remainingSpace;
    List<TupleForHaystack> listOfHaystacks;
    int currentHaystack;
    
    
    ListOfHaystacks(){
        
        listOfHaystacks = new ArrayList<TupleForHaystack>();
        
        currentHaystack = 0;
        
        String file_path = "Database_0.txt";
        
        HaystackObjectStore haystack = new HaystackObjectStore(file_path);
        
        IndexFile index = new IndexFile();
        
        TupleForHaystack tup = new TupleForHaystack(index, haystack);
        
        listOfHaystacks.add(tup);
        
        remainingSpace = haystack.MAXIMUM_BYTES;
        
        
    }

    @Override
    public int addPhotoToHaystack(Photo inputPhoto) {
        // TODO Auto-generated method stub
        int haystackID = assignHaystack(inputPhoto);
        
        TupleForHaystack curTup = listOfHaystacks.get(haystackID);
        
        IndexFile index = curTup.getIndex();
        
        HaystackObjectStore haystack = curTup.getHaystack();
        
        long offset = haystack.appendPhoto(inputPhoto);
        
        Map<Integer, Integer> flags = new HashMap<Integer, Integer>();
                
        IndexVal indexVal = new IndexVal(flags, offset, inputPhoto.getSize());
        
        IndexKey indexKey = new IndexKey(inputPhoto.getKey(), inputPhoto.getAlternateKey());
      
        index.addIndex(indexKey, indexVal);
        
        return haystackID;
        
    }

    
    @Override
    public int assignHaystack(Photo inputPhoto) {
        // TODO Auto-generated method stub
        int sizeOfPhoto = inputPhoto.getSize();
        
        int sizeOfEntry = sizeOfPhoto + 1000;
        
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
        
        String file_path = "Database_" + String.valueOf(currentHaystack) + ".txt";
        
        HaystackObjectStore haystack = new HaystackObjectStore(file_path);
        
        IndexFile index = new IndexFile();
        
        TupleForHaystack tup = new TupleForHaystack(index, haystack);
        
        listOfHaystacks.add(tup);

        
        remainingSpace = haystack.MAXIMUM_BYTES;
        
        
    }

    @Override
    public void compressHaystacks() {
        // TODO Auto-generated method stub
        
    }



}
