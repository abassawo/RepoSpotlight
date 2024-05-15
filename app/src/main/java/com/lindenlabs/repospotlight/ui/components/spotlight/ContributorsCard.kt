package com.lindenlabs.repospotlight.ui.components.spotlight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import coil.compose.AsyncImage
import com.lindenlabs.repospotlight.data.models.Contributor
import com.lindenlabs.repospotlight.ui.theme.Dimens

@Composable
fun ContributorsCard(contributors: List<Contributor>) {
    Card(
        Modifier
            .wrapContentHeight()
            .padding(Dimens.defaultSpacing)
    ) {
        Spacer(modifier = Modifier.height(Dimens.defaultSpacing))
        Text(
            text = "Top Contributors",
            style = TextStyle(
                fontSize = TextUnit(
                    Dimens.looseSpacing.value,
                    TextUnitType.Sp
                )
            ),
            modifier = Modifier.padding(horizontal = Dimens.defaultSpacing)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(Dimens.defaultSpacing)
        ) {
            items(contributors) { contributor ->
                ContributorCardItem(contributor = contributor)
            }
        }
    }
}

@Composable
fun ContributorCardItem(contributor: Contributor) {
    Column {
        Row {
            AsyncImage(
                model = contributor.avatarUrl,
                contentDescription = "Avatar",
                modifier = Modifier
                    .padding(end = Dimens.tightestSpacing)
                    .size(Dimens.looseSpacing)
            )
            Text(
                text = contributor.userName,
                style = TextStyle(
                    fontSize = TextUnit(
                        Dimens.looseSpacing.value,
                        TextUnitType.Sp
                    )
                )
            )

        }
        Text(text = "Contribution Count " + contributor.contributions)
        Spacer(modifier = Modifier.height(Dimens.tightSpacing))
    }
}
