package com.lickvalue.change.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VWapRequest {
    private String longSet;
    private String shortSet;
    private String email;
    private String symbol;
}
