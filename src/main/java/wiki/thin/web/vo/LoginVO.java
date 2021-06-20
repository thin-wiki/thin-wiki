package wiki.thin.web.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Beldon
 */
@Data
public class LoginVO {
    private String account;

    @NotEmpty(message = "密码不能为空")
    private String password;

}
