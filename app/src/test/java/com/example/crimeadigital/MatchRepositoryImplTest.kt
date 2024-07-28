import com.example.crimeadigital.data.local.MatchDao
import com.example.crimeadigital.data.local.MatchEntity
import com.example.crimeadigital.data.remote.ApiService
import com.example.crimeadigital.data.remote.MatchResponse
import com.example.crimeadigital.data.repository.MatchRepositoryImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MatchRepositoryImplTest {

    private lateinit var matchDao: MatchDao
    private lateinit var apiService: ApiService
    private lateinit var matchRepository: MatchRepositoryImpl

    @Before
    fun setUp() {
        matchDao = mock(MatchDao::class.java)
        apiService = mock(ApiService::class.java)
        matchRepository = MatchRepositoryImpl(matchDao, apiService)
    }

    @Test
    fun `getMatches should return matches from local database`() = runTest {
        val matchEntities = listOf(
            MatchEntity(1, 1, "2021-09-01", "Stadium", "Team A", "Team B", "Group A", 1, 2)
        )
        `when`(matchDao.getAllMatches()).thenReturn(flow { emit(matchEntities) })

        val matches = matchRepository.getMatches().first()
        assertEquals(1, matches.size)
        assertEquals("Team A", matches[0].homeTeam)
    }

    @Test
    fun `fetchAndCacheMatches should fetch from remote and save to local database`() = runBlocking {
        val matchResponses = listOf(
            MatchResponse(1, 1, "2021-09-01", "Stadium", "Team A", "Team B", "Group A", 1, 2)
        )
        `when`(apiService.getMatches()).thenReturn(matchResponses)

        matchRepository.fetchAndCacheMatches()

        verify(matchDao, times(1)).insertAll(anyList())
    }
}
