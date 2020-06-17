package response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import exception.enums.ResponseEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/*
 * @Description: REST API 异常返回结果
 * @Author: yuanye
 * @Company: yuntai
 * @Date: 2020/6/17 10:18 上午
 **/
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -6939421730067628529L;

    @ApiModelProperty("响应码")
    private Integer code;

    @ApiModelProperty("是否成功")
    private Boolean success;

    @ApiModelProperty("响应消息")
    private String message;

    @ApiModelProperty("错误信息集合")
    private List<String> errors;

    @ApiModelProperty("响应时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    public static ErrorResponse result() {
        return result(ResponseEnum.INTERNAL_SERVER_ERROR);
    }

    public static ErrorResponse result(ResponseEnum responseEnum){
        return result(responseEnum,null);
    }

    public static ErrorResponse result(Integer code,String message){
        return result(code,message,null);
    }

    public static ErrorResponse result(ResponseEnum responseEnum, List<String> errors){
        return result(responseEnum.getCode(),responseEnum.getMessage(),errors);
    }

    public static ErrorResponse result(Integer code,String message, List<String> errors){
        boolean success = false;
        if (code == ResponseEnum.OK.getCode()){
            success = true;
        }
        return  ErrorResponse.builder()
                .code(code)
                .message(message)
                .success(success)
                .time(LocalDateTime.now())
                .errors(errors)
                .build();
    }

}