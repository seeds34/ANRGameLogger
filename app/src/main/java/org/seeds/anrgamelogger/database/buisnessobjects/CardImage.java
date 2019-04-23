package org.seeds.anrgamelogger.database.buisnessobjects;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by user on 21/01/2018.
 */

//TODO:Clean up entire class
    //Exsists so Images can be added to DB sepratly after IDs...
public class CardImage {

    String code;

    byte[] imageByteArray;

    String imageUrl;

    String source;

    public CardImage(){}

    public CardImage(String code){
        this.code = code;
    }

    public CardImage(String code, byte[] imageByteArrayOutputStream){
        this.imageByteArray = imageByteArrayOutputStream;
        this.code = code;
    }

    public void setSource(String sourceIn){
        source = sourceIn;
    }

    public String getSource() {
        return source;
    }

    public void setImageUrl(String urlIn){
        imageUrl = urlIn;
    }

    public String getImageUrl(){
        return imageUrl;
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
        //TODO: Work out how to check if image is what is needed
        boolean ret = true;
        return ret;
    }

    public String toString(){
        return "Code: " + code + " | Image is length is " + imageByteArray.length;
    }
}