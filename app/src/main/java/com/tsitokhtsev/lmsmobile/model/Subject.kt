package com.tsitokhtsev.lmsmobile.model

import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("SubjectName")
    val name: String,
    @SerializedName("Score")
    val gradeOverall: Int,
    @SerializedName("componentGrades")
    val grades: String,
    @SerializedName("SemesterNumber")
    val semester: Int,
    @SerializedName("Credit")
    val credit: Int,
    @SerializedName("StatusID")
    val statusId: Int,
    @SerializedName("SubjectSelectionTypeName")
    val selectionType: String
)