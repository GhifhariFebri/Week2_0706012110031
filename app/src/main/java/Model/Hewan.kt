package Model

import android.os.Parcel
import android.os.Parcelable

open abstract class Hewan(open var nama:String?, open var jenis:String?, open var umur:Int? ){
    var imageUri:String = ""
    open var animalsuara:String = ""
    open var animalmakan:String = ""


}