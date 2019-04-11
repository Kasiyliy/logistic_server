package kz.logistic.logistic_server.exceptions;


import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceException extends Exception{

    protected String message;
    protected ErrorCode errorCode;

}
