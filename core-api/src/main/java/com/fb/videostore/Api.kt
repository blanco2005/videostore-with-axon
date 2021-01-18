package com.fb.videostore

import org.axonframework.modelling.command.TargetAggregateIdentifier


typealias SerialNumber = String

data class AddMovieToCatalogCommand(
        @TargetAggregateIdentifier val serialNumber: SerialNumber,
        val title: String
)


data class MovieAddedToCatalogEvent(
        val serialNumber: SerialNumber,
        val title: String
)

data class RentCommand(
        @TargetAggregateIdentifier val serialNumber: SerialNumber
)

data class MovieRentedEvent(
        val serialNumber: SerialNumber,
        val title: String
)
data class ReturnMovieCommand(
        @TargetAggregateIdentifier val serialNumber: SerialNumber
)

data class MovieReturnedToCatalogEvent(
        val serialNumber: SerialNumber,
        val title: String
)

class AllCatalogQuery()