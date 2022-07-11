package {{applicationId}}.di

import {{applicationId}}.data.repository.items.local.ItemsLocalSource
import {{applicationId}}.data.repository.items.local.ItemsMemorySource
import {{applicationId}}.data.repository.items.remote.{{pascalCase appName}}Api
import {{applicationId}}.data.repository.items.remote.ItemsApiSource
import {{applicationId}}.data.repository.items.remote.ItemsRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesItemsRemoteSource(appApi: {{pascalCase appName}}Api): ItemsRemoteSource {
        return ItemsApiSource(appApi)
    }

    @Provides
    @Singleton
    fun providesItemsLocalSource(): ItemsLocalSource {
        return ItemsMemorySource()
    }

}