package com.lindenlabs.repospotlight.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lindenlabs.repospotlight.MainViewModel
import com.lindenlabs.repospotlight.R
import com.lindenlabs.repospotlight.data.models.RepoModel
import com.lindenlabs.repospotlight.ui.components.GenericBox
import com.lindenlabs.repospotlight.ui.theme.Dimens

@Composable
fun TopReposListScreen() {
    val viewModel = hiltViewModel<MainViewModel>()
    val viewState = viewModel.viewState.collectAsState().value

    when (viewState) {
        is MainScreenContract.ViewState.ErrorState -> GenericBox(title = "Error")
        MainScreenContract.ViewState.Loading -> GenericBox(title = "Loading")
        is MainScreenContract.ViewState.Spotlight -> LazyColumn(Modifier.fillMaxSize()) {
            items(viewState.repos) { repository ->
                RepoCard(repoModel = repository)
            }
        }
    }
}

@Composable
fun RepoCard(repoModel: RepoModel) {
    val modifier = Modifier.padding(horizontal = Dimens.defaultSpacing)
    Card(
        Modifier
            .fillMaxWidth()
            .padding(Dimens.defaultSpacing)
            .height(150.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier.weight(1f)) {
                Text(
                    text = repoModel.name,
                    style = LocalTextStyle.current.copy(
                        fontSize = TextUnit(Dimens.looseSpacing.value, TextUnitType.Sp)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = repoModel.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            if (repoModel.topics.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.topics),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(top = 8.dp)
                )
                LazyRow(modifier = modifier.padding(bottom = Dimens.defaultSpacing)) {
                    items(repoModel.topics) {
                        Text(text = it, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Spacer(Modifier.width(Dimens.defaultSpacing))
                    }
                }
            }
        }
    }
}
