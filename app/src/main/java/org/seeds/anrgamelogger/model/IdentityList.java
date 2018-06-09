package org.seeds.anrgamelogger.model;

import java.util.ArrayList;
import java.util.List;
import org.seeds.anrgamelogger.buisnessobjects.Identity;

public class IdentityList  {

   // ArrayList<Card> cards;
    List<Identity> identities;

    public IdentityList(List<Identity> listIn){
        identities = listIn;
    }

    public IdentityList getOneSidedList(String sideIn){
        //cards.stream().filter(c -> c.getSide_code().equals(sideIn));
        ArrayList temp = new ArrayList();
        for (Identity c : identities) {
            if(c.getSide_code().equals(sideIn)){
                temp.add(c);
            }
        }
        return new IdentityList(temp);
    }

    public byte[] getImage(int i){
        return identities.get(i).getImageByteArrayOutputStream();
    }

    public String getName(int i){
        return identities.get(i).getName();
    }

    public int getSize(){return identities.size();}

    public ArrayList<String> getListOfNames(){
        ArrayList ret = new ArrayList();
        for (Identity c : identities) {
            ret.add(c.getName());
        }
        return ret;
    }

    public int getPosByName(String name){
        int ret = 0;
        for (int i = 0 ; i < identities.size() ; i++) {
            if(identities.get(i).getName().equals(name)) {
                ret = i;
            }
        }
        return ret;
    }
}