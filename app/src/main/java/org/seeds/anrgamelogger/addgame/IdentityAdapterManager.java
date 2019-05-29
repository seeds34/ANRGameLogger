package org.seeds.anrgamelogger.addgame;

import android.content.Context;
import android.widget.ArrayAdapter;
import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.model.IdentityList;

public class IdentityAdapterManager {

    private final Context context;
    private IdentityList idList;
    private IdentitesPageAdapter identitesPageAdapter;
    private ArrayAdapter<String> identityNameArrayAdapter;

    public IdentityAdapterManager(Context context, IdentityList idList){
        this.context = context;
        this.idList = idList;

        identitesPageAdapter = new IdentitesPageAdapter(context, idList);
        identityNameArrayAdapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, idList.getListOfNames());
    }

    public void setSelectedIdentity(String identity){

    }

    public String getNameAtPOS(int pos){
        return idList.getName(pos);
    }

    public int getPOSbyName(String name){
        return idList.getPosByName(name);
    }


}
