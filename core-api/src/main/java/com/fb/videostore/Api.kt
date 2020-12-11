package com.fb.videostore

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RegisterMovieCommand(@TargetAggregateIdentifier val serialNumber: String, val title: String)
data class MovieRegisteredEvent(val serialNumber: String, val title: String)

data class RentMovieCommand(@TargetAggregateIdentifier val serialNumber: String)
data class MovieRentedEvent(val serialNumber: String)

data class ReturnMovieCommand(@TargetAggregateIdentifier val serialNumber: String)
data class MovieReturnedEvent(val serialNumber: String)

