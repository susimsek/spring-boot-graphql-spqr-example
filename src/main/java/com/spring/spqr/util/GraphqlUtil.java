package com.spring.spqr.util;

import graphql.ExceptionWhileDataFetching;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class GraphqlUtil {

    public static final String GRAPHQL_MUTATION = "mutation";
    public static final String GRAPHQL_QUERY = "query";


    public Map<String,Object> getData(ExecutionResult result) throws Throwable {
        if(!result.getErrors().isEmpty()){
            GraphQLError error = result.getErrors().get(0);
            if (error instanceof ExceptionWhileDataFetching) {
                ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) error;
                throw exceptionError.getException();
            }
            else if(error instanceof Throwable){
                Throwable exceptionError = (Throwable) error;
                throw exceptionError;
            }
        }
        return result.getData();
    }
}
