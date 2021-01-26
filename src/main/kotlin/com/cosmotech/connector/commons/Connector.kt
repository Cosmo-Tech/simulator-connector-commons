package com.cosmotech.connector.commons

/**
 * Cosmo Solution Connector interface
 * @param CLIENT_TYPE is the type of your client (e.g. DigitalTwinsClient, FileReader, HttpClient,...)
 * @param CONSTRUCT_DATA_TYPE is the type return by the prepare method
 * @param OUTPUT_TYPE is the output type of your connector (e.g. List<String>, Map<String,Object>...)
 */
interface Connector<CLIENT_TYPE,CONSTRUCT_DATA_TYPE,OUTPUT_TYPE> {

    /**
     * Build a client to interact with the targeted data storage
     * @return the client
     */
    fun createClient(): CLIENT_TYPE

    /**
     * Prepare data or environment
     * @return an object of type CONSTRUCT_DATA_TYPE
     */
    fun prepare(client:CLIENT_TYPE): CONSTRUCT_DATA_TYPE

    /**
     * Connector mechanism
     * @return an object of type OUTPUT_TYPE
     */
    fun process() : OUTPUT_TYPE

}