package wiki.thin.web.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author beldon
 */
@Data
public class ChangePasswordVO {
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
