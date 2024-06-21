package com.sps.database.common

interface Mapper<in DataModel, out DomainModel> {

    fun toDomain(from: DataModel): DomainModel

    fun toDomain(from: List<DataModel>): List<DomainModel> {
        return from.map(::toDomain)
    }
}