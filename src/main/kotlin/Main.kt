import task1_TextEditor.Alignment
import task1_TextEditor.TextEditor

fun main() {
    val str = TextEditor("""
       |Ночь, улица, фонарь, аптека,
       |Бессмысленный и тусклый свет.
       |Живи еще хоть четверть века —
       |Всё будет так. Исхода нет.
       |Умрешь — начнешь опять сначала
       |И повторится всё, как встарь:
       |Ночь, ледяная рябь канала,
       |Аптека, улица, фонарь.
    """,30)
    print(str.align(Alignment.LEFT))
    println("===".repeat(10))
    println(str.align())
    println("===".repeat(10))
    println(str.align(Alignment.RIGHT))
    println("===".repeat(10))
    println(str.align(Alignment.CENTER))
}