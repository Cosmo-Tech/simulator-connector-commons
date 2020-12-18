package com.cosmotech.connector.commons.pojo


import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

/**
 * Pojo class to store CSV information
 */
data class CsvData(val fileName:String,val headers:MutableList<String>, val rows:MutableList<MutableList<String>>) {

    /** Export Directory path  */
    var exportDirectory = "/mnt/simulation-data/"

    constructor(
        fileName : String,
        headers : MutableList<String>,
        rows : MutableList<MutableList<String>>,
        exportDirectory : String
    ) : this (fileName,headers,rows) {
        this.exportDirectory = if ( !exportDirectory.endsWith("/") ) {
            exportDirectory.plus("/")
        } else  {
            exportDirectory
        }
    }

    /**
     * Export Data to corresponding CSV file
     * Overwrite existing file if exists
     */
    fun exportData() {
        val targetFileName = "$exportDirectory${fileName}.csv"
        csvWriter().open(targetFileName,false) {
            writeRow(headers)
            writeAll(rows)
        }
    }

}