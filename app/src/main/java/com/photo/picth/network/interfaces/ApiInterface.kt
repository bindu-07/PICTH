package com.photo.picth.network.interfaces


import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

//    @POST("check-refer-code")
//    @FormUrlEncoded
//    fun checkReferCode(
//        @Field("refer_code") refer_code: String
//
//    ): Call<DataResponse<ReferCodeModel>>
//
//    @POST("check-mobile")
//    @FormUrlEncoded
//    fun checkPhoneNumber(
//        @Field("mobile") mobile: String
//
//    ): Call<DataResponse<CheckMobileModel>>
//
//    @POST("resend-otp")
//    @FormUrlEncoded
//    fun resendOtp(
//        @Field("mobile") mobile: String
//
//    ): Call<DataResponse<CheckMobileModel>>
//
//
//    @POST("check-email")
//    @FormUrlEncoded
//    fun checkEmail(
//        @Field("email") email: String
//
//    ): Call<BaseResponse>
//
//    @Multipart
//    @POST("register")
//    fun register(
//        @Part("mobile") mobile: RequestBody,
//        @Part("name") name: RequestBody,
//        @Part("email") email: RequestBody,
//        @Part("refer_user_id") refer_user_id: RequestBody,
//        @Part("country_code") countryCode: RequestBody,
//        @Part("device_type") device_type: RequestBody,
//        @Part("device_token") device_token: RequestBody,
//        @Part("user_designation_id") user_designation_id: RequestBody,
//        @Part("user_bio") user_bio: RequestBody,
//        @Part image: MultipartBody.Part?,
//    ): Call<DataResponse<LoginResponse>>
//
//
//    @POST("login")
//    @FormUrlEncoded
//    fun login(
//        @Field("mobile") mobile: String,
//        @Field("device_type") device_type: String,
//        @Field("device_token") device_token: String,
//        ): Call<DataResponse<LoginResponse>>
//
//    @GET("user-designation")
//    fun userDesignation(
//
//
//    ): Call<ListResponse<UserDesignationResponse>>
//
//
//    @POST("user-profile")
//    @FormUrlEncoded
//    fun userProfile(
//        @Field("userid") userid: String
//
//    ): Call<DataResponse<UserProfileResponse>>
//
//
//    @Multipart
//    @POST("update-profile")
//    fun updateProfile(
//        @Part("userid") userid: RequestBody,
//        @Part("name") name: RequestBody,
//        @Part("user_designation_id") user_designation_id: RequestBody,
//        @Part("user_location") user_location: RequestBody,
//        @Part("user_bio") user_bio: RequestBody,
//        @Part("user_instagram") user_instagram: RequestBody,
//        @Part("user_tiktok") user_tiktok: RequestBody,
//        @Part("user_twitter") user_twitter: RequestBody,
//        @Part("user_linkedin") user_linkedin: RequestBody,
//        @Part("is_onboarding") is_onboarding: RequestBody,
//        @Part image: MultipartBody.Part?
//    ): Call<DataResponse<UserProfileResponse>>
//
//
//    @Multipart
//    @POST("update-profile")
//    fun inviteUpdateProfile(
//        @Part("userid") userid: RequestBody,
//        @Part("name") name: RequestBody,
//        @Part("user_designation_id") user_designation_id: RequestBody,
//        @Part("user_bio") user_bio: RequestBody,
//        @Part image: MultipartBody.Part?
//    ): Call<DataResponse<UserProfileResponse>>
//
//
//    @Multipart
//    @POST("update-profile")
//    fun onBoardingApi(
//        @Part("userid") userid: RequestBody,
//        @Part("user_instagram") user_instagram: RequestBody,
//        @Part("user_tiktok") user_tiktok: RequestBody,
//        @Part("user_twitter") user_twitter: RequestBody,
//        @Part("user_linkedin") user_linkedin: RequestBody,
//        @Part("is_onboarding") is_onboarding: RequestBody,
//        @Part("user_bio") user_bio: RequestBody
//
//
//    ): Call<DataResponse<UserProfileResponse>>
//
//
//    @POST("logout")
//    fun logout(): Call<BaseResponse>
//
//    @POST("deleteAccount")
//    @FormUrlEncoded
//    fun deleteAccount(
//        @Field("userid")userid:String
//    ): Call<BaseResponse>
//
//    @GET("get-currency")
//    fun currency(): Call<ListResponse<CurrencyModel>>
//
//    @POST("update-currency")
//    @FormUrlEncoded
//    fun updateCurrency(
//        @Field("userid") userid: String,
//        @Field("currency_id") currency_id: String,
//    ): Call<DataResponse<UpdateCurrencyResponse>>
//
//    @GET("get-callduration")
//    fun callDurtaion(): Call<ListResponse<CallDurantionModel>>
//
//    @POST("mentor-create-event")
//    @FormUrlEncoded
//    fun createEvent(
//        @Field("userid") userid: Int,
//        @Field("event_name") event_name: String,
//        @Field("event_duration") event_duration: Int,
//        @Field("event_currency") event_currency: Int,
//        @Field("event_amount") event_amount: Int,
//
//        ): Call<DataResponse<EventBean>>
//
//    @POST("mentor-create-event")
//    @FormUrlEncoded
//    fun getEvent(
//        @Field("userid") userid: String
//    ): Call<DataResponse<EventBean>>
//
//    @POST("mentor-send-notification")
//    @FormUrlEncoded
//    fun sendNotification(@Field("userid") userid: String): Call<DataResponse<TokenModel>>
//
//
//    @POST("cancel-call")
//    @FormUrlEncoded
//    fun declineCall(
//        @Field("userid") userid: String,
//        @Field("id") id: String,
//        @Field("to_send") to_send: String,
//    ): Call<BaseResponse>
//
//    @POST("remove-video")
//    @FormUrlEncoded
//    fun removeVideo(
//        @Field("userid") userid: String
//    ): Call<BaseResponse>
//
//
//    @POST("mentor-upcoming-calls")
//    @FormUrlEncoded
//    fun upcomingCalls(
//        @Field("userid") userid: String, @Field("date") date: String,
//    ): Call<ListResponse<UpcomingCallResponse>>
//
//    @POST("mentor-recent-calls")
//    @FormUrlEncoded
//    fun recentCalls(
//        @Field("userid") userid: String
//    ): Call<DataResponse<RecentCallDataResponse>>
//
//
//    @POST("mentor-call-waiting")
//    @FormUrlEncoded
//    fun callWaiting(
//        @Field("userid") userid: String
//    ): Call<DataResponse<MentorCallWaitingResponse>>
//
//    @POST("call-end")
//    @FormUrlEncoded
//    fun callEnd(
//        @Field("callid") callid: String, @Field("call_duration") call_duration: String
//    ): Call<BaseResponse>
//
//
//    @GET("get-time-zones")
//
//    fun getTimeZone(
//
//    ): Call<ListResponse<TimeZoneData>>
//
//
//    @POST("mentor-call-waiting-room")
//    @FormUrlEncoded
//    fun waitingAfterCall(
//        @Field("callid") callid: String, @Field("superadmin") superAdmin: String
//    ): Call<DataResponse<WaitingAfterCallResponse>>
//
//
//    @POST("update-timezone")
//    @FormUrlEncoded
//    fun updateTimeZone(
//        @Field("userid") userid: String,
//        @Field("user_timezone_id") user_timezone_id: String,
//        @Field("user_time_format") user_time_format: String
//
//    ): Call<DataResponse<UpdateTimeZoneResponse>>
//
//
//    @POST("mentor-add-payment")
//    @FormUrlEncoded
//    fun addPaypalAccount(
//        @Field("userid") userid: String,
//        @Field("payment_type") payment_type: String,
//        @Field("paypal_email") paypal_email: String,
//        @Field("payout_frequency") payout_frequency: String,
//
//        ): Call<BaseResponse>
//
//
//    @POST("mentor-add-payment")
//    @FormUrlEncoded
//    fun addBankAccountAccount(
//        @Field("userid") userid: String,
//        @Field("payment_type") payment_type: String,
//        @Field("bank_name") bank_name: String,
//        @Field("bank_acc_number") bank_acc_number: String,
//        @Field("bank_code") bank_code: String,
//        @Field("payout_frequency") payout_frequency: String,
//
//        ): Call<BaseResponse>
//
//    @POST("mentor-get-payment")
//    @FormUrlEncoded
//    fun getPaymentDetail(
//        @Field("userid") userid: String,
//    ): Call<DataResponse<PaymentModel>>
//
//    @POST("mentor-delete-payment")
//    @FormUrlEncoded
//    fun deteletDetail(
//        @Field("id") id: String,
//    ): Call<BaseResponse>
//
//    @POST("mentor-update-payout")
//    @FormUrlEncoded
//    fun updatePaymentDetail(
//        @Field("userid") userid: String,
//        @Field("payout_frequency") payout_frequency: String,
//        @Field("payment_type") payment_type: String,
//    ): Call<BaseResponse>
//
//
//    @POST("user-invite")
//    @FormUrlEncoded
//    fun userInvite(
//        @Field("userid") userid: String,
//        @Field("mobile") mobile: String,
//        @Field("name") name: String,
//    ): Call<BaseResponse>
//
//
//    @POST("user-invite-list")
//    @FormUrlEncoded
//    fun userInviteList(
//        @Field("userid") userid: String
//    ): Call<DataResponse<InviteMentorList>>
//
//    @GET("privacy-policy")
//    fun privacyPolicy(
//
//
//    ): Call<DataResponse<TermsPolicyResponse>>
//
//
//    @GET("terms-conditions")
//    fun termsCondition(
//    ): Call<DataResponse<TermsPolicyResponse>>
//
//
//    @POST("mentor-set-availability")
//    @FormUrlEncoded
//    fun mentorSetAvailability(
//        @Field("mentor_id") mentor_id: String,
//        @Field("availability_type") availability_type: String,
//        @Field("available_date") available_date: String,
//        @Field("available_from") available_from: String,
//        @Field("available_to") available_to: String,
//        @Field("available_type") available_type: String
//    ): Call<DataResponse<MentorCallWaitingResponse>>
//
//
//    @POST("mentor-get-availability-time")
//    @FormUrlEncoded
//    fun mentorGetAvailability(
//        @Field("mentor_id") mentor_id: String,
//        @Field("schedule_date") schedule_date: String
//    ): Call<ListResponse<AvailabilityListResponse>>
//
//    /*@Field("id") id: String,*/
//    @POST("mentor-end-session")
//    @FormUrlEncoded
//    fun endSession(
//        @Field("mentor_id") mentor_id: String, @Field("wallet") wallet: String
//    ): Call<BaseResponse>
//
//    @POST("update-notifications")
//    @FormUrlEncoded
//    fun updateNotification(
//        @Field("userid") userid: String,
//        @Field("notification_upcoming_calls") notification_upcoming_calls: String,
//        @Field("notification_remind_me") notification_remind_me: String,
//        @Field("notification_new_call") notification_new_call: String,
//        @Field("notification_payment_received") notification_payment_received: String,
//    ): Call<DataResponse<UserProfileResponse>>
//
//
//    @POST("request-charity")
//    @FormUrlEncoded
//    fun requestCharity(
//        @Field("userid") userid: String,
//        @Field("name") name: String,
//        @Field("description") description: String
//    ): Call<BaseResponse>
//
//
//    @GET("charity-list")
//    fun charityList(): Call<ListResponse<CharityListResponse>>
//
//    @POST("donate-charity")
//    @FormUrlEncoded
//    fun donateCHarity(
//        @Field("userid") userid: String,
//        @Field("charity_id") charity_id: String,
//        @Field("charity_percentage") charity_percentage: String
//    ): Call<BaseResponse>
//
//
//    @POST("donation-history")
//    @FormUrlEncoded
//    fun donateHistory(
//        @Field("userid") userid: String
//
//    ): Call<ListResponse<CharityHistoryResponse>>
//
//
//    @POST("mentor-earnings")
//    @FormUrlEncoded
//    fun mentorEarnings(
//        @Field("userid") userid: String, @Field("month") month: String
//
//    ): Call<DataResponse<TotalEarningResponse>>
//
//    @POST("remove-donate-charity")
//    @FormUrlEncoded
//    fun removeCharity(
//        @Field("userid") userid: String
//
//    ): Call<BaseResponse>
//
//    @POST("check-user-invite-list")
//    @FormUrlEncoded
//    fun checkUserInvites(
//        @Field("contact") contact: String
//    ): Call<ListResponse<CheckUserInvitesResponse>>
//
//
//    @GET("information")
//    fun information(
//
//    ): Call<DataResponse<InformationDataResponse>>
//
//    @POST("mentor-today-upcoming-calls")
//    @FormUrlEncoded
//    fun mentorUpcomingCalls(
//        @Field("userid") userid: String
//    ): Call<ListResponse<MentorUpcomingCallResponse>>
//
//
//    @POST("mentor-get-availability-dates")
//    @FormUrlEncoded
//    fun mentorAvailableDates(
//        @Field("mentor_id") mentor_id: String
//    ): Call<ListResponse<MentorAvailableDatesResponse>>
//
//    @POST("mentor-delete-availability-time")
//    @FormUrlEncoded
//    fun deleteAvailability(
//        @Field("mentor_id") mentor_id: String,
//        @Field("available_from") available_from: String,
//        @Field("available_to") available_to: String,
//        @Field("wallet") wallet: String,
//    ): Call<BaseResponse>
//
//
//    @POST("mentor-home-screen")
//    @FormUrlEncoded
//    fun mentorHomeApi(
//        @Field("mentor_id") mentor_id: String, @Field("schedule_date") schedule_date: String
//    ): Call<DataResponse<HomeScreenResponse>>
//
//
//    @POST("add-delay")
//    @FormUrlEncoded
//    fun addDelay(
//        @Field("delay_seconds") delay_seconds: String, @Field("callid") callid: String
//    ): Call<BaseResponse>
//
//    @POST("notification-for-recording")
//    @FormUrlEncoded
//    fun recordingRequest(
//        @Field("userid") user_id: String
//    ): Call<BaseResponse>
//
//    @POST("recording-stop")
//    @FormUrlEncoded
//    fun stopRecording(
//        @Field("menteeid") menteeid: String,
//        @Field("sid") sid: String,
//        @Field("cname") cname: String,
//        @Field("resourceid") resourceid: String,
//        @Field("callid") callid: String,
//        @Field("mentorid") mentorid: String,
//    ): Call<DataResponse<StopRecodingModel>>
//
//    @POST("recording-list")
//    @FormUrlEncoded
//    fun getMyRecordingListing(
//        @Field("userid") userId: String
//    ): Call<ListResponse<VideoListModel>>
//
//    @POST("recording-delete")
//    @FormUrlEncoded
//    fun delteVideo(
//        @Field("recording_id")recording_id:String
//    ):Call<BaseResponse>
//
//    @POST("recording-convert")
//    @FormUrlEncoded
//    fun convertRecording(
//        @Field("id")id:String
//    ):Call<BaseResponse>

}