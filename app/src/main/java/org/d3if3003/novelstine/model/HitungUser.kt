package org.d3if3003.novelstine.model

import org.d3if3003.novelstine.db.User

fun User.getNama(): HasilUser {
    return HasilUser(nama)
}