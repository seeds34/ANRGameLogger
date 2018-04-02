package org.seeds.anrgamelogger.model;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;

/**
 * Created by user on 21/01/2018.
 */

@StorIOContentResolverType(uri = "content://" + IdentitiesContract.CONTENT_AUTHORITY + "/" + IdentitiesContract.PATH_IDENTITIES)
public class CardImage {


    @StorIOContentResolverColumn(name = IdentitiesContract.IdentitiesColumns.NRDB_CODE, key=true)
    String code;

    @StorIOContentResolverColumn(name = IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY)
    byte[] imageByteArray;


    public CardImage(){}

    public CardImage(String code){
        this.code = code;
    }

    public CardImage(String code, byte[] imageByteArrayOutputStream){
        this.imageByteArray = imageByteArrayOutputStream;
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public byte[] getImageByteArray(){
        return imageByteArray;
    }

    public void setImageByteArray(byte[] imageByteArray){
        this.imageByteArray = imageByteArray;
    }

    public void setImageByteArray(InputStream is){
        int index;
        byte[] byteChunk = new byte[1024];

        ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream();
        if (is != null) {
            try {
                while ((index = is.read(byteChunk)) > 0) {
                    imageByteArrayOutputStream.write(byteChunk, 0, index);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageByteArray = imageByteArrayOutputStream.toByteArray();
    }

    public boolean isImageValid(){
        boolean ret = true;
        return ret;
    }
}
