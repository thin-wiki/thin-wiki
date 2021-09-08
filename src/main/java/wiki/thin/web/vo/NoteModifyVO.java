package wiki.thin.web.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NoteModifyVO {
    @NotEmpty(message = "内容不能为空")
    private String content;

}
