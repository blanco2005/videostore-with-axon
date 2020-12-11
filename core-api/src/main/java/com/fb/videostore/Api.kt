package com.fb.videostore

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RegisterMovieCommand(@TargetAggregateIdentifier val serialNumber: String, val title: String)
data class MovieRegisteredEvent(val serialNumber: String, val title: String)

data class RentMovieCommand(@TargetAggregateIdentifier val serialNumber: String)
data class MovieRentedEvent(val serialNumber: String, val title: String)

data class ReturnMovieCommand(@TargetAggregateIdentifier val serialNumber: String)
data class MovieReturnedEvent(val serialNumber: String, val title: String)

data class CreateCustomerCommand(@TargetAggregateIdentifier val customerName: String)
data class CustomerCreatedEvent(val customerName: String)

data class RequestRentalCommand(@TargetAggregateIdentifier val customerName: String)
data class CustomerRentalApprovedEvent(val customerName: String)

data class DecreaseRentalsCommand(@TargetAggregateIdentifier val customerName: String)
data class CustomerDecreasedRentalsEvent(@TargetAggregateIdentifier val customerName: String)

// QUERIES

class AllMovieSummaryQuery
class AllCustomersSummaryQuery

