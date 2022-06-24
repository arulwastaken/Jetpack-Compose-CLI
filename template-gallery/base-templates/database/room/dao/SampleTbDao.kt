package {{applicationId}}.db.dao

import androidx.room.*
import {{applicationId}}.db.model.SampleTb

@Dao
interface SampleTbDao {

    @Insert
    suspend fun addSample(sample: SampleTb)

    @Query("SELECT * from sample_tb")
    fun getAllSampleTb(): List<SampleTb>

    @Delete
    suspend fun removeSampleTb(item: SampleTb)

    @Query("DELETE FROM sample_tb WHERE id = :id")
    suspend fun removeSampleTbById(id: String)

    @Query("SELECT * FROM sample_tb WHERE id = :id")
    suspend fun getSampleTbById(id: String): SampleTb?

}