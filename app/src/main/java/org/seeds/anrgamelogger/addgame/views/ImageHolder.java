package org.seeds.anrgamelogger.addgame.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.seeds.anrgamelogger.R;

import java.io.ByteArrayInputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.identitiesImageView)
    protected ImageView identitiesImageView;

    public ImageHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setImage(byte[] image){

        ByteArrayInputStream imageStream;
        Bitmap theImage;

        if(image != null ) {
            imageStream= new ByteArrayInputStream(image);
            theImage = BitmapFactory.decodeStream(imageStream);
            identitiesImageView.setImageBitmap(theImage);
        }

    }


}
