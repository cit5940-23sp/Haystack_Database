# Haystack_Database

## Overview of project idea and reason for choosing this idea
We implemented Facebook’s photo storage system, as described in lecture based on the following paper: https://www.usenix.org/legacy/event/osdi10/tech/full_papers/Beaver.pdf

We found the setup and algorithm of Facebook’s photo storage system fascinating and decided to implement it for ourselves. 


## A list of features implemented 

### Haystack database photo storage system
1. Implementation of the Haystack object store using a binary file (where the actual photos are stored in the filesystem) 
2. Implementation of the Haystack index file using quadratic hashing (one to one relationship with the haystack, to determine where in the haystack is the photo stored)  
3. Implementation of the User Photo Store (where it provides a list of photos that the user has, and the key and alternate key of the photo)
4. Implementation of user actions on photos (Adding a new photo, Getting a previously added photo, Deleting a previously added photo, Updating a previously added photo, Compaction of the database)

### User friend recommendation 
1. Implementation of a graph of users (each node represent a user and each edges to represent mutual friendship)
2. Implementation of a 2D array (to be used in heuristic search of friend recommendation)
3. Implementation of friend-making and friend-searching actions (Adding a friend, Getting a list of friend recommendations) 


## 5940 topics covered in code implementation 
An explanation of we achieved meaningful and rigorous use of material from three units in this course

### Data structures 
1. LinkedList 
2. Treeset 
3. Priority Queue (with custom Comparator implementation) 
4. 2D arrays 
5. Tuple 
6. ArrayList 
7. HashSet 

Indexing 
1. Quadratic indexing 

Graph 
1. BFS traversal
2. Heuristic search 





1. Code review 
2. Milestones 
3. Use of branches 
4. Documentation
5. Style 


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
