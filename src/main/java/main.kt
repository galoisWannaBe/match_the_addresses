import com.opencsv.CSVWriter
import com.opencsv.bean.ColumnPositionMappingStrategy
import java.io.File
import java.io.FileWriter
import java.io.IOException

fun main() {
    var listedAddresses = ArrayList<String>()
    var fuckingRetardInput = ArrayList<String>()
    var isActive = true
    val fuckingRetardInputTracked = ArrayList<matchedAddress>()
    val addressTables = ArrayList<TableRow>()
    var matched = false
    val unmatchedAddresses = ArrayList<String>()
    val filteredOut = ArrayList<String>()
    val filteredOutMaster = ArrayList<String>()
    var duplicates = 0
    var temp = " "
    val test = Tests()

    //imports the text from the two files
    listedAddresses = readFilesAsLinesUsingUseLines("tables/to-table") as ArrayList<String>
    fuckingRetardInput = readFilesAsLinesUsingUseLines("tables/from-table") as ArrayList<String>

    println("Addresses to be matched: ${fuckingRetardInput.size}")
    for(obj in fuckingRetardInput){

        if (checkForValidInput(obj)) {
            temp = obj
            while (temp.startsWith(" ")){
                temp = temp.substringAfter(" ")
            }
            fuckingRetardInputTracked.add(matchedAddress(temp))
        }else {
            filteredOut.add(obj)
        }
    }

    for(obj in listedAddresses){
        if (checkForValidInput(obj)) {
            temp = obj
            while (temp.startsWith(" ")){
                temp = temp.substringAfter(" ")
            }
            if (isActive) {
                if (!obj.contains("retired")) {
                    addressTables.add(TableRow(temp, true))
                } else {
                    isActive = false
                }
            } else {
                addressTables.add(TableRow(temp, false))
            }
        } else {
            filteredOutMaster.add(obj)
        }
    }

    for (obj in fuckingRetardInputTracked){
        matched = false
        for (objs in addressTables){
            if (obj.address.contains(objs.addressComparator)){
                objs.addressArray.add(obj.address)
                obj.timesMatched++
                obj.matches.add(objs.address)
                matched = true
            }
        }
        if (!matched){
            unmatchedAddresses.add(obj.address)
        }
    }
    for (obj in fuckingRetardInputTracked){
        if (obj.timesMatched > 1){
            duplicates++
        }
    }

    //the magic ended; this is the output
    //test.printMatchedAddresses(addressTables)

    //test.printUnmatchedAddresses(unmatchedAddresses)

    //test.printIndividualMultiMatches(fuckingRetardInputTracked)

    //test.printFilteredFromMaster(filteredOutMaster)

    //test.printFilteredFromOtherList(filteredOut)

    //test.printMatchedStringsForOverMatched(fuckingRetardInputTracked)

    printOutputStringList("ListedAddresses", "Listed Address", listedAddresses)
    printOutputStringList("UserInputAddresses", "User Addresses", fuckingRetardInput)
    printOutputStringList("FilteredOut", "Filtered Out", filteredOut)
    printOutputStringList("FilteredFromMaster", "Filtered From Master", filteredOutMaster)
    printOutputsTableRows("MatchesPerMasterList", "Matches per Master List", addressTables)
    printOutputsAddressMatches("MatchesPerInputs", "Matches per User Inputs", fuckingRetardInputTracked)

    println("There are $duplicates duplicates")

}

fun readFileByLineUsingForEachLine(fileName: String)
    =File(fileName).forEachLine { println(it) }
fun readFilesAsLinesUsingUseLines(fileName: String): List<String>
    =File(fileName).useLines { it.toList() }
fun checkForValidInput(input: String): Boolean{
    return (input != " " && input != "`" && input != "Street" && input.length >=2 && input != "No onsite address provided")
}
fun printOutputStringList(fileName: String, title: String, theList: ArrayList<String>){

    var structuredoutputs = File("StructuredOutputs")

    if (!structuredoutputs.exists()){
        structuredoutputs.mkdir()
    }

    var fileWriter = FileWriter(File(structuredoutputs, fileName))
    var csvWriter = CSVWriter(fileWriter)


    try {
        csvWriter.writeNext(arrayOf(title))
        for(obj in theList){
            csvWriter.writeNext(arrayOf(obj))
        }

    }catch (e: IOException){
        println(e.stackTrace)
    }

    csvWriter.close()
}
fun printOutputsTableRows(fileName: String, title: String, theList: ArrayList<TableRow>) {
    var structuredoutputs = File("StructuredOutputs")

    if (!structuredoutputs.exists()) {
        structuredoutputs.mkdir()
    }

    var fileWriter = FileWriter(File(structuredoutputs, fileName))
    var csvWriter = CSVWriter(fileWriter)

    try {
        csvWriter.writeNext(arrayOf(title))
        for (obj in theList) {
            csvWriter.writeNext(arrayOf(obj.address, "Matches:"))
            for (indices in obj.addressArray) {
                csvWriter.writeNext(arrayOf(" ", indices))
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    csvWriter.close()
}
fun printOutputsAddressMatches(fileName: String, title: String, theList: ArrayList<matchedAddress>){
    var structuredoutputs = File("StructuredOutputs")

    if (!structuredoutputs.exists()){
        structuredoutputs.mkdir()
    }

    var fileWriter = FileWriter(File(structuredoutputs, fileName))
    var csvWriter = CSVWriter(fileWriter)

    try{
        csvWriter.writeNext(arrayOf(title))
        for (obj in theList){
            csvWriter.writeNext(arrayOf(title))
            for (indices in obj.matches){
                csvWriter.writeNext(arrayOf(" ", indices))
            }
        }
    } catch (e:IOException){
        e.printStackTrace()
    }
}