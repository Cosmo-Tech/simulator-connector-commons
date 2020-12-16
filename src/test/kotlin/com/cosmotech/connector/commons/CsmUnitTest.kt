package com.cosmotech.connector.commons

import java.net.URL

open class CsmUnitTest {

    fun getTestResource(filepath:String) : URL? {
        return this::class
            .java
            .classLoader
            .getResource(filepath)
    }

}