package com.grophin.ticketingsystem.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TicketStatus")
@Data
public class TicketStatus implements Serializable {

    @Id
    @Column(name = "IDTicket")
    String ticketId;

    @Column(name = "Status")
    String status;
}
