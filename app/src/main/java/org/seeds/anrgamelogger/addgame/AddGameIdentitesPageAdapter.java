package org.seeds.anrgamelogger.addgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

import org.seeds.anrgamelogger.R;
import org.seeds.anrgamelogger.model.IdentityList;

/**
 * Created by user on 09/12/2017.
 */

public class AddGameIdentitesPageAdapter extends PagerAdapter {

    private static final String LOG_TAG = AddGameIdentitesPageAdapter.class.getSimpleName();
    //ArrayList<byte[]> idList;
    IdentityList idList;
    Context context;
    LayoutInflater  inflater;

    public AddGameIdentitesPageAdapter(Context contextIn, IdentityList idListIn) {
        super();
        idList = idListIn;
        context = contextIn;
        inflater = LayoutInflater.from(contextIn);
    }

    //TODO: Deal with Null image return for DB. Inset default image
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.view_addgame_identities_image,container,false);
        ImageView identitiesIamgeView = (ImageView) imageLayout.findViewById(R.id.identitiesImageView);
        TextView identityImageRef = (TextView) imageLayout.findViewById(R.id.identitieImageReferance);

        Log.d(LOG_TAG,"Position is: " + position
                + "/n" + "Data at Position from idList: " + idList.getImage(position));

        if(idList != null) {
            byte[] imageByteArray = idList.getImage(position);
            if(imageByteArray != null) {
                ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByteArray);
                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                identitiesIamgeView.setImageBitmap(theImage);
            }
            container.addView(imageLayout, 0);
        }else{
            container.addView(imageLayout,0);
        }

        identityImageRef.setText(idList.getName(position));

        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return idList.getSize();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    public String getNameAtPOS(int pos){
        return idList.getName(pos);
    }


    public int getNameAtPOS(String name){
        return idList.getPosByName(name);
    }
}
