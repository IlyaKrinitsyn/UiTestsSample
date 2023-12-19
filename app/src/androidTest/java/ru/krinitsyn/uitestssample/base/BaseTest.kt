package ru.krinitsyn.uitestssample.base

import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

abstract class BaseTest(
    builder: Kaspresso.Builder = Kaspresso.Builder.withComposeSupport()
) : TestCase(builder) {

}