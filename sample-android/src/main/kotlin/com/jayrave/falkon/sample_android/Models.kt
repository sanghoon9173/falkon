package com.jayrave.falkon.sample_android

import java.util.*

/**
 * The models NEED NOT extend any base class or have any annotations! The models are yours!
 * Both mutable & immutable models are supported, but I lean heavily towards immutability :-)
 *
 * Since Falkon only does the O & M part of a traditional ORM i.e., Falkon is just a
 * object-mapping library & it doesn't map relationships, the structure of the models
 * end up mimicking that of their respective tables
 */

data class User(
        val id: UUID,
        val firstName: String,
        val lastName: String,
        val emailId: String,
        val age: Int?,
        val address: String?,
        val photoUrl: String?,
        val createdAt: Date,
        val lastSeenAt: Date
)


data class Message(
        val id: UUID,
        val content: String,
        val createdAt: String,
        val sentAt: String,
        val receivedAt: String,
        val fromUserId: UUID,
        val toUserId: UUID,
        val groupId: UUID?
)


data class Group(
        val id: UUID,
        val name: String,
        val photoUrl: String?
)


data class GroupMember(
        val id: UUID,
        val groupId: UUID,
        val userId: UUID,
        val invitedAt: Date
)