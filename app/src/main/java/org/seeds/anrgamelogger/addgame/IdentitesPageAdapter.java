package org.seeds.anrgamelogger.addgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.addgame.views.ImageHolder;
import org.seeds.anrgamelogger.gamelist.GameOverviewViewHolder;
import org.seeds.anrgamelogger.model.IdentityList;

/**
 * Created by user on 09/12/2017.
 */

public class IdentitesPageAdapter extends android.support.v7.widget.RecyclerView.Adapter<ImageHolder> {

    private static final String LOG_TAG = IdentitesPageAdapter.class.getSimpleName();
    //ArrayList<byte[]> idList;
    IdentityList idList;
    Context context;
    LayoutInflater  inflater;

    public IdentitesPageAdapter(Context contextIn, IdentityList idListIn) {
        super();
        idList = idListIn;
        context = contextIn;
        inflater = LayoutInflater.from(contextIn);
    }


    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.view_addgame_identities_image,parent, false);
        ImageHolder ih = new ImageHolder(view);

        return ih;
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
//Broken here
        holder.setImage(idList.getImage(position));
    }

    @Override
    public int getItemCount() {
        return idList.getSize();
    }

    public int getPOSbyName(String name){
        return idList.getPosByName(name);
    }

}
