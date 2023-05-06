package user;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import haystack.Photo;

public class UserPhotoList implements IUserPhotoList {

    private List<UserPhotoNode> masterPhotoList;
//    int deletedPhotoCount;

    public UserPhotoList() {
        masterPhotoList = new LinkedList<UserPhotoNode>();
//        deletedPhotoCount = 0;
    }

    @Override
    public int numberOfPhotos() {
        // TODO Auto-generated method stub
        return masterPhotoList.size();
    }

    @Override
    public void addPhotoToUserList(UserPhotoNode upn) {
        // TODO Auto-generated method stub
        masterPhotoList.add(upn);

    }

    @Override
    public void deletePhotoFromUserPhotoList(UserPhotoNode upn) {
        // TODO Auto-generated method stub

        masterPhotoList.remove(upn);



    }

    @Override
    public UserPhotoNode getPhoto(int key) {
        // TODO Auto-generated method stub

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
//            if (curNode.getDeleted() == 0) {
                allPhotos.add(curNode);
//            }
        }

        return allPhotos;
    }

}
