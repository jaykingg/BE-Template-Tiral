package org.example.template.domain

import org.bson.types.ObjectId
import org.example.template.view.UserView
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document
data class User(
    @MongoId
    val id: ObjectId? = null,

    val name: String?,
    val email: String?
) {
    companion object {
        inline fun user(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var name: String? = null
        var email: String? = null

        fun build() = User(
            name = name,
            email = email
        )
    }

    fun toView(): UserView {
        return UserView(
            id = this.id?.toHexString() ?: "",
            name = this.name ?: "DEFAULT_NAME",
            email = this.email ?: "DEFAULT_EMAIL"
        )
    }
}
