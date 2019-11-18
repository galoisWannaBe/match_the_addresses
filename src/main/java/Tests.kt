class Tests {

    fun printMatchedAddresses(addressTables: ArrayList<TableRow>){
        println("__________________________________________________")
        println()
        println("Matched Addresses: ${addressTables.size}")
        println()
        for (obj in addressTables){
            println("Address: ${obj.address}")
            println("Matches:")
            for (matches in obj.addressArray){
                println(matches)
            }
        }
    }
    fun printUnmatchedAddresses(unmatchedAddresses: ArrayList<String>) {
        println("___________________________________________________")
        println("Unmatched Addresses: ${unmatchedAddresses.size}")
        println()
        for (obj in unmatchedAddresses){
            println(obj)
        }
    }

    fun printIndividualMultiMatches(fuckingRetardInputTracked: ArrayList<matchedAddress>){
        println("___________________________________________________")
        for (obj in fuckingRetardInputTracked){
            println("${obj.address}: ${obj.timesMatched}")
        }
        println()
    }
    fun printFilteredFromMaster(filteredOutMaster: ArrayList<String>){
        println("___________________________________________________")
        println("Filtered from master list")
        println()
        for (obj in filteredOutMaster){
            println(obj)
        }
        println()
    }
    fun printFilteredFromOtherList(filteredOut: ArrayList<String>){
        println("___________________________________________________")
        println("Filtered from special input")
        println()
        for (obj in filteredOut){
            println(obj)
        }
        println()
    }
    fun printMatchedStringsForOverMatched(fuckingRetardInputTracked: ArrayList<matchedAddress>){
        println("___________________________________________________")
        println("addresses matched more than once:")
        println()
        for (obj in fuckingRetardInputTracked){
            if (obj.timesMatched > 1){
                println("${obj.address}: ${obj.timesMatched}")
                for (indices in obj.matches){
                    println("     $indices")
                }
            }
        }
    }
}