package cz.utb.meteodenik.cv04.model

import java.util.Locale

// ---------- data (hotové) ----------
data class Mesto(val id: Long, val nazev: String, val jePripnute: Boolean = false)

data class Mereni(
    val mestoId: Long,
    val teplotaC: Double,
    val poznamka: String? = null,   // otazník má smysl: poznámka opravdu chybět může
)

sealed interface StavDeniku {
    data object Nacitam : StavDeniku
    data object Prazdno : StavDeniku
    data class Hotovo(val mesta: List<Mesto>, val mereni: List<Mereni>) : StavDeniku
    data class Chyba(val zprava: String) : StavDeniku
}

fun Double.jakoTeplota(): String = String.format(Locale.ROOT, "%.1f °C", this)

// ---------- CP1: extension funkce ----------
fun Mesto.popisek(): String = if (jePripnute) "★ $nazev" else nazev

fun List<Mereni>.prumernaTeplota(): Double? = if (isEmpty()) null else sumOf { it.teplotaC } / size

// ---------- CP2: when nad zapečetěným typem – BEZ else ----------
fun StavDeniku.text(): String = when (this) {
    StavDeniku.Nacitam -> "Načítám..."
    StavDeniku.Prazdno -> "Deník je prázdný"
    is StavDeniku.Hotovo -> {
        val zaklad = "Měst: ${mesta.size}, měření: ${mereni.size}"
        val prumer = mereni.prumernaTeplota()
        if (prumer == null) zaklad else "$zaklad, průměr ${prumer.jakoTeplota()}"
    }
    is StavDeniku.Chyba -> "Chyba: $zprava"
}

// ---------- ⭐ Výzva: nový stav se vyrábí, nemění ----------
fun StavDeniku.pridejMesto(mesto: Mesto): StavDeniku = when (this) {
    is StavDeniku.Hotovo -> copy(mesta = mesta + mesto)
    else -> StavDeniku.Hotovo(mesta = listOf(mesto), mereni = emptyList())
}
