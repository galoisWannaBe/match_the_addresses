import java.io.File

fun main() {
    var listedAddresses = ArrayList<String>()
    var fuckingRetardInput = ArrayList<String>()
    var activeList = ArrayList<String>()
    var retiredList = ArrayList<String>()
    var isActive = true
    var activeAdds = ArrayList<String>()
    var retiredAdds = ArrayList<String>()
    var fuckingRetardInputTracked = ArrayList<matchedAddress>()
    var temp = " "
    var addressTables = ArrayList<TableRow>()
    var matched = false
    var unmatchedAddresses = ArrayList<String>()

    listedAddresses = readFilesAsLinesUsingUseLines("tables/to-table") as ArrayList<String>
    fuckingRetardInput = readFilesAsLinesUsingUseLines("tables/from-table") as ArrayList<String>
    for(obj in fuckingRetardInput){
        if (checkForValidInput(obj)) {
            fuckingRetardInputTracked.add(matchedAddress(obj))
        }
    }

    for(obj in listedAddresses){
        if (checkForValidInput(obj)) {
            if (isActive) {
                if (!obj.contains("retired")) {
                    addressTables.add(TableRow(obj, true))
                } else {
                    isActive = false
                }
            } else {
                addressTables.add(TableRow(obj, false))
            }
        }
    }

    for (obj in fuckingRetardInputTracked){
        matched = false
        for (objs in addressTables){
            if (obj.address.contains(objs.addressComparator)){
                objs.addressArray.add(obj.address)
                obj.timesMatched++
                matched = true
            }
        }
        if (!matched){
            unmatchedAddresses.add(obj.address)
        }
    }

    println("__________________________________________________")
    println()
    println("Matched Addresses")
    println()
    for (obj in addressTables){
        println("Address: ${obj.address}")
        println("Matches:")
        for (matches in obj.addressArray){
            println(matches)
        }
    }
    println("___________________________________________________")
    println("Unmatched Addresses")
    println()
    for (obj in unmatchedAddresses){
        println(obj)
    }
    println("___________________________________________________")

    for (obj in fuckingRetardInputTracked){
        println("${obj.address}: ${obj.timesMatched}")
    }


}

fun readFileByLineUsingForEachLine(fileName: String)
    =File(fileName).forEachLine { println(it) }
fun readFilesAsLinesUsingUseLines(fileName: String): List<String>
    =File(fileName).useLines { it.toList() }
fun checkForValidInput(input: String): Boolean{
    return (input != " " && input != "`" && input != "Street" && input.length >=2)
}