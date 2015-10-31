package com.github.lennonjesus.rebocar.me.entity

import com.fasterxml.jackson.core.JsonGenerator

interface SelfMarshallingEntity {
    void asJson(JsonGenerator g)
}
