package Model

class Ayam(override var nama:String?, override var jenis:String?,override var umur:Int?): Hewan("","", 0) {
    override var animalmakan: String = "You feed your chicken biji-bijian?"
    override var animalsuara: String = "Bok... Bok... Bok..."
}