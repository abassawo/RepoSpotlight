package com.lindenlabs.repospotlight.ui.components.spotlight

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.lindenlabs.repospotlight.R
import com.lindenlabs.repospotlight.data.models.RepoModel
import com.lindenlabs.repospotlight.screens.main.SpotlightRepoViewEntity
import com.lindenlabs.repospotlight.ui.theme.Dimens

@Composable
fun RepoCard(viewEntity: SpotlightRepoViewEntity, clickAction: (repo: RepoModel) -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(Dimens.defaultSpacing)
            .height(160.dp)
            .clickable { clickAction(viewEntity.repoModel) }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .padding(horizontal = Dimens.defaultSpacing)
                    .weight(1f)
            ) {
                Text(
                    text = viewEntity.repoName,
                    style = TextStyle(
                        fontSize = TextUnit(Dimens.looseSpacing.value, TextUnitType.Sp)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = Dimens.tightestSpacing)
                )
                Text(
                    text = viewEntity.description ?: "",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            if (viewEntity.topics.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.topics),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier =
                    Modifier
                        .padding(horizontal = Dimens.defaultSpacing)
                        .padding(top = 8.dp)
                )
                LazyRow(
                    modifier = Modifier
                        .padding(horizontal = Dimens.defaultSpacing)
                        .padding(bottom = Dimens.defaultSpacing)
                ) {
                    items(viewEntity.topics) { topic ->
                        val isAndroidRelated = topic.contains("Android", ignoreCase = true)
                        val textStyle = if (isAndroidRelated) {
                            LocalTextStyle.current.copy(
                                color = Color.Blue,
                                fontWeight = FontWeight.SemiBold
                            )
                        } else {
                            LocalTextStyle.current
                        }
                        Text(
                            text = topic,
                            maxLines = 1,
                            style = textStyle,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(Modifier.width(Dimens.defaultSpacing))
                    }
                }
            }
            Text(
                text = viewEntity.starGazersText, modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = Dimens.defaultSpacing)
                    .padding(bottom = Dimens.defaultSpacing)
            )
        }
    }
}
