package {{applicationId}}.data.repository.items.remote

import {{applicationId}}.data.model.HNItem
import {{applicationId}}.data.model.HNTopStories
import {{applicationId}}.data.model.Resource

interface ItemsRemoteSource {

    suspend fun getTopStories(): Resource<HNTopStories>

    suspend fun getItemDetails(itemId: Long): Resource<HNItem>
}