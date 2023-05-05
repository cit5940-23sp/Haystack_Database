# Haystack_Database

## Overview of project idea and reason for choosing this idea
We implemented Facebook’s photo storage system, as described in lecture based on the following paper: https://www.usenix.org/legacy/event/osdi10/tech/full_papers/Beaver.pdf

We found the setup and algorithm of Facebook’s photo storage system fascinating and decided to implement it for ourselves. We also used graphs to represent relationships between users for friend recommendation and public/private photo representation. 


## Features implemented

### Haystack database photo storage system
1. Implementation of the Haystack object store using a binary file (Where the actual photos are stored in the filesystem) 
2. Implementation of the Haystack index file using quadratic hashing (One to one relationship with the haystack, to determine where in the haystack is the photo stored)  
3. Implementation of the User Photo Store (Where it provides a list of photos that the user has, and the key and alternate key of the photo)
4. Implementation of user actions on photos (Adding a new photo, Getting a previously added photo, Deleting a previously added photo, Updating a previously added photo, Compaction of the database)
5. Compaction of Haystack (To remove deleted photos)

### User friend recommendation 
1. Implementation of a graph of users (each node represent a user and each edges to represent mutual friendship)
2. Implementation of a 2D array (to be used in heuristic search of friend recommendation)
3. Implementation of friend-making and friend-searching actions (Adding a friend, Getting a list of friend recommendations) 


## 5940 topics covered in code implementation 
An explanation of how we achieved meaningful and rigorous use of material from three units in this course

### Linear data structures / Hashing / Trees 
1. LinkedList (To store the list of photos that a User has)
2. Tuple (To store the coordinates of each User on the 2D array, to store distance and User pair for friend recommendation search, to store a mapping between an indexFile and its corresponding haystack)
3. TreeMap (To map the unique key ID of users to thier corresponding User object, to map the indexing between key and whiuch haystack it is stored in)
4. Priority Queue (with custom Comparator implementation) 
5. 2D arrays (To create a map of the location of each user via latitude and longitude coordinates) 
6. ArrayList (To create the 2D array, to store list of haystacks) 
7. HashSet (To store the list of friends that a User has, and to store the Users located in a particular location in the 2D array) 

### Indexing 
1. Quadratic indexing 
2. Linear indexing 

### Graphs 
1. Graph represented in an adjacency list 
2. BFS traversal
3. Heuristic search 

### Others 
1. File I/O (Read and wrote to binary file, used RandomFileAccess)
2. Magic number implementation  



### To consider 
1. Code review 
2. Milestones 
3. Use of branches 
4. Documentation
5. Style 
6. Binary search trees 


Files used by Ada





Files used by Elena 
1. Photo.java
2. IPhoto.java
3. HaystackObjectStore.java
4. IHaystackObjectStore.java


Files used by Alette
1. User.java
2. IUser.java
3. IUserPhotoList.java
4. UserPhotoList.java
5. UserPhotoNode.java
6. IUserPhotoNode.java


# Potential enhancements 
1. LRU caching in User 
2. Public/Private views 
3. Save each photo with different sizes (make alternate key and hashing with alternate key dynamic) 
4. A* search for recommended friends 
5. GUI 
