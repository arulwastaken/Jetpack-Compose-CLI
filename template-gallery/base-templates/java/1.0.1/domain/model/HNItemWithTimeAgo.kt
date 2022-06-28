package {{applicationId}}.domain.model

import {{applicationId}}.data.model.HNItem

// Preferring composition over inheritance
data class HNItemWithTimeAgo(
    val item: HNItem,
    val timeAgo: String
)