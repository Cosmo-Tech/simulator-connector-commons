package com.cosmotech.connector.commons

import com.cosmotech.connector.commons.pojo.CsvData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.test.assertEquals

/**
 * Abstract Class for Unit Test
 */
abstract class CsmUnitTest {

    // Working test directory
    protected val testWorkingFolder: Path = Files.createTempDirectory("testTempFolder")

    /**
     * Get the URL of a resource from a filePath
     * the filePath is relative from resources directory
     * @param filepath the file path
     * @return the URL of the resource (can be null)
     */
    private fun getTestResource(filepath:String) : URL? {
        return this::class
            .java
            .classLoader
            .getResource(filepath)
    }

    /**
     * Get the Path representing a resource file from a filePath
     * the filePath is relative from resources directory
     * @param filePath the file path
     * @return a Path with the desired resource
     * @throws RuntimeException if the resource does not exist or cannot be found
     */
    fun getResourceFile(filePath: String): Path {
        val resourceURL = getTestResource(filePath)
            ?: throw RuntimeException("Resource $filePath cannot be found")
        return Paths.get(resourceURL.file)
    }

    /**
     * Create simple CsvData for test
     * Simple Structure :
     * fileName : Test
     * headerNamesAndType : {column1,string},{column2,datetime},{column3,int}
     * rows :
     * {"data11", "2020-09-01T00:00:01+00:00", "1"}
     * {"data21", "2020-09-02T00:00:02+00:00", "2"}
     * {"data31", "2020-09-03T00:00:03+00:00", "3"}
     * @return a CsvData containing above information
     */
    fun createSampleDataForTest(): CsvData {
        return CsvData(
            "Test",
            mutableMapOf("column1" to "string","column2" to "datetime", "column3" to "int"),
            mutableListOf(
                mutableListOf("data11", "2020-09-01T00:00:01+00:00", "1"),
                mutableListOf("data21", "2020-09-02T00:00:02+00:00", "2"),
                mutableListOf("data31", "2020-09-03T00:00:03+00:00", "3")
            ),
            testWorkingFolder.toString()
        )
    }

    @Test
    fun test_getResourceFile_RuntimeException() {
        val filePath = "Test.csv"
        val exception = assertThrows<RuntimeException> {
            this.getResourceFile(filePath)
        }
        assertEquals("Resource $filePath cannot be found",exception.message)
    }

    @Test
    fun test_getResourceFile() {
        val fileName="Test.csv"
        val filePath = "pojo/$fileName"
        val resourceFile = this.getResourceFile(filePath)
        assertEquals(fileName,resourceFile.toFile().name)
    }
}