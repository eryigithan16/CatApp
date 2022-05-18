package com.example.kediuygulamasi.util
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.kediuygulamasi.model.Images
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class ImageTypeConverter {
    @TypeConverter
    fun fromImage(image: Images?) : String{
        //return image!!.url
        if (image != null) {
            return Gson().toJson(image.url)
        } else{
            return ""
        }
    }

    @TypeConverter
    fun toImage(image: String?) : Images{
        return image?.let {
            Images(it.replace("\"", ""))
        }!!
    }


}