import java.io.File

fun main() {
    var listedAddresses = ArrayList<String>()
    var fuckingRetardInput = ArrayList<matchedAddress>()
    var activeList = ArrayList<String>()
    var retiredList = ArrayList<String>()
    var isActive = true
    var activeAdds = ArrayList<String>()
    var retiredAdds = ArrayList<String>()
    var temp = " "
    var addressTables = ArrayList<TableRow>()
    var matched = false
    var unmatchedAddresses = ArrayList<String>()

    listedAddresses = readFilesAsLinesUsingUseLines("tables/to-table") as ArrayList<String>
    fuckingRetardInput = readFilesAsLinesUsingUseLines("tables/from-table") as ArrayList<matchedAddress>

    for(obj in listedAddresses){
        if (isActive){
            if (!obj.contains("retired")){
                addressTables.add(TableRow(obj, true))
            }else{
                isActive = false
            }
        } else {
            addressTables.add(TableRow(obj, false))
        }
    }

    for (obj in fuckingRetardInput){
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

    /*
    println("__________________________________________________")
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

     */

    for (obj in fuckingRetardInput){
        println("${obj.address}: ${obj.timesMatched}")
    }

    //Todo compare and output the answers

    /*
    activeList.sort()
    retiredList.sort()

    println("________________________________________")

    for (obj in activeList){
        if (obj.length <= 6){
            temp = obj
        }else{
            temp = obj.substring(0 , 6)
        }
        activeAdds.add(temp)
    }
    for (obj in activeAdds){
        println(obj)
    }
    println("________________________________________")
    for (obj in activeList){
        if (obj.contains("2792")){
            println(obj)
        }
    }
    println("________________________________________")
    for (obj in activeList){
        if(obj.contains("No on")){
            println(obj)
        }
    }
    println("________________________________________")
    println("Retired List")
    for (obj in retiredList){
        println(obj)
    }

    println("________________________________________")

    for (obj in activeList){
        if (obj.length <= 6){
            temp = obj
        }else{
            temp = obj.substring(0 , 6)
        }
        activeAdds.add(temp)
    }
    for (obj in activeAdds){
        println(obj)
    }
    println("________________________________________")
    for (obj in activeList){
        if (obj.contains("2792")){
            println(obj)
        }
    }
    println("________________________________________")
    for (obj in activeList){
        if(obj.contains("No on")){
            println(obj)
        }
    }

     */

}

fun readFileByLineUsingForEachLine(fileName: String)
    =File(fileName).forEachLine { println(it) }
fun readFilesAsLinesUsingUseLines(fileName: String): List<String>
    =File(fileName).useLines { it.toList() }