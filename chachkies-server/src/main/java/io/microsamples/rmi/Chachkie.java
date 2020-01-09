package io.microsamples.rmi;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
public class Chachkie implements Serializable {
    private UUID id;
    private String name;
    private Instant when;
}
