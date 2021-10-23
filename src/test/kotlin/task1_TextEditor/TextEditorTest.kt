package task1_TextEditor
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TextEditorTest {

    @Test
    fun simple_Str() {
        val str = TextEditor(
            """
       |Ночь
    """, 6
        )
        assertEquals(str.align(Alignment.LEFT), "Ночь  \n")
    }

    @Test
    fun simple2_Str() {
        val str = TextEditor(
            """
       |Ночь
    """, 6
        )
        assertEquals(str.align(Alignment.RIGHT), "  Ночь\n")
    }

    @Test
    fun simple3_Str() {
        val str = TextEditor(
            """
       |Ночь
    """, 6
        )
        assertEquals(str.align(Alignment.CENTER), " Ночь \n")
    }

    @Test
    fun medium1_Str() {
        val str = TextEditor(
            """
       |Ночь, улица, фонарь, аптека,
       |Бессмысленный и тусклый свет.
       |Живи еще хоть четверть века —
       |Всё будет так. Исхода нет.
    """, 12
        )
        assertEquals(str.align(Alignment.LEFT), "Ночь, улица,\n" +
                "фонарь,     \n" +
                "аптека, Бесс\n" +
                "мысленный и \n" +
                "тусклый     \n" +
                "свет. Живи  \n" +
                "еще хоть    \n" +
                "четверть    \n" +
                "века — Всё  \n" +
                "будет так.  \n" +
                "Исхода нет. \n")
    }
    @Test
    fun medium2_Str() {
        val str = TextEditor(
            """
       |Ночь, улица, фонарь, аптека,
       |Бессмысленный и тусклый свет.
       |Живи еще хоть четверть века —
       |Всё будет так. Исхода нет.
    """, 12
        )
        assertEquals(str.align(Alignment.RIGHT),
            "Ночь, улица,\n" +
                    "     фонарь,\n" +
                    "аптека, Бесс\n" +
                    " мысленный и\n" +
                    "     тусклый\n" +
                    "  свет. Живи\n" +
                    "    еще хоть\n" +
                    "    четверть\n" +
                    "  века — Всё\n" +
                    "  будет так.\n" +
                    " Исхода нет.\n")
    }
    @Test
    fun medium3_Str() {
        val str = TextEditor(
            """
       |Ночь, улица, фонарь, аптека,
       |Бессмысленный и тусклый свет.
       |Живи еще хоть четверть века —
       |Всё будет так. Исхода нет.
    """, 12
        )
        assertEquals(str.align(Alignment.CENTER), "Ночь, улица,\n" +
                "  фонарь,   \n" +
                "аптека, Бесс\n" +
                "мысленный и \n" +
                "  тусклый   \n" +
                " свет. Живи \n" +
                "  еще хоть  \n" +
                "  четверть  \n" +
                " века — Всё \n" +
                " будет так. \n" +
                "Исхода нет. \n")
    }
}

