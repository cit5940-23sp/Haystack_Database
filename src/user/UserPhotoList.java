package user;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class UserPhotoList implements IUserPhotoList {

    private List<UserPhotoNode> masterPhotoList;

    public UserPhotoList() {
        masterPhotoList = new LinkedList<UserPhotoNode>();
    }

    @Override
    public int numberOfPhotos() {
        return masterPhotoList.size();
    }

    @Override
    public void addPhotoToUserList(UserPhotoNode upn) {
        masterPhotoList.add(upn);

    }

    @Override
    public void deletePhotoFromUserPhotoList(UserPhotoNode upn) {

        for (int i = 0; i < masterPhotoList.size(); i++) {
            
            if (masterPhotoList.get(i).getKey() == upn.getKey()) {

                masterPhotoList.remove(i);  
            }
           
        }

    }

    @Override
    public UserPhotoNode getPhoto(int key) {
        for (int i = 0; i < masterPhotoList.size(); i++) {
            if (masterPhotoList.get(i).getKey() == key) {
                return masterPhotoList.get(i);
            }
        }

        return null;
    }

    @Override
    public List<UserPhotoNode> getAllPhotos() {

        List<UserPhotoNode> allPhotos = new ArrayList<UserPhotoNode>();

        for (int i = 0; i < masterPhotoList.size(); i++) {
            UserPhotoNode curNode = masterPhotoList.get(i);
            allPhotos.add(curNode);
        }

        return allPhotos;
    }

}
