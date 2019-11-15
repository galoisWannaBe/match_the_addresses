class TableRow (_address: String, isActive: Boolean){

    var address = _address
    var IsActive = isActive
    var addressComparator = " "

    init {
        addressComparator = if (address.length <= 6){
            address
        }else{
            address.substring(0, 6)
        }
    }

    var addressArray = ArrayList<String>()
}