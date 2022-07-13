package com.itsol.recruit.web.vm;

import lombok.Data;

import javax.annotation.Nullable;

@Data
public class SearchJobVM {

        @Nullable
        String skill;
        @Nullable
        String salary;
        @Nullable
        Long experience;
        @Nullable
        String address_work;
        @Nullable
        String working_form;
        @Nullable
        String academy;
        @Nullable
        Long pageNumber;
        @Nullable
        Long pageSize;

}
