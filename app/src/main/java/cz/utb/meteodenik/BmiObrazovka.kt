package cz.utb.meteodenik

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.utb.meteodenik.ui.theme.MeteoDenikTheme

@Composable
fun BmiObrazovka(modifier: Modifier = Modifier) {
    var vyskaText by remember { mutableStateOf("") }
    var vahaText by remember { mutableStateOf("") }
    var vysledek by remember { mutableStateOf<Double?>(null) }
    var chybaVstupu by remember { mutableStateOf(false) }

    fun spocitat() {
        val vyskaCm = vyskaText.trim().replace(',', '.').toDoubleOrNull()
        val vahaKg = vahaText.trim().replace(',', '.').toDoubleOrNull()

        if (vyskaCm == null || vahaKg == null || vyskaCm <= 0.0 || vahaKg <= 0.0) {
            chybaVstupu = true
            vysledek = null
        } else {
            val vyskaM = vyskaCm / 100.0
            chybaVstupu = false
            vysledek = vahaKg / (vyskaM * vyskaM)
        }
    }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = vyskaText,
            onValueChange = { vyskaText = it },
            label = { Text(stringResource(R.string.bmi_vyska)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = vahaText,
            onValueChange = { vahaText = it },
            label = { Text(stringResource(R.string.bmi_vaha)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = { spocitat() }) {
            Text(text = stringResource(R.string.bmi_spocitat))
        }

        val hodnota = vysledek
        when {
            chybaVstupu -> Text(
                text = stringResource(R.string.bmi_chyba),
                color = MaterialTheme.colorScheme.error
            )

            hodnota != null -> Text(
                text = stringResource(R.string.bmi_vysledek, hodnota),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BmiObrazovkaPreview() {
    MeteoDenikTheme {
        BmiObrazovka()
    }
}
