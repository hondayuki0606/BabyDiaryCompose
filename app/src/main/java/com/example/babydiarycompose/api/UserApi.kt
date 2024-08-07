package com.example.babydiarycompose.api

import com.example.babydiarycompose.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Response
import okhttp3.RequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.example.babydiarycompose.models.User

interface UserApi {
    /**
     * Create user
     * This can only be done by the logged in user.
     * Responses:
     *  - 0: successful operation
     *
     * @param body Created user object
     * @return [Unit]
     */
    @POST("user")
    suspend fun createUser(@Body body: User): Response<Unit>

    /**
     * Creates list of users with given input array
     * 
     * Responses:
     *  - 0: successful operation
     *
     * @param body List of user object
     * @return [Unit]
     */
    @POST("user/createWithArray")
    suspend fun createUsersWithArrayInput(@Body body: kotlin.collections.List<User>): Response<Unit>

    /**
     * Creates list of users with given input array
     * 
     * Responses:
     *  - 0: successful operation
     *
     * @param body List of user object
     * @return [Unit]
     */
    @POST("user/createWithList")
    suspend fun createUsersWithListInput(@Body body: kotlin.collections.List<User>): Response<Unit>

    /**
     * Delete user
     * This can only be done by the logged in user.
     * Responses:
     *  - 400: Invalid username supplied
     *  - 404: User not found
     *
     * @param username The name that needs to be deleted
     * @return [Unit]
     */
    @DELETE("user/{username}")
    suspend fun deleteUser(@Path("username") username: kotlin.String): Response<Unit>

    /**
     * Get user by user name
     * 
     * Responses:
     *  - 200: successful operation
     *  - 400: Invalid username supplied
     *  - 404: User not found
     *
     * @param username The name that needs to be fetched. Use user1 for testing. 
     * @return [User]
     */
    @GET("user/{username}")
    suspend fun getUserByName(@Path("username") username: kotlin.String): Response<User>

    /**
     * Logs user into the system
     * 
     * Responses:
     *  - 200: successful operation
     *  - 400: Invalid username/password supplied
     *
     * @param username The user name for login
     * @param password The password for login in clear text
     * @return [kotlin.String]
     */
    @GET("user/login")
    suspend fun loginUser(@Query("username") username: kotlin.String, @Query("password") password: kotlin.String): Response<kotlin.String>

    /**
     * Logs out current logged in user session
     * 
     * Responses:
     *  - 0: successful operation
     *
     * @return [Unit]
     */
    @GET("user/logout")
    suspend fun logoutUser(): Response<Unit>

    /**
     * Updated user
     * This can only be done by the logged in user.
     * Responses:
     *  - 400: Invalid user supplied
     *  - 404: User not found
     *
     * @param username name that need to be deleted
     * @param body Updated user object
     * @return [Unit]
     */
    @PUT("user/{username}")
    suspend fun updateUser(@Path("username") username: kotlin.String, @Body body: User): Response<Unit>

}
