/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.openapitools.client.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param id 
 * @param petId 
 * @param quantity 
 * @param shipDate 
 * @param status Order Status
 * @param complete 
 */


data class Order (

    @Json(name = "id")
    val id: kotlin.Long? = null,

    @Json(name = "petId")
    val petId: kotlin.Long? = null,

    @Json(name = "quantity")
    val quantity: kotlin.Int? = null,

    @Json(name = "shipDate")
    val shipDate: java.time.OffsetDateTime? = null,

    /* Order Status */
    @Json(name = "status")
    val status: Order.Status? = null,

    @Json(name = "complete")
    val complete: kotlin.Boolean? = false

) {

    /**
     * Order Status
     *
     * Values: placed,approved,delivered
     */
    @JsonClass(generateAdapter = false)
    enum class Status(val value: kotlin.String) {
        @Json(name = "placed") placed("placed"),
        @Json(name = "approved") approved("approved"),
        @Json(name = "delivered") delivered("delivered");
    }

}

