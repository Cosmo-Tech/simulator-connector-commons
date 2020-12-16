package com.cosmotech.connector.commons.pojo


import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

/**
 * Pojo class to store CSV information
 */
data class CsvData(val fileName:String,val headers:MutableList<String>, val rows:MutableList<MutableList<String>>) {

    /**
     * Export Data to corresponding CSV file
     * Overwrite existing file if exists
     */
    fun exportData(exportPath:String = "/mnt/simulation-data/") {
        val targetFileName: String = if ( exportPath.endsWith("/") ) {
            "$exportPath${fileName}.csv"
        } else {
            "$exportPath/${fileName}.csv"
        }
        csvWriter().open(targetFileName,false) {
            writeRow(headers)
            writeAll(rows)
        }
    }

}