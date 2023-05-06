# Haystack_Database

## Overview of project idea and reason for choosing this idea

We implemented various social networking features using a wide range of data structures (linear, hashing, trees), indexing, and graphs. 

Notably, we implemented Facebook’s photo storage system, as described in lecture based on the following paper: https://www.usenix.org/legacy/event/osdi10/tech/full_papers/Beaver.pdf. We also used graphs to represent relationships between users for friend recommendation and public/private photo settings. 

## Features implemented

### Haystack database photo storage system
1. Implementation of the Haystack object store using a binary file (Where the actual photos are stored in the filesystem) 
2. Implementation of the Haystack object store functions (Append a new photo to the end of the haystack, Delete and update photos by changing flags, Get photos, Conversion of Photo Object to bytes and bytes to Photo Object) 
3. Implementation of the Haystack index file using quadratic hashing (One to one relationship with the corresponding haystack)  
4. Implementation of the User Photo Store (Where it provides a list of photos that the user has, and the key and alternate key of each photo)
5. Implementation of user actions on photos (Adding a new photo, Getting a previously added photo, Deleting a previously added photo, Updating a previously added photo, Compaction of the database)
6. Compaction of Haystack (To remove deleted photos periodically, further increasing efficiency)

### User friend recommendation 
1. Implementation of a graph of users (Each node represent a user and each edge to represent friendship)
2. Implementation of a 2D array (To be used in heuristic search of friend recommendation)
3. Implementation of friend-making and friend-searching actions (Adding a friend, Getting a list of friend recommendations based on BFS search of friends of friends and nearest User location search using heuristic search) 
4. Implementation of public/private view of photos (Enables users to set photo to private, where only friends are allowed to see photos)


## How to run the program
1. Git clone the repository onto Eclipse 
2. Run "UserMain.java"


## 5940 topics covered in code implementation 
An explanation of how we achieved meaningful and rigorous use of material from three units in this course

### Linear data structures / Hashing / Trees 
1. LinkedList (To store the list of photos that a User has)
2. Tuple (To store the coordinates of each User on the 2D array, to store distance and User pair for friend recommendation search, to store a mapping between an indexFile and its corresponding haystack)
3. TreeMap (To map the unique key ID of users to thier corresponding User object, to map the indexing between key and which haystack it is stored in)
4. Priority Queue (To store the list of most recommended friends based on distance with custom Comparator implementation) 
5. 2D arrays (To create a map of the location of each user via latitude and longitude coordinates) 
6. ArrayList (To create the 2D array, to store list of haystacks) 
7. HashSet (To store the list of friends that a User has, and to store the Users located in a particular location in the 2D array) 

### Indexing 
1. Quadratic indexing (Hashing each key to its corresponding information in the haystack (offset, flags))
2. Linear indexing (To keep track of which keys belong to which haystack)

### Graphs 
1. Graph represented in an adjacency list
2. BFS traversal (To find friends of current friends)
3. Heuristic search (To optimize friend recommendation based on distance to user)

### Others 
1. File I/O (Read and wrote to binary file, used RandomFileAccess)
2. Magic number implementation  


## References and Acknowledgements 

### References
https://www.usenix.org/legacy/event/osdi10/tech/full_papers/Beaver.pdf

### Acknowledgements 
We would like to thank our Professor, Harry Smith, for a fun-filled semester of learning about data structures! We would also like to thank the TAs of the course for helping us through it all. 

