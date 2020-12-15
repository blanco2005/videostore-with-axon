package com.fb.videostore

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RegisterMovieCommand(@TargetAggregateIdentifier val serialNumber: String, val title: String)
data class MovieRegisteredEvent(val serialNumber: String, val title: String)

data class RentMovieCommand(@TargetAggregateIdentifier val serialNumber: String)
data class MovieRentedEvent(val serialNumber: String, val title: String)
data class MovieRentalRejectedEvent(val serialNumber: String)

data class ReturnMovieCommand(@TargetAggregateIdentifier val serialNumber: String)
data class MovieReturnedEvent(val serialNumber: String, val title: String)

data class CreateCustomerCommand(@TargetAggregateIdentifier val customerName: String)
data class CustomerCreatedEvent(val customerName: String)

data class RequestRentalToCustomerCommand(@TargetAggregateIdentifier val customerName: String)
data class CustomerRentalApprovedEvent(val customerName: String)

data class DecreaseRentalsCommand(@TargetAggregateIdentifier val customerName: String)
data class CustomerDecreasedRentalsEvent(val customerName: String)

data class CreateRentalCommand(@TargetAggregateIdentifier val rentalId: String, val customer: String, val movie: String)
data class RentalCreatedEvent(val rentalId: String, val customer: String, val movie: String)

data class ApproveRentalCommand(@TargetAggregateIdentifier val rentalId: String)
data class RentalApprovedEvent(val rentalId: String, val movie: String, val customer: String)

data class RejectRentalCommand(@TargetAggregateIdentifier val rentalId: String)
data class RentalRejectedEvent(val rentalId: String)

data class TerminateRentalCommand(@TargetAggregateIdentifier val rentalId: String)
data class RentalTerminatedEvent(val rentalId: String, val customer: String, val movie: String)

data class CustomerRentalRejectedEvent(val customerName: String)

// QUERIES

class AllMovieSummaryQuery
class AllCustomersSummaryQuery
class OngoingRentalsQuery
data class RentalStatusQuery(val rentalId: String)
data class RentalIdByMovieSerialNumber(val serialNumber: String)
