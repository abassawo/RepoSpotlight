package com.lindenlabs.repospotlight.screens.detail

import com.lindenlabs.repospotlight.TestCoroutineRule
import com.lindenlabs.repospotlight.TestData
import com.lindenlabs.repospotlight.data.api.AppDataSource
import com.lindenlabs.repospotlight.data.models.RepoModel
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
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private val mockDataSource = mock(AppDataSource::class.java)
    val arrangeBuilder = ArrangeBuilder()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val viewModelScopeRule = TestCoroutineRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    val testDispatcher = viewModelScopeRule.testCoroutineDispatcher
    val underTest: DetailViewModel = DetailViewModel(mockDataSource)

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
    fun `expect empty contributors list to yield empty state`() {
        val repoModel = TestData.repoModel
        testDispatcher.runBlockingTest {
            arrangeBuilder.withEmptyContributors(repoModel)
        }
        underTest.init(repoModel)
        assertEquals(DetailContract.ViewState.Empty, underTest.viewState.value)
    }

    @Test
    fun `expect non-empty contributors list to yield content state`() {
        val repoModel = TestData.repoModel
        testDispatcher.runBlockingTest {
            arrangeBuilder.withNonEmptyContributors(repoModel)
        }
        underTest.init(repoModel)
        assertEquals(DetailContract.ViewState.Content(TestData.contributors), underTest.viewState.value)
    }

    @Test
    fun `handling view repo interaction yields opening custom chrome tab`() {
        val repoModel = TestData.repoModel
        testDispatcher.runBlockingTest {
            arrangeBuilder.withNonEmptyContributors(repoModel)
        }
        underTest.init(repoModel)
        underTest.handleInteraction(DetailContract.Interaction.ViewRepoClicked(repoModel))
        assertEquals(DetailContract.ViewEvent.ShowCustomChromeTab(repoModel), underTest.viewEvent.value)
    }

    inner class ArrangeBuilder {

        suspend fun withEmptyContributors(repoModel: RepoModel) = also {
            whenever(mockDataSource.getTopContributors(repoModel)).thenReturn(emptyList())
        }

        suspend fun withNonEmptyContributors(repoModel: RepoModel) = also {
            whenever(mockDataSource.getTopContributors(repoModel)).thenReturn(TestData.contributors)
        }
    }
}