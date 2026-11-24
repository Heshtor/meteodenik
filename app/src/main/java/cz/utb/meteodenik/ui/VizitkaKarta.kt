package cz.utb.meteodenik.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.utb.meteodenik.R
import cz.utb.meteodenik.ui.theme.MeteoDenikTheme

@Composable
fun VizitkaKarta(
    jmeno: String,
    obor: String,
    email: String,
    modifier: Modifier = Modifier
) {
    // stringResource() je @Composable, uvnitr Modifier.semantics { } ji zavolat nelze —
    // texty pro cteci obrazovky se proto nactou tady a do lambdy jde hotova hodnota.
    val avatarPopis = stringResource(R.string.vizitka_avatar_popis, jmeno)
    val emailPopis = stringResource(R.string.vizitka_email_popis)

    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar: clip MUSÍ být před background, jinak se vybarví celý čtverec.
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .semantics { contentDescription = avatarPopis },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = inicialy(jmeno),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    // Iniciály jsou vizuální zkratka; TalkBack čte contentDescription
                    // rodičovského Boxu, tedy celé jméno.
                    modifier = Modifier.clearAndSetSemantics { }
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // weight(1f) je klíčové: bez něj si Column vezme šířku podle nejdelšího textu
            // a vyleze z karty.
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = jmeno,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = obor,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = email,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.semantics {
                        contentDescription = "$emailPopis: $email"
                    }
                )
            }
        }
    }
}

private fun inicialy(jmeno: String): String =
    jmeno.trim()
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .map { it.first().uppercaseChar() }
        .joinToString("")

@Preview(name = "Vizitka — světlá", showBackground = true)
@Preview(name = "Vizitka — tmavá", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Vizitka — velké písmo", showBackground = true, fontScale = 1.5f)
@Composable
private fun VizitkaKartaPreview() {
    MeteoDenikTheme {
        VizitkaKarta(
            jmeno = stringResource(R.string.vizitka_ukazka_jmeno),
            obor = stringResource(R.string.vizitka_ukazka_obor),
            email = stringResource(R.string.vizitka_ukazka_email),
            modifier = Modifier.padding(16.dp)
        )
    }
}
