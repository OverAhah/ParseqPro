package org.ParseqPro.enteties.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestData {
    private String name;
    private int pageZeroBasedNumber;
    private int pageSize;
}
