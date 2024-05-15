package com.lindenlabs.repospotlight.screens.main

import com.lindenlabs.repospotlight.TestCoroutineRule
import com.lindenlabs.repospotlight.TestData
import com.lindenlabs.repospotlight.data.api.AppDataSource
import com.lindenlabs.repospotlight.data.paging.SpotlightReposPagingSource
import com.lindenlabs.repospotlight.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private val mockDataSource: AppDataSource = mock(AppDataSource::class.java)
    private val pagingSource: SpotlightReposPagingSource =
        SpotlightReposPagingSource(mockDataSource)
    private val mapper = MapRepoModelToSpotlightViewEntity()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val viewModelScopeRule = TestCoroutineRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    val testDispatcher = viewModelScopeRule.testCoroutineDispatcher

    private lateinit var underTest: MainViewModel
    private val arrangeBuilder = ArrangeBuilder()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanUp() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `expect empty repositories to yield empty state`() {
        testDispatcher.runBlockingTest {
            arrangeBuilder.withEmptySpotlightRepos()
            underTest = MainViewModel(testDispatcher, pagingSource, mapper)
            assertEquals(MainScreenContract.ViewState.Empty, underTest.viewState.value)
        }
    }

    @Test
    fun `expect non-empty repositories to yield empty state`() {
        testDispatcher.runBlockingTest {
            arrangeBuilder.withNonEmptySpotlightRepos()
            val expectedEntities = mapper.invoke(listOf(TestData.repoModel))
            underTest = MainViewModel(testDispatcher, pagingSource, mapper)
            assertEquals(MainScreenContract.ViewState.Spotlight(expectedEntities), underTest.viewState.value)
        }
    }

    @Test
    fun `verify repo clicked interaction is correctly handled`() {
        underTest = MainViewModel(testDispatcher, pagingSource, mapper)
        val repo = TestData.repoModel
        underTest.handleInteraction(MainScreenContract.Interaction.SpotlightRepoClicked(repo))
        assertEquals(MainScreenContract.ViewEvent.NavigateToDetailScreen(repo), underTest.viewEvent.value)
    }

    inner class ArrangeBuilder {
        suspend fun withEmptySpotlightRepos() = also {
            whenever(mockDataSource.getPopularRepos(anyInt())).thenReturn(
                emptyList()
            )
        }

        suspend fun withNonEmptySpotlightRepos() = also {
            whenever(mockDataSource.getPopularRepos(anyInt())).thenReturn(
                listOf(
                    TestData.repoModel
                )
            )
        }
    }
}