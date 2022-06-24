package {{applicationId}}.data.repository

import {{applicationId}}.utility.Constants
import javax.inject.Inject
import {{applicationId}}.domain.repository.HomeRepository

class HomeRepositoryImpl @Inject constructor(
    private val sampleText: String
): HomeRepository {

    override suspend fun getAllSample(): List<String> {
        // do api call or any other opertaion here
        return emptyList()
    }

    override suspend fun getSampleText(): String {
        // do api call or any other opertaion here
        return sampleText
    }
}