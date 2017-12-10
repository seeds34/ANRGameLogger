package org.seeds.anrgamelogger.addgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.seeds.anrgamelogger.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by user on 09/12/2017.
 */

public class AddGameIdentitesPageAdapter extends PagerAdapter {

    ArrayList<byte[]> imageList;
    Context context;
    LayoutInflater  inflater;

    public AddGameIdentitesPageAdapter(Context contextIn, ArrayList<byte[]> imageListIn) {
        super();
        imageList = imageListIn;
        context = contextIn;
        inflater = LayoutInflater.from(contextIn);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.view_addgame_identities_image,container,false);
        ImageView identitiesIamgeView = (ImageView) imageLayout.findViewById(R.id.identitiesImageView);

        byte[] imageByteArray = imageList.get(position);
        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByteArray);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        identitiesIamgeView.setImageBitmap(theImage);
        container.addView(imageLayout,0);
        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
