package com.grophin.ticketingsystem.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="TicketResponse")
public class TicketResponse {

    @Id
    @Column(name = "TicketId")
    String ticketId;

    @Column(name = "Response")
    String response;
}
