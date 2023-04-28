package user;

import java.util.LinkedList;
import java.util.List;

import haystack.Photo;

public class UserPhotoList implements IUserPhotoList {

    private List<UserPhotoNode> masterPhotoList;
    int deletedPhotoCount;     
     
    public UserPhotoList() {
        masterPhotoList = new LinkedList<UserPhotoNode>();
        deletedPhotoCount = 0;
    }
    
    @Override
    public int numberOfPhotos() {
        // TODO Auto-generated method stub
        return masterPhotoList.size() - deletedPhotoCount;
    }

    @Override
    public void addPhotoToUserList(UserPhotoNode upn) {
        // TODO Auto-generated method stub
        masterPhotoList.add(upn);
        
    }

    @Override
    public void deletePhotoFromUserList(UserPhotoNode upn) {
        // TODO Auto-generated method stub
        masterPhotoList.remove(upn);
    }


    public List<Photo> getPhotos() {
        // TODO Auto-generated method stub
        return null;
    }

}
