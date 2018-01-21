package org.seeds.anrgamelogger.model;

import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio3.contentresolver.annotations.StorIOContentResolverType;

import org.seeds.anrgamelogger.database.contracts.IdentitiesContract;

/**
 * Created by user on 21/01/2018.
 */

@StorIOContentResolverType(uri = "content://" + IdentitiesContract.CONTENT_AUTHORITY + "/" + IdentitiesContract.PATH_IDENTITIES)
public class CardImage {


    @StorIOContentResolverColumn(name = IdentitiesContract.IdentitiesColumns.NRDB_CODE, key=true)
    String code;

    @StorIOContentResolverColumn(name = IdentitiesContract.IdentitiesColumns.IMAGE_BIT_ARRAY)
    byte[] imageByteArrayOutputStream;


    public CardImage(){}

    public CardImage(String code, byte[] imageByteArrayOutputStream){
        this.imageByteArrayOutputStream = imageByteArrayOutputStream;
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public byte[] getImageByteArrayOutputStream(){
        return imageByteArrayOutputStream;
    }

    public void setImageByteArrayOutputStream(byte[] imageByteArrayOutputStream){
        this.imageByteArrayOutputStream = imageByteArrayOutputStream;
    }
}
