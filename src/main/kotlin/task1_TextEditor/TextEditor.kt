package task1_TextEditor

enum class Alignment {
    LEFT,
    RIGHT,
    CENTER,
}

class TextEditor(
    text: String,
    private val pageWidth: Int = 120,
) {
    private val spaceStr = " "
    private val separatorStr = "\n"
    private val punctuationMarks = setOf('.', '$', '!', ',', '-', '"', '<', '>')
    private val textArr: ArrayList<String> = arrayListOf()
    private var alignStr: String = text

    init {
        if (pageWidth < 1)
            throw IllegalArgumentException("Invalid argument")
        removeSeparators()
        lineSplitting()
    }

    fun align(align: Alignment = Alignment.LEFT): String {
        return when (align) {
            Alignment.LEFT -> leftAlign()
            Alignment.CENTER -> centreAlign()
            Alignment.RIGHT -> rightAlign()
        }
    }

    private fun rightAlign(): String {
        var stringToReturn = ""
        for (strInList in textArr) {
            var str = strInList
            if (strInList[strInList.length - 1] == ' ')
                str = strInList.substring(0, strInList.length - 1)
            stringToReturn += (spaceStr.repeat(pageWidth - str.length) + str + separatorStr)
        }
        return stringToReturn
    }

    private fun leftAlign(): String {
        var stringToReturn = ""
        for (strInList in textArr) {
            stringToReturn += (strInList + spaceStr.repeat(pageWidth - strInList.length) + separatorStr)
        }
        return stringToReturn
    }

    private fun centreAlign(): String {
        var stringToReturn = ""
        for (strInList in textArr) {
            var str = strInList
            if (strInList[strInList.length - 1] == ' ')
                str = strInList.substring(0, strInList.length - 1)
            stringToReturn += (spaceStr.repeat((pageWidth - str.length) / 2) + str) + (spaceStr.repeat(pageWidth - (pageWidth - str.length) / 2 - str.length) + separatorStr)
        }
        return stringToReturn
    }

    private fun removeSeparators() {
        alignStr = alignStr.trimMargin().trim()
        alignStr = alignStr.replace(separatorStr, spaceStr) //remove line breaks
        while (alignStr.contains(spaceStr + spaceStr)) //while there are double spaces, replace them with single ones
            alignStr = alignStr.replace(spaceStr + spaceStr, spaceStr)
        alignStr += " "
    }

    private fun lineSplitting() {
        var stringNumb = 0
        textArr.add("")
        while (alignStr.isNotEmpty()) {
            if (textArr[stringNumb].length + alignStr.indexOf(spaceStr) <= pageWidth) {
                textArr[stringNumb] = textArr[stringNumb] + alignStr.substring(0, alignStr.indexOf(spaceStr))
                if (textArr[stringNumb].length != pageWidth)
                    textArr[stringNumb] = textArr[stringNumb] + " "
                else {
                    stringNumb++
                    textArr.add("")
                }
                alignStr = alignStr.substring(alignStr.indexOf(spaceStr) + 1)
            } else {
                if (alignStr.indexOf(spaceStr) > pageWidth) { //the word fit in the size of the line?
                    var x = pageWidth - textArr[stringNumb].length  //the index to which the trim will be performed
                    while ((alignStr[x] in punctuationMarks) && (x != 0)) //checking that we transfer not a punctuation mark
                        x--
                    if (x == 0) //the rest of the word contains only punctuation marks
                        x = pageWidth - textArr[stringNumb].length
                    textArr[stringNumb] = textArr[stringNumb] + alignStr.substring(0, x)
                    alignStr = alignStr.substring(x, alignStr.length)
                }
                stringNumb++
                textArr.add("")
            }
        }
        if (textArr[textArr.size - 1] == "") //checking if an extra empty line is added
            textArr.removeAt(textArr.size - 1)
    }
}
