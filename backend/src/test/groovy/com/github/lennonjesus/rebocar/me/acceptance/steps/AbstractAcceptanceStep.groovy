package com.github.lennonjesus.rebocar.me.acceptance.steps

import com.github.lennonjesus.rebocar.me.RebocarMe
import com.github.lennonjesus.rebocar.me.testhelpers.ResponseParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = RebocarMe, loader = SpringApplicationContextLoader.class)
abstract class AbstractAcceptanceStep {

    @Autowired
    protected MongoTemplate mongoTemplate

    protected ResponseParser response
}
