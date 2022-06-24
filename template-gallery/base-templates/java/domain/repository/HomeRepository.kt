package {{applicationId}}.domain.repository

interface HomeRepository {
    suspend fun getAllSample(): List<String>
    suspend fun getSampleText(): String
}