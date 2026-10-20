# Ve třídě Mesto metody component1, component2, component3, copy, copy$default, toString, hashCode, equals
Kompilátor je vygeneroval sám, protože Mesto je v Kotlinu data class.

# Druhý konstruktor s int a DefaultConstructorMarker
Slouží pro dosazení výchozí hodnoty jePripnute = false.

# Třída ModelKt se statickou metodou popisek(Mesto)
V Javě nemůže existovat žádná samostatná funkce mimo třídu, proto kompilátor vytvořil pomocnou třídu pojmenovanou podle souboru a funkce do ní vložil jako statické metody.

# prumernaTeplota(List) vracející java.lang.Double
Funkce v Kotlinu vrací nullovatelný Double?, který v Javě vyžaduje objektový obal Double, protože primitivní double hodnotu null mít nemůže.

# StavDeniku$Nacitam s polem INSTANCE
Jde o singleton definovaný klíčovým slovem object, který svou jedinou instanci vytvoří při načtení třídy a uloží do statického pole INSTANCE.
