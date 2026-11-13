## Tabulka stavů

| Stav               | Co udělal uživatel                                                    | Co zobrazila aplikace                                         |
|--------------------|-----------------------------------------------------------------------|---------------------------------------------------------------|
| NEZNAMY            | Zatím nic                                                             | Tlačítko "Zjistit moji polohu"                                |
| ODMITNUTO          | Kliknul na tlačítko a v systémovém dialogu zvolil "Nepovolovat"       | Vysvětlení + tlačítko "Romumím, pokračovat"                   |
| ODMITNUTO_NATRVALO | Kliknul na tlačítko a v systémovém dialogu opět zvolil "Nepovolovat"  | Vysvětlení + tlačítko "Otevřít nastavení aplikace"            |
| UDELENO            | Povolil přístup k poloze v nastavení aplikace a vrátil se do aplikace | Souřadnice nebo hláška o tom, že se polohu nepodařilo zjistit |

---

## Proč ACCESS_COARSE_LOCATION a ne ACCESS_FINE_LOCATION?

Přesná poloha (FINE) dává podle dokumentace odhad obvykle do 50 m.
Přibližný odhad (COARSE) v řádu 3 km².

Aplikace potřebuje určit MĚSTO, na což 3 km² stačí.
Žádost o přesnou polohu by zvýšila podíl zamítnutí uživatelem a při kontrole na Google Play by ji bylo nutné zdůvodňovat.
