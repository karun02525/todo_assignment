package com.sps.assessmentdotoapp.presentation.ui.state
import com.sps.assessmentdotoapp.domain.model.Note


data class ItemViewState(
    val isLoading:Boolean=false,
    val items:List<Note> = emptyList(),
    val searchItems:List<Note> = emptyList(),
    val error:String?=null
)