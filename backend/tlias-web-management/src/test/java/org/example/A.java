package org.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.HandlerMapping;

public class A {
    public static void main(String[] args) {
        int [] arr={1,2,3};
        /*if (arr!=null)*/
        System.out.println(arr);
        @ConditionalOnClass

    }
}
