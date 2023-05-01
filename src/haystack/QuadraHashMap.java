package haystack;
import java.util.Scanner;

public class QuadraHashMap {
     
    /** Class QuadraticProbingHashTable **/
    static class QuadraticHashMap
    {    
        private int currentSize, maxSize;       
        private IndexKey[] keys;   
        private IndexVal[] vals;    
     
        /** Constructor **/
        public QuadraticHashMap(int capacity) 
        {
            currentSize = 0;
            maxSize = capacity;
            keys = new IndexKey[maxSize];
            vals = new IndexVal[maxSize];
        }  
     
        /** Function to clear hash table **/
        public void makeEmpty()
        {
            currentSize = 0;
            keys = new IndexKey[maxSize];
            vals = new IndexVal[maxSize];
        }
     
        /** Function to get size of hash table **/
        public int getSize() 
        {
            return currentSize;
        }
     
        /** Function to check if hash table is full **/
        public boolean isFull() 
        {
            return currentSize == maxSize;
        }
     
        /** Function to check if hash table is empty **/
        public boolean isEmpty() 
        {
            return getSize() == 0;
        }
     
        /** Function to check if hash table contains a key **/
        public boolean contains(IndexKey key) 
        {
            return get(key) !=  null;
        }
     
        /** Function to get hash code of a given key **/
        private int hash(IndexKey key) 
        {
            return key.hashCode() % maxSize;
        }    
     
        /** Function to insert key-value pair **/
        public void insert(IndexKey key, IndexVal val) 
        {                
            int tmp = hash(key);
            int i = tmp, h = 1;
            do
            {
                if (keys[i] == null)
                {
                    keys[i] = key;
                    vals[i] = val;
                    currentSize++;
                    return;
                }
                if (keys[i].equals(key)) 
                { 
                    vals[i] = val; 
                    return; 
                }            
                i = (i + h * h++) % maxSize;            
            } while (i != tmp);       
        }
     
        /** Function to get value for a given key **/
        public IndexVal get(IndexKey key) 
        {
            int i = hash(key), h = 1;
            while (keys[i] != null)
            {
                if (keys[i].equals(key))
                    return vals[i];
                i = (i + h * h++) % maxSize;
            }            
            return null;
        }
     
    }

}
