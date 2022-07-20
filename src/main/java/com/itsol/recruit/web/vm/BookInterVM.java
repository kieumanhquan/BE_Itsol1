package com.itsol.recruit.web.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.util.Date;

@Data
public class BookInterVM {
    @Nullable
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date dateInterview;
    @Nullable
    String methodInterview;
    @Nullable
    String mediaType;
    @Nullable
    Time timeInterview;

}
