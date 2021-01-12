package com.cosmotech.connector.commons

import com.cosmotech.connector.commons.pojo.CsvData

/**
 * Cosmo Solution Connector interface
 */
interface Connector<CLIENT_TYPE,OUTPUT_TYPE> {

    /**
     * Build a client to interact with the targeted data storage
     * @return the client
     */
    fun buildClient(): CLIENT_TYPE

    /**
     * Construct data that will be transferred to simulator
     * @return a list of CsvData containing
     */
    fun constructSimulatorData(client:CLIENT_TYPE): List<OUTPUT_TYPE>

    /**
     * Retrieve, process and export data
     */
    fun process()

}