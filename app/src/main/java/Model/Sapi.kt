package Model

class Sapi(override var nama:String?, override var jenis:String?,override var umur:Int?): Hewan("","",0) {
    override var animalmakan: String = "You feed your cow grass"
    override var animalsuara: String = "Moo... Moo..."
}