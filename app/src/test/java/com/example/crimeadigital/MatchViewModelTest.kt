import com.example.crimeadigital.domain.model.Match
import com.example.crimeadigital.domain.repository.MatchRepository
import com.example.crimeadigital.presentation.MatchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MatchViewModelTest {

    private lateinit var matchRepository: MatchRepository
    private lateinit var viewModel: MatchViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        matchRepository = mock(MatchRepository::class.java)
        viewModel = MatchViewModel(matchRepository)
    }

    @Test
    fun `fetchMatches should fetch matches and update state`() = runTest {
        val matchList = listOf(
            Match(1, 1, "2021-09-01", "Stadium", "Team A", "Team B", "Group A", 1, 2)
        )
        `when`(matchRepository.getMatches()).thenReturn(flow { emit(matchList) })

        viewModel.fetchMatches()

        advanceUntilIdle()

        assertEquals(matchList, viewModel.matches.value)
        assertFalse(viewModel.loading.value)
    }
}
