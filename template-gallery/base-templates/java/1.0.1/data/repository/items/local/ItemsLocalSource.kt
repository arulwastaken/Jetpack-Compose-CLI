package {{applicationId}}.data.repository.items.local

import {{applicationId}}.data.model.HNItem
import {{applicationId}}.data.model.HNTopStories

interface ItemsLocalSource {

    suspend fun isValid(): Boolean

    suspend fun saveTopStories(stories: HNTopStories)

    suspend fun getTopStories(): HNTopStories

    suspend fun saveItem(item: HNItem)

    suspend fun saveItems(items: List<HNItem>)

    suspend fun getItemById(itemId: Long): HNItem?

    suspend fun getItemsById(list: List<Long>): List<HNItem?>

    suspend fun clearAll()
}