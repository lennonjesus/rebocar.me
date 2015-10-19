package com.github.lennonjesus.rebocar.me.acceptance

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        format = ["pretty", "html:target/cucumber", "json:target/cucumber.json"],
        glue = ["com.github.lennonjesus.rebocar.me.acceptance.steps"],
        features = "classpath:com/github/lennonjesus/rebocar/me/acceptance/features/")
class RunAcceptanceTests {

}
