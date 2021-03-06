package wiki.thin.web.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Beldon
 */
@Data
public class LoginVO {
    @NotEmpty(message = "account can't be empty")
    private String account;

    @NotEmpty(message = "password can't be empty")
    private String password;

}
