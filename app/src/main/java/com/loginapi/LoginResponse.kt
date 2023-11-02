package com.loginapi

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class LoginResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("token")
	val token: String? = null
) : Parcelable

@Parcelize
data class Role(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("permission")
	val permission: List<String?>? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class LinksItem(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class User(

	@field:SerializedName("role")
	val role: Role? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("chapter_of_initiation")
	val chapterOfInitiation: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("is_notify")
	val isNotify: String? = null,

	@field:SerializedName("document_notify")
	val documentNotify: String? = null,

	@field:SerializedName("profile_image")
	val profileImage: String? = null,

	@field:SerializedName("current_chapter")
	val currentChapter: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("links")
	val links: List<LinksItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("delete_request_time")
	val deleteRequestTime: String? = null,

	@field:SerializedName("job_title")
	val jobTitle: String? = null,

	@field:SerializedName("delete_request")
	val deleteRequest: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("is_login")
	val isLogin: String? = null,

	@field:SerializedName("life_membership_number")
	val lifeMembershipNumber: String? = null,

	@field:SerializedName("university_college_attended")
	val universityCollegeAttended: String? = null,

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("district_chapter_position_title")
	val districtChapterPositionTitle: String? = null,

	@field:SerializedName("place_of_employment")
	val placeOfEmployment: String? = null,

	@field:SerializedName("alpha_id")
	val alphaId: String? = null,

	@field:SerializedName("chat_notify")
	val chatNotify: String? = null,

	@field:SerializedName("message_alert_notify")
	val messageAlertNotify: String? = null,

	@field:SerializedName("mobile")
	val mobile: String? = null,

	@field:SerializedName("lifetime_number")
	val lifetimeNumber: String? = null,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: String? = null,

	@field:SerializedName("permission")
	val permission: List<String?>? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: String? = null,

	@field:SerializedName("event_notify")
	val eventNotify: String? = null,

	@field:SerializedName("dob")
	val dob: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable
