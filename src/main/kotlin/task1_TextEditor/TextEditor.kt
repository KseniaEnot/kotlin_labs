package task1_TextEditor

enum class Alignment {
    LEFT,
    RIGHT,
    CENTER,
}

class TextEditor(
    Text: String,
    private val PageWidth: Int = 120,
    ){
    private val spaceStr = " "
    //private val SeparatorStr = System.lineSeparator() //не работает
    private val separatorStr = "\n"
    private val punctuationMarks = setOf('.', '$', '!', ',', '-', '"','<','>')
    private var alignStr: String = Text
    private var textArr : ArrayList<String> = arrayListOf()

    init {
        removeSeparators()
        lineSplitting()
    }

    fun align(align: Alignment = Alignment.LEFT):String{
        return when (align){
            Alignment.LEFT -> leftAlign()
            Alignment.CENTER -> centreAlign()
            Alignment.RIGHT -> rightAlign()
        }
    }

    private fun rightAlign(): String{
        var stringToReturn = ""
        for (strInList in textArr){
            var str = strInList
            if(strInList[strInList.length-1] == ' ')
                str = strInList.substring(0,strInList.length-1)
            stringToReturn += (spaceStr.repeat(PageWidth-str.length)+str+separatorStr)
        }
        return stringToReturn
    }
    private fun leftAlign(): String{
        var stringToReturn = ""
        for (strInList in textArr){
            stringToReturn += (strInList+spaceStr.repeat(PageWidth-strInList.length)+separatorStr)
        }
        return stringToReturn
    }
    private fun centreAlign(): String{
        var stringToReturn = ""
        for (strInList in textArr){
            var str = strInList
            if(strInList[strInList.length-1] == ' ')
                str = strInList.substring(0,strInList.length-1)
            stringToReturn += (spaceStr.repeat((PageWidth-str.length)/2)+str) + (spaceStr.repeat(PageWidth-(PageWidth-str.length)/2-str.length)+separatorStr)
        }
        return stringToReturn
    }

    private fun removeSeparators(){
        alignStr = alignStr.trimMargin().trim()
        alignStr = alignStr.replace(separatorStr, spaceStr) //удаляем переносы строки
        while (alignStr.contains(spaceStr+spaceStr)) // пока есть двойные пробелы, заменяем их на одинарные
            alignStr = alignStr.replace(spaceStr+spaceStr, spaceStr)
        alignStr+=" "
    }
    private fun lineSplitting(){
        var stringNumb = 0
        textArr.add("")
        while (alignStr.isNotEmpty()){
            if (textArr[stringNumb].length+alignStr.indexOf(spaceStr) <= PageWidth){
                textArr[stringNumb] = textArr[stringNumb]+alignStr.substring(0,alignStr.indexOf(spaceStr))
                if (textArr[stringNumb].length != PageWidth)
                    textArr[stringNumb] = textArr[stringNumb]+" "
                else
                {
                    stringNumb++
                    textArr.add("")
                }
                alignStr=alignStr.substring(alignStr.indexOf(spaceStr)+1)
            }else{
                if (alignStr.indexOf(spaceStr) > PageWidth){ //слово вмещается в размер строки?
                    var x = PageWidth - textArr[stringNumb].length  //индекс, до которого будет производиться обрезка
                    while ((alignStr[x] in punctuationMarks)&&(x!=0)) //проверка что переносим не знак препинания
                        x--
                    if(x==0) //в оставшемся слове только знаки пунктуации
                        x = PageWidth - textArr[stringNumb].length
                    textArr[stringNumb] = textArr[stringNumb]+alignStr.substring(0,x)
                    alignStr=alignStr.substring(x,alignStr.length)
                }
                stringNumb++
                textArr.add("")
            }
        }
        if (textArr[textArr.size-1]=="") //проверка добавления лишней пустой строки
            textArr.removeAt(textArr.size-1)
    }
}
