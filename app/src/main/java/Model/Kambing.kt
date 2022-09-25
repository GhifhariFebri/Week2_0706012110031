package Model

class Kambing(override var nama:String?, override var jenis:String?,override var umur:Int?): Hewan("","",0) {
    override var animalmakan: String = "You feed your goat grass"
    override var animalsuara: String = "Blehhh...."
}